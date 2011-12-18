/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Cube;

/**
 *
 * @author lucian
 */
public class Cube
{
    private Vertex [] vertexs = new  Vertex[8];
    private int edgeLength;
    private int distance;

    public Cube(int edgeLength)
    {
        this.edgeLength = edgeLength/2;
        vertexs[0] = new Vertex(this.edgeLength, this.edgeLength, this.edgeLength);
        vertexs[1] = new Vertex(-this.edgeLength, this.edgeLength, this.edgeLength);
        vertexs[2] = new Vertex(-this.edgeLength, this.edgeLength, -this.edgeLength);
        vertexs[3] = new Vertex(this.edgeLength, this.edgeLength, -this.edgeLength);
        vertexs[4] = new Vertex(this.edgeLength, -this.edgeLength, this.edgeLength);
        vertexs[5] = new Vertex(-this.edgeLength, -this.edgeLength, this.edgeLength);
        vertexs[6] = new Vertex(-this.edgeLength, -this.edgeLength, -this.edgeLength);
        vertexs[7] = new Vertex(this.edgeLength, -this.edgeLength, -this.edgeLength);
        distance =  2*edgeLength;
    }

    public void rotate (double yaw, double pitch, double roll)
    {
        if (yaw != 0)
        {
            yawing(yaw);
        }
        if (roll != 0)
        {
            rolling(roll);
        }
        if (pitch != 0)
        {
            pitching(pitch);
        }
    }

    public int [] getXProjection ()
    {
        int [] tmp = new int [8];
        
        for (int i=0;i<8;i++)
        {
            tmp[i] =  ((int) Math.round(vertexs[i].getX()*(distance-vertexs[i].getZ())/(distance+edgeLength)));
        }        
        return tmp;
    }

    public  int [] getYProjection ()
    {
        int [] tmp = new int [8];
        for (int i=0;i<8;i++)
        {
            tmp [i] = (int) Math.round(vertexs[i].getY()*(distance-vertexs[i].getZ())/(distance+edgeLength));
        }
        return tmp;
    }

    public int [] getZProjection ()
    {
        int [] tmp = new int [8];
        for (int i=0;i<8;i++)
        {
            tmp [i] = (int) Math.round(vertexs[i].getZ() );
        }
        return tmp;
    }

    public void yawing (double yaw)
    {
        double cos = Math.cos(yaw);
        double sin = Math.sin (yaw);
        for (int i=0; i<8;i++)
        {
            double newX = vertexs[i].getX()*cos-vertexs[i].getY()*sin;
            double newY = vertexs[i].getX()*sin+vertexs[i].getY()*cos;
            double newZ = vertexs[i].getZ();

            vertexs[i] = new Vertex(newX, newY, newZ);
        }
    }

    public void pitching (double pitch)
    {
        double cos = Math.cos(pitch);
        double sin = Math.sin (pitch);
        for (int i=0;i<8;i++)
        {
            double newX = vertexs[i].getX()*cos + vertexs[i].getZ()*sin;
            double newY = vertexs[i].getY();
            double newZ = -vertexs[i].getX()*sin+vertexs[i].getZ()*cos;

            vertexs[i] = new Vertex (newX,newY,newZ);
        }
    }

    public void rolling (double roll)
    {
        double cos = Math.cos(roll);
        double sin = Math.sin(roll);

        for (int i=0;i<8;i++)
        {
            double newX = vertexs[i].getX();
            double newY = vertexs[i].getY()*cos - vertexs[i].getZ()*sin;
            double newZ = vertexs[i].getY()*sin + vertexs[i].getZ()*cos;

            vertexs[i] = new Vertex(newX, newY,newZ);
        }
        
    }

}
