package exam;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Toolqa {
	
	ChromeDriver d;
	
	@BeforeTest
	public void start()
	{
		d=new ChromeDriver();
		d.get("https://www.toolsqa.com/");
	}
	
	@Test (priority = 1)
	public void verifypage()
	{
		String actualpage=d.getCurrentUrl();
		String exppage="https://www.toolsqa.com/";
		Assert.assertEquals(actualpage, exppage, "Navigate page is not Matched");
	}
	
	@Test(priority = 2)
	public void link()
	{
		List<WebElement> li=d.findElements(By.tagName("a"));
		System.out.println("Total Number of link: " +li.size());
	}
	
	@Test(priority = 3)
	public void clickPlayground()
	{
		String parent= d.getWindowHandle();
		d.findElement(By.xpath("/html/body/header/nav/div/div/div[3]/div/div[1]/ul/li[3]/a")).click();
		Set <String> newtab=d.getWindowHandles();
		for(String playgd:newtab)
		{
			if(!playgd.equals(parent))
			{
				d.switchTo().window(playgd);
				
				String actTitle=d.getTitle();
				String expTitle="demosite";
				Assert.assertEquals(actTitle, expTitle, "Title not match");
			}
		}
		d.close();
		d.switchTo().window(parent);
	}
	
	@Test(priority = 4)
	public void logo()
	{
		WebElement logoo=d.findElement(By.xpath("/html/body/header/nav/div/div/a/img"));
		
		Assert.assertTrue(logoo.isDisplayed(),"Logo is not displayed");
	}
	
	@Test(priority = 5)
	public void seleniumThing()
	{
		d.findElement(By.xpath("/html/body/header/nav/div/div/div[3]/div/div[1]/ul/li[2]/a")).click();
		
		d.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Rugma");
		d.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("Raghav");
		
		d.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("abcd123@gmail.com");
		d.findElement(By.xpath("//*[@id=\"mobile\"]")).sendKeys("1234567890");
		
		WebElement deshh= d.findElement(By.xpath("//*[@id=\"country\"]"));
		Select contry=new Select(deshh);
		contry.selectByVisibleText("India");
		
		d.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Kochi");
		d.findElement(By.xpath("//*[@id=\"message\"]")).sendKeys("Hi iam just want to say nothing");

		
		



		
	}
	@Test(priority = 6)
	public void ss() throws IOException
	{
		File shot=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(shot, new File("./screen/shot.png"));
	}
	
	

}
