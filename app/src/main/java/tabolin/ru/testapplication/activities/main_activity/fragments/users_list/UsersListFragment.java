package tabolin.ru.testapplication.activities.main_activity.fragments.users_list;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import tabolin.ru.testapplication.R;
import tabolin.ru.testapplication.activities.main_activity.fragments.user.UserFragment;
import tabolin.ru.testapplication.activities.main_activity.fragments.users_list.ui.UsersAdapter;
import tabolin.ru.testapplication.databinding.UsersListFragmentBinding;
import tabolin.ru.testapplication.models.users.User;

public class UsersListFragment extends Fragment
    implements UsersListViewModel.Listener, UsersAdapter.Listener {

  public UsersListFragmentBinding binding;
  private UsersListViewModel viewModel;
  private int offset = 0;
  private boolean usersEnded = false;
  private UsersAdapter usersAdapter;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.users_list_fragment, container, false);
    binding.toolbar.title.setText(getResources().getString(R.string.users_title));
    binding.toolbar.leftImg.setVisibility(View.INVISIBLE);
    binding.users.setLayoutManager(new LinearLayoutManager(getContext()));
    usersAdapter = new UsersAdapter(new ArrayList<>(), this);
    binding.users.setAdapter(usersAdapter);
    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
    viewModel.inject(getContext());
    viewModel.setListener(this);
    viewModel.getUsers(offset);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    viewModel.release(getContext());
  }

  @Override
  public void usersFound(List<User> users) {
    if (users.size() == 0) {
      usersEnded = true;
      return;
    }
    binding.progressLayout.getRoot().setVisibility(View.GONE);
    offset = users.get(users.size() - 1).getId();
    usersAdapter.addUsers(users);
    usersAdapter.notifyDataSetChanged();
  }

  @Override
  public void usersNotFound() {
    Toast.makeText(getContext(), getResources().getString(R.string.havent), Toast.LENGTH_LONG)
        .show();
  }

  @Override
  public void error(Throwable throwable) {
    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
  }

  @Override
  public void onClick(User user, int position) {
    offset = 0;
    usersEnded = false;
    Bundle arguments = new Bundle();
    arguments.putString(UserFragment.LOGIN, user.getLogin());
    Navigation.findNavController(getActivity(), R.id.host_fragment)
        .navigate(R.id.action_global_toUserFragment, arguments);
  }

  @Override
  public void listScrolledToEnd() {
    if (!usersEnded) {
      viewModel.getUsers(offset);
      binding.progressLayout.getRoot().setVisibility(View.VISIBLE);
    }
  }
}
