package tabolin.ru.testapplication;

import android.app.Application;
import android.content.Context;

import tabolin.ru.testapplication.di.ComponentsHolder;

public class MainApplication extends Application {
  ComponentsHolder componentsHolder;

  @Override
  public void onCreate() {
    super.onCreate();
    componentsHolder = new ComponentsHolder(getApplicationContext());
    componentsHolder.initialize();
  }

  public static MainApplication getApplication(Context context) {
    return (MainApplication) context.getApplicationContext();
  }

  public ComponentsHolder getComponentsHolder() {
    return componentsHolder;
  }
}
