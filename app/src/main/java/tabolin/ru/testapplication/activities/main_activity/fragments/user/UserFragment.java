package tabolin.ru.testapplication.activities.main_activity.fragments.user;

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

import com.squareup.picasso.Picasso;

import androidx.navigation.Navigation;
import tabolin.ru.testapplication.R;
import tabolin.ru.testapplication.databinding.UserFragmentBinding;
import tabolin.ru.testapplication.models.users.User;

public class UserFragment extends Fragment implements UserViewModel.Listener {

  private UserViewModel viewModel;
  public static final String LOGIN = "LOGIN";
  public UserFragmentBinding binding;

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false);
    binding.toolbar.title.setText(getArguments().getString(LOGIN));
    binding.toolbar.leftImg.setOnClickListener(
        v -> Navigation.findNavController(getActivity(), R.id.host_fragment).popBackStack());
    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    viewModel.inject(getContext());
    viewModel.setListener(this);
    viewModel.getUser(getArguments().getString(LOGIN));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    viewModel.release(getContext());
  }

  @Override
  public void userFound(User user) {
    binding.name.setText(user.getLogin());
    Picasso.get()
        .load(user.getAvatarUrl())
        .placeholder(R.drawable.ic_account_box)
        .into(binding.img);
    binding.biography.setText(
        String.format(
            "%s %s\n %s %s\n %s %s\n",
            getResources().getString(R.string.user_biography),
            user.getBio() != null ? user.getBio() : getResources().getString(R.string.havent),
            getResources().getString(R.string.repos),
            user.getPublicRepos() != null
                ? user.getPublicRepos()
                : getResources().getString(R.string.havent),
            getResources().getString(R.string.followers),
            user.getFollowers() != null
                ? user.getFollowers()
                : getResources().getString(R.string.havent)));
  }

  @Override
  public void userNotFound() {
    Toast.makeText(
            getContext(), getResources().getString(R.string.user_not_found), Toast.LENGTH_LONG)
        .show();
  }

  @Override
  public void error(Throwable throwable) {
    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
  }
}
