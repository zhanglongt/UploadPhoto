package com.yfw.uploadphoto.View.Interface;

import java.util.ArrayList;

/**
 * Created by zlt on 2016/10/14.
 */

public interface IUploadPhotoView {
    void setDatas();
    void showFailedError(int error);
    ArrayList getFilePath();
}
