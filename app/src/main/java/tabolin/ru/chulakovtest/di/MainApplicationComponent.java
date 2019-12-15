package tabolin.ru.chulakovtest.di;

import javax.inject.Singleton;

import dagger.Component;
import tabolin.ru.chulakovtest.di.components.UserComponent;
import tabolin.ru.chulakovtest.di.components.UsersListComponent;
import tabolin.ru.chulakovtest.di.modules.ContextModule;
import tabolin.ru.chulakovtest.di.modules.ServiceModule;

@Component(modules = {ContextModule.class, ServiceModule.class})
@Singleton
public interface MainApplicationComponent {

  UserComponent createUserComponent();

  UsersListComponent createUsersListComponent();
}
