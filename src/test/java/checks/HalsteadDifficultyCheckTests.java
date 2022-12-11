package checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.mockito.Mockito.*;

import java.util.HashMap;

public class HalsteadDifficultyCheckTests {

	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		doReturn(1).when(mockAST).getLineNo();
		HalsteadDifficultyCheck check1 = spy(new HalsteadDifficultyCheck());
		
		// test with empty operators and operands hash maps.
		check1.finishTree(mockAST);
		verify(check1, times(0)).log(anyInt(), anyString()); // Should not log anything since there is insufficient operators/operands
		verify(check1, times(check1.getOperatorsMap().keySet().size() + 3)).getOperatorsMap(); // called 3 times initially. Once on this line, twice in the method, lines 104, 107
		verify(check1, times(check1.getOperandsMap().keySet().size() + 3)).getOperandsMap(); // called 3 times initially. Once on this line, twice in the method, lines 105, 111
		Assertions.assertEquals(check1.distinctOperators, check1.getOperatorsMap().keySet().size());
		Assertions.assertEquals(check1.distinctOperands, check1.getOperandsMap().keySet().size());
		
		// test with non empty operators and operands hash maps.
		HalsteadDifficultyCheck check2 = spy(new HalsteadDifficultyCheck());
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
		doNothing().when(check2).log(anyInt(), anyString()); // return nothing on log call to prevent weird complaint, we just care that the log function is called
		
		check2.finishTree(mockAST);
		verify(check2, times(1)).log(anyInt(), anyString()); // Should log the difficulty
		verify(check2, times(operators.keySet().size() + 2)).getOperatorsMap(); // called 2 times initially. Twice in the method, lines 104, 107
		verify(check2, times(operands.keySet().size() + 2)).getOperandsMap(); // called 2 times initially. Twice in the method, lines 105, 111
		Assertions.assertEquals(check2.distinctOperators, check2.getOperatorsMap().keySet().size());
		Assertions.assertEquals(check2.distinctOperands, check2.getOperandsMap().keySet().size());
	}
}
