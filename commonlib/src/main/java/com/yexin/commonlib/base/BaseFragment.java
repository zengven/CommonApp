package com.yexin.commonlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.yexin.commonlib.permissions.EasyPermissions;
import com.yexin.commonlib.permissions.SimplePermissionCallback;

import java.util.List;

/**
 * author: zengven
 * date: 2018/1/5
 * Desc: A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements EasyPermissions.PermissionCallback {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, new SimplePermissionCallback(getActivity()) {
            @Override
            public void onPermissionsFullGranted(int requestCode) {
                BaseFragment.this.onPermissionsFullGranted(requestCode);
            }

            @Override
            public void onPermissionsDenied(int requestCode, List<String> perms) {
                super.onPermissionsDenied(requestCode, perms);
                BaseFragment.this.onPermissionsDenied(requestCode, perms);
            }

            @Override
            public void onPermissionsGranted(int requestCode, List<String> perms) {
                super.onPermissionsGranted(requestCode, perms);
                BaseFragment.this.onPermissionsGranted(requestCode, perms);
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
}
