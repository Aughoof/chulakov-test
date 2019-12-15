package tabolin.ru.chulakovtest.activities.main_activity.fragments.users_list;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tabolin.ru.chulakovtest.MainApplication;
import tabolin.ru.chulakovtest.models.users.User;
import tabolin.ru.chulakovtest.services.UsersService;

public class UsersListViewModel extends ViewModel {
  @Inject UsersService usersService;
  Listener listener;
  CompositeDisposable disposable = new CompositeDisposable();

  /**
   * Inject UserListFragment dependency.
   *
   * @param context
   */
  public void inject(Context context) {
    MainApplication.getApplication(context)
        .getComponentsHolder()
        .getUsersListComponent()
        .inject(this);
  }

  /**
   * Clearing dependency injection.
   *
   * @param context
   */
  public void release(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().releaseUsersListComponent();
  }

  /**
   * Getting the array of models User from Github.
   *
   * @param offset - user id.
   */
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

  /** Listener getter. */
  public Listener getListener() {
    return listener;
  }

  /**
   * Listener setter.
   *
   * @param listener
   */
  public void setListener(Listener listener) {
    this.listener = listener;
  }

  public interface Listener {

    /**
     * Function adding a new array of models User to the old array.
     *
     * @param users - the array of models User.
     */
    void usersFound(List<User> users);

    /** Function showing message if users are ended. */
    void usersNotFound();

    /**
     * Function showing an error message.
     *
     * @param throwable
     */
    void error(Throwable throwable);
  }
}
