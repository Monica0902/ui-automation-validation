package com.cucumberFramework.stepdefinitions;

import cucumber.api.java.en.And;

import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.pageObjects.AmexLandingGoldPage;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AmexPageStepDefinitions extends TestBase {

	AmexLandingGoldPage loginPage = new AmexLandingGoldPage(driver);
	WaitHelper waitHelper = new WaitHelper(driver);
	
	@Given("^I am on the Login page URL \"([^\"]*)\"$")
	public void i_am_on_the_Login_page_URL(String url) throws Throwable {
		driver.get(url);
		loginPage.clickAlert("accept");
	}

	@When("^I click on the Cartes American Express link$")
	public void iClickOnTheCartesAmericanExpressLink() {
		loginPage.navigateToCartesAmericanExpress();
	}

	@Then("^I will select the En savoir plus under Gold$")
	public void iWillSelectTheEnSavoirPlusUnderGold() {
		loginPage.selectGoldEnSavoirPlus();
	}

	@And("^I will proceed to the next page by clicking Demandez votre Carte$")
	public void iWillProceedToTheNextPageByClickingDemandezVotreCarte() {
		loginPage.ClickDemandezvotreCarte();
	}

	@Then("^I will fill in the userDetails$")
	public void iWillFillInTheUserDetails() {
		loginPage.fillDetails();
	}

	@And("^I will click on continue button to evaluate the error messages$")
	public void iWillClickOnContinueButtonToEvaluateTheErrorMessages() {
		if(loginPage.btnContinue.isEnabled())
			loginPage.errorValidationNull();
	}

	@And("^Click on continue button$")
	public void clickOnContinueButton() {
		loginPage.clickContinueBtn();
	}
}