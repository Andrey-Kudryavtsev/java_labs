package ru.nsu.kudryavtsev.andrey.view.graphicView.panels;

import ru.nsu.kudryavtsev.andrey.model.GameConfig;
import ru.nsu.kudryavtsev.andrey.scoreUtils.ScoreNode;
import ru.nsu.kudryavtsev.andrey.view.graphicView.GridBagHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

public class ScorePanel extends JPanel
{
    private final JLabel title;
    private final JButton backButton;

    public ScorePanel(ActionListener actionListener)
    {
        setLayout(new GridBagLayout());
//        GridBagHelper helper = new GridBagHelper();
        setBackground(new Color(250, 150, 20));

        // add title
        title = new JLabel("BOMBERMAN");
        title.setFont(new Font("SERIF", Font.BOLD, 30));
//        add(title, helper.get());
//        helper.nextRow();
//        helper.gapTop(80);

        // add back button
        backButton = new JButton("BACK");
        backButton.setActionCommand("MENU");
        backButton.addActionListener(actionListener);
        backButton.setBackground(new Color(255, 100, 12));
        backButton.setPreferredSize(new Dimension(210, 70));
        backButton.setMinimumSize(new Dimension(210, 70));
        backButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2, true));
    }

    public void preparePanel(PriorityQueue<ScoreNode> highScores)
    {
        removeAll();
        GridBagHelper helper = new GridBagHelper();
        add(title, helper.get());
        helper.nextRow();
        helper.gapTop(80);

        int border = (highScores.size() > GameConfig.SCORE_ENTRIES) ? highScores.size() - GameConfig.SCORE_ENTRIES : 0;
        while (highScores.size() != border)
        {
            ScoreNode node = highScores.remove();
            JLabel scoreEntry = new JLabel(node.getUsername() + " " + node.getScore());
            scoreEntry.setFont(new Font("SERIF", Font.BOLD, 30));
            add(scoreEntry, helper.get());
            helper.nextRow();
        }
        helper.gapTop(40);
        add(backButton, helper.get());
    }
}
