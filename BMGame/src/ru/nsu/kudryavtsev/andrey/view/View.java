package ru.nsu.kudryavtsev.andrey.view;

import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.model.GameModelListener;

import java.io.IOException;

public interface View extends GameModelListener
{
    void draw(GameModel model);
}
