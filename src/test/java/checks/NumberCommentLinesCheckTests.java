package checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class NumberCommentLinesCheckTests {
	
	@Test
	public void getCountTest() {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}

	@Test
	public void getDefaultTokensTest() {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		
		Assertions.assertNotNull(check.getDefaultTokens());
		Assertions.assertEquals(check.getDefaultTokens().length, 2);
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		
		check.beginTree(mockAST);
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST mockAST = mock(DetailAST.class);
		DetailAST mockLastAST = mock(DetailAST.class);
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(mockAST).getType();
		doReturn(1).when(mockAST).getLineNo();
		doReturn(1).when(mockLastAST).getLineNo();
		doReturn(mockLastAST).when(mockAST).getLastChild();
		NumberCommentLinesCheck check1 = spy(new NumberCommentLinesCheck());
		
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 1); // Line 21 True
		
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockAST).getType();
		check1.beginTree(mockLastAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 1); // Line 21 False, Line 24 True, Line 26 False
		
		doReturn(4).when(mockLastAST).getLineNo();
		check1.beginTree(mockLastAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 4); // Line 21 False, Line 24 True, Line 26 True
		
		doReturn(TokenTypes.LITERAL_ASSERT).when(mockAST).getType();
		check1.beginTree(mockLastAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 0); // Line 21 False, Line 24 False, Line 26 False
	}
	
	@Test
	public void isCommentNodesRequiredTest() {
		NumberCommentLinesCheck check = new NumberCommentLinesCheck();
		Assertions.assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberCommentLinesCheck check = spy(new NumberCommentLinesCheck());
		doNothing().when(check).log(anyInt(), anyString());
		
		check.finishTree(mockAST);
		verify(check, times(1)).log(anyInt(), anyString()); // Should log the comment lines
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumberCommentLinesCheck check1 = spy(new NumberCommentLinesCheck());
		check1.getAcceptableTokens();
		verify(check1).getDefaultTokens();
		Assertions.assertEquals(check1.getAcceptableTokens().length, check1.getDefaultTokens().length);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumberCommentLinesCheck check1 = new NumberCommentLinesCheck();
		Assertions.assertTrue(check1.getRequiredTokens() instanceof int[]);
		Assertions.assertEquals(check1.getRequiredTokens().length, 0);
	}
}
