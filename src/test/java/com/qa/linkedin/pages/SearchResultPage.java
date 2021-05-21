package com.qa.linkedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class SearchResultPage extends BasePageWeb {

	private Logger log = Logger.getLogger(SearchResultPage.class);

	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}

//identifysee all people linktext
	@FindBy(xpath = "//div[contains(@class,'search-results__cluster-bottom-banner' )]/a")
	private WebElement seeAllPeopleResult;

	@FindBy(xpath = "//div[@class='pb2 t-black--light t-14']")
	private WebElement searchResultText;

	@FindBy(xpath = "//ul[contains(@class,'global-nav__primary-items')]/li[1]/a")
	private WebElement HomeTab;

	String searchResultPageTitle = "Search | LinkedIn";

	public void validateSearchResultPageTitle() {
		log.debug("wait for the search result page title");
		wait.until(ExpectedConditions.titleContains(searchResultPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(searchResultPageTitle));

	}

	public long getResultCount() {
		log.debug("performing fetching result count for the given peopel ");

		try {
			if (isPresentElement(seeAllPeopleResult)) {
				click(seeAllPeopleResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("wai for the search result text");
		wait.until(ExpectedConditions.visibilityOf(searchResultText));
		log.debug("get the search result text from web page");
		String txt = searchResultText.getText();
		System.out.println("search result text is" + txt);

		// txt="About 530,000 results"

		String[] str = txt.split("\\s");
		// str[]=["About", "5,30,000","result"]
		//          0        1              2
		
		String s=str[1];
		log.debug("result count in string format....." + s);
		String finalStringCnt = str[1].replace(",", "").trim();
		log.debug("convert String in to long integer");
		long count = Long.parseLong(finalStringCnt);
		return count;

	}
	
	public void clickHomeTab() throws InterruptedException
	{
		
		log.debug("click on home tab");
		clickUsingJsExecutor(HomeTab);
	}
	}

