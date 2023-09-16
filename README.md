# technical-exam
This is to test the fragrancex page to add an item to a cart

# Technical Exam for Automation Tester

### Create a selenium test, that satisfies the following requirements:
- a. Go to fragrancex.com
- b. store all links in an object
- c. log/store all Perfume names found under “Top Picks For You” section at the bottom of the page
- d. Click on the 3rd product in “Top Picks For You”
- e. verify that product name selected was displayed(use c. here)
- f. add the 2nd product variant to the bag(should be identified by name e.g 50 ml Eau de Toilette
Spray)
- g. verify that count ‘1’ is added to the bag icon
- h. update quantity to 5
- i. end test


*Create a working test project and upload it to github. Please make sure you are using standard and
basic practices(use of framework) in Automation. We will discuss your answers and the tests you
completed during the interview. Please prepare to share your screen.
This aims to answer the same questions we have during the interview (framework/s used, standard
and best practices, version control, knowledge in handling exceptions, locators, hovers/mouse
actions)*



### Requirements
- [Java SDK 11](https://www.oracle.com/java/technologies/downloads/#java11)
- [Maven](https://maven.apache.org/download.cgi) 
- [Java Eclipse IDE](https://www.eclipse.org/downloads/packages/installer)
- Cucumber from Java Eclipse Marketplace


### How to execute the test
- Make sure you checkout on the master branch using `git checkout master`
- Execute on terminal the `git pull origin master` to check if the changes is up to date
- Open your Java Eclipse IDE
- Make sure you update and install dependencies of the maven projects in pom.xml
- Go to Features folder
- Open the AddToCart.feature cucumber file
- Right click on the file and Run as Cucumber file