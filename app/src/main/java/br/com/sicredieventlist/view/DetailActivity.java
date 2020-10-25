package br.com.sicredieventlist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.sicredieventlist.R;
import br.com.sicredieventlist.adapter.PeopleAdapter;
import br.com.sicredieventlist.model.Event;
import br.com.sicredieventlist.model.People;
import br.com.sicredieventlist.util.DateManager;
import br.com.sicredieventlist.util.ImageManager;
import br.com.sicredieventlist.util.NumberManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Event event;
    private ImageView imageViewDetailImage;
    private TextView textViewDetailTitle,
                     textViewDetailDescription,
                     textViewDetailDate,
                     textViewDetailPrice,
                     textViewDetailConfirm;
    private RecyclerView recyclerView;
    private Uri uri;

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
        textViewDetailConfirm = (TextView) findViewById(R.id.textViewDetailConfirm);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDetailConfirm);
        Intent intent = getIntent();
        uri = intent.getParcelableExtra("uri");
        Bundle bundle = intent.getExtras();
        event = (Event) bundle.getSerializable("event");
        ImageManager.loadEventImage(DetailActivity.this,imageViewDetailImage,event.getImage());
        textViewDetailTitle.setText(event.getTitle());
        textViewDetailDescription.setText(event.getDescription());
        textViewDetailDate.setText("Data: " + DateManager.formatDateTimePtBr(new Date(event.getDate())));
        textViewDetailPrice.setText("Preço: " + NumberManager.formatCurrenty(event.getPrice()));
        if (event.getPeople().size() > 0) {
            textViewDetailConfirm.setText("Confirmados.");
            recyclerView.setVisibility(View.VISIBLE);
            listPeople(event.getPeople());
        } else {
            textViewDetailConfirm.setText("Ainda não há confirmados.");
            recyclerView.setVisibility(View.GONE);
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_check:
                return true;

            case R.id.action_share: shareEvent(mountTextToShare());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(event.getLatitude(), event.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                openMap(event.getLatitude(),event.getLongitude());
            }
        });
    }

    private void openMap(Double lat, Double lng) {
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void listPeople(List<People> list) {
        PeopleAdapter peopleAdapter = new PeopleAdapter(this, list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(peopleAdapter);
    }

    private void shareEvent(String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private String mountTextToShare() {
        String text = textViewDetailTitle.getText().toString()
           + "\n\n" + textViewDetailDescription.getText().toString()
           + "\n\n" + textViewDetailDate.getText().toString()
           + "\n" + textViewDetailPrice.getText().toString();
        return text;
    }
}
