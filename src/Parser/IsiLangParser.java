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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, AP=14, FP=15, AC=16, FC=17, SC=18, 
		DP=19, VR=20, OA=21, OP=22, OR=23, OL=24, NOT=25, ATTR=26, ID=27, BOOLEAN=28, 
		NUMBER=29, STRING=30, WS=31;
	public static final int
		RULE_prog = 0, RULE_bloco = 1, RULE_cmd = 2, RULE_cmdleitura = 3, RULE_cmdescrita = 4, 
		RULE_cmdescritasl = 5, RULE_cmdattrib = 6, RULE_cmdif = 7, RULE_cmdwhile = 8, 
		RULE_cmd_dowhile = 9, RULE_boolean_expr = 10, RULE_cmd_declaracao = 11, 
		RULE_declaravar = 12, RULE_tipo = 13, RULE_expr = 14, RULE_operacao = 15, 
		RULE_fator = 16;
	public static final String[] ruleNames = {
		"prog", "bloco", "cmd", "cmdleitura", "cmdescrita", "cmdescritasl", "cmdattrib", 
		"cmdif", "cmdwhile", "cmd_dowhile", "boolean_expr", "cmd_declaracao", 
		"declaravar", "tipo", "expr", "operacao", "fator"
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

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			bloco();
			setState(36);
			match(T__1);
			setState(37);
			match(SC);

						checkUnusedSymbols();
						program.setComandos(allCommands.pop());
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

				currentThread = new ArrayList<AbstractCommand>();
				allCommands.push(currentThread);

			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				cmd();
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdescritaslContext cmdescritasl() {
			return getRuleContext(CmdescritaslContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdifContext cmdif() {
			return getRuleContext(CmdifContext.class,0);
		}
		public CmdwhileContext cmdwhile() {
			return getRuleContext(CmdwhileContext.class,0);
		}
		public Cmd_dowhileContext cmd_dowhile() {
			return getRuleContext(Cmd_dowhileContext.class,0);
		}
		public Cmd_declaracaoContext cmd_declaracao() {
			return getRuleContext(Cmd_declaracaoContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cmd);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				cmdleitura();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				cmdescrita();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				cmdescritasl();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				cmdattrib();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				cmdif();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 6);
				{
				setState(51);
				cmdwhile();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 7);
				{
				setState(52);
				cmd_dowhile();
				}
				break;
			case T__9:
			case T__10:
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 8);
				{
				setState(53);
				cmd_declaracao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__2);
			setState(57);
			match(AP);
			setState(58);
			match(ID);

							_readId = _input.LT(-1).getText();
							
							verificaDeclaracao(_readId);
							markSymbolAsInitialized(_readId);
							
							DataType _idType = getTypeById(_readId);
						
			setState(60);
			match(FP);
			setState(61);
			match(SC);

							CommandLeitura cmd = new CommandLeitura(_readId, _idType);
							allCommands.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__3);
			 
							cleanExpression();
						
			setState(66);
			match(AP);
			setState(67);
			expr();

							DataType _exprType = verificaTipoExpressao();
						
			setState(69);
			match(FP);
			setState(70);
			match(SC);

							CommandEscrita cmd = new CommandEscrita(_expressionString, _exprType);
							allCommands.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdescritaslContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaslContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescritasl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescritasl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescritasl(this);
		}
	}

	public final CmdescritaslContext cmdescritasl() throws RecognitionException {
		CmdescritaslContext _localctx = new CmdescritaslContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdescritasl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__4);

								cleanExpression();
							
			setState(75);
			match(AP);
			setState(76);
			expr();

								DataType _exprType = verificaTipoExpressao();
							
			setState(78);
			match(FP);
			setState(79);
			match(SC);

								CommandEscritaSL cmd = new CommandEscritaSL(_expressionString, _exprType);
								allCommands.peek().add(cmd);
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(ID);

							String id = _input.LT(-1).getText();
							verificaDeclaracao(id);
							_leftType = getTypeById(id);
							
							cleanExpression();
						
			setState(84);
			match(ATTR);
			setState(85);
			expr();

							_rightType = verificaTipoExpressao();

							if (isExpressionEvaluable(_expressionString, _rightType)) {
								_expressionString = evaluateExpression(_expressionString, _rightType);
							}

							IsiVariable var = (IsiVariable) symbolTable.get(id);
							var.setValue(_expressionString);
						
			setState(87);
			match(SC);

							verificaTiposAttrib(_leftType, _rightType);
					   		
					   		markSymbolAsInitialized(id);
					   		IsiVariable x = (IsiVariable) symbolTable.get(id);
			                x.setValue(_expressionString);
					   		
					   		CommandAttrib cmd = new CommandAttrib(id, _expressionString, _rightType);
					   		allCommands.peek().add(cmd);
					   	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdifContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> AC() { return getTokens(IsiLangParser.AC); }
		public TerminalNode AC(int i) {
			return getToken(IsiLangParser.AC, i);
		}
		public List<TerminalNode> FC() { return getTokens(IsiLangParser.FC); }
		public TerminalNode FC(int i) {
			return getToken(IsiLangParser.FC, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdif(this);
		}
	}

	public final CmdifContext cmdif() throws RecognitionException {
		CmdifContext _localctx = new CmdifContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdif);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__5);
			setState(91);
			match(AP);

						cleanExpression();
						_decisionString = "";
					
			setState(93);
			boolean_expr();
			setState(94);
			match(FP);
			setState(95);
			match(AC);

						currentThread = new ArrayList<AbstractCommand>();
						allCommands.push(currentThread);
					
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				cmd();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(102);
			match(FC);

						listaTrue = allCommands.pop();
					
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(104);
				match(T__6);
				setState(105);
				match(AC);

								currentThread = new ArrayList<AbstractCommand>();
								allCommands.push(currentThread);
							
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(107);
					cmd();
					}
					}
					setState(110); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
				setState(112);
				match(FC);

								listaFalse = allCommands.pop();
							
				}
			}

			setState(117);
			match(SC);

						CommandIf cmd = new CommandIf(_decisionString, listaTrue, listaFalse);
						allCommands.peek().add(cmd);
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdwhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode AC() { return getToken(IsiLangParser.AC, 0); }
		public TerminalNode FC() { return getToken(IsiLangParser.FC, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdwhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdwhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdwhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdwhile(this);
		}
	}

	public final CmdwhileContext cmdwhile() throws RecognitionException {
		CmdwhileContext _localctx = new CmdwhileContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdwhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__7);
			setState(121);
			match(AP);

							cleanExpression();
							_decisionString = "";
						
			setState(123);
			boolean_expr();
			setState(124);
			match(FP);
			setState(125);
			match(AC);

							currentThread = new ArrayList<AbstractCommand>();
							allCommands.push(currentThread);
						
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				cmd();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(132);
			match(FC);

							subLista = allCommands.pop();
						
			setState(134);
			match(SC);

							CommandWhile cmd = new CommandWhile(_decisionString, subLista);
							allCommands.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_dowhileContext extends ParserRuleContext {
		public TerminalNode AC() { return getToken(IsiLangParser.AC, 0); }
		public TerminalNode FC() { return getToken(IsiLangParser.FC, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public Boolean_exprContext boolean_expr() {
			return getRuleContext(Boolean_exprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public Cmd_dowhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_dowhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd_dowhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd_dowhile(this);
		}
	}

	public final Cmd_dowhileContext cmd_dowhile() throws RecognitionException {
		Cmd_dowhileContext _localctx = new Cmd_dowhileContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmd_dowhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(T__8);
			setState(138);
			match(AC);

								currentThread = new ArrayList<AbstractCommand>();
								allCommands.push(currentThread);
							
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				cmd();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(145);
			match(FC);

								subLista = allCommands.pop();
							
			setState(147);
			match(T__7);
			setState(148);
			match(AP);

								cleanExpression();
								_decisionString = "";
							
			setState(150);
			boolean_expr();
			setState(151);
			match(FP);
			setState(152);
			match(SC);

								CommandDoWhile cmd = new CommandDoWhile(_decisionString, subLista);
								allCommands.peek().add(cmd);
							
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_exprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(IsiLangParser.OR, 0); }
		public TerminalNode BOOLEAN() { return getToken(IsiLangParser.BOOLEAN, 0); }
		public Boolean_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBoolean_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBoolean_expr(this);
		}
	}

	public final Boolean_exprContext boolean_expr() throws RecognitionException {
		Boolean_exprContext _localctx = new Boolean_exprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_boolean_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(155);
				expr();

											verificaExpressaoBooleana();
											_leftType = verificaTipoExpressao();

											if (isExpressionEvaluable(_expressionString, _leftType))
												_expressionString = evaluateExpression(_expressionString, _leftType);

											_decisionString += _expressionString;
										
				}
				break;
			case 2:
				{
				setState(158);
				expr();

											_leftType = verificaTipoExpressao();

											if (isExpressionEvaluable(_expressionString, _leftType))
												_expressionString = evaluateExpression(_expressionString, _leftType);

											_exprRel1 = _expressionString;
											
											_decisionString += _expressionString;
										
				setState(160);
				match(OR);

											_opRel = _input.LT(-1).getText();
											
											_decisionString += _input.LT(-1).getText();
											
											cleanExpression();
										
				setState(162);
				expr();

											_rightType = verificaTipoExpressao();

											if (isExpressionEvaluable(_expressionString, _rightType))
												_expressionString = evaluateExpression(_expressionString, _rightType);

											_exprRel2 = _expressionString;
											
											checkRelationalOperation(_leftType, _rightType, _exprRel1, _exprRel2, _opRel);
												
											_decisionString += _expressionString;
										
				}
				break;
			case 3:
				{
				setState(165);
				match(BOOLEAN);

										_decisionString += _input.LT(-1).getText();
									
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declaracaoContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public Cmd_declaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd_declaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd_declaracao(this);
		}
	}

	public final Cmd_declaracaoContext cmd_declaracao() throws RecognitionException {
		Cmd_declaracaoContext _localctx = new Cmd_declaracaoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmd_declaracao);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(170); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(169);
					declaravar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(172); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			tipo();
			setState(175);
			match(ID);

							_varName = _input.LT(-1).getText();
							_tipo = _input.LT(-2).getText();
							_varValue = null;
							_leftType = getType(_tipo);

							cleanExpression();
						
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATTR) {
				{
				setState(177);
				match(ATTR);
				setState(178);
				expr();

									_rightType = verificaTipoExpressao();
									verificaTiposAttrib(_leftType, _rightType);
									markSymbolAsInitialized(_varName);
								
									if (isExpressionEvaluable(_expressionString, _leftType))
										_expressionString = evaluateExpression(_expressionString, _leftType);

									_varValue = _expressionString;
								
				}
			}

			setState(183);
			match(SC);

							if (!symbolTable.exists(_varName)) {
								_symbol = new IsiVariable(_varName, _tipo, _varValue);
								symbolTable.add(_symbol);
							} else {
								throw new IsiSemanticException("Símbolo "+_varName+" já foi declarado.");
							}
							
							CommandDeclaracao cmd = new CommandDeclaracao(_tipo, _varName, _varValue);
							allCommands.peek().add(cmd);
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TerminalNode NOT() { return getToken(IsiLangParser.NOT, 0); }
		public List<OperacaoContext> operacao() {
			return getRuleContexts(OperacaoContext.class);
		}
		public OperacaoContext operacao(int i) {
			return getRuleContext(OperacaoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(188);
				match(NOT);

							_expressionString = _expressionString + "!";
							expressionOperations.add(_input.LT(-1).getText());
						
				}
			}

			setState(192);
			fator();
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(193);
					operacao();

									String op = _input.LT(-1).getText();
									_expressionString = _expressionString + op;
									expressionOperations.add(_input.LT(-1).getText());
								
					setState(195);
					expr();
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperacaoContext extends ParserRuleContext {
		public TerminalNode OA() { return getToken(IsiLangParser.OA, 0); }
		public TerminalNode OP() { return getToken(IsiLangParser.OP, 0); }
		public TerminalNode OL() { return getToken(IsiLangParser.OL, 0); }
		public OperacaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterOperacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitOperacao(this);
		}
	}

	public final OperacaoContext operacao() throws RecognitionException {
		OperacaoContext _localctx = new OperacaoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_operacao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OA) | (1L << OP) | (1L << OL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(IsiLangParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_fator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(204);
				match(ID);

							 	String id = _input.LT(-1).getText();
							 	checkDeclarationAndInitialization(id);
							 	DataType idType = getTypeById(id);
							 	
							 	_expressionString = _expressionString + id;
							 	expressionTypes.add(idType);
							 
				}
				break;
			case NUMBER:
				{
				setState(206);
				match(NUMBER);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		DataType numberType = getNumberType(fator);
						   		expressionTypes.add(numberType);
						   
				}
				break;
			case BOOLEAN:
				{
				setState(208);
				match(BOOLEAN);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		expressionTypes.add(DataType.BOOL);
						   
				}
				break;
			case STRING:
				{
				setState(210);
				match(STRING);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		expressionTypes.add(DataType.STR);
						   
				}
				break;
			case AP:
				{
				setState(212);
				match(AP);

						   		_expressionString = _expressionString + "(";
						   	 
				setState(214);
				expr();
				setState(215);
				match(FP);

						   	 	_expressionString = _expressionString + ")";	
						     
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00df\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\6\3-\n\3\r\3\16\3.\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\49\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\te\n\t\r\t\16\t"+
		"f\3\t\3\t\3\t\3\t\3\t\3\t\6\to\n\t\r\t\16\tp\3\t\3\t\3\t\5\tv\n\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0083\n\n\r\n\16\n\u0084"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\6\13\u0090\n\13\r\13\16\13\u0091"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00aa\n\f\3\r\6\r\u00ad\n\r\r\r\16\r"+
		"\u00ae\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00b8\n\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\20\3\20\5\20\u00c1\n\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u00c8\n\20\f\20\16\20\u00cb\13\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00dd\n\22\3\22\2\2"+
		"\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\4\3\2\f\17\4\2\27\30\32"+
		"\32\2\u00e4\2$\3\2\2\2\4*\3\2\2\2\68\3\2\2\2\b:\3\2\2\2\nB\3\2\2\2\fK"+
		"\3\2\2\2\16T\3\2\2\2\20\\\3\2\2\2\22z\3\2\2\2\24\u008b\3\2\2\2\26\u00a9"+
		"\3\2\2\2\30\u00ac\3\2\2\2\32\u00b0\3\2\2\2\34\u00bc\3\2\2\2\36\u00c0\3"+
		"\2\2\2 \u00cc\3\2\2\2\"\u00dc\3\2\2\2$%\7\3\2\2%&\5\4\3\2&\'\7\4\2\2\'"+
		"(\7\24\2\2()\b\2\1\2)\3\3\2\2\2*,\b\3\1\2+-\5\6\4\2,+\3\2\2\2-.\3\2\2"+
		"\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\609\5\b\5\2\619\5\n\6\2\629\5\f\7\2"+
		"\639\5\16\b\2\649\5\20\t\2\659\5\22\n\2\669\5\24\13\2\679\5\30\r\28\60"+
		"\3\2\2\28\61\3\2\2\28\62\3\2\2\28\63\3\2\2\28\64\3\2\2\28\65\3\2\2\28"+
		"\66\3\2\2\28\67\3\2\2\29\7\3\2\2\2:;\7\5\2\2;<\7\20\2\2<=\7\35\2\2=>\b"+
		"\5\1\2>?\7\21\2\2?@\7\24\2\2@A\b\5\1\2A\t\3\2\2\2BC\7\6\2\2CD\b\6\1\2"+
		"DE\7\20\2\2EF\5\36\20\2FG\b\6\1\2GH\7\21\2\2HI\7\24\2\2IJ\b\6\1\2J\13"+
		"\3\2\2\2KL\7\7\2\2LM\b\7\1\2MN\7\20\2\2NO\5\36\20\2OP\b\7\1\2PQ\7\21\2"+
		"\2QR\7\24\2\2RS\b\7\1\2S\r\3\2\2\2TU\7\35\2\2UV\b\b\1\2VW\7\34\2\2WX\5"+
		"\36\20\2XY\b\b\1\2YZ\7\24\2\2Z[\b\b\1\2[\17\3\2\2\2\\]\7\b\2\2]^\7\20"+
		"\2\2^_\b\t\1\2_`\5\26\f\2`a\7\21\2\2ab\7\22\2\2bd\b\t\1\2ce\5\6\4\2dc"+
		"\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\7\23\2\2iu\b\t\1\2"+
		"jk\7\t\2\2kl\7\22\2\2ln\b\t\1\2mo\5\6\4\2nm\3\2\2\2op\3\2\2\2pn\3\2\2"+
		"\2pq\3\2\2\2qr\3\2\2\2rs\7\23\2\2st\b\t\1\2tv\3\2\2\2uj\3\2\2\2uv\3\2"+
		"\2\2vw\3\2\2\2wx\7\24\2\2xy\b\t\1\2y\21\3\2\2\2z{\7\n\2\2{|\7\20\2\2|"+
		"}\b\n\1\2}~\5\26\f\2~\177\7\21\2\2\177\u0080\7\22\2\2\u0080\u0082\b\n"+
		"\1\2\u0081\u0083\5\6\4\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\23"+
		"\2\2\u0087\u0088\b\n\1\2\u0088\u0089\7\24\2\2\u0089\u008a\b\n\1\2\u008a"+
		"\23\3\2\2\2\u008b\u008c\7\13\2\2\u008c\u008d\7\22\2\2\u008d\u008f\b\13"+
		"\1\2\u008e\u0090\5\6\4\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\7\23"+
		"\2\2\u0094\u0095\b\13\1\2\u0095\u0096\7\n\2\2\u0096\u0097\7\20\2\2\u0097"+
		"\u0098\b\13\1\2\u0098\u0099\5\26\f\2\u0099\u009a\7\21\2\2\u009a\u009b"+
		"\7\24\2\2\u009b\u009c\b\13\1\2\u009c\25\3\2\2\2\u009d\u009e\5\36\20\2"+
		"\u009e\u009f\b\f\1\2\u009f\u00aa\3\2\2\2\u00a0\u00a1\5\36\20\2\u00a1\u00a2"+
		"\b\f\1\2\u00a2\u00a3\7\31\2\2\u00a3\u00a4\b\f\1\2\u00a4\u00a5\5\36\20"+
		"\2\u00a5\u00a6\b\f\1\2\u00a6\u00aa\3\2\2\2\u00a7\u00a8\7\36\2\2\u00a8"+
		"\u00aa\b\f\1\2\u00a9\u009d\3\2\2\2\u00a9\u00a0\3\2\2\2\u00a9\u00a7\3\2"+
		"\2\2\u00aa\27\3\2\2\2\u00ab\u00ad\5\32\16\2\u00ac\u00ab\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\31\3\2\2"+
		"\2\u00b0\u00b1\5\34\17\2\u00b1\u00b2\7\35\2\2\u00b2\u00b7\b\16\1\2\u00b3"+
		"\u00b4\7\34\2\2\u00b4\u00b5\5\36\20\2\u00b5\u00b6\b\16\1\2\u00b6\u00b8"+
		"\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\7\24\2\2\u00ba\u00bb\b\16\1\2\u00bb\33\3\2\2\2\u00bc\u00bd\t\2"+
		"\2\2\u00bd\35\3\2\2\2\u00be\u00bf\7\33\2\2\u00bf\u00c1\b\20\1\2\u00c0"+
		"\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c9\5\""+
		"\22\2\u00c3\u00c4\5 \21\2\u00c4\u00c5\b\20\1\2\u00c5\u00c6\5\36\20\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00c3\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9\u00c7\3\2"+
		"\2\2\u00c9\u00ca\3\2\2\2\u00ca\37\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd"+
		"\t\3\2\2\u00cd!\3\2\2\2\u00ce\u00cf\7\35\2\2\u00cf\u00dd\b\22\1\2\u00d0"+
		"\u00d1\7\37\2\2\u00d1\u00dd\b\22\1\2\u00d2\u00d3\7\36\2\2\u00d3\u00dd"+
		"\b\22\1\2\u00d4\u00d5\7 \2\2\u00d5\u00dd\b\22\1\2\u00d6\u00d7\7\20\2\2"+
		"\u00d7\u00d8\b\22\1\2\u00d8\u00d9\5\36\20\2\u00d9\u00da\7\21\2\2\u00da"+
		"\u00db\b\22\1\2\u00db\u00dd\3\2\2\2\u00dc\u00ce\3\2\2\2\u00dc\u00d0\3"+
		"\2\2\2\u00dc\u00d2\3\2\2\2\u00dc\u00d4\3\2\2\2\u00dc\u00d6\3\2\2\2\u00dd"+
		"#\3\2\2\2\17.8fpu\u0084\u0091\u00a9\u00ae\u00b7\u00c0\u00c9\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}