package pl.kwolszczak.userservice.data;

import pl.kwolszczak.userservice.model.User;

public interface UsersRepository {

    boolean save(User user);
}
