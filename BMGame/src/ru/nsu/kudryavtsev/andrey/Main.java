package ru.nsu.kudryavtsev.andrey;

import ru.nsu.kudryavtsev.andrey.controller.GraphicController;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.view.graphicView.GraphicView;
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            int playerID = 0;
            CommandFactory commandFactory = new CommandFactory("config");
            GameModel model = new GameModel();

            GraphicController controller = new GraphicController(playerID, model, commandFactory);
            GraphicView gv = new GraphicView(playerID, controller);
            model.addListener(gv);
            Thread gameThread = new Thread(model);
            gameThread.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
