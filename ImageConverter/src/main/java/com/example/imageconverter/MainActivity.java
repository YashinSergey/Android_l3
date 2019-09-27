package com.example.imageconverter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    private static final String IMAGE_FOLDER = "/documents/document";
    private DisposableObserver observer;
    private Disposable disposable;
    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFile();

        observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) { }
            @SuppressLint("ShowToast")
            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "converting complete with error " + e.getLocalizedMessage(),Toast.LENGTH_LONG);
                Log.i("MainActivity", e.getLocalizedMessage());
            }
            @SuppressLint("ShowToast")
            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "converting complete",Toast.LENGTH_LONG);
            }
        };
    }

    private void readFile() {
        findViewById(R.id.btnSelectImage).setOnClickListener((view)-> {
            Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) return;
        if (setPermissions()) {
            Completable observable = Completable.create((emitter) -> {
                Uri selectedFile = data.getData();
                Bitmap bm = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), selectedFile);
                String root = Environment.getExternalStorageDirectory().toString();

                createNewDirectory(root, IMAGE_FOLDER);

                if (!sleep(4000)) return;

                convertImage(bm, root,IMAGE_FOLDER);

                emitter.onComplete();
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            disposable = observable.subscribe(
                    () -> {
                        if (alert.isShowing()) alert.cancel();
                        Toast.makeText(MainActivity.this, "converting complete", Toast.LENGTH_LONG).show();
                    },
                    (e) -> {
                        if (alert.isShowing()) alert.cancel();
                        Toast.makeText(MainActivity.this, "converting complete with error "
                                + (e).getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
            );
            showAlertDialog();
        }
    }

    private void createNewDirectory(String root, String folder) {
        File myDir = new File(root + folder);
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
    }

    private void convertImage(Bitmap bm, String root, String folder) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(String.format("%s%s/res.png", root, folder)));
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Conversation")
                .setMessage("converting in progress!")
                .setCancelable(false)
                .setNegativeButton("Cancel", (dialog, id) -> {
                    if (!disposable.isDisposed()) disposable.dispose();
                    dialog.cancel();
                });
        alert = builder.create();
        alert.show();
    }

    private Boolean setPermissions() {
        if (!checkPermissions()) {
            new Thread(() -> ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5)).start();
        }
        return checkPermissions();
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }
}
