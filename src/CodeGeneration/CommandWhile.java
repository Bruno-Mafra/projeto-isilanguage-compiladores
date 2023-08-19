package CodeGeneration;

import java.util.ArrayList;

public class CommandWhile extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaComandos;
	
	public CommandWhile(String cond, ArrayList<AbstractCommand> lista) {
		this.condition = cond;
		this.listaComandos = lista;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", "&&").replace("|", "||");
		condicao = condicao.replaceAll("True", "true").replaceAll("False", "false");
		
		str.append("while ("+condicao+") {\n");
		for (AbstractCommand cmd: listaComandos) {
			str.append(cmd.generateJavaCode()+"\n");
		}
		str.append("}\n");
		
		return str.toString();
		
	}

	@Override
	public String generateCCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replaceAll("False", "false").replaceAll("True", "true");
		condicao = condicao.replace("&", "&&").replace("|", "||");
		
		str.append("while ("+condicao+") {\n");
		for (AbstractCommand cmd: listaComandos) {
			str.append(cmd.generateCCode()+"\n");
		}
		str.append("}\n");
		
		return str.toString();
	}

	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", " and ").replace("|", " or ").replace("!", "not ");
		
		str.append("while ("+condicao+"):\n");
		for (AbstractCommand cmd: listaComandos) {
			str.append("\t"+cmd.generatePythonCode()+"\n");
		}
		
		return str.toString();
	}

}
