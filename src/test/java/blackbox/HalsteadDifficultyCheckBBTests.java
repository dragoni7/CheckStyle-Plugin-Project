package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.HalsteadDifficultyCheck;

public class HalsteadDifficultyCheckBBTests {

	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 2.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getDifficulty());
	}
	
	@Test
	/**
	 * Covers fault models: 2.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		HalsteadDifficultyCheck check = new HalsteadDifficultyCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		Assertions.assertEquals(24.82, check.getDifficulty());
	}
}
