import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

public class MiniJajaVisitorOverride  implements MiniJajaVisitor<Void> {
    Stack<MiniJajaNode> stack = new Stack<>();

    //done
    @Override
    public Void visitClasse(MiniJajaParser.ClasseContext ctx) {
        //CLASS ident LBRACE decls methmain RBRACE

        //IdentNode
        visitIdent((MiniJajaParser.IdentContext) ctx.getChild(1));
        IdentNode ident = (IdentNode)stack.peek();
        stack.pop();

        //declsNode
        visitDecls((MiniJajaParser.DeclsContext) ctx.getChild(3));
        DeclsNode declsNode;
        if (stack.size()!=0){
            declsNode = (DeclsNode)stack.peek();
            stack.pop();
        }else{
            declsNode = null;
        }

        //methmain
        visitMethmain((MiniJajaParser.MethmainContext) ctx.getChild(4));
        MainNode mainNode = (MainNode)stack.peek();
        stack.pop();

        ClasseNode miniJajaNode = ClasseNode.builder()
                .identifier(ident)
                .decls(declsNode)
                .methmain(mainNode)
                .build();

        stack.push(miniJajaNode);

        return null;
    }


    //Done
    @Override
    public Void visitIdent(MiniJajaParser.IdentContext ctx) {
        //Identifier
        IdentNode identNode = IdentNode.builder()
                .line(ctx.getStart().getLine())
                .column(ctx.getStart().getCharPositionInLine())
                .value(ctx.getChild(0).getText())
                .build();

        stack.push(identNode);
        return null;
    }

    @Override
    public Void visitDecls(MiniJajaParser.DeclsContext ctx) {
        //decl SEMI decls
        if ( ctx.getChild(0) != null){
            //decl
            visitDecl((MiniJajaParser.DeclContext) ctx.getChild(0));
            MiniJajaNode declNode = stack.peek();
            stack.pop();

            //decls
            visitDecls((MiniJajaParser.DeclsContext) ctx.getChild(2));
            DeclsNode declsNode;
            if (stack.size()!=0){
                declsNode = (DeclsNode)stack.peek();
                stack.pop();
            }else{
                declsNode = DeclsNode.builder().build();
            }

            MiniJajaNode miniJajaNode = DeclsNode.builder()
                    .decl(declNode)
                    .decls(declsNode)
                    .line(ctx.getStart().getLine())
                    .column(ctx.getStart().getLine())
                    .build();
            stack.push(miniJajaNode);
        }
        //sinon eps
        return null;
    }

    @Override
    public Void visitDecl(MiniJajaParser.DeclContext ctx) {
        //var
        if (ctx.getChild(0).getClass() == MiniJajaParser.VarContext.class){
            visitVar((MiniJajaParser.VarContext) ctx.getChild(0));
            /*
            Quel type ?
            */

            VarNode varNode = (VarNode)stack.peek();
            stack.pop();
            stack.push(varNode);

        }

        //methode
        if (ctx.getChild(0).getClass() == MiniJajaParser.MethodeContext.class){
            visitMethode((MiniJajaParser.MethodeContext)ctx.getChild(0));
            MethodNode methodNode = (MethodNode)stack.peek();
            stack.pop();
            stack.push(methodNode);
        }
         // /!\ pas de decl

        return null;
    }

    //Done
    @Override
    public Void visitVars(MiniJajaParser.VarsContext ctx) {
        //var SEMI vars
        MiniJajaNode miniJajaNode= null;
        if (ctx.children.size()!=0){
            //var SEMI vars

            visitVar((MiniJajaParser.VarContext)ctx.getChild(0));
            VarNode varNode = (VarNode)stack.peek();
            stack.pop();

            visitVars((MiniJajaParser.VarsContext)ctx.getChild(2));
            VarsNode varsNode=null;
            if (stack.size()!=0){
                varsNode = (VarsNode)stack.peek();
                stack.pop();
            }

            miniJajaNode = VarsNode.builder()
                    .var(varNode)
                    .vars(varsNode)
                    .line(ctx.start.getLine())
                    .column(ctx.start.getCharPositionInLine())
                    .build();

            stack.push(miniJajaNode);
        }
        //eps
        return null;
    }

