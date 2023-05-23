import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;
import java.util.stream.Stream;

public class JsonParserTests {
    private Gson gson;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        gson = new Gson();
        cart = new Cart("Test");
    }

    @Test
    public void fileExistTest() {

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        Assertions.assertAll(
                    "GroupedAssertionFileExist",
                    () -> Assertions.assertTrue(gsonFile.exists(), "Assert validation for the file existing price is failed"),
                    () -> Assertions.assertTrue(gsonFile.isFile(), "Assert file validation is failed")
        );

        gsonFile.delete();
    }

    @Disabled
    @Test
    public void readFromFileTest() {

        Parser parser = new JsonParser();

        RealItem car = new RealItem();
        car.setName("A");
        cart.addRealItem(car);

        parser.writeToFile(cart);

        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
        Cart gsonCart = parser.readFromFile(gsonFile);

        cart.showItems();
        gsonCart.showItems();

        Assertions.assertAll(
                    "GroupedAssertionReadFromFile",
                    (Executable) () -> Assertions.assertTrue(gsonFile.canRead(), "Assert validation for the file's readability is failed"),
                    (Executable) () -> Assertions.assertInstanceOf(Cart.class, gsonCart, "Assert instance validation for the cart is failed")
        );
        gsonFile.delete();
    }

    @ParameterizedTest
    @MethodSource("invalidFileProvider")
    void exceptionTest(File file) {

        Parser parser = new JsonParser();
        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        Assertions.assertThrows(NoSuchFileException.class, () -> {
                parser.readFromFile(gsonFile);
        }, "Assert exception validation is failed");
    }
    private static Stream<File> invalidFileProvider(){
        return Stream.of(
                    new File("src/main/Test.json"),
                    new File("src/main/resources/test.json"),
                    new File("src/main/resources/Test"),
                    new File(""),
                    new File("src/main/Test")
        );
    }

}
