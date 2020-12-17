package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.Quadruplet;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaOperatorNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;

import java.util.ArrayDeque;
import java.util.Deque;

public class MiniJajaInterpreterVisitor extends MiniJajaASTVisitor {
    private final Memory memory;
    private boolean modeRetrait;
    private final Deque<Object> evals;
    private final MJJInterpreterController controller;

    public MiniJajaInterpreterVisitor(Memory memory, MJJInterpreterController controller) {
        this.memory = memory;
        this.modeRetrait = false;
        this.evals = new ArrayDeque<>();
        this.controller = controller;
    }

    // to mock evals for tests purposes
    public MiniJajaInterpreterVisitor(Memory memory, MJJInterpreterController controller, Deque<Object> deque) {
        this.memory = memory;
        this.modeRetrait = false;
        this.evals = deque;
        this.controller = controller;
    }

    public final boolean modeRetrait() {
        return this.modeRetrait;
    }

    public void switchOnRetrait() {
        this.modeRetrait = true;
    }

    public void switchOffRetrait() {
        this.modeRetrait = false;
    }


    @Override
    public void visit(ClasseNode node) throws Exception {
        debug(node);
        try {
            memory.declVar(node.identifier().value(), null, null);
        } catch (Exception e) {
            mjjError(node, e.getMessage());
        }
        if (node.decls() != null ) {
            node.decls().accept(this);
        }
        node.methmain().accept(this);

        switchOnRetrait();
        
        if (node.decls() != null ) {
            node.decls().accept(this);
        }

        switchOffRetrait();

        try {
            memory.retirerDecl(node.identifier().value());
        } catch (Exception e) {
            mjjError(node, e.getMessage());
        }
    }

    private void debug(ASTNode node) throws InterruptedException {
        controller.debug(node.line());
    }

    @Override
    public void visit(IdentNode node) throws Exception {
        evals.push(memory.val(node.value()));
    }

