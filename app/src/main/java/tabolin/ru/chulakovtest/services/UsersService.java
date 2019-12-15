package tabolin.ru.chulakovtest.services;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tabolin.ru.chulakovtest.BuildConfig;
import tabolin.ru.chulakovtest.services.api.UsersApi;

public class UsersService {
  private Retrofit retrofit;

  public UsersService() {
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    retrofit =
        new Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public UsersApi getUsersApi() {
    return retrofit.create(UsersApi.class);
  }
}
