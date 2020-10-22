package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.util.DateManager;
import br.com.sicredieventlist.util.ImageManager;
import br.com.sicredieventlist.util.NumberManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Event event;
    private ImageView imageViewDetailImage;
    private TextView textViewDetailTitle,
                     textViewDetailDescription,
                     textViewDetailDate,
                     textViewDetailPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        event = new Event();
        imageViewDetailImage = (ImageView) findViewById(R.id.imageViewDetailImage);
        textViewDetailTitle = (TextView) findViewById(R.id.textViewDetailTitle);
        textViewDetailDescription = (TextView) findViewById(R.id.textViewDetailDescription);
        textViewDetailDate = (TextView) findViewById(R.id.textViewDetailDate);
        textViewDetailPrice = (TextView) findViewById(R.id.textViewDetailPrice);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        event = (Event) bundle.getSerializable("event");
        ImageManager.load(imageViewDetailImage,event.getImage());
        textViewDetailTitle.setText(event.getTitle());
        textViewDetailDescription.setText(event.getDescription());
        textViewDetailDate.setText("Data: " + DateManager.formatDateTimePtBr(event.getDate()));
        textViewDetailPrice.setText("Preço: " + NumberManager.formatCurrenty(event.getPrice()));
    }
}
