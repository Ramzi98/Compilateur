package edu.ubfc.st.vm.project.grp7.mini.jaja.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
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
        memory.declVar(node.identifier().value(), null, null);
        if (node.decls() != null ) {
            node.decls().accept(this);
        }
        node.methmain().accept(this);

        switchOnRetrait();
        
        if (node.decls() != null ) {
            node.decls().accept(this);
        }

        switchOffRetrait();

        memory.retirerDecl(node.identifier().value());
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
            memory.retirerDecl(node.identifier().value());
        } else {
            node.expression().accept(this);
            memory.declVar(node.identifier().value(),
                    evals.pop(),
                    SORTE.of(node.typeMeth().value())
            );
        }
    }

    @Override
    public void visit(ArrayNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            memory.retirerDecl(node.identifier().value());
        } else {
            node.expression().accept(this);
            memory.declTab(node.identifier().value(),
                    evals.pop(),
                    SORTE.of(node.typeMeth().value())
            );
        }
    }

    @Override
    public void visit(CstNode node) throws Exception {
        debug(node);
        if (modeRetrait) {
            memory.retirerDecl(node.identifier().value());
        } else {
            node.expression().accept(this);
            memory.declCst(node.identifier().value(),
                    evals.pop(),
                    SORTE.of(node.type().value())
            );
        }
    }

    @Override
    public void visit(MethodNode node) throws Exception {
        // TODO: 29/11/2020
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
            memory.retirerDecl(node.identifier().value());
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
            memory.affecterValT(tab.identifier().value(), (int) evals.pop(), evals.pop());
        } else { // affectation
            IdentNode ident = (IdentNode) node.identifier();
            memory.affecterVal(ident.value(), evals.pop());
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
            memory.affecterVal(ident.value(), (int) evals.pop() + (int) evals.pop());
        }
    }

    @Override
    public void visit(IncrementNode node) throws Exception {
        debug(node);
        if (node.identifier() instanceof ArrayItemNode) { // incrémentT
            ArrayItemNode tab = (ArrayItemNode) node.identifier();
            tab.identifier().accept(this);  // Val(i,m)
            tab.expression().accept(this);  // process index
            memory.affecterValT(tab.identifier().value(), (int) evals.pop(), (int) evals.pop() + 1);
        } else { // incrément
            IdentNode ident = (IdentNode) node.identifier();
            ident.accept(this);     // Val(i,m)
            memory.affecterVal(ident.value(), (int) evals.pop() + 1);
        }
    }

    @Override
    public void visit(AppelINode node) throws Exception {
        // TODO: 29/11/2020
        throw new RuntimeException("Not implemented yet");
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
        // TODO: 29/11/2020
        throw new RuntimeException("Not implemented yet");
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
        evals.push((int)evals.pop() / (int)evals.pop());
    }

    @Override
    public void visit(AppelENode node) throws Exception {
        // TODO: 29/11/2020
        throw new RuntimeException("Not implemented yet");
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
        Object e = memory.valT(node.identifier().value(), (int) evals.pop());
        evals.push(e);
    }

    @Override
    public void visit(TypeMethNode node) throws Exception {

    }

    @Override
    public void visit(TypeNode node) throws Exception {

    }
}