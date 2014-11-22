/**
 * Copyright (c) 2014 John Ahrens
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.johnahrens.rocketanalyzer;

/**
 * @author john
 *
 * The Suborbital Stage makes no attempt to reach orbit. It can reach whatever altitude desired, but there is no attempt
 * at this time to add a downrange component to velocity. At this time, we assume the stage starts at zero velocity at 
 * sea-level.
 */
public class SuborbitalStage extends Stage {
	
	public SuborbitalStage() {
		super(0.0, 0L, 0L, 0L, 0L, 0L, 0L, 0.0, 0.0);
	}

	/**
	 * Constructor
	 * 
	 * @param payload
	 * @param targetAltitude
	 * @param averageIsp
	 * @param thrustDuration
	 * @param gravityLosses
	 * @param aeroLosses
	 * @param propellantRatio
	 */
	public SuborbitalStage(double payload, long targetAltitude, long averageIsp, long thrustDuration, long gravityLosses, 
			long aeroLosses, long propellantRatio) {
		super(payload, targetAltitude, averageIsp, thrustDuration, aeroLosses, gravityLosses, 
				0.0, 0.0, propellantRatio);
	}
	
	public boolean analyzeStage() {
		boolean retval = true;
		// Flight duration ground to target altitude, in seconds
		double duration = Math.sqrt(2 * getTargetAltitude() * METERS / GRAVITY); 
		// Maximum velocity achieved to reach target altitude in meters/second
		double velocity = duration * GRAVITY; 
		// The total deltaV or velocity change, including losses in meters/second
		setDeltaV(velocity + getGravityLosses() + getAeroLosses());
		// The mass ratio 
		double massRatio = Math.pow(Math.E, getDeltaV() / (GRAVITY * getAverageIsp()));
		double pr = getPropellantRatio();
		if (0.0 == Double.compare(Double.NaN, pr)) {
			retval = false;
		} else {
			setPropellantMass((getPayload() * (1 - massRatio)) / ((massRatio - 1) * (1 / pr - 1) - 1));
			setStructuralMass(getPropellantMass() * ((1 / pr) - 1));
		}
		
		return retval;
	}
}
