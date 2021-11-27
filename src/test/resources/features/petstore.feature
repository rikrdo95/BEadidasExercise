@Regression @PetStore
Feature: Petstore - back-end automated testing

    Background:
        Given I want to test "PetStore" API

    Scenario Outline: Petstore test
        Given I retrieve all the pets with status "<status1>"
        And I post a new "<status1>" pet
        Then I update the status of the pet "<petId>" to "<status2>"
        And I delete the pet "<petId>"
        Examples:
            | status1   | status2 | petId              |
            | available | sold    | previously created |