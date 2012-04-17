/**
 * @author Архангельский Илья
 */
package Cube;

import java.awt.Color;
import java.util.Arrays;

/**
 * Реализация класса "Куб"
 * @author lucian
 */
public class Cube
{
    private Vertex [] vertexs = new  Vertex[8];
    /* Длина ребра*/
    private int edgeLength;
    /** Проекционные переменные*/
    private int distance;
    private final int distCoeff = 8;

    public Cube(int edgeLength)
    {
        edgeLength /=2;
        this.edgeLength = edgeLength;
        
        vertexs[0] = new Vertex(this.edgeLength, this.edgeLength, this.edgeLength);
        vertexs[1] = new Vertex(-this.edgeLength, this.edgeLength, this.edgeLength);
        vertexs[2] = new Vertex(-this.edgeLength, this.edgeLength, -this.edgeLength);
        vertexs[3] = new Vertex(this.edgeLength, this.edgeLength, -this.edgeLength);
        vertexs[4] = new Vertex(this.edgeLength, -this.edgeLength, this.edgeLength);
        vertexs[5] = new Vertex(-this.edgeLength, -this.edgeLength, this.edgeLength);
        vertexs[6] = new Vertex(-this.edgeLength, -this.edgeLength, -this.edgeLength);
        vertexs[7] = new Vertex(this.edgeLength, -this.edgeLength, -this.edgeLength);
        distance =  distCoeff*this.edgeLength;
    }
       /**
        * Повернуть куб 
        * @param yaw    угол поворота в плоскости XY
        * @param pitch   угол поворота в плоскости ZX
        * @param roll  угол поворота в плоскости ZY
        */
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
    /**
     *
     * @return массив X  координат спроецированных вершин
     */
    public int [] getXProjection ()
    {
        int [] tmp = new int [8];
        
        for (int i=0;i<8;i++)
        {
            tmp[i] =  ((int) Math.round(vertexs[i].getX()*(distance-vertexs[i].getZ())/(distance+edgeLength)));
        }        
        return tmp;
    }
    /**
     *
     * @return  массив Y координат спроецированных вершин
     */
    public  int [] getYProjection ()
    {
        int [] tmp = new int [8];
        for (int i=0;i<8;i++)
        {
            tmp [i] = (int) Math.round(vertexs[i].getY()*(distance-vertexs[i].getZ())/(distance+edgeLength));
        }
        return tmp;
    }
    /**
     *
     * @return массив Z координат вершин
     */
    public int [] getZProjection ()
    {
        int [] tmp = new int [8];
        for (int i=0;i<8;i++)
        {
            tmp [i] = (int) Math.round(vertexs[i].getZ() );
        }
        return tmp;
    }
    /**
     * Повернуть куб  в плоскости XY
     * @param yaw  угол поворота в плоскости XY
     */
    public void yawing (double yaw)
    {
        double cos = Math.cos(yaw);
        double sin = Math.sin (yaw);
        /** Для поворота применяем соответсвующую матрицу преобразования координат*/
        for (int i=0; i<8;i++)
        {
            double newX = vertexs[i].getX()*cos-vertexs[i].getY()*sin;
            double newY = vertexs[i].getX()*sin+vertexs[i].getY()*cos;
            double newZ = vertexs[i].getZ();

            vertexs[i] = new Vertex(newX, newY, newZ);
        }
    }
    /**
     * Повернуть куб в плоскости ZX
     * @param pitch   угол поворота в плоскости ZX
     */
    public void pitching (double pitch)
    {
        double cos = Math.cos(pitch);
        double sin = Math.sin (pitch);
        /** Для поворота применяем соответсвующую матрицу преобразования координат*/
        for (int i=0;i<8;i++)
        {
            double newX = vertexs[i].getX()*cos + vertexs[i].getZ()*sin;
            double newY = vertexs[i].getY();
            double newZ = -vertexs[i].getX()*sin+vertexs[i].getZ()*cos;

            vertexs[i] = new Vertex (newX,newY,newZ);
        }
    }
   /**
    * Повернуть куб в плоскости ZY
    * @param roll   угол поворота в плоскости ZY
    */
    public void rolling (double roll)
    {
        double cos = Math.cos(roll);
        double sin = Math.sin(roll);
        /** Для поворота применяем соответсвующую матрицу преобразования координат*/
        for (int i=0;i<8;i++)
        {
            double newX = vertexs[i].getX();
            double newY = vertexs[i].getY()*cos - vertexs[i].getZ()*sin;
            double newZ = vertexs[i].getY()*sin + vertexs[i].getZ()*cos;

            vertexs[i] = new Vertex(newX, newY,newZ);
        }        
    }
    /**
     * Получить длину куба
     * @return длину ребра куба
     */
    public int getEdgeLength()
    {
        return edgeLength*2;
    }

    /**
     * Устанавливает новую длину куба. 
     * @param edgeLength   новая длина ребра куба
     */
    public void setEdgeLength(int edgeLength)
    {
        edgeLength = edgeLength/2;
        for (int i=0;i<8;i++)
        {
            vertexs[i].setNorm(edgeLength*Math.sqrt(3));
        }
        this.edgeLength = edgeLength;
        distance =  distCoeff*this.edgeLength;
    }

    public Side[] getProjectedSides ()
    {
        int[] x = this.getXProjection();
        int[] y = this.getYProjection();
        int[] z = this.getZProjection();

        Side[] sides = new Side[6];

        int[] SideAX =
        {
            x[0], x[1], x[2], x[3]
        };
        int[] SideBX =
        {
            x[0], x[1], x[5], x[4]
        };
        int[] SideCX =
        {
            x[0], x[3], x[7], x[4]
        };
        int[] SideDX =
        {
            x[6], x[5], x[1], x[2]
        };
        int[] SideEX =
        {
            x[6], x[7], x[4], x[5]
        };
        int[] SideFX =
        {
            x[6], x[2], x[3], x[7]
        };

        int[] SideAY =
        {
            y[0], y[1], y[2], y[3]
        };
        int[] SideBY =
        {
            y[0], y[1], y[5], y[4]
        };
        int[] SideCY =
        {
            y[0], y[3], y[7], y[4]
        };
        int[] SideDY =
        {
            y[6], y[5], y[1], y[2]
        };
        int[] SideEY =
        {
            y[6], y[7], y[4], y[5]
        };
        int[] SideFY =
        {
            y[6], y[2], y[3], y[7]
        };

        int SideAZCenter = (z[0] + z[2]) / 2;
        int SideBZCenter = (z[0] + z[5]) / 2;
        int SideCZCenter = (z[0] + z[7]) / 2;
        int SideDZCenter = (z[6] + z[1]) / 2;
        int SideEZCenter = (z[6] + z[4]) / 2;
        int SideFZCenter = (z[6] + z[3]) / 2;

        sides[0] = new Side(SideAZCenter, SideAX, SideAY, new Color(238, 135, 31));
        sides[1] = new Side(SideBZCenter, SideBX, SideBY, new Color(84, 31, 20));
        sides[2] = new Side(SideCZCenter, SideCX, SideCY, new Color(147, 129, 114));
        sides[3] = new Side(SideDZCenter, SideDX, SideDY, new Color(204, 158, 97));
        sides[4] = new Side(SideEZCenter, SideEX, SideEY, new Color(98, 98, 102));
        sides[5] = new Side(SideFZCenter, SideFX, SideFY, new Color(254, 233, 142));
        /**Сортировка граней по Z координате. От нижних к верхним. Для правильной
         * очередности отрисовки
         */
        Arrays.sort(sides);
        return sides;
    }
}
