package dm.sandbox.cube;

import dm.sandbox.gfx.Color;

/**
 * Класс "Цветовая грань".
 * Дает возможность разбить грань на сегменты с "градиентным" заполнением цветом.
 *
 * @author Ilya Arkhanhelsky
 */
public class ColorSide extends Side
{
    /**
     * Color индексы вершин
     */
    private Color[] rgbIDs;

    /**
     * Построить грань с цветовыми индексами вершин
     *
     * @param zAxis   центр грани по Z
     * @param xPoints массив точек описывающих Х координаты вершин грани
     * @param yPoints массив точек описывающих Y координаты вершин грани
     * @param rgbIDs  массив Color индексов соответсвующих вершинам грани.
     */

    public ColorSide(int zAxis, int[] xPoints, int[] yPoints, dm.sandbox.gfx.Color[] rgbIDs)
    {
        super(zAxis, xPoints, yPoints, Color.BLACK);
        this.rgbIDs = rgbIDs;
        Color clr = new Color((rgbIDs[0].r() + rgbIDs[1].r() + rgbIDs[2].r() + rgbIDs[3].r()) / 4,
                (rgbIDs[0].g() + rgbIDs[1].g() + rgbIDs[2].g() + rgbIDs[3].g()) / 4,
                (rgbIDs[0].b() + rgbIDs[1].b() + rgbIDs[2].b() + rgbIDs[3].b()) / 4
        );
        this.setColor(clr);
    }

    /**
     * Метод для получения массива сегментов грани
     *
     * @param uCount - количество сегментов вдоль стороны U
     * @param vCount - количество сегментов вдоль стороны V
     * @return массив сегментов грани.
     */
    public Side[] pieces(int uCount, int vCount)
    {
        Side[] pSides = new Side[uCount * vCount];
        // Суть метода получения нужных координат сводится к параметрическому заданию прямой в пространстве.
        for (int i = 0; i < uCount; i++)
        {
            // Вычисляем границы i-ого столбца из @count квадратных сегментов в ХY координатах
            int xUp = (xPoints[2] - xPoints[1]) * i / uCount + xPoints[1];
            int yUp = (yPoints[2] - yPoints[1]) * i / uCount + yPoints[1];

            int xUpNxt = (xPoints[2] - xPoints[1]) * (i + 1) / uCount + xPoints[1];
            int yUpNxt = (yPoints[2] - yPoints[1]) * (i + 1) / uCount + yPoints[1];

            int xBot = (xPoints[3] - xPoints[0]) * i / uCount + xPoints[0];
            int yBot = (yPoints[3] - yPoints[0]) * i / uCount + yPoints[0];

            int xBotNxt = (xPoints[3] - xPoints[0]) * (i + 1) / uCount + xPoints[0];
            int yBotNxt = (yPoints[3] - yPoints[0]) * (i + 1) / uCount + yPoints[0];

            // И Color координатах
            Color rgbUp = rgbIDs[2].sub(rgbIDs[1]).mul((double) i / (double) uCount).sum(rgbIDs[1]);
            Color rgbBot = rgbIDs[3].sub(rgbIDs[0]).mul((double) i / (double) uCount).sum(rgbIDs[0]);
            Color rgbUpNxt = rgbIDs[2].sub(rgbIDs[1]).mul((double) (i + 1) / (double) uCount).sum(rgbIDs[1]);
            Color rgbBotNxt = rgbIDs[3].sub(rgbIDs[0]).mul((double) (i + 1) / (double) uCount).sum(rgbIDs[0]);

            for (int j = 0; j < vCount; j++)
            {
                // В столбце выбираем j-ый квадтратный сегмент в XY координатах
                int x00 = (xUp - xBot) * j / vCount + xBot;
                int y00 = (yUp - yBot) * j / vCount + yBot;

                int x01 = (xUp - xBot) * (j + 1) / vCount + xBot;
                int y01 = (yUp - yBot) * (j + 1) / vCount + yBot;

                int x02 = (xUpNxt - xBotNxt) * (j + 1) / vCount + xBotNxt;
                int y02 = (yUpNxt - yBotNxt) * (j + 1) / vCount + yBotNxt;

                int x03 = (xUpNxt - xBotNxt) * j / vCount + xBotNxt;
                int y03 = (yUpNxt - yBotNxt) * j / vCount + yBotNxt;

                int[] resX = {x00, x01, x02, x03};
                int[] resY = {y00, y01, y02, y03};

                // И Color координатах
                Color clr = (Color) rgbUp.sub(rgbBot).mul((double) j / (double) vCount).sum(rgbBot);
                Color clrNxt = (Color) rgbUpNxt.sub(rgbBotNxt).mul((double) (j + 1) / (double) vCount).sum(rgbBot);
                Color realClr = (Color) clr.sum(clrNxt).mul(0.5);

                pSides[i * vCount + j] = new Side(zAxis, resX, resY, new Color(realClr.r(), realClr.g(), realClr.b()));
            }
        }
        return pSides;
    }


}
