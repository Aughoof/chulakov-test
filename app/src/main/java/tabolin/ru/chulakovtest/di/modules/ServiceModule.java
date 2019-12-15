package tabolin.ru.chulakovtest.di.modules;

import dagger.Module;
import dagger.Provides;
import tabolin.ru.chulakovtest.services.UsersService;

@Module
public class ServiceModule {

  @Provides
  public UsersService provideUsersService() {
    return new UsersService();
  }
}
