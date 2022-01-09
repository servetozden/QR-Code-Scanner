package com.example.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.qrcodescanner.databinding.ActivityMainBinding;
import com.example.qrcodescanner.fragment.QRCodeScannerFragment;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_main);


       binding.scannerButton.setOnClickListener(view -> {
           openCameraPermisson();
       });
    }

    public void openCameraPermisson() {

        String[] permissions = {Manifest.permission.CAMERA};
        // String rationale = "Please provide location permission so that you can ...";
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("Info")
                .setSettingsDialogTitle("Warning");

        Permissions.check(this/*context*/, permissions, "", options, new PermissionHandler() {
            @Override
            public void onGranted() {
                //burda qr okuma ekranı acılacak.
                binding.constraintScreen.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,new QRCodeScannerFragment()).commit();


            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                // permission denied, block the feature.
                Toast.makeText(context, "Yes! SMS Receved.", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onJustBlocked(Context context, ArrayList<String> justBlockedList, ArrayList<String> deniedPermissions) {
                super.onJustBlocked(context, justBlockedList, deniedPermissions);
            }

            @Override
            public boolean onBlocked(Context context, ArrayList<String> blockedList) {
                return super.onBlocked(context, blockedList);
            }
        });
    }
}