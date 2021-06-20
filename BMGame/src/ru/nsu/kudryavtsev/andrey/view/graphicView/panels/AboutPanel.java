package ru.nsu.kudryavtsev.andrey.view.graphicView.panels;

import ru.nsu.kudryavtsev.andrey.commands.Command;
import ru.nsu.kudryavtsev.andrey.commands.Task;
import ru.nsu.kudryavtsev.andrey.factory.CommandFactory;
import ru.nsu.kudryavtsev.andrey.view.graphicView.GridBagHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class AboutPanel extends JPanel
{
    public AboutPanel(ActionListener actionListener)
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

        // add application information
        JLabel about = new JLabel("<html><p>Author:    Andrey Kudryavtsev<br><br>" + "<p>All rights reserved");
        about.setFont(new Font("SERIF", Font.PLAIN, 30));
        add(about, helper.get());
        helper.nextRow();
        helper.gapTop(40);

        // add back button
        JButton backButton = new JButton("BACK");
        backButton.setActionCommand("MENU");
        backButton.addActionListener(actionListener);
        backButton.setBackground(new Color(255, 100, 12));
        backButton.setPreferredSize(new Dimension(210, 70));
        backButton.setMinimumSize(new Dimension(210, 70));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
        add(backButton, helper.get());
    }
}
