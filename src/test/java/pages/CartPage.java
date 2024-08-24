package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By placeOrderButton = By.xpath("//button[text()='Place Order']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        driver.findElement(placeOrderButton).click();
    }
}
