package br.com.sicredieventlist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.util.ImageManager;

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
        ImageManager.load(myViewHolder.imageViewEventListImage,list.get(i).getImage());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewEventListImage;
        TextView textViewEventListTitle,
                textViewEventListDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEventListImage = (ImageView) itemView.findViewById(R.id.imageViewEventListImage);
            textViewEventListTitle = (TextView) itemView.findViewById(R.id.textViewEventListTitle);
            textViewEventListDetails = (TextView) itemView.findViewById(R.id.textViewEventListDetails);
        }
    }
}
