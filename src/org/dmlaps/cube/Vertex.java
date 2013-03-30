/**
 * @author Архангельский Илья
 */
package org.dmlaps.cube;

/**
 * Реализация класса "Вершина".
 * Вершина задается вектором с центром в точке (0,0,0).
 *
 * @author Ilya Arkhanhelsky.
 */
public class Vertex
{
    private double x;
    private double y;
    private double z;

    /**
     * Конструктор создает вершину с координатами в пространстве XYZ
     *
     * @param x координата по X
     * @param y координата по Y
     * @param z координата по Z
     */
    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Получить X координату
     *
     * @return
     */
    public double getX()
    {
        return x;
    }


    /**
     * Получить Y координату
     *
     * @return
     */
    public double getY()
    {
        return y;
    }


    /**
     * Получить Z координату
     *
     * @return
     */
    public double getZ()
    {
        return z;
    }

    /**
     * Создание вершины из массива целых чисел
     *
     * @param vector массив X,Y,Z координат соотвественно
     * @throws UnsupportedOperationException если длина массива не равна 3
     */

    /**
     * Получить норму вектора
     *
     * @return
     */
    public double getNorm()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Установить норму вектора
     *
     * @param n новая норма вектора
     */
    public void setNorm(double n)
    {
        if (n != 0)
        {
            double old = getNorm();
            x = x * n / old;
            y = y * n / old;
            z = z * n / old;
        }
    }
}