    @Override
    public Void visitVar(MiniJajaParser.VarContext ctx) {
        TypeMethNode typeMethNode;
        IdentNode identNode;
        MiniJajaNode vexpNode;
        MiniJajaNode vexpNode2;
        TypeNode typeNode;
        CstNode cstNode;
        MiniJajaNode miniJajaNode=null;
        switch (ctx.children.size()){

            case  3:
                //typemeth ident vexp
                visitTypemeth((MiniJajaParser.TypemethContext)ctx.getChild(0));
                typeMethNode = (TypeMethNode) stack.peek();
                stack.pop();

                visitIdent((MiniJajaParser.IdentContext)ctx.getChild(1));
                identNode = (IdentNode) stack.peek();
                stack.pop();

                visitVexp((MiniJajaParser.VexpContext)ctx.getChild(2));
                vexpNode = stack.peek();
                stack.pop();


                miniJajaNode = VarNode.builder()
                        .typeMeth(typeMethNode)
                        .identifier(identNode)
                        .expression(vexpNode)
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();
                break;

            case 4 :
                //FINAL type ident vexp
                visitType((MiniJajaParser.TypeContext)ctx.getChild(0)) ;
                typeNode = (TypeNode) stack.peek();
                stack.pop();

                visitIdent((MiniJajaParser.IdentContext)ctx.getChild(1));
                identNode = (IdentNode) stack.peek();
                stack.pop();

                visitVexp((MiniJajaParser.VexpContext)ctx.getChild(2));
                vexpNode = stack.peek();
                stack.pop();

                //cst node

                miniJajaNode = CstNode.builder()
                        .type(typeNode)
                        .identifier(identNode)
                        .expression(vexpNode)
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();

                break;

            case  5:
                //typemeth ident LBRACK exp RBRACK
                //tableau
                visitTypemeth((MiniJajaParser.TypemethContext)ctx.getChild(0));
                typeMethNode = (TypeMethNode) stack.peek();
                stack.pop();

                visitIdent((MiniJajaParser.IdentContext)ctx.getChild(1));
                identNode = (IdentNode) stack.peek();
                stack.pop();

                visitExp((MiniJajaParser.ExpContext)ctx.getChild(3));
                MiniJajaNode stackReturn = stack.peek();
                MiniJajaNode exp = returnBooleanOrNumberNode(stackReturn,stackReturn.line(),stackReturn.column());
                stack.pop();


                miniJajaNode = ArrayNode.builder()
                        .typeMeth(typeMethNode)
                        .identifier(identNode)
                        .expression(exp)
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();
                break;


        }
        stack.push(miniJajaNode);

        return null;
    }

    //return assign whithout ident
    @Override
    public Void visitVexp(MiniJajaParser.VexpContext ctx) {
        MiniJajaNode stackReturn = null;
        MiniJajaNode miniJajaNode = null;
        MiniJajaNode exp = null;
        //ASSIGN exp
        if (ctx.getChild(0) != null){
            visitExp((MiniJajaParser.ExpContext)ctx.getChild(0));
            stack.pop();
            stackReturn = stack.peek();
            exp = returnBooleanOrNumberNode(stackReturn,stackReturn.line(),stackReturn.column());
            miniJajaNode = AssignNode.builder()
                    .expression(exp)
                    .build();
        }
        //eps
        return null;
    }

    @Override
    public Void visitMethode(MiniJajaParser.MethodeContext ctx) {
        //typemeth ident LPAR entetes RPAR LBRACE vars instrs RBRACE
        MiniJajaNode miniJajaNode = null;

        visitTypemeth((MiniJajaParser.TypemethContext)ctx.getChild(0));
        TypeMethNode typeMethNode = (TypeMethNode)stack.peek();
        stack.pop();

        visitIdent((MiniJajaParser.IdentContext)ctx.getChild(1));
        IdentNode identNode = (IdentNode) stack.peek();
        stack.pop();

        visitEntetes((MiniJajaParser.EntetesContext)ctx.getChild(3));
        HeadersNode headersNode = (HeadersNode)stack.peek();
        stack.pop();

        visitVars((MiniJajaParser.VarsContext)ctx.getChild(6));
        VarsNode varsNode =(VarsNode)stack.peek();
        stack.pop();

        visitInstrs((MiniJajaParser.InstrsContext)ctx.getChild(7));
        InstrsNode instrsNode = (InstrsNode)stack.peek();
        stack.pop();

        miniJajaNode = MethodNode.builder()
                .headers(headersNode)
                .identifier(identNode)
                .instrs(instrsNode)
                .typeMeth(typeMethNode)
                .vars(varsNode)
                .build();

        stack.push(miniJajaNode);

        return null;
    }

