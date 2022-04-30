package com.utstam.taja;

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
    private String[][] localDataSet;

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
    public TutorialListAdapter(String[][] localDataSet){
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TutorialListBinding tutorialListBinding = TutorialListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TutorialViewHolder(tutorialListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        holder.getTitle().setText(localDataSet[position][0]);
        holder.getDate().setText(localDataSet[position][1]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}