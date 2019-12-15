package tabolin.ru.testapplication.di.components;

import dagger.Subcomponent;
import tabolin.ru.testapplication.activities.main_activity.fragments.user.UserFragment;
import tabolin.ru.testapplication.activities.main_activity.fragments.user.UserViewModel;

@Subcomponent
public interface UserComponent {
  void inject(UserFragment fragment);

  void inject(UserViewModel viewModel);
}
