package Cube;

import java.awt.Color;

/**
 * Класс "Цветовая грань".
 * Дает возможность разбить грань на сегменты с "градиентным" заполнением цветом.
 * @author Архангельский Илья
 */
public class ColorSide extends Side {
    /**
     * RGB индексы вершин
     */
    private RGB [] rgbIDs;

    /**
     * Построить грань с цветовыми индексами вершин
     * @param zAxis центр грани по Z
     * @param xPoints массив точек описывающих Х координаты вершин грани
     * @param yPoints массив точек описывающих Y координаты вершин грани
     * @param rgbIDs массив RGB индексов соответсвующих вершинам грани.
     */

    public ColorSide(int zAxis, int[] xPoints, int[] yPoints, RGB[] rgbIDs)
    {
        super(zAxis, xPoints, yPoints, Color.BLACK);
        this.rgbIDs = rgbIDs;
        RGB clr = new RGB ((rgbIDs[0].getR()+rgbIDs[1].getR()+rgbIDs[2].getR()+rgbIDs[3].getR())/4,
                (rgbIDs[0].getG()+rgbIDs[1].getG()+rgbIDs[2].getG()+rgbIDs[3].getG())/4,
                (rgbIDs[0].getB()+rgbIDs[1].getB()+rgbIDs[2].getB()+rgbIDs[3].getB())/4
                );
        this.setColor(new Color ((float) clr.getR(),(float) clr.getG(),(float)clr.getB()));
    }

    /**
     * Метод для получения массива сегментов грани
     * @param count корень квадратный из количества получаемых сегментов. Грань разбивается
     * на count сегментов по вертикали и count сегментов по горизонтали.
     * @return массив сегментов грани.
     */
    public Side[] pieces (int uCount, int vCount)
    {
        Side[] pSides = new Side[uCount*vCount];
        // Суть метода получения нужных координат сводится к параметрическому заданию прямой в пространстве.
        for (int i=0;i<uCount;i++)
        {
            // Вычисляем границы i-ого столбца из @count квадратных сегментов в ХY координатах
            int xUp = (xPoints[2]-xPoints[1])*i/uCount+xPoints[1];
            int yUp = (yPoints[2]-yPoints[1])*i/uCount+yPoints[1];
            
            int xUpNxt = (xPoints[2]-xPoints[1])*(i+1)/uCount+xPoints[1];
            int yUpNxt = (yPoints[2]-yPoints[1])*(i+1)/uCount+yPoints[1];

            int xBot = (xPoints[3]-xPoints[0])*i/uCount+xPoints[0];
            int yBot = (yPoints[3]-yPoints[0])*i/uCount+yPoints[0];

            int xBotNxt = (xPoints[3]-xPoints[0])*(i+1)/uCount+xPoints[0];
            int yBotNxt = (yPoints[3]-yPoints[0])*(i+1)/uCount+yPoints[0];

            // И RGB координатах
            RGB rgbUp = rgbIDs[2].diff(rgbIDs[1]).mulScalar((double)i / (double) uCount).sum(rgbIDs[1]);
            RGB rgbBot = rgbIDs[3].diff(rgbIDs[0]).mulScalar((double) i/(double) uCount).sum(rgbIDs[0]);
            
            for (int j=0;j<vCount;j++)
            {
                // В столбце выбираем j-ый квадтратный сегмент в XY координатах
                int x00 = (xUp-xBot)*j/vCount+xBot;
                int y00 = (yUp-yBot)*j/vCount+yBot;

                int x01 =  (xUp-xBot)*(j+1)/vCount+xBot;
                int y01 =  (yUp-yBot)*(j+1)/vCount+yBot;

                int x02 =  (xUpNxt-xBotNxt)*(j+1)/vCount+xBotNxt;
                int y02 =  (yUpNxt-yBotNxt)*(j+1)/vCount+yBotNxt;

                int x03 =  (xUpNxt-xBotNxt)*j/vCount+xBotNxt;
                int y03 =  (yUpNxt-yBotNxt)*j/vCount+yBotNxt;

                int [] resX = {x00,x01,x02,x03};
                int [] resY = {y00,y01,y02,y03};

                // И RGB координатах
                RGB clr = rgbUp.diff(rgbBot).mulScalar((double)j / (double) vCount).sum(rgbBot);

                pSides[i*vCount+j] = new Side(zAxis, resX, resY, new Color ((float)clr.getR() ,(float) clr.getG(),(float)  clr.getB()));
            }
        }
        return pSides;
    }


}