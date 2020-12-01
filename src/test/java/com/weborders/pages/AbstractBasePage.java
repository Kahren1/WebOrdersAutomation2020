package com.weborders.pages;

import com.weborders.utilities.BrowserUtilities;
import com.weborders.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * It meant to be extended
 */
public abstract class AbstractBasePage {

    protected WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15);


    @FindBy (tagName="h2")
    protected WebElement pageSubtitle;

    public String getPageSubtitleText(){
        BrowserUtilities.waitForPageToLoad(10);
        return pageSubtitle.getText().trim();
    }

    @FindBy(tagName="h1")
    protected WebElement pageLogo;

    public String getPageLogoText(){
        return pageLogo.getText();
    }

    @FindBy(id="ctl00_logout")
    protected WebElement logoutBtn;


    public AbstractBasePage(){
        PageFactory.initElements(driver, this);
    }

    /**
     * specify component name as a parameter, like: View all products or Orders
     * @param component
     */
    public void navigateTo(String component){
        String locator="//a[text()='"+component+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
    }

}
