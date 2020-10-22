package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.adapter.EventAdapter;
import br.com.sicredieventlist.model.Event;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
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
        event.setDescription("O Patas Dadas estará na Redenção, nesse domingo, " +
                "com cães para adoção e produtos à venda!\n\nNa ocasião, " +
                "teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, " +
                "os amigos e o chima, e venha aproveitar esse dia de sol com a gente e " +
                "com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ " +
                "de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os " +
                "tipos de doação:\n- guias e coleiras em bom estado\n- ração (as que " +
                "mais precisamos no momento são sênior e filhote)\n- roupinhas \n- cobertas " +
                "\n- remédios dentro do prazo de validade");
        event.setPrice(29.99);
        event.setDate(new Date(1534784400));
        event.setLatitude(-30.0392981);
        event.setLongitude(-51.2146267);

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
