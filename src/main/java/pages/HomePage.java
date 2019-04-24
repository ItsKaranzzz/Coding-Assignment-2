package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Calendar;
import utils.CommonFunctions;
import utils.Constants;

import java.util.List;

public class HomePage extends PageBase {

    @FindBy(xpath = "//a[text()='Flights']")
    private WebElement flights;

    @FindBy(xpath = "//li[contains(text(),'Round Trip')]")
    private WebElement roundTripBtn;

    @FindBy(xpath = "//input[@id='fromCity']")
    private WebElement fromCity;

    @FindBy(xpath = "//input[@id='toCity']")
    private WebElement toCity;

    @FindBy(xpath = "//a[text()='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "  //span[text()='DEPARTURE']")
    private WebElement departureCalendarLink;

    @FindBy(xpath = "  //span[text()='RETURN']")
    private WebElement returnCalendarLink;

    @FindBy(xpath = "//span[text()='Non Stop']")
    private WebElement nonStopCheck;

    @FindBy(xpath = "//span[text()='1 Stop']")
    private WebElement oneStopCheck;

    @FindBy(xpath = "//div[@class='splitVw-footer-left ']/descendant::p[@class='actual-price']")
    private WebElement selectedDepartureFlightPriceDisplayedOnFooter;

    @FindBy(xpath = "//div[@class='splitVw-footer-right ']/descendant::p[@class='actual-price']")
    private WebElement selectedReturnedFlightPriceDisplayedOnFooter;

    @FindBy(xpath = "//span[@class='splitVw-total-fare']")
    private WebElement totalPriceDisplayedOnFooter;


    By searchedDepartureFlights = By.xpath("//div[@class='splitVw-sctn pull-left']/descendant::div[@class='fli-list splitVw-listing']");

    By searchedReturnFlights = By.xpath("//div[@class='splitVw-sctn pull-right']/descendant::div[@class='fli-list splitVw-listing']");

    By radioBtnForDepartureFlights = By.xpath("//div[@class='splitVw-sctn pull-left']/descendant::div[@class='fli-list splitVw-listing']/descendant::span[@class='splitVw-inner']");

    By radioBtnForReturnFlights = By.xpath("//div[@class='splitVw-sctn pull-right']/descendant::div[@class='fli-list splitVw-listing']/descendant::span[@class='splitVw-inner']");

    By departurepriceList = By.xpath("//div[@class='splitVw-sctn pull-left']/descendant::div[@class='fli-list splitVw-listing']/descendant::p[@class='actual-price']");

    By priceReturnList = By.xpath("//div[@class='splitVw-sctn pull-right']/descendant::div[@class='fli-list splitVw-listing']/descendant::p[@class='actual-price']");

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnFlightsLink(WebDriver driver) {
        clickOnWebElement(flights, 10, driver);
    }

    public void clickOnRoundtripBtn(WebDriver driver) {
        clickOnWebElement(roundTripBtn, 10, driver);
    }

    public void clickOnSearchBtn(WebDriver driver) {
        clickOnWebElement(searchBtn, 10, driver);
    }

    public void clickOnFromCity(WebDriver driver) {
        clickOnWebElement(fromCity, 10, driver);
    }

    public void clickOnToCity(WebDriver driver) {
        clickOnWebElement(toCity, 10, driver);
    }

    public void clickOnDepartureCalendar(WebDriver driver) {
        clickOnWebElement(departureCalendarLink, 10, driver);
    }

    public void clickOnReturnCalendar(WebDriver driver) {
        clickOnWebElement(returnCalendarLink, 10, driver);
    }


    public void enterFromCity(String inputCity, WebDriver driver) {
        sendKeysToElement(fromCity, inputCity, 10, driver);
        WebElement selectedFromCity = driver.findElement(By.xpath("//ul[@role='listbox']/li/descendant::div[contains(text(),'" + inputCity + "')]"));
        applyFluentWaitandClick(selectedFromCity, 10, driver);

    }

    public void enterToCity(String inputToCity, WebDriver driver) {
        sendKeysToElement(toCity, inputToCity, 10, driver);
        WebElement selectedToCity = driver.findElement(By.xpath("//ul[@role='listbox']/li/descendant::div[contains(text(),'" + inputToCity + "')]"));
        applyFluentWaitandClick(selectedToCity, 10, driver);

    }

    public WebElement getdeparturedatefromCalendar(WebDriver driver, Calendar cal) {
        WebElement getDate = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][1]/descendant::div[@class='dateInnerCell']/p[text()='" + cal.getDay() + "']"));
        applyWaitTillElementClickable(getDate, 10, driver);

        return getDate;
    }

