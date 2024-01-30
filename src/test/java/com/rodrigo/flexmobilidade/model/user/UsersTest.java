package com.rodrigo.flexmobilidade.model.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    public static final String ID = "1";
    public static final String NAME = "test";
    public static final String USERNAME = "test@email.com";
    public static final String PASSWORD = "12345";
    public static final UserRole USER_ROLE = UserRole.USER;
    public static final UserRole USER_ROLE_1 = UserRole.ADMIN;
    private static final Users USERS = new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE);
    private static final Users USERS_1 = new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE);
    private static final Users USERS_2= new Users(ID, NAME, USERNAME, PASSWORD, USER_ROLE_1);
    private static final String EXPECTED = "Users(id=1, name=test, email=test@email.com, password=12345, role=USER)";
    @InjectMocks
    private Users users = new Users();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAuthorities() {
        Users user = new Users("1", "John Doe", "john@example.com", "password", UserRole.ADMIN);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void getAuthoritiesNonAdmin() {
        Users user = new Users("2", "Jane Doe", "jane@example.com", "password", UserRole.USER);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertFalse(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void getUsername() {
        String username = USERS.getUsername();
        assertEquals(USERNAME, username);
    }

    @Test
    void isAccountNonExpired() {
        boolean accountNonExpired = USERS_1.isAccountNonExpired();

        assertTrue(accountNonExpired);
    }

    @Test
    void isAccountNonLocked() {
        boolean accountNonLocked = USERS.isAccountNonLocked();

        assertTrue(accountNonLocked);
    }

    @Test
    void isCredentialsNonExpired() {
        boolean credentialsNonExpired = USERS.isCredentialsNonExpired();

        assertTrue(credentialsNonExpired);
    }

    @Test
    void isEnabled() {
        boolean isEnable = USERS.isEnabled();

        assertTrue(isEnable);
    }

    @Test
    void getIdAndSetId() {
        USERS.setId(ID);
        String result = USERS.getId();
        assertEquals(ID, result);

    }

    @Test
    void getNameAndSetName() {
        USERS.setName(NAME);
        String result = USERS.getName();
        assertEquals(NAME, result);
    }

    @Test
    void getEmailAndSetEmail() {
        USERS.setEmail(USERNAME);
        String result = USERS.getEmail();
        assertEquals(USERNAME, result);
    }

    @Test
    void getPasswordAndSetPassword() {
        USERS.setPassword(PASSWORD);
        String result = USERS.getPassword();
        assertEquals(PASSWORD, result);
    }

    @Test
    void getRoleAndSetRole() {
        USERS.setRole(USER_ROLE);
        UserRole result = USERS.getRole();
        assertEquals(USER_ROLE, result);
    }

    @Test
    void testEquals() {
        assertEquals(USERS, USERS_1);
    }

    @Test
    void canEqual() {
        assertTrue(USERS.canEqual(USERS_1));
    }

    @Test
    void testHashCode() {
        assertEquals(USERS.hashCode(), USERS_1.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(EXPECTED, USERS.toString());
    }
}