

import com.onlineauction.dao.UserDAO;
import com.onlineauction.model.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserDAOTest {
    private static Connection connection;
    private UserDAO userDAO;

    @BeforeAll
    static void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute(
            "CREATE TABLE users (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "username VARCHAR(255), " +
            "password VARCHAR(255), " +
            "email VARCHAR(255));"
        );
    }

    @BeforeEach
    void setup() {
        userDAO = new UserDAO();
    }

    @Test
    void testRegisterUser() {
        User user = new User( "testuser", "password123", "test@example.com");
        boolean result = userDAO.registerUser(user);
        assertTrue(result, "User should be registered successfully");
    }

    @Test
    void testValidateUser() {
        User user = new User( "validuser", "securepass", "valid@example.com");
        userDAO.registerUser(user);

        User validatedUser = userDAO.validateUser("validuser", "securepass");
        assertNotNull(validatedUser, "User validation should succeed");
        assertEquals("validuser", validatedUser.getUsername(), "Username should match");
    }

    @AfterAll
    static void teardownDatabase() throws Exception {
        connection.close();
    }
}


