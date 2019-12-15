package tabolin.ru.testapplication.di;

import android.content.Context;

import tabolin.ru.testapplication.di.components.UserComponent;
import tabolin.ru.testapplication.di.components.UsersListComponent;
import tabolin.ru.testapplication.di.modules.ContextModule;

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
