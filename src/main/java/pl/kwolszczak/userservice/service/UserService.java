package pl.kwolszczak.userservice.service;

import pl.kwolszczak.userservice.model.User;

public interface UserService {
    User createUser(String firstName, String lastName, String email, String password, String repeatPassword);
}
