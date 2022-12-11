package checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberCommentsCheck extends AbstractCheck {
	
	private int commentCount = 0;
	
	public int getCount() {
		return commentCount;
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN};
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		if (aAST.getType() == TokenTypes.SINGLE_LINE_COMMENT || aAST.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
			commentCount++;
        }
    }
	
	@Override
	public boolean isCommentNodesRequired() {
        return true;
    }
	
	public void beginTree(DetailAST aAST) {
		commentCount = 0;
	}
	
	public void finishTree(DetailAST aAST) {
		log(aAST.getLineNo(), "Number of comments = " + commentCount + " -SG");
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
