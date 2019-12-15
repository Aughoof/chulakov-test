package tabolin.ru.chulakovtest.di.components;

import dagger.Subcomponent;
import tabolin.ru.chulakovtest.activities.main_activity.fragments.users_list.UsersListFragment;
import tabolin.ru.chulakovtest.activities.main_activity.fragments.users_list.UsersListViewModel;

@Subcomponent
public interface UsersListComponent {
  void inject(UsersListFragment fragment);

  void inject(UsersListViewModel viewModel);
}
