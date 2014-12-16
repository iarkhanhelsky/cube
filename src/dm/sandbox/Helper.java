package dm.sandbox;

import dm.sandbox.gfx.Color;

/**
 * User: Ilya Arkhanhelsky
 * Date: 05.06.13
 * Time: 13:33
 */
public class Helper
{
    public static java.awt.Color toAWT(Color color)
    {
        return new java.awt.Color((float)color.r(), (float)color.g(), (float)color.b());
    }
}