    @Override
    public Void visitMethmain(MiniJajaParser.MethmainContext ctx) {
        //MAIN LBRACE vars instrs RBRACE

        return null;
    }

    @Override
    public Void visitEntetes(MiniJajaParser.EntetesContext ctx) {
        int size = ctx.children.size();
        if (size!=0){
            switch (size){
                //case
                case 3:
                    break;

            }
        }
       //entete COMMA entetes
        //entete
        // //eps
        return null;
    }

    @Override
    public Void visitEntete(MiniJajaParser.EnteteContext ctx) {
       //type ident

        return null;
    }

    @Override
    public Void visitInstrs(MiniJajaParser.InstrsContext ctx) {
        //instr SEMI instrs
        //eps
        return null;
    }

    @Override
    public Void visitInstr(MiniJajaParser.InstrContext ctx) {
        String typeNode="";
        MiniJajaNode exp = null;
        MiniJajaNode ident1 = null;
        if (ctx.getChild(0).getClass() == MiniJajaParser.Ident1Context.class){

            visitIdent1((MiniJajaParser.Ident1Context)ctx.getChild(0));
            ident1 = stack.peek();

            if (ident1.getClass()==IdentNode.class){
                //ident1 -> ident
                IdentNode identNode = (IdentNode)ident1;
            }else{
                //ident1-> ArrayItem
                ArrayItemNode arrayItemNode = (ArrayItemNode)ident1;
            }

            typeNode = ctx.getChild(1).getText();
            switch (typeNode){
                case "=":
                    /*ident1 EQUAL exp*/
                    visitExp((MiniJajaParser.ExpContext)ctx.getChild(1));
                    exp = stack.peek();
                    stack.pop();

                    AssignNode assignNode = AssignNode.builder()
                            .expression(exp)
                            .build();
                    break;
                case "+=":

                    visitExp((MiniJajaParser.ExpContext)ctx.getChild(1));
                    exp = stack.peek();
                    stack.pop();

                    IncrementNode incrementNode = IncrementNode.builder()
                            //.
                            .build();
                    break;
                case "++":


                    break;
            }
            /*ident1 EQUAL exp
            | ident1 ADD_ASSIGN exp
            | ident1 INC exp1
        */}else{
            typeNode = ctx.getChild(0).getText();
            switch (typeNode){
                case "return":
                    //RETURN exp
                    break;
                case "write":
                    //WRITE '(' ( ident | STRING ) ')'
                    break;
                case "writeln":
                    //WRITELN '(' ( ident | STRING ) ')'
                    break;
                case "if":
                    int size =ctx.children.size();
                    if (size==5){
                        //IF exp LBRACE instrs RBRACE ELSE LBRACE instrs RBRACE
                    }else{
                        //IF exp LBRACE instrs RBRACE
                    }
                    break;
                case "while":
                    //WHILE LPAR exp RPAR LBRACE instrs RBRACE
                    break;
            }
        }

        return null;
    }

    @Override
    public Void visitListexp(MiniJajaParser.ListexpContext ctx) {
        MiniJajaNode miniJajaNode = null;
        int size = ctx.children.size();
        switch (size){
            case 1:
                // exp
                visitExp((MiniJajaParser.ExpContext)ctx.getChild(0));
                MiniJajaNode expNode = stack.peek();
                if (expNode.getClass() == BooleanNode.class){

                }
                if (expNode.getClass() == NumberNode.class){

                }
                break;
            case 3:
                //exp COMMA listexp
                break;
        }


        //| //eps

        return null;
    }

    @Override
    public Void visitExp(MiniJajaParser.ExpContext ctx) {

        MiniJajaNode miniJajaNode= null;
        int size = ctx.children.size();
        System.out.println("exp :"+size);
        switch (size){
            case 2:
                //exp1 e1
                visitExp1((MiniJajaParser.Exp1Context)ctx.getChild(0));
                MiniJajaNode exp = stack.peek();
                stack.pop();

                visitE1((MiniJajaParser.E1Context)ctx.getChild(1));
                break;
            case 3:
                //BANG exp1 e1
                visitExp1((MiniJajaParser.Exp1Context)ctx.getChild(1));
                BooleanNode booleanNode = (BooleanNode)stack.peek();
                //visitE1((MiniJajaParser.E1Context)ctx.getChild(2));
                NotNode.builder()
                        .expression(booleanNode)
                        .build();
                break;
        }


        return null;
    }

