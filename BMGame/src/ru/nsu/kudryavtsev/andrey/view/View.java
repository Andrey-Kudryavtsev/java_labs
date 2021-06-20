package ru.nsu.kudryavtsev.andrey.view;

import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.model.GameModelListener;

public interface View extends GameModelListener
{
    void draw(GameModel model);
}
