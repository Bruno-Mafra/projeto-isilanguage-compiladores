grammar IsiLang;

@header{
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
}

@members{
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
}

/*
 * java -cp antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package Parser -o ./src/Parser/
 */

prog: 	'programa'	
		bloco	
		'fimprog' 
		SC {
			checkUnusedSymbols();
			program.setComandos(allCommands.pop());
		};

bloco: 	{
	currentThread = new ArrayList<AbstractCommand>();
	allCommands.push(currentThread);
}
		(cmd)+; 

cmd:  cmdleitura { System.out.println("Reconheci um comando de leitura."); }
	| cmdescrita { System.out.println("Reconheci um comando de escrita."); }
	| cmdescritasl { System.out.println("Reconheci um comando de escrita na mesma linha."); }
	| cmdattrib  { System.out.println("Reconheci um comando de atribuição."); }
	| cmdif 	 { System.out.println("Reconheci um comando if."); }
	| cmdwhile	 { System.out.println("Reconheci um comando while."); }
	| cmd_dowhile { System.out.println("Reconheci um comando dowhile."); }
	| cmd_declaracao { System.out.println("Reconheci uma declaração de variável."); };

	
cmdleitura: 'leia' 
			AP 
			ID {
				_readId = _input.LT(-1).getText();
				
				verificaDeclaracao(_readId);
				markSymbolAsInitialized(_readId);
				
				DataType _idType = getTypeById(_readId);
			} 
			FP 
			SC {
				CommandLeitura cmd = new CommandLeitura(_readId, _idType);
				allCommands.peek().add(cmd);
			};
			
			
cmdescrita: 'escreva' { 
				cleanExpression();
			}
			AP 
			expr {
				DataType _exprType = verificaTipoExpressao();
			}
			FP 
			SC {
				CommandEscrita cmd = new CommandEscrita(_expressionString, _exprType);
				allCommands.peek().add(cmd);
			};
			
cmdescritasl: 	'escrevasl' {
					cleanExpression();
				}
				AP
				expr {
					DataType _exprType = verificaTipoExpressao();
				}
				FP
				SC {
					CommandEscritaSL cmd = new CommandEscritaSL(_expressionString, _exprType);
					allCommands.peek().add(cmd);
				};
			
cmdattrib:  ID {
				String id = _input.LT(-1).getText();
				verificaDeclaracao(id);
				_leftType = getTypeById(id);
				
				cleanExpression();
			}
		   	ATTR 
		   	expr {
				_rightType = verificaTipoExpressao();

				if (isExpressionEvaluable(_expressionString, _rightType)) {
					_expressionString = evaluateExpression(_expressionString, _rightType);
				}

				IsiVariable var = (IsiVariable) symbolTable.get(id);
				var.setValue(_expressionString);
			}
		   	SC {
				verificaTiposAttrib(_leftType, _rightType);
		   		
		   		markSymbolAsInitialized(id);
		   		IsiVariable x = (IsiVariable) symbolTable.get(id);
                x.setValue(_expressionString);
		   		
		   		CommandAttrib cmd = new CommandAttrib(id, _expressionString, _rightType);
		   		allCommands.peek().add(cmd);
		   	};
		   	
cmdif: 	'se' 
		AP {
			cleanExpression();
			_decisionString = "";
		} 
		boolean_expr
		FP 
		AC {
			currentThread = new ArrayList<AbstractCommand>();
			allCommands.push(currentThread);
		}
		(cmd)+ 
		FC {
			listaTrue = allCommands.pop();
		}
		(
			'senao' 
			AC {
				currentThread = new ArrayList<AbstractCommand>();
				allCommands.push(currentThread);
			}
			(cmd)+ 
			FC {
				listaFalse = allCommands.pop();
			}
		)? 
		SC {
			CommandIf cmd = new CommandIf(_decisionString, listaTrue, listaFalse);
			allCommands.peek().add(cmd);
		};
		
