package fpt.edu.realtimedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    private Context context;
    private List<Job> mlist;
    private IClickListener mIClickListener;

    public interface IClickListener{
        void clickUpdate(Job job);
        void clickDelete(Job job);
    }

    public JobAdapter(Context context, IClickListener iClickListener) {
        this.context = context;
        this.mIClickListener = iClickListener;
    }

    public void setData(List<Job> list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent,false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = mlist.get(position);
        if (job == null){
            return;
        }
        holder.tvId.setText(job.getId()+"");
        holder.tvName.setText(job.getName());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.clickUpdate(job);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.clickDelete(job);
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

    public class JobViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, tvName;
        private Button btnUpdate, btnDelete;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            btnUpdate = itemView.findViewById(R.id.btn_update_job);
            btnDelete = itemView.findViewById(R.id.btn_delete_job);
        }
    }
}
