package edu.ubfc.st.vm.project.grp7.compiler.printer;

import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeASTVisitor;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.*;

import java.util.List;

public class JCCPrinter extends JajaCodeASTVisitor {
    private final List<JajaCodeNode> nodes;
    private final StringBuilder builder;

    public JCCPrinter(List<JajaCodeNode> nodes) throws Exception {
        this.nodes = nodes;
        this.builder = new StringBuilder();
        if (nodes.isEmpty()) {
            throw new IllegalStateException("Cannot Print an Empty JajaCode program");
        }
        this.nodes.get(0).accept(this);
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    @Override
    public void visit(JcInitNode node) throws Exception {
        builder.append("init\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcAddNode node) throws Exception {
        builder.append("add\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcAincNode node) throws Exception {
        builder.append("ainc(").append(node.identifier()).append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcAloadNode node) throws Exception {
        builder.append("aload(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcAndNode node) throws Exception {
        builder.append("and\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcAstoreNode node) throws Exception {
        builder.append("astore(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcCmpNode node) throws Exception {
        builder.append("cmp\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcDivNode node) throws Exception {
        builder.append("div\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcGotoNode node) throws Exception {
        builder.append("ainc(")
                .append(node.adresse()+1)
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcIfNode node) throws Exception {
        builder.append("if(")
                .append(node.adresse() + 1)
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcIncNode node) throws Exception {
        builder.append("inc(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcInvokeNode node) throws Exception {
        builder.append("invoke(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcLoadNode node) throws Exception {
        builder.append("load(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcMulNode node) throws Exception {
        builder.append("mul\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcNegNode node) throws Exception {
        builder.append("neg\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcNewarrayNode node) throws Exception {
        builder.append("newarray(")
                .append(node.identifier())
                .append(", ")
                .append(node.type().toString())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcNewNode node) throws Exception {
        builder.append("new(")
                .append(node.identifier())
                .append(", ")
                .append(node.type().toString())
                .append(", ")
                .append(node.sorte().toString())
                .append(", ")
                .append(node.depth())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcNopNode node) throws Exception {
        builder.append("nop\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcNotNode node) throws Exception {
        builder.append("not\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcOrNode node) throws Exception {
        builder.append("or\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcPopNode node) throws Exception {
        builder.append("pop\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcPushNode node) throws Exception {
        builder.append("push(")
                .append(node.valeur())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcReturnNode node) throws Exception {
        builder.append("return\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcStopNode node) throws Exception {
        builder.append("jcstop\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcStoreNode node) throws Exception {
        builder.append("store(")
                .append(node.identifier())
                .append(")\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcSubNode node) throws Exception {
        builder.append("sub\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcSupNode node) throws Exception {
        builder.append("sup\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcSwapNode node) throws Exception {
        builder.append("swap\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcWriteNode node) throws Exception {
        builder.append("write\n");
        node.next().accept(this);
    }

    @Override
    public void visit(JcWriteLnNode node) throws Exception {
        builder.append("writeln\n");
        node.next().accept(this);
    }
}