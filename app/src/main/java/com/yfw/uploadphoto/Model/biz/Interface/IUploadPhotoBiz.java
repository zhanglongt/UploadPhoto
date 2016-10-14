package com.yfw.uploadphoto.Model.biz.Interface;

import java.io.File;

/**
 * Created by zlt on 2016/10/14.
 */

public interface IUploadPhotoBiz {
    void uploadImage(UploadPhotoListener listener, File file, String TK);
}
