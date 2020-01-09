package com.carta.llc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lombok.Getter;
import lombok.Setter;

public class UserIntegrationTest {
 
    @Test
    public void givenAnnotatedUser_thenHasGettersAndSetters() {
        User user = new User();
        user.setFirstName("Test");
        assertEquals(user.getFirstName(), "Test");
    }
 
    @Getter @Setter
    class User {
        private String firstName;
    }
}