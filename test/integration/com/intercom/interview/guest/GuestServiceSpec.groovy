package com.intercom.interview.guest;

import static org.junit.Assert.*;
import grails.test.mixin.TestFor;

import org.junit.Test;

import spock.lang.Specification;

@TestFor(GuestService)
class GuestServiceSpec extends Specification {
	
	Coordinate defaultStartinCoordinate
	
	def setup() {
		defaultStartinCoordinate = new Coordinate(latitude: 1, longitude: 1)
	}

	void "test find all guests if they are all within distance"() {
		setup: "Define a list of ordered guests"
			service.metaClass.findAllCandidateGuests = {[
			    new Guest(userId: 1, name: "Frank White", location: new Coordinate(longitude: 1.1, latitude: 1.1)),
			    new Guest(userId: 2, name: "Dan brown", location: new Coordinate(longitude: 1.2, latitude: 1.2)),
			    new Guest(userId: 3, name: "Paul Green", location: new Coordinate(longitude: 1.3, latitude: 1.3))
		    ]}
		when: "I filter guests based on their distance from Dublin HQ"
			List<Guest> guests = service.findGuestsWithinDistance(defaultStartinCoordinate, 100) 
		then: "I can find all of the 3 guests"
			guests.size() == 3
	}
	
	void "test discards guests that are too distant from given point"() {
		setup: "Defines a list of guests who gravitate around 2 reference points"
			service.metaClass.findAllCandidateGuests = {[
				new Guest(userId: 1, name: "Frank White", location: new Coordinate(longitude: 1.1, latitude: 1.1)),
				new Guest(userId: 2, name: "Dan Brown", location: new Coordinate(longitude: 51.2, latitude: 51.2)),
				new Guest(userId: 3, name: "Paul Green", location: new Coordinate(longitude: 1.3, latitude: 1.3)),
				new Guest(userId: 4, name: "Tom Red", location: new Coordinate(longitude: 51.4, latitude: 51.4))
			]}

		when: "I filter the guests for both reference points "
			List<Guest> guestsNearDefaultCoordinate = service.findGuestsWithinDistance(defaultStartinCoordinate, 100)
			List<Guest> guestsNearAlternativeCoordinate = service.findGuestsWithinDistance(new Coordinate(latitude: 51, longitude: 51), 100)

		then: "for every reference point I retrieve only the guests who are closer than 100 km"
			guestsNearDefaultCoordinate.size() == 2
			guestsNearDefaultCoordinate[0].name == "Frank White"
			guestsNearDefaultCoordinate[1].name == "Paul Green"
			
			guestsNearAlternativeCoordinate.size() == 2
			guestsNearAlternativeCoordinate[0].name == "Dan Brown"
			guestsNearAlternativeCoordinate[1].name == "Tom Red"
	}
	
	void "test return guests ordered by ID (ascending)"() {
		setup: "defines a list of guests presented not in order in regards of field 'user_id'"
			service.metaClass.findAllCandidateGuests = {[
				new Guest(userId: 1, name: "Frank White", location: new Coordinate(longitude: 1.1, latitude: 1.1)),
				new Guest(userId: 4, name: "Dan brown", location: new Coordinate(longitude: 1.2, latitude: 1.2)),
				new Guest(userId: 5, name: "Paul Green", location: new Coordinate(longitude: 1.3, latitude: 1.3)),
				new Guest(userId: 3, name: "Tom Red", location: new Coordinate(longitude: 1.5, latitude: 1.5)),
				new Guest(userId: 2, name: "John Grey", location: new Coordinate(longitude: 1.5, latitude: 1.5))
			]}
		when: "I retrieve the guests"
			List<Guest> guests = service.findGuestsWithinDistance(defaultStartinCoordinate, 100)

		then: "My list is ordered by userId ASC"
			guests.size() == 5
			guests[0].userId == 1
			guests[1].userId == 2
			guests[2].userId == 3
			guests[3].userId == 4
			guests[4].userId == 5
	}

}
