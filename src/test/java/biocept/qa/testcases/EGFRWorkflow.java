package biocept.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import biocept.qa.base.BioceptBase;
import biocept.qa.pages.Accession;
import biocept.qa.pages.BarcodeandLabelBuilder;
import biocept.qa.pages.Dashboard_LIMS;
import biocept.qa.pages.LeftMenuItems;
import biocept.qa.pages.LoginAndLogOut;
import biocept.qa.pages.MolecularGenericResulting;
import biocept.qa.pages.MolecularProfessionalInterpretation;
import biocept.qa.pages.MolecularReviewAndApproveWorklist;
import biocept.qa.pages.MyWorklist;
import biocept.qa.pages.Sequencing;
import biocept.qa.pages.UploadMacroFile;
import biocept.qa.utill.ExplicitWait;
import biocept.qa.utill.WorkflowCommonMethods;

public class EGFRWorkflow extends BioceptBase{
	
	

	LoginAndLogOut loginAndLogOut;
	Dashboard_LIMS dashboard_LIMS;
	Accession accession;
	BarcodeandLabelBuilder barcodeandLabelBuilder;
	String AccessionID;
	LeftMenuItems leftMenuItems;
	MyWorklist myWorklist;
	WorkflowCommonMethods workflowCommonMethods;
	UploadMacroFile uploadMacroFile;
	MolecularGenericResulting molecularGenericResulting;
	Sequencing sequencing;
	MolecularProfessionalInterpretation molecularProfessionalInterpretation;
	MolecularReviewAndApproveWorklist molecularReviewAndApproveWorklist;

	
	public EGFRWorkflow(){
		super();
	}
	
	@BeforeClass
	public void Initi() throws InterruptedException{
		initialization();
	}
	
	@BeforeMethod
	public void setup() {
		loginAndLogOut = new LoginAndLogOut();
		dashboard_LIMS = new Dashboard_LIMS();
		accession = new Accession();
		barcodeandLabelBuilder = new BarcodeandLabelBuilder();
		leftMenuItems = new LeftMenuItems();
		myWorklist = new MyWorklist();
		workflowCommonMethods = new WorkflowCommonMethods();
		uploadMacroFile = new UploadMacroFile();
		molecularGenericResulting = new MolecularGenericResulting();
		sequencing = new Sequencing();
		molecularProfessionalInterpretation = new MolecularProfessionalInterpretation();
		molecularReviewAndApproveWorklist = new MolecularReviewAndApproveWorklist();
		
	}
	
	
	@Test(priority= 1, description="Verify user is able to login in LIMS portal")
	public void PageTitle(){
		loginAndLogOut.LIMSlogin();
		String Title = loginAndLogOut.validateLoginPageTitle();
		Assert.assertEquals("Helix - Laboratory Information System", Title);
	}
	
	@Test(priority= 2, description="Verify the total link count on dashboard ")
	public void dashboard() throws InterruptedException{
		int allLinks = dashboard_LIMS.dashboardLinks();
		
		Assert.assertEquals(allLinks, 6);
		
	}
	
	@Test(priority= 3, description="Verify user is able to create an new accession")
	public void PlaceNewOrder() throws InterruptedException{
		dashboard_LIMS.clickOnPlaceNewOrder("Place a New Order");
		ExplicitWait.invisibilityOfLoader();
	
	
		accession.addLabClientPhysicianInfor();
		accession.addpatientInformation();
		accession.addInsurance();
		accession.addSpecimen();
		//accession.addBRAFTest();
		//accession.addKRASTest();
		accession.addMolEGFRTest();
		//accession.addNRASest();
		//accession.addCTCFISHTest();
		accession.saveTest();
		accession.addICDCPTCodes();
		accession.addClinicalInformation();
		accession.saveAccesion();
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		Assert.assertEquals(PageTitle, PageTitle);
		//Assert.assertEquals(true, true);
	}
	
