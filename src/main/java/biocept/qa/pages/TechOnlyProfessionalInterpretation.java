package biocept.qa.pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class TechOnlyProfessionalInterpretation extends BioceptBase{
	
	@FindBy(css="a[data-bind*='ReviewAndApprove']")
	WebElement reviewAndApproveTab;
	
	@FindBy(css="input[data-bind*=ReviewedCTCResult]")
	WebElement review;	
	
	@FindBy(css="button[data-bind*='ApproveCtcResults']")
	WebElement qcComplete;
	
	@FindBy(css="div[class='bootbox-body']")
	WebElement confirmationMessageEle;
	
	@FindBy(css="button[data-bb-handler='confirm']")
	WebElement okButtonEle;
	
	@FindBy(css="input[value='Go Back to Physician Portal']")
	WebElement goBacktoPhysicianPortalEle;
	
	@FindBy(css="lis_component_review_and_approve > div:nth-child(1) > div:nth-child(2) > div > label > span")
	WebElement review_Clientportal;	
	
	@FindBy(css="button[data-bind*='click:SignOff")
	WebElement signOffEle;
	
	@FindBy(id="txtbx")
	List<WebElement> txtbx;
	
	@FindBy(xpath ="//*[@id='panel-default']/div[1]/strong")
	WebElement htmlReportTitle;
	
	@FindBy(css ="span[data-notify='message']")
	WebElement completeMessage;
	


	
	public TechOnlyProfessionalInterpretation(){
		PageFactory.initElements(driver, this);
	}
	
	public void fishReviewTab(){
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeClickable(reviewAndApproveTab).click();
	
	}
	
	public boolean qcCompleteButton(){
		ExplicitWait.waitUntilElementToBeVisible(htmlReportTitle);
		return ExplicitWait.waitUntilElementToBeVisible(qcComplete).isDisplayed();
		
	}
	
	public String qccompleteConfirmation() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		ExplicitWait.waitUntilElementToBeVisible(htmlReportTitle);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", review);
		//ExplicitWait.waitUntilElementToBeClickable(review).click();
		ExplicitWait.waitUntilElementToBeClickable(qcComplete).click();
		Thread.sleep(1000);
		return confirmationMessageEle.getText();
		
	}
	
	public void clickOnOkButton(){
		okButtonEle.click();
		
		
	}
	
	public boolean goBacktoPhysicianPortalButton(){
		return ExplicitWait.waitUntilElementToBeVisible(goBacktoPhysicianPortalEle).isDisplayed();

	}
	
	public void signOff(){
		
		ExplicitWait.waitUntilElementToBeVisible(htmlReportTitle);
		ExplicitWait.waitUntilElementToBeVisible(review_Clientportal);
		ExplicitWait.waitUntilElementToBeClickable(review_Clientportal);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", review_Clientportal);
		ExplicitWait.waitUntilElementToBeVisible(signOffEle).click();
		
	}
	

	public void waitUntilTargetElementsToBeVisible(){
		
		ExplicitWait.waitUntilAllElementToBeVisible(txtbx);
	}
	
	public String completeMessage (){
		ExplicitWait.invisibilityOfLoader();
		String message =ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
		return message;
	}
	
}
