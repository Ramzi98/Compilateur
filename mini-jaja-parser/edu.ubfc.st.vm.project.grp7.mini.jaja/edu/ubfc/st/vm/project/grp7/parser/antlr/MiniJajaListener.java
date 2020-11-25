// Generated from C:/Users/Vincent/Documents/git/m1comp7/mini-jaja-parser/src/resources\MiniJaja.g4 by ANTLR 4.8
package edu.ubfc.st.vm.project.grp7.parser.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniJajaParser}.
 */
public interface MiniJajaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#classe}.
	 * @param ctx the parse tree
	 */
	void enterClasse(MiniJajaParser.ClasseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#classe}.
	 * @param ctx the parse tree
	 */
	void exitClasse(MiniJajaParser.ClasseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(MiniJajaParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(MiniJajaParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterMultiDecls(MiniJajaParser.MultiDeclsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitMultiDecls(MiniJajaParser.MultiDeclsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterEmptyDecls(MiniJajaParser.EmptyDeclsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyDecls}
	 * labeled alternative in {@link MiniJajaParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitEmptyDecls(MiniJajaParser.EmptyDeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(MiniJajaParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(MiniJajaParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 */
	void enterMultiVars(MiniJajaParser.MultiVarsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 */
	void exitMultiVars(MiniJajaParser.MultiVarsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 */
	void enterEmptyVars(MiniJajaParser.EmptyVarsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyVars}
	 * labeled alternative in {@link MiniJajaParser#vars}.
	 * @param ctx the parse tree
	 */
	void exitEmptyVars(MiniJajaParser.EmptyVarsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarNode}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVarNode(MiniJajaParser.VarNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarNode}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVarNode(MiniJajaParser.VarNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Array}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterArray(MiniJajaParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Array}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitArray(MiniJajaParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Cst}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void enterCst(MiniJajaParser.CstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Cst}
	 * labeled alternative in {@link MiniJajaParser#var}.
	 * @param ctx the parse tree
	 */
	void exitCst(MiniJajaParser.CstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VexpAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 */
	void enterVexpAssign(MiniJajaParser.VexpAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VexpAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 */
	void exitVexpAssign(MiniJajaParser.VexpAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OmegaAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 */
	void enterOmegaAssign(MiniJajaParser.OmegaAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OmegaAssign}
	 * labeled alternative in {@link MiniJajaParser#vexp}.
	 * @param ctx the parse tree
	 */
	void exitOmegaAssign(MiniJajaParser.OmegaAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#methode}.
	 * @param ctx the parse tree
	 */
	void enterMethode(MiniJajaParser.MethodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#methode}.
	 * @param ctx the parse tree
	 */
	void exitMethode(MiniJajaParser.MethodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#methmain}.
	 * @param ctx the parse tree
	 */
	void enterMethmain(MiniJajaParser.MethmainContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#methmain}.
	 * @param ctx the parse tree
	 */
	void exitMethmain(MiniJajaParser.MethmainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiHeaders}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void enterMultiHeaders(MiniJajaParser.MultiHeadersContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiHeaders}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void exitMultiHeaders(MiniJajaParser.MultiHeadersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnitHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void enterUnitHeader(MiniJajaParser.UnitHeaderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnitHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void exitUnitHeader(MiniJajaParser.UnitHeaderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void enterEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyHeader}
	 * labeled alternative in {@link MiniJajaParser#entetes}.
	 * @param ctx the parse tree
	 */
	void exitEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniJajaParser#entete}.
	 * @param ctx the parse tree
	 */
	void enterEntete(MiniJajaParser.EnteteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniJajaParser#entete}.
	 * @param ctx the parse tree
	 */
	void exitEntete(MiniJajaParser.EnteteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 */
	void enterMultiInstrs(MiniJajaParser.MultiInstrsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 */
	void exitMultiInstrs(MiniJajaParser.MultiInstrsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 */
	void enterEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyInstrs}
	 * labeled alternative in {@link MiniJajaParser#instrs}.
	 * @param ctx the parse tree
	 */
	void exitEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(MiniJajaParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(MiniJajaParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterSum(MiniJajaParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sum}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitSum(MiniJajaParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Inc}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterInc(MiniJajaParser.IncContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Inc}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitInc(MiniJajaParser.IncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AppelI}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterAppelI(MiniJajaParser.AppelIContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AppelI}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitAppelI(MiniJajaParser.AppelIContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterReturn(MiniJajaParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Return}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitReturn(MiniJajaParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterWriteIdent(MiniJajaParser.WriteIdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitWriteIdent(MiniJajaParser.WriteIdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterWriteString(MiniJajaParser.WriteStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitWriteString(MiniJajaParser.WriteStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteLnIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterWriteLnIdent(MiniJajaParser.WriteLnIdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteLnIdent}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitWriteLnIdent(MiniJajaParser.WriteLnIdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteLnString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterWriteLnString(MiniJajaParser.WriteLnStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteLnString}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitWriteLnString(MiniJajaParser.WriteLnStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(MiniJajaParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(MiniJajaParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterIf(MiniJajaParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code If}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitIf(MiniJajaParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void enterWhile(MiniJajaParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code While}
	 * labeled alternative in {@link MiniJajaParser#instr}.
	 * @param ctx the parse tree
	 */
	void exitWhile(MiniJajaParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void enterMultiListexp(MiniJajaParser.MultiListexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void exitMultiListexp(MiniJajaParser.MultiListexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnitListExp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void enterUnitListExp(MiniJajaParser.UnitListExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnitListExp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void exitUnitListExp(MiniJajaParser.UnitListExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void enterEmptyListexp(MiniJajaParser.EmptyListexpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyListexp}
	 * labeled alternative in {@link MiniJajaParser#listexp}.
	 * @param ctx the parse tree
	 */
	void exitEmptyListexp(MiniJajaParser.EmptyListexpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNot(MiniJajaParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNot(MiniJajaParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterOr(MiniJajaParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitOr(MiniJajaParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAnd(MiniJajaParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAnd(MiniJajaParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpIsExp1}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpIsExp1}
	 * labeled alternative in {@link MiniJajaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code Equals}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterEquals(MiniJajaParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equals}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitEquals(MiniJajaParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GreaterThan}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterGreaterThan(MiniJajaParser.GreaterThanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GreaterThan}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitGreaterThan(MiniJajaParser.GreaterThanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exp1IsExp2}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void enterExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code Exp1IsExp2}
	 * labeled alternative in {@link MiniJajaParser#exp1}.
	 * @param ctx the parse tree
	 */
	void exitExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void enterSub(MiniJajaParser.SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sub}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void exitSub(MiniJajaParser.SubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exp2IsTerme}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void enterExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Exp2IsTerme}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void exitExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void enterPlus(MiniJajaParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void exitPlus(MiniJajaParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void enterMinus(MiniJajaParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link MiniJajaParser#exp2}.
	 * @param ctx the parse tree
	 */
	void exitMinus(MiniJajaParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Div}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void enterDiv(MiniJajaParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void exitDiv(MiniJajaParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TermeIsFact}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void enterTermeIsFact(MiniJajaParser.TermeIsFactContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TermeIsFact}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void exitTermeIsFact(MiniJajaParser.TermeIsFactContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void enterMul(MiniJajaParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link MiniJajaParser#terme}.
	 * @param ctx the parse tree
	 */
	void exitMul(MiniJajaParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FactIsIdent1}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code FactIsIdent1}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code AppelE}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterAppelE(MiniJajaParser.AppelEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AppelE}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitAppelE(MiniJajaParser.AppelEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(MiniJajaParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Boolean}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(MiniJajaParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterNumber(MiniJajaParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitNumber(MiniJajaParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RecExp}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void enterRecExp(MiniJajaParser.RecExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RecExp}
	 * labeled alternative in {@link MiniJajaParser#fact}.
	 * @param ctx the parse tree
	 */
	void exitRecExp(MiniJajaParser.RecExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ident1IsIdent}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 */
	void enterIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ident1IsIdent}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 */
	void exitIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayItem}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 */
	void enterArrayItem(MiniJajaParser.ArrayItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayItem}
	 * labeled alternative in {@link MiniJajaParser#ident1}.
	 * @param ctx the parse tree
	 */
	void exitArrayItem(MiniJajaParser.ArrayItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Void}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 */
	void enterVoid(MiniJajaParser.VoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Void}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 */
	void exitVoid(MiniJajaParser.VoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeMethIsType}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 */
	void enterTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeMethIsType}
	 * labeled alternative in {@link MiniJajaParser#typemeth}.
	 * @param ctx the parse tree
	 */
	void exitTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeIsINT}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeIsINT(MiniJajaParser.TypeIsINTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeIsINT}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeIsINT(MiniJajaParser.TypeIsINTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeIsBoolean}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeIsBoolean}
	 * labeled alternative in {@link MiniJajaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx);
}