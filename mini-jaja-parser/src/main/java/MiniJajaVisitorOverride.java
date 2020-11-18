import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

public class MiniJajaVisitorOverride  implements MiniJajaVisitor<Void> {
    Stack<MiniJajaNode> stack = new Stack<>();
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
            declsNode = DeclsNode.builder().build();
        }

        //methmain
        //visitMethmain((MiniJajaParser.MethmainContext) ctx.getChild(4));
        MainNode mainNode = MainNode.builder().build() ;//(MainNode)stack.peek();
        //stack.pop();

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
            VarNode varNode = (VarNode)stack.peek();
            stack.pop();
            stack.push((MiniJajaNode) varNode);
        }

        //methode
        if (ctx.getChild(0).getClass() == MiniJajaParser.MethodeContext.class){
        }
         // /!\ pas de decl
        return null;
    }

    @Override
    public Void visitVars(MiniJajaParser.VarsContext ctx) {
        //var SEMI vars
        //eps
        return null;
    }

    @Override
    public Void visitVar(MiniJajaParser.VarContext ctx) {
        TypeMethNode typeMethNode;
        IdentNode identNode;
        MiniJajaNode vexpNode;
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

                visitVexp((MiniJajaParser.VexpContext)ctx.getChild(2));
                vexpNode = stack.peek();
                stack.pop();


                miniJajaNode = ArrayNode.builder()
                        .typeMeth(typeMethNode)
                        .identifier(identNode)
                        .expression(vexpNode)
                        .line(ctx.start.getLine())
                        .column(ctx.start.getCharPositionInLine())
                        .build();
                break;


        }
        stack.push(miniJajaNode);

        return null;
    }

    @Override
    public Void visitVexp(MiniJajaParser.VexpContext ctx) {
        //ASSIGN exp
        //eps


        return null;
    }

    @Override
    public Void visitMethode(MiniJajaParser.MethodeContext ctx) {
        //typemeth ident LPAR entetes RPAR LBRACE vars instrs RBRACE


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
                /*ident1 EQUAL exp
                | ident1 ADD_ASSIGN exp
                     | ident1 INC exp1
                | ident '(' listexp ')'
                | RETURN exp
                | WRITE '(' ( ident | STRING ) ')'
                | WRITELN '(' ( ident | STRING ) ')'
                | IF exp LBRACE instrs RBRACE ELSE LBRACE instrs RBRACE
                     | IF exp LBRACE instrs RBRACE
                | WHILE LPAR exp RPAR LBRACE instrs RBRACE*/

        return null;
    }

    @Override
    public Void visitListexp(MiniJajaParser.ListexpContext ctx) {

        //exp COMMA listexp
        //| exp
        //| //eps

        return null;
    }

    @Override
    public Void visitExp(MiniJajaParser.ExpContext ctx) {
        //BANG exp1 e1
        //exp1 e1

        return null;
    }

    @Override
    public Void visitE1(MiniJajaParser.E1Context ctx) {
        // AND exp1
         //OR exp1
         ////eps
        ;
        return null;
    }

    @Override
    public Void visitExp1(MiniJajaParser.Exp1Context ctx) {
        //exp2 e2
        return null;
    }

    @Override
    public Void visitE2(MiniJajaParser.E2Context ctx) {
        //EQUAL exp2
        //GT exp2
         ////eps
        ;
        return null;
    }

    @Override
    public Void visitExp2(MiniJajaParser.Exp2Context ctx) {
        //SUB terme e3
         //terme e3

        return null;
    }

    @Override
    public Void visitE3(MiniJajaParser.E3Context ctx) {
        //ADD terme
        //SUB terme
        //eps

        return null;
    }

    @Override
    public Void visitTerme(MiniJajaParser.TermeContext ctx) {
        //fact e4
        return null;
    }

    @Override
    public Void visitE4(MiniJajaParser.E4Context ctx) {
        //MUL fact
        //DIV fact
        //eps
        return null;
    }

    @Override
    public Void visitFact(MiniJajaParser.FactContext ctx) {
        //ident1
         /*       | ident LPAR listexp RPAR
                | BoolLitteral
                | NumberLitteral
                | LPAR exp RPAR*/
                ;
        return null;
    }

    @Override
    public Void visitIdent1(MiniJajaParser.Ident1Context ctx) {
        //ident
         //       | ident LBRACK exp RBRACK
        ;

        return null;
    }

    //push un type node dans la stack
    @Override
    public Void visitTypemeth(MiniJajaParser.TypemethContext ctx) {
        MiniJajaNode miniJajaNode= null;
        if ( ctx.getChild(0).getClass() == MiniJajaParser.TypeContext.class){
            // type
            visitType((MiniJajaParser.TypeContext)ctx.getChild(0));
            TypeNode typeNode = (TypeNode)stack.peek();
            if (typeNode.value()== TypeNode.Type.INT){

                stack.pop();
                 miniJajaNode = TypeMethNode.builder()
                        .column(ctx.start.getLine())
                        .line(ctx.start.getCharPositionInLine())
                        .value(TypeMethNode.TypeMeth.INT)
                        .build();

            }
            if (typeNode.value()== TypeNode.Type.BOOLEAN){
                stack.pop();
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

    @Override
    public Void visitType(MiniJajaParser.TypeContext ctx) {
        //INT
        //BOOLEAN
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

        /*
        MiniJajaNode
        stack.push(miniJajaNode);*/
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
}
