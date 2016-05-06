package com.example.panweijie.sdcarddemo;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;

/**
 * Created by panweijie on 16/1/16.
 */
public class FileService {
    private Context context;

    public FileService(Context context) {
        this.context = context;
    }

    public FileService() {

    }


    public String getFileFromSdcard(String fileName) {
        FileInputStream inputStream = null;
        //缓冲的流，和磁盘无关，不需要关闭
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
      if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
          try {
              inputStream = new FileInputStream(file);
              int len = 0;
              byte[] data = new byte[1024];
              while ((len = inputStream.read(data))!=-1){
                outStream.write(data,0,len);
              }

          }catch (FileNotFoundException ex){
              ex.printStackTrace();
          }catch (IOException e){
              e.printStackTrace();
          }finally {
              {
                  if (inputStream != null){
                      try {
                          inputStream.close();
                      }catch (IOException ex){
                          ex.printStackTrace();
                      }
                  }
              }
          }
      }
        return new String(outStream.toByteArray());
    }


    /**
     * @param fileName 文件的名称
     * @param content  文件内容
     * @return
     */
    public boolean saveContentToSdcard(String fileName, String content) {
        boolean flag = false;
        FileOutputStream fileOutputStream = null;
        //获取sdcard卡所在路径
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        //判断sdcard是否可用

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(content.getBytes());
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }


        }
        return flag;
    }

}
