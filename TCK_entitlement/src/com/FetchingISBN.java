package com;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FetchingISBN {

	static WebDriver driver;
	int last_row;
	String data;
	String data0;
	XSSFSheet sheet;
	XSSFWorkbook Workbook;

	public static void main(String[] args) {
		FetchingISBN obj = new FetchingISBN();
		obj.GetISBN();
		try {
			Files.createDirectory(Paths.get("D:\\ENT-1000"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void GetExcelSheet() {
		try {
			
			File fs = new File("D:\\ENT-16447\\ReadExcelTCK6.xlsx");
			FileInputStream input = new FileInputStream(fs);

			Workbook = new XSSFWorkbook(input);
			sheet = Workbook.getSheetAt(0);

			last_row = sheet.getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void GetISBN() {

		try {
			GetExcelSheet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("working");

		// select User

		for (int i = 0; i <= last_row; i++) {
			data = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println(data);
			data0 = sheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println(data0);
			process();
		}
	}

	public void setBrowser() {

		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chrome_driver\\chromedriver.exe");// need to change
																										// the chrome
		// driver
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}

	public void process() {
		BufferedWriter bw = null;
		try

		{
			setBrowser();

			driver.get("http://admin-k6.thinkcentral.com/ePCAdmin/login");

			// login page

			driver.findElement(By.xpath("//input[@name=\"userName\"]")).sendKeys("karishma.9.jain");
			driver.findElement(By.xpath("//input[@name=\"j_password\"]")).sendKeys("1377952787");
			driver.findElement(By.xpath("//select[@name=\"domainVal\"]")).click();
			driver.findElement(By.xpath("//option[@value=\"niit.com\"]")).click();
			driver.findElement(By.xpath("//option[@value=\"niit.com\"]")).click();
			driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();

			driver.findElement(By.xpath("//*[@id=\"topMenuTab\"]/td[9]/span/a")).click();
			driver.findElement(By.xpath("//*[@id=\"sUserName\"]")).sendKeys("tteachercusd");
//			driver.findElement(By.xpath("//h2[@class='accordionButton']")).click();
//			driver.findElement(By.name("firstName")).sendKeys("Erica");
//			driver.findElement(By.name("lastName")).sendKeys("Sibug");
//			driver.findElement(By.name("pid")).sendKeys("88719662");
			driver.findElement(By.xpath("//*[@id=\"accountGo\"]")).click();
			Thread.sleep(1000);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement Log = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title=\"Log in to this user\"][1]")));
			Log.click();

			// driver.findElement(By.xpath("//a[@title=\"Log in to this
			// user\"][1]")).click();
			driver.findElement(By.xpath("//*[@id=\"caseNumber\"]")).sendKeys("csi");
			driver.findElement(By.id("notes")).sendKeys("test123");
			driver.findElement(By.xpath("//input[@id='logInNow']")).click();
			System.out.println("done");

			Thread.sleep(60000 / 2);

			String winHandleBefore = driver.getWindowHandle();
			for (String wind : driver.getWindowHandles()) {
				if (!wind.equals(winHandleBefore)) {
					driver.switchTo().window(wind);
					System.out.println(driver.getCurrentUrl());
				}
			}

			System.out.println(driver.getCurrentUrl());

			// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"2\"]/header/h2/a")));
			WebElement element = driver.findElement(By.linkText("My Account"));
			element.click();

			WebElement account = driver.findElement(By.xpath("//li[@id=\"account\"]"));
			Actions act = new Actions(driver);
			act.moveToElement(account).build().perform();

			// searching product(generating error)

			driver.findElement(By.linkText("Products")).click();

			System.out.println(driver.getCurrentUrl());
			String str = driver.findElement(By.xpath(
					"/html/body/fieldset/div/table/tbody/tr/td/div/form/table/tbody/tr[1]/td/table/tbody/tr[2]/td/fieldset/table[2]/tbody/tr/td[1]/table/tbody/tr/td/span"))
					.getText();
			String str2[] = str.split(" ");
			System.out.println(str2[4]);
			int pages = (Integer.parseInt(str2[4]) / 20) + 1;
			int lastEntry = Integer.parseInt(str2[4]) % 20;
			System.out.println(pages + "\t" + lastEntry);

			bw = new BufferedWriter(new FileWriter(new File("D:\\ENT-1000\\" + data0 + ".txt")));
			bw.write("ISBN\n");
			bw.newLine();
			String isbn;
			for (int i = 0; i < pages; i++) {
				driver.findElement(By.xpath(
						"/html/body/fieldset/div/table/tbody/tr/td/div/form/table/tbody/tr[1]/td/table/tbody/tr[2]/td/fieldset/table[1]/tbody/tr/td[2]/table/tbody/tr/td["
								+ ((i * 2) + 1) + "]"))
						.click();
				if (i < pages - 1) {
					for (int j = 0; j < 20; j++) {
						isbn = driver.findElement(By.xpath(
								"/html/body/fieldset/div/table/tbody/tr/td/div/form/table/tbody/tr[1]/td/table/tbody/tr[2]/td/fieldset/div/table/tbody/tr["
										+ (j + 1) + "]/td[2]"))
								.getText();
						System.out.println(isbn);
						bw.write(isbn + "\n");
						bw.newLine();
					}
				} else {
					for (int k = 0; k < lastEntry; k++) {
						isbn = driver.findElement(By.xpath(
								"/html/body/fieldset/div/table/tbody/tr/td/div/form/table/tbody/tr[1]/td/table/tbody/tr[2]/td/fieldset/div/table/tbody/tr["
										+ (k + 1) + "]/td[2]"))
								.getText();
						System.out.println(isbn);
						bw.write(isbn + "\n");
						bw.newLine();
					}
				}

			}

			System.out.println("completed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[] args = {};
		try {
			Eval_creator2.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
