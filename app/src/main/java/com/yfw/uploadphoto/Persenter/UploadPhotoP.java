package com.yfw.uploadphoto.Persenter;

import android.util.Log;

import com.yfw.uploadphoto.BitmapHelper;
import com.yfw.uploadphoto.Model.biz.Interface.IUploadPhotoBiz;
import com.yfw.uploadphoto.Model.biz.Interface.UploadPhotoListener;
import com.yfw.uploadphoto.Model.biz.UploadPhotoBiz;
import com.yfw.uploadphoto.View.Interface.IUploadPhotoView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by zlt on 2016/10/14.
 */

public class UploadPhotoP {
    private IUploadPhotoView iUploadPhotoView;
    private IUploadPhotoBiz iUploadPhotoBiz;

    public UploadPhotoP(IUploadPhotoView iUploadPhotoView) {
        this.iUploadPhotoView = iUploadPhotoView;
        this.iUploadPhotoBiz=new UploadPhotoBiz();
    }
    public void uploadDatas(){
        String TK="/YjtZF+sKPwLvJ+tcPmxdKLsqqb0Kj35iE1S0VYkl0IYcQfcW+G9sTF+a4Un37e5";
        ArrayList<String> filePath = BitmapHelper.compressPicture(iUploadPhotoView.getFilePath());
        for (String s : filePath) {
            Log.i("ii","file:"+s);
            File file = new File(s);
            if (file.exists()) {
                iUploadPhotoBiz.uploadImage(new UploadPhotoListener() {
                    @Override
                    public void upLoadSuccess(Object o) {

                    }

                    @Override
                    public void upLoadFailure() {

                    }
                }, file, TK);
            }
        }


    }
}
