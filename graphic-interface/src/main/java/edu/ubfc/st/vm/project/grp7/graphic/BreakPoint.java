package edu.ubfc.st.vm.project.grp7.graphic;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.reactfx.value.Val;

import java.util.function.IntFunction;

class BreakPoint implements IntFunction<Node> {
    private final ObservableValue<Integer> shownLine;

    BreakPoint(ObservableValue<Integer> shownLine) {
        this.shownLine = shownLine;
    }

    @Override
    public Node apply(int lineNumber) {
        Polygon triangle = new Polygon(0.0, 0.0, 10.0, 5.0, 0.0, 10.0);
        triangle.setFill(Color.RED);

        ObservableValue<Boolean> visible = Val.map(
                shownLine,
                sl -> sl == lineNumber);

        triangle.visibleProperty().bind(((Val<Boolean>) visible).conditionOnShowing(triangle));

        return triangle;
    }
}