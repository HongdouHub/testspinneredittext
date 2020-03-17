package com.example.chivas.testspinneredittext.adater;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chivas.commui.adapter.AbstractSpinnerEditTextAdapter;
import com.example.chivas.testspinneredittext.R;
import com.example.chivas.testspinneredittext.bean.MultiValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义可以编辑又可以下拉选中View对应的适配器(多参数)
 */
public class MultiSpinnerEditTextListAdapter extends AbstractSpinnerEditTextAdapter<MultiValueBean> {

    public MultiSpinnerEditTextListAdapter(Context context) {
        super(context);
    }

    static class ViewHolder {
        TextView tvType;
        TextView tvValue;

        ViewHolder(View convertView) {
            tvType = (TextView) convertView.findViewById(R.id.tv_type);
            tvValue = (TextView) convertView.findViewById(R.id.tv_value);
        }

        private void setParamValesFromBean(MultiValueBean scanWorkBean) {
            tvType.setText(scanWorkBean.getType());
            tvValue.setText(scanWorkBean.getValue());
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_spn_multi_edt_list_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final MultiValueBean scanWorkBean = mList.get(position);
        holder.setParamValesFromBean(scanWorkBean);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    String value = scanWorkBean.getValue();
                    onItemClickListener.onClickItem(TextUtils.isEmpty(value) ? DEFAULT_VALUE : value);
                }
            }
        });
        return convertView;
    }

    @Override
    protected List<MultiValueBean> getFilterList(List<MultiValueBean> rawList, String filterText) {
        if (TextUtils.isEmpty(filterText)) {
            return new ArrayList<>(rawList);
        }

        List<MultiValueBean> filterList = new ArrayList<>();
        String upperCase = filterText.toUpperCase();
        for (MultiValueBean bean : rawList) {
            if (bean.getType().contains(upperCase) || bean.getValue().contains(upperCase)) {
                filterList.add(bean);
            }
        }
        return filterList;
    }

    @Override
    public boolean isFilterEnable() {
        return true;
    }
}
