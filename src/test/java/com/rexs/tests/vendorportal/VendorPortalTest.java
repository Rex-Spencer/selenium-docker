package com.rexs.tests.vendorportal;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rexs.pages.vendorportal.DashboardPage;
import com.rexs.pages.vendorportal.LoginPage;
import com.rexs.tests.AbstractTest;
import com.rexs.tests.vendorportal.model.VendorPortalTestData;
import com.rexs.util.JsonUtil;

public class VendorPortalTest extends AbstractTest{
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(dashboardPage.isAt());

        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());

        dashboardPage.logout();
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