    //return
    //AndNode
    //OrNode
    @Override
    public Void visitE1(MiniJajaParser.E1Context ctx) {
        MiniJajaNode miniJajaNode = null;
        if (ctx.getChild(0) !=null) {
            String text = ctx.getChild(0).getText();
            if (text.equals("&&")) {
                //AND exp1
                visitExp1((MiniJajaParser.Exp1Context)ctx.getChild(1));

                miniJajaNode = AndNode.builder()
                        .column(ctx.start.getCharPositionInLine())
                        .line(ctx.start.getLine())
                        //.leftOperand()
                        //.rightOperand()
                        .build();

                stack.push(miniJajaNode);
            } else {
                //OR exp1
                visitExp1((MiniJajaParser.Exp1Context)ctx.getChild(1));
                miniJajaNode = OrNode.builder()
                        .column(ctx.start.getCharPositionInLine())
                        .line(ctx.start.getLine())
                        //.leftOperand()
                        //.rightOperand()
                        .build();

                stack.push(miniJajaNode);
            }
        }
        //eps
        return null;
    }

    @Override
    public Void visitExp1(MiniJajaParser.Exp1Context ctx) {
        //exp2 e2
        visitExp2((MiniJajaParser.Exp2Context)ctx.getChild(0));
        MiniJajaNode exp2 = stack.peek();




        return null;
    }

    @Override
    public Void visitE2(MiniJajaParser.E2Context ctx) {

        if (ctx.getChild(0) !=null) {
            String text = ctx.getChild(0).getText();
            if (text.equals("==")) {
                //EQUAL exp2
                visitExp2((MiniJajaParser.Exp2Context)ctx.getChild(1));
            } else {
                //GT exp2
                visitExp2((MiniJajaParser.Exp2Context)ctx.getChild(1));
            }
        }
        //eps
        return null;
    }

    @Override
    public Void visitExp2(MiniJajaParser.Exp2Context ctx) {

        int size = ctx.children.size();
        switch (size){
            case 2:
                //terme e3
                visitTerme((MiniJajaParser.TermeContext)ctx.getChild(0));
                MiniJajaNode terme = stack.peek();

                visitE3((MiniJajaParser.E3Context)ctx.getChild(1));
                break;
            case 3:
                //SUB terme e3
                visitTerme((MiniJajaParser.TermeContext)ctx.getChild(1));

                visitE3((MiniJajaParser.E3Context)ctx.getChild(2));
                break;
        }


        return null;
    }

    @Override
    public Void visitE3(MiniJajaParser.E3Context ctx) {
        MiniJajaNode miniJajaNode = null;

        if (ctx.getChild(0) !=null) {
            String text = ctx.getChild(0).getText();
            if (text.equals("+")) {
                //ADD terme
                visitTerme((MiniJajaParser.TermeContext)ctx.getChild(1));


            } else {
                //SUB terme
                visitTerme((MiniJajaParser.TermeContext)ctx.getChild(1));

            }
        }
        //eps

        return null;
    }

    @Override
    public Void visitTerme(MiniJajaParser.TermeContext ctx) {
        //fact e4
        MiniJajaNode stackReturn1 = null;
        MiniJajaNode stackReturn2 = null;
        MiniJajaNode miniJajaNode = null;
        visitFact((MiniJajaParser.FactContext)ctx.getChild(0));
        stackReturn1 =  stack.peek();;
        stack.pop();

        visitE4((MiniJajaParser.E4Context)ctx.getChild(1));
        stackReturn2 = stack.peek();
        if (stack.size()==1){
            stack.pop();
            if (stackReturn2.getClass()==MultNode.class){
                //mul
                miniJajaNode = MultNode.builder()
                        .leftOperand((NumberNode)stackReturn1)
                        .rightOperand(((MultNode)stackReturn2).rightOperand())
                        .line(stackReturn2.line())
                        .column(stackReturn2.column())
                        .build();
                stack.push(miniJajaNode);

            }else{
                //div
                miniJajaNode = DivNode.builder()
                        .leftOperand((NumberNode)stackReturn1)
                        .rightOperand(((DivNode)stackReturn2).rightOperand())
                        .line(stackReturn2.line())
                        .column(stackReturn2.column())
                        .build();
                stack.push(miniJajaNode);
            }
        }else{
            //factNode

            visitFact((MiniJajaParser.FactContext)ctx.getChild(0));
            MiniJajaNode fact = stack.peek();
            stack.pop();
            stack.push(fact);
        }



        return null;
    }

