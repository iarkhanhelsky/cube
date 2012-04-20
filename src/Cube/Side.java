/**
 * @author Архангельский Илья
 */
package Cube;

import java.awt.Color;

/**
 * Реализация класса "Грань"
 * Вообще говоря класс служит лишь для отрисовки граней в правильной последовательности
 * от нижних к верхним
 * @author Архангельский Илья
 */
public class Side implements Comparable<Side>
{

    protected  int zAxis;
    protected  int[] xPoints;
    protected  int[] yPoints;
    protected  Color color;

    public Side(int zAxis, int[] xPoints, int[] yPoints, Color color)
    {
        this.zAxis = zAxis;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int compareTo(Side o)
    {
        /** Сравниваем по Z координате */
        return o.zAxis - zAxis;
    }

    public Color getColor()
    {
        return color;
    }

    /** Массив Х координат вершин */
    public int[] getXPoints()
    {
        return xPoints;
    }

    /** Массив Y координат вершин */
    public int[] getYPoints()
    {
        return yPoints;
    }

    /** Сдвинуть при проецировании по Х и У */
    public void move(int x, int y)
    {
        for (int i = 0; i < xPoints.length; i++)
        {
            xPoints[i] += x;
        }
        for (int i = 0; i < yPoints.length; i++)
        {
            yPoints[i] += y;
        }
    }
}
