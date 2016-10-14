package com.yfw.uploadphoto.Model.biz;

import android.util.Log;

import com.yfw.uploadphoto.Model.biz.Interface.IUploadPhotoBiz;
import com.yfw.uploadphoto.Model.biz.Interface.UploadPhotoListener;

import java.io.File;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zlt on 2016/10/14.
 */

public class UploadPhotoBiz implements IUploadPhotoBiz {
    @Override
    public void uploadImage(UploadPhotoListener listener, File file, String TK) {
        String url="http://192.168.0.28:81/zgb/upload/cargo_image";
        RequestParams params=new RequestParams();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("TK", TK)
                .addFormDataPart("file", file.getName(), RequestBody.create(null, file))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"another\";filename=\"another.dex\""), RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        params.setCustomRequestBody(requestBody);

        Log.i("ii","parr:"+requestBody);
        HttpRequest.post(url,params,new BaseHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, Object o) {
                super.onSuccess(headers, o);
                Log.i("ii","i:"+o);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
            }
        });

    }
}
