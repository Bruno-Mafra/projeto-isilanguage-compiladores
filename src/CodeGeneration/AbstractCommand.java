package CodeGeneration;

public abstract class AbstractCommand {
	public abstract String generateJavaCode();
	public abstract String generateCCode();
	public abstract String generatePythonCode();
}
