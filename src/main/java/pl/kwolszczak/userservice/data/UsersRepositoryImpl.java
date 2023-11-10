package pl.kwolszczak.userservice.data;

import pl.kwolszczak.userservice.model.User;

import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepository {

    Map<String, User> users = new HashMap<>();
    @Override
    public boolean save(User user) {

        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return true;
        }

        return false;
    }
}
