package fpt.edu.recycleviewexample;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user1, parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mlist.get(position);
        if (user == null){
            return;
        }
        holder.imgUser.setImageResource(user.getResourceImage());
        holder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if (mlist != null){
            return mlist.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgUser;
        private TextView tvName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_user1);
            tvName = itemView.findViewById(R.id.tv_name1);
        }
    }
}
