package com.tools.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tools.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS=1;
    public static final int CALL_PHONE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }
    public void click(View view){
        requestPermission();

    }
    //可以在Fragment中使用，用v13兼容包：FragmentCompat.requestPermissions() and FragmentCompat.shouldShowRequestPermissionRationale()和activity效果一样。
    private void requestPermission(){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        List<String> permissionsNeeded = new ArrayList<String>();
        List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.CALL_PHONE)){
            permissionsNeeded.add("打电话");
        }
        if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS)) {
            permissionsNeeded.add("读写联系人");
        }
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionsNeeded.add("定位权限");
        }
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionsNeeded.add("读写文件");
        }
        if(permissionsList.size()>0){
            if (permissionsNeeded.size()>0){
                ActivityCompat.requestPermissions(PermissionActivity.this,
                        permissionsList.toArray(new String[permissionsList.size()]),REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }
    private boolean addPermission(List<String> permissionList,String permission){
        if (ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission);//据说4.4 这个方法总是返回false
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                Map<Object,Integer> perms=new HashMap();
                perms.put(Manifest.permission.CALL_PHONE,PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_CONTACTS,PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION,PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE,PackageManager.PERMISSION_GRANTED);

                for (int i = 0; i < permissions.length; i++) {
                    perms.put(permissions[i],grantResults[i]);
                }

                if (perms.get(Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "因为要打电话给客服,请允许打电话权限", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                if (perms.get(Manifest.permission.WRITE_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "因为要保存联系人,读写联系人权限", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "因为要定位当前城市,请允许定位权限", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "因为要保存文件到本地请允许读写文件权限", Toast.LENGTH_LONG).show();
                    requestPermission();
                }
                break;
        }
    }

  /*//请求权限   同一组的任何一个权限被授权了，其他权限也自动被授权。例如，一旦WRITE_CONTACTS被授权了，app也有READ_CONTACTS和GET_ACCOUNTS权限了。
    public void requestPermission(){//申请的权限在manifest里面也要注册
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //没有权限
            //判断这个权限是否被用户拒绝过   //据说6.0以下shouldShowRequestPermissionRationale 这个方法总是返回false
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                //如果已经拒绝, 提供额外是权限说明
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("温馨提示")
                        .setMessage("你需要打电话权限才能 打电话给乘客哦")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE);
                            }
                        })
                        .setCancelable(false)
                        .show();
            }else{
                //进行权限请求
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE);
            }
        }else{//已经获得相关权限
            Intent intent = new Intent(); // 创建意图对象
            intent.setAction(Intent.ACTION_CALL); // 设置意图的动作(拨打电话)
            intent.setData(Uri.parse("tel:" + "18356031908")); // 设置意图的数据(电话号码)
            startActivity(intent); // 使用意图开启一个界面(拨打电话的界面)
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CALL_PHONE:
                //判断请求是否通过
                if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //请求通过
                    Intent intent = new Intent(); // 创建意图对象
                    intent.setAction(Intent.ACTION_CALL); // 设置意图的动作(拨打电话)
                    intent.setData(Uri.parse("tel:" + "18356031908")); // 设置意图的数据(电话号码)
                    startActivity(intent); // 使用意图开启一个界面(拨打电话的界面)
                }else{
                    //请求被拒绝
                    Toast.makeText(this, "被拒绝了", Toast.LENGTH_SHORT).show();
//                        ActivityCompat.requestPermissions(MainActivity.this,
//                                new String[]{Manifest.permission.CALL_PHONE},CALL_PHONE);
                }
                break;
        }
    }*/

}
