package com.cucumberFramework.pageObjects;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cucumberFramework.helper.WaitHelper;
import org.testng.Assert;

public class LoginLogoutPage {

	private WebDriver driver;
	Actions builder;
	@FindBy(xpath = "(//*[contains(@class,'product-nav-item-content ')])[1]")
	public WebElement cartesAmex;

	@FindBy(xpath = "//a[contains(@href,'proprietary-gold')]")
	public WebElement goldEnSavoirPlus;

	@FindBy(xpath = "//*[contains(@class,'button') and contains(text(),'Demandez')]")
	public WebElement btnDemandez;

	@FindBy(xpath="//input[@type='email']")
	public WebElement userName;

	@FindBy(xpath="//input[@id='continue']")
	WebElement Continue;

	@FindBy(xpath="//input[@type='password']")
	public WebElement password;

	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement loginButton;

	@FindBy(xpath="//div[@id='nav-tools']/a[@data-nav-role='signin']")
	WebElement SignInfromNav;

	@FindBy(xpath="//span[contains(text(),'Sign')]/parent::a")
	public WebElement logoutBtn;

	@FindBy(xpath="//div[@id='nav-shop']/a")
	public WebElement allShopNav;

	@FindBy(xpath="//span[@data-nav-panelkey='TvApplElecPanel']")
	public WebElement TvApplElecPanel;

	@FindBy(xpath="//span[contains(text(),'Headphones')]/parent::a")
	public WebElement headPhonesCatLnk;

	@FindBy(xpath="//div[@id='mainResults']/ul/li[1]/div/div/div/a[contains(@class,'access-detail-page')]")
	public WebElement firstHeadPhoneLnk;

	@FindBy(xpath="//input[@id='add-to-cart-button']")
	public WebElement addToCartBtn;

	@FindBy(xpath="//a[@id='nav-cart']")
	public WebElement cartButton;

	@FindBy(xpath="//form[@id='activeCartViewForm']/div[@data-name='Active Items' or contains(@class,'sc-list-body')]//input[@value='Delete']")
	public List<WebElement> itemList;

	//form[@id='activeCartViewForm']/div[@data-name='Active Items' or contains(@class,'sc-list-body')]//input[@value='Delete']

	@FindBy(xpath="//div[contains(@class,'nav-search-field')]/input")
	public WebElement itemSearchField;

	@FindBy(xpath="//div[starts-with(@class,'sg-col-4')]/div[@class='sg-col-inner']/div/h5/a")
	public WebElement secondMacbookItem;

	@FindBy(xpath="//select[@id='quantity' or @name='quantity']")
	public List<WebElement> qtyField;


	WaitHelper waitHelper;

	public LoginLogoutPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		builder = new Actions(driver);
	}
	public static boolean checkAlert(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	public void clickAlert(String act) {
		if (checkAlert(driver)) {
			Alert alert = driver.switchTo().alert();
			if (act.equalsIgnoreCase("accept")) {
				alert.accept();
			} else if (act.equalsIgnoreCase("dismiss")) {
				alert.dismiss();
			}
		}
		else {
			System.out.println("No alert is present.");
		}
	}
	public void navigateToCartesAmericanExpress()
	{
		cartesAmex.click();
		String title = driver.getTitle();
		Assert.assertEquals(title,"Cartes American Express : Dépenser + pour Gagner | AMEX France");
		System.out.println(title);
	}

	public void selectGoldEnSavoirPlus()
	{
		goldEnSavoirPlus.click();
		String title = driver.getTitle();
		Assert.assertEquals(title,"Gold American Express: La Carte à la Hauteur de Votre Quotidien");
		System.out.println(title);
	}

	public void ClickDemandezvotreCarte()
	{
		btnDemandez.click();
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"American Express - CARTE GOLD AMERICAN EXPRESS");

	}

	public void fillDetails()
	{

	}

//	public void enterUserName(String userName){
//		this.userName.sendKeys(userName);
//	}
//
//	public void enterPassword(String password){
//		this.password.sendKeys(password);
//	}
//
//	public void clickLoginButton(){
//		loginButton.click();
//	}
//
//	public void enterSearchItemandAddToCart(String item){
//		String mainWindow=driver.getWindowHandle();
//		this.itemSearchField.sendKeys(item);
//		this.itemSearchField.submit();
//		secondMacbookItem.click();
//		Set<String> set =driver.getWindowHandles();
//		Iterator<String> itr= set.iterator();
//		while(itr.hasNext()){
//		 String childWindow=itr.next();
//		 if(!mainWindow.equals(childWindow)){
//			 driver.switchTo().window(childWindow);
//			 System.out.println(driver.switchTo().window(childWindow).getTitle());
//			 if(qtyField.size()>=1){
//
//				 Select sel = new Select(qtyField.get(0));
//				 sel.selectByValue("2");
//			 }
//
//			 JavascriptExecutor js = (JavascriptExecutor)driver;
//			 js.executeScript("arguments[0].scrollIntoView(true);",addToCartBtn);
//			 js.executeScript("arguments[0].click();", addToCartBtn);
//			 if(driver.findElements(By.xpath("//div[@class='a-popover-inner']//button[contains(text(),'Skip')]")).size()>=1){
//
//				 driver.findElements(By.xpath("//div[@class='a-popover-inner']//button[contains(text(),'Skip')]")).get(0).click();
//			 }
//			 //addToCartBtn.click();
//			 //driver.close();
//		 }
//		}
//		driver.switchTo().window(mainWindow);
//	}
//
//	public void clickSignInButton(){
//		Actions builder = new Actions(driver);
//		builder.moveToElement(SignInfromNav).build().perform();
//		SignInfromNav.click();
//	}
//
//	public void clearCartItemifExist(){
//		cartButton.click();
//		int i = itemList.size();
//		if(i>=1){
//			itemList.get(0).click();
//			i = itemList.size();
//		}
//	}
//
//	public void clickHeadphonesLnk(){
//		Actions builder = new Actions(driver);
//		builder.moveToElement(allShopNav).build().perform();
//		builder.moveToElement(TvApplElecPanel).build().perform();
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", headPhonesCatLnk);
//
//	}
//
//	public void AddHeadphoneToCart(){
//
//		firstHeadPhoneLnk.click();
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		 js.executeScript("arguments[0].click();", addToCartBtn);
//	}
//
//	public void clickContinueButton(){
//		Continue.click();
//	}
//
//
//
//	public void clickLogoutButton(){
//		Actions builder = new Actions(driver);
//		builder.moveToElement(SignInfromNav).build().perform();
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		 js.executeScript("arguments[0].click();", logoutBtn);;
//	}


}
