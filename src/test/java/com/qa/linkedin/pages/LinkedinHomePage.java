	package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{
	
	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	
	//create constructor initilize the page object
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	//identify the element using @FindBy annotation
	//@FindBy(locator="locator value")
	//private WebElement elementName;
	
	@FindBy(xpath="//a[@class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	//identify the sigin link lest
	@FindBy(linkText="Sign in")
	private WebElement signInLink;
	
	//identify the Singnin page header text
	@FindBy(xpath="//h1[contains(@class,'heading')]")
	private WebElement signInHeaderText;
	
	//identify the signin edit box
	@FindBy(id="username")
	private WebElement emailEditBox;
	
	//identify the password edit box
	@FindBy(name="session_password")
	private WebElement passwordEditbox;
	
	//identify the signin button
	@FindBy(xpath="//button[@type='submit']")
	private WebElement signInButton;
	
	//identify skip button
	private WebElement skipBtn;
		
	
	String SigninPageTitle="LinkedIn Login, Sign in | LinkedIn";
	String HomePageTitle="LinkedIn: Log In or Sign Up";
	
	//verify the home page linkedin home title
	public void verifyLinkedinHomepageTitle()
	{
		log.debug("Wait for the linked in home page title");
		wait.until(ExpectedConditions.titleContains(HomePageTitle));
		Assert.assertEquals(driver.getTitle(), HomePageTitle);
		
	}
	
	//verify the linkedin logo
	public void verifyLinkedinLogo()
	{
		log.debug("wait for the linkedin logo");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(),"Linkedin logo is not present");
	}
	
    public void clickSigninLink() throws InterruptedException
    {
    	log.debug("click on signin link in home page");
    	click(signInLink);
    	
    }
    
	public void verifyLinkedinSigninTitle()
	{
		log.debug("Wait for the linkedin SingninPage title");
		wait.until(ExpectedConditions.titleContains(SigninPageTitle));
		Assert.assertEquals(driver.getTitle(), SigninPageTitle);
		
	}
	
	//verify the signInHeadderTExt
		public void verifySignInHeaserText()
		{
			log.debug("wait for the signInHeaderText");
			wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
			Assert.assertTrue(signInHeaderText.isDisplayed(),"signInHeaderText is not present");
		}
	//click  click button
		
		  public void clicSigninkButton() throws InterruptedException
		    {
		    	log.debug("click on signin buttonin sign in page");
		    	click(signInButton);
		    	
		    }
		  
		  //click on skip button
		  public void clicSkipkButton() throws InterruptedException
		    {
		    	log.debug("click on SkipButton in sign in process page");
		    	click(skipBtn);
		    }
		    	
		  //do login
		  public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException
		  {
			  log.debug("started login process.....");
			  sendKey(emailEditBox,uname );
			  sendKey(passwordEditbox,pwd );
			  clicSigninkButton(); 
			  try
			  {
				  if(isPresentElement(skipBtn)) {
					  click(skipBtn);
				  }
			  }catch(Exception e)
			  {
				  e.printStackTrace(); 
			  }
			  
			  return  new LinkedinLoggedinPage();
		  }
}
