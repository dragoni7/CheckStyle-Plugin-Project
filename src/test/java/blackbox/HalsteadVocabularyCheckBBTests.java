package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.HalsteadVocabularyCheck;

public class HalsteadVocabularyCheckBBTests {
	
	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 5.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getVocabulary());
	}
	
	@Test
	/**
	 * Covers fault models: 5.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		Assertions.assertEquals(25, check.getVocabulary());
	}
}
