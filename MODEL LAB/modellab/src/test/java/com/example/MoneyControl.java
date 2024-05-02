package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MoneyControl {
    WebDriver driver;
    ExtentReports reports;
    ExtentTest test;

    @BeforeMethod
    public void before()
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\Users\\v.dharaneesh\\Desktop\\SOFTWARE TESTING\\MODEL LAB\\modellab\\report.html");
        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void test1() throws InterruptedException
    {
        driver.get("https://www.moneycontrol.com");
        SoftAssert assert1 = new SoftAssert();
        test = reports.createTest("test1","Reliance Industries Ltd.");
        WebElement search = driver.findElement(By.xpath("//*[@id=\"search_str\"]"));
        search.sendKeys("Reliance Industries");
        Thread.sleep(5000);
        WebElement reliance = driver.findElement(By.xpath("//*[@id=\"autosuggestlist\"]/ul/li[1]/a"));
        reliance.click();

        String relianceVerify = driver.findElement(By.xpath("//*[@id=\"stockName\"]/h1")).getText();
        assert1.assertTrue(relianceVerify.equals("Reliance Industries Ltd."));
        assert1.assertAll();

        if (relianceVerify.equals("Reliance Industries Ltd.")) {
            System.out.println("Reliance Industries Ltd. Page Opened Correctly.");
        }
        else{
            System.out.println("Entered in a Wrong Page");
        }
    }

    @Test
    public void test2() throws InterruptedException
    {
         driver.get("https://www.moneycontrol.com");
        SoftAssert assert2 = new SoftAssert();
        test = reports.createTest("test 2","SIP Return Calculator");
        WebElement mutualFund = driver.findElement(By.xpath("//*[@id='common_header']/div[1]/div[3]/nav/div/ul/li[10]/a"));
        Actions action = new Actions(driver);
        action.moveToElement(mutualFund).perform();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"common_header\"]/div[1]/div[3]/nav/div/ul/li[10]/div/div/ul/li[2]/ul/li[5]/a")).click();

        WebElement mutud = driver.findElement(By.xpath("//*[@id=\"ff_id\"]"));
        Select mutudSelect = new Select(mutud);
        mutudSelect.selectByVisibleText("Axis Mutual Fund");

        WebElement scheme = driver.findElement(By.xpath("//*[@id=\"im_id\"]"));
        Select schemeSelect = new Select(scheme);
        schemeSelect.selectByVisibleText("Axis Arbitrage Fund - Direct Plan (D)");

        WebElement investAmt = driver.findElement(By.xpath("//*[@id=\"invamt\"]"));
        investAmt.sendKeys("100000");

        WebElement frequency = driver.findElement(By.xpath("//*[@id=\"frq\"]"));
        Select frequencySelect = new Select(frequency);
        frequencySelect.selectByVisibleText("Monthly");

        WebElement stdt = driver.findElement(By.name("stdt"));
        stdt.sendKeys("2021-08-02");
        
        WebElement endt = driver.findElement(By.name("endt"));
        endt.sendKeys("2021-08-02");

        WebElement calculateButton = driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input"));
        calculateButton.click();

        WebElement op1 = driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[1]"));
        System.out.println(op1.getText());

        WebElement op2 = driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[2]/div/div[3]/div[2]/div[6]/table/tbody/tr[3]"));
        System.out.println(op2.getText());

        
    }

}
