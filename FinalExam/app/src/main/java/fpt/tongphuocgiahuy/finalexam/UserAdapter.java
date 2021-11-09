package fpt.tongphuocgiahuy.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<User> mlist;
    OnListItemClickListener mListener ;

    public UserAdapter(Context context, OnListItemClickListener listener) {
        this.context = context;
        mListener = listener;
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
        holder.tvId.setText(user.getId()+"");
        holder.tvName.setText(user.getName());
        holder.tvQue.setText(user.getQueQuan());
        holder.tvNam.setText(user.getNamSinh());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onUpdateClick(holder.tvId.getText().toString(), holder.tvName.getText().toString(), holder.tvQue.getText().toString(),holder.tvNam.getText().toString());
            }
        });
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
        private TextView tvId, tvName, tvQue, tvNam;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvQue = itemView.findViewById(R.id.tv_que);
            tvNam = itemView.findViewById(R.id.tv_nam);
        }
    }
}
