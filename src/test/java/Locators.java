import org.openqa.selenium.By;

public class Locators {

    By byId = By.id("id");
    By byName = By.name("name");
    By byClassName = By.className("className");
    By byTagName = By.tagName("tagName");

    By byCssSelector1 = By.cssSelector("div");

    By byCssSelector2 = By.cssSelector("#elementId");
    By byCssSelector3 = By.cssSelector(".myClass");

    By byCssSelector4 = By.cssSelector("[class^='attributeClass']");
    By byCssSelector5 = By.cssSelector("[class$='attributeClass']");
    By byCssSelector6 = By.cssSelector("[class*='attributeClass']");

    By byCssSelector7 = By.cssSelector("#id.myClass");
    By byCssSelector8 = By.cssSelector("[name='attributeName']");
    By byCssSelector9 = By.cssSelector("[name='attributeName'][type='attributeType']");

    By byCssSelector10 = By.cssSelector("div > span");
    By byCssSelector11 = By.cssSelector("div span");

    By byLinkTest = By.linkText("linkText");
    By byPartialLinkText = By.partialLinkText("partialLinkText");

    By byXpath = By.xpath("//*[@id='elementId']");
    
}
