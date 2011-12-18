/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Cube;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author lucian
 */
public class CubeForm
{
     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MPannel());
        f.pack();
        f.setVisible(true);
    }


}
