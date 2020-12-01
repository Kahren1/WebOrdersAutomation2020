package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractBaseTest {

//    @Test
//    public void verifyAllProductsPage() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.login();
//        loginPage.navigateTo("View all products");
//        Assert.assertEquals(loginPage.getPageSubtitleText(), "List of Products");
//    }
//    @Test
//    public void verifyAllOrdersPage() {
//    }
//    @Test
//    public void orderPage() {
//    }

    @Test(dataProvider="smokeTestData")
    public void verifyAllProductsPage(String component, String expectedPageSubTitle){
        extentTest=extentReports.createTest("Verify " + component);
LoginPage loginPage = new LoginPage();
loginPage.login();
loginPage.navigateTo(component);
Assert.assertEquals(loginPage.getPageSubtitleText(), expectedPageSubTitle);
extentTest.pass(component + " verified!");
    }


@DataProvider(parallel=true)
    public Object [][] smokeTestData(){
        return new Object[][]{
                {"View all orders", "List of All Orders"},
                {"View all products", "List of Products"},
                {"Order", "Order"}
        };
    }




}
