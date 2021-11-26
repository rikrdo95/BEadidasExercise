@Regression
Feature: Petstore - back-end automated testing 
  
    Scenario Outline: Petstore test  
        Given I retrieve all the "<status1>" pets  
        And I post a new "<status1>" pet  
        Then I update the pet's status to "<status2>"  
        And I delete the pet previously created 
        Examples:  
        | status1    | status2 | 
        | available  | sold    |