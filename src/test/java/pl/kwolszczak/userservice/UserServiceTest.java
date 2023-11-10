package pl.kwolszczak.userservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kwolszczak.userservice.data.UsersRepository;
import pl.kwolszczak.userservice.exceptions.EmailServiceException;
import pl.kwolszczak.userservice.exceptions.ExceptionServiceUser;
import pl.kwolszczak.userservice.model.User;
import pl.kwolszczak.userservice.service.EmailVerificationService;
import pl.kwolszczak.userservice.service.UserServiceImpl;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UsersRepository usersRepository;
    @Mock
    private EmailVerificationService emailVerificationService;

    @InjectMocks
    private UserServiceImpl userService; // mockito needs class not interface


    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;

    @BeforeEach
    void setUp() {
        System.out.println("start init setup");
        userService = new UserServiceImpl(usersRepository, emailVerificationService);
        firstName = "tom";
        lastName = "Volodia";
        email = "test@test.it";
        password = "1234567";
        repeatPassword = "1234567";
    }

    @Test
    @DisplayName("UserObject created")
    void testCreateUser_whenUserDetailsProvided_returnUserObject() {

        //mockito
        Mockito.when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);

        //
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //Mockito
        Mockito.verify(usersRepository, Mockito.times(2)).save(Mockito.any(User.class));

        Assertions.assertNotNull(user);
        Assertions.assertEquals(firstName, user.getFirstName(), () -> "wrong firstName");
        Assertions.assertEquals(lastName, user.getLastName(), () -> "wrong lastname");
        Assertions.assertEquals(email, user.getEmail(), () -> "wrong email");
        Assertions.assertNotNull(user.getId());
    }

    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {

        firstName = "";

        ExceptionServiceUser exception = Assertions.assertThrows(ExceptionServiceUser.class, () -> {
                    userService.createUser(firstName, lastName, email, password, repeatPassword);
                },
                () -> "No exception, wrong exception handled");

        Assertions.assertEquals("First name should not be empty", exception.getMessage(), "different messages");
    }

    //password -alphanumeric
    //password small, big character
    //name one character
    //..


    @Test
    void testUserService_whenRepeatPasswordIsDifferent_returnNull() {
        repeatPassword = "980";

        //Act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        Assertions.assertNull(user);
    }

    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUser() {
        Mockito.when(usersRepository.save(Mockito.any(User.class))).thenReturn(true);
        Mockito.doThrow(EmailServiceException.class)
                .when(emailVerificationService)
                .scheduleEmailConfirmation(Mockito.any());
    /*
        doNothing()
                .when(emailVerificationService)
                .scheduleEmailConfirmation(any());*/


        Assertions.assertThrows(EmailServiceException.class, ()->{
                userService.createUser(firstName, lastName, email, password, repeatPassword);},
                ()->" no exception found");
        Mockito.verify(emailVerificationService,Mockito.times(1)).scheduleEmailConfirmation(Mockito.any());

    }

}
