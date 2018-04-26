package laszlo.karsai.billing.integration.solution.test;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

import laszlo.karsai.billing.integration.solution.IntegrationImpl;

public class SolutionTest extends IntegrationTestRunner {
	
	public static void main(String... args) {
		Integration integration = new IntegrationImpl();
		IntegrationTestRunner testRunner = new SolutionTest();
		testRunner.runTests(integration);
	}
}
