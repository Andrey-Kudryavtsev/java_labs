package ru.nsu.kudryavtsev.andrey.view.textView;

import ru.nsu.kudryavtsev.andrey.enums.GameState;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.model.GameModel;
import ru.nsu.kudryavtsev.andrey.view.View;

import java.io.IOException;

public class TextView implements View
{
    public TextView()
    {
    }

    private void clearConsole()
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GameModel model)
    {
        GameState gameState = model.getGameState();

        clearConsole();
        System.out.println();
        switch (gameState)
        {
            case MENU -> {
                drawMenu(model);
            }
            case ABOUT -> {
                drawAbout(model);
            }
            case HIGH_SCORES -> {
                drawHighScores(model);
            }
            case PLAY -> {
                drawGame(model);
            }
            case GAME_OVER -> {
                drawGameOver(model);
            }
        }
    }

    @Override
    public void gameModelChanged(GameModel model)
    {
        // можно добавить в аргументы модель и проверять на соответствие
        draw(model);
    }

    private void drawMenu(GameModel model)
    {
        System.out.println("\t\t\t\tBOMBERMAN\n\n"                       +
                           "\t\t\tYou can enter following commands:\n"   +
                           "\t\t\tPLAY\t\tstart the game\n"              +
                           "\t\t\tABOUT\t\tshow info about the game\n"   +
                           "\t\t\tSCORE\t\tshow high scores\n"           +
                           "\t\t\tEXIT\t\texit the game\n"              );
    }

    private void drawAbout(GameModel model)
    {
        System.out.println("\t\t\t\tBOMBERMAN\n\n"               +
                           "\t\t\tAuthor: Andrey Kudryavtsev\n\n\n"  +
                           "\t\t\t\tAll rights reserved\n"        );
    }

    private void drawHighScores(GameModel model)
    {

    }

    private void drawGame(GameModel model)
    {
        Field field = model.getField();
        for (int i = 0; i < field.getHeight(); ++i)
        {
            System.out.print("| ");
            for (int j = 0; j < field.getWidth(); ++j)
            {
                System.out.print(field.getCurrTile(j, i).getForm() + " ");
            }
            System.out.println("|");
        }
//        System.out.println("Score:");
//        for (int i = 0; i < GameConfiguration.MAX_PLAYERS; ++i)
//        {
//            System.out.println("Player " + i + ": " + model.getScore(i));
//        }
    }

    private void drawWin(GameModel model)
    {
        System.out.println("Winner winner chicken dinner!");
    }

    private void drawGameOver(GameModel model)
    {
        System.out.println("GAME OVER");
//        int winnerID = model.getWinnerID();
//        if (winnerID == -2)
//            System.out.println("Draw");
//        else
//            System.out.println("Player " + (model.getWinnerID()+1) + " win!\n\n" +
//                               "Score:\t" + model.getScore(winnerID) + "\n");
    }
}
