package com.intercom.interview.guest

import java.util.List;

import grails.converters.JSON

import com.intercom.interview.util.Coordinate;
import com.intercom.interview.util.MathUtils;

class GuestService {

	def grailsResourceLocator
	
	def List<Guest> findGuestsWithinDistance(Coordinate referencePoint) {
		findAllPossibleGuests().each {
			it.distance = MathUtils.computeDistance(it.coordinate, referencePoint)
		}.findAll {
           it.distance <= 100
       }.sort {it.userId}
	}
	
	private List<Guest> findAllPossibleGuests() {
		List dataEntries = loadFromFile()
		dataEntries.collect {
			new Guest(
				userId: it.user_id,
				name: it.name,
				coordinate: new Coordinate (
					latitude: it.latitude.toDouble(),
					longitude: it.longitude.toDouble()
				)
			)
		}
	}
	
	private List loadFromFile() {
		def fileIn = grailsResourceLocator.findResourceForURI('classpath:data/guests.json').file
		List guests = []
		fileIn.eachLine {
			guests.add JSON.parse(it)
		}
		guests
		
		
//		[
//			new Guest(latitude: 52.986375, userId: 12, name: "Christina McArdle", longitude: -6.043701),
//			
//			
//			new Guest(latitude: 51.92893, userId: 1, name: "Alice Cahill", longitude: -10.27699),
//			new Guest(latitude: 51.8856167, userId: 2, name: "Ian McArdle", longitude: -10.4240951),
//			new Guest(latitude: 52.3191841, userId: 3, name: "Jack Enright", longitude: -8.5072391),
//			new Guest(latitude: 53.807778, userId: 28, name: "Charlie Halligan", longitude: -7.714444),
//			new Guest(latitude: 53.4692815, userId: 7, name: "Frank Kehoe", longitude: -9.436036),
//			new Guest(latitude: 54.0894797, userId: 8, name: "Eoin Ahearn", longitude: -6.18671),
//			new Guest(latitude: 53.038056, userId: 26, name:  "Stephen McArdle", longitude: -7.653889),
//			new Guest(latitude: 54.1225, userId: 27, name: "Enid Gallagher", longitude: -8.143333),
//			new Guest(latitude: 53.1229599, userId: 6, name: "Theresa Enright", longitude: -6.2705202),
//			new Guest(latitude: 52.2559432, userId: 9, name: "Jack Dempsey", longitude: -7.1048927),
//			new Guest(latitude: 52.240382, userId: 10, name: "Georgina Gallagher", longitude: -6.972413),
//			new Guest(latitude: 53.2451022, userId: 4, name: "Ian Kehoe", longitude: -6.238335),
//			new Guest(latitude: 53.1302756, userId: 5, name: "Nora Dempsey", longitude: -6.2397222)
////			,
////			{"latitude": "53.008769", "user_id": 11, "name": "Richard Finnegan", "longitude": "-6.1056711"}
////			{"latitude": "53.1489345", "user_id": 31, "name": "Alan Behan", "longitude": "-6.8422408"}
////			{"latitude": "53", "user_id": 13, "name": "Olive Ahearn", "longitude": "-7"}
////			{"latitude": "51.999447", "user_id": 14, "name": "Helen Cahill", "longitude": "-9.742744"}
////			{"latitude": "52.966", "user_id": 15, "name": "Michael Ahearn", "longitude": "-6.463"}
////			{"latitude": "52.366037", "user_id": 16, "name": "Ian Larkin", "longitude": "-8.179118"}
////			{"latitude": "54.180238", "user_id": 17, "name": "Patricia Cahill", "longitude": "-5.920898"}
////			{"latitude": "53.0033946", "user_id": 39, "name": "Lisa Ahearn", "longitude": "-6.3877505"}
////			{"latitude": "52.228056", "user_id": 18, "name": "Bob Larkin", "longitude": "-7.915833"}
////			{"latitude": "54.133333", "user_id": 24, "name": "Rose Enright", "longitude": "-6.433333"}
////			{"latitude": "55.033", "user_id": 19, "name": "Enid Cahill", "longitude": "-8.112"}
////			{"latitude": "53.521111", "user_id": 20, "name": "Enid Enright", "longitude": "-9.831111"}
////			{"latitude": "51.802", "user_id": 21, "name": "David Ahearn", "longitude": "-9.442"}
////			{"latitude": "54.374208", "user_id": 22, "name": "Charlie McArdle", "longitude": "-8.371639"}
////			{"latitude": "53.74452", "user_id": 29, "name": "Oliver Ahearn", "longitude": "-7.11167"}
////			{"latitude": "53.761389", "user_id": 30, "name": "Nick Enright", "longitude": "-7.2875"}
////			{"latitude": "54.080556", "user_id": 23, "name": "Eoin Gallagher", "longitude": "-6.361944"}
////			{"latitude": "52.833502", "user_id": 25, "name": "David Behan", "longitude": "-8.522366"}
//		]
	}

}
