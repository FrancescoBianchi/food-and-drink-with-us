package com.intercom.interview.guest

import java.util.List;

import grails.converters.JSON

import com.intercom.interview.util.MathUtils;

class GuestService {

	def grailsResourceLocator
	
	def List<Guest> findGuestsWithinDistance(Coordinate referencePoint, int maximumAllowedDistance) {
		findAllCandidateGuests().findAll {
           MathUtils.computeDistance(it.location, referencePoint) <= maximumAllowedDistance
       }.sort {it.userId}
	}
	
	private List<Guest> findAllCandidateGuests() {
		List dataEntries = loadFromFile()
		dataEntries.collect {
			new Guest(
				userId: it.user_id,
				name: it.name,
				location: new Coordinate (
					latitude: it.latitude.toDouble(),
					longitude: it.longitude.toDouble()
				)
			)
		}
	}
	
	private List loadFromFile() {
		def guestsFile = grailsResourceLocator.findResourceForURI('classpath:/data/guests.json').file
		List guests = []
		guestsFile.eachLine {
			try {
				guests << JSON.parse(it)
			} catch (Exception exception) {
				log.warn "A file entry was not unmarshalled because it didn't contain a properly formatted JSON [${it}]"
			}
		}
		guests
	}

}
