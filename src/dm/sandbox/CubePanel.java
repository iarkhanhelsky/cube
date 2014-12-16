/**
 * @author Архангельский Илья
 */
package dm.sandbox;


import dm.sandbox.cube.ColorCube;
import dm.sandbox.cube.ColorSide;
import dm.sandbox.cube.Cube;
import dm.sandbox.cube.Side;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Ilya Arkhanhelsky
 */
public class CubePanel extends JPanel implements ActionListener
{

    private Cube cube;
    /**
     * Позиция мыши
     */
    private long mouseX = 0;
    private long mouseY = 0;
    /**
     * Коэффициент для преобразования перемещения мыши в угол поворота
     */
    private long angleRatio = 3600;
    private Timer timer;

    /**
     * Углы поворота
     */
    private double yaw = 0;
    private double roll = 0;
    private double pitch = 0;

    private JSlider segments;


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

    public CubePanel()
    {
        JPanel sliderPanel = initSlider();
        add(sliderPanel);

        setBorder(BorderFactory.createLineBorder(Color.black));

        timer = new Timer(50, this);
        timer.start();

        addMouseMotionListener(new mouseAdapter());
        addMouseListener(new mouseAdapter());
        addMouseWheelListener(new mouseAdapter());

        //начальное значение длины ребра куба
        int EDGE = 300;
        cube = new ColorCube(EDGE);
    }

    private JPanel initSlider()
    {
        int SEGMENTS_MAX = 80;
        int SEGMENTS_MIN = 2;
        int SEGMENTS_INIT = 8;
        segments = new JSlider(JSlider.HORIZONTAL, SEGMENTS_MIN, SEGMENTS_MAX, SEGMENTS_INIT);

        segments.setMajorTickSpacing(10);
        segments.setBounds(new Rectangle(this.getBounds().width, 10));

        segments.setMinorTickSpacing(1);
        segments.setPaintTicks(true);

        segments.setPaintLabels(true);
        segments.setBounds(10, 10, 10, this.getBounds().width);


        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayout(2, 1, 5, 5));

        sliderPanel.add(new JLabel("Слои"));
        sliderPanel.add(segments);
        return sliderPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        cube.rotate(yaw, pitch, roll);

        // Коэффициент затухания вращения куба
        double g = 0.0098;

        yaw = Math.signum(yaw) * Math.abs(Math.abs(yaw) - Math.abs(yaw * g));
        pitch = Math.signum(pitch) * Math.abs(Math.abs(pitch) - Math.abs(pitch * g));
        roll = Math.signum(roll) * Math.abs(Math.abs(roll) - Math.abs(roll * g));

        this.repaint();
        timer.restart();
    }

    @Override
    protected void paintComponent(Graphics gOrig)
    {
        super.paintComponent(gOrig);
        Graphics2D g = (Graphics2D) gOrig;
        this.setDoubleBuffered(true);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(new Color(236, 236, 236));
        g.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        g.setColor(Color.BLACK);

        Side[] sides = cube.getProjectedSides();

        g.translate(this.getBounds().width / 2, this.getBounds().height / 2);

        /** Отрисовка*/
        for (Side side : sides)
        {
            Side[] seg = ((ColorSide) side).pieces(segments.getValue(), segments.getValue());
            for (Side aSeg : seg)
            {
                g.setColor(Helper.toAWT(aSeg.getColor()));
                g.fillPolygon(aSeg.getXPoints(), aSeg.getYPoints(), 4);
            }
        }
        g.translate(-this.getBounds().width / 2, -this.getBounds().height / 2);

    }
}
