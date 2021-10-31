package stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;


public class DemoDefinitions {
    WebDriver driver = null;

    @Given("that the Browser is open")
    public void thatTheBrowserIsOpen() {
        driver = new ChromeDriver();
    }

    @Then("the test can proceed")
    public void theTestCanProceed() {
        //Enter a navigation verification if needed
        driver.manage().window().maximize();
        System.out.println("Automated Chrome Browser is ready to go!");

    }

    @Given("that I enter for PC Part picker in the Google search bar")
    public void that_i_enter_for_pc_part_picker_in_the_google_search_bar() {

        driver.get("https://www.google.com/");
        WebElement wait = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@alt='Google']")));

        WebElement searchBar = driver.findElement(By.xpath("//*[@title='Search']"));
        searchBar.clear();
        searchBar.click();
        searchBar.sendKeys("PcPartPicker.com");
        searchBar.sendKeys(Keys.ENTER);

    }
    @When("I click on the System Builder Link")
    public void i_click_on_the_system_builder_link() {

        WebElement systemBuilderLink = driver.findElement(By.xpath("//*[contains(@href,'pcpartpicker') and (text()='System Builder')]"));
        systemBuilderLink.click();

        //or one can do -->
        // driver.findElement(By.xpath("//*[contains(@href,'pcpartpicker') and (text()='System Builder')]")).click();

    }
    @Then("I should appear on the system Builder page")
    public void i_should_appear_on_the_system_builder_page() {

        WebElement wait = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[(@class='pageTitle') and (text()='System Builder')]")));
    }

    @Then("the Original Compatibility banner should appear with the correct appearance")
    public void the_original_compatibility_banner_should_appear_with_the_correct_appearance() {

        String desiredColor = "#00b16a";

        WebElement banner = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='partlist__compatibility--noIssues']")));

        String s = banner.getCssValue("background-color");
        String bannerColor = Color.fromString(s).asHex();

        WebElement insideBanner = driver.findElement(By.xpath("//*[@class='partlist__compatibility partlist__compatibility--noIssues']"));
        String s2 = insideBanner.getCssValue("background-color");
        String insideBannerColor = Color.fromString(s2).asHex();

        WebElement successIcon = driver.findElement(By.xpath("//*[@class='icon shape-success']"));

        assertEquals(desiredColor, bannerColor);
        assertEquals(desiredColor, insideBannerColor);
        assertNotNull(successIcon);

    }
    @Then("the list of components buttons should be available to click")
    public void the_list_of_components_buttons_should_be_available_to_click() {

        List <WebElement> listofItems = driver.findElements(By.xpath("//*[@class='button  button--icon button--small']"));
    }

    @Then("I should be able to navigate to each link")
    public void iShouldBeAbleToNavigateToEachLink() {

        List <WebElement> listofItems = driver.findElements(By.xpath("//*[@class='button  button--icon button--small']"));
        WebDriverWait wait = new WebDriverWait(driver, 20); //Wait time of 20 seconds
        for (int i=1; i<=listofItems.size(); i++)
        {
        /*Getting the list of items again so that when the page is
        navigated back to, then the list of items will be refreshed
        again */
            listofItems = driver.findElements(By.xpath("//*[@class='button  button--icon button--small']"));

            //Waiting for the element to be visible
            //Used (i-1) because the list's item start with 0th index, like in an array
            wait.until(ExpectedConditions.visibilityOf(listofItems.get(i-1)));

            //Clicking on the first element
            String temp =listofItems.get(i-1).getAttribute("href");

            System.out.println(temp);

        }

    }

    @Then("I should close the browser")
    public void iShouldCloseTheBrowser() {

        driver.quit();
    }

}

