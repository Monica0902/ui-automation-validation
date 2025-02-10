package com.cucumberFramework.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cucumberFramework.helper.WaitHelper;
import org.testng.Assert;

public class AmexLandingGoldPage {

	private WebDriver driver;
	Actions builder;
	@FindBy(xpath = "(//*[contains(@class,'product-nav-item-content ')])[1]")
	public WebElement cartesAmex;

	@FindBy(xpath = "//a[contains(@href,'proprietary-gold')]")
	public WebElement goldEnSavoirPlus;

	@FindBy(xpath = "//*[contains(@class,'button') and contains(text(),'Demandez')]")
	public WebElement btnDemandez;

	@FindBy(xpath = "//*[contains(@class,'granular-banner-inner')]")
	public WebElement granularBanner;

	@FindBy(xpath="//button[contains(text(),'Tout Accepter')]")
	public WebElement acceptBtn;

	@FindBy(xpath = "//*[contains(@for,'MS')]")
	public WebElement rBtnMs;

	@FindBy(xpath = "//*[contains(@for,'MR')]")
	public WebElement rBtnMr;

	@FindBy(xpath = "//*[contains(@name,'firstName')]")
	public WebElement txtFirstName;

	@FindBy(xpath = "//*[contains(@name,'lastName')]")
	public WebElement txtLastName;

	@FindBy(xpath = "//*[contains(@name,'dateOfBirth')]")
	public WebElement txtDateOfBirth;

	@FindBy(xpath = "//*[contains(@name,'email')]")
	public WebElement txtEmail;

	@FindBy(xpath = "//*[contains(@id,'country')]")
	public WebElement ddCountryCode;

	@FindBy(xpath = "//*[contains(@name,'mobile')]")
	public WebElement txtPhoneNo;

	@FindBy(xpath = "//*[contains(@type,'submit') and contains(text(),'Continuer')]")
	public WebElement btnContinue;

	@FindBy(xpath = "//*[contains(text(),'Merci de préciser votre civilité.')]")
	public WebElement rbtErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Prénom ob')]")
	public WebElement firstNameErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Nom ob')]")
	public WebElement lastNameErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Vous devez')]")
	public WebElement dobErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Merci de vérifier')]")
	public WebElement emailErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Téléphone mobile obligatoire')]")
	public WebElement phoneErrMsgNull;

	@FindBy(xpath = "//*[contains(text(),'Veuillez corriger')]")
	public WebElement pageErrMsgNull;

	WaitHelper waitHelper;

	public AmexLandingGoldPage(WebDriver driver){
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
	public void handlebanner()
	{
		try {
			waitHelper.WaitForElement(granularBanner, 5);
			acceptBtn.click();
		}
		catch (Exception e) {
			System.out.println("No standard accept button found.");
		}
	}
	public void navigateToCartesAmericanExpress()
	{
		handlebanner();
		cartesAmex.click();
		String title = driver.getTitle();
		Assert.assertEquals(title,"Cartes American Express : Dépenser + pour Gagner | AMEX France");
		System.out.println(title);
	}

	public void selectGoldEnSavoirPlus()
	{
		handlebanner();
		waitHelper.WaitForElementBtn(goldEnSavoirPlus,10);
		goldEnSavoirPlus.click();
		String title = driver.getTitle();
		Assert.assertEquals(title,"Gold American Express: La Carte à la Hauteur de Votre Quotidien");
		System.out.println(title);
	}

	public void ClickDemandezvotreCarte()
	{
		handlebanner();
		waitHelper.WaitForElementBtn(btnDemandez,10);
		btnDemandez.click();
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title,"American Express - CARTE GOLD AMERICAN EXPRESS");

	}

	public void fillDetails()
	{
		handlebanner();
		waitHelper.WaitForElement(rBtnMr,10);
		if(!this.rBtnMr.isSelected()) {
			this.rBtnMr.click();
		}
		this.txtFirstName.sendKeys("John");
		txtLastName.sendKeys("Smith");
		txtDateOfBirth.sendKeys("01/01/1999");
		txtEmail.sendKeys("john.smith@gmail.com");
		Select selet = new Select(ddCountryCode);
		selet.selectByValue("Algérie");
		txtPhoneNo.sendKeys("1234567890");
	}
	public void clickContinueBtn()
	{
		waitHelper.WaitForElementBtn(btnContinue,10);
		btnContinue.click();
	}
	public void errorValidationNull()
	{
		handlebanner();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnContinue);
		clickContinueBtn();
		String rbtErrMsg = rbtErrMsgNull.getText();
		Assert.assertEquals(rbtErrMsg,"Merci de préciser votre civilité.");
		String fnameErrMsg = firstNameErrMsgNull.getText();
		Assert.assertEquals(fnameErrMsg,"Prénom obligatoire.");
		String lnameErrMsg = lastNameErrMsgNull.getText();
		Assert.assertEquals(lnameErrMsg,"Nom obligatoire.");
		String dobErrMsg = dobErrMsgNull.getText();
		Assert.assertEquals(dobErrMsg,"Vous devez avoir plus de 18 ans.");
		String emailErrMsg = emailErrMsgNull.getText();
		Assert.assertEquals(emailErrMsg,"Merci de vérifier le format de votre adresse email (exemple : nom@domaine.fr). Les caractères autorisés sont : lettres, chiffres, tirets (_-), arobase (@), et point (.).");
		String phoneErrMsg = phoneErrMsgNull.getText();
		Assert.assertEquals(phoneErrMsg,"Téléphone mobile obligatoire en chiffres uniquement et sans espaces. Exemple France, Guadeloupe, Martinique, Guyane, La Réunion, Saint- Barthélemy, Saint Martin, Mayotte: - 0612345678 - 0712345678 IMPORTANT : Pour tout autre pays ou région, dont Polynésie Française et Nouvelle Calédonie, merci de sélectionner l’indicatif du territoire correspondant et saisir le numéro de mobile sans le préfixe « 0 » au début.");
		String pageErrMsg = pageErrMsgNull.getText();
		Assert.assertEquals(pageErrMsg,"Veuillez corriger les erreurs ci-dessus pour continuer.");

	}

}
