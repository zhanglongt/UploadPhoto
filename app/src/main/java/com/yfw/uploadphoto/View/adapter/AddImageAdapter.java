package com.yfw.uploadphoto.View.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yfw.uploadphoto.R;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by whx on 2016/8/9.
 */
public class AddImageAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    /**
     * 图片地址
     */
    private ArrayList<String> images;
    private int REQUEST_CODE;
    private List<SimpleDraweeView> sv;

    public AddImageAdapter(Context context, int REQUEST_CODE, List<SimpleDraweeView> svAddImage) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        images = new ArrayList<>();
        this.REQUEST_CODE = REQUEST_CODE;
        this.sv = svAddImage;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images != null ? images.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Holder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_addimage, null);
            holder = new Holder();
            holder.delete = (ImageView) convertView.findViewById(R.id.ivRoundDelete0);
            holder.add = (SimpleDraweeView) convertView.findViewById(R.id.svAddImage);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (images.get(position).matches("^(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?")) {
            holder.add.setImageURI(images.get(position));
        } else {
            holder.add.setImageURI("file://" + images.get(position));
        }

        holder.delete.setVisibility(View.VISIBLE);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create(context)
                        // 是否显示相机. 默认为显示
                        .count(4) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                        // 单选模式.single()
                        .multi() // 多选模式, 默认模式;
                        .origin(images) // 默认已选择图片. 只有在选择模式为多选时有效
                        .start((Activity) context, REQUEST_CODE);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images.remove(position);
                sv.get(position).setVisibility(View.GONE);
                if (images.size() == 0) {
                    for (int i = 0; i < sv.size(); i++) {
                        sv.get(i).setVisibility(View.GONE);
                    }
                    sv.get(0).setVisibility(View.VISIBLE);
                }
                notifyDataSetChanged();
            }
        });

        sv.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create(context)
                        // 是否显示相机. 默认为显示
                        .count(4) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                        // 单选模式.single()
                        .multi() // 多选模式, 默认模式;
                        .origin(images) // 默认已选择图片. 只有在选择模式为多选时有效
                        .start((Activity) context, REQUEST_CODE);
            }
        });
        for (int i = 0; i < sv.size(); i++) {
            sv.get(i).setVisibility(View.GONE);

        }
        if (images.size() != 4 && images.size() != 0) {
            sv.get(images.size()).setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class Holder {
        ImageView delete;
        SimpleDraweeView add;
    }
}
