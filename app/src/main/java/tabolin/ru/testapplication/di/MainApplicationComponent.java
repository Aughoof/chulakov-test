package tabolin.ru.testapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import tabolin.ru.testapplication.di.components.UserComponent;
import tabolin.ru.testapplication.di.components.UsersListComponent;
import tabolin.ru.testapplication.di.modules.ContextModule;
import tabolin.ru.testapplication.di.modules.ServiceModule;

@Component(modules = {ContextModule.class, ServiceModule.class})
@Singleton
public interface MainApplicationComponent {

  UserComponent createUserComponent();

  UsersListComponent createUsersListComponent();
}
