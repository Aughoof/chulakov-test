package tabolin.ru.chulakovtest.services.api;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tabolin.ru.chulakovtest.models.users.User;

public interface UsersApi {
  @GET("users")
  Single<List<User>> getUsers(@Query("since") Integer integer);

  @GET("users/{login}")
  Single<User> getUser(@Path("login") String userLogin);
}
