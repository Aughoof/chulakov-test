package tabolin.ru.chulakovtest;

import android.app.Application;
import android.content.Context;

import tabolin.ru.chulakovtest.di.ComponentsHolder;

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
