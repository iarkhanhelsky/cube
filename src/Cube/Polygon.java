/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Cube;

/**
 * Класс "многоугольник".
 * @author lucian
 */
public class Polygon {
    private int[] xPoints;
    private int[] yPoints;

    public Polygon(int[] xPoints, int[] yPoints)
    {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public int[] getxPoints()
    {
        return xPoints;
    }

    public void setxPoints(int[] xPoints)
    {
        this.xPoints = xPoints;
    }

    public int[] getyPoints()
    {
        return yPoints;
    }

    public void setyPoints(int[] yPoints)
    {
        this.yPoints = yPoints;
    }
}
