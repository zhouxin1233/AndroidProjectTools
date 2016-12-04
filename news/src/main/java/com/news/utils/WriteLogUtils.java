package com.news.utils;

import android.os.Environment;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.news.BuildConfig;

import java.io.File;
import java.text.SimpleDateFormat;

import static com.blankj.utilcode.utils.TimeUtils.getCurTimeString;

/**
 * Created by Administrator on 2016/8/30 0030.
 */
public class WriteLogUtils {
    /**
     * 向文件中写入信息
     * @param info
     */
    public static void writeLog(String FileNameDir, String FileName, String info) {
        if (!BuildConfig.DEBUG){//上线模式的话就不打印了
            return;
        }
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/News/"+FileNameDir+"/";
        String name = FileName + getCurTimeString(new SimpleDateFormat("yy-MM-dd")) + ".txt";
//        info=TimeUtils.getCurTimeString()+info+"\r\n";
        info=TimeUtils.getCurTimeString(new SimpleDateFormat("HH:mm:ss"))+info+"\r\n";
        FileUtils.createOrExistsDir(dir);
        FileUtils.createOrExistsFile(name);
        FileUtils.writeFileFromString(dir+File.separator+name,info,true);
    }
}
