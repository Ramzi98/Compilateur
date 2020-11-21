import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;

import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Deque;

public class MiniJajaListenerImpl extends MiniJajaBaseListener {

    private final Deque<MiniJajaNode> stack = new ArrayDeque<>(7);


    public MiniJajaNode getRoot(){
       return stack.peek();
    }

    @Override
    public void exitClasse(MiniJajaParser.ClasseContext ctx) {
        ClasseNode.Builder builder = ClasseNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .methmain((MainNode) stack.pop());

        MiniJajaNode node = stack.pop();
        if (node instanceof DeclsNode) {
            builder.decls((DeclsNode) node)
                   .identifier((IdentNode) stack.pop());
        } else {
            builder.decls(null)
                   .identifier((IdentNode) node);
        }

        stack.push(builder.build());
    }


    @Override
    public void exitIdent(MiniJajaParser.IdentContext ctx) {
        IdentNode identNode = IdentNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(ctx.getText())
                .build();
        stack.push(identNode);
    }

    @Override
    public void exitMultiDecls(MiniJajaParser.MultiDeclsContext ctx) {
        DeclsNode.Builder builder = DeclsNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        if (stack.peek() instanceof DeclsNode){
            builder.decls((DeclsNode)stack.pop());
        } else {
            builder.decls(null);
        }
        
        builder.decl(stack.pop());
        stack.push(builder.build());
    }


    @Override
    public void exitMultiVars(MiniJajaParser.MultiVarsContext ctx) {
        VarsNode.Builder builder = VarsNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof VarsNode) {
            builder.vars((VarsNode) node)
                   .var(stack.pop());
        } else {
            builder.vars(null)
                   .var(node);
        }

