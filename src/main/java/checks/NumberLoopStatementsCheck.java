package checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberLoopStatementsCheck extends AbstractCheck {
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE};
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		if (aAST.getType() == TokenTypes.LITERAL_FOR || aAST.getType() == TokenTypes.LITERAL_WHILE || aAST.getType() == TokenTypes.DO_WHILE) {
			count++;
        }
    }
	
	public void beginTree(DetailAST aAST) {
		count = 0;
	}
	
	public void finishTree(DetailAST aAST) {
		log(aAST.getLineNo(), "Number of loop statements = " + count + " -SG");
	}

	@Override
	public int[] getAcceptableTokens() {
		return this.getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
}
