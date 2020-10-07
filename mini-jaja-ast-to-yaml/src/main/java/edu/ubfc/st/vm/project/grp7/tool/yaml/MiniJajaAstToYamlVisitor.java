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

    }

    @Override
    public void visit(VarsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(VarNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ArrayNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(CstNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MethodNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MainNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeadersNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(HeaderNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(InstrsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AssignNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SumNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(IncrementNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelINode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ReturnNode node) throws IllFormedNodeException, IOException {

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

    }

    @Override
    public void visit(WhileNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(ListExpNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(NotNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AndNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(OrNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(EqualsNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(GreaterNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(PlusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(SubNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MinusNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(MultNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(DivNode node) throws IllFormedNodeException, IOException {

    }

    @Override
    public void visit(AppelENode node) throws IllFormedNodeException, IOException {

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
