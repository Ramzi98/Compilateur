package edu.ubfc.st.vm.project.grp7.type.checker;

import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;
import edu.ubfc.st.vm.project.grp7.memory.SymbolDictionnary;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mjj.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mjj.ast.node.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TypeCheckerVisitor extends MiniJajaASTVisitor {
    public static final String SCOPE_MAIN = "main";
    public static final String SCOPE_GLOBAL = "global-0";

    HashMap<MiniJajaNode,SORTE> miniJajaNodeType = new HashMap<>();
    HashMap<MiniJajaNode,OBJ> identNature = new HashMap<>();
    HashMap<String,HashMap<String, SORTE>> methodesignature = new HashMap<>();

    private Pass pass;
    int indice;
    String currentscope = SCOPE_GLOBAL;

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    private SymbolDictionnary symbolDictionnary;

    public void setDataDictionnary(SymbolDictionnary symbolDictionnary)
    {
        this.symbolDictionnary = symbolDictionnary;
    }

    public SymbolDictionnary getSymbolDictionnary() {
        return symbolDictionnary;
    }

    public HashMap<MiniJajaNode, SORTE> getMiniJajaNodeType() {
        return miniJajaNodeType;
    }

    @Override
    public void visit(ClasseNode node) throws TypeCheckerException, IOException {
        IdentNode identifier = node.identifier();
        MiniJajaNode decls = node.decls();
        MiniJajaNode main = node.methmain();
        currentscope = SCOPE_GLOBAL;

        if (pass == Pass.FIRST_PASS)
        {
            try{
                miniJajaNodeType.put(identifier,null);
                identNature.put(identifier,OBJ.VAR);
                symbolDictionnary.register(identifier.value(), currentscope, indice++);
            }catch (Exception e){
                throw new TypeCheckerException(node.line() ,  node.column()," The symbol \"" + identifier.value() + "\" has already been declared.");
            }
        }

        try {
            if(decls != null) {
                decls.accept(this);
            }
            main.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

    }

    @Override
    public void visit(IdentNode node) throws TypeCheckerException, IOException {

        if (node.value() != null) {
            int ind = symbolDictionnary.find(node.value(),currentscope);
            if (ind == -1) {
                int ind2 = symbolDictionnary.find(node.value(),SCOPE_GLOBAL);
                if (ind2 == -1) {
                    throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The identifier \"" + node.value() + "\" has not been declared.");
                }
            }
        }else{
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The Identifier does not have a value");
        }
        updateSorte(node);


    }
    @Override
    public void visit(DeclsNode node) throws TypeCheckerException, IOException {

        MiniJajaNode nodeDecl = node.decl();
        MiniJajaNode nodeDecls = node.decls();
            try {
                nodeDecl.accept(this);
                if(nodeDecls != null) {
                    nodeDecls.accept(this);
                }
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }
        }


    @Override
    public void visit(VarsNode node) throws TypeCheckerException, IOException {

        MiniJajaNode nodeVar = node.var();
        MiniJajaNode nodeVars = node.vars();

            try {
                nodeVar.accept(this);
                if(nodeVars != null) {
                    nodeVars.accept(this);
                }
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }

    }

    @Override
    public void visit(VarNode node) throws TypeCheckerException, IOException {

        MiniJajaNode nodeType = node.typeMeth();
        MiniJajaNode nodeExpression = node.expression();
        IdentNode identifier = node.identifier();

        if(pass == Pass.FIRST_PASS) {
            try {
                nodeType.accept(this);
                if(nodeExpression != null) {
                    nodeExpression.accept(this);
                }
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }

            if (miniJajaNodeType.get(nodeType) == SORTE.OMEGA) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can not declare a variable of type " + miniJajaNodeType.get(nodeType));
            }

            if (miniJajaNodeType.get(nodeExpression) != null && miniJajaNodeType.get(nodeExpression) != miniJajaNodeType.get(nodeType)) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type " + miniJajaNodeType.get(nodeExpression) + " is not compatible with the variable \"" + identifier.value() + "\" of type " + miniJajaNodeType.get(nodeType) + ".");
            }

            try {
                miniJajaNodeType.put(identifier,miniJajaNodeType.get(nodeType));
                identNature.put(identifier,OBJ.VAR);
                symbolDictionnary.register(identifier.value(), currentscope, indice++);
            } catch (Exception e) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The symbol \"" + identifier.value() + "\" has already been declared.");
            }
        }

    }

    @Override
    public void visit(ArrayNode node) throws TypeCheckerException, IOException {


        MiniJajaNode typeNode = node.typeMeth();
        MiniJajaNode expression = node.expression();
        IdentNode identifier = node.identifier();

        if(pass == Pass.FIRST_PASS) {
            try {
                typeNode.accept(this);
                expression.accept(this);
            }catch(Exception e){
                throw new TypeCheckerException(e);
            }

            if (miniJajaNodeType.get(typeNode) == SORTE.OMEGA) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Impossible to declare an array of type void");
            }

            if (miniJajaNodeType.get(expression) != SORTE.INT) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The size of the array should be an integer");
            }


            try {
                miniJajaNodeType.put(identifier,miniJajaNodeType.get(typeNode));
                identNature.put(identifier,OBJ.TAB);
                symbolDictionnary.register(identifier.value(), currentscope, indice++);
            } catch (Exception e) {

                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The symbol \"" + identifier.value() + "\" has already been declared.");
            }
        }

    }


    @Override
    public void visit(CstNode node) throws TypeCheckerException, IOException {

        MiniJajaNode typeNode = node.type();
        MiniJajaNode expression = node.expression();
        IdentNode identifier = node.identifier();

        if(pass == Pass.FIRST_PASS) {
            try {
                typeNode.accept(this);
                if(expression != null) {
                    expression.accept(this);
                }
            }catch(Exception e){
                throw new TypeCheckerException(e);
            }

            if (miniJajaNodeType.get(expression) != null && miniJajaNodeType.get(expression) != miniJajaNodeType.get(typeNode) ) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type"+miniJajaNodeType.get(expression)+" is not compatible with "+miniJajaNodeType.get(typeNode));
            }

            try {
                miniJajaNodeType.put(identifier,miniJajaNodeType.get(typeNode));
                identNature.put(identifier,OBJ.CST);
                symbolDictionnary.register(identifier.value(), currentscope, indice++);
            } catch (Exception e) {

                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The symbol \"" + identifier.value() + "\" has already been declared.");
            }
        }

    }

    @Override
    public void visit(MethodNode node) throws TypeCheckerException, IOException {

        MiniJajaNode typeNode = node.typeMeth();
        MiniJajaNode headers = node.headers();
        InstrsNode instrs = node.instrs();
        MiniJajaNode vars = node.vars();
        IdentNode identifier = node.identifier();
        currentscope = identifier.value();

        String signature = getMethodArguments(headers);
        HashMap<String, SORTE> h = new HashMap<>();
        h.put(signature,SORTE.of(node.typeMeth().value()));
        methodesignature.put(identifier.value(),h);

        if (pass == Pass.FIRST_PASS) {
            try {
                typeNode.accept(this);
                miniJajaNodeType.put(identifier,miniJajaNodeType.get(typeNode));
                identNature.put(identifier,OBJ.METH);
                symbolDictionnary.register(identifier.value(), "global" , indice++);

            } catch (Exception e) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" "+e);
            }


        }

        if(existReturn(instrs) && miniJajaNodeType.get(typeNode) == SORTE.OMEGA){

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" A return statement was specified but the method should return void");
        }

        if(!existReturn(instrs) && miniJajaNodeType.get(typeNode) != SORTE.OMEGA){

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" This method needs a return statement");

        }

        if(pass == Pass.FIRST_PASS)
        {
            symbolDictionnary.pushScope(identifier.value());
        }

        try {
            if(headers != null){
                headers.accept(this);
            }
            if(vars != null) {
                vars.accept(this);
            }
            if(instrs != null) {
                instrs.accept(this);
            }

        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        currentscope = "global";

    }

    @Override
    public void visit(MainNode node) throws TypeCheckerException, IOException {
        MiniJajaNode nodeVars = node.vars();
        MiniJajaNode nodeInstrs = node.instrs();
        currentscope = SCOPE_MAIN;

        if(pass == Pass.FIRST_PASS)
        {
            symbolDictionnary.pushScope(SCOPE_MAIN);
        }

        try {
            if(nodeVars != null) {
                nodeVars.accept(this);
            }
            if(nodeInstrs != null) {
                nodeInstrs.accept(this);
            }
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if(pass == Pass.SECOND_PASS)
        {
            symbolDictionnary.popScope();
        }

    }

    @Override
    public void visit(HeadersNode node) throws TypeCheckerException, IOException {

        MiniJajaNode nodeHeader = node.header();
        MiniJajaNode nodeHeaders = node.headers();

            try {
                nodeHeader.accept(this);
                if(nodeHeaders != null) {
                    nodeHeaders.accept(this);
                }
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }
        }


    @Override
    public void visit(HeaderNode node) throws TypeCheckerException, IOException {

        MiniJajaNode typeNode = node.type();
        IdentNode identifier = node.identifier();

        if (pass == Pass.FIRST_PASS) {
            try {
                typeNode.accept(this);
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }

            if (miniJajaNodeType.get(typeNode) == SORTE.OMEGA) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Impossible to declare a parametere of type void");
            }
            try {
                miniJajaNodeType.put(identifier,miniJajaNodeType.get(typeNode));
                identNature.put(identifier,OBJ.VAR);
                symbolDictionnary.register(identifier.value(), currentscope, indice++);
            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }
        }
    }

    @Override
    public void visit(InstrsNode node) throws TypeCheckerException, IOException {

        MiniJajaNode instr = node.instruction();
        MiniJajaNode instrs = node.instrs();

            if(pass == Pass.SECOND_PASS){

                try
                {
                    instr.accept(this);
                    if(instrs != null) {
                        instrs.accept(this);
                    }
                }
                catch(Exception e){

                    throw new TypeCheckerException(e);

                }
            }
        }

    @Override
    public void visit(AssignNode node) throws TypeCheckerException, IOException {

        MiniJajaNode identifier = node.identifier();
        MiniJajaNode expression = node.expression();


        if(identifier instanceof ArrayItemNode){

            try {
                identifier.accept(this);
                expression.accept(this);

            }catch(Exception e){
                throw new TypeCheckerException(e);
            }

            updateSorte(identifier);

            if (miniJajaNodeType.get(expression) != miniJajaNodeType.get(identifier) ) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type"+miniJajaNodeType.get(expression)+" is not compatible with "+miniJajaNodeType.get(identifier));            }

        }else{

            try {
                identifier.accept(this);
            }catch(Exception e){
                throw new TypeCheckerException(e);

            }
            int ind = symbolDictionnary.find(((IdentNode) identifier).value(),currentscope);
            if (ind == -1) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The identifier \"" + ((IdentNode) identifier).value() + "\" has not been declared.");
            }
                
            updateSorte(identifier);
            updateSorte(expression);
            updateObj((IdentNode) identifier);
            
            if(identNature.get(identifier) == OBJ.METH){

                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Impossible to assign a value to a method ");

            }
            if(identNature.get(identifier) == OBJ.TAB){

                try {
                    expression.accept(this);
                }catch(Exception e){
                    throw new TypeCheckerException(e);

                }

                if (miniJajaNodeType.get(expression) != miniJajaNodeType.get(identifier) ) {
                    throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type"+miniJajaNodeType.get(expression)+" is not compatible with "+miniJajaNodeType.get(identifier));                }

                if (!(expression instanceof IdentNode) ) {
                    throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" An array can only be assigned to an array not an expression");
                }

            }else{

                try {
                    expression.accept(this);
                }catch(Exception e){
                    throw new TypeCheckerException(e);

                }

                if (miniJajaNodeType.get(expression) != miniJajaNodeType.get(identifier) ) {
                    throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type "+miniJajaNodeType.get(expression)+" is not compatible with "+miniJajaNodeType.get(identifier));                }

                if(identNature.get(identifier) == OBJ.VCST){


                    try {
                        symbolDictionnary.unregister(((IdentNode) identifier).value());
                    }catch(Exception e){

                        throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The constant "+ ((IdentNode) identifier).value() +" has not been declared");

                    }

                    try {
                        identNature.put(identifier, OBJ.CST);
                        symbolDictionnary.register(((IdentNode) identifier).value(), currentscope, indice++);
                    } catch (Exception e) {
                    throw new TypeCheckerException(e);
                }

                }
            }
        }
    }

    @Override
    public void visit(SumNode node) throws TypeCheckerException, IOException {

        MiniJajaNode identifier = node.identifier();
        MiniJajaNode expression = node.expression();

        if(identifier instanceof ArrayItemNode){

            try {
                identifier.accept(this);
                expression.accept(this);

            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }

            if (miniJajaNodeType.get(identifier) != SORTE.INT) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Cannot add an expression to an item of an array with an index of type "+miniJajaNodeType.get(identifier));
            }
            if (miniJajaNodeType.get(expression) != SORTE.INT) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Cannot add a"+miniJajaNodeType.get(expression) + "to integer array");
            }
        }else{

            try {
                identifier.accept(this);
                expression.accept(this);

            } catch (Exception e) {
                throw new TypeCheckerException(e);
            }

            updateSorte(identifier);
            updateSorte(expression);

            if (miniJajaNodeType.get(identifier) != SORTE.INT) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Cannot add a value to type "+miniJajaNodeType.get(identifier));
            }
            if (miniJajaNodeType.get(expression) != SORTE.INT) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Cannot add a "+miniJajaNodeType.get(expression) + "to this variable");
            }
        }

    }

    @Override
    public void visit(IncrementNode node) throws TypeCheckerException, IOException {
        MiniJajaNode identifier = node.identifier();
        try {
            identifier.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }
        
        updateSorte(identifier);

        if (identifier instanceof ArrayItemNode)
        {
            if(miniJajaNodeType.get(identifier) != SORTE.INT){
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't increment element of Array with Type "+ miniJajaNodeType.get(identifier));
            }

        }
        else
        {
            if(miniJajaNodeType.get(identifier) != SORTE.INT){
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't increment a variable of Type "+ miniJajaNodeType.get(identifier));
            }
        }
        miniJajaNodeType.put(node,miniJajaNodeType.get(identifier));

    }

    @Override
    public void visit(AppelINode node) throws TypeCheckerException, IOException {

        IdentNode identifier = node.identifier();
        MiniJajaNode nodelistexp = node.listexp();

        try {
            if(nodelistexp != null) {
                nodelistexp.accept(this);
            }
        } catch (Exception e) {
            throw new TypeCheckerException(node.line(), node.column(), e.toString());
        }
        int ind = symbolDictionnary.find(identifier.value(),"global");
        if(ind == -1)
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" There is no declared method called \""+identifier.value()+"\" .");
        }

        String signature = getCallArguments(nodelistexp);
        if(!methodesignature.get(identifier.value()).keySet().toArray()[0].equals(signature))
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" There is a probleme in methode signature \""+identifier.value()+"\" .");
        }

        updateSorte(identifier);
        miniJajaNodeType.put(node,miniJajaNodeType.get(identifier));

    }

    @Override
    public void visit(ReturnNode node) throws TypeCheckerException, IOException {

        MiniJajaNode expression = node.ret();

        if(currentscope.startsWith("main")){

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't return something in main method");

        }else if(currentscope.startsWith("global"))
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't return something in global scope");
        }

        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(node.line() ,node.column() , e.toString());
        }
        if(methodesignature.get(currentscope).values().toArray()[0] != miniJajaNodeType.get(expression))
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The returned Type is not compatible with the expected method return ");
        }


    }

    @Override
    public void visit(WriteNode node) throws TypeCheckerException, IOException {

    }

    @Override
    public void visit(WriteLnNode node) throws TypeCheckerException, IOException {

    }

    @Override
    public void visit(StringNode node) throws TypeCheckerException, IOException {

    }

    @Override
    public void visit(IfNode node) throws TypeCheckerException, IOException {

        MiniJajaNode expression = node.expression();
        MiniJajaNode nodeTrueInstr = node.trueInstrs();
        MiniJajaNode nodeFalseInstr = node.falseInstrs();

        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if(miniJajaNodeType.get(expression) != SORTE.BOOLEAN){
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't evaluate expression with Type "+ miniJajaNodeType.get(expression) +" as a conditional expression.");
        }

        try {
            if(nodeTrueInstr != null) {
                nodeTrueInstr.accept(this);
            }
            if(nodeFalseInstr !=null) {
                nodeFalseInstr.accept(this);
            }
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }



    }

    @Override
    public void visit(WhileNode node) throws TypeCheckerException, IOException {

        MiniJajaNode expressionWhile = node.expression();
        MiniJajaNode nodeInstr = node.instrs();

        try {
            expressionWhile.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if(miniJajaNodeType.get(expressionWhile) != SORTE.BOOLEAN){
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Can't evaluate expression with Type "+ miniJajaNodeType.get(expressionWhile) + "as a conditional expression.");
        }

        try {
            if(nodeInstr != null) {
                nodeInstr.accept(this);
            }
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }
    }

    @Override
    public void visit(ListExpNode node) throws TypeCheckerException, IOException {



    }

    @Override
    public void visit(NotNode node) throws TypeCheckerException, IOException {
        MiniJajaNode expression = node.expression();
        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if(miniJajaNodeType.get(expression) != SORTE.BOOLEAN){

            throw new TypeCheckerException(node.line(), node.column(), "The type of "+ miniJajaNodeType.get(expression) +"Is not compatible with the NOT operator");

        }
        miniJajaNodeType.put(node,SORTE.BOOLEAN);

    }

    @Override
    public void visit(AndNode node) throws TypeCheckerException, IOException {

        MiniJajaNode leftOperand = node.leftOperand();
        MiniJajaNode rightOperand = node.rightOperand();

        try {
            leftOperand.accept(this);
            rightOperand.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if(miniJajaNodeType.get(leftOperand) != SORTE.BOOLEAN){

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of "+leftOperand +"Is not compatible with the AND operator");

        }

        if(miniJajaNodeType.get(rightOperand) != SORTE.BOOLEAN){

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of "+rightOperand +"Is not compatible with the AND operator");

        }

        miniJajaNodeType.put(node,SORTE.BOOLEAN);

    }

    @Override
    public void visit(OrNode node) throws TypeCheckerException, IOException {

        MiniJajaNode leftOperand = node.leftOperand();
        MiniJajaNode rightOperand = node.rightOperand();

        try {
            leftOperand.accept(this);
            rightOperand.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperand) != SORTE.BOOLEAN) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperand + "Is not compatible with the OR operator");

        }

        if (miniJajaNodeType.get(rightOperand) != SORTE.BOOLEAN) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperand + "Is not compatible with the OR operator");

        }

        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(EqualsNode node) throws TypeCheckerException, IOException {

        MiniJajaNode leftOperandEquals = node.leftOperand();
        MiniJajaNode rightOperandEquals = node.rightOperand();
        try {
            leftOperandEquals.accept(this);
            rightOperandEquals.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandEquals) != miniJajaNodeType.get(rightOperandEquals)) {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandEquals + " can not be compared with the type of "+ leftOperandEquals);
        }
        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(GreaterNode node) throws TypeCheckerException, IOException {

        MiniJajaNode leftOperandGreater = node.leftOperand();
        MiniJajaNode rightOperandGreater = node.rightOperand();
        try {
            leftOperandGreater.accept(this);
            rightOperandGreater.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandGreater) != SORTE.INT) {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperandGreater + "Is not compatible with the GREATER (>) operator");
        }

        if (miniJajaNodeType.get(rightOperandGreater) != SORTE.INT) {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandGreater + "Is not compatible with the GREATER (>) operator");
        }
        miniJajaNodeType.put(node, SORTE.BOOLEAN);
    }

    @Override
    public void visit(PlusNode node) throws TypeCheckerException, IOException {
        MiniJajaNode leftOperandAdd = node.leftOperand();
        MiniJajaNode rightOperandAdd = node.rightOperand();
        try {
            leftOperandAdd.accept(this);
            rightOperandAdd.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandAdd) != SORTE.INT) {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperandAdd + "Is not compatible with the ADD operator");
        }

        if (miniJajaNodeType.get(rightOperandAdd) != SORTE.INT) {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandAdd + "Is not compatible with the ADD operator");
        }
        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(SubNode node) throws TypeCheckerException, IOException {
        MiniJajaNode leftOperandSub = node.leftOperand();
        MiniJajaNode rightOperandSub = node.rightOperand();
        try {
            leftOperandSub.accept(this);
            rightOperandSub.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandSub) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperandSub + "Is not compatible with the SUB operator");

        }

        if (miniJajaNodeType.get(rightOperandSub) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandSub + "Is not compatible with the SYB operator");
        }
        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(MinusNode node) throws TypeCheckerException, IOException {
        MiniJajaNode expression = node.expression();
        try {
            expression.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(expression) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + expression + "Is not compatible with the Minus operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(MultNode node) throws TypeCheckerException, IOException {
        MiniJajaNode leftOperandMult = node.leftOperand();
        MiniJajaNode rightOperandMult = node.rightOperand();
        try {
            leftOperandMult.accept(this);
            rightOperandMult.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandMult) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperandMult + "Is not compatible with the MULT operator");

        }

        if (miniJajaNodeType.get(rightOperandMult) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandMult + "Is not compatible with the MULT operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);
    }

    @Override
    public void visit(DivNode node) throws TypeCheckerException, IOException {
        MiniJajaNode leftOperandDiv = node.leftOperand();
        MiniJajaNode rightOperandDiv = node.rightOperand();
        try {
            leftOperandDiv.accept(this);
            rightOperandDiv.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(e);
        }

        if (miniJajaNodeType.get(leftOperandDiv) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + leftOperandDiv + "Is not compatible with the Div operator");

        }

        if (miniJajaNodeType.get(rightOperandDiv) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" The type of " + rightOperandDiv + "Is not compatible with the Div operator");

        }

        miniJajaNodeType.put(node, SORTE.INT);

    }

    @Override
    public void visit(AppelENode node) throws TypeCheckerException, IOException {

        IdentNode identifier = node.identifier();
        MiniJajaNode nodelistexp = node.listexp();

        try {
            if(nodelistexp != null) {
                nodelistexp.accept(this);
            }
        } catch (Exception e) {
            throw new TypeCheckerException(node.line(), node.column(), e.toString());
        }
        int ind = symbolDictionnary.find(identifier.value(),"global");
        if(ind == -1)
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" There is no declared method called  \""+identifier.value()+"\" ");
        }

        String signature = getCallArguments(nodelistexp);
        if(!methodesignature.get(identifier.value()).keySet().toArray()[0].equals(signature))
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" There is a probleme in methode signature \""+identifier.value()+"\" .");
        }

        updateSorte(identifier);
        miniJajaNodeType.put(node,miniJajaNodeType.get(identifier));

    }

    @Override
    public void visit(BooleanNode node) throws TypeCheckerException, IOException {

        if(node.value() != null)
        {
            SORTE type = SORTE.of(TypeNode.Type.BOOLEAN);
            miniJajaNodeType.put(node, type);
        }
        else {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Node has no value specified");
        }
    }

    @Override
    public void visit(NumberNode node) throws TypeCheckerException, IOException {

        if(node.value() != null)
        {
            SORTE type = SORTE.of(TypeNode.Type.INT);
            miniJajaNodeType.put(node,type);
        }
        else
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+" Node has no value specified");
        }

    }

    @Override
    public void visit(ArrayItemNode node) throws TypeCheckerException, IOException {
        IdentNode identifier = node.identifier();
        MiniJajaNode nodeExp = node.expression();

        try {
            nodeExp.accept(this);
        } catch (Exception e) {
            throw new TypeCheckerException(node.line(), node.column(), e.toString());
        }

        if (miniJajaNodeType.get(nodeExp) != SORTE.INT) {

            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+"The parameter that you are trying to enter as the array's index should be an integer ");

        }

        int ind = symbolDictionnary.find(identifier.value(),currentscope);
        if (ind == -1) {
            int ind2 = symbolDictionnary.find(identifier.value(),SCOPE_GLOBAL);
            if (ind2 == -1) {
                throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+"The identifier \""+identifier.value()+"\" has not been declared.");
            }
        }

        updateObj(identifier);
        updateSorte(identifier);

        if(identNature.get(identifier) != OBJ.TAB)
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+"The identifier \""+identifier.value()+"\" is not an array but it is"+identNature.get(identifier));
        }

        miniJajaNodeType.put(node,miniJajaNodeType.get(identifier));

    }

    @Override
    public void visit(TypeMethNode node) throws TypeCheckerException, IOException {
        if(node.value() != null)
        {
            SORTE type = SORTE.of(node.value());
            miniJajaNodeType.put(node,type);
        }
        else
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+"Node TypeNode has no value specified");
        }
    }

    @Override
    public void visit(TypeNode node) throws TypeCheckerException, IOException {
        if(node.value() != null)
        {
            SORTE type = SORTE.of(node.value());
            miniJajaNodeType.put(node,type);
        }
        else
        {
            throw new TypeCheckerException(node.line(),node.column(),"line : "+node.line()+" column : "+node.column()+"Node TypeNode has no value specified");
        }
    }

    public boolean existReturn(MiniJajaNode nodeInstrs){

        boolean exist = false;

        while(nodeInstrs instanceof InstrsNode){
            MiniJajaNode nodeInstr = ((InstrsNode)nodeInstrs).instruction();

            if(nodeInstr instanceof IfNode){

                exist =  existReturn(((IfNode) nodeInstr).falseInstrs()) && existReturn(((IfNode) nodeInstr).trueInstrs());
            }

            if(nodeInstr instanceof ReturnNode){

                exist = true;
            }
            nodeInstrs = ((InstrsNode) nodeInstrs).instrs();

        }

        return exist;
    }

    private String getCallArguments(MiniJajaNode nodeExpList) {
        StringBuilder buffer = new StringBuilder();

        while (nodeExpList instanceof ListExpNode) {
            MiniJajaNode nodeExp = ((ListExpNode) nodeExpList).expression();

            if(nodeExp != null)
            {
                try {
                    nodeExp.accept(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (miniJajaNodeType.get(nodeExp) == SORTE.INT) {
                    buffer.append("int");
                } else if (miniJajaNodeType.get(nodeExp) == SORTE.BOOLEAN) {
                    buffer.append("boolean");
                }
            }

            nodeExpList = ((ListExpNode) nodeExpList).listexp();

            if (nodeExpList instanceof ListExpNode) {
                buffer.append("_");
            }
        }

        return buffer.toString();
    }

    private String getMethodArguments(MiniJajaNode nodeHeaders) {
        StringBuilder buffer = new StringBuilder();

        while (nodeHeaders instanceof HeadersNode) {
            MiniJajaNode nodeHeader = ((HeadersNode) nodeHeaders).header();

            if(nodeHeader != null)
            {
                TypeNode nodeType = ((HeaderNode) nodeHeader).type();

                try {
                    nodeType.accept(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (miniJajaNodeType.get(nodeType) == SORTE.INT) {
                    buffer.append("int");
                } else if (miniJajaNodeType.get(nodeType) == SORTE.BOOLEAN) {
                    buffer.append("boolean");
                }

            }

            nodeHeaders = ((HeadersNode) nodeHeaders).headers();

            if (nodeHeaders instanceof HeadersNode) {
                buffer.append("_");
            }
        }

        return buffer.toString();
    }

    //Faire relation entre ident node est ident node existant deja dans la table minijajaType
    void updateSorte(MiniJajaNode node)
    {
        if(node instanceof IdentNode)
        {
            for (Map.Entry<MiniJajaNode, SORTE> m : miniJajaNodeType.entrySet())
            {
                if(m.getKey() instanceof IdentNode)
                {
                    if(((IdentNode) m.getKey()).value().equals(((IdentNode)node).value()))
                    {
                        miniJajaNodeType.put(node,m.getValue());
                        break;
                    }
                }
            }
        }
        else if (node instanceof ArrayItemNode)
        {
            for (Map.Entry<MiniJajaNode, SORTE> m : miniJajaNodeType.entrySet())
            {
                if(m.getKey() instanceof IdentNode)
                {
                    if(((IdentNode) m.getKey()).value().equals(((ArrayItemNode) node).identifier().value()))
                    {
                        miniJajaNodeType.put(node,m.getValue());
                        break;
                    }
                }
            }
        }
        else if (node instanceof AppelENode)
        {
            for (Map.Entry<MiniJajaNode, SORTE> m : miniJajaNodeType.entrySet())
            {
                if(m.getKey() instanceof IdentNode)
                {
                    if(((IdentNode) m.getKey()).value().equals(((AppelENode) node).identifier().value()))
                    {
                        miniJajaNodeType.put(node,m.getValue());
                        break;
                    }
                }
            }
        }
        else if (node instanceof AppelINode)
        {
            for (Map.Entry<MiniJajaNode, SORTE> m : miniJajaNodeType.entrySet())
            {
                if(m.getKey() instanceof IdentNode)
                {
                    if(((IdentNode) m.getKey()).value().equals(((AppelINode) node).identifier().value()))
                    {
                        miniJajaNodeType.put(node,m.getValue());
                        break;
                    }
                }
            }
        }
    }

    //Faire relation entre ident node est ident node existant deja dans la table identnature
    void updateObj(IdentNode node)
    {

        for (Map.Entry<MiniJajaNode, OBJ> m : identNature.entrySet())
        {
            if(m.getKey() instanceof IdentNode)
            {
                if(((IdentNode) m.getKey()).value().equals(node.value()))
                {
                    identNature.put(node,m.getValue());
                    break;
                }
            }
        }
    }

}
