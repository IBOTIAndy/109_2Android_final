package com.bot.final_project_2021.Database;

import android.content.Context;
import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bot.final_project_2021.R;

import java.util.List;

public class MoneyListAdapter extends RecyclerView.Adapter<MoneyListAdapter.MoneyViewHolder> {
    class MoneyViewHolder extends RecyclerView.ViewHolder{
        private final TextView moneyItemView;

        private MoneyViewHolder(View itemView){
            super(itemView);
            moneyItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Money> mMoneys;

    public MoneyListAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    @Override
    public MoneyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MoneyViewHolder(itemView);
    }

    @Override public void onBindViewHolder(MoneyViewHolder holder, int position){   //設定recyclerview item
        String string = "";
        Money current = mMoneys.get(position);
        string += "日期: " + Integer.toString(current.getDate_year())
                + "/" + Integer.toString(current.getDate_month())
                + "/" + Integer.toString(current.getDate_day())
                + " [" + current.getTag() + "] ";
        if(current.getType()){
            string += "收入: ";
        }
        else{
            string += "支出: ";
        }
        string += Integer.toString(current.getMoney()) + "$"
                + "備註: " + current.getText();

        holder.moneyItemView.setText(string);
    }

    public void setMoneys(List<Money> moneys){
        mMoneys = moneys;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mMoneys != null){
            return mMoneys.size();
        }
        else{
            return 0;
        }
    }
}
