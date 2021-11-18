package com.test.pages;

import com.test.configs.ConfigFileReader;
import com.test.configs.FileUtil;
import com.test.configs.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilPage {

    private static UtilPage utilPage;

    public static UtilPage getInstance() {
        return (utilPage == null) ? new UtilPage() : utilPage;
    }

    private WebDriverManager webDriverManager = new WebDriverManager();
    protected WebDriver driver = webDriverManager.getDriver();

    WebElement findElement(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(4))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(locator));
    }

    String getText(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return findElement(locator).getText();
    }

    public void enterText(By locator, String text) {
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
    }

    public void clickOn(By locator) {
        findElement(locator).click();
    }

    public List<String> getRegNumbersFromInputFile(String fileName) {
        String path = ConfigFileReader.getInstance().getFilePath();
        String inputText = FileUtil.readFileToString(path + fileName);
        Pattern pattern = Pattern.compile("([a-zA-Z]{2}[0-9]{2}\\s?[a-zA-Z]{3})");
        return getTagValues(pattern, inputText);
    }

    private List<String> getTagValues(Pattern pattern, final String str) {
        final List<String> tagValues = new ArrayList<>();
        final Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }
}
