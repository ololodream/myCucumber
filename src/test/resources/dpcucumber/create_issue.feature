Feature: Create issue
 Create issue

	Scenario: Get all bookings
		Given a user want to get all bookings
		When a request is sent
		Then all bookings should be returned

	Scenario: Create new a booking
		Given a booking record
		When request to create a new booking
		Then a new booking should be created
	Scenario: Delete a booking by id
		Given a booing id
		When request to delete this record
		Then this booking record should be deleted