# Cucumber-starter

An empty intellij IDEA project configured for entering Cucumber tests 
using Java 11 and Gradle 

Put your xxx.feature files under src\test\resources\features

Put your Cucumber testcode under src\test\java
( If your step definitions (tests) are under another package, specify 
it in the glue attribute of be.kdg.cucumber.RunCucumberTest)

Open the Gradle window using the right border icon
In this window click Tasks > Verification > Test to run your tests 
(both JUnit and Cucumber tests will be executed)
