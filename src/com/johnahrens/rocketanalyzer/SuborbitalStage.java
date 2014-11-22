/**
 * 
 */
package com.johnahrens.rocketanalyzer;

/**
 * @author john
 *
 */
public class SuborbitalStage {
	
	public final static double GRAVITY = 9.8; /// Standard earth gravity = 9.8 meters per second per second
	public final static double METERS = 1000; /// Meters per kilometer
	public final static double PERCENT = 100;
	
	private double payload; /// in kilograms
	private long targetAltitude; /// in kilometers
	private long averageIsp; /// seconds
	private long thrustDuration; /// seconds
	private long gravityLosses; /// meters/second
	private long aeroLosses; /// meters/second
	private double propellantRatio; /// fraction
	
	private double propellantMass; /// kilograms
	private double structuralMass; /// kilograms
	
	public SuborbitalStage() {
		new SuborbitalStage(0L, 0L, 0L, 0L, 0L, 0L, 0L);
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
		this.payload = payload;
		this.targetAltitude = targetAltitude;
		this.averageIsp = averageIsp;
		this.thrustDuration = thrustDuration;
		this.gravityLosses = gravityLosses;
		this.aeroLosses = aeroLosses;
		this.propellantRatio = propellantRatio / PERCENT;
		
		this.propellantMass = 0;
		this.structuralMass = 0;
	}
	
	public boolean analyzeStage() {
		boolean retval = true;
		// Flight duration ground to target altitude, in seconds
		double duration = Math.sqrt(2 * targetAltitude * METERS / GRAVITY); 
		// Maximum velocity achieved to reach target altitude in meters/second
		double velocity = duration * GRAVITY; 
		// The total deltaV or velocity change, including losses in meters/second
		double deltaV = velocity + gravityLosses + aeroLosses;
		// The mass ratio 
		double massRatio = Math.pow(Math.E, deltaV / (GRAVITY * averageIsp));
		propellantMass = (payload * (1 - massRatio)) / ((massRatio - 1) * (1 / propellantRatio - 1) - 1);
		
		structuralMass = propellantMass * ((1 / propellantRatio) - 1);
		
		return retval;
	}

	/**
	 * @return the payload, in kilograms
	 */
	public double getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set, in kilograms
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
	 * @param targetAltitude the targetAltitude to set,  in kilometers
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
	 * @param averageIsp the averageIsp to set, in seconds
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
	 * @param thrustDuration the thrustDuration to set, in seconds
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
	 * @param gravityLosses the gravityLosses to set, in meters/second
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
	 * @param aeroLosses the aeroLosses to set, in meters/second
	 */
	public void setAeroLosses(long aeroLosses) {
		this.aeroLosses = aeroLosses;
	}

	/**
	 * @return the propellantRatio, in percent
	 */
	public long getPropellantRatio() {
		return (long)(propellantRatio * PERCENT);
	}

	/**
	 * @param propellantRatio the propellantRatio to set, in percent
	 */
	public void setPropellantRatio(long propellantRatio) {
		this.propellantRatio = propellantRatio / PERCENT;
	}

	/**
	 * @return the propellantMass, in kilograms
	 */
	public double getPropellantMass() {
		return propellantMass;
	}

	/**
	 * @return the structuralMass, in kilograms
	 */
	public double getStructuralMass() {
		return structuralMass;
	}
}
