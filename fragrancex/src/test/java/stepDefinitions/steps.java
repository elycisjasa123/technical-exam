package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;

public class steps {
	
	ChromeDriver driver;
    // Create a List to store the href attribute values of the links
    List<String> linkUrls = new ArrayList();
    // Create a List to store the href attribute values of the links
    List<String> topPickForYouItem = new ArrayList();
    String expectedText; // variable container for the third item
    String selectedProductName; // variable container for the selected product
    String finalProductName; // product without cologne or perfume label
	
	@Before
	@Given("User launch browser")
    public void setup() {    //Junit hook - executes once before starting
		
    }
	
	@And("open {string} url")
	public void open_url(String url) {
		driver = new ChromeDriver();
		// Maximize the browser window to fullscreen
        driver.manage().window().maximize();
		driver.get(url);
	}
	
	@Then("fragrancex site will display")
	public void get_url() {
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
	}
	
	
	@And("store all links in an object")
	public void store_links() {
		// b. store all links in an object
				// Find all the links on the page and store them in a List
		        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		        
		    
		        // Loop through each link and store its href attribute value
		        for (WebElement link : allLinks) {
		            String url = link.getAttribute("href");
		            if (url != null && !url.isEmpty()) {
		                linkUrls.add(url);
		            }
		        }
	}
	
	
	@Then("it will logs all the links")
	public void log_link() {
		System.out.println("List of all the links in the pages");
        for(String link: linkUrls ) {
        	System.out.println(link);
        }
	}
	
	@And("store perfume names under top picks for you")
	public void store_perfume_names_top_pick_for_you() {
		List<WebElement> topPickForYou = driver.findElements(By.cssSelector("#recommended-items > div > div > div > div > div > a > div.desc-section > div.serif.h3"));
		
		// Loop
        for(WebElement name : topPickForYou) {
        	String itemName = name.getText();
        	if(itemName != null && !itemName.isEmpty()) {
        		topPickForYouItem.add(itemName);
        	}
        }
	}
	
	@Then("it will log all the perfume names under top pick for you")
	public void log_top_pick_items() {
		 System.out.println("List of all the items from Top Pick For You");
	        for(String item : topPickForYouItem) {
	        	System.out.println(item);
	        }
	}
	
	@And("select third products under top pick for you")
	public void select_product() {
		 // Select 3rd product from top picks for you
        WebElement thirdPick = driver.findElement(By.cssSelector("#recommended-items > div > div.slider-wrapper > div > div:nth-child(3) > div > a > div.desc-section > div.serif.h3"));
        thirdPick.click();
        
       expectedText = topPickForYouItem.get(2); // Store the product name of 3rd product 
       selectedProductName = driver.findElement(By.cssSelector("span.perfume-name")).getText(); // get the text of the selected product name
        
	}
	
	@Then("verify the product name that the same on the third product")
	public void verify_product_name() {
		expectedText = topPickForYouItem.get(2); // Pull the 3rd element item from top picks for you
		String[] productWords = selectedProductName.split(" "); // Split the selected product name
		finalProductName=selectedProductName;
		
		// Remove the Cologne or Perfume post words in the page
	     if(productWords.length >= 2) {
	        	
	        	// Create a StringBuilder to build the result
	            StringBuilder result = new StringBuilder();
	
	            // Append all words except the last one
	            for (int i = 0; i < productWords.length - 1; i++) {
	                result.append(productWords[i]).append(" ");
	            }
	         // Remove the trailing space and update the web element's text
	            finalProductName = result.toString().trim();
		}
	     Assert.assertEquals(expectedText, finalProductName);
	}
	
	@And("select the second variant of selected product")
	public void select_variant() {
		// 2nd variant Add to cart button
        WebElement addToCartButtons = driver.findElements(By.cssSelector("div.add-to-cart")).get(2); // 2nd Add to cart button 
        addToCartButtons.click();
	}
	
	@Then("verify if the number of cart was {int}")  // Verify that the item is added on the bag and count is equal to 1
	public void verifyCart(int total_cart) {
        WebElement cartItems = driver.findElement(By.cssSelector("div.count"));
        String numberCart = cartItems.getText();
        Assert.assertEquals(Integer.toString(total_cart), numberCart);
	}
	
	@And("change the quantity to {int}") // Change the quantity to 5
	public void change_product_quantity(int expected_value) throws InterruptedException {
        WebElement quantitySelection = driver.findElement(By.cssSelector("select.cart-qty-select"));
        quantitySelection.click();
        WebElement selectedOption = driver.findElement(By.cssSelector("option[value='"+ Integer.toString(expected_value) +"']"));
        selectedOption.click();
        execute_static_wait(5000);
	}
	
	// This will create a static wait function
	public void execute_static_wait(int milliseconds) throws InterruptedException {
        // Static wait for 5 seconds
        Thread.sleep(milliseconds);
    }
	
	
	
	
	@Then("end and close the browser")
	public void browser_exit() {
		driver.close();
	}
	

}
