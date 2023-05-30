import com.google.gson.Gson;

import org.mockito.Mock;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static org.mockito.Mockito.*;

public class JsonParserTests {
    private Gson gson;
    private Cart cart;


    @Parameters({"cart name"})
    @org.testng.annotations.BeforeMethod (groups = "first")
    public void setUp(@Optional String cartName) {
        gson = new Gson();
        cart = new Cart(cartName);
    }

    @Test(groups = "first")
    public void fileExistTest() {

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        File gsonFile = new File("src/main/resources/" + cart.getCartName() + ".json");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(gsonFile.exists(), "Assert validation for the file existing price is failed");
        softAssert.assertTrue(gsonFile.isFile(), "Assert file validation is failed");
        softAssert.assertAll();

        gsonFile.delete();
    }

    @Test(enabled = false)
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
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(gsonFile.canRead(), "Assert validation for the file's readability is failed");
        softAssert.assertTrue(gsonCart instanceof Cart, "Assert instance validation for the cart is failed");
        softAssert.assertAll();

        gsonFile.delete();
    }


    @Mock
    private Cart mockedCart;

    @Test (groups = "third")
    public void ioExceptionTest() throws IOException {
        Cart mockedCart = new Cart("Mock");
        Parser parser = new JsonParser();


        Assert.assertThrows(IOException.class, () -> {
            when(new FileWriter(anyString())).thenThrow(new IOException());
            parser.writeToFile(mockedCart);
        });

    }


    @DataProvider(name = "Invalid path")
    public Object[][] invalidFileProvider() {
        return  new Object[][]{
                {"src/main/Test.json"},
                {"src/main/resources/test.json"},
                {"src/main/resources/Test"},
                {""},
                {"src/main/Test"}
        };
    }

    @Test (dataProvider = "Invalid path" )
    void exceptionTest(String parameter) {

        Parser parser = new JsonParser();
        File gsonFile = new File(parameter);

        Assert.assertThrows(NoSuchFileException.class, () -> {
            parser.readFromFile(gsonFile);
        });

    }



}
