

import com.onlineauction.dao.ProductDAO;
import com.onlineauction.model.Product;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

public class ProductDAOTest {
    private static Connection connection;
    private ProductDAO productDAO;

    @BeforeAll
    static void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute(
            "CREATE TABLE products (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255), " +
            "description VARCHAR(255), " +
            "starting_price DOUBLE, " +
            "end_date TIMESTAMP);"
        );
    }

    @BeforeEach
    void setup() {
        productDAO = new ProductDAO();
    }

    @Test
    void testAddProduct() {
        Product product = new Product(0, "Laptop", "High-performance laptop", 1000.0, new Timestamp(new Date().getTime()));
        boolean result = productDAO.addProduct(product);
        assertTrue(result, "Product should be added successfully");
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(0, "Laptop", "High-performance laptop", 1000.0, new Timestamp(new Date().getTime()));
        Product product2 = new Product(0, "Smartphone", "Latest model smartphone", 800.0, new Timestamp(new Date().getTime()));
        productDAO.addProduct(product1);
        productDAO.addProduct(product2);

        List<Product> products = productDAO.getAllProducts();
        assertEquals(2, products.size(), "Should retrieve 2 products");
    }

    @AfterAll
    static void teardownDatabase() throws Exception {
        connection.close();
    }
}

