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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLangParser}.
 */
public interface IsiLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiLangParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdleitura(IsiLangParser.CmdleituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdleitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdleitura(IsiLangParser.CmdleituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdescrita(IsiLangParser.CmdescritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdescrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdescrita(IsiLangParser.CmdescritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdescritasl}.
	 * @param ctx the parse tree
	 */
	void enterCmdescritasl(IsiLangParser.CmdescritaslContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdescritasl}.
	 * @param ctx the parse tree
	 */
	void exitCmdescritasl(IsiLangParser.CmdescritaslContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdattrib(IsiLangParser.CmdattribContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdattrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdattrib(IsiLangParser.CmdattribContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void enterCmdif(IsiLangParser.CmdifContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdif}.
	 * @param ctx the parse tree
	 */
	void exitCmdif(IsiLangParser.CmdifContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdwhile(IsiLangParser.CmdwhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmdwhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdwhile(IsiLangParser.CmdwhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd_dowhile}.
	 * @param ctx the parse tree
	 */
	void enterCmd_dowhile(IsiLangParser.Cmd_dowhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd_dowhile}.
	 * @param ctx the parse tree
	 */
	void exitCmd_dowhile(IsiLangParser.Cmd_dowhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_expr(IsiLangParser.Boolean_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#boolean_expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_expr(IsiLangParser.Boolean_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd_declaracao}.
	 * @param ctx the parse tree
	 */
	void enterCmd_declaracao(IsiLangParser.Cmd_declaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd_declaracao}.
	 * @param ctx the parse tree
	 */
	void exitCmd_declaracao(IsiLangParser.Cmd_declaracaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(IsiLangParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLangParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#operacao}.
	 * @param ctx the parse tree
	 */
	void enterOperacao(IsiLangParser.OperacaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#operacao}.
	 * @param ctx the parse tree
	 */
	void exitOperacao(IsiLangParser.OperacaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(IsiLangParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(IsiLangParser.FatorContext ctx);
}