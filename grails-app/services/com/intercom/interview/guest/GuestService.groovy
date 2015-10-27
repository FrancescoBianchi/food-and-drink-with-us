package com.intercom.interview.guest

import java.util.List;

import grails.converters.JSON

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
		def fileIn = grailsResourceLocator.findResourceForURI('classpath:/data/guests.json').file
		List guests = []
		fileIn.eachLine {
			guests.add JSON.parse(it)
		}
		guests
	}

}
