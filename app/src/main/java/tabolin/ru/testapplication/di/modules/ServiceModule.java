package tabolin.ru.testapplication.di.modules;

import dagger.Module;
import dagger.Provides;
import tabolin.ru.testapplication.services.UsersService;

@Module
public class ServiceModule {

  @Provides
  public UsersService provideUsersService() {
    return new UsersService();
  }
}
