package tabolin.ru.chulakovtest.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
  private Context context;

  public ContextModule(Context context) {
    this.context = context;
  }

  @Provides
  Context provideContext() {
    return context;
  }
}
