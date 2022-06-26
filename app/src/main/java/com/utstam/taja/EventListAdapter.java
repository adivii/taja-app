package com.utstam.taja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.utstam.taja.databinding.EventListBinding;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {
    List<Event> localDataSet;

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle, eventPlace, eventDate;

        public EventViewHolder(EventListBinding eventListBinding) {
            super(eventListBinding.getRoot());

            eventTitle = eventListBinding.titleText;
            eventPlace = eventListBinding.eventPlace;
            eventDate = eventListBinding.contentDate;
        }

        public TextView getEventTitle() {
            return eventTitle;
        }

        public TextView getEventPlace() {
            return eventPlace;
        }

        public TextView getEventDate() {
            return eventDate;
        }
    }

    public EventListAdapter(List<Event> localDataSet, Context context) {
        this.localDataSet = localDataSet;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventListBinding eventListBinding = EventListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EventViewHolder(eventListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.getEventTitle().setText(localDataSet.get(position).getEvent_title());
        holder.getEventPlace().setText(localDataSet.get(position).getEvent_place());
        holder.getEventDate().setText(localDataSet.get(position).getEvent_date());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
