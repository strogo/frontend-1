package com.gu.test;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.annotation.en.Then;

public class TypographySteps {

	private final SharedDriver webDriver;

	public TypographySteps(SharedDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Then("^the typeface should be rendered as \"([^\"]*)\"$")
	public void the_typeface_should_be_rendered_as(String fontName) throws Throwable {
		//check the font family for header contains the fontName
		 Assert.assertTrue(webDriver.getelementCssValue(By.cssSelector("h1"), "font-family").contains(fontName));
	}
}