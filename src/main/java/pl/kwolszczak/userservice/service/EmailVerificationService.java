package pl.kwolszczak.userservice.service;

import pl.kwolszczak.userservice.model.User;

public interface EmailVerificationService {

    void scheduleEmailConfirmation(User user);
}
