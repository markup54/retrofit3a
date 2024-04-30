package pl.zabrze.zs10.myapplicationretrofit3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/markup54/retrofit-pytania/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<Pytanie>> call = jsonPlaceHolderAPI.getPytania();
        call.enqueue(new Callback<List<Pytanie>>() {
            @Override
            public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
                else{
                    List<Pytanie> pytania = response.body();
                    Toast.makeText(MainActivity.this, pytania.get(0).getTresc(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable t) {

            }
        });
    }

    /*
    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/markup54/retro_proba/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Pytanie>> call = jsonPlaceHolderApi.getPytanie();
        call.enqueue(new Callback<List<Pytanie>>() {
            @Override
            public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response) {
                if(!response.isSuccessful()) {
                    textView.setText(response.code());
                    return;
                }
                textView.setText("");
                List<Pytanie> pytania = response.body();
                textView.setText(pytania.get(0).getTresc());
            }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
     */
}