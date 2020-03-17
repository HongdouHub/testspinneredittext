package com.example.chivas.commui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpinnerEditTextAdapter<E> extends CommonBaseAdapter<E>
        implements Filterable {

    private ArrayFilter mFilter;
    private volatile List<E> mRawList;
    protected OnItemClickListener onItemClickListener;
    protected static final String DEFAULT_VALUE = "";

    public interface OnItemClickListener {
        void onClickItem(String item);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AbstractSpinnerEditTextAdapter(Context context) {
        super(context);
    }

    @Override
    public void replaceList(List<E> list) {
        super.replaceList(list);
        this.mRawList = list;
    }

    @Override
    public Filter getFilter() {
        ArrayFilter filter;
        return (filter = mFilter) == null ? (mFilter = new ArrayFilter()) : filter;
    }

    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence content) {
            FilterResults results = new FilterResults();
            if (mRawList == null) {
                mRawList = new ArrayList<>(mList);
            }
            String filterText = TextUtils.isEmpty(content) ? DEFAULT_VALUE : content.toString();
            List<E> filterList = getFilterList(mRawList, filterText);

            if (filterList != null) {
                results.values = filterList;
                results.count = filterList.size();
            }
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList = (List<E>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    protected List<E> getFilterList(List<E> rawList, String filterText) {
        return null;
    }

    public abstract boolean isFilterEnable();
}
