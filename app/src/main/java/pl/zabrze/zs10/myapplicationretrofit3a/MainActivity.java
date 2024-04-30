package pl.zabrze.zs10.myapplicationretrofit3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Pytanie> pytania;
    TextView textView;
    Button button;

    int aktualne = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewTrescPytania);
        button = findViewById(R.id.button);
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
                    pytania = response.body();
                    Toast.makeText(MainActivity.this, pytania.get(0).getTresc(), Toast.LENGTH_SHORT).show();
                    wyswietlAktualenPytanie(aktualne);
                }
            }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable t) {

            }
        });
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualne++;
                        if(aktualne<pytania.size())
                            wyswietlAktualenPytanie(aktualne);
                        else{
                            aktualne --;
                            Toast.makeText(MainActivity.this, "Koniec testu", Toast.LENGTH_SHORT).show();
                            button.setVisibility(View.INVISIBLE);
                        }
                    }
                }
        );
    }

   private void wyswietlAktualenPytanie(int i){
        textView.setText(pytania.get(i).getTresc());
        //TODO: wypelnic radio
   }
}