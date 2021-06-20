package ru.nsu.kudryavtsev.andrey.view.graphicView;

import javax.swing.*;
import java.awt.*;

public class GridBagHelper {
    // координаты текущей ячейки
    private int gridx, gridy;
    // настраиваемый объект GridBagConstraints
    private GridBagConstraints constraints = new GridBagConstraints();

    // возвращает настроенный объект GridBagConstraints
    public GridBagConstraints get() {
        return constraints;
    }
    // двигается на следующую ячейку
    public GridBagHelper nextCell() {
        constraints = new GridBagConstraints();
        constraints.gridx = gridx++;
        constraints.gridy = gridy;
        // для удобства возвращаем себя
        return this;
    }
    // двигается на следующий ряд
    public GridBagHelper nextRow() {
        constraints = new GridBagConstraints();
        gridy++;
        gridx = 0;
        constraints.gridx = 0;
        constraints.gridy = gridy;
        return this;
    }
    // раздвигает ячейку до конца строки
    public GridBagHelper span() {
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        return this;
    }
    // заполняет ячейку по горизонтали
    public GridBagHelper fillHorizontally() {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        return this;
    }
    // вставляет распорку справа
    public GridBagHelper gapRight(int size) {
        constraints.insets.right = size;
        return this;
    }

    public GridBagHelper gapLeft(int size) {
        constraints.insets.left = size;
        return this;
    }

    public GridBagHelper gapTop(int size) {
        constraints.insets.top = size;
        return this;
    }

    public GridBagHelper gapBottom(int size) {
        constraints.insets.bottom = size;
        return this;
    }

    public GridBagHelper spanY() {
        constraints.gridheight = GridBagConstraints.REMAINDER;
        return this;
    }


    public GridBagHelper fillBoth() {
        constraints.fill = GridBagConstraints.BOTH;
        return this;
    }

    public GridBagHelper alignLeft() {
        constraints.anchor = GridBagConstraints.LINE_START;
        return this;
    }

    public GridBagHelper alignRight() {
        constraints.anchor = GridBagConstraints.LINE_END;
        return this;
    }

    public GridBagHelper alignTop() {
        constraints.anchor = GridBagConstraints.NORTH;
        return this;
    }

    public GridBagHelper alignBottom() {
        constraints.anchor = GridBagConstraints.SOUTH;
        return this;
    }

    public GridBagHelper setInsets(int left, int top, int right, int bottom) {
        Insets i = new Insets(top, left, bottom, right);
        constraints.insets = i;
        return this;
    }

    public GridBagHelper setWeights(float horizontal, float vertical) {
        constraints.weightx = horizontal;
        constraints.weighty = vertical;
        return this;
    }

    public void insertEmptyRow(Container c, int height) {
        Component comp = Box.createVerticalStrut(height);
        nextCell().nextRow().fillHorizontally().span();
        c.add(comp, get());
        nextRow();
    }

    public void insertEmptyFiller(Container c) {
        Component comp = Box.createGlue();
        nextCell().nextRow().fillBoth().span().spanY().setWeights(1.0f, 1.0f);
        c.add(comp, get());
        nextRow();
    }
}