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

  /**
   * Inject UserFragment dependency.
   *
   * @param context
   */
  public void inject(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().getUserComponent().inject(this);
  }

  /**
   * Clearing dependency injection.
   *
   * @param context
   */
  public void release(Context context) {
    MainApplication.getApplication(context).getComponentsHolder().releaseUserComponent();
  }

  /**
   * Getting model User from Github.
   *
   * @param login - login of current user.
   */
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
     * Function showing data from model User
     * @param user - model User.
     */
    void userFound(User user);

    /**
     * Function showing message if model User is null.
     */
    void userNotFound();

    /**
     * Function showing an error message.
     * @param throwable
     */
    void error(Throwable throwable);
  }
}
