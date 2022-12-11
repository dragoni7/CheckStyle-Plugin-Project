package checks;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class OperandAmountCheckTests {
	
	@Test
	public void finishTreeTest() {
		DetailAST mockAST = mock(DetailAST.class);
		OperandAmountCheck check = spy(new OperandAmountCheck());
		doNothing().when(check).log(anyInt(), anyString());
		
		check.finishTree(mockAST);
		verify(check, times(1)).log(anyInt(), anyString()); // Should log the count
	}
}
