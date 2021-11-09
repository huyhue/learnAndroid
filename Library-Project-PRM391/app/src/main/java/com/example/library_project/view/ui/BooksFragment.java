package com.example.library_project.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.library_project.R;
import com.example.library_project.data.constants.Common;
import com.example.library_project.data.models.Book;
import com.example.library_project.data.models.Category;
import com.example.library_project.view.adapter.BookListAdapter;
import com.example.library_project.view.ui.interfaces.ToolbarTitle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksFragment extends Fragment {

    int cat_id = 0;
    ToolbarTitle toolbarTitleCallback;
    GridView booksGrid;
    List<Book> bookList = new ArrayList<>();
    BookListAdapter bookListAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        toolbarTitleCallback = (ToolbarTitle) context;
    }

    public BooksFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_book_vertical, container,false);
//        booksGrid = v.findViewById(R.id.books_grid);

        // get category id
        Bundle args = getArguments();
        assert args != null;
        cat_id = args.getInt(Common.CAT_ID_KEY);

        if (cat_id > 0) {
            // Show Back Button and Set Title
//            showBackButtonCallback.showBackButton();
            toolbarTitleCallback.setToolbarTitle(args.getString(Common.CAT_NAME));
        }
//        fillGridView();
        return v;
    }

    private void fillGridView() {
        Call<List<Book>> listCall = MainActivity.apiInterface.getListBooks();
        listCall.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                bookList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getActivity(),"Fail API",Toast.LENGTH_SHORT).show();
            }
        });

        booksGrid.setNumColumns(2);
        bookListAdapter = new BookListAdapter(getActivity(), bookList);
        booksGrid.setAdapter(bookListAdapter);
    }
}
