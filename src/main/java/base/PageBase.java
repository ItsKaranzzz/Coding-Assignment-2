package base;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageBase {

    WebDriverWait wait;

    protected void clickOnWebElement(WebElement element, int Wait, WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, Wait);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }


    protected void sendKeysToElement(WebElement element, String keyInput, int Wait, WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, Wait);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(keyInput);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void applyFluentWaitandClick(WebElement element, int Wait, WebDriver driver) {

        FluentWait<WebDriver> wait = new FluentWait<>(driver).pollingEvery(1, TimeUnit.SECONDS).ignoring(ElementNotVisibleException.class).withTimeout(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();


    }

    protected boolean verifyelementEnablity(WebDriver driver, int Wait, WebElement element) {
        wait = new WebDriverWait(driver, Wait);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isEnabled();

    }

    protected void applyWaitTillElementClickable(WebElement element, int Wait, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Wait);
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    protected void scrollTillendOfPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    protected void scrollTillBeginingOfPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollTop,0)");
    }
}

