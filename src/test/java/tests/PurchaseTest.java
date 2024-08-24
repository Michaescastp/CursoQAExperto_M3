package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.ProductPage;

import java.time.Duration;

public class PurchaseTest {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        driver.get("https://www.demoblaze.com");
    }

    @Test
    public void testPurchase() {
        homePage.selectProduct();
        productPage.addToCart();
        driver.findElement(By.linkText("Cart")).click();
        cartPage.proceedToCheckout();
        checkoutPage.fillCheckoutForm("John Doe", "USA", "New York", "1234567890123456", "12", "2025");
        checkoutPage.confirmPurchase();

        String confirmationMessage = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")).getText();
        Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

