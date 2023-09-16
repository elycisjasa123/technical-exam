Feature: Technical Exam Procedures

@sanity

	Scenario: Add to cart
		Given User launch browser
		And open "https://fragrancex.com" url
		Then fragrancex site will display
		And store all links in an object
		Then it will logs all the links
		And store perfume names under top picks for you
		Then it will log all the perfume names under top pick for you
		And select third products under top pick for you
    Then verify the product name that the same on the third product
    And select the second variant of selected product
    Then verify if the number of cart was 1
    And change the quantity to 5
    Then verify if the number of cart was 5
    Then end and close the browser