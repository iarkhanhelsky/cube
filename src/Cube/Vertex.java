/**
 * @author Архангельский Илья
 */
package Cube;

/**
 * Реализация класса "Вершина"
 * Вершина задается вектором с центром в точке (0,0,0)
 * @author Архангельский Илья
 */
public class Vertex
{
    private double x;
    private double y;
    private double z;

    /**
     * Конструктор создает вершину с координатами в пространстве XYZ
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

    /* Геттеры сеттеры*/
    public Vertex()
    {
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }
    /**
     * Создание вершины из массива целых чисел
     * @param vector массив X,Y,Z координат соотвественно
     * @throws UnsupportedOperationException если длина массива не равна 3
     */
    public Vertex(int[] vector) throws UnsupportedOperationException
    {
        if (vector.length != 3)
        {
            throw new UnsupportedOperationException("Wrong Dimetion!");
        }

        x = vector[0];
        y = vector[1];
        z = vector[2];
    }
    /* Получить целочисленные значения вектора */
    public int[] getIntVector()
    {
        int[] tmp =
        {
            (int) Math.round(x), (int) Math.round(y), (int) Math.round(z)
        };
        return tmp;
    }
    /** Получить норму вектора*/
    public double getNorm()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }
    /** Установить норму вектора*/
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
