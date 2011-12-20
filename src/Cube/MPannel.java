/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Cube;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Arrays;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author lucian
 */
public class MPannel extends JPanel implements ActionListener
{
    private final int edge = 300;
    private Cube cube = new Cube (edge);
    private long mouseX = 0;
    private long mouseY = 0;
    private long time = 0;
    private long angleRatio = 360;

    private Timer timer;
    private final double G = 0.00098;
    private double yaw = 0;
    private double roll = 0;
    private double pitch = 0;
    private Rotation rotation = new Rotation(cube);



    public MPannel() {

        setBorder(BorderFactory.createLineBorder(Color.black));
        SwingUtilities.invokeLater(rotation);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = Math.round(e.getPoint().getX());
                mouseY = Math.round(e.getPoint().getY());
                time = e.getWhen();
            }
        });
        timer = new Timer (50,this);
        timer.start();
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
            long deltaX = mouseX - Math.round(e.getPoint().getX());
            long deltaY = mouseY - Math.round(e.getPoint().getY());
            
            yaw = (-deltaX*(deltaY==0?0:1)/(deltaY==0?1:deltaY))*2*Math.PI/angleRatio;
            pitch = deltaX*(2*Math.PI)/angleRatio;
            roll =  -deltaY*(2*Math.PI)/angleRatio;
                         
            cube.rotate(yaw, pitch, roll);

            repaint();
            mouseX = Math.round(e.getPoint().getX());
            mouseY = Math.round(e.getPoint().getY());

            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased (MouseEvent e)
            {

            }
        });

    }

    public void actionPerformed(ActionEvent e)
    {
        cube.rotate(yaw, pitch, roll);

        yaw = sgn(yaw)*Math.abs( Math.abs(yaw) - Math.abs(yaw*G));
        pitch =sgn(pitch)*Math.abs( Math.abs(pitch) - Math.abs(pitch*G));
        roll = sgn(roll)*Math.abs( Math.abs(roll) - Math.abs(roll*G));
     

        this.repaint();
        timer.restart();
    }

    protected int sgn (double x )
    {
        if (Math.abs(x) < 0.00003 )
        {
            return  0;
        }
        if (x<0)
        {
            return -1;

        }
        return 1;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color (236,236,236));
        g.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        g.setColor(Color.BLACK);
        g.drawString("X = "+mouseX+" Y = "+mouseY+ " YAW = " +yaw + " PITCH = " + pitch + " ROLL = " + roll ,10,20);
        
        
        int[] x = cube.getXProjection();
        int[] y = cube.getYProjection();
        int[] z = cube.getZProjection();
        for (int i = 0; i < x.length; i++)
        {
            x[i] += edge;
            y[i] += edge;
        }
        Side[] sides = new Side[6];
      


        int[] SideAX =
        {
            x[0], x[1], x[2], x[3]
        };
        int[] SideBX =
        {
            x[0], x[1], x[5], x[4]
        };
        int[] SideCX =
        {
            x[0], x[3], x[7], x[4]
        };
        int[] SideDX =
        {
            x[6], x[5], x[1], x[2]
        };
        int[] SideEX =
        {
            x[6], x[7], x[4], x[5]
        };
        int[] SideFX =
        {
            x[6], x[2], x[3], x[7]
        };

        int[] SideAY =
        {
            y[0], y[1], y[2], y[3]
        };
        int[] SideBY =
        {
            y[0], y[1], y[5], y[4]
        };
        int[] SideCY =
        {
            y[0], y[3], y[7], y[4]
        };
        int[] SideDY =
        {
            y[6], y[5], y[1], y[2]
        };
        int[] SideEY =
        {
            y[6], y[7], y[4], y[5]
        };
        int[] SideFY =
        {
            y[6], y[2], y[3], y[7]
        };

        int SideAZCenter = (z[0] + z[2]) / 2;
        int SideBZCenter = (z[0] + z[5]) / 2;
        int SideCZCenter = (z[0] + z[7]) / 2;
        int SideDZCenter = (z[6] + z[1]) / 2;
        int SideEZCenter = (z[6] + z[4]) / 2;
        int SideFZCenter = (z[6] + z[3]) / 2;

        sides[0] = new Side(SideAZCenter, SideAX, SideAY, new Color(238,135,31));
        sides[1] = new Side(SideBZCenter, SideBX, SideBY, new Color(84, 31, 20));
        sides[2] = new Side(SideCZCenter, SideCX, SideCY, new Color(147, 129, 114));
        sides[3] = new Side(SideDZCenter, SideDX, SideDY, new Color(204, 158, 97));
        sides[4] = new Side(SideEZCenter, SideEX, SideEY, new Color(98, 98, 102));
        sides[5] = new Side(SideFZCenter, SideFX, SideFY, new Color(254, 233, 142));
        Arrays.sort(sides);
        

        for (int i = 0; i < 6; i++)
        {

            g.setColor(sides[i].getColor());
            g.fillPolygon(sides[i].getxAxises(), sides[i].getyAxises(), 4);
            g.drawPolygon(sides[i].xAxises, sides[i].yAxises, 4);
        }

        for (int i=0;i<8;i++)
        {
            g.setColor(Color.BLACK);
            g.drawString("Vertex ID = "+i+" X = " + x[i] + " Y = "+ y[i] + " Z = "+ z[i], 10, 40+i*20);
            g.drawString(""+i, x[i], y[i]);
        }
        

    }  
    

}
