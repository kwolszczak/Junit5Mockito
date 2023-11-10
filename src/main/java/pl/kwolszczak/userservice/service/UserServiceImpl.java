package pl.kwolszczak.userservice.service;

import pl.kwolszczak.userservice.data.UsersRepository;
import pl.kwolszczak.userservice.exceptions.EmailServiceException;
import pl.kwolszczak.userservice.exceptions.ExceptionServiceUser;
import pl.kwolszczak.userservice.exceptions.UserServiceException;
import pl.kwolszczak.userservice.model.User;

public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private EmailVerificationService emailVerificationService ;

    public UserServiceImpl(UsersRepository usersRepository,EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

        if (firstName.isEmpty()) {
            throw new ExceptionServiceUser("First name should not be empty");

        }

        if (!password.equals(repeatPassword)) {
            return null;
        }

        User newUser = new User(firstName, lastName, email, password, repeatPassword);

        boolean isUserCreated = usersRepository.save(newUser);
        try {
            isUserCreated = usersRepository.save(newUser);
        } catch (RuntimeException exception) {
            throw new UserServiceException(exception.getMessage());
        }

        if (!isUserCreated){
            throw new UserServiceException("could not create user");
        }


        try {
            emailVerificationService.scheduleEmailConfirmation(newUser);
        } catch (Exception e) {
            throw new EmailServiceException(e.getMessage());
        }

        return newUser;
    }
}
