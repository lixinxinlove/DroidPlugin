package com.lee.dema1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.morgoo.droidplugin.pm.PluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            //requestPermissions();
            requestPermissions();
        }
    }


    /**
     * 请求权限
     */
    private void requestPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "这个权限用来缓存图片", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 100);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 100);
            }

        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

        }
    }


    public void onClick1(View v) {


        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.apk";
        String filepath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test2.apk";
        // String filepath = Environment.getExternalStorageDirectory().getPath() + "/test.apk";

        //  String filepath=getAssets()


        File file = new File(filepath2);

//        if (!file.exists()) {
//            try {
//              //  file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


        Log.e("app", filepath);
        int falg = 100;
        try {

           // PackageManagerCompat

            falg = PluginManager.getInstance().installPackage(filepath, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.e("app", falg + "");
    }

    public void onClick2(View v) {

//        Intent intent = new Intent("com.lee.test.TestActivity");
//        intent.setPackage("com.lee.test");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        //  intent.setAction("com.lee.test.TestActivity");
//        startActivity(intent);


        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.lee.test");

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this,"空intent",Toast.LENGTH_SHORT).show();
        }


    }

}
