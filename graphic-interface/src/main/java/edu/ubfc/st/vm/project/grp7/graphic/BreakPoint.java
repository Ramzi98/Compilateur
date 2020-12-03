package edu.ubfc.st.vm.project.grp7.graphic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.reactfx.value.Val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntFunction;

class BreakPoint implements IntFunction<Node> {
    private final ObservableValue<Integer> shownLine;
    int line = -1;
    boolean checked = false;

    List<CheckBoxWithLine> lineCheckBoxArrayList = new ArrayList<>();

    BreakPoint(ObservableValue<Integer> shownLine) {
        this.shownLine = shownLine;
        this.line = shownLine.getValue();
    }

    public int returnLineIfChecked(){
        if (checked){
            return line;
        }
        return -1;
    }

    @Override
    public Node apply(int lineNumber) {
        CheckBoxWithLine checkBoxWithLine = new CheckBoxWithLine(lineNumber+1);
        lineCheckBoxArrayList.add(checkBoxWithLine);
        return checkBoxWithLine;
    }

    public List<Integer> returnCheckedLine(){
        List<Integer> lineList = new ArrayList<>();
        for (CheckBoxWithLine c : lineCheckBoxArrayList){
            if (c.isSelected()){
                System.out.println("line : "+c.getLine());
                lineList.add(c.getLine());
            }
        }
        return lineList;

    }

}

class CheckBoxWithLine extends  CheckBox{
    private int line;

    public CheckBoxWithLine(int line){
        super();
        this.line = line;
    }

    public int getLine(){return this.line;}

}