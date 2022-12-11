package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.BaseHalsteadCheck;

public class BaseHalsteadCheckBBTests {
	
	private static final String checkType = "basehalstead";
	
	@Test
	/**
	 * Covers fault models: 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.9
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleOperatorOperandTest() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "HalsteadMetricTestCase"), check);
		
		Assertions.assertEquals(54, check.getOperatorCount());
		Assertions.assertEquals(14, check.getDistinctOperatorCount());
		Assertions.assertEquals(39, check.getOperandCount());
		Assertions.assertEquals(11, check.getDistinctOperandCount());
	}
	
	@Test
	/**
	 * Covers fault models: 1.1, 1.6
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void arrayCountTest() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "ArrayCount"), check);
		
		Assertions.assertEquals(15, check.getOperatorCount());
		Assertions.assertEquals(7, check.getDistinctOperatorCount());
		Assertions.assertEquals(10, check.getOperandCount());
		Assertions.assertEquals(8, check.getDistinctOperandCount());
	}
	
	@Test
	/**
	 * Covers fault models: 1.3, 1.7
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void ignoreCommentsTest() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "IgnoreComments"), check);
		
		Assertions.assertEquals(1, check.getOperatorCount());
		Assertions.assertEquals(1, check.getDistinctOperatorCount());
		Assertions.assertEquals(1, check.getOperandCount());
		Assertions.assertEquals(1, check.getDistinctOperandCount());
	}
	
	@Test
	/**
	 * Covers fault models: 1.10
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void operatorOperandInStringTest() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "OperatorOperandInString"), check);
		
		Assertions.assertEquals(4, check.getOperatorCount());
		Assertions.assertEquals(4, check.getDistinctOperatorCount());
		Assertions.assertEquals(3, check.getOperandCount());
		Assertions.assertEquals(3, check.getDistinctOperandCount());
	}
	
	@Test
	/**
	 * Covers fault models: 1.8
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void allUniqueOperatorOperandTest() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "AllUniqueOperatorOperand"), check);
		
		Assertions.assertEquals(6, check.getOperatorCount());
		Assertions.assertEquals(6, check.getDistinctOperatorCount());
		Assertions.assertEquals(4, check.getOperandCount());
		Assertions.assertEquals(4, check.getDistinctOperandCount());
	}
	
	@Test
	/**
	 * Covers fault models: 1.8
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noOperatorsOperands() throws IOException, CheckstyleException {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		blackBoxTestEngine.runTest("src/test/java/blackbox/cases/basehalstead/NoOperatorOperands.txt", check);
		
		Assertions.assertEquals(0, check.getOperatorCount());
		Assertions.assertEquals(0, check.getDistinctOperatorCount());
		Assertions.assertEquals(0, check.getOperandCount());
		Assertions.assertEquals(0, check.getDistinctOperandCount());
	}
}
