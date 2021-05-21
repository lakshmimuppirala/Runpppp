 package com.qa.linkedin.pages;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb {

	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	
	//create constructor
	public LinkedinLoggedinPage() {
	
	PageFactory.initElements(driver,this);
	}
	//identify the element using @FindBy annotation
		//@FindBy(locator="locator value")
		//private WebElement elementName;
	
		//identify profile rail card
	    @FindBy(xpath ="//div[contains(@class,'profile-rail-card')]")
		private WebElement profileRailCard;
		
		//identify the img icon
		@FindBy(xpath="//img[contains(@class,'global-nav__me-photo ember-view')]")
		private WebElement profileImageIcon;
		
		//identify the signout link
		@FindBy(xpath="//a[normalize-space()='Sign Out']")
		private WebElement signOutLink;
		
		//identify the search edit box
		@FindBy(xpath="//input[contains(@class,'search-global-typeahead__input always-show-placeholder')]")
		private WebElement searchEditBox;
		
		
		//respective methods
		public void verifyProfileRailCard() {
		
		log.debug("wait for the profile rail card");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "profile rail caid is not available");
		 		
		}
		
		public void doLogut() throws InterruptedException
		{
			log.debug("perform the logout operation from the application");
			    click(profileImageIcon);
				click(signOutLink);
			System.out.println ("Hello Hai");
		}
		
		//type the search button in people search
		public SearchResultPage doPeopleSearch(String keyword) throws InterruptedException
		{
			log.debug("perform the peopek search");
			sendKey(searchEditBox,keyword);
			Thread.sleep(2000);
			log.debug("press the enter key to sudmit the search form");
			searchEditBox.sendKeys(Keys.ENTER);
			return new SearchResultPage();
				System.out.println ("Hello Hai");
		}
 
	
	
	
}
