import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MiniJajaParserInstrTest extends MiniJajaParserBaseTest{

    @Test
    public void check__Instr__WhileEmpty() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWhileEmptyTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WhileNode whileNode = (WhileNode) listener.getRoot();
        assertThat(whileNode.line(),is(1));
        assertThat(whileNode.column(),is(0));

        NumberNode numberNode = (NumberNode)whileNode.expression();
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(6));
        assertThat(numberNode.value(),is(1));

        assertThat(whileNode.instrs(),is(nullValue()));
    }

    @Test
    public void check__Instr__While() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWhileTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WhileNode whileNode = (WhileNode) listener.getRoot();
        assertThat(whileNode.line(), is(1));
        assertThat(whileNode.column(), is(0));

        NumberNode numberNode = (NumberNode) whileNode.expression();
        assertThat(numberNode.line(), is(1));
        assertThat(numberNode.column(), is(6));
        assertThat(numberNode.value(), is(1));


        InstrsNode instrsNode = whileNode.instrs();
        WhileNode whileNode1 = (WhileNode)instrsNode.instruction();
        assertThat(whileNode1.line(), is(2));
        assertThat(whileNode1.column(), is(4));

        NumberNode numberNode1 = (NumberNode) whileNode1.expression();
        assertThat(numberNode1.line(), is(2));
        assertThat(numberNode1.column(), is(10));
        assertThat(numberNode1.value(), is(1));

        assertThat(whileNode1.instrs(), is(nullValue()));
    }

    @Test
    public void check__Instr__IfEmpty() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrIfEmptyTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        IfNode ifNode = (IfNode) listener.getRoot();
        assertThat(ifNode.line(), is(1));
        assertThat(ifNode.column(), is(0));

        IdentNode identNode = (IdentNode)ifNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.column(),is(3));
        assertThat(identNode.line(),is(1));

        InstrsNode instrsNode = ifNode.trueInstrs();
        assertThat(instrsNode,is(nullValue()));
    }

    @Test
    public void check__Instr__If() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrIfTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        IfNode ifNode = (IfNode) listener.getRoot();
        assertThat(ifNode.line(), is(1));
        assertThat(ifNode.column(), is(0));

        IdentNode identNode = (IdentNode)ifNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.column(),is(5));
        assertThat(identNode.line(),is(1));

        InstrsNode instrsNode = ifNode.trueInstrs();
        ReturnNode returnNode = (ReturnNode)instrsNode.instruction();
        assertThat(returnNode.column(),is(4));
        assertThat(returnNode.line(),is(2));

        IdentNode identNode1 = (IdentNode)returnNode.ret();
        assertThat(identNode1.column(),is(11));
        assertThat(identNode1.line(),is(2));
        assertThat(identNode1.value(),is("i"));
    }

    @Test
    public void check__Instr__IfElseEmpty() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrIfElseEmptyTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        IfNode ifNode = (IfNode) listener.getRoot();
        assertThat(ifNode.line(), is(1));
        assertThat(ifNode.column(), is(0));

        IdentNode identNode = (IdentNode)ifNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.column(),is(5));
        assertThat(identNode.line(),is(1));

        InstrsNode instrsNode = ifNode.trueInstrs();
        ReturnNode returnNode = (ReturnNode)instrsNode.instruction();
        assertThat(returnNode.column(),is(4));
        assertThat(returnNode.line(),is(2));

        IdentNode identNode1 = (IdentNode)returnNode.ret();
        assertThat(identNode1.column(),is(11));
        assertThat(identNode1.line(),is(2));
        assertThat(identNode1.value(),is("i"));

        InstrsNode instrsNode1 = ifNode.falseInstrs();
        assertThat(instrsNode1,is(nullValue()));
    }

    @Test
    public void check__Instr__IfEmptyElse() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrIfEmptyElseTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        IfNode ifNode = (IfNode) listener.getRoot();
        assertThat(ifNode.line(), is(1));
        assertThat(ifNode.column(), is(0));

        IdentNode identNode = (IdentNode)ifNode.expression();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.column(),is(5));
        assertThat(identNode.line(),is(1));

        InstrsNode instrsNode = ifNode.falseInstrs();
        ReturnNode returnNode = (ReturnNode)instrsNode.instruction();
        assertThat(returnNode.column(),is(4));
        assertThat(returnNode.line(),is(4));

        IdentNode identNode1 = (IdentNode)returnNode.ret();
        assertThat(identNode1.column(),is(11));
        assertThat(identNode1.line(),is(4));
        assertThat(identNode1.value(),is("i"));

        InstrsNode instrsNode1 = ifNode.trueInstrs();
        assertThat(instrsNode1,is(nullValue()));
    }

    @Test
    public void check__Instr__WriteIdent() throws IOException {
        TestConstructor testConstructor = new TestConstructor("write (i)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WriteNode writeNode = (WriteNode) listener.getRoot();
        assertThat(writeNode.line(), is(1));
        assertThat(writeNode.column(), is(0));

        IdentNode identNode = (IdentNode)writeNode.printable();
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(7));
        assertThat(identNode.value(),is("i"));
    }

    @Test
    public void check__Instr__WriteLnIdent() throws IOException {
        TestConstructor testConstructor = new TestConstructor("writeln (i)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WriteLnNode writeLnNode = (WriteLnNode) listener.getRoot();
        assertThat(writeLnNode.line(), is(1));
        assertThat(writeLnNode.column(), is(0));

        IdentNode identNode = (IdentNode)writeLnNode.printable();
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(9));
        assertThat(identNode.value(),is("i"));
    }
