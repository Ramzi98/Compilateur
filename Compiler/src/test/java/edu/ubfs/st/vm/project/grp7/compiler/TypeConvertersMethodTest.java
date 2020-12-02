//package edu.ubfs.st.vm.project.grp7.compiler;
//
//import edu.ubfc.st.vm.project.grp7.ast.Breakpoint;
//import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
//import edu.ubfc.st.vm.project.grp7.compiler.Compiler;
//import edu.ubfc.st.vm.project.grp7.compiler.CompilerImpl;
//import edu.ubfc.st.vm.project.grp7.compiler.CompilerVisitor;
//import edu.ubfc.st.vm.project.grp7.jaja.code.ast.JajaCodeNode;
//import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcInitNode;
//import edu.ubfc.st.vm.project.grp7.jaja.code.ast.node.JcNewNode;
//import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaNode;
//import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Stack;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//public class TypeConvertersMethodTest {
//    public CompilerVisitor compiler;
//
//    @Before
//    public void start() {
//        compiler = new CompilerVisitor();
//        Stack<HashMap<MiniJajaNode, Integer>> stack = new Stack<>();
//        MiniJajaNode classe = new MiniJajaNode() {
//            @Override
//            public Breakpoint breakpoint() {
//                return null;
//            }
//
//            @Override
//            public MiniJajaNode children(int n) throws IndexOutOfBoundsException {
//                return null;
//            }
//
//            @Override
//            public int line() {
//                return 0;
//            }
//
//            @Override
//            public int column() {
//                return 0;
//            }
//        };
//
//        ArrayList<HashMap<MiniJajaNode,Integer>> miniJajaNodes = new ArrayList<>();
//        HashMap<MiniJajaNode,Integer>startingHash = new HashMap<>();
//        startingHash.put(classe,1);
//        stack.push(startingHash);
//        miniJajaNodes.add(startingHash);
//        compiler.setStack(stack);
//        compiler.setMinijajaNodes(miniJajaNodes);
//        ArrayList<JajaCodeNode> jjnodes = new ArrayList<>();
//        JcInitNode init = JcInitNode.builder().build();
//        jjnodes.add(init);
//        compiler.setJajaCodeNodes(jjnodes);
//
//    }
//
//    @Test
//    public void TypeConverterMethodTest() throws IOException, IllFormedNodeException {
//
//        TypeNode type1 = TypeNode.builder()
//                .line(1)
//                .column(0)
//                .value(TypeNode.Type.INT)
//                .build();
//
//        TypeNode type2 = TypeNode.builder()
//                .line(1)
//                .column(0)
//                .value(TypeNode.Type.BOOLEAN)
//                .build();
//
//        TypeMethNode type3 = TypeMethNode.builder()
//                .line(1)
//                .column(0)
//                .value(TypeMethNode.TypeMeth.INT)
//                .build();
//
//        TypeMethNode type4 = TypeMethNode.builder()
//                .line(1)
//                .column(0)
//                .value(TypeMethNode.TypeMeth.BOOLEAN)
//                .build();
//
//        TypeMethNode type5 = TypeMethNode.builder()
//                .line(1)
//                .column(0)
//                .value(TypeMethNode.TypeMeth.VOID)
//                .build();
//
//        IdentNode ident = IdentNode.builder()
//                .value("i")
//                .build();
//
//        NumberNode expression = NumberNode.builder()
//                .value(1)
//                .build();
//
//        CstNode cstNode1 = CstNode.builder()
//                .identifier(ident)
//                .expression(expression)
//                .type(type1)
//                .build();
//
//        assertThat(compiler.getType(cstNode1.type().value()), is(JcNewNode.Type.INT));
//
//        CstNode cstNode2 = CstNode.builder()
//                .identifier(ident)
//                .expression(expression)
//                .type(type2)
//                .build();
//
//        assertThat(compiler.getType(cstNode2.type().value()), is(JcNewNode.Type.BOOLEAN));
//
//        MethodNode methodNode1 = MethodNode.builder()
//                .identifier(ident)
//                .typeMeth(type3)
//                .build();
//
//        assertThat(compiler.getType(methodNode1.typeMeth().value()), is(JcNewNode.Type.INT));
//
//        MethodNode methodNode3 = MethodNode.builder()
//                .identifier(ident)
//                .typeMeth(type4)
//                .build();
//
//        assertThat(compiler.getType(methodNode3.typeMeth().value()), is(JcNewNode.Type.BOOLEAN));
//
//        MethodNode methodNode4 = MethodNode.builder()
//                .identifier(ident)
//                .typeMeth(type5)
//                .build();
//
//        assertThat(compiler.getType(methodNode4.typeMeth().value()), is(JcNewNode.Type.VOID));
//    }
//}
