// Generated from IsiLang.g4 by ANTLR 4.7.1
package Parser;

	import DataStructures.IsiSymbol;
	import DataStructures.IsiVariable;
	import DataStructures.IsiSymbolTable;
	import DataStructures.DataType;
	import CodeGeneration.AbstractCommand;
	import CodeGeneration.CommandAttrib;
	import CodeGeneration.CommandDeclaracao;
	import CodeGeneration.CommandDoWhile;
	import CodeGeneration.CommandEscrita;
	import CodeGeneration.CommandEscritaSL;
	import CodeGeneration.CommandIf;
	import CodeGeneration.CommandLeitura;
	import CodeGeneration.CommandWhile;
	import CodeGeneration.IsiProgram;
	import Exceptions.IsiSemanticException;
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.Set;
	import ExpressionEval.VariableExtractor;
	import ExpressionEval.EvaluateExpression;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, AC=16, FC=17, SC=18, 
		DP=19, VR=20, OA=21, OP=22, OR=23, OL=24, NOT=25, ATTR=26, ID=27, BOOLEAN=28, 
		NUMBER=29, STRING=30, WS=31;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "AP", "FP", "AC", "FC", "SC", "DP", 
		"VR", "OA", "OP", "OR", "OL", "NOT", "ATTR", "ID", "BOOLEAN", "NUMBER", 
		"STRING", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog'", "'leia'", "'escreva'", "'escrevasl'", 
		"'se'", "'senao'", "'enquanto'", "'fa\u00E7a'", "'int'", "'float'", "'bool'", 
		"'str'", "'('", "')'", "'{'", "'}'", "';'", "':'", "','", null, null, 
		null, null, "'!'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "AP", "FP", "AC", "FC", "SC", "DP", "VR", "OA", "OP", "OR", 
		"OL", "NOT", "ATTR", "ID", "BOOLEAN", "NUMBER", "STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private String _tipo;
		private String _varName;
		private String _varValue;
		private DataType _leftType;
		private DataType _rightType;
		private ArrayList<DataType> expressionTypes = new ArrayList<DataType>();
		private ArrayList<String> expressionOperations = new ArrayList<String>();
		private IsiVariable _symbol;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private String _opRel;
		private String _exprRel1;
		private String _exprRel2;

		private IsiProgram program = new IsiProgram();
		private String _readId;
		private String _expressionString;
		private String _decisionString;
		private String _exprId;
		private Stack<ArrayList<AbstractCommand>> allCommands = new Stack<ArrayList<AbstractCommand>>();
		private ArrayList<AbstractCommand> currentThread;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> subLista;
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(String target) {
			if (target.equals("Java")) {
				program.generateJavaTarget();
			} else if (target.equals("Python")) {
				program.generatePythonTarget();
			} else if (target.equals("C")) {
				program.generateCTarget();
			}
		}
		
		public DataType getType(String type) {
			if (type.equals("str")) return DataType.STR;
			if (type.equals("int")) return DataType.INT;
			if (type.equals("float")) return DataType.FLOAT;
			if (type.equals("bool")) return DataType.BOOL;
				
			return DataType.EMPTY;
		}
		
		public DataType getTypeById(String id) {
			String type = symbolTable.getType(id);
			
			if (type.equals("str")) return DataType.STR;
			if (type.equals("int")) return DataType.INT;
			if (type.equals("float")) return DataType.FLOAT;
			if (type.equals("bool")) return DataType.BOOL;
				
			return DataType.EMPTY;
		}
		
		public DataType getNumberType(String number) {
			if (number.contains(".")) {
				return DataType.FLOAT;
			} else {
				return DataType.INT;
			}
		}
		
		public void cleanExpression() {
			_expressionString = "";
			expressionTypes = new ArrayList<DataType>();
			expressionOperations = new ArrayList<String>();
		}
		
		public void markSymbolAsInitialized(String id) {
			IsiSymbol symbol = symbolTable.get(id);

			if (symbol != null) symbol.markAsInitialized();
		}
		
		public void markSymbolAsUsed(String id) {
			IsiSymbol symbol = symbolTable.get(id);

			if (symbol != null) symbol.markAsUsed();
		}
		
		public void verificaDeclaracao(String id) {
			if (!symbolTable.exists(id)) {
				throw new IsiSemanticException("Símbolo "+id+" não foi declarado.");
			}
		}
		
		public void checkUnusedSymbols() {
			Set<String> keys = symbolTable.getAllSymbolsKeys();

			for (String key : keys) {
				IsiSymbol symbol = symbolTable.get(key);
			    
			    try {
				    if (!symbol.getIsUsed())
				    	throw new IsiSemanticException("Símbolo " + symbol.getName() + " foi declarado mas não foi utilizado.");
			    } catch (IsiSemanticException e) {
			    	System.out.println("WARNING: " + e.getMessage());
			    }
			}
		}
		
		public void checkDeclarationAndInitialization(String id) {
			IsiSymbol symbol = symbolTable.get(id);
			
			if (symbol == null) 
				throw new IsiSemanticException("Símbolo " + id + " não foi declarado.");
			
			try {
				if (!symbol.getIsInitialized()) {
					throw new IsiSemanticException("Símbolo " + id + " está sendo usado sem ter um valor inicial.");	
				}
			} catch (IsiSemanticException e) {
			    System.out.println("WARNING: " + e.getMessage());
			}
			
			symbol.markAsUsed();
		}
		
		public void verifyOperations(DataType expressionType) {
			if (expressionType == DataType.INT || expressionType == DataType.FLOAT ) {
				for (String operation : expressionOperations) {
		            if (operation.contains("&") || operation.contains("|")) {
		                throw new IsiSemanticException("Operação matemática incompatível: O operador: " + operation + " na expressão: " + _expressionString +
		                " é incompatível com o tipo " + expressionType + ".");
		            }
		        }	
			}
			
			if (expressionType == DataType.STR) {
				for (String operation : expressionOperations) {
		            if (operation != null) {
		                throw new IsiSemanticException("Operação com strings incompatível: O operador: " + operation + " na expressão: " + _expressionString +
		                " é incompatível com o tipo " + expressionType + ".");
		            }
		        }	
			}
			
			if (expressionType == DataType.BOOL ) {
				for (String operation : expressionOperations) {
		            if (!(operation.contains("&") || operation.contains("|") || operation.contains("!"))) {
		                throw new IsiSemanticException("Operação booleana incompatível: O operador: " + operation + " na expressão: " + _expressionString +
		                " é incompatível com o tipo " + expressionType + ".");
		            }
		        }	
			}
		}
		
		
		public DataType verificaTipoExpressao() {
			DataType expressionType = expressionTypes.get(0);
			
			for (DataType type : expressionTypes) {
				if (expressionType == type) continue;
			
				if (expressionType == DataType.INT || expressionType == DataType.FLOAT) {
					if (type == DataType.STR || type == DataType.BOOL)
						throw new IsiSemanticException("Conflito entre os tipos " + expressionType + " e " + type + " na expressão: " + _expressionString);
						
					if (type == DataType.FLOAT) expressionType = type;	
				}
				
				if ((expressionType == DataType.STR && type != DataType.STR) || (expressionType == DataType.BOOL && type != DataType.BOOL)) {
					throw new IsiSemanticException("Conflito entre os tipos " + expressionType + " e " + type + " na expressão: " + _expressionString);
				}
			}

			verifyOperations(expressionType);
			return expressionType;
		}
		
		public void checkRelationalOperation(DataType left, DataType right, String exprRel1, String exprRel2, String opRel) {
			if (left != right) {
				if (!((left == DataType.FLOAT || left == DataType.INT) && (right == DataType.FLOAT || right == DataType.INT))) {
					throw new IsiSemanticException("Tipos " + left + " e " + right + " não podem ser comparados na relação: "
						+ exprRel1 + " " + opRel + " " + exprRel2);
				}
			}
				
		 	if (!(opRel.contains("!=") || opRel.contains("=="))) {
		 		if (left == DataType.STR || right == DataType.STR)
					throw new IsiSemanticException("Tipo " + DataType.STR + " é incompatÍvel com o operador relacional: " 
						+ opRel + ", na relação: " + exprRel1 + " " + opRel + " " + exprRel2);
		 		
		 		if (left == DataType.BOOL || right == DataType.BOOL)
					throw new IsiSemanticException("Tipo " + DataType.BOOL + " é incompatÍvel com o operador relacional: " 
						+ opRel + ", na relação: " + exprRel1 + " " + opRel + " " + exprRel2);
		 	}
		}
		
		public void verificaExpressaoBooleana() {
			DataType expressionType = verificaTipoExpressao();
			if (!(expressionType == DataType.BOOL)) {
				throw new IsiSemanticException("Expressão inserida em condicional não é uma expressão booleana.");
			}
		}
		
		public void verificaTiposAttrib(DataType leftType, DataType rightType) {
			if (leftType != rightType) {
				if (!(leftType == DataType.FLOAT && rightType == DataType.INT))
					throw new IsiSemanticException("Erro: O tipo " + leftType + " não pode receber variável do tipo " + rightType + " na atribuição.");
			}
		}

		public boolean isExpressionEvaluable(String expression, DataType type) {
			if (!(type == DataType.INT || type == DataType.FLOAT)) return false;

			Set<String> variableIds = VariableExtractor.extractVariableIds(expression);

			return variableIds.isEmpty() ? true : false;
		}

		public String evaluateExpression(String expression, DataType type) {
			if (type == DataType.FLOAT) {
				float result = EvaluateExpression.evaluate(expression);
				return Float.toString(result);
			} else {
				int result = (int)EvaluateExpression.evaluate(expression);
				return Integer.toString(result);
			}
		}


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u00e9\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00af\n\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\7\34\u00b9\n\34\f\34\16\34\u00bc\13"+
		"\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u00c7\n\35\3\36"+
		"\6\36\u00ca\n\36\r\36\16\36\u00cb\3\36\3\36\6\36\u00d0\n\36\r\36\16\36"+
		"\u00d1\5\36\u00d4\n\36\3\37\3\37\3\37\3\37\3\37\7\37\u00db\n\37\f\37\16"+
		"\37\u00de\13\37\3\37\3\37\3 \3 \3 \3 \5 \u00e6\n \3 \3 \2\2!\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!\3\2"+
		"\13\4\2--//\4\2,,\61\61\4\2>>@@\4\2((~~\3\2c|\5\2\62;C\\c|\3\2\62;\7\2"+
		"\13\f\"\"\62;C\\c|\4\2\13\f\"\"\2\u00f6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3"+
		"A\3\2\2\2\5J\3\2\2\2\7R\3\2\2\2\tW\3\2\2\2\13_\3\2\2\2\ri\3\2\2\2\17l"+
		"\3\2\2\2\21r\3\2\2\2\23{\3\2\2\2\25\u0080\3\2\2\2\27\u0084\3\2\2\2\31"+
		"\u008a\3\2\2\2\33\u008f\3\2\2\2\35\u0093\3\2\2\2\37\u0095\3\2\2\2!\u0097"+
		"\3\2\2\2#\u0099\3\2\2\2%\u009b\3\2\2\2\'\u009d\3\2\2\2)\u009f\3\2\2\2"+
		"+\u00a1\3\2\2\2-\u00a3\3\2\2\2/\u00ae\3\2\2\2\61\u00b0\3\2\2\2\63\u00b2"+
		"\3\2\2\2\65\u00b4\3\2\2\2\67\u00b6\3\2\2\29\u00c6\3\2\2\2;\u00c9\3\2\2"+
		"\2=\u00d5\3\2\2\2?\u00e5\3\2\2\2AB\7r\2\2BC\7t\2\2CD\7q\2\2DE\7i\2\2E"+
		"F\7t\2\2FG\7c\2\2GH\7o\2\2HI\7c\2\2I\4\3\2\2\2JK\7h\2\2KL\7k\2\2LM\7o"+
		"\2\2MN\7r\2\2NO\7t\2\2OP\7q\2\2PQ\7i\2\2Q\6\3\2\2\2RS\7n\2\2ST\7g\2\2"+
		"TU\7k\2\2UV\7c\2\2V\b\3\2\2\2WX\7g\2\2XY\7u\2\2YZ\7e\2\2Z[\7t\2\2[\\\7"+
		"g\2\2\\]\7x\2\2]^\7c\2\2^\n\3\2\2\2_`\7g\2\2`a\7u\2\2ab\7e\2\2bc\7t\2"+
		"\2cd\7g\2\2de\7x\2\2ef\7c\2\2fg\7u\2\2gh\7n\2\2h\f\3\2\2\2ij\7u\2\2jk"+
		"\7g\2\2k\16\3\2\2\2lm\7u\2\2mn\7g\2\2no\7p\2\2op\7c\2\2pq\7q\2\2q\20\3"+
		"\2\2\2rs\7g\2\2st\7p\2\2tu\7s\2\2uv\7w\2\2vw\7c\2\2wx\7p\2\2xy\7v\2\2"+
		"yz\7q\2\2z\22\3\2\2\2{|\7h\2\2|}\7c\2\2}~\7\u00e9\2\2~\177\7c\2\2\177"+
		"\24\3\2\2\2\u0080\u0081\7k\2\2\u0081\u0082\7p\2\2\u0082\u0083\7v\2\2\u0083"+
		"\26\3\2\2\2\u0084\u0085\7h\2\2\u0085\u0086\7n\2\2\u0086\u0087\7q\2\2\u0087"+
		"\u0088\7c\2\2\u0088\u0089\7v\2\2\u0089\30\3\2\2\2\u008a\u008b\7d\2\2\u008b"+
		"\u008c\7q\2\2\u008c\u008d\7q\2\2\u008d\u008e\7n\2\2\u008e\32\3\2\2\2\u008f"+
		"\u0090\7u\2\2\u0090\u0091\7v\2\2\u0091\u0092\7t\2\2\u0092\34\3\2\2\2\u0093"+
		"\u0094\7*\2\2\u0094\36\3\2\2\2\u0095\u0096\7+\2\2\u0096 \3\2\2\2\u0097"+
		"\u0098\7}\2\2\u0098\"\3\2\2\2\u0099\u009a\7\177\2\2\u009a$\3\2\2\2\u009b"+
		"\u009c\7=\2\2\u009c&\3\2\2\2\u009d\u009e\7<\2\2\u009e(\3\2\2\2\u009f\u00a0"+
		"\7.\2\2\u00a0*\3\2\2\2\u00a1\u00a2\t\2\2\2\u00a2,\3\2\2\2\u00a3\u00a4"+
		"\t\3\2\2\u00a4.\3\2\2\2\u00a5\u00af\t\4\2\2\u00a6\u00a7\7@\2\2\u00a7\u00af"+
		"\7?\2\2\u00a8\u00a9\7>\2\2\u00a9\u00af\7?\2\2\u00aa\u00ab\7#\2\2\u00ab"+
		"\u00af\7?\2\2\u00ac\u00ad\7?\2\2\u00ad\u00af\7?\2\2\u00ae\u00a5\3\2\2"+
		"\2\u00ae\u00a6\3\2\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00aa\3\2\2\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\60\3\2\2\2\u00b0\u00b1\t\5\2\2\u00b1\62\3\2\2\2\u00b2\u00b3"+
		"\7#\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7?\2\2\u00b5\66\3\2\2\2\u00b6\u00ba"+
		"\t\6\2\2\u00b7\u00b9\t\7\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb8\3\2\2\2\u00bc\u00ba\3\2\2\2"+
		"\u00bd\u00be\7V\2\2\u00be\u00bf\7t\2\2\u00bf\u00c0\7w\2\2\u00c0\u00c7"+
		"\7g\2\2\u00c1\u00c2\7H\2\2\u00c2\u00c3\7c\2\2\u00c3\u00c4\7n\2\2\u00c4"+
		"\u00c5\7u\2\2\u00c5\u00c7\7g\2\2\u00c6\u00bd\3\2\2\2\u00c6\u00c1\3\2\2"+
		"\2\u00c7:\3\2\2\2\u00c8\u00ca\t\b\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb"+
		"\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00d3\3\2\2\2\u00cd"+
		"\u00cf\7\60\2\2\u00ce\u00d0\t\b\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d1\3"+
		"\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3"+
		"\u00cd\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4<\3\2\2\2\u00d5\u00dc\7$\2\2\u00d6"+
		"\u00db\t\t\2\2\u00d7\u00d8\7y\2\2\u00d8\u00db\7p\2\2\u00d9\u00db\7\17"+
		"\2\2\u00da\u00d6\3\2\2\2\u00da\u00d7\3\2\2\2\u00da\u00d9\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7$\2\2\u00e0>\3\2\2\2\u00e1\u00e6"+
		"\t\n\2\2\u00e2\u00e3\7y\2\2\u00e3\u00e6\7p\2\2\u00e4\u00e6\7\17\2\2\u00e5"+
		"\u00e1\3\2\2\2\u00e5\u00e2\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2"+
		"\2\2\u00e7\u00e8\b \2\2\u00e8@\3\2\2\2\r\2\u00ae\u00b8\u00ba\u00c6\u00cb"+
		"\u00d1\u00d3\u00da\u00dc\u00e5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}