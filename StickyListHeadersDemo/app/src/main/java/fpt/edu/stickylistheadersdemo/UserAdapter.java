package fpt.edu.stickylistheadersdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class UserAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<String> list;

    public void setData(List<String> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder itemViewHolder;
        if(view == null){
            itemViewHolder = new ItemViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup,false);
            itemViewHolder.tvItem = view.findViewById(R.id.tv_item);
            view.setTag(itemViewHolder);
        }else{
            itemViewHolder = (ItemViewHolder) view.getTag();
        }
        itemViewHolder.tvItem.setText(list.get(i));
        return view;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        if (convertView == null){
            headerViewHolder = new HeaderViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent,false);
            headerViewHolder.tvHeader = convertView.findViewById(R.id.tv_header);
            convertView.setTag(headerViewHolder);
        }else{
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        headerViewHolder.tvHeader.setText(list.get(position).substring(0,1));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return list.get(position).subSequence(0,1).charAt(0);
    }

    public class HeaderViewHolder{
        private TextView tvHeader;
    }

    public class ItemViewHolder{
        private TextView tvItem;
    }
}
