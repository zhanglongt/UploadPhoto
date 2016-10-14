package com.yfw.uploadphoto.Model.biz.Interface;

/**
 * Created by zlt on 2016/10/14.
 */

public interface UploadPhotoListener<T>{
    void upLoadSuccess(T t);
    void upLoadFailure();
}
