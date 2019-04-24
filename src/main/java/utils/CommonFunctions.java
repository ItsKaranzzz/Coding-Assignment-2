package utils;

import base.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class CommonFunctions extends TestBase {

    public static Properties readProperties() throws FileNotFoundException {

        FileInputStream fileRead = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/configurations.properties");
        try {
            props = new Properties();
            props.load(fileRead);
        } catch (IOException e) {
            System.out.print("Properties file not found!");
        }
        return props;

    }

    public static String getLocalCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate));
        return localDate.toString();//2016/11/16

    }

    public static String getLocalReturnDateAsPerUsersChoice(int userTripDays) {

        String presentDay = CommonFunctions.getLocalCurrentDate().split("-")[2];
        String presentMonth = CommonFunctions.getLocalCurrentDate().split("-")[1];

        int returnDay = Integer.parseInt(presentDay) + userTripDays;
        long presentMonthreturned = Long.parseLong(presentMonth);

        //can be extended to other months scenario as well currently works for current month

        if ((returnDay >= 31) && (presentMonthreturned == 04)) {
            returnDay = returnDay - 30;
            return Integer.toString(returnDay);
        } else
            return Integer.toString(returnDay);


    }

    public static int getnumericPriceValue(String price) {

        String numericprice = price.split("\\s")[1];
        numericprice = numericprice.replaceAll(",", "");
        int retPrice = Integer.parseInt(numericprice);
        return retPrice;
    }


}
