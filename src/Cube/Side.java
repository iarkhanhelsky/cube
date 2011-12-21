/**
 * @author Архангельский Илья
 */
package Cube;

import java.awt.Color;

/**
 *
 * @author lucian
 */
public class Side implements Comparable<Side>
{
    int zAxis;
    int [] xAxises;
    int [] yAxises;
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
        return o.zAxis-zAxis;
    }

    public Color getColor()
    {
        return color;
    }

    public int[] getxAxises()
    {
        return xAxises;
    }

    public int[] getyAxises()
    {
        return yAxises;
    }

    
}
