package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.NumberExpressionCheck;

public class NumberExpressionCheckBBTests {
	
	private static final String checkType = "expressions";
	
	@Test
	/**
	 * Covers fault models: 9.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void oneExpressionTest() throws IOException, CheckstyleException {
		NumberExpressionCheck check = new NumberExpressionCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "OneExpression"), check);
		Assertions.assertEquals(1, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 9.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noExpressionsTest() throws IOException, CheckstyleException {
		NumberExpressionCheck check = new NumberExpressionCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "NoExpression"), check);
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 9.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleExpressionsTest() throws IOException, CheckstyleException {
		NumberExpressionCheck check = new NumberExpressionCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "MultipleExpressions"), check);
		Assertions.assertEquals(4, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 9.5
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void commentExpressionTest() throws IOException, CheckstyleException {
		NumberExpressionCheck check = new NumberExpressionCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "ExpressionInComment"), check);
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test 
	/**
	 * Covers fault models: 9.4
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void stringExpressionTest() throws IOException, CheckstyleException {
		NumberExpressionCheck check = new NumberExpressionCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "ExpressionInString"), check);
		Assertions.assertEquals(2, check.getCount());
	}
	
}
