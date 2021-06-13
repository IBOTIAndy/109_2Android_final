package com.bot.final_project_2021.RoomDatabase;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Data> data;
    private Activity activity;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(Activity activity, List<Data> data) {
        this.activity = activity;
        this.data = data;
    }
    /**建立對外接口*/
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(android.R.id.text1);
            view = itemView;
        }
    }
    /**更新資料*/
//    public void refreshView() {
//        new Thread(()->{
//            List<Data> data = DataRoomDatabase.getInstance(activity).getDataDao().displayAll();
//            this.data = data;
//            activity.runOnUiThread(() -> {
//                notifyDataSetChanged();
//            });
//        }).start();
//    }
//    /**刪除資料*/
//    public void deleteData(int position){
//        new Thread(()->{
//            DataRoomDatabase.getInstance(activity).getDataDao().deleteData(data.get(position).getId());
//            activity.runOnUiThread(()->{
//                notifyItemRemoved(position);
//                refreshView();
//            });
//        }).start();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getMoney());
        holder.view.setOnClickListener((v)->{
            onItemClickListener.onItemClick(data.get(position));
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    /**建立對外接口*/
    public interface OnItemClickListener {
        void onItemClick(Data data);
    }

}