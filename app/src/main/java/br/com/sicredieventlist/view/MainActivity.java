package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.adapter.EventAdapter;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.service.EventService;
import br.com.sicredieventlist.util.ProgressDialogManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.ProgressDialog;
import android.os.Bundle;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEventList);
        progressDialog = new ProgressDialog(this);
        getListEvent();
    }


    private void listEvents(List<Event> list) {
        EventAdapter eventAdapter = new EventAdapter(this, list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(eventAdapter);
    }

    private void getListEvent() {
        EventService service = EventService.retrofit.create(EventService.class);
        Call<List<Event>> call = service.listAll();
        ProgressDialogManager.wait(progressDialog);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                final List<Event> list = response.body();
                System.out.println("title: " + list.get(1).getTitle());
                listEvents(list);
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                System.out.println("ErrorRetrofit: " + t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
}
