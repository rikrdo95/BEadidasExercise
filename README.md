# BEadidasExercise
Back-end automated testing repo by Ricardo Villanueva Ojosnegros for Adidas' recruiting challenge

> **Note**: The test execution fails most of the times due to the service not working fine. After the actions POST, PUT and DELETE, even waiting 5 seconds to let the service update the data, most of the times return outdated or even incorrect data when the pet it's retrieved by id to check the expected data. I consider this service is failing because we can't assert that the previous actions took place properly so the test works correctly finding out this bad behaviour and failing.

## Requirements

 - Java 11.0.13
 - Maven 3.8.4
 - Windows 10
 - Git
 
## Download
Execute this command to download the repo

    git clone https://github.com/rikrdo95/BEadidasExercise.git
    
## Execute
Run this command in project directory

    mvn clean verify

## Check results
In the folder BEadidasExercise/target/site/serenity open the file index.html after the execution finish to open the report. 
