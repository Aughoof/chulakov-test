package tabolin.ru.testapplication.activities.main_activity.fragments.users_list.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import tabolin.ru.testapplication.R;
import tabolin.ru.testapplication.databinding.UserItemBinding;
import tabolin.ru.testapplication.models.users.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

  List<User> users;
  Listener listener;

  public UsersAdapter(List<User> users, Listener listener) {
    this.listener = listener;
    this.users = users;
  }

  @NonNull
  @Override
  public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new UsersHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()), R.layout.user_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
    User user = users.get(position);
    Context context = holder.binding.getRoot().getContext();
    holder.binding.id.setText(
        String.format(
            "%s %s", context.getResources().getString(R.string.position_id), user.getId()));
    holder.binding.name.setText(user.getLogin());
    Picasso.get()
        .load(user.getAvatarUrl())
        .placeholder(R.drawable.ic_account_box)
        .into(holder.binding.img);
    holder.binding.getRoot().setOnClickListener(v -> listener.onClick(user, position));
    if (position == getItemCount() - 1) {
      listener.listScrolledToEnd();
    }
  }

  public void addUsers(List<User> users) {
    this.users.addAll(users);
  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class UsersHolder extends RecyclerView.ViewHolder {

    public UserItemBinding binding;

    public UsersHolder(UserItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }

  public interface Listener {
    void onClick(User user, int position);

    void listScrolledToEnd();
  }
}
