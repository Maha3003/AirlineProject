package org.maven.Airline;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AirlineBooking extends BaseClass  {
	
	@BeforeClass
	public static void browserLaunch() throws IOException {
		 launchBrowser();
		 driver.manage().deleteAllCookies();
		 openUrl("https://www.palairlines.ca/en/");
		 
		
	}
	@AfterClass
	public static void quit()
	{
		driver.quit();
	}
	
	@Before
	public  void beforeMethod() {
		Date d=new Date();
		System.out.println(d);
	}
	
	@After
	public  void afterMethod() {
		Date date=new Date();
		System.out.println(date);
	}
	
	
	@Test
	public void BookingAirTicket() throws AWTException, InterruptedException, IOException
	{
		WebElement flightWay = findByXPathLocator(driver, "//input[@id='flttype1']");
		flightWay.click();
		String oneWayText = flightWay.getAttribute("value");
		Assert.assertEquals("Verify Flight Type", "off",oneWayText);
		
		WebElement departure = findByXPathLocator(driver, "//select[@id='lstDep']");
		Select s=new Select(departure);
		s.selectByValue("YCL");
		WebElement departureSelect = s.getFirstSelectedOption();
		String departuretext = departureSelect.getText();
		Assert.assertEquals("Verify Departure", "YCL - Charlo, NB",departuretext);
		
		WebElement arrival = findByXPathLocator(driver, "//select[@id='lstArr']");
		Select s1=new Select(arrival);
		s1.selectByVisibleText("YZV - Sept-Iles, QC");
		WebElement arrivalSelect = s1.getFirstSelectedOption();
		String arrivalText = arrivalSelect.getText();
		Assert.assertEquals("Verify Departure", "YZV - Sept-Iles, QC",arrivalText);
		
		WebElement departureDate = findByXPathLocator(driver, "//input[@name='dlstDepDate']");
		departureDate.clear();
		WebElement datePicker = findByXPathLocator(driver, "(//table//table//tbody[2]/tr[5])[1]");
		List<WebElement> columns=datePicker.findElements(By.tagName("td"));

		  for (WebElement cell: columns){

		   if (cell.getText().equals("29")){

		   cell.findElement(By.linkText("29")).click();

		   break;

		   }
		  }
		WebElement oneText = findByXPathLocator(driver, "//span[text()='One-Way']");
		oneText.click();
		String departureDateText = departureDate.getAttribute("value");
		Assert.assertEquals("Verify date", "2020-01-29",departureDateText);
		
		
		WebElement adults = findByXPathLocator(driver, "//select[@id='lstAdult']");
		Select s3=new Select(adults);
		s3.selectByValue("2");
		WebElement adultsSelect = s3.getFirstSelectedOption();
		String adultsText = adultsSelect.getText();
		Assert.assertEquals("Verify adults", "2",adultsText);
		takeScreenShot("airlineHome.png");
	}
	
	@Test
	public void cabinSelection() throws InterruptedException, IOException
	{
	    WebElement submit = findByXPathLocator(driver, "//button[@id='submit_reserve']");
		submit.click();
		Thread.sleep(10000);
		WebElement cabinType = findByXPathLocator(driver, "//input[@value='2,B1,O']");
		System.out.println(cabinType);
		cabinType.click();
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		int count=0;
		List<String> li=new ArrayList<String>();
		li.addAll(handles);
		for (String handle1 : driver.getWindowHandles()) {
		    if(count==1)
		        	 {
		          System.out.println(handle1);
		          driver.switchTo().window(handle1);
		        	 }
		        	 count++;
		          }
	  WebElement closeCharge = findByXPathLocator(driver, "//input[@value='Close Charges']");
	  closeCharge.click();
	  driver.switchTo().window(li.get(0));
	  takeScreenShot("cabinselection.png");
	  WebElement continueText = findByXPathLocator(driver, "//a[contains(text(),'Continue')]");
	  continueText.click();
	}
	
	@Test
	public void personalInformation() throws InterruptedException, IOException
	{
		Thread.sleep(1000);
		WebElement gender = findByXPathLocator(driver, "//select[@id='txtPax1_Gender']");
		Select s=new Select(gender);
		s.selectByVisibleText("Ms./Mrs.");
		WebElement genderSelect = s.getFirstSelectedOption();
		String genderText = genderSelect.getAttribute("value");
		Assert.assertEquals("Verify the gender", "F",genderText);
		
		WebElement firstName = findByXPathLocator(driver, "//input[@id='txtPax1_FName']");
		firstName.sendKeys("Maha");
		Assert.assertEquals("verify First Name", "Maha", firstName.getAttribute("value"));
		
		WebElement lastName = findByXPathLocator(driver, "//input[@id='txtPax1_LName']");
		lastName.sendKeys("Lakshmi");
		Assert.assertEquals("verify Last Name", "Lakshmi", lastName.getAttribute("value"));
		
		WebElement address = findByXPathLocator(driver, "//input[@id='txtPax1_Addr1']");
		address.sendKeys("Shollinganallur");
		Assert.assertEquals("verify Address", "Shollinganallur", address.getAttribute("value"));
		
		WebElement city = findByXPathLocator(driver, "//input[@id='txtPax1_City']");
		city.sendKeys("Chennai");
		Assert.assertEquals("verify city", "Chennai", city.getAttribute("value"));
		
		WebElement country = findByXPathLocator(driver, "//select[@id='txtPax1_Ctry']");
		Select s1=new Select(country);
		s1.selectByVisibleText("India");
		WebElement firstSelectedOption = s1.getFirstSelectedOption();
		Assert.assertEquals("verify Country", "102", firstSelectedOption.getAttribute("value"));
		
		WebElement email = findByXPathLocator(driver, "//input[@id='txtPax1_EMail']");
		email.sendKeys("maha@gmail.com");
		Assert.assertEquals("verify Email", "maha@gmail.com", email.getAttribute("value"));
		
		WebElement verifyEmail = findByXPathLocator(driver, "//input[@id='txtPax1_EMail2']");
		verifyEmail.sendKeys("maha@gmail.com");
		Assert.assertEquals("verify Confirm Email", "maha@gmail.com", verifyEmail.getAttribute("value"));
		
		WebElement phoneNum = findByXPathLocator(driver, "//input[@id='txtPax1_Phone1']");
		phoneNum.sendKeys("9876543210");
		Assert.assertEquals("verify Phnum", "9876543210", phoneNum.getAttribute("value"));
		
		WebElement gender1 = findByXPathLocator(driver, "//select[@id='txtPax2_Gender']");
		Select s3=new Select(gender1);
		s3.selectByVisibleText("Mr.");
		WebElement genderSelect1 = s3.getFirstSelectedOption();
		String genderText1 = genderSelect1.getAttribute("value");
		Assert.assertEquals("Verify the gender", "M",genderText1);
		
		WebElement firstName1 = findByXPathLocator(driver, "//input[@id='txtPax2_FName']");
		firstName1.sendKeys("Vignesh");
		Assert.assertEquals("verify First Name", "Vignesh", firstName1.getAttribute("value"));
		
		WebElement lastName1 = findByXPathLocator(driver, "//input[@id='txtPax2_LName']");
		lastName1.sendKeys("Rajan");
		Assert.assertEquals("verify Last Name", "Rajan", lastName1.getAttribute("value"));
		
		WebElement phoneNum1 = findByXPathLocator(driver, "//input[@id='txtPax2_Phone1']");
		phoneNum1.sendKeys("8976543567");
		Assert.assertEquals("verify Phnum", "8976543567", phoneNum1.getAttribute("value"));
		
		WebElement continueField = findByXPathLocator(driver, "//a[contains(text(), 'Continue')]");
		continueField.click();
		takeScreenShot("passengerReview.png");
		
		WebElement totalCost = findByXPathLocator(driver, "//td[text()='1,680.54']");
		String text = totalCost.getText();
		Assert.assertEquals("verify Total Cost", "1,680.54", text);
		
		ExcelWrite.printTotalCost(text);
		}
	
}