    @Override
    public Void visitE4(MiniJajaParser.E4Context ctx) {
        MiniJajaNode miniJajaNode = null;

        if (ctx.getChild(0) !=null) {
            String text = ctx.getChild(0).getText();
            if (text.equals("*")) {
                //MUL fact
                visitFact((MiniJajaParser.FactContext)ctx.getChild(1));
                NumberNode numberNode = (NumberNode)stack.peek();
                miniJajaNode = MultNode.builder()
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .rightOperand(numberNode)
                        .build();
            } else {
                //DIV fact
                visitFact((MiniJajaParser.FactContext)ctx.getChild(1));
                NumberNode numberNode = (NumberNode)stack.peek();
                miniJajaNode = DivNode.builder()
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .rightOperand(numberNode)
                        .build();
            }
            stack.push(miniJajaNode);
        }
        //eps
        return null;
    }

    //return
    //NumberNode
    //BooleanNode
    @Override
    public Void visitFact(MiniJajaParser.FactContext ctx) {
        MiniJajaNode miniJajaNode = null;
        /*
        MiniJajaNode miniJajaNode = null;
        boolean Ident1Context = ((ctx.getChild(0).getClass() == MiniJajaParser.Ident1Context.class)?true:false);
        boolean IdentContext = ((ctx.getChild(0).getClass() == MiniJajaParser.IdentContext.class)?true:false);
        if (Ident1Context){
            //ident1 -> ident
            visitIdent1((MiniJajaParser.Ident1Context)ctx.getChild(0));
            MiniJajaNode ident1 = stack.peek();
            System.out.println(ident1.getClass().toString());
            if (ident1.getClass()==IdentNodeImpl.class){
                //ident1 -> ident
                IdentNode ident = (IdentNode)ident1 ;
                miniJajaNode = IdentNode.builder()
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .value(ident.value())
                        .build();
            }else{
                ArrayItemNode arrayItemNode = (ArrayItemNode)ident1;

                //ident1 -> ident[exp]
            }
        }
        if (IdentContext){
            //ident LPAR listexp RPAR
            visitIdent((MiniJajaParser.IdentContext)ctx.getChild(0));
            visitListexp((MiniJajaParser.ListexpContext)ctx.getChild(2));
        }


        String text = ctx.getChild(0).getText();
        if (!Ident1Context && !IdentContext) {

            if (text.equals("true") || text.equals("false")) {
                // BoolLitteral
                boolean b = (text.equals("true") ? true : false);
                miniJajaNode = BooleanNode.builder()
                        .value(b)
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();
            }

            if (text.equals("(")) {
                // LPAR exp RPAR
                visitExp((MiniJajaParser.ExpContext) ctx.getChild(1));
                MiniJajaNode expNode = stack.peek();
                stack.pop();
                if (expNode.getClass() == BooleanNode.class) {
                    BooleanNode booleanNode = (BooleanNode) expNode;
                    boolean b = booleanNode.value();
                    miniJajaNode = BooleanNode.builder()
                            .value(b)
                            .line(ctx.start.getLine())
                            .column(ctx.start.getCharPositionInLine())
                            .build();
                }

            }
            if (!text.equals("(") && !text.equals("true") && !text.equals("false")) {
                // NumberLitteral
                miniJajaNode = NumberNode.builder()
                        .value(Double.parseDouble(ctx.getChild(0).getText()))
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();

            }
        }
        stack.push(miniJajaNode);*/

        //ident1
        if (ctx.getChild(0).getClass() == MiniJajaParser.Ident1Context.class){
            //ident1 -> ident
            visitIdent1((MiniJajaParser.Ident1Context)ctx.getChild(0));
            MiniJajaNode ident1 = stack.peek();
            if (ident1.getClass() == IdentNode.class){
                //ident1 -> ident
                IdentNode ident = (IdentNode)ident1 ;
                miniJajaNode =
                        IdentNode.builder()
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .value(ident.value())
                        .build();
            }else{
                System.out.println(ident1.getClass());
                //ident1 -> ident[exp]
                ArrayItemNode arrayItemNode = (ArrayItemNode) ident1 ;
                miniJajaNode = ArrayItemNode.builder()
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .identifier(arrayItemNode.identifier())
                        .expression(arrayItemNode.expression())
                        .build();
            }


        }
        stack.push(miniJajaNode);
        return null;
    }

