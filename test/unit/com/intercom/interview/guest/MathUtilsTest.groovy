
package com.intercom.interview.guest;

import com.intercom.interview.util.MathUtils;

class MathUtilsTest {

	@Test
	public void returnZeroForDistanceFromSelf() {
		Coordinate positiveCoordinate = new Coordinate(latitude: 15, longitude: 15)
		assert MathUtils.computeDistance(positiveCoordinate, positiveCoordinate) == 0
		
		Coordinate zeroCoordinate = new Coordinate(latitude: 0, longitude: 0)
		assert MathUtils.computeDistance(zeroCoordinate, zeroCoordinate) == 0
		
		Coordinate floatCoordinate = new Coordinate(latitude: 12.34, longitude: 45.68)
		assert MathUtils.computeDistance(floatCoordinate, floatCoordinate) == 0
		
		Coordinate negativeCoordinate = new Coordinate(latitude: -20, longitude: -1)
		assert MathUtils.computeDistance(negativeCoordinate, negativeCoordinate) == 0;
	}
	
	@Test
	public void computeCorrectDistance() {
		Coordinate positiveCoordinate = new Coordinate(latitude: 15, longitude: 15)
		Coordinate zeroCoordinate = new Coordinate(latitude: 0, longitude: 0)
		Coordinate negativeCoordinate = new Coordinate(latitude: -15, longitude: -15)
		
		assert MathUtils.computeDistance(positiveCoordinate, zeroCoordinate) > 100
		assert MathUtils.computeDistance(zeroCoordinate, positiveCoordinate) == MathUtils.computeDistance(zeroCoordinate, positiveCoordinate)
		
		assert MathUtils.computeDistance(zeroCoordinate, negativeCoordinate) > 0
		assert MathUtils.computeDistance(negativeCoordinate, zeroCoordinate) > 0
		assert MathUtils.computeDistance(zeroCoordinate, negativeCoordinate) > 1000
		
		assert MathUtils.computeDistance(positiveCoordinate, zeroCoordinate) == MathUtils.computeDistance(negativeCoordinate, zeroCoordinate)
		
		assert MathUtils.computeDistance(positiveCoordinate, negativeCoordinate) > MathUtils.computeDistance(positiveCoordinate, zeroCoordinate)
	}

}
