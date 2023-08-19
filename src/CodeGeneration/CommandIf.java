package CodeGeneration;

import java.util.ArrayList;

public class CommandIf extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	
	public CommandIf(String condition, ArrayList<AbstractCommand> lt, ArrayList<AbstractCommand> lf) {
		this.condition = condition;
		this.listaTrue = lt;
		this.listaFalse = lf;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", "&&").replace("|", "||");
		condicao = condicao.replaceAll("True", "true").replaceAll("False", "false");
		
		
		str.append("if ("+condicao+") {\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateJavaCode()+"\n");
		}
		str.append("}\n");
		if ((listaFalse != null) && (listaFalse.size() > 0)) {
			str.append("else {\n");
			for (AbstractCommand cmd: listaFalse) {
				str.append(cmd.generateJavaCode()+"\n");
			}
			str.append("}\n");
		}
		
		return str.toString();
	}

	@Override
	public String generateCCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replaceAll("False", "false").replaceAll("True", "true");
		condicao = condicao.replace("&", "&&").replace("|", "||");
		
		str.append("if ("+condicao+") {\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append(cmd.generateCCode()+"\n");
		}
		str.append("}\n");
		if ((listaFalse != null) && (listaFalse.size() > 0)) {
			str.append("else {\n");
			for (AbstractCommand cmd: listaFalse) {
				str.append(cmd.generateCCode() + "\n");
			}
			str.append("}\n");
		}
		
		return str.toString();
		
	}

	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", " and ").replace("|", " or ").replace("!", "not ");
		
		str.append("if ("+condicao+"):\n");
		for (AbstractCommand cmd: listaTrue) {
			str.append("\t"+cmd.generatePythonCode().replace("\n", "\n\t")+"\n");
		}
		if ((listaFalse != null) && (listaFalse.size() > 0)) {
			str.append("else:\n");
			for (AbstractCommand cmd: listaFalse) {
				str.append("\t"+cmd.generatePythonCode().replace("\n", "\n\t")+"\n");
			}
		}
		
		return str.toString();
	}

}