    @Override
    public Void visitIdent1(MiniJajaParser.Ident1Context ctx) {
        int size = ctx.children.size();
        IdentNode identNode =null;
        ArrayNode arrayNode=null;
        MiniJajaNode miniJajaNode = null;
        MiniJajaNode exp = null;
        System.out.println(size);
        switch (size){
            case 1:
                //ident
                visitIdent((MiniJajaParser.IdentContext)ctx.getChild(0));
                identNode = (IdentNode)stack.peek();
                stack.pop();

                miniJajaNode = IdentNode.builder()
                        .column(ctx.start.getCharPositionInLine())
                        .line(ctx.start.getLine())
                        .value(identNode.value())
                        .build();
                stack.push(miniJajaNode);

                break;
            case 4:
                // ident LBRACK exp RBRACK
                visitIdent((MiniJajaParser.IdentContext)ctx.getChild(0));
                identNode = (IdentNode)stack.peek();
                stack.pop();

                visitExp((MiniJajaParser.ExpContext)ctx.getChild(2));
                exp = stack.peek();
                miniJajaNode = ArrayItemNode.builder()
                        .expression(exp)
                        .identifier(identNode)
                        .column(ctx.start.getCharPositionInLine())
                        .line(ctx.start.getLine())
                        .build();
                stack.push(miniJajaNode);

                break;
        }
        return null;
    }

    //return TypeNode
    //Done
    @Override
    public Void visitTypemeth(MiniJajaParser.TypemethContext ctx) {
        MiniJajaNode miniJajaNode= null;

        if ( ctx.getChild(0).getClass() == MiniJajaParser.TypeContext.class){
            // type
            visitType((MiniJajaParser.TypeContext)ctx.getChild(0));
            TypeNode typeNode = (TypeNode)stack.peek();
            stack.pop();
            if (typeNode.value()== TypeNode.Type.INT){
                 miniJajaNode = TypeMethNode.builder()
                        .column(ctx.start.getLine())
                        .line(ctx.start.getCharPositionInLine())
                        .value(TypeMethNode.TypeMeth.INT)
                        .build();

            }
            if (typeNode.value()== TypeNode.Type.BOOLEAN){
                 miniJajaNode = TypeMethNode.builder()
                        .column(ctx.start.getLine())
                        .line(ctx.start.getCharPositionInLine())
                        .value(TypeMethNode.TypeMeth.BOOLEAN)
                        .build();

            }
            stack.push(miniJajaNode);
        }else{
            //VOID
            miniJajaNode = TypeNode.builder()
                    .column(ctx.start.getLine())
                    .line(ctx.start.getCharPositionInLine())
                    .value(TypeNode.Type.VOID)
                    .build();

        }
        stack.push(miniJajaNode);
        return null;
    }

    //Done
    @Override
    public Void visitType(MiniJajaParser.TypeContext ctx) {
        MiniJajaNode miniJajaNode = null;
        String type = ctx.getChild(0).getText();
        switch (type){
            //INT
            case "int" :
                miniJajaNode = TypeNode.builder()
                        .column(ctx.start.getLine())
                        .line(ctx.start.getCharPositionInLine())
                        .value(TypeNode.Type.INT)
                        .build();
                break;
            //BOOLEAN
            case "boolean":
                miniJajaNode = TypeNode.builder()
                        .column(ctx.start.getLine())
                        .line(ctx.start.getCharPositionInLine())
                        .value(TypeNode.Type.BOOLEAN)
                        .build();
                break;
        }
        stack.push(miniJajaNode);

        return null;
    }

    @Override
    public Void visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Void visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Void visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Void visitErrorNode(ErrorNode errorNode) {
        return null;
    }


    /**
     * Annexe fonction
     */

    MiniJajaNode returnBooleanOrNumberNode(MiniJajaNode miniJajaNode, int line,int column){
        if (miniJajaNode.getClass() == BooleanNode.class){
            BooleanNode booleanNode = (BooleanNode)miniJajaNode;
            return BooleanNode.builder()
                    .line(line)
                    .column(column)
                    .value(booleanNode.value())
                    .build();
        }else{
            NumberNode numberNode = (NumberNode)miniJajaNode;
            return NumberNode.builder()
                    .line(line)
                    .column(column)
                    .value(numberNode.value())
                    .build();
        }
    }
}
