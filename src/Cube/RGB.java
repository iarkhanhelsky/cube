/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cube;

/**
 *
 * @author lucian
 */
public class RGB
{

    private double r;
    private double g;
    private double b;

    public RGB(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGB(int r, int g, int b)
    {
        this.r = ((double) r) / 255;
        this.g = ((double) g) / 255;
        this.b = ((double) b) / 255;
    }

    public double getB()
    {
        return b;
    }

    public void setB(double b)
    {
        this.b = b;
    }

    public double getG()
    {
        return g;
    }

    public void setG(double g)
    {
        this.g = g;
    }

    public double getR()
    {
        return r;
    }

    public void setR(double r)
    {
        this.r = r;
    }

    public RGB diff (RGB x)
    {
        return new RGB (this.r-x.r,this.g-x.g,this.b-x.b);
    }

    public RGB mulScalar (double x)
    {
        return new RGB (this.r*x,this.g*x,this.b*x);
    }

    public RGB sum (RGB x)
    {
        return new RGB (this.r+x.r,this.g+x.g,this.b+x.b);
    }
}