cmdwhile: 	'enquanto' 
			AP {
				cleanExpression();
				_decisionString = "";
			}
			boolean_expr 
			FP 
			AC {
				currentThread = new ArrayList<AbstractCommand>();
				allCommands.push(currentThread);
			}
			(cmd)+ 
			FC {
				subLista = allCommands.pop();
			}
			SC {
				CommandWhile cmd = new CommandWhile(_decisionString, subLista);
				allCommands.peek().add(cmd);
			};
			
cmd_dowhile:	'faça' 
				AC {
					currentThread = new ArrayList<AbstractCommand>();
					allCommands.push(currentThread);
				}
				(cmd)+ 
				FC {
					subLista = allCommands.pop();
				}
				'enquanto' 
				AP {
					cleanExpression();
					_decisionString = "";
				}
				boolean_expr
				FP
				SC {
					CommandDoWhile cmd = new CommandDoWhile(_decisionString, subLista);
					allCommands.peek().add(cmd);
				};

/*
 * Uma boolean_expr pode ser tanto uma expressão isolada do tipo bool, isto é, contendo
 * fatores atômicos do tipo bool e operadores lógicos, quanto pode ser uma operação relacional
 * envolvendo duas expressões normais.
 * 
 * Em primeiro lugar, em todos os casos será verificado se a expressão é sequer válida, isto é, se os tipos
 * são compatíveis e se os operadores são compatíveis com o tipo.
 * 
 * A função verificaExpressaoBooleana, em particular, verifica se o tipo da expressão é "bool", e só é chamada
 * no primeiro cenário, o da expressão isolada.
 * 
 * No caso da expressão relacional, é necessário se as expressões dos dois lados da relação são de tipos compatíveis, 
 * afinal não faz sentido comparar se uma string é maior que um inteiro. Ademais, optamos por limitar o que é uma expressão 
 * relacional válida, reconhecendo que apenas expressões numéricas podem ser relacionadas.
 * 
 */

boolean_expr:	(
					    expr {
							verificaExpressaoBooleana();
							_leftType = verificaTipoExpressao();

							if (isExpressionEvaluable(_expressionString, _leftType))
								_expressionString = evaluateExpression(_expressionString, _leftType);

							_decisionString += _expressionString;
						}
					| 	expr {
							_leftType = verificaTipoExpressao();

							if (isExpressionEvaluable(_expressionString, _leftType))
								_expressionString = evaluateExpression(_expressionString, _leftType);

							_exprRel1 = _expressionString;
							
							_decisionString += _expressionString;
						} 
						OR {
							_opRel = _input.LT(-1).getText();
							
							_decisionString += _input.LT(-1).getText();
							
							cleanExpression();
						}
						expr {
							_rightType = verificaTipoExpressao();

							if (isExpressionEvaluable(_expressionString, _rightType))
								_expressionString = evaluateExpression(_expressionString, _rightType);

							_exprRel2 = _expressionString;
							
							checkRelationalOperation(_leftType, _rightType, _exprRel1, _exprRel2, _opRel);
								
							_decisionString += _expressionString;
						}
					| BOOLEAN {
						_decisionString += _input.LT(-1).getText();
					}
				);
				
/*
 * Limitamos o comando de declaração a fim de que seja feita apenas uma declaração por linha. 
 * Por essa estratégia fica mais fácil realizar a geração de código para outras linguagens, tal como 
 * realizar uma atribuição de valor na própria declaração.
 * 
 */
cmd_declaracao: (declaravar)+;
declaravar: tipo
			ID {
				_varName = _input.LT(-1).getText();
				_tipo = _input.LT(-2).getText();
				_varValue = null;
				_leftType = getType(_tipo);

				cleanExpression();
			}
			(
				ATTR 
				expr {
					_rightType = verificaTipoExpressao();
					verificaTiposAttrib(_leftType, _rightType);
					markSymbolAsInitialized(_varName);
				
					if (isExpressionEvaluable(_expressionString, _leftType))
						_expressionString = evaluateExpression(_expressionString, _leftType);

					_varValue = _expressionString;
				}
			)? 
			SC {
				if (!symbolTable.exists(_varName)) {
					_symbol = new IsiVariable(_varName, _tipo, _varValue);
					System.out.println("Simbolo adicionado "+_symbol + " com valor: " + _symbol.getValue());
					symbolTable.add(_symbol);
				} else {
					throw new IsiSemanticException("Símbolo "+_varName+" já foi declarado.");
				}
				
				CommandDeclaracao cmd = new CommandDeclaracao(_tipo, _varName, _varValue);
				allCommands.peek().add(cmd);
			};
			
