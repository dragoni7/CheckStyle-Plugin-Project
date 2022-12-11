package checks;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberExpressionCheckTests {
	
	@Test
	public void getCountTest() {
		NumberExpressionCheck check = new NumberExpressionCheck();
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}

	@Test
	public void getDefaultTokensTest() {
		NumberExpressionCheck check = new NumberExpressionCheck();
		
		Assertions.assertNotNull(check.getDefaultTokens());
		Assertions.assertEquals(check.getDefaultTokens().length, 1);
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberExpressionCheck check = new NumberExpressionCheck();
		
		check.beginTree(mockAST);
		
		Assertions.assertNotNull(check.getCount());
		Assertions.assertEquals(check.getCount(), 0);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(TokenTypes.EXPR).when(mockAST).getType();
		NumberExpressionCheck check1 = spy(new NumberExpressionCheck());
		
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 1); // Line 21 True
		
		doReturn(TokenTypes.LITERAL_ASSERT).when(mockAST).getType();
		check1.beginTree(mockAST);
		check1.visitToken(mockAST);
		Assertions.assertEquals(check1.getCount(), 0); // Line 21 False
	}
	
	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		NumberExpressionCheck check = spy(new NumberExpressionCheck());
		doNothing().when(check).log(anyInt(), anyString());
		
		check.finishTree(mockAST);
		verify(check, times(1)).log(anyInt(), anyString()); // Should log the count
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumberExpressionCheck check1 = spy(new NumberExpressionCheck());
		check1.getAcceptableTokens();
		verify(check1).getDefaultTokens();
		Assertions.assertEquals(check1.getAcceptableTokens().length, check1.getDefaultTokens().length);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumberExpressionCheck check1 = new NumberExpressionCheck();
		Assertions.assertTrue(check1.getRequiredTokens() instanceof int[]);
		Assertions.assertEquals(check1.getRequiredTokens().length, 0);
	}
}
