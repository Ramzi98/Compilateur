package edu.ubfc.st.vm.project.grp7.tool.yaml;

import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import edu.ubfc.st.vm.project.grp7.ast.IllFormedNodeException;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.MiniJajaASTVisitor;
import edu.ubfc.st.vm.project.grp7.mini.jaja.ast.node.*;

import java.io.IOException;

public class MiniJajaAstToYamlVisitor extends MiniJajaASTVisitor {
    private final YAMLGenerator generator;
    public MiniJajaAstToYamlVisitor(YAMLGenerator generator) throws IOException {
        super();
        this.generator = generator;
        if (this.generator == null) {
            throw new IOException("Impossible to serialize Yaml without a valid generator");
        }
    }

    @Override
    public void visit(ClasseNode node) throws IllFormedNodeException, IOException {

        if (node == null) {
            throw new IllFormedNodeException("ClassNode shouldn't be null ");
        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("ClassNode");

            generator.writeFieldName("identifier");
                try {
                    node.identifier().accept(this);
                }catch (IllFormedNodeException | IOException e){
                    throw e;
                }catch (Exception e) {
                    throw new IllFormedNodeException(
                            node.identifier().line(),
                            node.identifier().column(),
                            e.getMessage()
                    );
                }
            try {
                if(node.decls() != null) {
                    generator.writeFieldName("decls");
                    node.decls().accept(this);
                }
            }catch (IllFormedNodeException | IOException e){
                throw e;
            }catch (Exception e) {
                throw new IllFormedNodeException(
                        node.decls().line(),
                        node.decls().column(),
                        e.getMessage()
                );
            }
            generator.writeFieldName("main");
            try {
                node.methmain().accept(this);
            }catch (IllFormedNodeException | IOException e){
                throw e;
            }catch (Exception e) {
                throw new IllFormedNodeException(
                        node.methmain().line(),
                        node.methmain().column(),
                        e.getMessage()
                );
            }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(IdentNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.value() == null) {
            throw new IllFormedNodeException("IdentNode shouldn't be null or embed a null string");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("IdentNode");
                this.generator.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
                generator.writeStringField("identifier", node.value());
                this.generator.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(DeclsNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("DeclsNode");
        generator.writeFieldName("decl");
        try {
                node.decl().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.decl().line(),
                    node.decl().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.decls() != null) {
                generator.writeFieldName("decls");
                node.decls().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.decls().line(),
                    node.decls().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("VarsNode");
        generator.writeFieldName("var");
        try {
            node.var().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.var().line(),
                    node.var().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.vars() != null) {
                generator.writeFieldName("vars");
                node.vars().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.vars().line(),
                    node.vars().column(),
                    e.getMessage()
            );
        }

        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.typeMeth() == null){
            throw new IllFormedNodeException("VarNode shouldn't be null or embed a null identifier or type");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("VarNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("type");
        try {

                node.typeMeth().accept(this);
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.typeMeth().line(),
                    node.typeMeth().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.expression() != null) {
                generator.writeFieldName("expression");
                node.expression().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.typeMeth() == null ){
            throw new IllFormedNodeException("ArrayNode shouldn't be null or embed a null identifier or type");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("ArrayNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("type");
        try {

            node.typeMeth().accept(this);
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.typeMeth().line(),
                    node.typeMeth().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("expression");
        try {
            if(node.expression() != null) {
                node.expression().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.type() == null || node.expression() == null ){
            throw new IllFormedNodeException("CstNode shouldn't be null or embed a null identifier or type or expression");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("CstNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("type");
        try {

            node.type().accept(this);
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.type().line(),
                    node.type().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("expression");
        try {
                node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.typeMeth() == null){
            throw new IllFormedNodeException("MethodNode shouldn't be null or embed a null identifieror a null type ");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("MethodNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("typeMeth");
        try {
            node.typeMeth().accept(this);
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.typeMeth().line(),
                    node.typeMeth().column(),
                    e.getMessage()
            );
        }
        try {

            if(node.headers() != null) {
                generator.writeFieldName("headers");
                node.headers().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.headers().line(),
                    node.headers().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.vars() != null) {
                generator.writeFieldName("vars");
                node.vars().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.vars().line(),
                    node.vars().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.instrs() != null) {
                generator.writeFieldName("instrs");
                node.instrs().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.instrs().line(),
                    node.instrs().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException {

        if(node == null){
            throw new IllFormedNodeException("MainNode shouldn't be null ");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("MainNode");
        try {
            if(node.vars() != null) {
                generator.writeFieldName("vars");
                node.vars().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.vars().line(),
                    node.vars().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.instrs() != null) {
                generator.writeFieldName("instrs");
                node.instrs().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.instrs().line(),
                    node.instrs().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("HeadersNode");
        generator.writeFieldName("header");
        try {
            node.header().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.header().line(),
                    node.header().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.headers() != null) {
                generator.writeFieldName("headers");
                node.headers().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.headers().line(),
                    node.headers().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.type() == null){
            throw new IllFormedNodeException("Header shouldn't be null or embed a null identifier or type");

        }
        generator.writeStartObject();
        generator.writeObjectFieldStart("HeaderNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("type");
        try {

            node.type().accept(this);
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.type().line(),
                    node.type().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("InstrsNode");
        try {
            if(node.instruction() != null) {
                generator.writeFieldName("instruction");
                node.instruction().accept(this);
            }

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.instruction().line(),
                    node.instruction().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.instrs() != null) {
                generator.writeFieldName("instrs");
                node.instrs().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.instrs().line(),
                    node.instrs().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.expression() == null){
            throw new IllFormedNodeException("AssignNode shouldn't be null or embed a null identifier or expression");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("AssignNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("expression");
        try {
                node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.expression() == null ){
            throw new IllFormedNodeException("SumNode shouldn't be null or embed a null identifier or expression");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("SumNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null){
            throw new IllFormedNodeException("IncrementNode shouldn't be null or embed a null identifier ");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("IncrementNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException {
        if(node == null || node.identifier() == null ){
            throw new IllFormedNodeException("AppelINode shouldn't be null or embed a null identifier ");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("AppelINode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.listexp() !=null) {
                generator.writeFieldName("listexp");
                node.listexp().accept(this);
            }

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.listexp().line(),
                    node.listexp().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException {

        if(node == null ){
            throw new IllFormedNodeException("ReturnNode shouldn't be null");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("RetrunNode");
        generator.writeFieldName("ret");
        try {
            node.ret().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.ret().line(),
                    node.ret().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(WriteNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.printable() == null) {
            throw new IllFormedNodeException("WriteNode shouldn't be null or embed a null printable");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("WriteNode");
                generator.writeFieldName("printable");
                try {
                    node.printable().accept(this);
                }catch (IllFormedNodeException | IOException e){
                    throw e;
                }catch (Exception e) {
                    throw new IllFormedNodeException(
                            node.printable().line(),
                            node.printable().column(),
                            e.getMessage()
                    );
                }
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(WriteLnNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.printable() == null) {
            throw new IllFormedNodeException("WriteLnNode shouldn't be null or embed a null printable");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("WriteLnNode");
                generator.writeFieldName("printable");
                    try {
                        node.printable().accept(this);
                    }catch (IllFormedNodeException | IOException e){
                        throw e;
                    }catch (Exception e) {
                        throw new IllFormedNodeException(
                                node.printable().line(),
                                node.printable().column(),
                                e.getMessage()
                        );
                    }
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(StringNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.value() == null) {
            throw new IllFormedNodeException("StringNode shouldn't be null or embed a null string");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("StringNode");
                this.generator.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
                generator.writeStringField("chaine", node.value());
                this.generator.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(IfNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.expression() == null ){
            throw new IllFormedNodeException("IfNode shouldn't be null or embed a null expression");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("IfNode");
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("trueInstrs");
        try {
            node.trueInstrs().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.trueInstrs().line(),
                    node.trueInstrs().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("falseInstrs");
        try {
            node.falseInstrs().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.falseInstrs().line(),
                    node.falseInstrs().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.expression() == null ){
            throw new IllFormedNodeException("WhileNode shouldn't be null or embed a null expression");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("WhileNode");
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("instrs");
        try {
            node.instrs().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.instrs().line(),
                    node.instrs().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("ListExpNode");
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.listexp() != null) {
                generator.writeFieldName("listexp");
                node.listexp().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.listexp().line(),
                    node.listexp().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("NotNode");
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("AndNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("OrNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("EqualsNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("GreaterNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("PlusNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("subNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("MinusNode");
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("MultNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {

        generator.writeStartObject();
        generator.writeObjectFieldStart("DivNode");
        generator.writeFieldName("leftOperand");
        try {
            node.leftOperand().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.leftOperand().line(),
                    node.leftOperand().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.rightOperand() != null) {
                generator.writeFieldName("rightOperand");
                node.rightOperand().accept(this);
            }
        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.rightOperand().line(),
                    node.rightOperand().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null ){
            throw new IllFormedNodeException("AppelENode shouldn't be null or embed a null identifier ");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("AppelENode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        try {
            if(node.listexp() !=null) {
                generator.writeFieldName("listexp");
                node.listexp().accept(this);
            }

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.listexp().line(),
                    node.listexp().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();

    }

    @Override
    public void visit(BooleanNode node) throws IllFormedNodeException, IOException {
        if (node == null) {
            throw new IllFormedNodeException("BooleanNode shouldn't be null");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("BooleanNode");
                generator.writeBooleanField("value", node.value());
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(NumberNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.value() == null) {
            throw new IllFormedNodeException("NumberNode shouldn't be null or embed a null value");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("NumberNode");
                generator.writeNumberField("value", node.value());
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(ArrayItemNode node) throws IllFormedNodeException, IOException {

        if(node == null || node.identifier() == null || node.expression() == null){
            throw new IllFormedNodeException("ArrayItemNode shouldn't be null or embed a null identifier or expression ");

        }

        generator.writeStartObject();
        generator.writeObjectFieldStart("ArrayItemNode");
        generator.writeFieldName("identifier");
        try {
            node.identifier().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.identifier().line(),
                    node.identifier().column(),
                    e.getMessage()
            );
        }
        generator.writeFieldName("expression");
        try {
            node.expression().accept(this);

        }catch (IllFormedNodeException | IOException e){
            throw e;
        }catch (Exception e) {
            throw new IllFormedNodeException(
                    node.expression().line(),
                    node.expression().column(),
                    e.getMessage()
            );
        }
        generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(TypeMethNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.value() == null) {
            throw new IllFormedNodeException("TypeMethNode shouldn't be null or embed a null type");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("TypeMethNode");
                this.generator.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
                generator.writeStringField("type", node.value().toString());
                this.generator.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
            generator.writeEndObject();
        generator.writeEndObject();
    }

    @Override
    public void visit(TypeNode node) throws IllFormedNodeException, IOException {
        if (node == null || node.value() == null) {
            throw new IllFormedNodeException("TypeNode shouldn't be null or embed a null type");
        }
        generator.writeStartObject();
            generator.writeObjectFieldStart("TypeNode");
                this.generator.enable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
                generator.writeStringField("type", node.value().toString());
                this.generator.disable(YAMLGenerator.Feature.MINIMIZE_QUOTES);
            generator.writeEndObject();
        generator.writeEndObject();
    }
}
