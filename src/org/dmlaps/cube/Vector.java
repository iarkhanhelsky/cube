package org.dmlaps.cube;

import java.util.Arrays;

/**
 * User: Ilya Arkhanhelsky
 * Date: 05.06.13
 * Time: 13:00
 */
public class Vector
{
    private final double[] vector;

    public Vector(double... vector)
    {
        this.vector = Arrays.copyOf(vector, vector.length);
    }

    public double get(int id)
    {
        return vector[id];
    }

    public double norm()
    {
        double sum = 0;
        for (double x : vector)
        {
            sum += x * x;
        }
        return Math.sqrt(sum);
    }

    protected void set(int id, double value)
    {
        vector[id] = value;
    }

    protected Vector mul(double m)
    {
        double [] vec = new double[length()];
        for (int i = 0; i < vector.length; i++)
        {
            vec[i] = this.get(i) * m;
        }
        return new Vector(vec);
    }


    public Vector sub(Vector another)
    {
        return this.sum(another.mul(-1));
    }

    public Vector sum(Vector another)
    {
        if (this.length() != another.length())
        {
            throw new IndexOutOfBoundsException();
        }

        double[] newVector = new double[length()];
        for (int i = 0; i < length(); i++)
        {
            newVector[i] = this.get(i) + another.get(i);
        }
        return new Vector(newVector);
    }

    private int length()
    {
        return vector.length;
    }
}
