package biocept.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;

public class FISHReloAnalysis extends BioceptBase{


	@FindBy(css="input[data-bind*='value: ComponentValue']")
	WebElement fishReloInitials;

	
	@FindBy(css="input[data-bind*='FileUploadInput']")
	WebElement uploadCSV;
	
	@FindBy(css="input[placeholder='Target']")
	WebElement target;
	
	@FindBy(css ="span[data-notify='message']")
	WebElement completeMessage;
	
	
	public FISHReloAnalysis(){
		PageFactory.initElements(driver, this);
	}
	

	public void fishReloInitials() throws InterruptedException{
		Thread.sleep(4000);
		fishReloInitials.sendKeys(prop.getProperty("username"));	
	}
	
	
	public void uploadCSVFile() throws InterruptedException{
		
		WebElement TestName = driver.findElement(By.cssSelector("span[data-bind='text:ChannelTestDisplayName']"));
		ExplicitWait.waitUntilElementToBeVisible(TestName);
		ExplicitWait.waitUntilElementToBeClickable(target).click();
		uploadCSV.sendKeys(System.getProperty("user.dir") + "/src/main/java/biocept/qa/testdata/a.csv");
		Thread.sleep(3000);
	
}
	

	public String completeMessage() throws InterruptedException{
		
		String Message = ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
		if(Message.contains("Activity Components have been successfully completed.")){
				
			
		}else{
			while(Message!="Activity Components have been successfully completed."){
				String NewMessage = ExplicitWait.waitUntilElementToBeVisible(completeMessage).getText();
				Message = Message.replace(NewMessage, Message);
			}
		}
		return Message;
		
		
		
		
}

}
