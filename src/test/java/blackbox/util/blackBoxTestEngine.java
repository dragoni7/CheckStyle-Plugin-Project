package blackbox.util;

import java.io.File;
import java.io.IOException;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

public class blackBoxTestEngine {
	
	public static void runTest(String filePath, AbstractCheck check) throws IOException, CheckstyleException {
		
		File testCaseFile = new File(filePath);
		FileContents contents = new FileContents(new FileText(testCaseFile, "UTF-8"));
		DetailAST rootAST = JavaParser.parse(contents);
		
		if (check.isCommentNodesRequired()) {
			rootAST = JavaParser.appendHiddenCommentNodes(rootAST);
		}
		
		check.setFileContents(contents);
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		check.beginTree(rootAST);
		processIter(rootAST, check);
		check.finishTree(rootAST);
	}
	
	private static void processIter(DetailAST root, AbstractCheck check) {
		DetailAST curNode = root;
		
		while (curNode != null) {
			check.visitToken(curNode);
			
			DetailAST toVisit = curNode.getFirstChild();
			while (curNode != null && toVisit == null) {
				check.leaveToken(curNode);
				toVisit = curNode.getNextSibling();
				curNode = curNode.getParent();
			}
			
			curNode = toVisit;
		}
	}

}
