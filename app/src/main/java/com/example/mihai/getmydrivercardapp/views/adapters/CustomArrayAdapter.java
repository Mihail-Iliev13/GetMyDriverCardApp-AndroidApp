package com.example.mihai.getmydrivercardapp.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomArrayAdapter extends ArrayAdapter<CardApplication> {

    private int mLayout;

    public CustomArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mLayout = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mainViewHolder;
        CardApplication cardApplication = (CardApplication) getItem(position);

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(mLayout, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);

            viewHolder.showCardApplicationSubmissionDate(cardApplication);
            viewHolder.showDriverName(cardApplication);
            viewHolder.showDriverID(cardApplication);
            viewHolder.showCardApplicationStatus(cardApplication);

            convertView.setTag(viewHolder);

        } else {

            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.showCardApplicationSubmissionDate(cardApplication);
            mainViewHolder.showDriverName(cardApplication);
            mainViewHolder.showDriverID(cardApplication);
            mainViewHolder.showCardApplicationStatus(cardApplication);
        }

        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.tv_date_of_submission)
        TextView date;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_id)
        TextView id;
        @BindView(R.id.tv_status)
        TextView status;


        private ViewHolder (View view) {
            ButterKnife.bind(this, view);
        }


        private void showCardApplicationSubmissionDate(CardApplication cardApplication) {
            String text = date.getText().toString() + String.valueOf(cardApplication.getDateOfSubmission());
            this.date.setText(text);
        }

        private void showDriverName(CardApplication cardApplication) {
            String text = name.getText().toString()
                    + cardApplication.getDetails().getFirstNameLatin()
                    + cardApplication.getDetails().getSurNameLatin();
            this.name.setText(text);
        }

        private void showDriverID(CardApplication cardApplication){
            String text = id.getText().toString()
                    + cardApplication.getDetails().getDriverID();
            this.id.setText(text);
        }

        private void showCardApplicationStatus(CardApplication cardApplication) {
            String text = status.getText().toString()
                    + cardApplication.getStatus();
            this.status.setText(text);
        }
    }

}
