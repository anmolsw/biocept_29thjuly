package biocept.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import biocept.qa.base.BioceptBase;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.WorkflowCommonMethods;



public class Accession extends BioceptBase{
	

	
	////Lab / Client / Physician Information////
	@FindBy(className ="requisition-number")
	WebElement requisitionNumber;
	@FindBy(css ="input[data-bind='value: CancerType']")
	WebElement cancerType;
	@FindBy(css ="input[data-bind='textInput:$root.Accession().ClientAccessionId']")
	WebElement clientAccessionId;
	@FindBy(css ="input[data-bind='value: FedexTracking']")
	WebElement fedexTracking;
	@FindBy(css ="select[data-bind*='optionsText: 'LabName'']")
	WebElement lab;
	
	@FindBy(id ="client")
	WebElement client;
	
	@FindBy(id ="facility")
	WebElement Ordering_Location;
	@FindBy(id ="physician")
	WebElement Ordering_Provider;
//	/////Patient Information/////
	@FindBy(css ="input[placeholder='First Name']")
	WebElement patientFN;
	@FindBy(css ="input[placeholder='Last Name']")
	WebElement patientLN;
	@FindBy(css ="input[placeholder='Date of Birth']")
	WebElement patientDOB;
	@FindBy(css ="select[data-bind*='value: GenderId']")
	WebElement patientGender;
	@FindBy(id ="countryId")
	WebElement patientCountry;
	@FindBy(css ="input[placeholder='Address Line 1']")
	WebElement patientAddress;
//	
//	/////Insurance/////
	@FindBy(linkText ="Add Insurance")
	WebElement addInsurance;
	@FindBy(css ="input[placeholder='Payor Name']")
	WebElement payorName;
	@FindBy(id ="relationshipId")
	WebElement patientRelationship;
//	
//	/////Specimen / Test/////
	@FindBy(css ="button[data-bind*='EditSpecimen']")
	WebElement addSpecimen;
	@FindBy(css ="input[data-bind*='ExternalSpecimenId']")
	WebElement externalSpecimenId;
	
	@FindBy(id ="autoSpecimenTypes")
	WebElement addSpecimenType;
	@FindBy(id ="autoBodySite")
	WebElement addBodySite;
	@FindBy(id ="autoTransportType")
	WebElement addTransportType;
	@FindBy(id ="collectionTime")
	WebElement addcollectionTime;
	@FindBy(id ="txtReceivedDate")
	WebElement addReceivedDate;
	@FindBy(id ="autoUnitOfMeasurement")
	WebElement addUnitOfMeasurement;
	@FindBy(id ="btn-AddNew")
	WebElement addTubes;
	@FindBy(css ="tbody[data-bind='foreach: SpecimenTubes']")
	WebElement SpecimenTubes;	
	@FindBy(xpath ="//*[@id='addSpecimenModal']/div[2]/div/div[3]/button[2]")
	WebElement saveSpecimen;
	@FindBy(className ="test-assign")
	WebElement testassign;
	@FindBy(css ="input[value='Search Tests']")
	WebElement searchTests;	
	@FindBy(className ="todo-title")
	List<WebElement> childTest;
	
	@FindBy(css ="button[data-bind='click: $root.SaveTestAndPanelConfiguration']")
	WebElement saveTestButton;
	
	
	
////////AssignProfessionalWork
	@FindBy(css ="button[data-bind*='SetupAssignProfessionalWork']")
	WebElement assignProfessionalWork_Button;
	
	@FindBy(id ="chkAssignProfessionalWork")
	WebElement assignProfessionalWork_Checkbox;
	
	@FindBy(id ="CloseBtnAssignProfessionalWork")
	WebElement assignProfessionalWork_CloseButton;

/////add file///
	@FindBy(css ="input[name='files[]']")
	WebElement addFilesButton;
	
/////Add ICD 10 and CPT Code///
	@FindBy(id ="icd10CodeSelect")
	WebElement icd10Code;
	@FindBy(id ="cptCodeSelect")
	WebElement cptCode;
	
