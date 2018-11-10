package com.example.guru.bookingku.Activity.History;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.guru.bookingku.Activity.History.utils.DateTimeUtils;
import com.example.guru.bookingku.Model.HistoryBooking;
import com.example.guru.bookingku.R;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<HistoryBooking> historyBookingList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<HistoryBooking> historyBookingList) {
        this.historyBookingList = historyBookingList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline_line_padding, parent, false);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        HistoryBooking historyBooking = historyBookingList.get(position);
        holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorPrimary));

        if (!historyBooking.getDate().isEmpty()) {
            holder.mDate.setVisibility(View.VISIBLE);
            holder.mDate.setText(DateTimeUtils.parseDateTime(historyBooking.getDate(), "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy"));
        } else
            holder.mDate.setVisibility(View.GONE);

        holder.mMessage.setText(historyBooking.getOrder());
    }

    @Override
    public int getItemCount() {
        return (historyBookingList != null ? historyBookingList.size() : 0);
    }

}
