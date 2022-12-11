package checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberExpressionCheck extends AbstractCheck {
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.EXPR};
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		if (aAST.getType() == TokenTypes.EXPR) {
			count++;
        }
    }
	
	public void beginTree(DetailAST aAST) {
		count = 0;
	}
	
	public void finishTree(DetailAST aAST) {
		log(0, "Number of expressions = " + count + " -SG");
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
