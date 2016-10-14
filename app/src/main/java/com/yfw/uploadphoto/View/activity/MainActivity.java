package com.yfw.uploadphoto.View.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yfw.uploadphoto.Persenter.UploadPhotoP;
import com.yfw.uploadphoto.R;
import com.yfw.uploadphoto.View.Interface.IUploadPhotoView;
import com.yfw.uploadphoto.View.adapter.AddImageAdapter;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class MainActivity extends Activity implements View.OnClickListener ,IUploadPhotoView{
    private SimpleDraweeView ivAddImage1,ivAddImage2,ivAddImage3,ivAddImage4;
    List<SimpleDraweeView> sv;
    /**
     * 上传地址
     */
    ArrayList<String> imageFiles;
    //默认显示图片
    private ArrayList<String> mResults;
    private static final int REQUEST_CODE = 868;
    private AddImageAdapter adapter;
    private ListView lvAdd;
    private UploadPhotoP uploadPhotoP;
    private Button submitID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv=new ArrayList<>();
        mResults=new ArrayList<>();
        submitID= (Button) findViewById(R.id.submitID);
        ivAddImage1= (SimpleDraweeView) findViewById(R.id.ivAddImage1);
        ivAddImage2= (SimpleDraweeView) findViewById(R.id.ivAddImage2);
        ivAddImage3= (SimpleDraweeView) findViewById(R.id.ivAddImage3);
        ivAddImage4= (SimpleDraweeView) findViewById(R.id.ivAddImage4);
        lvAdd= (ListView) findViewById(R.id.lvAdd);
        sv.add(ivAddImage1);
        sv.add(ivAddImage2);
        sv.add(ivAddImage3);
        sv.add(ivAddImage4);
        ivAddImage1.setOnClickListener(this);
        ivAddImage2.setOnClickListener(this);
        ivAddImage3.setOnClickListener(this);
        ivAddImage4.setOnClickListener(this);
        adapter = new AddImageAdapter(this, REQUEST_CODE, sv);
        lvAdd.setAdapter(adapter);
        uploadPhotoP=new UploadPhotoP(this);
        submitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ii","click");
                uploadPhotoP.uploadDatas();
            }
        });

    }

    @Override
    public void onClick(View view) {
        MultiImageSelector.create(this)
                // 是否显示相机. 默认为显示
                .count(4) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                // 单选模式.single()
                .multi() // 多选模式, 默认模式;
                .origin(mResults) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(this, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                mResults = (ArrayList<String>) path;
                adapter.setImages(mResults);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void showFailedError(int error) {

    }

    @Override
    public ArrayList getFilePath() {
        return adapter.getImages();
    }
}
