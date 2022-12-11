package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.NumberCommentLinesCheck;

public class NumberCommentLinesCheckBBTests {
	
	private static final String checkType = "comment";
	
	@Test
	/**
	 * Covers fault models: 7.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noCommentLines() throws IOException, CheckstyleException {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "NoCommentLines"), check);
		
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 7.2, 7.3
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleCommentLines() throws IOException, CheckstyleException {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "MultipleCommentLines"), check);
		
		Assertions.assertEquals(13, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 7.4
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void onlyBlockComments() throws IOException, CheckstyleException {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "BlockCommentLines"), check);
		
		Assertions.assertEquals(9, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 7.5, 7.6
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void commentsInString() throws IOException, CheckstyleException {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "CommentString"), check);
		
		Assertions.assertEquals(0, check.getCount());
	}
}
