package blackbox;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import blackbox.util.blackBoxTestEngine;
import blackbox.util.testCasePath;
import checks.NumberCommentsCheck;

public class NumberCommentsCheckBBTests {
	
	private static final String checkType = "comment";
	
	@Test
	/**
	 * Covers fault models: 8.1
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void noComments() throws IOException, CheckstyleException {
		NumberCommentsCheck check = new NumberCommentsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "NoCommentLines"), check);
		
		Assertions.assertEquals(0, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 8.2, 8.3, 8.5
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void multipleComments() throws IOException, CheckstyleException {
		NumberCommentsCheck check = new NumberCommentsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "MultipleCommentLines"), check);
		
		Assertions.assertEquals(8, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 8.4, 8.5
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void onlyBlockComments() throws IOException, CheckstyleException {
		NumberCommentsCheck check = new NumberCommentsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "BlockCommentLines"), check);
		
		Assertions.assertEquals(3, check.getCount());
	}
	
	@Test
	/**
	 * Covers fault models: 8.6, 8.7
	 * @throws IOException
	 * @throws CheckstyleException
	 */
	public void commentsInString() throws IOException, CheckstyleException {
		NumberCommentsCheck check = new NumberCommentsCheck();
		blackBoxTestEngine.runTest(testCasePath.get(checkType, "CommentString"), check);
		
		Assertions.assertEquals(0, check.getCount());
	}
}
