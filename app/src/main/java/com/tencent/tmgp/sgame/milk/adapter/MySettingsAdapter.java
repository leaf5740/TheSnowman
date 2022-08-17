package com.tencent.tmgp.sgame.milk.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.tmgp.sgame.milk.R;
import com.tencent.tmgp.sgame.milk.draw.Drawing;

public class MySettingsAdapter extends BaseAdapter {
    private final Context mContext;
    public String[] item01 = {
        "方框", "射线", "血量",  "大招", "技能"};
        
    public MySettingsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return item01.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_floatingwindowlist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textview01 = convertView.findViewById(R.id.item_textview_01);
            viewHolder.textview02 = convertView.findViewById(R.id.item_textview_02);
            viewHolder.view01 = convertView.findViewById(R.id.xfcitemView1);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GradientDrawable DrawableYJ = new GradientDrawable();
        DrawableYJ.setShape(GradientDrawable.RECTANGLE);
        DrawableYJ.setCornerRadius(8);
        DrawableYJ.setColor(Drawing.itemcolor[position]);
        viewHolder.view01.setBackground(DrawableYJ);
        viewHolder.textview01.setText(item01[position] + "颜色:");
        viewHolder.textview02.setText("选取颜色");
        viewHolder.textview02.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemstr = item01[position];          
                    switch (position) {
                        case 0:
                            Toast.makeText(mContext, "0", Toast.LENGTH_SHORT).show();

                            break;
                        default:
                            Toast.makeText(mContext, "Error code---" + itemstr, Toast.LENGTH_SHORT).show();

                            break;
                    }

                }
            });
        return convertView;
    }

    class ViewHolder {
        TextView textview01;
        View view01;
        TextView textview02;

    }

}

