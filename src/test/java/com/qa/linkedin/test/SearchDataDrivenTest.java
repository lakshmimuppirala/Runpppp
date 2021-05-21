package com.qa.linkedin.test;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase  {
	
	private String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	private Logger log=Logger.getLogger(SearchDataDrivenTest.class);
	LinkedinHomePage lHmPage;
	LinkedinLoggedinPage llPage;
	SearchResultPage srPage;
 
  @BeforeClass
  public void beforeClass() throws InterruptedException, Exception {
	  
	  
	  log.debug("page initilization");
	  lHmPage=new LinkedinHomePage();
	  llPage=new LinkedinLoggedinPage();
	  srPage=new SearchResultPage();
	  log.debug("calling title verifiation");
	  lHmPage.verifyLinkedinHomepageTitle();
	  lHmPage.verifyLinkedinLogo();
	  lHmPage.clickSigninLink();
	  lHmPage.verifySignInHeaserText();
	  llPage=lHmPage.doLogin(readPropertyValue("username"), readPropertyValue("password"));
	  
	  
	  
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  
	  
	/*  log.debug("Perform the logout operations from the application");*/
	  llPage.doLogut();
	 /*lHmPage.verifyLinkedinHomepageTitle();
	  
	  */
	  
  }
  
  
  @Test(dataProvider="getData")
  public void searchDataDrivenTest(String keyword) throws Exception {
	  
	  log.debug("started executing the search test  for people"+keyword);
	  llPage.verifyProfileRailCard();
	 srPage= llPage.doPeopleSearch(keyword);
	 long count=0;
	try {
		count = srPage.getResultCount();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 log.debug("search result count for:"+keyword +" is "+count);
	 Thread.sleep(2000);
	 srPage.clickHomeTab();
	/* srPage.validateSearchResultPageTitle();
	long cnt=0;
	try {
		cnt = srPage.getResultCount();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	log.debug("search result count for "+keyword+" is:"+cnt);
	Thread.sleep(2000);
	log.debug("click on home tab to go to loggedin page");
	srPage.clickHomeTab();
	Thread.sleep(2000);*/
	log.debug("****************************One Iteration is done*****************************************");
	  
	  
  }
  
  @DataProvider()
  public Object[][] getData() throws InvalidFormatException, IOException{
	 Object[][] data=new ExcelUtils().getTestData(path, "Sheet1");
	 
	 return data;
  }
	  
  }


