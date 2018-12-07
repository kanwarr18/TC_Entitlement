package com;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Eval_upload {
	 static WebDriver driver;

	public static void main(String args[]) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chrome_driver\\chromedriver.exe");

		driver = new ChromeDriver();
		System.out.println("eval_upload started  done");
		Eval_upload obj = new Eval_upload();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("http://admin-review-cert.thinkcentral.com/ePCAdmin/login");
//		Thread.sleep(3000);
		obj.upLoader();
	}

	public void upLoader() {


        driver.findElement(By.xpath("//*[@id=\"Table_01\"]/tbody/tr/td/table/tbody/tr[1]/td[2]/input")).sendKeys("karishma.9.jain");
        driver.findElement(By.xpath("//*[@id=\"Table_01\"]/tbody/tr/td/table/tbody/tr[2]/td[2]/input")).sendKeys("1377952787");
                        WebElement element = driver.findElement(By.xpath("//*[@id=\"Table_01\"]/tbody/tr/td/table/tbody/tr[1]/td[4]/select"));
                        Select dropdown = new Select(element);
                        dropdown.selectByValue("niit.com");
                        driver.findElement(By.xpath("//*[@id=\"Table_01\"]/tbody/tr/td/table/tbody/tr[3]/td[2]/input")).click();
                        driver.findElement(By.xpath("//*[@id=\"topMenuTab\"]/td[7]/span/a")).click();
                        driver.findElement(By.xpath("//*[@id=\"menu\"]/table/tbody/tr[3]/td/a")).click();
                        
                        WebElement chooseFile = driver.findElement(By.id("file"));
                        chooseFile.sendKeys("D:\\ENT-1000\\rkan.zip");// ***Add zip file here***
                        driver.findElement(By.xpath("//*[@id=\"PA_FindUA\"]/tbody/tr[9]/td/input[1]")).click();
                        Alert simpleAlert = driver.switchTo().alert();
                        simpleAlert.accept();
                        
                        if(driver.getPageSource().contains("eval.zip Imported Successfully."))
                         {
                                        System.out.println("Eval File Import Successfully");
                                        
                        }
                        
                        else
                        {
                                       System.out.println("Eval.zip is incorrect");
                       }

	}

	
}
