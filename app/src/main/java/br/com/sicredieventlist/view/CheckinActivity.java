package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.model.Checkin;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.service.EventService;
import br.com.sicredieventlist.util.DialogManager;
import br.com.sicredieventlist.util.ProgressDialogManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CheckinActivity extends AppCompatActivity {

    private Long eventId;
    private ProgressDialog progressDialog;
    private EditText editTextCheckinNome,
                     editTextCheckinEmail;
    private Button buttonCheckinConcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Checkin");
        setContentView(R.layout.activity_checkin);
        editTextCheckinNome = (EditText) findViewById(R.id.editTextCheckinNome);
        editTextCheckinEmail = (EditText) findViewById(R.id.editTextCheckinEmail);
        buttonCheckinConcluir = (Button) findViewById(R.id.buttonCheckinConcluir);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        eventId = bundle.getLong("eventId");
        progressDialog = new ProgressDialog(this);
        buttonCheckinConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concludeCheckin();
            }
        });
    }

    private void concludeCheckin() {
        if (! editTextCheckinNome.getText().toString().equals("")
                && ! editTextCheckinEmail.getText().toString().equals("")) {
            Checkin checkin =  new Checkin();
            checkin.setEventId(eventId);
            checkin.setNome(editTextCheckinNome.getText().toString());
            checkin.setEmail(editTextCheckinEmail.getText().toString());
            EventService service = EventService.retrofit.create(EventService.class);
            Call<Checkin> call = service.save(checkin);
            ProgressDialogManager.wait(progressDialog);
            call.enqueue(new Callback<Checkin>() {
                @Override
                public void onResponse(Call<Checkin> call, Response<Checkin> response) {
                    DialogManager.showMessage(CheckinActivity.this,"Checkin conluído com sucesso!");
                    finish();
                    progressDialog.dismiss();
                }
                @Override
                public void onFailure(Call<Checkin> call, Throwable t) {
                    DialogManager.showMessage(CheckinActivity.this,"Falha ao concluir checkin, tente novamente!");
                    System.out.println("ErrorRetrofitSave: " + t.getMessage());
                    progressDialog.dismiss();
                }
            });

        }

    }
}
