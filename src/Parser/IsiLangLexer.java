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
	import CodeGeneration.CommandIf;
	import CodeGeneration.CommandLeitura;
	import CodeGeneration.CommandWhile;
	import CodeGeneration.IsiProgram;
	import Exceptions.IsiSemanticException;
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.Set;

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
		T__9=10, T__10=11, T__11=12, AP=13, FP=14, AC=15, FC=16, SC=17, DP=18, 
		VR=19, OA=20, OP=21, OR=22, OL=23, NOT=24, ATTR=25, ID=26, BOOLEAN=27, 
		NUMBER=28, STRING=29, WS=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "AP", "FP", "AC", "FC", "SC", "DP", "VR", "OA", 
		"OP", "OR", "OL", "NOT", "ATTR", "ID", "BOOLEAN", "NUMBER", "STRING", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog'", "'leia'", "'escreva'", "'se'", "'senao'", 
		"'enquanto'", "'fa\u00E7a'", "'int'", "'float'", "'bool'", "'str'", "'('", 
		"')'", "'{'", "'}'", "';'", "':'", "','", null, null, null, null, "'!'", 
		"'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "AP", "FP", "AC", "FC", "SC", "DP", "VR", "OA", "OP", "OR", "OL", 
		"NOT", "ATTR", "ID", "BOOLEAN", "NUMBER", "STRING", "WS"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00db\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\5\27\u00a3\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\7\33\u00ad\n"+
		"\33\f\33\16\33\u00b0\13\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\5\34\u00bb\n\34\3\35\6\35\u00be\n\35\r\35\16\35\u00bf\3\35\3\35\6\35"+
		"\u00c4\n\35\r\35\16\35\u00c5\5\35\u00c8\n\35\3\36\3\36\3\36\3\36\7\36"+
		"\u00ce\n\36\f\36\16\36\u00d1\13\36\3\36\3\36\3\37\3\37\3\37\5\37\u00d8"+
		"\n\37\3\37\3\37\2\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= \3\2\13\4\2--//\4\2,,\61\61\4\2>>@@\4\2((~~\3\2"+
		"c|\5\2\62;C\\c|\3\2\62;\7\2\13\f\"\"\62;C\\c|\4\2\13\f\"\"\2\u00e6\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\3?\3\2\2\2\5H\3\2\2\2\7P\3\2\2\2\tU\3\2\2\2\13]\3\2\2\2"+
		"\r`\3\2\2\2\17f\3\2\2\2\21o\3\2\2\2\23t\3\2\2\2\25x\3\2\2\2\27~\3\2\2"+
		"\2\31\u0083\3\2\2\2\33\u0087\3\2\2\2\35\u0089\3\2\2\2\37\u008b\3\2\2\2"+
		"!\u008d\3\2\2\2#\u008f\3\2\2\2%\u0091\3\2\2\2\'\u0093\3\2\2\2)\u0095\3"+
		"\2\2\2+\u0097\3\2\2\2-\u00a2\3\2\2\2/\u00a4\3\2\2\2\61\u00a6\3\2\2\2\63"+
		"\u00a8\3\2\2\2\65\u00aa\3\2\2\2\67\u00ba\3\2\2\29\u00bd\3\2\2\2;\u00c9"+
		"\3\2\2\2=\u00d7\3\2\2\2?@\7r\2\2@A\7t\2\2AB\7q\2\2BC\7i\2\2CD\7t\2\2D"+
		"E\7c\2\2EF\7o\2\2FG\7c\2\2G\4\3\2\2\2HI\7h\2\2IJ\7k\2\2JK\7o\2\2KL\7r"+
		"\2\2LM\7t\2\2MN\7q\2\2NO\7i\2\2O\6\3\2\2\2PQ\7n\2\2QR\7g\2\2RS\7k\2\2"+
		"ST\7c\2\2T\b\3\2\2\2UV\7g\2\2VW\7u\2\2WX\7e\2\2XY\7t\2\2YZ\7g\2\2Z[\7"+
		"x\2\2[\\\7c\2\2\\\n\3\2\2\2]^\7u\2\2^_\7g\2\2_\f\3\2\2\2`a\7u\2\2ab\7"+
		"g\2\2bc\7p\2\2cd\7c\2\2de\7q\2\2e\16\3\2\2\2fg\7g\2\2gh\7p\2\2hi\7s\2"+
		"\2ij\7w\2\2jk\7c\2\2kl\7p\2\2lm\7v\2\2mn\7q\2\2n\20\3\2\2\2op\7h\2\2p"+
		"q\7c\2\2qr\7\u00e9\2\2rs\7c\2\2s\22\3\2\2\2tu\7k\2\2uv\7p\2\2vw\7v\2\2"+
		"w\24\3\2\2\2xy\7h\2\2yz\7n\2\2z{\7q\2\2{|\7c\2\2|}\7v\2\2}\26\3\2\2\2"+
		"~\177\7d\2\2\177\u0080\7q\2\2\u0080\u0081\7q\2\2\u0081\u0082\7n\2\2\u0082"+
		"\30\3\2\2\2\u0083\u0084\7u\2\2\u0084\u0085\7v\2\2\u0085\u0086\7t\2\2\u0086"+
		"\32\3\2\2\2\u0087\u0088\7*\2\2\u0088\34\3\2\2\2\u0089\u008a\7+\2\2\u008a"+
		"\36\3\2\2\2\u008b\u008c\7}\2\2\u008c \3\2\2\2\u008d\u008e\7\177\2\2\u008e"+
		"\"\3\2\2\2\u008f\u0090\7=\2\2\u0090$\3\2\2\2\u0091\u0092\7<\2\2\u0092"+
		"&\3\2\2\2\u0093\u0094\7.\2\2\u0094(\3\2\2\2\u0095\u0096\t\2\2\2\u0096"+
		"*\3\2\2\2\u0097\u0098\t\3\2\2\u0098,\3\2\2\2\u0099\u00a3\t\4\2\2\u009a"+
		"\u009b\7@\2\2\u009b\u00a3\7?\2\2\u009c\u009d\7>\2\2\u009d\u00a3\7?\2\2"+
		"\u009e\u009f\7#\2\2\u009f\u00a3\7?\2\2\u00a0\u00a1\7?\2\2\u00a1\u00a3"+
		"\7?\2\2\u00a2\u0099\3\2\2\2\u00a2\u009a\3\2\2\2\u00a2\u009c\3\2\2\2\u00a2"+
		"\u009e\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3.\3\2\2\2\u00a4\u00a5\t\5\2\2"+
		"\u00a5\60\3\2\2\2\u00a6\u00a7\7#\2\2\u00a7\62\3\2\2\2\u00a8\u00a9\7?\2"+
		"\2\u00a9\64\3\2\2\2\u00aa\u00ae\t\6\2\2\u00ab\u00ad\t\7\2\2\u00ac\u00ab"+
		"\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\66\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7V\2\2\u00b2\u00b3\7t\2\2"+
		"\u00b3\u00b4\7w\2\2\u00b4\u00bb\7g\2\2\u00b5\u00b6\7H\2\2\u00b6\u00b7"+
		"\7c\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2\2\u00b9\u00bb\7g\2\2\u00ba"+
		"\u00b1\3\2\2\2\u00ba\u00b5\3\2\2\2\u00bb8\3\2\2\2\u00bc\u00be\t\b\2\2"+
		"\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c7\3\2\2\2\u00c1\u00c3\7\60\2\2\u00c2\u00c4\t\b\2\2"+
		"\u00c3\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c1\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		":\3\2\2\2\u00c9\u00cf\7$\2\2\u00ca\u00ce\t\t\2\2\u00cb\u00cc\7y\2\2\u00cc"+
		"\u00ce\7p\2\2\u00cd\u00ca\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00d1\3\2"+
		"\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\u00d3\7$\2\2\u00d3<\3\2\2\2\u00d4\u00d8\t\n\2\2\u00d5"+
		"\u00d6\7y\2\2\u00d6\u00d8\7p\2\2\u00d7\u00d4\3\2\2\2\u00d7\u00d5\3\2\2"+
		"\2\u00d8\u00d9\3\2\2\2\u00d9\u00da\b\37\2\2\u00da>\3\2\2\2\r\2\u00a2\u00ac"+
		"\u00ae\u00ba\u00bf\u00c5\u00c7\u00cd\u00cf\u00d7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}