Feature: Create issue
 Create issue
    
	Scenario: Create a issue
	  Given a user want to create a issue
	  When a request is sent 
	  Then a issue is created in jira
