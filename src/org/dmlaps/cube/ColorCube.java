package org.dmlaps.cube;

import org.dmlaps.gfx.ColorRGB;

import java.util.Arrays;

/**
 * Класс "Цветовой куб".
 * Служит для разбиения сторон на сегменты, определение цвета сегмента.
 *
 * @author Ilya Arkhanhelsky
 */
public class ColorCube extends Cube
{

    /**
     * RGB индексы вершин
     */
    private ColorRGB[] rgbIDs;

    /**
     * Создает куб с центром в точке (0,0,0) и длинной ребра edgeLength
     *
     * @param edgeLength длина ребра куба
     */
    public ColorCube(int edgeLength)
    {
        super(edgeLength);
        rgbIDs = new ColorRGB[8];
        rgbIDs[0] = new ColorRGB(0.0, 1.0, 0.0);
        rgbIDs[1] = new ColorRGB(0.0, 0.0, 0.0);
        rgbIDs[2] = new ColorRGB(0.0, 0.0, 1.0);
        rgbIDs[3] = new ColorRGB(0.0, 1.0, 1.0);
        rgbIDs[4] = new ColorRGB(1.0, 1.0, 0.0);
        rgbIDs[5] = new ColorRGB(1.0, 0.0, 0.0);
        rgbIDs[6] = new ColorRGB(1.0, 0.0, 1.0);
        rgbIDs[7] = new ColorRGB(1.0, 1.0, 1.0);
    }

    /**
     * Проецирует на экран грани куба
     *
     * @return спроецированные на UV плоскость (плоскость экрана) грани куба
     */
    @Override
    public ColorSide[] getProjectedSides()
    {
        int[] x = this.getXProjection();
        int[] y = this.getYProjection();
        int[] z = this.getZProjection();

        ColorSide[] sides = new ColorSide[6];
        ColorRGB[] SideAColor =
                {
                        rgbIDs[0], rgbIDs[1], rgbIDs[2], rgbIDs[3]
                };
        ColorRGB[] SideBColor =
                {
                        rgbIDs[0], rgbIDs[1], rgbIDs[5], rgbIDs[4]
                };
        ColorRGB[] SideCColor =
                {
                        rgbIDs[0], rgbIDs[3], rgbIDs[7], rgbIDs[4]
                };
        ColorRGB[] SideDColor =
                {
                        rgbIDs[6], rgbIDs[5], rgbIDs[1], rgbIDs[2]
                };
        ColorRGB[] SideEColor =
                {
                        rgbIDs[6], rgbIDs[7], rgbIDs[4], rgbIDs[5]
                };
        ColorRGB[] SideFColor =
                {
                        rgbIDs[6], rgbIDs[2], rgbIDs[3], rgbIDs[7]
                };
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

        sides[0] = new ColorSide(SideAZCenter, SideAX, SideAY, SideAColor);
        sides[1] = new ColorSide(SideBZCenter, SideBX, SideBY, SideBColor);
        sides[2] = new ColorSide(SideCZCenter, SideCX, SideCY, SideCColor);
        sides[3] = new ColorSide(SideDZCenter, SideDX, SideDY, SideDColor);
        sides[4] = new ColorSide(SideEZCenter, SideEX, SideEY, SideEColor);
        sides[5] = new ColorSide(SideFZCenter, SideFX, SideFY, SideFColor);
        /**Сортировка граней по Z координате. От нижних к верхним. Для правильной
         * очередности отрисовки
         */
        Arrays.sort(sides);
        return sides;
    }
}
