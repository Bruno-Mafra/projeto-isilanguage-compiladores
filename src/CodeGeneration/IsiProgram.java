package CodeGeneration;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import DataStructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
	
	public void generateJavaTarget() {
		StringBuilder str = new StringBuilder();
		
		str.append("import java.util.Scanner;\n");
		str.append("public class MainClass{\n");
		str.append("public static void main(String args[]) {\n");
		str.append("Scanner reader = new Scanner(System.in);\n");
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode()+"\n");
		}
		str.append("}\n");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void generatePythonTarget() {
		StringBuilder str = new StringBuilder();
		
		str.append("if __name__ == \"__main__\":\n");
		for (AbstractCommand command: comandos) {
			str.append("\t"+command.generatePythonCode().replace("\n", "\n\t")+"\n");
		}
		
		try {
			FileWriter fr = new FileWriter(new File("main.py"));
			fr.write(str.toString());
			fr.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void generateCTarget() {
		StringBuilder str = new StringBuilder();
		
		str.append("#include <stdio.h>\n");
		str.append("#include <stdbool.h>\n");
		str.append("int main() {\n");
		for (AbstractCommand command: comandos) {
			str.append(command.generateCCode()+"\n");
		}
		str.append("return 0;\n");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("main.c"));
			fr.write(str.toString());
			fr.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
