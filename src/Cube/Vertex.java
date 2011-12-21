/**
 * @author Архангельский Илья
 */
package Cube;

/**
 *
 * @author lucian
 */
public class Vertex
{

    private double x;
    private double y;
    private double z;

    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public int[] getIntVector()
    {
        int[] tmp =
        {
            (int) Math.round(x), (int) Math.round(y), (int) Math.round(z)
        };
        return tmp;
    }

    public double getNorm ()
    {
        return Math.sqrt(x*x+y*y+z*z);
    }
    public void setNorm (double n)
    {
        double old = getNorm();
        x = x*n/old;
        y = y*n/old;
        z = z*n/old;
    }
}
