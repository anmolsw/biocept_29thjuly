package biocept.qa.utill;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import biocept.qa.base.BioceptBase;

public class WorkflowCommonMethods extends BioceptBase{
	
	@FindBy(tagName="h3")
	WebElement activityName;
	
	@FindBy(css="input[placeholder='Date Time']")
	WebElement DateTime;
	
	@FindBy(css ="input[value='Complete']")
	WebElement ctcFishCompleteButtonElement;
	
	@FindBy(css ="span[data-notify='message']")
	WebElement completeMessage;
	
	@FindBy(id="btn-Complete")
	WebElement molecularCompleteButton;
	
	@FindBy(id="btn-Save")
	WebElement molecularSaveButton;
	
	String title;

	public WorkflowCommonMethods(){
		PageFactory.initElements(driver, this);
	}
	
	public  String pageTitle (){
		try{
			ExplicitWait.invisibilityOfLoader();
		
			title=ExplicitWait.waitUntilElementToBeVisible(activityName).getText();
		
		}catch (Exception e) {
			ExplicitWait.invisibilityOfLoader();
			
			title=ExplicitWait.waitUntilElementToBeVisible(activityName).getText();
		}
		return title;
		
	}
	
	
	
	public void activityStartEndDateTime(){
		try{
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeClickable(DateTime).click();
		DateTime.sendKeys(Keys.TAB);
		DateTime.sendKeys(Keys.TAB);
		}catch(Exception e){
			
		}

	}
	
	public void ctcFishCompleteButton (){
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeClickable(ctcFishCompleteButtonElement);
		jsClick(ctcFishCompleteButtonElement);
		
	}
	
	public void molecularCompleteButton (){
		ExplicitWait.invisibilityOfLoader();
		jsClick(molecularCompleteButton);
		//ExplicitWait.waitUntilElementToBeClickable(molecularCompleteButton).click();
		
		
	}
	public void molecularSaveButton (){
		ExplicitWait.invisibilityOfLoader();
		jsClick(molecularSaveButton);
		//ExplicitWait.waitUntilElementToBeClickable(molecularSaveButton).click();
		
	}
	public String completeMessage (){
		//ExplicitWait.invisibilityOfLoader();
		
		String message =ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
		ExplicitWait.invisibilityOfLoader();
		return message;
	}
	
	public static void scrollIntoView (WebElement Element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		
	}
	
	public static void jsClick (WebElement Element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Element);
		
	}

}
