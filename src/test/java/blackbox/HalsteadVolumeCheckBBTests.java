package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.HalsteadVolumeCheck;

public class HalsteadVolumeCheckBBTests {

	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 6.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getVolume());
	}
	
	@Test
	/**
	 * Covers fault models: 6.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		HalsteadVolumeCheck check = new HalsteadVolumeCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		Assertions.assertEquals(431.88, check.getVolume());
	}
}
