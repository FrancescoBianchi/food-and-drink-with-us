package com.intercom.interview.util

import static java.lang.Math.*

import com.intercom.interview.guest.Coordinate;
import com.intercom.interview.guest.Guest


class MathUtils {

	static final int EARTH_RADIUS_IN_KM = 6371
	
	public static double computeDistance(Coordinate guestCoordinate, Coordinate referenceCoordinate) {
		EARTH_RADIUS_IN_KM * computeCentralAngle(guestCoordinate, referenceCoordinate)
	}
	
	private static double degreeToRadians(double degrees) {
		degrees * PI / 180
	}

	private static double computeCentralAngle(Coordinate guestCoordinate, Coordinate referenceCoordinate) {
		double gLat = degreeToRadians(guestCoordinate.latitude)
		double refLat = degreeToRadians(referenceCoordinate.latitude)
		double deltaLong = degreeToRadians(guestCoordinate.longitude - referenceCoordinate.longitude)
		
		acos( sin(gLat)*sin(refLat) + cos(gLat)*cos(refLat) * cos(deltaLong) )
	}
	
}
