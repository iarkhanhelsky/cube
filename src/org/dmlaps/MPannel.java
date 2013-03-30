/**
 * @author Архангельский Илья
 */
package org.dmlaps;


import org.dmlaps.cube.ColorCube;
import org.dmlaps.cube.ColorSide;
import org.dmlaps.cube.Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Ilya Arkhanhelsky
 */
public class MPannel extends JPanel implements ActionListener
{

    private final int SEGMENTS_MAX = 20;
    private final int SEGMENTS_MIN = 2;
    private final int SEGMENTS_INIT = 8;
    /**
     * начальное значение длины ребра куба
     */
    private final int EDGE = 300;
    private ColorCube cube;
    /**
     * Позиция мыши
     */
    private long mouseX = 0;
    private long mouseY = 0;
    /**
     * Коэффициент для преобразования перемещения мыши в угол поворота
     */
    private final long ANGLE_RATIO = 3600;
    private long angleRatio = 3600;
    private Timer timer;
    /**
     * Коэффициент затухания вращения куба
     */
    private final double G = 0.0098;
    private final double EPS = 0.000000000000000001;
    /**
     * Углы поворота
     */
    private double yaw = 0;
    private double roll = 0;
    private double pitch = 0;

    private JSlider segments;
    private JPanel sliderPannel = new JPanel();


    public class mouseAdapter extends MouseAdapter
    {

        @Override
        public void mouseReleased(MouseEvent e)
        {
            long deltaX = mouseX - Math.round(e.getPoint().getX());
            long deltaY = mouseY - Math.round(e.getPoint().getY());
            // Преобразование перемещения мыши в уголы поворота куба
            yaw += (-deltaX / (deltaY == 0 ? 1 : deltaY)) * 2 * Math.PI / angleRatio;
            pitch += deltaX * (2 * Math.PI) / angleRatio;
            roll += -deltaY * (2 * Math.PI) / angleRatio;
            timer.restart();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            if (cube.getEdgeLength() - 10 * e.getWheelRotation() > 0)
            {
                cube.setEdgeLength(cube.getEdgeLength() - 10 * e.getWheelRotation());

                repaint();
            }

        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            long deltaX = mouseX - Math.round(e.getPoint().getX());
            long deltaY = mouseY - Math.round(e.getPoint().getY());
            yaw = (-deltaX / (deltaY == 0 ? 1 : deltaY)) * 2 * Math.PI * 10 / (angleRatio);
            pitch = deltaX * (2 * Math.PI) * 10 / (angleRatio);
            roll = -deltaY * (2 * Math.PI) * 10 / (angleRatio);
            cube.rotate(yaw, pitch, roll);
            repaint();
            mouseX = Math.round(e.getPoint().getX());
            mouseY = Math.round(e.getPoint().getY());
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            mouseX = Math.round(e.getPoint().getX());
            mouseY = Math.round(e.getPoint().getY());
            timer.stop();
            yaw = 0;
            pitch = 0;
            roll = 0;
        }
    }

    public MPannel()
    {
        segments = new JSlider(JSlider.HORIZONTAL, SEGMENTS_MIN, SEGMENTS_MAX, SEGMENTS_INIT);

        segments.setMajorTickSpacing(2);
        segments.setBounds(new Rectangle(this.getBounds().width, 10));

        segments.setMinorTickSpacing(1);
        segments.setPaintTicks(true);

        segments.setPaintLabels(true);
        segments.setBounds(10, 10, 10, this.getBounds().width);


        sliderPannel.setLayout(new GridLayout(2, 1, 5, 5));

        sliderPannel.add(new JLabel("Слои"));
        sliderPannel.add(segments);


        add(sliderPannel);
        setBorder(BorderFactory.createLineBorder(Color.black));
        timer = new Timer(50, this);
        timer.start();
        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        cube = new ColorCube(EDGE);
        addMouseWheelListener(new mouseAdapter());

    }

    public void actionPerformed(ActionEvent e)
    {
        cube.rotate(yaw, pitch, roll);
        yaw = sgn(yaw) * Math.abs(Math.abs(yaw) - Math.abs(yaw * G));
        pitch = sgn(pitch) * Math.abs(Math.abs(pitch) - Math.abs(pitch * G));
        roll = sgn(roll) * Math.abs(Math.abs(roll) - Math.abs(roll * G));
        this.repaint();
        timer.restart();
    }

    /**
     * @param x
     * @return реализация математической функции signum
     */
    protected int sgn(double x)
    {
        return (int) (x / Math.abs(x));
    }

    @Override
    protected void paintComponent(Graphics gOrig)
    {

        super.paintComponent(gOrig);
        Graphics2D g = (Graphics2D) gOrig;
        this.setDoubleBuffered(true);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(236, 236, 236));
        g.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        g.setColor(Color.BLACK);

//        /*Отладочный вывод */
//        g.drawString("EDGE = " + cube.getEdgeLength() + " YAW = " + yaw + " PITCH = " + pitch + " ROLL = " + roll, 10, 20);

        ColorSide[] sides = cube.getProjectedSides();
        /** Смещаем в центр фрэйма*/
        int[] x = cube.getXProjection();
        int[] y = cube.getYProjection();
        int[] z = cube.getZProjection();

        for (int i = 0; i < sides.length; i++)
        {
            sides[i].move(this.getBounds().width / 2, this.getBounds().height / 2);
        }

        for (int i = 0; i < x.length; i++)
        {
            x[i] += getBounds().width / 2;
            y[i] += getBounds().height / 2;
        }
        /** Отрисовка*/
        for (int i = 0; i < sides.length; i++)
        {
            Side[] seg = sides[i].pieces(segments.getValue(), segments.getValue());
            for (int j = 0; j < seg.length; j++)
            {
                g.setColor(seg[j].getColor());
                g.fillPolygon(seg[j].getXPoints(), seg[j].getYPoints(), 4);
                g.drawPolygon(seg[j].getXPoints(), seg[j].getYPoints(), 4);
            }
        }
        /* Отладочный вывод */

//        for (int i = 0; i < 8; i++)
//        {
//            g.setColor(Color.BLACK);
//            g.drawString("Vertex ID = " + (i+1) + " X = " + x[i] + " Y = " + y[i] + " Z = " + z[i], 10, 40 + i * 20);
//            g.drawString("" + (i + 1), x[i], y[i]);
//
//        }

    }
}