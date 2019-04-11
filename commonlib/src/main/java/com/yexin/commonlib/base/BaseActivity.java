package com.yexin.commonlib.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yexin.commonlib.manager.ActivityManager;
import com.yexin.commonlib.permissions.EasyPermissions;
import com.yexin.commonlib.permissions.SimplePermissionCallback;
import com.yexin.commonlib.utils.Logger;

import java.util.List;

/**
 * author: zengven
 * date: 2019/1/7
 * Desc: activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallback {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        ActivityManager.getInstance().addElement(this);
        Logger.e("current activity name: " + getClass().getSimpleName());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, new SimplePermissionCallback(this) {
            @Override
            public void onPermissionsFullGranted(int requestCode) {
                BaseActivity.this.onPermissionsFullGranted(requestCode);
            }

            @Override
            public void onPermissionsDenied(int requestCode, List<String> perms) {
                super.onPermissionsDenied(requestCode, perms);
                BaseActivity.this.onPermissionsDenied(requestCode, perms);
            }

            @Override
            public void onPermissionsGranted(int requestCode, List<String> perms) {
                super.onPermissionsGranted(requestCode, perms);
                BaseActivity.this.onPermissionsGranted(requestCode, perms);
            }
        });
    }

    @Override
    public void onPermissionsFullGranted(int requestCode) {

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeElement(this);
    }

    //设置字体不随系统设置的大小而改变
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
