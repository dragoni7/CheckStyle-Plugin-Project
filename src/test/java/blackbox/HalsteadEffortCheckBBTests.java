package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.HalsteadEffortCheck;

public class HalsteadEffortCheckBBTests {
	
	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 3.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getEffort());
	}
	
	@Test
	/**
	 * Covers fault models: 3.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		Assertions.assertEquals(10719.2616, check.getEffort());
	}
}