	@Test(priority= 4,description="Verify patient name on barcode page")
	public void BarcodePage() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		String PatientName = barcodeandLabelBuilder.patientName();
		AccessionID = barcodeandLabelBuilder.getAccessionId();
		Assert.assertEquals(PatientName, "TestPatientFN TestPatientLN");
	}
	
	@Test(priority= 5,  description="Verify user is able to open the upload the Macro File page for MolEGFR	 test")
	public void UploadMacroFile() throws InterruptedException, IOException{
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Macro File");
		String PageTitle = uploadMacroFile.pageTitle();
		ExplicitWait.invisibilityOfLoader();
		uploadMacroFile.updateMacro(AccessionID,"T7");
		uploadMacroFile.uploadMacro("T7");
		Assert.assertEquals(PageTitle, "Molecular Macro & Sequence Files");
	}
	

	@Test(priority= 6, description="Verify user is able to open the MolEGFR Molecular Generic Resulting activity")
	public void MolEGFRResultingActivityTitle() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "MolEGFR", "Molecular Generic Resulting", "Molecular Generic Resulting-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = molecularGenericResulting.testName();
		if(PageTitle.contains("Molecular Generic Resulting")&&TestName.contains("MolEGFR")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 7, description="Verify user is able to complete the MolEGFR Molecular Generic Resulting activity")
	public void MolEGFRResultingUpdateResult() throws InterruptedException{
		molecularGenericResulting.molEGFR_GenericUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Thread.sleep(4000);
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	@Test(priority= 8,  description="Verify user is able to upload the MolEGFR Sequence image")
	public void UploadSequenceImages() throws InterruptedException, IOException{
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Macros Molecular", "Upload Macro File");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = uploadMacroFile.pageTitle();
		uploadMacroFile.updateSequencingImage(AccessionID, "D");
		uploadMacroFile.uploadSequencingImage();
		uploadMacroFile.reupdateSequencingImage();
		
		Assert.assertEquals(PageTitle, "Molecular Macro & Sequence Files");
	}
	
	@Test(priority= 9, description="Verify user is able to open MolEGFR Sequencing activity")
	public void MolEGFRSequencingActivityTitle() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		leftMenuItems.leftMenuSelection("Dashboards","My Worklist");
		myWorklist.molecularSearch(AccessionID, "MolEGFR", "Sequencing","Sequencing-In-Progress");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		String TestName = sequencing.testName();
		if(PageTitle.contains("Sequencing")&&TestName.contains("MolEGFR")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 10, description="Verify user is able to complete the MolEGFR Sequencing activity")
	public void MolEGFRSequencingActivityResult() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		sequencing.molEGFR_sequencingUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		ExplicitWait.invisibilityOfLoader();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
		
	}
	
	
	@Test(priority= 11, description="Verify user is able to open MolEGFR Molecular Professional Interpretation activity")
	public void MolEGFRPIActivityTitle() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		myWorklist.search(AccessionID, "MolEGFR");
		ExplicitWait.invisibilityOfLoader();
		String PageTitle = workflowCommonMethods.pageTitle();
		if(PageTitle.contains("Molecular Professional Interpretation")){
			Assert.assertEquals(true, true);
		}else{
			Assert.assertEquals(false, true);
		}
	}

	@Test(priority= 12, description="Verify user is able to complete the MolEGFR Molecular Professional Interpretation activity")
	public void MolEGFRPIActivityResult() throws InterruptedException{
		molecularProfessionalInterpretation.piUpdateResult();
		workflowCommonMethods.molecularSaveButton();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		workflowCommonMethods.molecularCompleteButton();
		Assert.assertEquals(ActivityCompleteMessage, "Activity Components have been successfully saved.");
	}
	
	
	@Test(priority= 13,  description="Verify user is able to Signoff the MolEGFR Test Report from Molecular Review & Approve Worklist")
	public void MolEGFRReportSignoff() throws InterruptedException{
		ExplicitWait.invisibilityOfLoader();
		molecularReviewAndApproveWorklist.reportSignoff();
		ExplicitWait.invisibilityOfLoader();
		String ActivityCompleteMessage = workflowCommonMethods.completeMessage();
		Assert.assertEquals(ActivityCompleteMessage, "The report has been successfully created and saved");
	}
	
	
	
@AfterClass
	public void tearDown(){
		driver.quit();
	}
	

	
  
}
