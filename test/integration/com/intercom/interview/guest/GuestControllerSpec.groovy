package com.intercom.interview.guest;

import static org.junit.Assert.*;
import grails.test.mixin.TestFor;

import org.junit.Test;

import spock.lang.Specification;

@TestFor(GuestController)
class GuestControllerSpec extends Specification {

    void "test controllers returns a non empty list of guests in the response"() {
        when:
        def model = controller.list()

        then:
        model.guests != null
		!model.guests.isEmpty()
		model.guests[0] instanceof Guest
		model.guests[0].with {
			name
			userId
			coordinate != null
			coordinate.latitude > 0
		}
    }

}
