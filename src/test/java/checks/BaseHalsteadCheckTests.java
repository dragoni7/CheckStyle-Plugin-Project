package checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.*;

import java.util.HashMap;


public class BaseHalsteadCheckTests {
	
	@Test
	public void getDefaultTokensTest() {
		BaseHalsteadCheck check = new BaseHalsteadCheck();
		
		Assertions.assertNotNull(check.getDefaultTokens());
		Assertions.assertEquals(check.getDefaultTokens().length, 68);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST mockAST = mock(DetailAST.class);
		String astText;
		
		BaseHalsteadCheck check1 = spy(new BaseHalsteadCheck());
		doReturn(TokenTypes.PLUS).when(mockAST).getType(); // Plus is in default operator tokens
		
		// Test adding new operator to hash map. Line 78
		// Branch 73: True, Branch 74: False, Branch 84: False, Branch 85: False
		check1.visitToken(mockAST);
		Assertions.assertTrue(check1.getOperatorsMap().containsKey(TokenTypes.PLUS));
		Assertions.assertEquals(1, check1.getOperatorsMap().get(TokenTypes.PLUS));
		Assertions.assertTrue(check1.getOperandsMap().isEmpty());
		verify(check1, times(1)).getOperandsMap();
		verify(check1, times(1)).putOperator(TokenTypes.PLUS, 1);
		
		// Test incrementing existing operator in hash map. Line 75
		// Branch 73: True, Branch 74: True, Branch 84: False, Branch 85: False
		check1.visitToken(mockAST);
		Assertions.assertTrue(check1.getOperatorsMap().containsKey(TokenTypes.PLUS));
		Assertions.assertEquals(2, check1.getOperatorsMap().get(TokenTypes.PLUS));
		Assertions.assertTrue(check1.getOperandsMap().isEmpty());
		verify(check1, times(2)).getOperandsMap();
		verify(check1, times(1)).putOperator(TokenTypes.PLUS, 2);
		
		BaseHalsteadCheck check2 = spy(new BaseHalsteadCheck());
		doReturn(TokenTypes.STRING_LITERAL).when(mockAST).getType(); // String Literal is in default operand tokens
		doReturn("string_literal").when(mockAST).getText();
		
		// Test adding new operand to hash map. Line 89
		// Branch 73: False, Branch 74: False, Branch 84: True, Branch 85: False
		DetailAST mockParent = mock(DetailAST.class);
		
		doReturn(mockParent).when(mockAST).getParent();
		
		check2.visitToken(mockAST);
		astText = mockAST.getText();
		Assertions.assertTrue(check2.getOperandsMap().containsKey(astText));
		Assertions.assertEquals(1, check2.getOperandsMap().get(astText));
		Assertions.assertTrue(check2.getOperatorsMap().isEmpty());
		verify(check2, times(1)).getOperatorsMap();
		verify(check2, times(1)).putOperand(astText, 1);
		
		// Test incrementing existing operand in hash map. Line 86
		// Branch 73: False, Branch 74: False, Branch 84: True, Branch 85: True
		check2.visitToken(mockAST);
		Assertions.assertTrue(check2.getOperandsMap().containsKey(astText));
		Assertions.assertEquals(2, check2.getOperandsMap().get(astText));
		Assertions.assertTrue(check2.getOperatorsMap().isEmpty());
		verify(check2, times(2)).getOperatorsMap();
		verify(check2, times(1)).putOperand(astText, 2);
		
		// Test no changes to operator or operand hash map on invalid TokenType
		// Branch 73: False, Branch 74: False, Branch 84: False, Branch 85: False
		BaseHalsteadCheck check3 = spy(new BaseHalsteadCheck());
		doReturn(TokenTypes.PACKAGE_DEF).when(mockAST).getType(); // PackageDef is not in either default tokens
		doReturn("package_def").when(mockAST).getText();
		
		check3.visitToken(mockAST);
		astText = mockAST.getText();
		Assertions.assertTrue(check3.getOperandsMap().isEmpty());
		Assertions.assertTrue(check3.getOperatorsMap().isEmpty());
		verify(check3, times(1)).getOperatorsMap();
		verify(check3, times(1)).getOperandsMap();
		verify(check3, times(0)).putOperator(anyInt(), anyInt());
		verify(check3, times(0)).putOperand(anyString(), anyInt());
		
		// Test adding type identifier as operand to hash map. Line 89
		// Branch 73: False, Branch 74: False, Branch 84: False, Branch 85: False
		BaseHalsteadCheck check4 = spy(new BaseHalsteadCheck());
		// when the parent is a type and the ast is an ident, not allowed
		doReturn(TokenTypes.TYPE).when(mockParent).getType();
		doReturn(TokenTypes.IDENT).when(mockAST).getType();
		doReturn("identifier").when(mockAST).getText();
		
		check4.visitToken(mockAST);
		astText = mockAST.getText();
		Assertions.assertFalse(check4.getOperandsMap().containsKey(astText));
		Assertions.assertTrue(check4.getOperandsMap().isEmpty());
		Assertions.assertTrue(check4.getOperatorsMap().isEmpty());
		verify(check4, times(1)).getOperatorsMap();
		verify(check4, times(0)).putOperand(astText, 1);
	}

