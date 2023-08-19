package CodeGeneration;

import java.util.ArrayList;

public class CommandDoWhile extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> listaComandos;
	
	public CommandDoWhile(String cond, ArrayList<AbstractCommand> lista) {
		this.condition = cond;
		this.listaComandos = lista;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", "&&").replace("|", "||");
		condicao = condicao.replaceAll("True", "true").replaceAll("False", "false");
		
		str.append("do {\n");
		for (AbstractCommand cmd: listaComandos) {
			str.append(cmd.generateJavaCode()+"\n");
		}
		str.append("} while ("+condicao+");");
		
		return str.toString();
	}

	@Override
	public String generateCCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replaceAll("False", "false").replaceAll("True", "true");
		condicao = condicao.replace("&", "&&").replace("|", "||");
		
		str.append("do {\n");
		for (AbstractCommand cmd: listaComandos) {
			str.append(cmd.generateCCode()+"\n");
		}
		str.append("} while ("+condicao+");");
		
		return str.toString();
	}

	@Override
	public String generatePythonCode() {
		StringBuilder str = new StringBuilder();
		String condicao = this.condition;
		
		condicao = condicao.replace("&", " and ").replace("|", " or ").replace("!", "not ");
		
		str.append("while True:\n");
		for(AbstractCommand cmd: listaComandos) {
			str.append("\t"+cmd.generatePythonCode()+"\n");
		}
		str.append("\t"+"if ("+condicao+"): break");
		
		return str.toString();
	}

}
