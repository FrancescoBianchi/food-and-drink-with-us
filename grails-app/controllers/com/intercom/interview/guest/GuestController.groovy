package com.intercom.interview.guest

import org.codehaus.groovy.grails.commons.GrailsApplication;

class GuestController {
	
	GuestService guestService
	GrailsApplication grailsApplication
	
	
	def list() {
		int distance = grailsApplication.config.maximumAllowedDistanceToInviteGuest
		[guests: guestService.findGuestsWithinDistance(getDublinCoordinate(), distance)]
	}
	
	private Coordinate getDublinCoordinate() {
		def dublinOffice = grailsApplication.config.coordinates.dublinOffice
		
		new Coordinate(
			latitude: dublinOffice.latitude.toDouble(),
			longitude: dublinOffice.longitude.toDouble()
		)
	}
}
