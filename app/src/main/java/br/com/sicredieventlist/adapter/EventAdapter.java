package br.com.sicredieventlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.util.ImageManager;
import br.com.sicredieventlist.view.DetailActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private Context context;
    private List<Event> list;

    public EventAdapter(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.event_cardview, viewGroup, false);
        return new EventAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.textViewEventListTitle.setText(list.get(i).getTitle());
        ImageManager.loadEventImage(context,myViewHolder.imageViewEventListImage,list.get(i).getImage());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("event", (Serializable) list.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageViewEventListImage;
        TextView textViewEventListTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.event_cardview);
            imageViewEventListImage = (ImageView) itemView.findViewById(R.id.imageViewEventListImage);
            textViewEventListTitle = (TextView) itemView.findViewById(R.id.textViewEventListTitle);
        }
    }
}
