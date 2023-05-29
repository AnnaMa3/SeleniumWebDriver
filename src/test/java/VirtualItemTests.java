import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.VirtualItem;

import java.io.File;

public class VirtualItemTests {

    private Gson gson;
    private Cart cart;

    @org.testng.annotations.BeforeMethod (groups = "second")
    public void setUp() {
        gson = new Gson();
        cart = new Cart("Test");
    }

    @Test(groups = "second")
    public void virtualItemTest() {

        VirtualItem disk = new VirtualItem();
        double size = 30000;
        disk.setSizeOnDisk(size);
        cart.addVirtualItem(disk);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        Assert.assertEquals(size, disk.getSizeOnDisk(), "Assert validation for the size on disk is failed");
    }

    @org.testng.annotations.AfterMethod (groups = "second")
    public void tearDown() {
        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");
        gsonFile.delete();
    }

}
