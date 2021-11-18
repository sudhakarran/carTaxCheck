package com.test.pages;

import com.test.configs.ConfigFileReader;
import org.openqa.selenium.By;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class HomePage extends UtilPage {

    private static final By regTextField = By.cssSelector("input[id='vrm-input']");
    private static final By freeCarCheckButton = By.xpath("//button[contains(text(),'Free Car Check')]");
    private static Map<String, List<String>> actualCarDetails = new HashMap<>();

    private static HomePage homePage;

    public static HomePage getInstance() {
        return (homePage == null) ? new HomePage() : homePage;
    }

    private void enterRegNumber(String txt) {
        enterText(regTextField, txt);
    }

    private void clickCheckBtn() {
        findElement(freeCarCheckButton).click();
    }

    private void searchWithRegNumber(String txt) {
        enterRegNumber(txt);
        clickCheckBtn();
    }

    private By getCarProperty(String key) {
        return By.xpath("//dt[text()='" + key + "']/../dd");
    }

    public void getCarDetails(List<String> regNumbersFromInputFile, List<String> carProps) {
        regNumbersFromInputFile.forEach(regNo -> {
            List<String> carVals = new ArrayList<>();
            searchWithRegNumber(regNo);
            if (vehicleNotFound())
                carVals.add(findElement(By.cssSelector("h5 span")).getText());
            else
                carProps.forEach(prop -> carVals.add(getText(getCarProperty(prop))));
            actualCarDetails.put(regNo, carVals);
            Navigate.getInstance().toTaxCarCheckSite();
        });
    }

    private boolean vehicleNotFound() {
        return findElement(By.cssSelector("h5 span")).getText().equalsIgnoreCase("Vehicle Not Found");
    }

    public void isCarDetailsMatchWithOutPutFile(String fileName) throws Exception {
        String path = ConfigFileReader.getInstance().getFilePath();
        File testFile = new File(path + fileName);
        List<Car> cars = CarReader.readFile(testFile);
        List<String> regNumbers = new ArrayList<>(actualCarDetails.keySet());
        regNumbers.forEach(regNumber -> {
            Car expectedCarDetails;
            try {
                expectedCarDetails = cars.stream().filter(car -> car.getREGISTRATION().equals(regNumber.replace(" ", ""))).collect(Collectors.toList()).get(0);
                isCarDetailsMatching(regNumber, expectedCarDetails);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\n" + regNumber + " Details: \n");
                System.out.println(regNumber + " details are not in output file");
                if (actualCarDetails.get(regNumber).size() < 2)
                    System.out.println(regNumber + " details are not found in website");
            }
        });
    }

    private void isCarDetailsMatching(String regNumber, Car expectedCarDetails) {
        Map<String, Boolean> matched = new HashMap<>();
        boolean make = actualCarDetails.get(regNumber).get(0).equalsIgnoreCase(expectedCarDetails.getMAKE());
        boolean model = actualCarDetails.get(regNumber).get(1).equalsIgnoreCase(expectedCarDetails.getMODEL());
        boolean colour = actualCarDetails.get(regNumber).get(2).equalsIgnoreCase(expectedCarDetails.getCOLOR());
        boolean year = actualCarDetails.get(regNumber).get(3).equalsIgnoreCase(expectedCarDetails.getYEAR());
        matched.put("make", make);
        matched.put("model", model);
        matched.put("colour", colour);
        matched.put("year", year);
        System.out.println("\n" + regNumber + " Details: \n");
        matched.keySet().forEach(key -> {
            if (!matched.get(key))
                System.out.println("Car having the " + regNumber + " " + key + " is NOT matched in website");
            else
                System.out.println("This Car " + key + " is same in output file and cartaxcheck website");
        });
    }
}
