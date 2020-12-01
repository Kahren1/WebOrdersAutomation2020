package com.weborders.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BrowserUtilities {

    //methods that handle browser, manipulate browser
    //waits, clicks, switching between window frames

    public static void wait(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTextFromWebElements(List<WebElement> elements) {
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            if (!element.getText().isEmpty()) {
                textValues.add(element.getText());
            }
        }
        return textValues;
    }

    /**
     * waits for background processes on the browser to complete
     *
     * @param timeOutInSeconds
     */

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).
                executeScript("return document.readyState").
                equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Clicks on an element using JavaScriptExecutor
     * simple click, with with JavaScriptExecutor
     *
     * @param element
     */
    public static void clickWithJs(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Scroll to element using JavaScript
     *
     * @param element
     */
    public static void scrollto(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * @param name screenshot name
     * @return a location of the screenshot as a string (path to the screenshot)
     */
    public static String getScreenshot(String name) {
        //adding date and time to screenshot name, to make screenshot unique
        //get java.io.IOException (the filename, dir name or volume label syntax is incorrect
        name = new Date().toString().
                replace(" ", "_").
                replace(":", "-") + "_" + name;
        String path = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + name + ".png";
        System.out.println("Screenshot is here: " + path);
//since our reference type is a WebDriver, we need to to casting to see a method from TakesScreenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        //screenshot itself
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        //where screenshot will be saved
        File destination = new File(path);
        try {
            //copy file to the previously specified location (shown by path)
            //the screeenshot (source) goes to the path, wrapped into a File object
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * this method will switch webdriver from current window
     * to target window based on the page title
     *
     * @param title of the window to switch
     */
    public static void switchWindow(String title) {
        Set<String> windowHandles = Driver.getDriver().getWindowHandles();
        for (String window : windowHandles) {
            Driver.getDriver().switchTo().window(window);
            if (Driver.getDriver().getTitle().equals(title)) {
                break;
            }
        }
    }
}