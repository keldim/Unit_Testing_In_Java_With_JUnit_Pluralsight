package com.chrisyoo.protein_tracker_tests;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.chrisyoo.protein_tracker.InvalidGoalException;
import com.chrisyoo.protein_tracker.TrackingService;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;


public class TrackingServiceTests {

	private TrackingService service;

	@BeforeClass
	public static void before() {
		System.out.println("Before Class");
	} 
	
	@AfterClass
	public static void after() {
		System.out.println("After Class");
	}
	
	
	@Before
	public void setUp() {
		System.out.println("Before");
		service = new TrackingService();
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	@Test
	@Category({GoodTestsCategory.class, BadCategory.class})
	public void newTrackingServiceTotalIsZero() {
		assertEquals("Tracking service total is not zero", 0, service.getTotal());	
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void whenAddingProteinTotalIncreasesByThatAmount() {
		service.addProtein(10);
		assertThat(service.getTotal(), allOf(is(10), instanceOf(Integer.class)));
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void whenRemovingProteinTotalRemainsZero() {
		service.removeProtein(5);
		assertEquals(0, service.getTotal());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void whenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException {
		thrown.expect(InvalidGoalException.class);
		thrown.expectMessage(containsString("Goal"));
		service.setGoal(-5);
	}
	
	@Rule
	public Timeout timeout = new Timeout(20);
	
	@Test
	public void badTest() {
		for(int i = 0; i < 10000000; i++) service.addProtein(1);
	}
	
	
}
