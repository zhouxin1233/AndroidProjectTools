package com.tools.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tools.R;
import com.tools.view.SelectPicPopupWindow;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CameraOrSelectPhoto extends AppCompatActivity {

    private ImageView mCameraOrPhoto;
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");//图片的存储目录
    SelectPicPopupWindow mSelectPicPopupWindow;
    File mCurrentPhotoFile;
    private static final int CAMERA_WITH_DATA = 1;//相机
    private static final int PHOTO_REQUEST = 2;//相机
    private static final int PHOTO_PICKED_WITH_DATA = 3;//裁剪
    private Bundle bitmapImage;
    private Bitmap bitmap;
    String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_or_select_photo);
        mCameraOrPhoto = (ImageView)findViewById(R.id.iv_camera_Or_photo);
    }
    public void click(View view){
        showHidePopupWindow();
    }
    private void showHidePopupWindow() {
        mSelectPicPopupWindow = new SelectPicPopupWindow(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPicPopupWindow.dismiss();
                PHOTO_DIR.mkdir();
                mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName()); //用当前时间给取得的图片命名
                switch (v.getId()) {
                    case R.id.popup_tv_camera:
                        //拍照
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
                            startActivityForResult(intent, CAMERA_WITH_DATA);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "没有sd卡", Toast.LENGTH_LONG);
                        }
                        break;
                    case R.id.popup_tv_photo:
                        //调用系统相册
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, PHOTO_REQUEST);
                        break;
                    case R.id.popup_tv_cancel:
                        if(mSelectPicPopupWindow!= null){
                            mSelectPicPopupWindow.dismiss();
                        }
                        break;
                }
            }
        });
        mSelectPicPopupWindow.showAtLocation(CameraOrSelectPhoto.this.findViewById(R.id.iv_camera_Or_photo), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    //用当前时间给取得的图片命名
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_WITH_DATA://相机返回来的数据
                    if (mCurrentPhotoFile == null) {
                        return;
                    }
                    try {
                        // 启动gallery去剪辑这个照片
                        getCropImageIntent(Uri.fromFile(mCurrentPhotoFile));
                    } catch (Exception e) {
                        Toast.makeText(this, "失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                case PHOTO_REQUEST://相册返回来的数据
                    if (data == null) {
                        return;
                    }
                    if (data.getData() != null) {
                        try {
                            // 启动gallery去剪辑这个照片
                            getCropImageIntent(data.getData());
                        } catch (Exception e) {
                            Toast.makeText(this, "失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "取消修改头像", Toast.LENGTH_LONG).show();
                    }
                    break;
                case PHOTO_PICKED_WITH_DATA://裁剪返回来的数据
                    if (data == null || TextUtils.isEmpty(data.toString())) {
                        return;
                    }
                    bitmapImage = data.getExtras();
                    if (bitmapImage != null) {
                        bitmap = (Bitmap) bitmapImage.get("data");
                        mCameraOrPhoto.setImageBitmap(bitmap);
                        SavePicInLocal(bitmap);
                    }
                    else {
                        Toast.makeText(this, "取消上传头像", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
    /**
     * Constructs an intent for image cropping. 调用图片剪辑程序
     * 剪裁后的图片跳转到新的界面
     */
    public void getCropImageIntent(Uri photoUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(photoUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
    }
    private void SavePicInLocal(Bitmap bitmap) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ByteArrayOutputStream baos = null; // 字节数组输出流
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] byteArray = baos.toByteArray();// 字节数组输出流转换成字节数组
            // 将字节数组写入到刚创建的图片文件中
            fos = new FileOutputStream(mCurrentPhotoFile);
            bos = new BufferedOutputStream(fos);
            bos.write(byteArray);
            photoPath = mCurrentPhotoFile.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
