import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;

public class CartTests {

    private Gson gson;
    private Cart cart;


    @Test
    public void cartNameTest() {
        String name = "Test";
        cart = new Cart(name);

        Assertions.assertEquals(name, cart.getCartName(), "Assert validation for the Cart name is failed");
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

        Assertions.assertEquals(totalPrice, cart.getTotalPrice(), "Assert validation for the total price is failed");
    }


    @AfterEach
    public void tearDown() {
        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
        gsonFile.delete();
    }

}
