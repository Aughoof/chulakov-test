package tabolin.ru.chulakovtest.di;

import android.content.Context;

import tabolin.ru.chulakovtest.di.components.UserComponent;
import tabolin.ru.chulakovtest.di.components.UsersListComponent;
import tabolin.ru.chulakovtest.di.modules.ContextModule;

public class ComponentsHolder {

  private Context context;
  private MainApplicationComponent mainApplicationComponent;
  private UserComponent userComponent;
  private UsersListComponent usersListComponent;

  public ComponentsHolder(Context context) {
    this.context = context;
  }

  public void initialize() {
    mainApplicationComponent =
        DaggerMainApplicationComponent.builder().contextModule(new ContextModule(context)).build();
  }

  public UserComponent getUserComponent() {
    if (userComponent == null) {
      userComponent = mainApplicationComponent.createUserComponent();
    }
    return userComponent;
  }

  public UsersListComponent getUsersListComponent() {
    if (usersListComponent == null) {
      usersListComponent = mainApplicationComponent.createUsersListComponent();
    }
    return usersListComponent;
  }

  public void releaseUsersListComponent() {
    usersListComponent = null;
  }

  public void releaseUserComponent() {
    userComponent = null;
  }
}
