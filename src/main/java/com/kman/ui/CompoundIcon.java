package com.kman.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Generates compound icon from list of icons
public class CompoundIcon implements Icon
{
    private List<Icon> icons = new ArrayList<>();

    public CompoundIcon(List<Icon> icons)
    {
        this.icons = icons;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        for (Icon icon: icons)
        {
            icon.paintIcon(c, g, x, y);
            x += icon.getIconWidth();
        }
    }

    public int getIconWidth()
    {
        int width = 0;
        for (Icon icon: icons)
        {
            width += icon.getIconWidth();
        }
        return width;
    }

    public int getIconHeight()
    {
        int height = 0;
        for (Icon icon: icons)
        {
            if (icon.getIconHeight() > height){
                height = icon.getIconHeight();
            }
            height = height;
        }
        return height;
    }
}
