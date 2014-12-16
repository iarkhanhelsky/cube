package dm.sandbox.gfx;

import dm.sandbox.Helper;

/**
 * Created by Ilya Arkhanhelsky on 12/16/14.
 */
public class AWTGraphics implements Graphics
{
    private final java.awt.Graphics g;

    public AWTGraphics(java.awt.Graphics g)
    {
        this.g = g;
    }

    @Override
    public void fillPoly(Color color, int[] points)
    {
        java.awt.Color c = g.getColor();
        int[] x = new int[points.length / 2];
        int[] y = new int[points.length / 2];

        for(int i = 0; i < x.length; i++)
        {
            x[i] = points[2*i];
            y[i] = points[2*i + 1];
        }
        g.setColor(Helper.toAWT(color));
        g.fillPolygon(x, y, x.length);
        g.setColor(c);
    }

    @Override
    public void translate(int x, int y)
    {
        g.translate(x, y);
    }

    @Override
    public void fillRect(Color color, int x, int y, int width, int height)
    {
        java.awt.Color c = g.getColor();
        g.setColor(Helper.toAWT(color));
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}


