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
   /**
    * центр грани по Z
    */
    protected  int zAxis;
    /**
     *  массив точек описывающих Х координаты вершин грани
     */
    protected  int[] xPoints;
    /**
     * массив точек описывающих Y координаты вершин грани
     */
    protected  int[] yPoints;
    /**
     * Цвет грани
     */
    protected  Color color;
    /**
     *
     * @param zAxis центр грани по Z
     * @param xPoints массив точек описывающих Х координаты вершин грани
     * @param yPoints массив точек описывающих Y координаты вершин грани
     * @param color цвет грани
     */
    public Side(int zAxis, int[] xPoints, int[] yPoints, Color color)
    {
        this.zAxis = zAxis;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.color = color;
    }
    /**
     * Установить цвет грани
     * @param color
     */
    public void setColor(Color color)
    {
        this.color = color;
    }
    /**
     * Сравнить две грани. Сравнение проводится по Z координате
     * @param o
     * @return 0 если Z координаты совпадают, значение большее 0, если Z координата объекта сравнения больше, значение меньшее 0, во всех остальных случаях
     */
    public int compareTo(Side o)
    {
        /** Сравниваем по Z координате */
        return o.zAxis - zAxis;
    }
    /**
     * Получить цвет грани
     * @return цвет грани
     */
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
