package br.com.sicredieventlist.service;

import java.util.List;
import br.com.sicredieventlist.model.Event;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface EventService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Headers({"Accept: application/json"})
    @GET("events")
    Call<List<Event>> listAll();

}
