package edu.ubfc.st.vm.project.grp7.jaja.code.interpreter;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;
import edu.ubfc.st.vm.project.grp7.memory.Memory;
import edu.ubfc.st.vm.project.grp7.memory.Quadruplet;
import edu.ubfc.st.vm.project.grp7.memory.OBJ;
import edu.ubfc.st.vm.project.grp7.memory.SORTE;

import java.util.List;

public class JJCInterpreterVisitor extends JajaCodeASTVisitor {
    private final Memory memory;
    private final JJCInterpreterController controller;
    private final List<JajaCodeNode> nodes;
    private int n = 0;

    public JJCInterpreterVisitor(Memory memory, List<JajaCodeNode> nodes, JJCInterpreterController controller) {
        this.memory = memory;
        this.nodes = nodes;
        this.controller = controller;
    }

    //<m,a> |- init -> < [], a+1>
    @Override
    public void visit(JcInitNode node) throws Exception {
        if (n != 0) {
            jjcError(node, "Empty JajaCode structure to be interpreted");
        }
        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 + v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAddNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        chackValidIntegers(node, lhs, rhs);

        int newVal = ((int) lhs.val()) + ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst, w >.< w, ind, cst, w >.m,a> |- ainc(i) -> <AffecterValT(i,ind,ValT(i,ind,m)+v,m), a+1>
    @Override
    public void visit(JcAincNode node) throws Exception {
        Quadruplet v = memory.depiler();
        Quadruplet i = memory.depiler();
        if (! (i.val() instanceof Integer && v.val() instanceof Integer)) {
            jjcError(node, "ainc tab index or value aren't integers");
        }
        Object val = memory.valT(node.identifier() , (int)i.val());
        if (! (val instanceof Integer)) {
            jjcError(node, "ainc tab isn't embedding integers");
        }
        memory.affecterValT(node.identifier(), (int)i.val(), (int) v.val() + (int) val);

        n++;
        node.next().accept(this);
    }

    //<< w, ind, cst, w >.m,a> |- aload(i) -> << w, ValT(i, ind, m), cst,w>.m, a+1>
    @Override
    public void visit(JcAloadNode node) throws Exception {
        Quadruplet i = memory.depiler();
        if (! (i.val() instanceof Integer)) {
            jjcError(node, "aload tab index isn't an integer");
        }
        Object val = memory.val(node.identifier());
        memory.empiler(new Quadruplet(null, val, OBJ.CST, null));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 && v2, cst,w>.m, a+1>
    @Override
    public void visit(JcAndNode node) throws Exception {
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
        Quadruplet i = memory.depiler();
        Quadruplet v = memory.depiler();
        if (! (i.val() instanceof Integer)) {
            jjcError(node, "astore tab index isn't an integer");
        }
        memory.affecterValT(node.identifier(), (int) i.val(), v.val());

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcCmpNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        if(rhs.val().getClass() != lhs.val().getClass()){
            jjcError(node, "You try to compare two incompatible type");
        }

        memory.empiler(new Quadruplet(null, rhs.val().equals(lhs.val()), OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 / v2, cst,w>.m, a+1>
    @Override
    public void visit(JcDivNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        chackValidIntegers(node, lhs, rhs);

        int newVal = ((int) lhs.val()) / ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<m,a> |- goto(a1) -> <m, a1 >
    @Override
    public void visit(JcGotoNode node) throws Exception {
        n = node.adresse();
        if (n < 0 || n >= nodes.size()) {
           jjcError(node, "goto destination out of range");
        }
        nodes.get(n).accept(this);
    }

    //<< w,true, cst, w>.m,a> |- if(a1)⇣ <m,a1 > //ifTrue
    //<< w,false, cst, w>.m,a> |- if(a1)⇣ <m,a+1> //ifFalse
    @Override
    public void visit(JcIfNode node) throws Exception {
        Quadruplet exp = memory.depiler();
        if (! (exp.val() instanceof Boolean)) {
            jjcError(node, "if expression isn't a boolean");
        }

        if ((boolean)exp.val()) {
            n = node.adresse();
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
        Quadruplet quad = memory.depiler();
        Object val = memory.val(node.identifier());
        if (! (val instanceof Integer && quad.val() instanceof Integer)) {
            jjcError(node, "inc an invalid type");
        }
        memory.affecterVal(node.identifier(), (int) quad.val() + (int) val);

        n++;
        node.next().accept(this);
    }

    //<m,a> |- invoke(i) -> << w, a+1,cst,w>.m, Val(i, m)>
    @Override
    public void visit(JcInvokeNode node) throws Exception {
        // TODO: 02/12/2020
    }

    //<m,a> |- load(i) -> << w, Val(i,m), cst,w>.m, a+1>
    @Override
    public void visit(JcLoadNode node) throws Exception {
        Object val = memory.val(node.identifier());
        memory.empiler(new Quadruplet(null, val, OBJ.CST, null));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 * v2, cst,w>.m, a+1>
    @Override
    public void visit(JcMulNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();

        chackValidIntegers(node, rhs, lhs);

        int newVal = ((int) lhs.val()) * ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    private void chackValidIntegers(JajaCodeNode node, Quadruplet rhs, Quadruplet lhs) {
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Integer) || !(lhs.val() instanceof Integer)) {
                    jjcError(node, "the last elements of memory aren't valid integers");
        }
    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, neg v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNegNode node) throws Exception {
        Quadruplet quad = memory.depiler();
        if (! (quad.val() instanceof Integer)) {
            jjcError(node, "invalid top of the stack element type");
        }
        quad.val(!((boolean) quad.val()));
        memory.empiler(quad);

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst,w>.m,a>|- newarray(i, t) -> <DeclTab(i, v, t, m), a+1>
    @Override
    public void visit(JcNewarrayNode node) throws Exception {
        String ident = node.identifier();
        JcNewarrayNode.Type type = node.type();
        Quadruplet quad = memory.depiler();
        memory.declTab(ident, quad.val(), SORTE.of(type));

        n++;
        node.next().accept(this);
    }

    // [newV] : <m,a> |- new(i,t,var,s) ⇣ <IdentVal(i,t,m,s),a+1>
    // [newC] : << w,v, cst,w>.m,a> |- new(i,t,cst,0) ⇣ <DeclCst(i,v,t,m),a+1>
    // [newM] : << w, v, cst,w>.m,a> |- new(i,t,meth,0) ⇣ <DeclMeth(i, v, t, m),a+1>
    @Override
    public void visit(JcNewNode node) throws Exception {
        String ident = node.identifier();
        JcNewNode.Sorte sorte = node.sorte();
        JcNewNode.Type type = node.type();
        int depth = node.depth();
        Quadruplet quad;
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

        n++;
        node.next().accept(this);
    }

    //<m,a> |- nop -> <m, a+1>
    @Override
    public void visit(JcNopNode node) throws Exception {
        controller.nop();
        n++;
        node.next().accept(this);
    }

    //<< w, v1,cst,w>.m,a> |- oper1 -> << w, not v1, cst,w>.m, a+1>
    @Override
    public void visit(JcNotNode node) throws Exception {
        Quadruplet quad = memory.depiler();
        if (! (quad.val() instanceof Boolean)) {
             jjcError(node, "Expression of not is not a boolean");
        }
        quad.val(!((boolean) quad.val()));
        memory.empiler(quad);

        n++;
        node.next().accept(this);
    }

    ////<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 || v2, cst,w>.m, a+1>
    @Override
    public void visit(JcOrNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();
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
        memory.depiler();

        n++;
        node.next().accept(this);
    }

    //<m,a> |- push(v) -> << w, v, cst,w>.m, a+1>
    @Override
    public void visit(JcPushNode node) throws Exception {
        memory.empiler(new Quadruplet(null, node.valeur(), OBJ.CST, null));

        n++;
        node.next().accept(this);
    }

    //<< w, a1,cst,w>.m,a> |- return -> <m, a1 >
    @Override
    public void visit(JcReturnNode node) throws Exception {
        Quadruplet quad = memory.depiler();
        if (!(quad.val() instanceof Integer)) {
            jjcError(node, "return parameter isn't an integer");
        }

        n = (int) quad.val();
        if (n < 0 || n >= nodes.size()) {
            jjcError(node, "return destination out of range");
        }
        nodes.get(n).accept(this);
    }

    //<m,a> |- jcstop -> <m, |_>
    @Override
    public void visit(JcStopNode node) throws Exception {
        // do nothing end of the program
    }


    //<< w, v, cst, w >.m,a> |- store(i) -> <AffecterVal(i,v,m), a+1>
    @Override
    public void visit(JcStoreNode node) throws Exception {
        Quadruplet quad = memory.depiler();
        memory.affecterVal(node.identifier(), quad.val());

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 - v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSubNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Integer) || !(lhs.val() instanceof Integer)) {
                    jjcError(node, "the last elements of memory aren't valid integers");
        }
        int newVal = ((int) lhs.val()) - ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //<< w, v2,cst,w>.< w, v1,cst,w>.m,a> |- oper2 -> << w, v1 > v2, cst,w>.m, a+1>
    @Override
    public void visit(JcSupNode node) throws Exception {
        Quadruplet rhs = memory.depiler();
        Quadruplet lhs = memory.depiler();
        if (rhs.id() != null || lhs.id() != null ||
                !(rhs.val() instanceof Integer) || !(lhs.val() instanceof Integer)) {
                    jjcError(node, "the last elements of memory aren't valid integers");
        }
        boolean newVal = ((int) lhs.val()) > ((int) rhs.val());
        memory.empiler(new Quadruplet(null, newVal, OBJ.CST, SORTE.INT));

        n++;
        node.next().accept(this);
    }

    //< q1.q2.m,a> |- swap -> < q2.q1.m, a+1>
    @Override
    public void visit(JcSwapNode node) throws Exception {
        n++;
        memory.echanger();
        node.next().accept(this);
    }

    //<< w, v, cst, w>.m,a> |- write -> <Afficher(v,m), a+1>
    @Override
    public void visit(JcWriteNode node) throws Exception {
        Quadruplet quad = memory.depiler();

        controller.write(quad.val().toString());

        n++;
        node.next().accept(this);
    }

    //<< w, v, cst, w>.m,a> |- writeln -> <AfficherLn(v,m), a+1>
    @Override
    public void visit(JcWriteLnNode node) throws Exception {
        Quadruplet quad = memory.depiler();

        controller.writeLn(quad.val().toString());

        n++;
        node.next().accept(this);
    }

}