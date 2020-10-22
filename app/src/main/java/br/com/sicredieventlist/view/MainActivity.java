package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.adapter.EventAdapter;
import br.com.sicredieventlist.model.Event;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEventList);
        Event event = new Event();
        event.setId(new Long(1));
        event.setTitle("Feira de adoção de animais na Redenção");
        event.setImage("http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png");
        List<Event> list = new ArrayList<>();
        list.add(event);
        listEvents(list);
    }

    private void listEvents(List<Event> list) {
        EventAdapter eventAdapter = new EventAdapter(this, list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(eventAdapter);
    }
}
