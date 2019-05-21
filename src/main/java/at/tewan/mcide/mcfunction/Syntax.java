package at.tewan.mcide.mcfunction;

import java.util.ArrayList;

public class Syntax {
	private ArrayList<SyntaxPattern> syntaxPatterns;
	
	public Syntax() {
		syntaxPatterns = new ArrayList<>();
	}

	public ArrayList<SyntaxPattern> getSyntaxPatterns() {
		return syntaxPatterns;
	}

	public void setSyntaxPatterns(ArrayList<SyntaxPattern> syntaxPatterns) {
		this.syntaxPatterns = syntaxPatterns;
	}

	public SyntaxPattern getSyntaxPattern(String name) {
		for(SyntaxPattern s : syntaxPatterns) {
			if(s.getName().equals(name)) return s;
		}

		return null;
	}

	public String getPattern(String name) {
		for(SyntaxPattern s : syntaxPatterns) {
			if(s.getName().equals(name)) return s.getPattern();
		}

		return null;
	}

	public String getName(String pattern) {
		for(SyntaxPattern s : syntaxPatterns) {
			if(s.getPattern().equals(pattern)) return s.getName();
		}

		return null;
	}
}
