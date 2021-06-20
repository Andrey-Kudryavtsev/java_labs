package ru.nsu.kudryavtsev.andrey.view.graphicView;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame
{
    private Color bgColor = new Color(250, 150, 20);

    public AppFrame(int width, int height)
    {
        Container c = getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagHelper helper = new GridBagHelper();

        helper.gapBottom(height / 2);
        c.setBackground(bgColor);
        JLabel title = new JLabel("BOMBERMAN");
        title.setFont(new Font("SERIF", Font.BOLD, 30));
        c.add(title, helper.get());
    }
}
