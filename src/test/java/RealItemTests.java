import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;

public class RealItemTests {

    private Gson gson;
    private Cart cart;
    @org.testng.annotations.BeforeMethod (groups = "second")
    public void setUp() {
        cart = new Cart("Test");
    }

    @Test(groups = "second")
    public void realItemTest() {
        RealItem car = new RealItem();
        double weight = 1560;
        car.setWeight(weight);
        cart.addRealItem(car);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        Assert.assertEquals(weight, car.getWeight(), "Assert validation for the weight is failed");
    }

    @org.testng.annotations.AfterMethod (groups = "second")
    public void tearDown() {
        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
        gsonFile.delete();
    }

}
