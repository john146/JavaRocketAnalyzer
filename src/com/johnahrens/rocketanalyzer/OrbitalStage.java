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
 * The OrbitalStage is a second stage that gets the payload from the suborbital stage to orbit.
 * 
 * At this point, some values should be calculated that aren't. But if you take an orbital stage as the payload of a 
 * suborbital stage, you have a two stage rocket that can get to orbit. 
 */
public class OrbitalStage extends Stage {
	
	public OrbitalStage() {
		super(0.0, 0L, 0L, 0L, 0L, 0L, 0L, 0.0, 0.0);
	}
	
	public OrbitalStage(double payload, long averageIsp, double propellantRatio, double deltaV, 
			double initialAltitude, long targetAltitude) {
		super(payload, targetAltitude, averageIsp, 0L, 0L, 0L, deltaV, initialAltitude, propellantRatio);
	}
	
	public boolean analyzeStage() {
		boolean retval = true;
		
		return retval;
	}
}
