import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.VirtualItem;

import java.io.File;

public class VirtualItemTests {

    private Gson gson;
    private Cart cart;

    @Nested
    class VirtualItemTest {

        @BeforeEach
        public void setUp() {
            gson = new Gson();
            cart = new Cart("Test");

        }

        @Test
        public void virtualItemTest() {

            VirtualItem disk = new VirtualItem();
            double size = 30000;
            disk.setSizeOnDisk(size);
            cart.addVirtualItem(disk);


            Parser parser = new JsonParser();
            parser.writeToFile(cart);

            Assertions.assertEquals(size, disk.getSizeOnDisk());

        }

        @AfterEach
        public void tearDown() {
            File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
            gsonFile.delete();
        }

    }
}
