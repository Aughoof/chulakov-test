package tabolin.ru.chulakovtest.di.components;

import dagger.Subcomponent;
import tabolin.ru.chulakovtest.activities.main_activity.fragments.user.UserFragment;
import tabolin.ru.chulakovtest.activities.main_activity.fragments.user.UserViewModel;

@Subcomponent
public interface UserComponent {
  void inject(UserFragment fragment);

  void inject(UserViewModel viewModel);
}
