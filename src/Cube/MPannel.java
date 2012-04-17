/**
 * @author Архангельский Илья
 */
package Cube;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import javax.swing.Timer;

/**
 *
 * @author Архангельский Илья
 */
public class MPannel extends JPanel implements ActionListener
{

    /** начальное значение длины ребра куба */
    private final int edge = 300;
    private Cube cube;
    /** Позиция мыши */
    private long mouseX = 0;
    private long mouseY = 0;
    /** Коэффициент для преобразования перемещения мыши в угол поворота */
    private final long angleRatio = 3600;
    private Timer timer;
    /** Коэффициент затухания вращения куба*/
    private final double G = 0.0098;
    /** Углы поворота */
    private double yaw = 0;
    private double roll = 0;
    private double pitch = 0;

    public class mouseAdapter extends MouseAdapter
    {

        @Override
        public void mouseReleased(MouseEvent e)
        {
            long deltaX = mouseX - Math.round(e.getPoint().getX());
            long deltaY = mouseY - Math.round(e.getPoint().getY());
            // Преобразование перемещения мыши в уголы поворота куба
            yaw += (-deltaX * (deltaY == 0 ? 0 : 1) / (deltaY == 0 ? 1 : deltaY)) * 2 * Math.PI / angleRatio;
            pitch += deltaX * (2 * Math.PI) / angleRatio;
            roll += -deltaY * (2 * Math.PI) / angleRatio;
            timer.restart();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            cube.setEdgeLength(cube.getEdgeLength() - 10 * e.getWheelRotation());
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            long deltaX = mouseX - Math.round(e.getPoint().getX());
            long deltaY = mouseY - Math.round(e.getPoint().getY());
            yaw = (-deltaX * (deltaY == 0 ? 0 : 1) / (deltaY == 0 ? 1 : deltaY)) * 2 * Math.PI * 10 / (angleRatio);
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
        setBorder(BorderFactory.createLineBorder(Color.black));
        timer = new Timer(50, this);
        timer.start();
        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        cube = new Cube(edge);
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
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(236, 236, 236));
        g.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        g.setColor(Color.BLACK);

        /*Отладочный вывод */
//        g.drawString(" EDGE = " + cube.getEdgeLength() + " YAW = " + yaw + " PITCH = " + pitch + " ROLL = " + roll, 10, 20);
        Side[] sides = cube.getProjectedSides();
        /** Смещаем в центр фрэйма*/
        for (int i = 0; i < sides.length; i++)
        {
            sides[i].move(this.getBounds().width / 2, this.getBounds().height / 2);
        }
        /** Отрисовка*/
        for (int i = 0; i < 6; i++)
        {
            g.setColor(sides[i].getColor());
            g.fillPolygon(sides[i].getxAxises(), sides[i].getyAxises(), 4);
            g.drawPolygon(sides[i].xAxises, sides[i].yAxises, 4);
        }
        /* Отладочный вывод */
//        for (int i = 0; i < 8; i++)
//        {
//            g.setColor(Color.BLACK);
//            g.drawString("Vertex ID = " + i + " X = " + x[i] + " Y = " + y[i] + " Z = " + z[i], 10, 40 + i * 20);
//            g.drawString("" + i, x[i], y[i]);
//        }
    }
}
