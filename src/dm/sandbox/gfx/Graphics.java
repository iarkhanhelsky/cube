package dm.sandbox.gfx;

/**
 * Created by Ilya Arkhanhelsky on 12/16/14.
 */
public interface Graphics
{
    void fillPoly(Color color, int[] points);
    void translate(int x, int y);
    void fillRect(Color color, int x, int y, int width, int height);
}
