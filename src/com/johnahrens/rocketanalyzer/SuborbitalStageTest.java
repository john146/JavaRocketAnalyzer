/**
 * 
 */
package com.johnahrens.rocketanalyzer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author john
 *
 */
public class SuborbitalStageTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test stage analysis assuming starting at sea-level, 0 velocity and attempting to lift 10 kilograms to
	 * 100 kilometers. Our rocket engine ISP is 245 seconds, will burn for 40 seconds, losing 393 m/s to gravity and
	 * 640 m/s to aerodynamic drag. Propellent ratio is 80%.
	 * 
	 * Expect a result of Mass lofted of 10 kilograms, Mass of propellant of 31.2 kilograms and a structural mass 
	 * of 7.8 kilograms.
	 */
	@Test
	public void testAnalyzeStageTo100Km() {
		SuborbitalStage ss = new SuborbitalStage(10D, 100L, 245L, 40L, 393L, 640L, 80L);
		assertNotNull("No Suborbital Stage constructed.", ss);
		assertTrue("Failed to perform analysis successfully.", ss.analyzeStage());
		assertEquals("Incorrect payload mass.", 10, ss.getPayload(), 0.1);
		assertEquals("Incorrect propellant mass.", 31.2, ss.getPropellantMass(), 0.1);
		assertEquals("Incorrect structural mass.", 7.8, ss.getStructuralMass(), 0.1);
	}

	/**
	 * Test stage analysis assuming starting at sea-level, 0 velocity and attempting to lift 10 kilograms to
	 * 65 kilometers. Our rocket engine ISP is 245 seconds, will burn for 40 seconds, losing 393 m/s to gravity and
	 * 640 m/s to aerodynamic drag. Propellent ratio is 80%.
	 * 
	 * Expect a result of Mass lofted of 10 kilograms, Mass of propellant of 23.0 kilograms and a structural mass 
	 * of 5.75 kilograms.
	 */
	@Test
	public void testAnalyzeStageTo65Km() {
		SuborbitalStage ss = new SuborbitalStage(10D, 65L, 245L, 40L, 393L, 640L, 80L);
		assertNotNull("No Suborbital Stage constructed", ss);
		assertTrue("Failed to perform analysis successfully.", ss.analyzeStage());
		assertEquals("Incorrect payload mass.", 10, ss.getPayload(), 0.001);
		assertEquals("Incorrect propellant mass.", 23.0030, ss.getPropellantMass(), 0.001);
		assertEquals("Incorrect structural mass.", 5.7508, ss.getStructuralMass(), 0.001);
	}
}
