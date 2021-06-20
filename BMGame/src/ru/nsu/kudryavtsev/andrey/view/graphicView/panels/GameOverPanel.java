package ru.nsu.kudryavtsev.andrey.view.graphicView.panels;

import ru.nsu.kudryavtsev.andrey.view.graphicView.GridBagHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel
{
    private JLabel gameOverInfo = new JLabel();

    public GameOverPanel(ActionListener actionListener)
    {
        setLayout(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();
        setBackground(new Color(250, 150, 20));

        gameOverInfo.setFont(new Font("SERIF", Font.PLAIN, 30));
        add(gameOverInfo, helper.get());
        helper.nextRow();
        helper.gapTop(40);

        // add menu button
        JButton backButton = new JButton("MENU");
        backButton.setActionCommand("MENU");
        backButton.addActionListener(actionListener);
        backButton.setBackground(new Color(255, 100, 12));
        backButton.setPreferredSize(new Dimension(210, 70));
        backButton.setMinimumSize(new Dimension(210, 70));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(backButton, helper.get());
    }

    public void preparePanel(String gameOverInfo)
    {
        this.gameOverInfo.setText(gameOverInfo);
    }
}
