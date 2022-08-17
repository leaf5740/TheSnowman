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
import java.util.ArrayList;
import com.tencent.tmgp.sgame.milk.utils.MyToast;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import com.tencent.tmgp.sgame.milk.utils.SPUtils;

public class MyDrawingsAdapter extends BaseAdapter {
    public final Context mContext;
    public String[] item01 = {
        "小地图上调", "小地图下调","小地图左调", "小地图右调","方框上调","方框下调","方框左调","方框右调"};
    public int 地图上下,地图左右,方框上下,方框左右;
    public MyDrawingsAdapter(Context context) {
        mContext = context;
        地图上下 = Drawing.get小地图调上下();
        地图左右 = Drawing.get小地图调左右();
        方框上下 = Drawing.get方框调上下();
        方框左右 = Drawing.get方框调左右();
        
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_floatingwindowgriddraw, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.item_draw_list__textView1 = convertView.findViewById(R.id.itemfloatingwindowlistdrawTextView1);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_draw_list__textView1.setText(item01[position]);
        viewHolder.item_draw_list__textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemstr1 = item01[position];          
                    switch (position) {
                        case 0:
                            地图上下 = 地图上下 - 3;
                            Drawing.小地图调上下 = 地图上下;
                            SPUtils.setParam(mContext,"小地图调上下",地图上下);
                            break;
                        case 1:
                            地图上下 = 地图上下 + 3;
                            Drawing.小地图调上下 = 地图上下;
                            SPUtils.setParam(mContext,"小地图调上下",地图上下);
                            
                            break;
                        case 2:
                            地图左右 = 地图左右 - 3;
                            Drawing.小地图调左右 = 地图左右;
                            SPUtils.setParam(mContext,"小地图调左右",地图左右);
                            break;
                        case 3:
                            地图左右 = 地图左右 + 3;
                            Drawing.小地图调左右 = 地图左右;
                            SPUtils.setParam(mContext,"小地图调左右",地图左右);
                            break;
                        case 4:
                            方框上下 = 方框上下 - 3;
                            Drawing.方框调上下 = 方框上下;
                            SPUtils.setParam(mContext,"方框调上下",方框上下);
                            break;
                        case 5:
                            方框上下 = 方框上下 + 3;
                            Drawing.方框调上下 = 方框上下;
                            SPUtils.setParam(mContext,"方框调上下",方框上下);

                            break;
                        case 6:
                            方框左右 = 方框左右 - 3;
                            Drawing.方框调左右 = 方框左右;
                            SPUtils.setParam(mContext,"方框调左右",方框左右);
                            break;
                        case 7:
                            方框左右 = 方框左右 + 3;
                            Drawing.方框调左右 = 方框左右;
                            SPUtils.setParam(mContext,"方框调左右",方框左右);
                            break;
                        default:
                            Toast.makeText(mContext, "Error code---" + itemstr1, Toast.LENGTH_SHORT).show();

                            break;
                    }

                }
            });

        return convertView;
    }

    class ViewHolder {
        TextView item_draw_list__textView1;
        SeekBar item_draw_list_SeekBar1;

    }

}
