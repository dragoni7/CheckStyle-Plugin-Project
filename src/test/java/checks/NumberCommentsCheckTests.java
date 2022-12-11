package checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class NumberCommentsCheckTests {
	
	@Test
	public void getCountTest() {
		NumberCommentsCheck check = new NumberCommentsCheck();
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}

	@Test
	public void getDefaultTokensTest() {
		NumberCommentsCheck check = new NumberCommentsCheck();
		
		Assertions.assertNotNull(check.getDefaultTokens());
		Assertions.assertEquals(check.getDefaultTokens().length, 2);
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberCommentsCheck check = new NumberCommentsCheck();
		
		check.beginTree(mockAST);
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.SINGLE_LINE_COMMENT).when(mockAST).getType();
		NumberCommentsCheck check1 = spy(new NumberCommentsCheck());
		
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 1); // Line 22 True
		
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockAST).getType();
		check1.beginTree(mockAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 1); // Line 22 True
		
		doReturn(TokenTypes.LITERAL_ASSERT).when(mockAST).getType();
		check1.beginTree(mockAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 0); // Line 22 False
	}
	
	@Test
	public void isCommentNodesRequiredTest() {
		NumberCommentsCheck check = new NumberCommentsCheck();
		Assertions.assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberCommentsCheck check = spy(new NumberCommentsCheck());
		doNothing().when(check).log(anyInt(), anyString());
		
		check.finishTree(mockAST);
		verify(check, times(1)).log(anyInt(), anyString()); // Should log the count
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumberCommentsCheck check1 = spy(new NumberCommentsCheck());
		check1.getAcceptableTokens();
		verify(check1).getDefaultTokens();
		Assertions.assertEquals(check1.getAcceptableTokens().length, check1.getDefaultTokens().length);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumberCommentsCheck check1 = new NumberCommentsCheck();
		Assertions.assertTrue(check1.getRequiredTokens() instanceof int[]);
		Assertions.assertEquals(check1.getRequiredTokens().length, 0);
	}
}