	@Test
	public void beginTreeTest() {
		BaseHalsteadCheck check1 = spy(new BaseHalsteadCheck());
		DetailAST mockAST = mock(DetailAST.class);
		
		check1.beginTree(mockAST);
		verify(check1).getOperandsMap();
		verify(check1).getOperatorsMap();
		
		Assertions.assertTrue(check1.distinctOperators == 0);
		Assertions.assertTrue(check1.distinctOperands == 0);
		Assertions.assertTrue(check1.numOperators == 0);
		Assertions.assertTrue(check1.numOperands == 0);
		Assertions.assertTrue(check1.getOperatorsMap().isEmpty());
		Assertions.assertTrue(check1.getOperandsMap().isEmpty());
	}
	
	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		
		BaseHalsteadCheck check1 = spy(new BaseHalsteadCheck());
		
		// test with empty operators and operands hash maps.
		check1.finishTree(mockAST);
		verify(check1, times(check1.getOperatorsMap().keySet().size() + 3)).getOperatorsMap(); // called 3 times initially. Once on this line, twice in the method, lines 104, 107
		verify(check1, times(check1.getOperandsMap().keySet().size() + 3)).getOperandsMap(); // called 3 times initially. Once on this line, twice in the method, lines 105, 111
		Assertions.assertEquals(check1.distinctOperators, check1.getOperatorsMap().keySet().size());
		Assertions.assertEquals(check1.distinctOperands, check1.getOperandsMap().keySet().size());
		
		// test with non empty operators and operands hash maps.
		BaseHalsteadCheck check2 = spy(new BaseHalsteadCheck());
		HashMap<Integer, Integer> operators = new HashMap<Integer, Integer>();
		operators.put(TokenTypes.PLUS, 5);
		operators.put(TokenTypes.MINUS, 1);
		operators.put(TokenTypes.DIV, 5);
		operators.put(TokenTypes.MOD, 2);
		operators.put(TokenTypes.BXOR, 10);
		operators.put(TokenTypes.EQUAL, 1);
		HashMap<String, Integer> operands = new HashMap<String, Integer>();
		operands.put("ident", 3);
		operands.put("num_double", 1);
		operands.put("num_float", 2);
		operands.put("char_literal", 1);
		operands.put("string_literal", 6);
		doReturn(operators).when(check2).getOperatorsMap();
		doReturn(operands).when(check2).getOperandsMap();
		
		check2.finishTree(mockAST);
		verify(check2, times(operators.keySet().size() + 2)).getOperatorsMap(); // called 2 times initially. Twice in the method, lines 104, 107
		verify(check2, times(operands.keySet().size() + 2)).getOperandsMap(); // called 2 times initially. Twice in the method, lines 105, 111
		Assertions.assertEquals(check2.distinctOperators, check2.getOperatorsMap().keySet().size());
		Assertions.assertEquals(check2.distinctOperands, check2.getOperandsMap().keySet().size());
	}
	
	@Test
	public void getAcceptableTokensTest() {
		BaseHalsteadCheck check1 = spy(new BaseHalsteadCheck());
		check1.getAcceptableTokens();
		verify(check1).getDefaultTokens();
		Assertions.assertEquals(check1.getAcceptableTokens().length, check1.getDefaultTokens().length);
	}
	
	@Test
	public void getRequiredTokensTest() {
		BaseHalsteadCheck check1 = new BaseHalsteadCheck();
		Assertions.assertTrue(check1.getRequiredTokens() instanceof int[]);
		Assertions.assertEquals(check1.getRequiredTokens().length, 0);
	}
}
