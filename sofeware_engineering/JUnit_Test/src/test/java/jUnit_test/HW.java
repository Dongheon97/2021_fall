package jUnit_test;

import static org.junit.Assert.*;

import org.junit.Test;

public class HW {

	// Practice 1
	@Test
	public void test01() {
		boolean result;
		int givenNumber = 41;
		Practice practice = new Practice();
		
		result = practice.isPrimeNumber(givenNumber);
		
		assertEquals(true, result);
	}
	
	@Test
	public void test02() {
		boolean result;
		int givenNumber = 10;
		Practice practice = new Practice();
		
		result = practice.isPrimeNumber(givenNumber);
		
		assertFalse(result);
	}
	
	// Practice 2
	@Test
	public void test03() {
		int result;
		Practice practice = new Practice();
		result = practice.sum(10);
				
		assertEquals(55, result);
	}
		
	@Test
	public void test04() {
		int result;
		Practice practice = new Practice();
		result = practice.sum(-10);
				
		assertEquals(-55, result);
	}
}
