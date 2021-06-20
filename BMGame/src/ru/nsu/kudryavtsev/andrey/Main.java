package ru.nsu.kudryavtsev.andrey;

import ru.nsu.kudryavtsev.andrey.controller.ConsoleController;
import ru.nsu.kudryavtsev.andrey.controller.Controller;
import ru.nsu.kudryavtsev.andrey.controller.GraphicController;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.field.DefaultField;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.view.graphicView.GraphicView;
import ru.nsu.kudryavtsev.andrey.view.textView.TextView;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            int playerID = 0;
            CommandFactory commandFactory = new CommandFactory("config");
            Field field = new DefaultField("map1.txt");
            GameModel model = new GameModel();

            GraphicController controller = new GraphicController(playerID, model, commandFactory);
            GraphicView gv = new GraphicView(playerID, controller);
            model.addListener(gv);
//            TextView tv = new TextView();
//            model.startConsoleGame();
//            model.addListener(tv);
//            int ID = model.registerPlayer();
//            Controller controller = new ConsoleController(0, model, commandFactory);
            Thread gameThread = new Thread(model);
            gameThread.start();
            controller.waitForInput();

//            Timer t = new Timer();
//            t.schedule(model, 0, 1000);
//            Controller controller = new Controller(model);
//            System.out.println("START CONTROLLER");
//            controller.doTasks();
//            Thread.currentThread().sleep(10000);
//            model.stopGameLoop();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
