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
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				cmdleitura();
				 System.out.println("Reconheci um comando de leitura."); 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				cmdescrita();
				 System.out.println("Reconheci um comando de escrita."); 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				cmdescritasl();
				 System.out.println("Reconheci um comando de escrita na mesma linha."); 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(55);
				cmdattrib();
				 System.out.println("Reconheci um comando de atribuição."); 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				cmdif();
				 System.out.println("Reconheci um comando if."); 
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				cmdwhile();
				 System.out.println("Reconheci um comando while."); 
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 7);
				{
				setState(64);
				cmd_dowhile();
				 System.out.println("Reconheci um comando dowhile."); 
				}
				break;
			case T__9:
			case T__10:
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 8);
				{
				setState(67);
				cmd_declaracao();
				 System.out.println("Reconheci uma declaração de variável."); 
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
			setState(72);
			match(T__2);
			setState(73);
			match(AP);
			setState(74);
			match(ID);

							_readId = _input.LT(-1).getText();
							
							verificaDeclaracao(_readId);
							markSymbolAsInitialized(_readId);
							
							DataType _idType = getTypeById(_readId);
						
			setState(76);
			match(FP);
			setState(77);
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
			setState(80);
			match(T__3);
			 
							cleanExpression();
						
			setState(82);
			match(AP);
			setState(83);
			expr();

							DataType _exprType = verificaTipoExpressao();
						
			setState(85);
			match(FP);
			setState(86);
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
			setState(89);
			match(T__4);

								cleanExpression();
							
			setState(91);
			match(AP);
			setState(92);
			expr();

								DataType _exprType = verificaTipoExpressao();
							
			setState(94);
			match(FP);
			setState(95);
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
			setState(98);
			match(ID);

							String id = _input.LT(-1).getText();
							verificaDeclaracao(id);
							_leftType = getTypeById(id);
							
							cleanExpression();
						
			setState(100);
			match(ATTR);
			setState(101);
			expr();

							_rightType = verificaTipoExpressao();
						
			setState(103);
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
			setState(106);
			match(T__5);
			setState(107);
			match(AP);

						cleanExpression();
						_decisionString = "";
					
			setState(109);
			boolean_expr();
			setState(110);
			match(FP);
			setState(111);
			match(AC);

						currentThread = new ArrayList<AbstractCommand>();
						allCommands.push(currentThread);
					
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				cmd();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(118);
			match(FC);

						listaTrue = allCommands.pop();
					
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(120);
				match(T__6);
				setState(121);
				match(AC);

								currentThread = new ArrayList<AbstractCommand>();
								allCommands.push(currentThread);
							
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123);
					cmd();
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
				setState(128);
				match(FC);

								listaFalse = allCommands.pop();
							
				}
			}

			setState(133);
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
			setState(136);
			match(T__7);
			setState(137);
			match(AP);

							cleanExpression();
							_decisionString = "";
						
			setState(139);
			boolean_expr();
			setState(140);
			match(FP);
			setState(141);
			match(AC);

							currentThread = new ArrayList<AbstractCommand>();
							allCommands.push(currentThread);
						
			setState(144); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(143);
				cmd();
				}
				}
				setState(146); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(148);
			match(FC);

							subLista = allCommands.pop();
						
			setState(150);
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
			setState(153);
			match(T__8);
			setState(154);
			match(AC);

								currentThread = new ArrayList<AbstractCommand>();
								allCommands.push(currentThread);
							
			setState(157); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156);
				cmd();
				}
				}
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << ID))) != 0) );
			setState(161);
			match(FC);

								subLista = allCommands.pop();
							
			setState(163);
			match(T__7);
			setState(164);
			match(AP);

								cleanExpression();
								_decisionString = "";
							
			setState(166);
			boolean_expr();
			setState(167);
			match(FP);
			setState(168);
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
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(171);
				expr();

											verificaExpressaoBooleana();
											_decisionString += _expressionString;
										
				}
				break;
			case 2:
				{
				setState(174);
				expr();

											_leftType = verificaTipoExpressao();
											_exprRel1 = _expressionString;
											
											_decisionString += _expressionString;
										
				setState(176);
				match(OR);

											_opRel = _input.LT(-1).getText();
											
											_decisionString += _input.LT(-1).getText();
											
											cleanExpression();
										
				setState(178);
				expr();

											_rightType = verificaTipoExpressao();
											_exprRel2 = _expressionString;
											
											checkRelationalOperation(_leftType, _rightType, _exprRel1, _exprRel2, _opRel);
												
											_decisionString += _expressionString;
										
				}
				break;
			case 3:
				{
				setState(181);
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
			setState(186); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(185);
					declaravar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(188); 
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
			setState(190);
			tipo();
			setState(191);
			match(ID);

							_varName = _input.LT(-1).getText();
							_tipo = _input.LT(-2).getText();
							_varValue = null;
							_leftType = getType(_tipo);
							
							cleanExpression();
						
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATTR) {
				{
				setState(193);
				match(ATTR);
				setState(194);
				expr();

									_rightType = verificaTipoExpressao();
									verificaTiposAttrib(_leftType, _rightType);
									
									_varValue = _expressionString;
								
				}
			}

			setState(199);
			match(SC);

							if (!symbolTable.exists(_varName)) {
								_symbol = new IsiVariable(_varName, _tipo, _varValue);
								System.out.println("Simbolo adicionado "+_symbol);
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
			setState(202);
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
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(204);
				match(NOT);

							_expressionString = _expressionString + "!";
							expressionOperations.add(_input.LT(-1).getText());
						
				}
			}

			setState(208);
			fator();
			setState(215);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(209);
					operacao();

									String op = _input.LT(-1).getText();
									_expressionString = _expressionString + op;
									expressionOperations.add(_input.LT(-1).getText());
								
					setState(211);
					expr();
					}
					} 
				}
				setState(217);
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
			setState(218);
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
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(220);
				match(ID);

							 	String id = _input.LT(-1).getText();
							 	checkDeclarationAndInitialization(id);
							 	DataType idType = getTypeById(id);
							 	
							 	_expressionString = _expressionString + id;
							 	expressionTypes.add(idType);
							 	System.out.println("Fator ID detectado "+id+" com tipo "+idType);
							 
				}
				break;
			case NUMBER:
				{
				setState(222);
				match(NUMBER);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		DataType numberType = getNumberType(fator);
						   		expressionTypes.add(numberType);
						   		System.out.println("Fator NUMBER detectado "+fator+" com tipo "+numberType);
						   
				}
				break;
			case BOOLEAN:
				{
				setState(224);
				match(BOOLEAN);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		expressionTypes.add(DataType.BOOL);
						   		System.out.println("Fator BOOL detectado "+fator);
						   
				}
				break;
			case STRING:
				{
				setState(226);
				match(STRING);

						   		String fator = _input.LT(-1).getText();
						   		_expressionString = _expressionString + fator;
						   		expressionTypes.add(DataType.STR);
						   		System.out.println("Fator STRING detectado "+fator);
						   
				}
				break;
			case AP:
				{
				setState(228);
				match(AP);

						   		_expressionString = _expressionString + "(";
						   	 
				setState(230);
				expr();
				setState(231);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00ef\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\6\3-\n\3\r\3\16\3.\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4I\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\6\tu\n\t\r\t\16\tv\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\6\t\177\n\t\r\t\16\t\u0080\3\t\3\t\3\t\5\t\u0086"+
		"\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0093\n\n\r\n\16"+
		"\n\u0094\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\6\13\u00a0\n\13\r\13"+
		"\16\13\u00a1\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ba\n\f\3\r\6\r\u00bd\n"+
		"\r\r\r\16\r\u00be\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00c8\n\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\20\3\20\5\20\u00d1\n\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u00d8\n\20\f\20\16\20\u00db\13\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00ed\n\22"+
		"\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\4\3\2\f\17\4"+
		"\2\27\30\32\32\2\u00f4\2$\3\2\2\2\4*\3\2\2\2\6H\3\2\2\2\bJ\3\2\2\2\nR"+
		"\3\2\2\2\f[\3\2\2\2\16d\3\2\2\2\20l\3\2\2\2\22\u008a\3\2\2\2\24\u009b"+
		"\3\2\2\2\26\u00b9\3\2\2\2\30\u00bc\3\2\2\2\32\u00c0\3\2\2\2\34\u00cc\3"+
		"\2\2\2\36\u00d0\3\2\2\2 \u00dc\3\2\2\2\"\u00ec\3\2\2\2$%\7\3\2\2%&\5\4"+
		"\3\2&\'\7\4\2\2\'(\7\24\2\2()\b\2\1\2)\3\3\2\2\2*,\b\3\1\2+-\5\6\4\2,"+
		"+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60\61\5\b\5\2\61\62"+
		"\b\4\1\2\62I\3\2\2\2\63\64\5\n\6\2\64\65\b\4\1\2\65I\3\2\2\2\66\67\5\f"+
		"\7\2\678\b\4\1\28I\3\2\2\29:\5\16\b\2:;\b\4\1\2;I\3\2\2\2<=\5\20\t\2="+
		">\b\4\1\2>I\3\2\2\2?@\5\22\n\2@A\b\4\1\2AI\3\2\2\2BC\5\24\13\2CD\b\4\1"+
		"\2DI\3\2\2\2EF\5\30\r\2FG\b\4\1\2GI\3\2\2\2H\60\3\2\2\2H\63\3\2\2\2H\66"+
		"\3\2\2\2H9\3\2\2\2H<\3\2\2\2H?\3\2\2\2HB\3\2\2\2HE\3\2\2\2I\7\3\2\2\2"+
		"JK\7\5\2\2KL\7\20\2\2LM\7\35\2\2MN\b\5\1\2NO\7\21\2\2OP\7\24\2\2PQ\b\5"+
		"\1\2Q\t\3\2\2\2RS\7\6\2\2ST\b\6\1\2TU\7\20\2\2UV\5\36\20\2VW\b\6\1\2W"+
		"X\7\21\2\2XY\7\24\2\2YZ\b\6\1\2Z\13\3\2\2\2[\\\7\7\2\2\\]\b\7\1\2]^\7"+
		"\20\2\2^_\5\36\20\2_`\b\7\1\2`a\7\21\2\2ab\7\24\2\2bc\b\7\1\2c\r\3\2\2"+
		"\2de\7\35\2\2ef\b\b\1\2fg\7\34\2\2gh\5\36\20\2hi\b\b\1\2ij\7\24\2\2jk"+
		"\b\b\1\2k\17\3\2\2\2lm\7\b\2\2mn\7\20\2\2no\b\t\1\2op\5\26\f\2pq\7\21"+
		"\2\2qr\7\22\2\2rt\b\t\1\2su\5\6\4\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3"+
		"\2\2\2wx\3\2\2\2xy\7\23\2\2y\u0085\b\t\1\2z{\7\t\2\2{|\7\22\2\2|~\b\t"+
		"\1\2}\177\5\6\4\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081"+
		"\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\23\2\2\u0083\u0084\b\t\1\2"+
		"\u0084\u0086\3\2\2\2\u0085z\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0087\u0088\7\24\2\2\u0088\u0089\b\t\1\2\u0089\21\3\2\2\2\u008a"+
		"\u008b\7\n\2\2\u008b\u008c\7\20\2\2\u008c\u008d\b\n\1\2\u008d\u008e\5"+
		"\26\f\2\u008e\u008f\7\21\2\2\u008f\u0090\7\22\2\2\u0090\u0092\b\n\1\2"+
		"\u0091\u0093\5\6\4\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0092"+
		"\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\7\23\2\2"+
		"\u0097\u0098\b\n\1\2\u0098\u0099\7\24\2\2\u0099\u009a\b\n\1\2\u009a\23"+
		"\3\2\2\2\u009b\u009c\7\13\2\2\u009c\u009d\7\22\2\2\u009d\u009f\b\13\1"+
		"\2\u009e\u00a0\5\6\4\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\23\2\2"+
		"\u00a4\u00a5\b\13\1\2\u00a5\u00a6\7\n\2\2\u00a6\u00a7\7\20\2\2\u00a7\u00a8"+
		"\b\13\1\2\u00a8\u00a9\5\26\f\2\u00a9\u00aa\7\21\2\2\u00aa\u00ab\7\24\2"+
		"\2\u00ab\u00ac\b\13\1\2\u00ac\25\3\2\2\2\u00ad\u00ae\5\36\20\2\u00ae\u00af"+
		"\b\f\1\2\u00af\u00ba\3\2\2\2\u00b0\u00b1\5\36\20\2\u00b1\u00b2\b\f\1\2"+
		"\u00b2\u00b3\7\31\2\2\u00b3\u00b4\b\f\1\2\u00b4\u00b5\5\36\20\2\u00b5"+
		"\u00b6\b\f\1\2\u00b6\u00ba\3\2\2\2\u00b7\u00b8\7\36\2\2\u00b8\u00ba\b"+
		"\f\1\2\u00b9\u00ad\3\2\2\2\u00b9\u00b0\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba"+
		"\27\3\2\2\2\u00bb\u00bd\5\32\16\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\31\3\2\2\2\u00c0\u00c1"+
		"\5\34\17\2\u00c1\u00c2\7\35\2\2\u00c2\u00c7\b\16\1\2\u00c3\u00c4\7\34"+
		"\2\2\u00c4\u00c5\5\36\20\2\u00c5\u00c6\b\16\1\2\u00c6\u00c8\3\2\2\2\u00c7"+
		"\u00c3\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\7\24"+
		"\2\2\u00ca\u00cb\b\16\1\2\u00cb\33\3\2\2\2\u00cc\u00cd\t\2\2\2\u00cd\35"+
		"\3\2\2\2\u00ce\u00cf\7\33\2\2\u00cf\u00d1\b\20\1\2\u00d0\u00ce\3\2\2\2"+
		"\u00d0\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d9\5\"\22\2\u00d3\u00d4"+
		"\5 \21\2\u00d4\u00d5\b\20\1\2\u00d5\u00d6\5\36\20\2\u00d6\u00d8\3\2\2"+
		"\2\u00d7\u00d3\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\37\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00dd\t\3\2\2\u00dd"+
		"!\3\2\2\2\u00de\u00df\7\35\2\2\u00df\u00ed\b\22\1\2\u00e0\u00e1\7\37\2"+
		"\2\u00e1\u00ed\b\22\1\2\u00e2\u00e3\7\36\2\2\u00e3\u00ed\b\22\1\2\u00e4"+
		"\u00e5\7 \2\2\u00e5\u00ed\b\22\1\2\u00e6\u00e7\7\20\2\2\u00e7\u00e8\b"+
		"\22\1\2\u00e8\u00e9\5\36\20\2\u00e9\u00ea\7\21\2\2\u00ea\u00eb\b\22\1"+
		"\2\u00eb\u00ed\3\2\2\2\u00ec\u00de\3\2\2\2\u00ec\u00e0\3\2\2\2\u00ec\u00e2"+
		"\3\2\2\2\u00ec\u00e4\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ed#\3\2\2\2\17.Hv"+
		"\u0080\u0085\u0094\u00a1\u00b9\u00be\u00c7\u00d0\u00d9\u00ec";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}