        stack.push(builder.build());
    }




    @Override
    public void exitVarNode(MiniJajaParser.VarNodeContext ctx) {
        VarNode.Builder builder = VarNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof  IdentNode){
            builder.expression(null)
                    .identifier((IdentNode)node)
                    .typeMeth((TypeMethNode)stack.pop());
        }else{
            builder.expression(node)
                    .identifier((IdentNode)stack.pop())
                    .typeMeth((TypeMethNode)stack.pop());
        }

        stack.push(builder.build());
    }


    @Override
    public void exitArray(MiniJajaParser.ArrayContext ctx) {
        ArrayNode arrayItemNode = ArrayNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .identifier((IdentNode) stack.pop())
                .typeMeth((TypeMethNode) stack.pop())
                .build();

        stack.push(arrayItemNode);
    }


    @Override
    public void exitCst(MiniJajaParser.CstContext ctx) {
        CstNode.Builder builder = CstNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof  IdentNode){
            builder.expression(null)
                    .identifier((IdentNode)node)
                    .type((TypeNode) stack.pop());
        }else{
            builder.expression(node)
                    .identifier((IdentNode)stack.pop())
                    .type((TypeNode) stack.pop());
        }

        stack.push(builder.build());
    }


    @Override
    public void exitVexpAssign(MiniJajaParser.VexpAssignContext ctx) {

    }


    @Override
    public void exitMethode(MiniJajaParser.MethodeContext ctx) {
        MethodNode.Builder builder = MethodNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (!(node instanceof InstrsNode)){
            builder.instrs(null);
        }else{
            builder.instrs((InstrsNode)node);
            node = stack.pop();
        }

        if (!(node instanceof VarsNode)){
            builder.vars(null);
        }else{
            builder.vars((VarsNode)node);
            node = stack.pop();
        }

        if (!(node instanceof HeadersNode)){
            builder.headers(null);
        }else{
            builder.headers((HeadersNode)node);
            node = stack.pop();
        }

        builder.identifier((IdentNode) node)
                .typeMeth((TypeMethNode) stack.pop());

        stack.push(builder.build());
    }


    @Override
    public void exitMethmain(MiniJajaParser.MethmainContext ctx) {
        MainNode.Builder builder = MainNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.peek();

        if (!(node instanceof InstrsNode)){
            builder.instrs(null);
        }else{
            builder.instrs((InstrsNode)stack.pop());
            node = stack.peek();
        }

        if (!(node instanceof VarsNode)){
            builder.vars(null);
        }else{
            builder.vars((VarsNode)stack.pop());
        }

        stack.push(builder.build());
    }

    @Override
    public void exitMultiHeaders(MiniJajaParser.MultiHeadersContext ctx) {
        HeadersNode.Builder builder = HeadersNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof HeadersNode){
            builder.headers((HeadersNode) node)
                    .header((HeaderNode) stack.pop());
        }else{
            builder.headers(null)
                    .header((HeaderNode) node);
        }

        stack.push(builder.build());
    }

    @Override
    public void exitUnitHeader(MiniJajaParser.UnitHeaderContext ctx) {
        HeadersNode headers = HeadersNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .header((HeaderNode) stack.pop())
                .headers(null)
                .build();

        stack.push(headers);
    }

    @Override
    public void exitEntete(MiniJajaParser.EnteteContext ctx) {
        HeaderNode headerNode = HeaderNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .identifier((IdentNode) stack.pop())
                .type((TypeNode) stack.pop())
                .build();

        stack.push(headerNode);
    }


    @Override
    public void exitMultiInstrs(MiniJajaParser.MultiInstrsContext ctx) {
        InstrsNode.Builder builder = InstrsNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof InstrsNode){
            builder.instrs((InstrsNode) node);
            builder.instruction(stack.pop());
        }else{
            builder.instrs(null);
            builder.instruction(node);
        }

        stack.push(builder.build());
    }


    @Override
    public void exitAssign(MiniJajaParser.AssignContext ctx) {
        AssignNode assignNode = AssignNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .identifier(stack.pop())
                .build();

        stack.push(assignNode);
    }


    @Override
    public void exitSum(MiniJajaParser.SumContext ctx) {
        SumNode sumNode = SumNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .identifier(stack.pop())
                .build();

        stack.push(sumNode);
    }


    @Override
    public void exitInc(MiniJajaParser.IncContext ctx) {
        IncrementNode incrementNode = IncrementNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .identifier(stack.pop())
                .build();

        stack.push(incrementNode);
    }


    @Override
    public void exitAppelI(MiniJajaParser.AppelIContext ctx) {
        AppelINode.Builder builder = AppelINode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof IdentNode){
            builder.listexp(null)
                    .identifier((IdentNode)stack.pop());
        }else{
            builder.listexp((ListExpNode) node)
                    .identifier((IdentNode) stack.pop());
        }

        stack.push(builder.build());
    }


    @Override
    public void exitReturn(MiniJajaParser.ReturnContext ctx) {
        ReturnNode returnNode = ReturnNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .ret(stack.pop())
                .build();

        stack.push(returnNode);
    }

    @Override
    public void exitWriteIdent(MiniJajaParser.WriteIdentContext ctx) {
        WriteNode node = WriteNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .printable(stack.pop())
                .build();

        stack.push(node);
    }

    @Override
    public void exitWriteString(MiniJajaParser.WriteStringContext ctx) {
        WriteNode node = WriteNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .printable(
                        StringNode.builder()
                                .line(ctx.StringLitteral().getSymbol().getLine())
                                .column(ctx.StringLitteral().getSymbol().getLine())
                                .value(ctx.StringLitteral().getText())
                                .build()
                ).build();

        stack.push(node);
    }

    @Override
    public void exitWriteLnIdent(MiniJajaParser.WriteLnIdentContext ctx) {
        WriteLnNode node = WriteLnNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .printable(stack.pop())
                .build();

        stack.push(node);
    }

    @Override
    public void exitWriteLnString(MiniJajaParser.WriteLnStringContext ctx) {
        WriteLnNode node = WriteLnNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .printable(
                        StringNode.builder()
                                .line(ctx.StringLitteral().getSymbol().getLine())
                                .column(ctx.StringLitteral().getSymbol().getLine())
                                .value(ctx.StringLitteral().getText())
                                .build()
                ).build();

        stack.push(node);
    }

    @Override
    public void exitIfElse(MiniJajaParser.IfElseContext ctx) {
        IfNode.Builder builder = IfNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();

        //si le contexte elseInstrs n'a pas d'enfant alors il est vide
        if (ctx.elseInstrs.children.size()==0){
            builder.falseInstrs(null);
        }else{
            builder.falseInstrs((InstrsNode) node);
            node = stack.pop();
        }

        //si le contexte ifInstrs n'a pas d'enfant alors il est vide
        if (ctx.ifInstrs.children.size()==0){
            builder.trueInstrs(null);
        }else{
            builder.trueInstrs((InstrsNode) node);
            node = stack.pop();
        }

        builder.expression(node);

        stack.push(builder.build());
    }


    @Override
    public void exitIf(MiniJajaParser.IfContext ctx) {
        IfNode.Builder builder= IfNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (!(node instanceof InstrsNode)){
            builder.trueInstrs(null)
                    .falseInstrs(null)
                    .expression(node);
        }else{
            builder.trueInstrs((InstrsNode)node)
                    .falseInstrs(null)
                    .expression(stack.pop());
        }
        stack.push(builder.build());
    }


    @Override
    public void exitWhile(MiniJajaParser.WhileContext ctx) {
        WhileNode.Builder builder = WhileNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof InstrsNode){
            builder.instrs((InstrsNode)node)
                    .expression(stack.pop());
        }else{
            builder.instrs(null)
                    .expression(node);
        }

        stack.push(builder.build());
    }



    @Override
    public void exitMultiListexp(MiniJajaParser.MultiListexpContext ctx) {
        ListExpNode.Builder builder = ListExpNode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof ListExpNode){
            builder.listexp((ListExpNode)node)
                    .expression(stack.pop());
        }else{
            builder.listexp(null)
                    .expression(node);
        }

        stack.push(builder.build());
    }





    @Override
    public void exitNot(MiniJajaParser.NotContext ctx) {
        NotNode notNode = NotNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .build();

        stack.push(notNode);
    }


    @Override
    public void exitOr(MiniJajaParser.OrContext ctx) {
        OrNode orNode = OrNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(orNode);
    }


    @Override
    public void exitAnd(MiniJajaParser.AndContext ctx) {
        AndNode andNode = AndNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(andNode);
    }

    @Override
    public void exitEquals(MiniJajaParser.EqualsContext ctx) {
        EqualsNode equalsNode = EqualsNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(equalsNode);
    }


    @Override
    public void exitGreaterThan(MiniJajaParser.GreaterThanContext ctx) {
        GreaterNode greaterNode = GreaterNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(greaterNode);
    }


    @Override
    public void exitSub(MiniJajaParser.SubContext ctx) {
        SubNode subNode = SubNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(subNode);
    }


    @Override
    public void exitPlus(MiniJajaParser.PlusContext ctx) {
        PlusNode plusNode = PlusNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(plusNode);
    }


    @Override
    public void exitMinus(MiniJajaParser.MinusContext ctx) {
        MinusNode minusNode = MinusNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .build();

        stack.push(minusNode);
    }


    @Override
    public void exitDiv(MiniJajaParser.DivContext ctx) {
        DivNode divNode = DivNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(divNode);
    }


    @Override
    public void exitMul(MiniJajaParser.MulContext ctx) {
        MultNode multNode = MultNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .rightOperand(stack.pop())
                .leftOperand(stack.pop())
                .build();

        stack.push(multNode);
    }


    @Override
    public void exitUnitListExp(MiniJajaParser.UnitListExpContext ctx) {
        ListExpNode listExpNode = ListExpNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .expression(stack.pop())
                .listexp(null)
                .build();

        stack.push(listExpNode);
    }

    @Override
    public void exitAppelE(MiniJajaParser.AppelEContext ctx) {
        AppelENode.Builder builder = AppelENode.builder()
                .line(line(ctx))
                .column(column(ctx));

        MiniJajaNode node = stack.pop();
        if (node instanceof ListExpNode ){
            builder.listexp((ListExpNode) node)
                    .identifier((IdentNode) stack.pop());
        }else{
            builder.listexp(null)
                    .identifier((IdentNode) node);
        }

        stack.push(builder.build());
    }


    @Override
    public void exitBoolean(MiniJajaParser.BooleanContext ctx) {
        BooleanNode booleanNode = BooleanNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(Boolean.parseBoolean(ctx.getText()))
                .build();
        
        stack.push(booleanNode);
    }


    @Override
    public void exitNumber(MiniJajaParser.NumberContext ctx) {
        NumberNode numberNode = NumberNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(Double.parseDouble(ctx.getText()))
                .build();
        
        stack.push(numberNode);
    }


    @Override
    public void exitArrayItem(MiniJajaParser.ArrayItemContext ctx) {
        ArrayItemNode arrayItemNode = ArrayItemNode.builder()
                .line(ctx.start.getLine())
                .column(ctx.start.getCharPositionInLine())
                .expression(stack.pop())
                .identifier((IdentNode)stack.pop())
                .build();

        stack.push(arrayItemNode);
    }


    @Override
    public void exitVoid(MiniJajaParser.VoidContext ctx) {
        TypeMethNode typeNode = TypeMethNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(TypeMethNode.TypeMeth.VOID)
                .build();
        
        stack.push(typeNode);
    }


    @Override
    public void exitTypeMethIsType(MiniJajaParser.TypeMethIsTypeContext ctx) {
        TypeNode typeNode = (TypeNode) stack.pop();
        
        TypeMethNode typeMethNode = TypeMethNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(TypeMethNode.TypeMeth.from(typeNode.value()))
                .build();
        
        stack.push(typeMethNode);
    }


    @Override
    public void exitTypeIsINT(MiniJajaParser.TypeIsINTContext ctx) {
        TypeNode typeNode = TypeNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(TypeNode.Type.INT)
                .build();
        
        stack.push(typeNode);
    }


    @Override
    public void exitTypeIsBoolean(MiniJajaParser.TypeIsBooleanContext ctx) {
        TypeNode typeNode = TypeNode.builder()
                .line(line(ctx))
                .column(column(ctx))
                .value(TypeNode.Type.BOOLEAN)
                .build();
        
        stack.push(typeNode);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        throw new ASTParsingException(
                node.getSymbol().getLine(),
                node.getSymbol().getCharPositionInLine(),
                node.getText()
                );
    }

    private int line(ParserRuleContext ctx) {
        return ctx.start.getLine();
    }
    
    private int column(ParserRuleContext ctx) {
        return ctx.start.getCharPositionInLine();
    }
}
