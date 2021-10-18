package fpt.edu.retrofitdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> mlist;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<User> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mlist.get(position);
        if (user == null){
            return;
        }
        holder.tvTitle.setText(user.getTitle());
        holder.tvComplete.setText(user.isCompleted()+"");
    }

    @Override
    public int getItemCount() {
        if (mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvComplete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvComplete = itemView.findViewById(R.id.tv_completed);
        }
    }
}
