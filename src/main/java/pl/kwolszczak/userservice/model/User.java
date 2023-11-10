package pl.kwolszczak.userservice.model;

public class User{

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeteadPassword;
    private String userId;
    private static int lastId=0;


    public User(String firstName, String lastName, String email, String password, String repeatedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.repeteadPassword = repeatedPassword;
        this.userId =String.valueOf(lastId++);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeteadPassword() {
        return repeteadPassword;
    }

    public String getId() {
        return userId;
    }
}
