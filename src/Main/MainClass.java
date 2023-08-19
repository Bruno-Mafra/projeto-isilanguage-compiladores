package Main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import Parser.IsiLangLexer;
import Parser.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			parser.exibeComandos();
			
			String outputCode = args[0];
			
			parser.generateCode(outputCode);
			
			System.out.println("Compilation Succesful");
		} catch(Exception ex) {
			System.err.println("ERROR "+ex.getMessage());
		}
	}
}
