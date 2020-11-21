package edu.ubfs.st.vm.project.grp7.compiler;

import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.IdentNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.NumberNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.TypeMethNode;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.VarNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class CompilerVisitorTest {
 public CompilerVisitor compiler;

    @Before
    public void start(){

         compiler = new CompilerVisitor();

    }


    @Test

    public void NodeVarCompilerVisitor() throws IOException, IllFormedNodeException {

        TypeMethNode typeMeth = TypeMethNode.builder().line(1).column(0).value(TypeMethNode.TypeMeth.INT).build();
        IdentNode ident = IdentNode.builder().value("I").build();
        NumberNode expression = NumberNode.builder().value(2.5).build();
        VarNode varNode = VarNode.builder().line(1).column(0).typeMeth(typeMeth).identifier(ident).expression(expression).build();

        compiler.visit(varNode);



    }
}
