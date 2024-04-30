package pl.zabrze.zs10.myapplicationretrofit3a;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("pytania")
    public Call<List<Pytanie>>getPytania();
}