//
    @Test
    public void check__Instr__WriteString() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWriteStringTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WriteNode writeNode = (WriteNode) listener.getRoot();
        assertThat(writeNode.line(), is(1));
        assertThat(writeNode.column(), is(0));

        StringNode stringNode = (StringNode) writeNode.printable();
        assertThat(stringNode.line(),is(1));
        assertThat(stringNode.column(),is(7));
        assertThat(stringNode.value(),is("coucou"));
    }

    @Test
    public void check__Instr__WriteLnString() throws IOException {
        TestConstructor testConstructor = new TestConstructor("instr","InstrWriteLnStringTest");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        WriteLnNode writeLnNode = (WriteLnNode) listener.getRoot();
        assertThat(writeLnNode.line(), is(1));
        assertThat(writeLnNode.column(), is(0));

        StringNode stringNode = (StringNode) writeLnNode.printable();
        assertThat(stringNode.line(),is(1));
        assertThat(stringNode.column(),is(9));
        assertThat(stringNode.value(),is("coucou"));
    }

    @Test
    public void check__Instr__return() throws IOException {
        TestConstructor testConstructor = new TestConstructor("return i");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        ReturnNode returnNode = (ReturnNode) listener.getRoot();
        assertThat(returnNode.line(), is(1));
        assertThat(returnNode.column(), is(0));

        IdentNode identNode = (IdentNode)returnNode.ret();
        assertThat(identNode.line(), is(1));
        assertThat(identNode.column(), is(7));
        assertThat(identNode.value(),is("i"));
    }

    @Test
    public void check__Instr__IdentListExp() throws IOException {
        TestConstructor testConstructor = new TestConstructor("i ( j, k)");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        AppelINode appelINode = (AppelINode) listener.getRoot();
        assertThat(appelINode.line(), is(1));
        assertThat(appelINode.column(), is(0));

        IdentNode identNode = appelINode.identifier();
        assertThat(identNode.value(),is("i"));

        ListExpNode listExpNode = appelINode.listexp();
        IdentNode identNode1 = (IdentNode)listExpNode.expression();
        assertThat(identNode1.value(),is("j"));

        ListExpNode listExpNode1 = listExpNode.listexp();
        IdentNode identNode2 = (IdentNode)listExpNode1.expression();
        assertThat(identNode2.value(),is("k"));

        assertThat(listExpNode1.listexp(),is(nullValue()));
    }

    @Test
    public void check__Instr__Inc() throws IOException {
        TestConstructor testConstructor = new TestConstructor("i++");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        IncrementNode incrementNode = (IncrementNode) listener.getRoot();
        assertThat(incrementNode.line(),is(1));
        assertThat(incrementNode.column(),is(0));

        IdentNode identNode = (IdentNode)incrementNode.identifier();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));
    }

    @Test
    public void check__Instr__Add() throws IOException {
        TestConstructor testConstructor = new TestConstructor("i+=0");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        SumNode sumNode  = (SumNode) listener.getRoot();
        assertThat(sumNode.line(),is(1));
        assertThat(sumNode.column(),is(0));

        IdentNode identNode = (IdentNode)sumNode.identifier();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));

        NumberNode numberNode = (NumberNode)sumNode.expression();
        assertThat(numberNode.value(),is(0));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(3));
    }

    @Test
    public void check__Instr__Assign() throws IOException {
        TestConstructor testConstructor = new TestConstructor("i=0");
        parser = testConstructor.getParser();
        walker.walk(listener, parser.instr());

        AssignNode assignNode  = (AssignNode) listener.getRoot();
        assertThat(assignNode.line(),is(1));
        assertThat(assignNode.column(),is(0));

        IdentNode identNode = (IdentNode)assignNode.identifier();
        assertThat(identNode.value(),is("i"));
        assertThat(identNode.line(),is(1));
        assertThat(identNode.column(),is(0));

        NumberNode numberNode = (NumberNode)assignNode.expression();
        assertThat(numberNode.value(),is(0));
        assertThat(numberNode.line(),is(1));
        assertThat(numberNode.column(),is(2));
    }

}
