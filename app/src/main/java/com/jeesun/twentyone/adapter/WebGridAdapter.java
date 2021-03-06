package com.jeesun.twentyone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeesun.twentyone.activity.PicActivity;
import com.jeesun.twentyone.R;
import com.jeesun.twentyone.customui.SavePicDialog;
import com.jeesun.twentyone.model.WebPicInfo;
import com.jeesun.twentyone.util.ContextUtil;
import com.jeesun.twentyone.util.ImageUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by simon on 2017/12/13.
 */

public class WebGridAdapter extends RecyclerView.Adapter<WebGridAdapter.ViewHolder>{
    private static final String TAG = WebGridAdapter.class.getName();
    private List<WebPicInfo> webPicInfoList;
    private static Context context;

    public WebGridAdapter(List<WebPicInfo> webPicInfoList, Context context) {
        this.webPicInfoList = webPicInfoList;
        WebGridAdapter.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        /*View itemView = View.inflate(context, R.layout.web_list_item, null);
        ViewHolder holder = new ViewHolder(itemView);*/
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WebPicInfo webPicInfo = webPicInfoList.get(position);
        holder.setData(webPicInfo);
    }

    @Override
    public int getItemCount() {
        if(null != webPicInfoList && webPicInfoList.size() > 0){
            return webPicInfoList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.item_staggered_icon);
            textView = itemView.findViewById(R.id.item_staggered_name);
        }

        public void setData(final WebPicInfo webPicInfo){
            if(webPicInfo.getCid() == imageView.getTag()){
                return;
            }

            //Log.i(TAG, webPicInfo.getImg_1366_768());

            Picasso.get()
                    .load(webPicInfo.getUrl())
                    .resize(ImageUtil.dp2px(context, 540), ImageUtil.dp2px(context, 270))
                    .placeholder(R.drawable.bg_default)
                    .error(R.drawable.bg_default)
                    .tag(ContextUtil.PICASSO_TAG_WEB)
                    .onlyScaleDown()
                    .into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PicActivity.class);
                    intent.putExtra("picPath", webPicInfo.getUrl());
                    intent.putExtra("picType", ContextUtil.PIC_WEB);
                    context.startActivity(intent);
                }
            });

            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    SavePicDialog dialog = new SavePicDialog(context, R.style.dialogStyle, webPicInfo);
                    dialog.setIvPicture(webPicInfo.getUrl());
                    dialog.show();

                    return true;
                }
            });
        }
    }
}
