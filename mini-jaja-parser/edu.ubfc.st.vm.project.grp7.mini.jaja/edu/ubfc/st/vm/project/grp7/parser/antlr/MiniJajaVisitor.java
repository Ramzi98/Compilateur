// Generated from C:/Users/Vincent/Documents/git/m1comp7/mini-jaja-parser/src/resources\MiniJaja.g4 by ANTLR 4.8
package edu.ubfc.st.vm.project.grp7.parser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniJajaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniJajaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#classe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClasse(MiniJajaParser.ClasseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(MiniJajaParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiDecls(MiniJajaParser.MultiDeclsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyDecls(MiniJajaParser.EmptyDeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(MiniJajaParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiVars(MiniJajaParser.MultiVarsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyVars(MiniJajaParser.EmptyVarsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarNode}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarNode(MiniJajaParser.VarNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(MiniJajaParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Cst}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCst(MiniJajaParser.CstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VexpAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVexpAssign(MiniJajaParser.VexpAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OmegaAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOmegaAssign(MiniJajaParser.OmegaAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#methode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethode(MiniJajaParser.MethodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#methmain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethmain(MiniJajaParser.MethmainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiHeaders}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiHeaders(MiniJajaParser.MultiHeadersContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnitHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitHeader(MiniJajaParser.UnitHeaderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniJajaParser#entete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntete(MiniJajaParser.EnteteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiInstrs(MiniJajaParser.MultiInstrsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(MiniJajaParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(MiniJajaParser.SumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inc}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc(MiniJajaParser.IncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AppelI}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAppelI(MiniJajaParser.AppelIContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(MiniJajaParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WriteIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteIdent(MiniJajaParser.WriteIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WriteString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteString(MiniJajaParser.WriteStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WriteLnIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteLnIdent(MiniJajaParser.WriteLnIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WriteLnString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteLnString(MiniJajaParser.WriteLnStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElse(MiniJajaParser.IfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(MiniJajaParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(MiniJajaParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiListexp(MiniJajaParser.MultiListexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnitListExp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnitListExp(MiniJajaParser.UnitListExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyListexp(MiniJajaParser.EmptyListexpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(MiniJajaParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(MiniJajaParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(MiniJajaParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpIsExp1}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Equals}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(MiniJajaParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterThan}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterThan(MiniJajaParser.GreaterThanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exp1IsExp2}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(MiniJajaParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exp2IsTerme}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(MiniJajaParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(MiniJajaParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(MiniJajaParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TermeIsFact}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermeIsFact(MiniJajaParser.TermeIsFactContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(MiniJajaParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FactIsIdent1}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code AppelE}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAppelE(MiniJajaParser.AppelEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(MiniJajaParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MiniJajaParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RecExp}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecExp(MiniJajaParser.RecExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ident1IsIdent}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayItem}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayItem(MiniJajaParser.ArrayItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Void}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoid(MiniJajaParser.VoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeMethIsType}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeIsINT}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIsINT(MiniJajaParser.TypeIsINTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TypeIsBoolean}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx);
}