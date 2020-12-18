package edu.ubfc.st.vm.project.grp7.jjc.interpreter;

import edu.ubfc.st.vm.project.grp7.ast.ASTNode;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jjc.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jjc.ast.node.*;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.Quadruplet;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;

import java.util.List;
import java.util.UUID;

public class JJCInterpreterVisitor extends JajaCodeASTVisitor {
    private final Memory memory;
    private final JJCInterpreterController controller;
    private final List<JajaCodeNode> nodes;
    private int n = 0; // TODO: 07/12/2020 eliminate n with node.line()

    public JJCInterpreterVisitor(Memory memory, List<JajaCodeNode> nodes, JJCInterpreterController controller) {
        this.memory = memory;
        this.nodes = nodes;
        this.controller = controller;
    }

    private void debug(ASTNode node) throws InterruptedException {
        controller.debug(node.line());
    }

    //<m,a> |- init -> < [], a+1>
    @Override
    public void visit(JcInitNode node) throws Exception {
        debug(node);
        if (n != 0) {
            jjcError(node, "Empty JajaCode structure to be interpreted");
        }
        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 + v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAddNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        checkValidIntegers(node, lhs, rhs);

        int newVal = ((int) lhs.val()) + ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst, w >.< w, ind, cst, w >.m,a> |- ainc(i) -> <AffecterValT(i,ind,ValT(i,ind,m)+v,m), a+1>
    @Override
    public void visit(JcAincNode node) throws Exception {
        debug(node);
        Quadruplet v = memory.depiler();
        Quadruplet i = memory.depiler();
        if (!(i.val() instanceof Integer && v.val() instanceof Integer)) {
            jjcError(node, "ainc tab index or value aren't integers");
        }
        Object val = null;
        try {
            val = memory.valT(node.identifier(), (int) i.val());
        } catch (IllegalAccessException e) {
            jjcError(node, e.getMessage());
        }
        if (!(val instanceof Integer)) {
            jjcError(node, "ainc tab isn't embedding integers");
        }
        try {
            memory.affecterValT(node.identifier(), (int) i.val(), (int) v.val() + (int) val);
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        n++;
        node.next().accept(this);
    }

    //<< w, ind, cst, w >.m,a> |- aload(i) -> << w, ValT(i, ind, m), cst,w>.m, a+1>
    @Override
    public void visit(JcAloadNode node) throws Exception {
        debug(node);
        Quadruplet i = memory.depiler();
        if (!(i.val() instanceof Integer)) {
            jjcError(node, "aload tab index isn't an integer");
        }
        Object val = null;
        try {
            val = memory.valT(node.identifier(), (int)i.val());
        } catch (IllegalAccessException e) {
            jjcError(node, e.getMessage());
        }
        memory.empiler(new Quadruplet(null, val, OBJ.CST, null));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 && v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAndNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Boolean) || !(lhs.val() instanceof Boolean)) {
            jjcError(node, "the last elements of memory are invalid");
        }
        boolean newVal = ((boolean) lhs.val()) && ((boolean) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.BOOLEAN));

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst, w >.< w, ind, cst, w >.m,a> |- astore(i) -> < AffecterValT(i, ind, v, m), a+1>
    @Override
    public void visit(JcAstoreNode node) throws Exception {
        debug(node);
        Quadruplet v = memory.depiler();
        Quadruplet i = memory.depiler();
        if (!(i.val() instanceof Integer)) {
            jjcError(node, "astore tab index isn't an integer");
        }
        try {
            memory.affecterValT(node.identifier(), (int) i.val(), v.val());
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcCmpNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        if (rhs.val().getClass() != lhs.val().getClass()) {
            jjcError(node, "You try to compare two incompatible type");
        }

        memory.empiler(new Quadruplet(null, rhs.val().equals(lhs.val()), OBJ.CST, SORTE.BOOLEAN));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 / v2, cst,w>.m, a+1>
    @Override
    public void visit(JcDivNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        checkValidIntegers(node, lhs, rhs);

        if ((int) rhs.val() == 0) {
            throw new ArithmeticException(
                    String.format("ligne %d ; colonne %d : Division by zero", node.line(), node.column())
            );
        }

        int newVal = ((int) lhs.val()) / ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<m,a> |- goto(a1) -> <m, a1 >
    @Override
    public void visit(JcGotoNode node) throws Exception {
        debug(node);
        n = node.adresse() - 1;
        if (n < 0 || n >= nodes.size()) {
            jjcError(node, "goto destination out of range");
        }
        nodes.get(n).accept(this);
    }

    //<< w,true, cst, w>.m,a> |- if(a1)⇣ <m,a1 > //ifTrue
    //<< w,false, cst, w>.m,a> |- if(a1)⇣ <m,a+1> //ifFalse
    @Override
    public void visit(JcIfNode node) throws Exception {
        debug(node);
        Quadruplet exp = memory.depiler();
        if (!(exp.val() instanceof Boolean)) {
            jjcError(node, "if expression isn't a boolean");
        }

        if ((boolean) exp.val()) {
            n = node.adresse() - 1;
            if (n < 0 || n >= nodes.size()) {
                jjcError(node, "if destination out of range");
            }
            nodes.get(n).accept(this);
        } else {
            n++;
            node.next().accept(this);
        }
    }

    //<< w,v, cst,w>.m,a>|- inc(i) -> <AffecterVal(i,Val(i,m)+v,m), a+1>
    @Override
    public void visit(JcIncNode node) throws Exception {
        debug(node);
        Quadruplet quad = memory.depiler();
        Object val = null;
        try {
            val = memory.val(node.identifier());
        } catch (IllegalAccessException e) {
            jjcError(node, e.getMessage());
        }
        if (!(val instanceof Integer && quad.val() instanceof Integer)) {
            jjcError(node, "inc an invalid type");
        }
        memory.affecterVal(node.identifier(), (int) quad.val() + (int) val);

        n++;
        node.next().accept(this);
    }

    //<m,a> |- invoke(i) -> << w, a+1,cst,w>.m, Val(i, m)>
    @Override
    public void visit(JcInvokeNode node) throws Exception {
        debug(node);
        memory.empiler(new Quadruplet(null, n + 1, OBJ.CST, null));
        int next = 0;
        try {
            next = (int) memory.val(node.identifier());
        } catch (IllegalAccessException e) {
            jjcError(node, e.getMessage());
        }

        if (next < 0 || next >= nodes.size()) {
            jjcError(node, "invoke destination out of range");
        }
        n = next - 1;

        memory.pushContext(UUID.randomUUID().toString());
        nodes.get(n).accept(this);
    }

    //<m,a> |- load(i) -> << w, Val(i,m), cst,w>.m, a+1>
    @Override
    public void visit(JcLoadNode node) throws Exception {
        debug(node);
        try {
            Object val = memory.val(node.identifier());
            memory.empiler(new Quadruplet(null, val, OBJ.CST, null));
        } catch (IllegalStateException | IllegalAccessException e) {
            jjcError(node, e.getMessage());
        }

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 * v2, cst,w>.m, a+1>
    @Override
    public void visit(JcMulNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        checkValidIntegers(node, rhs, lhs);

        int newVal = ((int) lhs.val()) * ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    private void checkValidIntegers(JajaCodeNode node, Quadruplet rhs, Quadruplet lhs) throws IllegalStateException {
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Integer) || !(lhs.val() instanceof Integer)) {
            jjcError(node, "the last elements of memory aren't valid integers");
        }
    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, neg v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNegNode node) throws Exception {
        debug(node);
        Quadruplet quad = memory.depiler();
        if (!(quad.val() instanceof Integer)) {
            jjcError(node, "invalid top of the stack element type");
        }

        memory.empiler(new Quadruplet(null, -((int) quad.val()), OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst,w>.m,a>|- newarray(i, t) -> <DeclTab(i, v, t, m), a+1>
    @Override
    public void visit(JcNewarrayNode node) throws Exception {
        debug(node);
        String ident = node.identifier();
        JcNewarrayNode.Type type = node.type();
        Quadruplet quad = memory.depiler();
        try {
            memory.declTab(ident, quad.val(), SORTE.of(type));
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        n++;
        node.next().accept(this);
    }

    // [newV] : <m,a> |- new(i,t,var,s) ⇣ <IdentVal(i,t,m,s),a+1>
    // [newC] : << w,v, cst,w>.m,a> |- new(i,t,cst,0) ⇣ <DeclCst(i,v,t,m),a+1>
    // [newM] : << w, v, cst,w>.m,a> |- new(i,t,meth,0) ⇣ <DeclMeth(i, v, t, m),a+1>
    @Override
    public void visit(JcNewNode node) throws Exception {
        debug(node);
        String ident = node.identifier();
        JcNewNode.Sorte sorte = node.sorte();
        JcNewNode.Type type = node.type();
        int depth = node.depth();
        Quadruplet quad;
        try {
            switch (sorte) {
                case VAR:
                    memory.identVal(ident, SORTE.of(type), depth);
                    break;
                case CST:
                    quad = memory.depiler();
                    memory.declCst(ident, quad.val(), SORTE.of(type));
                    break;
                case METH:
                    quad = memory.depiler();
                    memory.declMeth(ident, quad.val(), SORTE.of(type));
                    break;
            }
        } catch (IllegalStateException e) {
            jjcError(node, e.getMessage());
        }
        n++;
        node.next().accept(this);
    }

    //<m,a> |- nop -> <m, a+1>
    @Override
    public void visit(JcNopNode node) throws Exception {
        debug(node);
        controller.nop();
        n++;
        node.next().accept(this);
    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, not v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNotNode node) throws Exception {
        debug(node);
        Quadruplet quad = null;
        try {
            quad = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        if (!(quad.val() instanceof Boolean)) {
            jjcError(node, "Expression of not is not a boolean");
        }

        memory.empiler(new Quadruplet(null, !((boolean) quad.val()), OBJ.CST, SORTE.BOOLEAN));

        n++;
        node.next().accept(this);
    }

    ////<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 || v2, cst,w>.m, a+1>
    @Override
    public void visit(JcOrNode node) throws Exception {
        debug(node);
        Quadruplet rhs = null;
        try {
            rhs = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        Quadruplet lhs = null;
        try {
            lhs = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Boolean) || !(lhs.val() instanceof Boolean)) {
            jjcError(node, "the last elements of memory are invalid");
        }
        boolean newVal = ((boolean) lhs.val()) || ((boolean) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.BOOLEAN));

        n++;
        node.next().accept(this);
    }

    private void jjcError(JajaCodeNode node, String message) throws IllegalStateException {
        throw new IllegalStateException(
                String.format(
                        "Error interpreting jajaCode at line %d, column %d : \n%s",
                        node.line(),
                        node.column(),
                        message
                )
        );
    }

    //<q.m,a> |- pop -> < m, a+1>
    @Override
    public void visit(JcPopNode node) throws Exception {
        debug(node);
        memory.depiler();

        n++;
        node.next().accept(this);
    }

    //<m,a> |- push(v) -> << w, v, cst,w>.m, a+1>
    @Override
    public void visit(JcPushNode node) throws Exception {
        debug(node);
        memory.empiler(new Quadruplet(null, node.valeur(), OBJ.CST, null));
        n++;
        node.next().accept(this);
    }

    //<< w, a1,cst,w>.m,a> |- return -> <m, a1 >
    @Override
    public void visit(JcReturnNode node) throws Exception {
        debug(node);
        Quadruplet quad = null;
        try {
            quad = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        if (!(quad.val() instanceof Integer)) {
            jjcError(node, "return parameter isn't an integer");
        }

        n = (int) quad.val();
        if (n < 0 || n >= nodes.size()) {
            jjcError(node, "return destination out of range");
        }

        memory.popContext();
        nodes.get(n).accept(this);
    }

    //<m,a> |- jcstop -> <m, |_>
    @Override
    public void visit(JcStopNode node) throws Exception {
        debug(node);
    }


    //<< w, v, cst, w >.m,a> |- store(i) -> <AffecterVal(i,v,m), a+1>
    @Override
    public void visit(JcStoreNode node) throws Exception {
        debug(node);
        try {
            Quadruplet quad = memory.depiler();
            memory.affecterVal(node.identifier(), quad.val());
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 - v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSubNode node) throws Exception {
        debug(node);
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();
        checkValidIntegers(node, lhs, rhs);

        int newVal = ((int) lhs.val()) - ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSupNode node) throws Exception {
        debug(node);
        Quadruplet rhs = null;
        try {
            rhs = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        Quadruplet lhs = null;
        try {
            lhs = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }
        checkValidIntegers(node, lhs, rhs);

        boolean newVal = ((int) lhs.val()) > ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.BOOLEAN));

        n++;
        node.next().accept(this);
    }

    //< q1.q2.m,a> |- swap -> < q2.q1.m, a+1>
    @Override
    public void visit(JcSwapNode node) throws Exception {
        debug(node);
        n++;
        memory.echanger();
        node.next().accept(this);
    }

    //<< w, v, cst, w>.m,a> |- write -> <Afficher(v,m), a+1>
    @Override
    public void visit(JcWriteNode node) throws Exception {
        debug(node);
        Quadruplet quad = null;
        try {
            quad = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        controller.write(quad.val().toString());

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst, w>.m,a> |- writeln -> <AfficherLn(v,m), a+1>
    @Override
    public void visit(JcWriteLnNode node) throws Exception {
        debug(node);
        Quadruplet quad = null;
        try {
            quad = memory.depiler();
        } catch (Exception e) {
            jjcError(node, e.getMessage());
        }

        controller.writeLn(quad.val().toString());

        n++;
        node.next().accept(this);
    }
}