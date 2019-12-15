package tabolin.ru.testapplication.di.components;

import dagger.Subcomponent;
import tabolin.ru.testapplication.activities.main_activity.fragments.users_list.UsersListFragment;
import tabolin.ru.testapplication.activities.main_activity.fragments.users_list.UsersListViewModel;

@Subcomponent
public interface UsersListComponent {
  void inject(UsersListFragment fragment);

  void inject(UsersListViewModel viewModel);
}
