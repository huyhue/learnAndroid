package com.example.library_project.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library_project.R;
import com.example.library_project.data.models.Book;
import com.example.library_project.view.ui.BookDetailActivity;

import java.util.List;

public class BookListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Book> bookList;

    public BookListAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        final Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.view_book_vertical, null);
        holder.name = rowView.findViewById(R.id.name_book);
        holder.type = rowView.findViewById(R.id.category_book);

        holder.name.setText(bookList.get(position).getName());
//        holder.type.setText(bookList.get(position).);

        // Book Item Click
        holder.itemLay = rowView.findViewById(R.id.item_book);
        holder.itemLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("BookId", bookList.get(position).getId());
                context.startActivity(intent);
                Activity activity = (Activity) context;
                activity.overridePendingTransition(0,0);
            }
        });


        return rowView;
    }

    public class Holder {
        LinearLayout itemLay;
        TextView name, type;
        ImageView heart;
    }
}
