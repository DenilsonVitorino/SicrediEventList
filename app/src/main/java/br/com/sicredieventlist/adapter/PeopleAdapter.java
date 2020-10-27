package br.com.sicredieventlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.model.People;
import br.com.sicredieventlist.util.ImageManager;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {

    private Context context;
    private List<People> list;

    public PeopleAdapter(Context context, List<People> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PeopleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.people_cardview, viewGroup, false);
        return new PeopleAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PeopleAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.textViewPeopleListName.setText(list.get(i).getName());
        ImageManager.loadPeopleImage(context,myViewHolder.imageViewEventListImage,list.get(i).getPicture());
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewEventListImage;
        TextView textViewPeopleListName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEventListImage = (ImageView) itemView.findViewById(R.id.imageViewPeopleListImage);
            textViewPeopleListName = (TextView) itemView.findViewById(R.id.textViewPeopleListName);
        }
    }
}

