package com.example.mihai.getmydrivercardapp.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mihai.getmydrivercardapp.R;
import com.example.mihai.getmydrivercardapp.models.CardApplication;
import com.example.mihai.getmydrivercardapp.models.enums.CardApplicationStatus;
import com.example.mihai.getmydrivercardapp.views.fragments.viewsInterfaces.CardApplicationListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardApplicationArrayAdapter extends ArrayAdapter<CardApplication> {

    private int mLayout;
    private CardApplicationListView mCardApplicationListView;

    public CardApplicationArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mLayout = resource;
    }

    public void setCardApplicationListView(CardApplicationListView listView) {
        this.mCardApplicationListView = listView;
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

            viewHolder.showCardApplicationSubmissionDate(Objects.requireNonNull(cardApplication));
            viewHolder.showDriverName(cardApplication);
            viewHolder.showDriverID(cardApplication);
            viewHolder.showCardApplicationStatus(cardApplication);
            viewHolder.setButtonTag(position);

            convertView.setTag(viewHolder);

        } else {

            mainViewHolder = (ViewHolder) convertView.getTag();
            mainViewHolder.showCardApplicationSubmissionDate(Objects.requireNonNull(cardApplication));
            mainViewHolder.showDriverName(cardApplication);
            mainViewHolder.showDriverID(cardApplication);
            mainViewHolder.showCardApplicationStatus(cardApplication);
            mainViewHolder.setButtonTag(position);
        }

        return convertView;
    }


    class ViewHolder {
        @BindView(R.id.tv_date)
        TextView date;
        @BindView(R.id.tv_full_name)
        TextView name;
        @BindView(R.id.tv_driver_id)
        TextView id;
        @BindView(R.id.tv_status_text)
        TextView status;
        @BindView(R.id.btn_change_status)
        Button changeStatus;


        private ViewHolder (View view) {
            ButterKnife.bind(this, view);
            changeStatus.setOnClickListener(v -> {
                int position = (int) changeStatus.getTag();
                CardApplication cardApplication = getItem(position);
                mCardApplicationListView.setSelectedCardApplication(cardApplication);
                mCardApplicationListView.showStatusDialog();
            });
        }

        @SuppressLint("SimpleDateFormat")
        private void showCardApplicationSubmissionDate(CardApplication cardApplication) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String date = df.format(cardApplication.getDateOfSubmission());;
            this.date.setText(date);
        }

        private void showDriverName(CardApplication cardApplication) {
            String firstName = cardApplication.getDetails().getFirstNameLatin();
            String surName = cardApplication.getDetails().getSurNameLatin();
            String fullName = firstName + " " + surName;
            this.name.setText(fullName);
        }

        private void showDriverID(CardApplication cardApplication){
            String id = cardApplication.getDetails().getDriverID();
            this.id.setText(id);
        }

        private void showCardApplicationStatus(CardApplication cardApplication) {
            CardApplicationStatus status = cardApplication.getStatus();
            String statusStr = status.toString();
            switch (status){
                case COMPLETED:
                    this.status.setTextColor(Color.GREEN);
                    break;
                case APPROVED:
                    this.status.setTextColor(Color.BLUE);
                    statusStr = "IN PROGRESS";
                    break;
                case REJECTED:
                    this.status.setTextColor(Color.RED);
                    break;
                case NEW:
                    this.status.setTextColor(Color.YELLOW);
                    break;
                    default:
                        break;
            }

            this.status.setText(statusStr);
        }

        private void setButtonTag(int position) {
            changeStatus.setTag(position);
        }

    }

}
