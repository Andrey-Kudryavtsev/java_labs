package ru.nsu.kudryavtsev.andrey.view.graphicView.panels;

import ru.nsu.kudryavtsev.andrey.view.graphicView.GridBagHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel
{
    public MenuPanel(ActionListener actionListener)
    {
        setLayout(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();
        setBackground(new Color(250, 150, 20));

        // add title
        JLabel title = new JLabel("BOMBERMAN");
        title.setFont(new Font("SERIF", Font.BOLD, 30));
        add(title, helper.get());
        helper.nextRow();
        helper.gapTop(80);

        // add play button
        JButton playButton = new JButton("PLAY");
        playButton.setActionCommand("PLAY");
        playButton.addActionListener(actionListener);
        playButton.setBackground(new Color(255, 100, 12));
        playButton.setPreferredSize(new Dimension(210, 70));
        playButton.setMinimumSize(new Dimension(210, 70));
        playButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(playButton, helper.get());
        helper.nextRow();
        helper.gapTop(20);

        // add score button
        JButton scoreButton = new JButton("SCORE");
        scoreButton.setActionCommand("SCORE");
        scoreButton.addActionListener(actionListener);
        scoreButton.setBackground(new Color(255, 100, 12));
        scoreButton.setPreferredSize(new Dimension(210, 70));
        scoreButton.setMinimumSize(new Dimension(210, 70));
        scoreButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(scoreButton, helper.get());
        helper.nextRow();
        helper.gapTop(20);

        // add about button
        JButton aboutButton = new JButton("ABOUT");
        aboutButton.setActionCommand("ABOUT");
        aboutButton.addActionListener(actionListener);
        aboutButton.setBackground(new Color(255, 100, 12));
        aboutButton.setPreferredSize(new Dimension(210, 70));
        aboutButton.setMinimumSize(new Dimension(210, 70));
        aboutButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(aboutButton, helper.get());
        helper.nextRow();
        helper.gapTop(20);

        // add exit button
        JButton exitButton = new JButton("EXIT");
        exitButton.setActionCommand("EXIT");
        exitButton.addActionListener(actionListener);
        exitButton.setBackground(new Color(255, 100, 12));
        exitButton.setPreferredSize(new Dimension(210, 70));
        exitButton.setMinimumSize(new Dimension(210, 70));
        exitButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(exitButton, helper.get());
    }
}
