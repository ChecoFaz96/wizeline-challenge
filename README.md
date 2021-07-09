# Automation Challenge Wizeline 2021
This repository contains a series of automated scenarios for the sample page https://www.saucedemo.com/

## Getting started
1. Clone this repository or download the zip with the source code in to your local system. 
```
git clone https://github.com/ChecoFaz96/wizeline-challenge.git
```
2. Ensure Java is installed in your system and the path is set into your System Variables.
3. Ensure [maven](https://maven.apache.org/download.cgi) is installed in your system and the path is set into your System Variables.
4. Identify the version of your chrome browser and download [chrome webdriver](https://sites.google.com/a/chromium.org/chromedriver), unzip the file if that is the case, move it to the drivers folder of this repository.


## Prepare your development environment

1. In command shell change to the directory where the code of this repository live. Notice in my case I'm in a Windows environment.
```
C:\> cd C:\_\wizeline-challenge\wizeline_challenge
```

## Running the tests
1. You can run the tests suite from a command shell by calling the following command.
```
mvn test
```
## Test Results
To see the test cases report results open the index.html file located on the reports folder of the repository