	//////Special Instructions/Clinical Information/Treatment Status/Cancer State/Comments////
	@FindBy(css ="textarea[data-bind='value:SpecialInstructions']")
	WebElement specialInstructions;
	@FindBy(css ="textarea[data-bind='value:ClinicalInformation']")
	WebElement clinicalInformation;
	@FindBy(css ="input[data-bind='value: TreatmentStatus']")
	WebElement treatmentStatus;
	@FindBy(css ="input[data-bind='value: CancerState']")
	WebElement cancerState;
	@FindBy(css ="input[placeholder='Add Comment ..']")
	WebElement addComment;
	
	@FindBy(id ="btnSaveAccession")
	WebElement saveAccession;
	
	
	//WebDriverWait wait=new WebDriverWait(driver, 30);
	JavascriptExecutor js = (JavascriptExecutor) driver;
public Accession(){

	PageFactory.initElements(driver, this);
}

public void addLabClientPhysicianInfor () throws InterruptedException {
	WorkflowCommonMethods.scrollIntoView(patientFN);
	patientFN.click();
	ExplicitWait.waitUntilElementToBeClickable(requisitionNumber).sendKeys(accessionprop.getProperty("requisitionNumber"));
	requisitionNumber.sendKeys(Keys.TAB);
	cancerType.sendKeys(accessionprop.getProperty("cancerType"));
	cancerType.sendKeys(Keys.TAB);
	clientAccessionId.sendKeys(accessionprop.getProperty("clientAccessionId"));
	clientAccessionId.sendKeys(Keys.TAB);
	fedexTracking.sendKeys(accessionprop.getProperty("fedexTracking"));
	fedexTracking.sendKeys(Keys.TAB);
	
	try{
		ExplicitWait.waitUntilElementToBeClickable(client).click();
		client.sendKeys(accessionprop.getProperty("client"));
		client.sendKeys(Keys.TAB);
		}catch(ElementNotInteractableException e){
			ExplicitWait.waitUntilElementToBeClickable(client).click();
			client.sendKeys(accessionprop.getProperty("client"));
			client.sendKeys(Keys.TAB);	
		}
		
	
	try{
	ExplicitWait.waitUntilElementToBeClickable(Ordering_Location).click();
	Ordering_Location.sendKeys(accessionprop.getProperty("Ordering_Location"));
	Ordering_Location.sendKeys(Keys.TAB);
	}catch(ElementNotInteractableException e){
		ExplicitWait.waitUntilElementToBeClickable(Ordering_Location).click();
		Ordering_Location.sendKeys(accessionprop.getProperty("Ordering_Location"));
		Ordering_Location.sendKeys(Keys.TAB);	
	}
	Ordering_Provider.sendKeys(accessionprop.getProperty("Ordering_Provider"));
	Ordering_Provider.sendKeys(Keys.TAB);
	
}
	
public void addpatientInformation () {
	
	ExplicitWait.waitUntilElementToBeClickable(patientFN).sendKeys(accessionprop.getProperty("patientFN"));
	patientLN.sendKeys(accessionprop.getProperty("patientLN"));
	patientLN.sendKeys(Keys.TAB);
	patientDOB.sendKeys(Keys.CONTROL + "a");
	patientDOB.sendKeys(accessionprop.getProperty("patientDOB"));
	patientDOB.sendKeys(Keys.TAB);
	Select Gender = new Select(patientGender);
	Gender.selectByVisibleText(accessionprop.getProperty("patientGender"));
	Select Country = new Select(patientCountry);
	Country.selectByVisibleText(accessionprop.getProperty("patientCountry"));
	patientAddress.sendKeys(accessionprop.getProperty("patientAddress"));
}

public void addInsurance () {
	
	
	//addInsurance.click();
	ExplicitWait.waitUntilElementToBeClickable(addInsurance).click();
	payorName.sendKeys(accessionprop.getProperty("payorName"));
	payorName.sendKeys(Keys.TAB);
	Select Relationship = new Select(patientRelationship);
	Relationship.selectByVisibleText("self");
	
}

public void addSpecimen () throws InterruptedException{

	ExplicitWait.waitUntilElementToBeClickable(addSpecimen).click();
	
	WebElement ExtSpecimenId = ExplicitWait.waitUntilElementToBeClickable(externalSpecimenId);
	ExtSpecimenId.click();
	ExtSpecimenId.sendKeys(accessionprop.getProperty("externalSpecimenId"));
	ExtSpecimenId.sendKeys(Keys.TAB);
	ExplicitWait.waitUntilElementToBeClickable(addSpecimenType).sendKeys(accessionprop.getProperty("specimenType"));
	addSpecimenType.sendKeys(Keys.ENTER);
	Thread.sleep(2000);
	ExplicitWait.waitUntilElementToBeClickable(addBodySite).click();
	addBodySite.sendKeys(accessionprop.getProperty("bodySite"));
	addBodySite.sendKeys(Keys.ENTER);
	ExplicitWait.waitUntilElementToBeClickable(addTransportType).click();
	addTransportType.sendKeys(accessionprop.getProperty("transportType"));
	addTransportType.sendKeys(Keys.ENTER);
	addTransportType.sendKeys(Keys.TAB);
	addcollectionTime.sendKeys(Keys.CONTROL + "a");
	addcollectionTime.sendKeys(accessionprop.getProperty("collectionTime"));
	addcollectionTime.sendKeys(Keys.TAB);
	addReceivedDate.sendKeys(Keys.CONTROL + "a");
	addReceivedDate.sendKeys(accessionprop.getProperty("receivedDate"));
	addReceivedDate.sendKeys(Keys.TAB);
	addUnitOfMeasurement.sendKeys(accessionprop.getProperty("UnitOfMeasurement"));
	addUnitOfMeasurement.sendKeys(Keys.ENTER);
	//String totalTube = accessionprop.getProperty("totalTube");
	int totalTube = Integer.parseInt(accessionprop.getProperty("totalTube"));
	for (int i = 0; i < totalTube; i++) {
		ExplicitWait.waitUntilElementToBeClickable(addTubes).click();
			
    }
	
	List<WebElement> SpecimenTubesChild = SpecimenTubes.findElements(By.tagName("tr"));
	 for (int i = 0; i < SpecimenTubesChild.size(); i++) {
		 
		 WebElement tubesChildEle = SpecimenTubesChild.get(i);	
			WebElement tubeVolume = tubesChildEle.findElement((By.cssSelector("input[data-bind='value:TubeVolume']")));
			 tubeVolume.sendKeys(accessionprop.getProperty("tubeVolume"));
			WebElement tubeLotNumber = tubesChildEle.findElement(By.cssSelector("input[data-bind='value:TubeLotNumber"));
			tubeLotNumber.sendKeys(accessionprop.getProperty("tubeLotNumber"));
			WebElement tubeType = tubesChildEle.findElement(By.cssSelector("input[data-bind='value:TubeType']"));
			tubeType.sendKeys(accessionprop.getProperty("tubeType"));
     }

	 ExplicitWait.waitUntilElementToBeClickable(saveSpecimen).click();
	WorkflowCommonMethods.scrollIntoView(testassign);
	ExplicitWait.waitUntilElementToBeVisible(testassign);
	WorkflowCommonMethods.jsClick(testassign);
	//ExplicitWait.waitUntilElementToBeClickable(testassign).click();
	 ExplicitWait.waitUntilElementToBeClickable(searchTests);
}
	 public void addCTCFISHTest (){
		 searchTests.click();
		 searchTests.sendKeys("CTC-FISH");
		 searchTests.sendKeys(Keys.ENTER);	
	 
		 String getTests = accessionprop.getProperty("fishTesst");
			String[] afterSplit = getTests.split(",");
			List<String> fixedLenghtList = Arrays.asList(afterSplit);
			ArrayList<String> CTCFISH_Tests = new ArrayList<String>(fixedLenghtList);
			int Total_CTCFISH_Tests = CTCFISH_Tests.size();
		        int P =1;
		        while(P<= Total_CTCFISH_Tests){
		        //String CurrentTest = CTCFISH_Tests[P-1];
		        	String CurrentTest = CTCFISH_Tests.get(P-1);
		        int availableTestCount = childTest.size();        
		        for (int i = 0; i < availableTestCount; i++) {
		            WebElement childTestElement = childTest.get(i);
		            String childTestName= childTestElement.getText();        
		            if(childTestName.equalsIgnoreCase(CurrentTest)){
		                   boolean checkboxstate =  driver.findElement(By.id("childTest"+i)).isEnabled();
		                    if (checkboxstate == true) {
		                        driver.findElement(By.id("childTest"+i)).click();
		                     }
		            }
		        }
		        P=P+1;
		        }
	 }
	 
	 
	 public void addBRAFTest () throws InterruptedException{
		 searchTests.click();
		 searchTests.sendKeys("BRAF");
		 searchTests.sendKeys(Keys.ENTER);
		
	 }
	 public void addKRASTest () throws InterruptedException{
		 searchTests.click();
		 searchTests.sendKeys("KRAS");
		 searchTests.sendKeys(Keys.ENTER);	
		
	 }
	 public void addMolEGFRTest () throws InterruptedException{
		 searchTests.click();
		 searchTests.sendKeys("MolEGFR");
		 searchTests.sendKeys(Keys.ENTER);
		
	 }
	 
