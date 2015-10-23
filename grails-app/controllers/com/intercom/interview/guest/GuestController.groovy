package com.intercom.interview.guest

import org.codehaus.groovy.grails.commons.GrailsApplication;

import com.intercom.interview.util.Coordinate;

class GuestController {
	
	GuestService guestService
	GrailsApplication grailsApplication
	
	
	def list() {
		def dublinOffice = grailsApplication.config.coordinates.dublinOffice
		
		
		[guests: guestService.findGuestsWithinDistance(getDublinCoordinate())]
	}
	
	private Coordinate getDublinCoordinate() {
		def dublinOffice = grailsApplication.config.coordinates.dublinOffice
		
		new Coordinate(
			latitude: dublinOffice.latitude.toDouble(),
			longitude: dublinOffice.longitude.toDouble()
		)
	}
}