tipo: 'int' | 'float' | 'bool' | 'str';

/*
 * Uma expressão é definida como um fator opcionalmente seguido por um ou mais pares de operador e expressão.
 * 
 * Um fator pode ser ID, número, bool ou outra expressão entre parenteses a fim de permitir incluir precedências 
 * na expressão.
 * 
 * Essa expressão é portanto genérica, envolve expressões com strings, ou numéricas, ou booleanas.
 * 
 * Para garantir a corretude da expressão duas verificações são feitas. A função verificaTipoExpressao verifica
 * se todos os fatores atômicos são de tipos compatíveis, e se não forem será gerada uma semantic exception. Uma 
 * segunda verificação detecta se os operadores da expressão são compatíveis com o tipo da expressão - por exemplo, 
 * operadores lógicos não fazem sentido em expressões com strings; isso é feito pela função verificaExpressaoValida.
 * 
 * Estas duas funções de verificação são chamadas sempre que for detectado uma 'expr'.
 */

expr: 	(NOT {
			_expressionString = _expressionString + "!";
			expressionOperations.add(_input.LT(-1).getText());
		})? 
		fator 
		(
			operacao {
				String op = _input.LT(-1).getText();
				_expressionString = _expressionString + op;
				expressionOperations.add(_input.LT(-1).getText());
			}
			expr
		)*;
		
operacao: OA | OP | OL;

fator: 	(
			 ID {
			 	String id = _input.LT(-1).getText();
			 	checkDeclarationAndInitialization(id);
			 	DataType idType = getTypeById(id);
			 	
			 	_expressionString = _expressionString + id;
			 	expressionTypes.add(idType);
			 	System.out.println("Fator ID detectado "+id+" com tipo "+idType);
			 } 
		   | NUMBER {
		   		String fator = _input.LT(-1).getText();
		   		_expressionString = _expressionString + fator;
		   		DataType numberType = getNumberType(fator);
		   		expressionTypes.add(numberType);
		   		System.out.println("Fator NUMBER detectado "+fator+" com tipo "+numberType);
		   }
		   | BOOLEAN {
		   		String fator = _input.LT(-1).getText();
		   		_expressionString = _expressionString + fator;
		   		expressionTypes.add(DataType.BOOL);
		   		System.out.println("Fator BOOL detectado "+fator);
		   }
		   | STRING {
		   		String fator = _input.LT(-1).getText();
		   		_expressionString = _expressionString + fator;
		   		expressionTypes.add(DataType.STR);
		   		System.out.println("Fator STRING detectado "+fator);
		   }
		   | AP {
		   		_expressionString = _expressionString + "(";
		   	 } 
		   	 expr
		   	 FP {
		   	 	_expressionString = _expressionString + ")";	
		     }
		);

AP: '(';
FP: ')';
AC: '{';
FC: '}';
SC: ';';
DP: ':';
VR: ',';
OA: '+' | '-';
OP: '*' | '/';
OR: '>' | '<' | '>=' | '<=' | '!=' | '==';
OL: '&' | '|';
NOT: '!';
ATTR: '=';
ID: [a-z] ([a-z] | [A-Z] | [0-9])*;
BOOLEAN: 'True' | 'False';
NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '"' ([a-z] | [A-Z] | [0-9] | ' ' | '\t' | '\n' | 'wn')* '"';
WS: (' ' | '\t' | '\n' | 'wn') -> skip;