    public WebElement getreturndatefromCalendar(WebDriver driver, Calendar cal) {

        WebElement getDate = driver.findElement(By.xpath("//div[@class='DayPicker-Month'][2]/descendant::div[@class='dateInnerCell']/p[text()='" + cal.getDay() + "']"));
        applyWaitTillElementClickable(getDate, 10, driver);

        return getDate;
    }


    public void selectDepartureCalendarDate(String inputDate, WebDriver driver) {

        Calendar cal = new Calendar();
        cal.setDay(inputDate.split("-")[2]);
        cal.setMonth(inputDate.split("-")[1]);
        cal.setYear(inputDate.split("-")[0]);


        if (verifyelementEnablity(driver, 10, getdeparturedatefromCalendar(driver, cal)) == true) {
            clickOnWebElement(getdeparturedatefromCalendar(driver, cal), 10, driver);
        } else {
            throw new RuntimeException("Current Date not enabled Please check the departure date!");
        }


    }

    public void selectArrivalCalendarDate(String inputDate, WebDriver driver) {

        Calendar cal = new Calendar();
        cal.setDay(inputDate);
        if (verifyelementEnablity(driver, 10, getreturndatefromCalendar(driver, cal)) == true) {
            clickOnWebElement(getreturndatefromCalendar(driver, cal), 10, driver);
        }
    }

    public int getSearchedDepartureFlightsCount(WebDriver driver) {
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        scrollTillendOfPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        List<WebElement> searchedFlightList = driver.findElements(searchedDepartureFlights);
        wait.until(ExpectedConditions.visibilityOfAllElements(searchedFlightList));

        return searchedFlightList.size();
    }

    public int getSearchedReturnFlightsCount(WebDriver driver) {
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        scrollTillendOfPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        List<WebElement> searchedFlightList = driver.findElements(searchedReturnFlights);
        wait.until(ExpectedConditions.visibilityOfAllElements(searchedFlightList));
        scrollTillBeginingOfPage(driver);

        return searchedFlightList.size();
    }

    public void checkNonStopFilterBtn(WebDriver driver) {

        if (!nonStopCheck.isSelected())
            clickOnWebElement(nonStopCheck, 10, driver);
        else
            throw new RuntimeException("Non stop Filter already selected");

    }

    public void checkOneStopFilterBtn(WebDriver driver) {

        if (!oneStopCheck.isSelected())
            clickOnWebElement(oneStopCheck, 10, driver);
        else
            throw new RuntimeException("Non stop Filter already selected");

    }

    public void selectTheDepartureFlightOption(WebDriver driver, int option) {

        List<WebElement> optionList = driver.findElements(radioBtnForDepartureFlights);

        clickOnWebElement(optionList.get(option), 10, driver);


    }

    public void selectTheReturnedFlightOption(WebDriver driver, int option) {

        List<WebElement> optionList = driver.findElements(radioBtnForReturnFlights);

        clickOnWebElement(optionList.get(option), 10, driver);

    }

    public String getselecteddepartureFlightPriceFromList(WebDriver driver, int option) {

        List<WebElement> priceList = driver.findElements(departurepriceList);
        String returnedFlightprice = priceList.get(option - 1).getText();
        return returnedFlightprice;

    }

    public String getselectedreturnFlightPriceFromList(WebDriver driver, int option) {

        List<WebElement> priceList = driver.findElements(priceReturnList);
        String returnderFlightprice = priceList.get(option - 1).getText();
        return returnderFlightprice;

    }

    public String selectedDepratureFlightPriceOnFooter(WebDriver driver) {
        applyWaitTillElementClickable(selectedDepartureFlightPriceDisplayedOnFooter, 10, driver);
        return selectedDepartureFlightPriceDisplayedOnFooter.getText();
    }

    public String selectedReturnedFlightPriceOnFooter(WebDriver driver) {
        applyWaitTillElementClickable(selectedReturnedFlightPriceDisplayedOnFooter, 10, driver);
        return selectedReturnedFlightPriceDisplayedOnFooter.getText();
    }

    public int getTheDisplayedTotalPrice(WebDriver driver) {
        applyWaitTillElementClickable(totalPriceDisplayedOnFooter, 10, driver);
        System.out.println(totalPriceDisplayedOnFooter.getText());

        return CommonFunctions.getnumericPriceValue(totalPriceDisplayedOnFooter.getText());

    }
}
