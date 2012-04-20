/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Cube;

import java.awt.Color;

/**
 *
 * @author Архангельский Илья
 */
public class ColorSide extends Side {
    private RGB [] rgbIDs;

    public ColorSide(int zAxis, int[] xPoints, int[] yPoints, RGB[] rgbIDs)
    {
        super(zAxis, xPoints, yPoints, Color.BLACK);
        this.rgbIDs = rgbIDs;
        RGB color = new RGB ((rgbIDs[0].getR()+rgbIDs[1].getR()+rgbIDs[2].getR()+rgbIDs[3].getR())/4,
                (rgbIDs[0].getG()+rgbIDs[1].getG()+rgbIDs[2].getG()+rgbIDs[3].getG())/4,
                (rgbIDs[0].getB()+rgbIDs[1].getB()+rgbIDs[2].getB()+rgbIDs[3].getB())/4
                );
        this.setColor(new Color ((float) color.getR(),(float) color.getG(),(float)color.getB()));       
    }

    public Side[] pieces (int count)
    {
        Side[] pSides = new Side[count*count];
        
        for (int i=0;i<count;i++)
        {
            int xUp = (xPoints[2]-xPoints[1])*i/count+xPoints[1];
            int yUp = (yPoints[2]-yPoints[1])*i/count+yPoints[1];
            
            int xUpNxt = (xPoints[2]-xPoints[1])*(i+1)/count+xPoints[1];
            int yUpNxt = (yPoints[2]-yPoints[1])*(i+1)/count+yPoints[1];

            int xBot = (xPoints[3]-xPoints[0])*i/count+xPoints[0];
            int yBot = (yPoints[3]-yPoints[0])*i/count+yPoints[0];

            int xBotNxt = (xPoints[3]-xPoints[0])*(i+1)/count+xPoints[0];
            int yBotNxt = (yPoints[3]-yPoints[0])*(i+1)/count+yPoints[0];
            
            for (int j=0;j<count;j++)
            {
                int x00 = (xUp-xBot)*j/count+xBot;
                int y00 = (yUp-yBot)*j/count+yBot;

                int x01 =  (xUp-xBot)*(j+1)/count+xBot;
                int y01 =  (yUp-yBot)*(j+1)/count+yBot;

                int x02 =  (xUpNxt-xBotNxt)*(j+1)/count+xBotNxt;
                int y02 =  (yUpNxt-yBotNxt)*(j+1)/count+yBotNxt;

                int x03 =  (xUpNxt-xBotNxt)*j/count+xBotNxt;
                int y03 =  (yUpNxt-yBotNxt)*j/count+yBotNxt;

                int [] resX = {x00,x01,x02,x03};
                int [] resY = {y00,y01,y02,y03};

                pSides[i*count+j] = new Side(zAxis, resX, resY, new Color ((float) Math.random(),(float) Math.random(),(float) Math.random()));
            }
        }
        return pSides;
    }


}
