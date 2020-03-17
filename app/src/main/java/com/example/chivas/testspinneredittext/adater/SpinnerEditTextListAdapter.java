package com.example.chivas.testspinneredittext.adater;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chivas.commui.adapter.AbstractSpinnerEditTextAdapter;
import com.example.chivas.testspinneredittext.R;

/**
 * 自定义可以编辑又可以下拉选中View对应的适配器
 */
public class SpinnerEditTextListAdapter extends AbstractSpinnerEditTextAdapter<String> {

    public SpinnerEditTextListAdapter(Context context) {
        super(context);
    }

    static class ViewHolder {
        TextView tvItem;

        ViewHolder(View convertView) {
            tvItem = (TextView) convertView.findViewById(R.id.tvItem);
        }

        private void setParamValesFromBean(String item) {
            tvItem.setText(item);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_spn_edt_list_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String item = mList.get(position);
        holder.setParamValesFromBean(item);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    String item = mList.get(position);
                    onItemClickListener.onClickItem(TextUtils.isEmpty(item) ? DEFAULT_VALUE : item);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isFilterEnable() {
        return false;
    }
}
