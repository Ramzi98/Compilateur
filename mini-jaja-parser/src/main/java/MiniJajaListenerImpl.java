import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MiniJajaListenerImpl extends MiniJajaBaseListener {

    Deque<MiniJajaNode> stack = new LinkedList<>();

    @Override
    public void exitClasse(MiniJajaParser.ClasseContext ctx) {
        super.exitClasse(ctx);
    }


    @Override
    public void exitIdent(MiniJajaParser.IdentContext ctx) {
        IdentNode identNode =IdentNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(ctx.getText())
                .build();
        stack.push(identNode);
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
    public void exitDecl(MiniJajaParser.DeclContext ctx) {
        super.exitDecl(ctx);
    }


    @Override
    public void exitMultiVars(MiniJajaParser.MultiVarsContext ctx) {
        super.exitMultiVars(ctx);
    }


    @Override
    public void exitEmptyVars(MiniJajaParser.EmptyVarsContext ctx) {
        super.exitEmptyVars(ctx);
    }


    @Override
    public void exitVarNode(MiniJajaParser.VarNodeContext ctx) {
        super.exitVarNode(ctx);
    }


    @Override
    public void exitArray(MiniJajaParser.ArrayContext ctx) {
        super.exitArray(ctx);
    }


    @Override
    public void exitCst(MiniJajaParser.CstContext ctx) {
        super.exitCst(ctx);
    }


    @Override
    public void exitVexpAssign(MiniJajaParser.VexpAssignContext ctx) {
        super.exitVexpAssign(ctx);
    }


    @Override
    public void exitOmegaAssign(MiniJajaParser.OmegaAssignContext ctx) {
        super.exitOmegaAssign(ctx);
    }


    @Override
    public void exitMethode(MiniJajaParser.MethodeContext ctx) {
        super.exitMethode(ctx);
    }


    @Override
    public void exitMethmain(MiniJajaParser.MethmainContext ctx) {
        super.exitMethmain(ctx);
    }


    @Override
    public void exitMultiHeaders(MiniJajaParser.MultiHeadersContext ctx) {
        super.exitMultiHeaders(ctx);
    }


    @Override
    public void exitUnitHeader(MiniJajaParser.UnitHeaderContext ctx) {
        super.exitUnitHeader(ctx);
    }


    @Override
    public void exitEmptyHeader(MiniJajaParser.EmptyHeaderContext ctx) {
        super.exitEmptyHeader(ctx);
    }


    @Override
    public void exitEntete(MiniJajaParser.EnteteContext ctx) {
        super.exitEntete(ctx);
    }


    @Override
    public void exitMultiInstrs(MiniJajaParser.MultiInstrsContext ctx) {
        super.exitMultiInstrs(ctx);
    }


    @Override
    public void exitEmptyInstrs(MiniJajaParser.EmptyInstrsContext ctx) {
        super.exitEmptyInstrs(ctx);
    }


    @Override
    public void exitAssign(MiniJajaParser.AssignContext ctx) {
        super.exitAssign(ctx);
    }


    @Override
    public void exitSum(MiniJajaParser.SumContext ctx) {
        super.exitSum(ctx);
    }


    @Override
    public void exitInc(MiniJajaParser.IncContext ctx) {
        super.exitInc(ctx);
    }


    @Override
    public void exitAppelI(MiniJajaParser.AppelIContext ctx) {
        super.exitAppelI(ctx);
    }


    @Override
    public void exitReturn(MiniJajaParser.ReturnContext ctx) {
        super.exitReturn(ctx);
    }


    @Override
    public void exitWrite(MiniJajaParser.WriteContext ctx) {
        super.exitWrite(ctx);
    }


    @Override
    public void exitWriteLn(MiniJajaParser.WriteLnContext ctx) {
        super.exitWriteLn(ctx);
    }


    @Override
    public void exitIfElse(MiniJajaParser.IfElseContext ctx) {
        super.exitIfElse(ctx);
    }


    @Override
    public void exitIf(MiniJajaParser.IfContext ctx) {
        super.exitIf(ctx);
    }


    @Override
    public void exitWhile(MiniJajaParser.WhileContext ctx) {
        super.exitWhile(ctx);
    }


    @Override
    public void exitMultiListexp(MiniJajaParser.MultiListexpContext ctx) {
        super.exitMultiListexp(ctx);
    }


    @Override
    public void exitUnitListExp(MiniJajaParser.UnitListExpContext ctx) {
        super.exitUnitListExp(ctx);
    }


    @Override
    public void exitEmptyListexp(MiniJajaParser.EmptyListexpContext ctx) {
        super.exitEmptyListexp(ctx);
    }


    @Override
    public void exitNot(MiniJajaParser.NotContext ctx) {
        super.exitNot(ctx);
    }


    @Override
    public void exitOr(MiniJajaParser.OrContext ctx) {
        super.exitOr(ctx);
    }


    @Override
    public void exitAnd(MiniJajaParser.AndContext ctx) {
        super.exitAnd(ctx);
    }


    @Override
    public void exitExpIsExp1(MiniJajaParser.ExpIsExp1Context ctx) {
        super.exitExpIsExp1(ctx);
    }


    @Override
    public void exitEquals(MiniJajaParser.EqualsContext ctx) {
        super.exitEquals(ctx);
    }


    @Override
    public void exitGreaterThan(MiniJajaParser.GreaterThanContext ctx) {
        super.exitGreaterThan(ctx);
    }


    @Override
    public void exitExp1IsExp2(MiniJajaParser.Exp1IsExp2Context ctx) {
        super.exitExp1IsExp2(ctx);
    }


    @Override
    public void exitSub(MiniJajaParser.SubContext ctx) {
        super.exitSub(ctx);
    }


    @Override
    public void exitExp2IsTerme(MiniJajaParser.Exp2IsTermeContext ctx) {
        super.exitExp2IsTerme(ctx);
    }


    @Override
    public void exitPlus(MiniJajaParser.PlusContext ctx) {
        super.exitPlus(ctx);
    }


    @Override
    public void exitMinus(MiniJajaParser.MinusContext ctx) {
        super.exitMinus(ctx);
    }


    @Override
    public void exitDiv(MiniJajaParser.DivContext ctx) {
        super.exitDiv(ctx);
    }


    @Override
    public void exitTermeIsFact(MiniJajaParser.TermeIsFactContext ctx) {
        super.exitTermeIsFact(ctx);
    }


    @Override
    public void exitMul(MiniJajaParser.MulContext ctx) {
        super.exitMul(ctx);
    }


    @Override
    public void exitFactIsIdent1(MiniJajaParser.FactIsIdent1Context ctx) {
        super.exitFactIsIdent1(ctx);
    }


    @Override
    public void exitAppelE(MiniJajaParser.AppelEContext ctx) {
        super.exitAppelE(ctx);
    }


    @Override
    public void exitBoolean(MiniJajaParser.BooleanContext ctx) {
        BooleanNode booleanNode = BooleanNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(Boolean.parseBoolean(ctx.getText()))
                .build();
        stack.push(booleanNode);
    }


    @Override
    public void exitNumber(MiniJajaParser.NumberContext ctx) {
        NumberNode numberNode = NumberNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(Double.parseDouble(ctx.getText()))
                .build();
        stack.push(numberNode);
    }


    @Override
    public void exitRecExp(MiniJajaParser.RecExpContext ctx) {
        super.exitRecExp(ctx);
    }


    @Override
    public void exitIdent1IsIdent(MiniJajaParser.Ident1IsIdentContext ctx) {
        super.exitIdent1IsIdent(ctx);
    }


    @Override
    public void exitArrayItem(MiniJajaParser.ArrayItemContext ctx) {
        super.exitArrayItem(ctx);
    }


    @Override
    public void exitVoid(MiniJajaParser.VoidContext ctx) {
        TypeMethNode typeNode = TypeMethNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(TypeMethNode.TypeMeth.VOID)
                .build();
        stack.push(typeNode);
    }


    @Override
    public void exitTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx) {
        TypeNode typeNode = (TypeNode)stack.pop();
        TypeMethNode typeMethNode= TypeMethNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(TypeMethNode.TypeMeth.from(typeNode.value()))
                .build();
        stack.push(typeMethNode);
    }


    @Override
    public void exitTypeIsINT(MiniJajaParser.TypeIsINTContext ctx) {
        TypeNode typeNode = TypeNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(TypeNode.Type.INT)
                .build();
        stack.push(typeNode);
    }


    @Override
    public void exitTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx) {
        TypeNode typeNode = TypeNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .value(TypeNode.Type.BOOLEAN)
                .build();
        stack.push(typeNode);
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
