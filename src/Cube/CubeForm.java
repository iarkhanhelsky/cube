/**
 * @author Архангельский Илья
 */
package Cube;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Архангельский Илья 
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
        f.add(new MPannel());
        f.pack();
        f.setVisible(true);
    }
}
