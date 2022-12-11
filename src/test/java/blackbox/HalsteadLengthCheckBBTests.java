package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.HalsteadLengthCheck;

public class HalsteadLengthCheckBBTests {
	
	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 4.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getLength());
	}
	
	@Test
	/**
	 * Covers fault models: 4.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		HalsteadLengthCheck check = new HalsteadLengthCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		Assertions.assertEquals(93, check.getLength());
	}
}
