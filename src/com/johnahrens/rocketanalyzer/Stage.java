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
 * This is the parent class for all stages. Since much of the calculations and parametric values are the same types,
 * we will construct a parent class to avoid repetition.
 */
public abstract class Stage {
	
	public final static double GRAVITY = 9.8; /// Standard earth gravity = 9.8 meters per second per second
	public final static double METERS = 1000; /// Meters per kilometer
	public final static double PERCENT = 100;
	
	private double payload; /// in kilograms
	private long targetAltitude; /// in kilometers
	private long averageIsp; /// seconds
	private long thrustDuration; /// seconds
	private long gravityLosses; /// meters/second
	private long aeroLosses; /// meters/second
	private double deltaV; // kilometers/second
	private double startingAltitude; // kilometers
	private double propellantRatio; /// fraction
	
	// Mass parameters
	private double propellantMass; /// kilograms
	private double structuralMass; /// kilograms
	private double emptyMass; // kilograms
	private double loadedMass; // kilograms
	
	public Stage(double payload, long targetAltitude, long averageIsp, long thrustDuration, long gravityLosses, 
				 long aeroLosses, double deltaV, double startingAltitude, double propellantRatio) {
		this.payload = payload;
		this.targetAltitude = targetAltitude;
		this.averageIsp = averageIsp;
		this.thrustDuration = thrustDuration;
		this.gravityLosses = gravityLosses;
		this.aeroLosses = aeroLosses;
		this.deltaV = deltaV;
		this.startingAltitude = startingAltitude;
		this.propellantRatio = propellantRatio / PERCENT;
		
		this.propellantMass = 0.0;
		this.structuralMass = 0.0;
		this.emptyMass = 0.0;
		this.loadedMass = 0.0;
	}
	
	/**
	 * @return the payload, in kilograms
	 */
	public double getPayload() {
		return payload;
	}
	
	/**
	 * @param payload the payload, in kilograms, to set
	 */
	public void setPayload(double payload) {
		this.payload = payload;
	}
	
	/**
	 * @return the targetAltitude, in kilometers
	 */
	public long getTargetAltitude() {
		return targetAltitude;
	}
	
	/**
	 * @param targetAltitude the targetAltitude, in kilometers, to set
	 */
	public void setTargetAltitude(long targetAltitude) {
		this.targetAltitude = targetAltitude;
	}
	
	/**
	 * @return the averageIsp, in seconds
	 */
	public long getAverageIsp() {
		return averageIsp;
	}
	
	/**
	 * @param averageIsp the averageIsp, in seconds, to set
	 */
	public void setAverageIsp(long averageIsp) {
		this.averageIsp = averageIsp;
	}
	
	/**
	 * @return the thrustDuration, in seconds
	 */
	public long getThrustDuration() {
		return thrustDuration;
	}
	
	/**
	 * @param thrustDuration the thrustDuration, in seconds, to set
	 */
	public void setThrustDuration(long thrustDuration) {
		this.thrustDuration = thrustDuration;
	}
	
	/**
	 * @return the gravityLosses, in meters/second
	 */
	public long getGravityLosses() {
		return gravityLosses;
	}
	
	/**
	 * @param gravityLosses the gravityLosses, in meters/second, to set
	 */
	public void setGravityLosses(long gravityLosses) {
		this.gravityLosses = gravityLosses;
	}
	
	/**
	 * @return the aeroLosses, in meters/second
	 */
	public long getAeroLosses() {
		return aeroLosses;
	}
	
	/**
	 * @param aeroLosses the aeroLosses, in meters/second, to set
	 */
	public void setAeroLosses(long aeroLosses) {
		this.aeroLosses = aeroLosses;
	}
	
	/**
	 * @return the deltaV, in meters/second
	 */
	public double getDeltaV() {
		return deltaV;
	}
	
	/**
	 * @param deltaV the deltaV, in meters/second, to set
	 */
	public void setDeltaV(double deltaV) {
		this.deltaV = deltaV;
	}
	
	/**
	 * @return the startingAltitude, in kilometers
	 */
	public double getStartingAltitude() {
		return startingAltitude;
	}
	
	/**
	 * @param startingAltitude the startingAltitude, in kilometers, to set
	 */
	public void setStartingAltitude(double startingAltitude) {
		this.startingAltitude = startingAltitude;
	}
	
	/**
	 * @return the propellantRatio, in percent
	 */
	public double getPropellantRatio() {
		return propellantRatio;
	}
	
	/**
	 * @param propellantRatio the propellantRatio, in percent, to set
	 */
	public void setPropellantRatio(double propellantRatio) {
		this.propellantRatio = propellantRatio / PERCENT;
	}
	
	/**
	 * @return the propellantMass, kilograms
	 */
	public double getPropellantMass() {
		return propellantMass;
	}
	
	/**
	 * @param propellantMass the propellantMass, in kilograms, to set
	 */
	public void setPropellantMass(double propellantMass) {
		this.propellantMass = propellantMass;
	}
	
	/**
	 * @return the structuralMass, in kilograms
	 */
	public double getStructuralMass() {
		return structuralMass;
	}
	
	/**
	 * @param structuralMass the structuralMass, in kilograms, to set
	 */
	public void setStructuralMass(double structuralMass) {
		this.structuralMass = structuralMass;
	}
	
	/**
	 * @return the emptyMass, in kilograms
	 */
	public double getEmptyMass() {
		return emptyMass;
	}
	
	/**
	 * @param emptyMass the emptyMass, in kilograms, to set
	 */
	public void setEmptyMass(double emptyMass) {
		this.emptyMass = emptyMass;
	}
	
	/**
	 * @return the loadedMass, in kilograms
	 */
	public double getLoadedMass() {
		return loadedMass;
	}

	/**
	 * @param loadedMass the loadedMass, in kilograms, to set
	 */
	public void setLoadedMass(double loadedMass) {
		this.loadedMass = loadedMass;
	}
}
