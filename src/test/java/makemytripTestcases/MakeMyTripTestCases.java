package makemytripTestcases;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.Calendar;
import utils.CommonFunctions;
import utils.Constants;

import java.io.FileNotFoundException;

public class MakeMyTripTestCases extends TestBase {

    HomePage oHome;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void preSetup() throws FileNotFoundException {
        initialization();

        oHome = new HomePage(driver);
        oHome.clickOnFlightsLink(driver);
        oHome.clickOnRoundtripBtn(driver);
        oHome.clickOnFromCity(driver);
        oHome.enterFromCity(Constants.FROM_CITY, driver);
        oHome.clickOnToCity(driver);
        oHome.enterFromCity(Constants.TO_CITY, driver);

        oHome.clickOnDepartureCalendar(driver);
        oHome.selectDepartureCalendarDate(CommonFunctions.getLocalCurrentDate(), driver);
        oHome.clickOnReturnCalendar(driver);
        oHome.selectArrivalCalendarDate(CommonFunctions.getLocalReturnDateAsPerUsersChoice(Constants.TRIP_DURATION), driver);
        oHome.clickOnSearchBtn(driver);


    }


    @Test
    public void test1() throws FileNotFoundException {


        String selectedDepartureFlightPrice = oHome.getselecteddepartureFlightPriceFromList(driver, Constants.USER_FLIGHT_SELECTION_OPTION);
        System.out.println(oHome.getselecteddepartureFlightPriceFromList(driver, Constants.USER_FLIGHT_SELECTION_OPTION));

        String selectedReturnFlightPrice = oHome.getselectedreturnFlightPriceFromList(driver, Constants.USER_FLIGHT_SELECTION_OPTION);
        System.out.println(oHome.getselectedreturnFlightPriceFromList(driver, Constants.USER_FLIGHT_SELECTION_OPTION));

        System.out.println("total number of Departure Flights Searched are:" + oHome.getSearchedDepartureFlightsCount(driver));
        System.out.println("total number of Return Flights Searched are:" + oHome.getSearchedReturnFlightsCount(driver));

        oHome.checkNonStopFilterBtn(driver);
        oHome.checkOneStopFilterBtn(driver);

        System.out.println("total number of Departure Flights Filtered Searched are:" + oHome.getSearchedDepartureFlightsCount(driver));
        System.out.println("total number of Return Flights Filtered Searched are:" + oHome.getSearchedReturnFlightsCount(driver));
        //Verifying the departure flight price
        softAssert.assertEquals(selectedDepartureFlightPrice, oHome.selectedDepratureFlightPriceOnFooter(driver));
        //Verifying the return flight price
        softAssert.assertEquals(selectedReturnFlightPrice, oHome.selectedReturnedFlightPriceOnFooter(driver));

        //Verifying the total flight price
        int departurePrice = CommonFunctions.getnumericPriceValue(selectedDepartureFlightPrice);
        int returnPrice = CommonFunctions.getnumericPriceValue(selectedReturnFlightPrice);
        int expectedTotalPrice = departurePrice + returnPrice;

        //verifying the total displayed price
        softAssert.assertEquals(expectedTotalPrice, oHome.getTheDisplayedTotalPrice(driver));

        softAssert.assertAll();


    }



    @AfterMethod
    public void closeResources() {
        tearDown();
    }
}
