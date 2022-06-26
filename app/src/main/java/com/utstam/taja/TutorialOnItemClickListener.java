package com.utstam.taja;

import android.view.View;

public class TutorialOnItemClickListener implements View.OnClickListener{
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public TutorialOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v, position);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
