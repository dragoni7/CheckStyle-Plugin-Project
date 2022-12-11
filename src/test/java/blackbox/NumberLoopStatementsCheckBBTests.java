package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.NumberLoopStatementsCheck;

public class NumberLoopStatementsCheckBBTests {
	
	private static final String checkType = "loops";
	
	@Test
	/**
	 * Covers fault models: 10.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noLoopsTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "noLoops"), check);
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 10.2
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void oneLoopTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "OneLoop"), check);
		Assertions.assertEquals(1, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 10.3
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleLoopsTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "MultipleLoops"), check);
		Assertions.assertEquals(7, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 10.4
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void loopInStringTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "LoopInString"), check);
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 10.5
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void loopInCommentTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "LoopInComment"), check);
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 10.6
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void nestedLoopTest() throws IOException, CheckstyleException {
		NumberLoopStatementsCheck check = new NumberLoopStatementsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "NestedLoop"), check);
		Assertions.assertEquals(3, check.getCount());
	}

}
