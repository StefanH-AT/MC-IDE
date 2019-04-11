package at.tewan.mcide.mcfunction;

import java.util.ArrayList;

public class Syntax {
	private ArrayList<SyntaxPattern> syntax;
	
	public Syntax() {
		syntax = new ArrayList<>();
	}

	public ArrayList<SyntaxPattern> getSyntax() {
		return syntax;
	}

	public void setSyntax(ArrayList<SyntaxPattern> syntax) {
		this.syntax = syntax;
	}
}
