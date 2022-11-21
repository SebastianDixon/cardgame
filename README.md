# Card Game
Multi-threaded card game for ECM2414 module coursework

## To setup the test suite:

1.  First, download the latest version of JUnit, referred to below as junit.zip.

2.  Then install JUnit on your platform of choice:

    To install JUnit on Windows, follow these steps:
    Unzip the junit.zip distribution file to a directory referred to as 
        
        %JUNIT_HOME%.
        
    Add JUnit to the classpath:
    
        set CLASSPATH=%CLASSPATH%;%JUNIT_HOME%\junit.jar

    Unix (bash)
    To install JUnit on Unix, follow these steps:
    Unzip the junit.zip distribution file to a directory referred to as 
    
        $JUNIT_HOME.
    
    Add JUnit to the classpath:
    
        export CLASSPATH=$CLASSPATH:$JUNIT_HOME/junit.jar

3.  (Optional) Unzip the $JUNIT_HOME/src.jar file.

4.  Test the installation by running the sample tests distributed with JUnit. Note that the sample tests are located in the installation directory directly, not the         junit.jar file. Therefore, make sure that the JUnit installation directory is on your CLASSPATH. Then simply type:

    java org.junit.runner.JUnitCore org.junit.tests.AllTests
    All the tests should pass with an "OK" message.

    If the tests don't pass, verify that junit.jar is in the CLASSPATH.


## To run the tests:

To run the test from the console, type:

    java org.junit.runner.JUnitCore junitfaq.SimpleTest
    
 To run the test with the test runner used in main(), type:

    java junitfaq.SimpleTest
