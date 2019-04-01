package com.spring.rest.amqp.broking.event;

@SuppressWarnings("serial")
public class ProfileCreatedEvent implements java.io.Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String occupation;

    public ProfileCreatedEvent(String email, String firstName, String lastName, Integer age, String occupation) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }
}