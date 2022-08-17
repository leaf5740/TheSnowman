package com.tencent.tmgp.sgame.milk.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.tencent.tmgp.sgame.milk.R;
import com.tencent.tmgp.sgame.milk.draw.Drawing;
import com.tencent.tmgp.sgame.milk.service.MilkService;
import com.tencent.tmgp.sgame.milk.utils.DocumentTool;
import com.tencent.tmgp.sgame.milk.utils.Milk;
import com.tencent.tmgp.sgame.milk.utils.MyToast;

import java.io.File;

public class MyDrawingAdapter extends BaseAdapter {
    private final Context mContext;

    public String[] itemString = {
            "初始化", "小地图", "方框", "射线", "血量", "大招CD", "技能CD"};

    public MyDrawingAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return itemString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_floatingwindowgrid, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = convertView.findViewById(R.id.item_fw_checkbox_1);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setText(itemString[position]);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String itemstr = itemString[position];
                switch (position) {
                    case 0:
                        if (isChecked) {
                            DocumentTool.writeData("/storage/emulated/0/.MyStop.log", "stop");
                            DocumentTool.deleteFile(new File("/storage/emulated/0/.MyStop.log"));
                                /*
                                 String dataljlj = "/storage/emulated/0/.MyData.log";
                                 if (DocumentTool.isFileExists(dataljlj)) {
                                 } else {
                                 DocumentTool.writeData(dataljlj, "");
                                 }*/
                            Milk.ShellJni(mContext, 1, "/assets/mytemp.so");
                            MilkService.mIsDrawing = true;
                            mContext.startService(new Intent(mContext, MilkService.class));

                            MyToast.showLong(mContext, "初始化成功");
                        } else {
                            DocumentTool.writeData("/storage/emulated/0/.MyStop.log", "stop");
                            MilkService.mIsDrawing = false;

                            mContext.stopService(new Intent(mContext, MilkService.class));

                            MyToast.showLong(mContext, "初始化已关闭");
                        }
                        break;
                    case 1:
                        if (isChecked) {
                            Drawing.Gn1 = true;
                            MyToast.showLong(mContext, "小地图开启成功");

                        } else {
                            Drawing.Gn1 = false;
                            MyToast.showLong(mContext, "小地图关闭成功");

                        }
                        break;
                    case 2:
                        if (isChecked) {
                            Drawing.Gn2 = true;
                            MyToast.showLong(mContext, "方框开启成功");
                        } else {
                            Drawing.Gn2 = false;
                            MyToast.showLong(mContext, "方框关闭成功");
                        }
                        break;
                    case 3:
                        if (isChecked) {
                            Drawing.Gn3 = true;
                            MyToast.showLong(mContext, "射线开启成功");
                        } else {
                            Drawing.Gn3 = false;
                            MyToast.showLong(mContext, "射线关闭成功");
                        }
                        break;
                    case 4:
                        if (isChecked) {
                            Drawing.Gn4 = true;
                            MyToast.showLong(mContext, "血量开启成功");
                        } else {
                            Drawing.Gn4 = false;
                            MyToast.showLong(mContext, "血量关闭成功");
                        }
                        break;
                    case 5:
                        if (isChecked) {
                            Drawing.Gn5 = true;
                            MyToast.showLong(mContext, "大招CD开启成功");
                        } else {
                            Drawing.Gn5 = false;
                            MyToast.showLong(mContext, "大招CD关闭成功");
                        }
                        break;
                    case 6:
                        if (isChecked) {
                            Drawing.Gn6 = true;
                            MyToast.showLong(mContext, "技能CD开启成功");
                        } else {
                            Drawing.Gn6 = false;
                            MyToast.showLong(mContext, "技能CD关闭成功");
                        }
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
        CheckBox checkBox;
    }

}
