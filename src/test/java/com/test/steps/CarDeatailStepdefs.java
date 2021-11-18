package com.test.steps;

import com.test.ObjectRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class CarDeatailStepdefs extends ObjectRepository {
    private List<String> regNumbersFromInputFile = new ArrayList<>();

    @Given("^user reads the input file \"([^\"]*)\" and extracts the car registration numbers$")
    public void userReadsTheInputFileAndExtractsTheCarRegistrationNumbers(String inputFileName) throws Throwable {
        regNumbersFromInputFile = utilPage().getRegNumbersFromInputFile(inputFileName);
        System.out.println(regNumbersFromInputFile);
    }

    @When("^user enters the extracted registered numbers into cartaxcheck website and saves the details of$")
    public void userEntersTheExtractedRegisteredNumbersIntoCartaxcheckWebsiteAndSavesTheDetailsOfMAKEMODELCOLORYEAR(List<String> carProps) {
        navigate().toTaxCarCheckSite();
        homePage().getCarDetails(regNumbersFromInputFile, carProps);
    }

    @Then("^compared the details with the output file name \"([^\"]*)\" and display any mismatches exists$")
    public void comparedTheDetailsWithTheOutputFileNameAndDisplayAnyMismatchesExists(String outputfile) throws Throwable {
        homePage().isCarDetailsMatchWithOutPutFile(outputfile);
    }
}
