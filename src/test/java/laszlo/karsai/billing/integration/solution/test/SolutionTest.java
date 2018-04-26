package laszlo.karsai.billing.integration.solution.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

import laszlo.karsai.billing.integration.solution.IntegrationImpl;

public class SolutionTest {
	
	@Test
	public void testSolution() {
		boolean isTestSuccess = false;
		try {
			Integration integration = new IntegrationImpl();
			IntegrationTestRunner testRunner = new IntegrationTestRunner();
			testRunner.runTests(integration);
			isTestSuccess = true;
		} finally {
			assertTrue(isTestSuccess);
		}
	}
}
