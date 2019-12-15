package tabolin.ru.testapplication.activities.main_activity.fragments.users_list;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tabolin.ru.testapplication.MainApplication;
import tabolin.ru.testapplication.models.users.User;
import tabolin.ru.testapplication.services.UsersService;

public class UsersListViewModel extends ViewModel {
  @Inject UsersService usersService;
  Listener listener;
  CompositeDisposable disposable = new CompositeDisposable();

  public void inject(Context context) {
    MainApplication.getApplication(context)
        .getComponentsHolder()
        .getUsersListComponent()
        .inject(this);
  }

  public void release(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().releaseUsersListComponent();
  }

  public void getUsers(int offset) {
    disposable.add(
        usersService
            .getUsersApi()
            .getUsers(offset)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                users -> {
                  if (users != null) {
                    listener.usersFound(users);
                  } else {
                    listener.usersNotFound();
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
    void usersFound(List<User> users);

    void usersNotFound();

    void error(Throwable throwable);
  }
}
