package com.hussain.fixturewc18.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hussain.fixturewc18.R;
import com.hussain.fixturewc18.activies.MainActivityMatchFixture;
import com.hussain.fixturewc18.model.Record;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<Record> records;

    public DataAdapter(List<Record> records) {
        this.records = records;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewfixture,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        final Record record = records.get(position);

        holder.teamA.setText(record.getTeamA());
        holder.teamB.setText(record.getTeamB());
        holder.group.setText(record.getGroup());
        holder.matchPlay.setText(record.getMatchPlay());
        holder.vanue.setText(record.getVanue());
        holder.result.setText(record.getResult());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //private TextView id;
        private TextView teamA;
        private TextView teamB;
        private TextView group;
        private TextView matchPlay;
        private TextView vanue;
        private TextView result;
        //private TextView winner;

        public ViewHolder(View itemView) {
            super(itemView);
            //id = (TextView)itemView.findViewById(R.id.id);
            teamA = (TextView)itemView.findViewById(R.id.teamA);
            teamB = (TextView)itemView.findViewById(R.id.teamB);
            group = (TextView)itemView.findViewById(R.id.group);
            matchPlay = (TextView)itemView.findViewById(R.id.dateTime);
            vanue = (TextView)itemView.findViewById(R.id.vanue);
            result = (TextView)itemView.findViewById(R.id.result);
            //winner = (TextView)itemView.findViewById(R.id.winner);
        }
    }
}