    @Override
    public void visit(DeclsNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            if (node.decls() != null) {
                node.decls().accept(this);
            }
            node.decl().accept(this);
        } else {
            node.decl().accept(this);
            if (node.decls() != null) {
                node.decls().accept(this);
            }
        }
    }

    @Override
    public void visit(VarsNode node) throws Exception {
        if (modeRetrait) {
            if (node.vars() != null) {
                node.vars().accept(this);
            }
            node.var().accept(this);
        } else {
            node.var().accept(this);
            if (node.vars() != null) {
                node.vars().accept(this);
            }
        }
    }

    @Override
    public void visit(VarNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            try {
                memory.retirerDecl(node.identifier().value());
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        } else {
            Object vexp = null;
            if (node.expression() != null) { // vexp = omega
                node.expression().accept(this);
                vexp = evals.pop();
            }
            try {
                memory.declVar(node.identifier().value(),
                        vexp,
                        SORTE.of(node.typeMeth().value())
                );
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(ArrayNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            try {
                memory.retirerDecl(node.identifier().value());
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        } else {
            node.expression().accept(this);
            try {
                memory.declTab(node.identifier().value(),
                        evals.pop(),
                        SORTE.of(node.typeMeth().value())
                );
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(CstNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            try {
                memory.retirerDecl(node.identifier().value());
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        } else {
            Object vexp = null;
            if (node.expression() != null) { // vexp = omega
                node.expression().accept(this);
                vexp = evals.pop();
            }
            try {
                memory.declCst(node.identifier().value(),
                        vexp,
                        SORTE.of(node.type().value())
                );
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(MethodNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            try {
                memory.retirerDecl(node.identifier().value());
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        } else {
            Quadruplet quad = new Quadruplet(node.identifier().value(), node, OBJ.METH, SORTE.of(node.typeMeth().value()));
            try {
                memory.empiler(quad);
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(MainNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            if (node.vars() != null ){
                node.vars().accept(this);
            }
        } else {
            if (node.vars() != null ){
                node.vars().accept(this);
            }
            if (node.instrs() != null){
                node.instrs().accept(this);
            }
        }
    }

    @Override
    public void visit(HeadersNode node) throws Exception {
        if (modeRetrait) {
            if (node.headers() != null) {
                node.headers().accept(this);
            }
            node.header().accept(this);
        } else {
            node.header().accept(this);
            if (node.headers() != null) {
                node.headers().accept(this);
            }
        }
    }

    @Override
    public void visit(HeaderNode node) throws Exception {
        if (modeRetrait) {
            try {
                memory.retirerDecl(node.identifier().value());
            } catch (Exception e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(InstrsNode node) throws Exception {
        node.instruction().accept(this);
        if (node.instrs() != null) {
            node.instrs().accept(this);
        }
    }

    @Override
    public void visit(AssignNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        if (node.identifier() instanceof ArrayItemNode) { // affectationT
            ArrayItemNode tab = (ArrayItemNode) node.identifier();
            tab.expression().accept(this);  // process index
            try {
                memory.affecterValT(tab.identifier().value(), (int) evals.pop(), evals.pop());
            } catch (IllegalAccessException e) {
                mjjError(node, e.getMessage());
            }
        } else { // affectation
            IdentNode ident = (IdentNode) node.identifier();
            try {
                memory.affecterVal(ident.value(), evals.pop());
            } catch (IllegalAccessException e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(SumNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        if (node.identifier() instanceof ArrayItemNode) { // sommeT
            ArrayItemNode tab = (ArrayItemNode) node.identifier();
            tab.identifier().accept(this);  // Val(i,m)
            tab.expression().accept(this);  // process index
            memory.affecterValT(tab.identifier().value(), (int) evals.pop(), (int) evals.pop() + (int) evals.pop());
        } else { // somme
            IdentNode ident = (IdentNode) node.identifier();
            ident.accept(this);     // Val(i,m)
            try {
                memory.affecterVal(ident.value(), (int) evals.pop() + (int) evals.pop());
            } catch (IllegalAccessException e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(IncrementNode node) throws Exception {
        debug(node);
        if (node.identifier() instanceof ArrayItemNode) { // incrémentT
            ArrayItemNode tab = (ArrayItemNode) node.identifier();
            tab.identifier().accept(this);  // Val(i,m)
            tab.expression().accept(this);  // process index
            try {
                memory.affecterValT(tab.identifier().value(), (int) evals.pop(), (int) evals.pop() + 1);
            } catch (IllegalAccessException e) {
                mjjError(node, e.getMessage());
            }
        } else { // incrément
            IdentNode ident = (IdentNode) node.identifier();
            ident.accept(this);     // Val(i,m)
            try {
                memory.affecterVal(ident.value(), (int) evals.pop() + 1);
            } catch (IllegalAccessException e) {
                mjjError(node, e.getMessage());
            }
        }
    }

    @Override
    public void visit(AppelINode node) throws Exception {
        Object meth = memory.val(node.identifier().value());
        if (!(meth instanceof MethodNode)) {
            mjjError(node, "Expecting the memory to peek a METHOD but got " + meth.getClass().getSimpleName());
        }
        MethodNode method = (MethodNode) meth;
        memory.pushContext(node.identifier().value());

        switchOffRetrait();
        expParam(node.listexp(), method.headers());
        if (method.vars() != null) {
            method.vars().accept(this);
        }
            if (method.instrs() != null) {
                method.instrs().accept(this);
            }
        switchOnRetrait();
        if (method.vars() != null) {
            method.vars().accept(this);
        }
        if (method.headers() != null) {
            method.headers().accept(this);
        }
        switchOffRetrait();
        memory.popContext();
    }

    @Override
    public void visit(ReturnNode node) throws Exception {
        node.ret().accept(this);
        memory.classVar(evals.pop());
    }

    @Override
    public void visit(WriteNode node) throws Exception {
        debug(node);
        node.printable().accept(this);
        controller.write(evals.pop().toString());
    }

    @Override
    public void visit(WriteLnNode node) throws Exception {
        debug(node);
        node.printable().accept(this);
        controller.writeLn(evals.pop().toString());
    }


    @Override
    public void visit(StringNode node) throws Exception {
        evals.push(node.value());
    }

    @Override
    public void visit(IfNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        if ((boolean)evals.pop()) {
            if(node.trueInstrs() != null) {
                node.trueInstrs().accept(this);
            }
        } else if(node.falseInstrs() != null) {
            node.falseInstrs().accept(this);
        }
    }

    @Override
    public void visit(WhileNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        boolean in = (boolean)evals.pop();
        while (in){

            if(node.instrs() != null) {
                node.instrs().accept(this);
            }

            node.expression().accept(this);
            in = (boolean)evals.pop();
        }
    }

    @Override
    public void visit(ListExpNode node) throws Exception {
        throw new RuntimeException("Not Supposed to be visited");
    }

    /**
     * Memeory ExpParam extension out of Memory Interface
     * @param lexp  (e, le1)                : EXP x LISTEXP
     * @param ent   (entete(t, i), ents)    : ENTÊTE x ENTÊTES
     */
    private final void expParam(ListExpNode lexp, HeadersNode ent) throws Exception {
        if (lexp != null && ent != null) {
            MiniJajaNode e  = lexp.expression();
            ListExpNode le1 = lexp.listexp();
            HeaderNode  header = ent.header();
            HeadersNode  ents  = ent.headers();

            e.accept(this);

            memory.declVar(header.identifier().value(), evals.pop(), SORTE.of(header.type().value()));

            expParam(le1, ents);
        }
    }


    @Override
    public void visit(NotNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        evals.push(!(boolean)evals.pop());
    }

    private void evaluateBinOp(MiniJajaOperatorNode node) throws Exception {
        node.rightOperand().accept(this);
        node.leftOperand().accept(this);
    }

    @Override
    public void visit(AndNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        boolean b1 = (boolean)evals.pop();
        boolean b2 = (boolean)evals.pop();
        evals.push(b1 && b2);
    }

    @Override
    public void visit(OrNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        boolean b1 = (boolean)evals.pop();
        boolean b2 = (boolean)evals.pop();
        evals.push(b1 || b2);
    }

    @Override
    public void visit(EqualsNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        evals.push(evals.pop() == evals.pop()); // maybe .equals() ?
    }

    @Override
    public void visit(GreaterNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        evals.push((int)evals.pop() > (int)evals.pop());
    }

    @Override
    public void visit(PlusNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        evals.push((int)evals.pop() + (int)evals.pop());
    }

    @Override
    public void visit(SubNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        evals.push((int)evals.pop() - (int)evals.pop());
    }

    @Override
    public void visit(MinusNode node) throws Exception {
        debug(node);
        node.expression().accept(this);
        evals.push(- (int) evals.pop());
    }

    @Override
    public void visit(MultNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        evals.push((int)evals.pop() * (int)evals.pop());
    }

    @Override
    public void visit(DivNode node) throws Exception {
        debug(node);
        evaluateBinOp(node);
        int opl = (int)evals.pop();
        int opr = (int)evals.pop();
        if (opr == 0){
            mjjError(node, "Division by zero");
        }
        evals.push(opl/opr);
    }

    @Override
    public void visit(AppelENode node) throws Exception {
        debug(node);
        AppelINode.builder()
                .identifier(node.identifier())
                .listexp(node.listexp())
                .build()
                .accept(this);

        Object ret = memory.classVar(null);
        if (ret == null) {
            mjjError(node, "Class Var must be non-null after an appelE");
        }
        evals.push(ret);
    }

    @Override
    public void visit(BooleanNode node) throws Exception {
        evals.push(node.value());
    }

    @Override
    public void visit(NumberNode node) throws Exception {
        evals.push(node.value());
    }

    @Override
    public void visit(ArrayItemNode node) throws Exception {
        node.expression().accept(this);
        Object e = null;
        try {
            e = memory.valT(node.identifier().value(), (int) evals.pop());
        } catch (IllegalAccessException ex) {
            mjjError(node, ex.getMessage());
        }
        evals.push(e);
    }

    @Override
    public void visit(TypeMethNode node) throws Exception {
        throw new RuntimeException("TypeMethNode is't supposed to be visited during MJJ Interpretation");
    }

    @Override
    public void visit(TypeNode node) throws Exception {
        throw new RuntimeException("TypeNode is't supposed to be visited during MJJ Interpretation");
    }

    private static void mjjError(ASTNode node, String message) {
        throw new IllegalStateException(
                String.format("%s at [%d ; %d]", message, node.line(), node.column())
        );
    }
}