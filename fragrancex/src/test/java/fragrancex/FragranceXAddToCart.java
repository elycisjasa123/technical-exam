package fragrancex;

/*
a. Go to fragrancex.com
b. store all links in an object
c. log/store all Perfume names found under “Top Picks For You” section at the bottom of the page
d. Click on the 3rd product in “Top Picks For You”
e. verify that product name selected was displayed(use c. here)
f. add the 2nd product variant to the bag(should be identified by name e.g 50 ml Eau de Toilette
Spray)
e. verify that count ‘1’ is added to the bag icon
g. update quantity to 5
h. end test
 */
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FragranceXAddToCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// WebDriverManager.chromedriver().setup();
		
		
		// Launch the browsers
		ChromeDriver driver=new ChromeDriver();
		
		// Maximize the browser window to fullscreen
        driver.manage().window().maximize();
		
		
		// a. Go to fragrancex.com
		driver.get("https://fragrancex.com");
		
		// b. store all links in an object
		// Find all the links on the page and store them in a List
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        // Create a List to store the href attribute values of the links
        List<String> linkUrls = new ArrayList<>();
        
    
        // Loop through each link and store its href attribute value
        for (WebElement link : allLinks) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                linkUrls.add(url);
            }
        }
        
        System.out.println();
        System.out.println("List of all the links in the pages");
        for(String link: linkUrls ) {
        	System.out.println(link);
        }
        
        
        List<WebElement> topPickForYou = driver.findElements(By.cssSelector("#recommended-items > div > div > div > div > div > a > div.desc-section > div.serif.h3"));
        
     // Create a List to store the href attribute values of the links
        List<String> topPickForYouItem = new ArrayList<>();
        // Loop
        for(WebElement name : topPickForYou) {
        	String itemName = name.getText();
        	if(itemName != null && !itemName.isEmpty()) {
        		topPickForYouItem.add(itemName);
        	}
        }
        
        System.out.println();
        System.out.println("List of all the items from Top Pick For You");
        for(String item : topPickForYouItem) {
        	System.out.println(item);
        }
        
        
        // Select 3rd product from top picks for you
        WebElement thirdPick = driver.findElement(By.cssSelector("#recommended-items > div > div.slider-wrapper > div > div:nth-child(3) > div > a > div.desc-section > div.serif.h3"));
        thirdPick.click();
        
        String expectedText = topPickForYouItem.get(2);
        String selectedProductName = driver.findElement(By.cssSelector("span.perfume-name")).getText();
        String[] productWords = selectedProductName.split(" ");
        String finalProductName = selectedProductName;
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
        
        
        System.out.println();
        System.out.println("The expected text: " + expectedText);
        System.out.println("The selected product name:" + finalProductName);
        Assert.assertEquals(finalProductName, expectedText, "Text does not match!");
        
       // 2nd variant Add to cart button
        WebElement addToCartButtons = driver.findElements(By.cssSelector("div.add-to-cart")).get(2);
        addToCartButtons.click();
       
        
        
        // Verify that the item is added on the bag
        WebElement cartItem = driver.findElement(By.cssSelector("div.count"));
        String numberCart = cartItem.getText();
        Assert.assertEquals(numberCart, "1", "Text does not match!");
        	
        // Change the quantity to 5
        WebElement quantitySelection = driver.findElement(By.cssSelector("select.cart-qty-select"));
        quantitySelection.click();
        
        WebElement optionFive = driver.findElement(By.cssSelector("option[value='5']"));
        optionFive.click();
        
        	try {
				Thread.sleep(3000);
				WebElement finalCartItem = driver.findElement(By.cssSelector("div.count"));
	            String finalNumberCart = finalCartItem.getText();
	            Assert.assertEquals(finalNumberCart, "5", "Text does not match!");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
        	driver.close();
        }
        
	}

}