	 public void addNRASTest () throws InterruptedException{
		 searchTests.click();
		 searchTests.sendKeys("NRAS");
		 searchTests.sendKeys(Keys.ENTER);
	
	 }
	 
	 public void addNGSTest () throws InterruptedException{
		 searchTests.click();
		 searchTests.sendKeys("Oncomine Breast");
		 searchTests.sendKeys(Keys.ENTER);
	
	 }
	 
	 public void saveTest(){
		 ExplicitWait.waitUntilElementToBeClickable(saveTestButton).click();

	 }
	      
	 public void assignProfessionalWork(){
		 
		 WorkflowCommonMethods.scrollIntoView(assignProfessionalWork_Button);
		 WorkflowCommonMethods.jsClick(assignProfessionalWork_Button);
		 ExplicitWait.waitUntilElementToBeClickable(assignProfessionalWork_Checkbox).click();
		 ExplicitWait.waitUntilElementToBeClickable(assignProfessionalWork_CloseButton).click();
		
	 }    
	 
	 


public void addICDCPTCodes () throws InterruptedException{
	
	 icd10Code.sendKeys(accessionprop.getProperty("icd10Code"));
	 icd10Code.sendKeys(Keys.ENTER);
	 cptCode.sendKeys(accessionprop.getProperty("cptCode"));
	 cptCode.sendKeys(Keys.ENTER);
	}

public void addClinicalInformation () throws InterruptedException{
	
	
	specialInstructions.sendKeys(accessionprop.getProperty("specialInstructions"));
	clinicalInformation.sendKeys(accessionprop.getProperty("clinicalInformation"));
	treatmentStatus.sendKeys(accessionprop.getProperty("treatmentStatus"));
	cancerState.sendKeys(accessionprop.getProperty("cancerState"));
	addComment.sendKeys(accessionprop.getProperty("addComment"));
	addComment.sendKeys(Keys.ENTER);
}
public void saveAccesion (){
	WorkflowCommonMethods.scrollIntoView(saveAccession);
	try{
	ExplicitWait.waitUntilElementToBeClickable(saveAccession).click();
	}catch(Exception e){
	ExplicitWait.waitUntilElementToBeClickable(saveAccession).click();		
	}
}


}
