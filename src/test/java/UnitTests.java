
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
import shop.VirtualItem;


import java.io.*;
import java.util.stream.Stream;



public class UnitTests {

    private Gson gson;
    private Cart cart;


    @Nested
    class JsonParserTest {

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
                    () -> Assertions.assertTrue(gsonFile.exists()),
                    () -> Assertions.assertTrue(gsonFile.isFile())
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
                    (Executable) () -> Assertions.assertTrue(gsonFile.canRead()),
                    (Executable) () -> Assertions.assertInstanceOf(Cart.class, gsonCart)
            );
            gsonFile.delete();

        }

        @ParameterizedTest
        @MethodSource ("invalidFileProvider")
        void exceptionTest(File file) {

            Parser parser = new JsonParser();
            File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");

            Assertions.assertThrows(NoSuchFileException.class, () -> {
                parser.readFromFile(gsonFile);
            });
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

        @AfterEach
        public void tearDown() {

        }

    }

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


    @Nested
    class CartTest {

        @BeforeEach
        public void setUp() {
        }

        @Test
        public void cartNameTest() {
            String name = "Test";
            cart = new Cart(name);

            Assertions.assertEquals(name, cart.getCartName());
        }

        @Test
        public void cartRealItemTest() {
            cart = new Cart("Test");
            double TAX = 0.2;

            RealItem car = new RealItem();
            double price = 3000;
            car.setPrice(price);
            cart.addRealItem(car);

            Parser parser = new JsonParser();
            parser.writeToFile(cart);

            double totalPrice = price + price*TAX;

            Assertions.assertEquals(totalPrice, cart.getTotalPrice());
        }


        @AfterEach
        public void tearDown() {
            File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
            gsonFile.delete();
        }

    }

}
