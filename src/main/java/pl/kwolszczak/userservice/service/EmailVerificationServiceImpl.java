package pl.kwolszczak.userservice.service;

import pl.kwolszczak.userservice.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        //put user details into email queue

    }
}
