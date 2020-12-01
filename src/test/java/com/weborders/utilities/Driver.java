package com.weborders.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {




    //create once, use multiple times
    //same for everyone
    private static ThreadLocal<WebDriver> driverPool=new ThreadLocal<>();

   // private static WebDriver driver; //so no one can create object of Driver class
    //everyone should call static getter method instead
    private Driver(){

    }
    public static WebDriver getDriver(){

        if(driverPool.get()==null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();

            switch(browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    //to turn headless mode (without interface)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver(){
        if(driverPool!=null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}

