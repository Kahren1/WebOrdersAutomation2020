package com.weborders.tests;

import com.weborders.pages.LoginPage;
import com.weborders.utilities.BrowserUtilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends AbstractBaseTest{

    @Test
    public void login(){
        extentTest = extentReports.createTest("Verify page logo");

        LoginPage loginPage = new LoginPage();
        loginPage.login();
        Assert.assertEquals(loginPage.getPageLogoText(), "Web Orders");

        extentTest.pass("Logo verified!");
    }



}
