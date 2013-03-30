/**
 * @author Архангельский Илья
 */
package org.dmlaps;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ilya Arkhanhelsky
 */
public class CubeForm
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame("Cube");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBounds(100, 100, 1000, 1000);
        f.setMinimumSize(new Dimension(800, 800));
        f.add(new MPannel());

        f.pack();
        f.setVisible(true);
    }
}
