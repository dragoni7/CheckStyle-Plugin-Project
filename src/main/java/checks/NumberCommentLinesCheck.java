package checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberCommentLinesCheck extends AbstractCheck {
	private int count = 0;
	
	public int getCount() {
		return count;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		if (aAST.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
			count++;
        }
		else if (aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
			count++;
			if (aAST.getLastChild().getLineNo() != aAST.getLineNo()) {
				count += (aAST.getLastChild().getLineNo() - aAST.getLineNo());
			}
		}
    }
	
	@Override
	public boolean isCommentNodesRequired() {
        return true;
    }
	
	public void beginTree(DetailAST aAST) {
		count = 0;
	}
	
	public void finishTree(DetailAST aAST) {
		log(aAST.getLineNo(), "Number of comment lines = " + count + " -SG");
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
