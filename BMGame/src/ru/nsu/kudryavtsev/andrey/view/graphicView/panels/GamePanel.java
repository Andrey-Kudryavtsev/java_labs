package ru.nsu.kudryavtsev.andrey.view.graphicView.panels;

import ru.nsu.kudryavtsev.andrey.commands.Command;
import ru.nsu.kudryavtsev.andrey.commands.Task;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.field.Field;
import ru.nsu.kudryavtsev.andrey.model.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel
{
    private Field field;
    private int width;
    private int height;

    public GamePanel(ActionListener actionListener)
    {

    }

    public void preparePanel(Field field)
    {
        width = field.getWidth();
        height = field.getHeight();
        setLayout(new GridLayout(height, width));
        this.field = field;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(250, 150, 20));
        g.fillRect(0, 0, getWidth(), getHeight());
//        setLayout(new GridLayout(height, width));
        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
//                JLabel tile = new JLabel();
//                tile.setOpaque(true);
//                tile.setBackground(field.getCurrTile(x, y).getColor());
//                add(tile);
                g.setColor(field.getCurrTile(x, y).getColor());
                g.fillRect(x * getWidth() / width, y * getHeight() / height, getWidth() / width + 1, getHeight() / height + 1);
            }
        }
        g2d.dispose();
    }
}
