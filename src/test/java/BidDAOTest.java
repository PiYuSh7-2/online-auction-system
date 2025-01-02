

import com.onlineauction.dao.BidDAO;
import com.onlineauction.model.Bid;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

public class BidDAOTest {
    private static Connection connection;
    private BidDAO bidDAO;

    @BeforeAll
    static void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute(
            "CREATE TABLE bids (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "product_id INT, " +
            "user_id INT, " +
            "bid_amount DOUBLE, " +
            "bid_time TIMESTAMP);"
        );
    }

    @BeforeEach
    void setup() {
        bidDAO = new BidDAO();
    }

    @Test
    void testPlaceBid() {
        Bid bid = new Bid(0, 1, 1, 100.0, new Timestamp(new Date().getTime()));
        boolean result = bidDAO.placeBid(bid);
        assertTrue(result, "Bid should be placed successfully");
    }

    @Test
    void testGetBidsForProduct() {
        Bid bid1 = new Bid(0, 1, 1, 100.0, new Timestamp(new Date().getTime()));
        Bid bid2 = new Bid(0, 1, 2, 150.0, new Timestamp(new Date().getTime()));
        bidDAO.placeBid(bid1);
        bidDAO.placeBid(bid2);

        List<Bid> bids = bidDAO.getBidsForProduct(1);
        assertEquals(2, bids.size(), "Should retrieve 2 bids for the product");
    }

    @AfterAll
    static void teardownDatabase() throws Exception {
        connection.close();
    }
}
