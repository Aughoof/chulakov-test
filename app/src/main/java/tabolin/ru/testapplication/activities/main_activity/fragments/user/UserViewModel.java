package tabolin.ru.testapplication.activities.main_activity.fragments.user;

import android.content.Context;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tabolin.ru.testapplication.MainApplication;
import tabolin.ru.testapplication.models.users.User;
import tabolin.ru.testapplication.services.UsersService;

public class UserViewModel extends ViewModel {
  @Inject UsersService usersService;
  CompositeDisposable disposable = new CompositeDisposable();
  private Listener listener;

  public void inject(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().getUserComponent().inject(this);
  }

  public void release(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().releaseUserComponent();
  }

  public void getUser(String login) {
    disposable.add(
        usersService
            .getUsersApi()
            .getUser(login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                user -> {
                  if (user != null) {
                    listener.userFound(user);
                  } else {
                    listener.userNotFound();
                  }
                },
                    throwable -> listener.error(throwable)));
  }

  public Listener getListener() {
    return listener;
  }

  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public interface Listener {
    void userFound(User user);

    void userNotFound();

    void error(Throwable throwable);
  }
}
