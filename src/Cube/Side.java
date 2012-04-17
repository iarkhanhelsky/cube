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

    int zAxis;
    int[] xAxises;
    int[] yAxises;
    Color color;

    public Side(int zAxis, int[] xAxises, int[] yAxises, Color color)
    {
        this.zAxis = zAxis;
        this.xAxises = xAxises;
        this.yAxises = yAxises;
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
    public int[] getxAxises()
    {
        return xAxises;
    }
    /** Массив Y координат вершин */
    public int[] getyAxises()
    {
        return yAxises;
    }
    /** Сдвинуть при проецировании по Х и У */
    public void move (int x, int y)
    {
        for (int i=0;i<xAxises.length;i++)
        {
            xAxises[i] +=x;
        }
        for (int i=0;i<yAxises.length;i++)
        {
            yAxises[i]+=y;
        }
    }


}
