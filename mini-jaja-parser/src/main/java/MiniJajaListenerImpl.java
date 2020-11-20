import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.DeclsNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MiniJajaListenerImpl extends MiniJajaBaseListener {

    Deque<MiniJajaNode> stack = new LinkedList<>();

    @Override
    public void enterClasse(MiniJajaParser.ClasseContext ctx) {
        super.enterClasse(ctx);
    }

    @Override
    public void exitClasse(MiniJajaParser.ClasseContext ctx) {
        super.exitClasse(ctx);
    }

    @Override
    public void enterIdent(MiniJajaParser.IdentContext ctx) {
        super.enterIdent(ctx);
    }

    @Override
    public void exitIdent(MiniJajaParser.IdentContext ctx) {
        super.exitIdent(ctx);
    }

    @Override
    public void exitMultiDecls(MiniJajaParser.MultiDeclsContext ctx) {
        DeclsNode.Builder builder = DeclsNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine());

        if (!stack.isEmpty() && stack.peekLast().getClass() == DeclsNode.class){
            builder.decls((DeclsNode)stack.pop());
        }else{
            builder.decls(null);
        }
        builder.decl(stack.pop());
        stack.push(builder.build()) ;
    }




    @Override
    public void enterDecl(MiniJajaParser.DeclContext ctx) {
        super.enterDecl(ctx);
    }

    @Override
    public void exitDecl(MiniJajaParser.DeclContext ctx) {
        super.exitDecl(ctx);
    }

    @Override
    public void enterMultiVars(MiniJajaParser.MultiVarsContext ctx) {
        super.enterMultiVars(ctx);
    }

    @Override
    public void exitMultiVars(MiniJajaParser.MultiVarsContext ctx) {
        super.exitMultiVars(ctx);
    }

    @Override
    public void enterEmptyVars(MiniJajaParser.EmptyVarsContext ctx) {
        super.enterEmptyVars(ctx);
    }

    @Override
    public void exitEmptyVars(MiniJajaParser.EmptyVarsContext ctx) {
        super.exitEmptyVars(ctx);
    }

    @Override
    public void enterVarNode(MiniJajaParser.VarNodeContext ctx) {
        super.enterVarNode(ctx);
    }

    @Override
    public void exitVarNode(MiniJajaParser.VarNodeContext ctx) {
        super.exitVarNode(ctx);
    }

    @Override
    public void enterArray(MiniJajaParser.ArrayContext ctx) {
        super.enterArray(ctx);
    }

    @Override
    public void exitArray(MiniJajaParser.ArrayContext ctx) {
        super.exitArray(ctx);
    }

    @Override
    public void enterCst(MiniJajaParser.CstContext ctx) {
        super.enterCst(ctx);
    }

    @Override
    public void exitCst(MiniJajaParser.CstContext ctx) {
        super.exitCst(ctx);
    }

    @Override
    public void enterVexpAssign(MiniJajaParser.VexpAssignContext ctx) {
        super.enterVexpAssign(ctx);
    }

    @Override
    public void exitVexpAssign(MiniJajaParser.VexpAssignContext ctx) {
        super.exitVexpAssign(ctx);
    }

    @Override
    public void enterOmegaAssign(MiniJajaParser.OmegaAssignContext ctx) {
        super.enterOmegaAssign(ctx);
    }

    @Override
    public void exitOmegaAssign(MiniJajaParser.OmegaAssignContext ctx) {
        super.exitOmegaAssign(ctx);
    }

    @Override
    public void enterMethode(MiniJajaParser.MethodeContext ctx) {
        super.enterMethode(ctx);
    }

    @Override
    public void exitMethode(MiniJajaParser.MethodeContext ctx) {
        super.exitMethode(ctx);
    }

    @Override
    public void enterMethmain(MiniJajaParser.MethmainContext ctx) {
        super.enterMethmain(ctx);
    }

    @Override
    public void exitMethmain(MiniJajaParser.MethmainContext ctx) {
        super.exitMethmain(ctx);
    }

    @Override
    public void enterMultiHeaders(MiniJajaParser.MultiHeadersContext ctx) {
        super.enterMultiHeaders(ctx);
    }

    @Override
    public void exitMultiHeaders(MiniJajaParser.MultiHeadersContext ctx) {
        super.exitMultiHeaders(ctx);
    }

    @Override
    public void enterUnitHeader(MiniJajaParser.UnitHeaderContext ctx) {
        super.enterUnitHeader(ctx);
    }

    @Override
    public void exitUnitHeader(MiniJajaParser.UnitHeaderContext ctx) {
        super.exitUnitHeader(ctx);
    }

    @Override
    public void enterEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx) {
        super.enterEmptyHeader(ctx);
    }

    @Override
    public void exitEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx) {
        super.exitEmptyHeader(ctx);
    }

    @Override
    public void enterEntete(MiniJajaParser.EnteteContext ctx) {
        super.enterEntete(ctx);
    }

    @Override
    public void exitEntete(MiniJajaParser.EnteteContext ctx) {
        super.exitEntete(ctx);
    }

    @Override
    public void enterMultiInstrs(MiniJajaParser.MultiInstrsContext ctx) {
        super.enterMultiInstrs(ctx);
    }

    @Override
    public void exitMultiInstrs(MiniJajaParser.MultiInstrsContext ctx) {
        super.exitMultiInstrs(ctx);
    }

    @Override
    public void enterEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx) {
        super.enterEmptyInstrs(ctx);
    }

    @Override
    public void exitEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx) {
        super.exitEmptyInstrs(ctx);
    }

    @Override
    public void enterAssign(MiniJajaParser.AssignContext ctx) {
        super.enterAssign(ctx);
    }

    @Override
    public void exitAssign(MiniJajaParser.AssignContext ctx) {
        super.exitAssign(ctx);
    }

    @Override
    public void enterSum(MiniJajaParser.SumContext ctx) {
        super.enterSum(ctx);
    }

    @Override
    public void exitSum(MiniJajaParser.SumContext ctx) {
        super.exitSum(ctx);
    }

    @Override
    public void enterInc(MiniJajaParser.IncContext ctx) {
        super.enterInc(ctx);
    }

    @Override
    public void exitInc(MiniJajaParser.IncContext ctx) {
        super.exitInc(ctx);
    }

    @Override
    public void enterAppelI(MiniJajaParser.AppelIContext ctx) {
        super.enterAppelI(ctx);
    }

    @Override
    public void exitAppelI(MiniJajaParser.AppelIContext ctx) {
        super.exitAppelI(ctx);
    }

    @Override
    public void enterReturn(MiniJajaParser.ReturnContext ctx) {
        super.enterReturn(ctx);
    }

    @Override
    public void exitReturn(MiniJajaParser.ReturnContext ctx) {
        super.exitReturn(ctx);
    }

    @Override
    public void enterWrite(MiniJajaParser.WriteContext ctx) {
        super.enterWrite(ctx);
    }

    @Override
    public void exitWrite(MiniJajaParser.WriteContext ctx) {
        super.exitWrite(ctx);
    }

    @Override
    public void enterWriteLn(MiniJajaParser.WriteLnContext ctx) {
        super.enterWriteLn(ctx);
    }

    @Override
    public void exitWriteLn(MiniJajaParser.WriteLnContext ctx) {
        super.exitWriteLn(ctx);
    }

    @Override
    public void enterIfElse(MiniJajaParser.IfElseContext ctx) {
        super.enterIfElse(ctx);
    }

    @Override
    public void exitIfElse(MiniJajaParser.IfElseContext ctx) {
        super.exitIfElse(ctx);
    }

    @Override
    public void enterIf(MiniJajaParser.IfContext ctx) {
        super.enterIf(ctx);
    }

    @Override
    public void exitIf(MiniJajaParser.IfContext ctx) {
        super.exitIf(ctx);
    }

    @Override
    public void enterWhile(MiniJajaParser.WhileContext ctx) {
        super.enterWhile(ctx);
    }

    @Override
    public void exitWhile(MiniJajaParser.WhileContext ctx) {
        super.exitWhile(ctx);
    }

    @Override
    public void enterMultiListexp(MiniJajaParser.MultiListexpContext ctx) {
        super.enterMultiListexp(ctx);
    }

    @Override
    public void exitMultiListexp(MiniJajaParser.MultiListexpContext ctx) {
        super.exitMultiListexp(ctx);
    }

    @Override
    public void enterUnitListExp(MiniJajaParser.UnitListExpContext ctx) {
        super.enterUnitListExp(ctx);
    }

    @Override
    public void exitUnitListExp(MiniJajaParser.UnitListExpContext ctx) {
        super.exitUnitListExp(ctx);
    }

    @Override
    public void enterEmptyListexp(MiniJajaParser.EmptyListexpContext ctx) {
        super.enterEmptyListexp(ctx);
    }

    @Override
    public void exitEmptyListexp(MiniJajaParser.EmptyListexpContext ctx) {
        super.exitEmptyListexp(ctx);
    }

    @Override
    public void enterNot(MiniJajaParser.NotContext ctx) {
        super.enterNot(ctx);
    }

    @Override
    public void exitNot(MiniJajaParser.NotContext ctx) {
        super.exitNot(ctx);
    }

    @Override
    public void enterOr(MiniJajaParser.OrContext ctx) {
        super.enterOr(ctx);
    }

    @Override
    public void exitOr(MiniJajaParser.OrContext ctx) {
        super.exitOr(ctx);
    }

    @Override
    public void enterAnd(MiniJajaParser.AndContext ctx) {
        super.enterAnd(ctx);
    }

    @Override
    public void exitAnd(MiniJajaParser.AndContext ctx) {
        super.exitAnd(ctx);
    }

    @Override
    public void enterExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx) {
        super.enterExpIsExp1(ctx);
    }

    @Override
    public void exitExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx) {
        super.exitExpIsExp1(ctx);
    }

    @Override
    public void enterEquals(MiniJajaParser.EqualsContext ctx) {
        super.enterEquals(ctx);
    }

    @Override
    public void exitEquals(MiniJajaParser.EqualsContext ctx) {
        super.exitEquals(ctx);
    }

    @Override
    public void enterGreaterThan(MiniJajaParser.GreaterThanContext ctx) {
        super.enterGreaterThan(ctx);
    }

    @Override
    public void exitGreaterThan(MiniJajaParser.GreaterThanContext ctx) {
        super.exitGreaterThan(ctx);
    }

    @Override
    public void enterExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx) {
        super.enterExp1IsExp2(ctx);
    }

    @Override
    public void exitExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx) {
        super.exitExp1IsExp2(ctx);
    }

    @Override
    public void enterSub(MiniJajaParser.SubContext ctx) {
        super.enterSub(ctx);
    }

    @Override
    public void exitSub(MiniJajaParser.SubContext ctx) {
        super.exitSub(ctx);
    }

    @Override
    public void enterExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx) {
        super.enterExp2IsTerme(ctx);
    }

    @Override
    public void exitExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx) {
        super.exitExp2IsTerme(ctx);
    }

    @Override
    public void enterPlus(MiniJajaParser.PlusContext ctx) {
        super.enterPlus(ctx);
    }

    @Override
    public void exitPlus(MiniJajaParser.PlusContext ctx) {
        super.exitPlus(ctx);
    }

    @Override
    public void enterMinus(MiniJajaParser.MinusContext ctx) {
        super.enterMinus(ctx);
    }

    @Override
    public void exitMinus(MiniJajaParser.MinusContext ctx) {
        super.exitMinus(ctx);
    }

    @Override
    public void enterDiv(MiniJajaParser.DivContext ctx) {
        super.enterDiv(ctx);
    }

    @Override
    public void exitDiv(MiniJajaParser.DivContext ctx) {
        super.exitDiv(ctx);
    }

    @Override
    public void enterTermeIsFact(MiniJajaParser.TermeIsFactContext ctx) {
        super.enterTermeIsFact(ctx);
    }

    @Override
    public void exitTermeIsFact(MiniJajaParser.TermeIsFactContext ctx) {
        super.exitTermeIsFact(ctx);
    }

    @Override
    public void enterMul(MiniJajaParser.MulContext ctx) {
        super.enterMul(ctx);
    }

    @Override
    public void exitMul(MiniJajaParser.MulContext ctx) {
        super.exitMul(ctx);
    }

    @Override
    public void enterFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx) {
        super.enterFactIsIdent1(ctx);
    }

    @Override
    public void exitFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx) {
        super.exitFactIsIdent1(ctx);
    }

    @Override
    public void enterAppelE(MiniJajaParser.AppelEContext ctx) {
        super.enterAppelE(ctx);
    }

    @Override
    public void exitAppelE(MiniJajaParser.AppelEContext ctx) {
        super.exitAppelE(ctx);
    }

    @Override
    public void enterBoolean(MiniJajaParser.BooleanContext ctx) {
        super.enterBoolean(ctx);
    }

    @Override
    public void exitBoolean(MiniJajaParser.BooleanContext ctx) {
        super.exitBoolean(ctx);
    }

    @Override
    public void enterNumber(MiniJajaParser.NumberContext ctx) {
        super.enterNumber(ctx);
    }

    @Override
    public void exitNumber(MiniJajaParser.NumberContext ctx) {
        super.exitNumber(ctx);
    }

    @Override
    public void enterRecExp(MiniJajaParser.RecExpContext ctx) {
        super.enterRecExp(ctx);
    }

    @Override
    public void exitRecExp(MiniJajaParser.RecExpContext ctx) {
        super.exitRecExp(ctx);
    }

    @Override
    public void enterIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx) {
        super.enterIdent1IsIdent(ctx);
    }

    @Override
    public void exitIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx) {
        super.exitIdent1IsIdent(ctx);
    }

    @Override
    public void enterArrayItem(MiniJajaParser.ArrayItemContext ctx) {
        super.enterArrayItem(ctx);
    }

    @Override
    public void exitArrayItem(MiniJajaParser.ArrayItemContext ctx) {
        super.exitArrayItem(ctx);
    }

    @Override
    public void enterVoid(MiniJajaParser.VoidContext ctx) {
        super.enterVoid(ctx);
    }

    @Override
    public void exitVoid(MiniJajaParser.VoidContext ctx) {
        super.exitVoid(ctx);
    }

    @Override
    public void enterTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx) {
        super.enterTypeMethIsType(ctx);
    }

    @Override
    public void exitTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx) {
        super.exitTypeMethIsType(ctx);
    }

    @Override
    public void enterTypeIsINT(MiniJajaParser.TypeIsINTContext ctx) {
        super.enterTypeIsINT(ctx);
    }

    @Override
    public void exitTypeIsINT(MiniJajaParser.TypeIsINTContext ctx) {
        super.exitTypeIsINT(ctx);
    }

    @Override
    public void enterTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx) {
        super.enterTypeIsBoolean(ctx);
    }

    @Override
    public void exitTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx) {
        super.exitTypeIsBoolean(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
