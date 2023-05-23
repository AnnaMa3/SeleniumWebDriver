import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;

public class RealItemTests {

    private Gson gson;
    private Cart cart;

    @Nested
    class RealItemTest {

        @BeforeEach
        public void setUp() {
            cart = new Cart("Test");

        }

        @Test
        public void realItemTest() {
            RealItem car = new RealItem();
            double weight = 1560;
            car.setWeight(weight);
            cart.addRealItem(car);

            Parser parser = new JsonParser();
            parser.writeToFile(cart);

            Assertions.assertEquals(weight, car.getWeight());

        }

        @AfterEach
        public void tearDown() {
            File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
            gsonFile.delete();
        }

    }

}
