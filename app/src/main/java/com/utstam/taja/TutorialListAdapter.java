package com.utstam.taja;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utstam.taja.databinding.TutorialListBinding;

import java.util.List;

public class TutorialListAdapter extends RecyclerView.Adapter<TutorialListAdapter.TutorialViewHolder> {
    // Accepted dataset = String[title][date]
    private List<Tutorial> localDataSet;
    Context context;

    public static class TutorialViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        Button btn_read;

        public TutorialViewHolder(TutorialListBinding tutorialListBinding) {
            super(tutorialListBinding.getRoot());

            // Initialize view component
            title = tutorialListBinding.titleText;
            date = tutorialListBinding.contentDate;
            btn_read = tutorialListBinding.btnRead;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDate() {
            return date;
        }

        public Button getBtn_read() {
            return btn_read;
        }
    }

    // Create constructor to set local dataset
    public TutorialListAdapter(List<Tutorial> localDataSet, Context context){
        this.localDataSet = localDataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TutorialListBinding tutorialListBinding = TutorialListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TutorialViewHolder(tutorialListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        holder.getTitle().setText(localDataSet.get(position).getTitle());
        holder.getDate().setText(localDataSet.get(position).getDate());
        holder.getBtn_read().setOnClickListener(new TutorialOnItemClickListener(position, new TutorialOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, TutorialDetailActivity.class);
                intent.putExtra("id", localDataSet.get(position).getId());
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}