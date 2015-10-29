package com.intercom.interview.guest;

import static org.junit.Assert.*;
import grails.test.mixin.TestFor;

import org.junit.Test;

import spock.lang.Specification;

@TestFor(GuestController)
class GuestControllerSpec extends Specification {

    void "test controllers returns a non empty list of guests in the response"() {
        when: "when I ask for a list of guests who match my criterias"
        	def model = controller.list()

        then: "I get the list of guests"
	        model.guests != null
			!model.guests.isEmpty()
			model.guests[0] instanceof Guest
			model.guests[0].with {
				name
				userId
				location != null
				location.latitude > 0
			}
    }

}
