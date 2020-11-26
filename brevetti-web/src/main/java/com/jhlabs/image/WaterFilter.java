package com.jhlabs.image;

import java.awt.Rectangle;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageFilter;

// Referenced classes of package com.jhlabs.image:
//            WholeImageFilter, ImageMath

public class WaterFilter extends WholeImageFilter
{

    public WaterFilter()
    {
        wavelength = 16D;
        amplitude = 10D;
        phase = 0.0D;
        antialias = true;
    }

    public void setWavelength(double d)
    {
        wavelength = d;
    }

    public double getWavelength()
    {
        return wavelength;
    }

    public void setAmplitude(double d)
    {
        amplitude = d;
    }

    public double getAmplitude()
    {
        return amplitude;
    }

    public void setPhase(double d)
    {
        phase = d;
    }

    public double getPhase()
    {
        return phase;
    }

    public void setAntialias(boolean flag)
    {
        antialias = flag;
    }

    public boolean getAntialias()
    {
        return antialias;
    }

    private boolean inside(int i, int j, int k)
    {
        return j <= i && i <= k;
    }

    public void imageComplete(int i)
    {
        if(i == 1 || i == 4 || i == 3)
        {
            super.consumer.imageComplete(i);
            return;
        }
        int j = super.originalSpace.width;
        int k = super.originalSpace.height;
        int l = 0;
        int i1 = j / 2;
        int j1 = k / 2;
        int ai[] = new int[j * k];
        int ai1[] = new int[4];
        for(int k1 = 0; k1 < k; k1++)
        {
            for(int l1 = 0; l1 < j; l1++)
            {
                double d = l1 - i1;
                double d1 = k1 - j1;
                double d2 = Math.sqrt(d * d + d1 * d1);
                double d3 = amplitude * Math.sin((d2 / wavelength) * 2D * 3.1415926535897931D + phase);
                double d4 = (double)i1 + d + d3;
                double d5 = (double)j1 + d1 + d3;
                d4 = ImageMath.clamp(d4, 0.0D, j - 1);
                d5 = ImageMath.clamp(d5, 0.0D, k - 1);
                int i2;
                if(antialias)
                {
                    int j2 = (int)d4;
                    int l2 = (int)d5;
                    boolean flag = inside(j2, 0, j - 1);
                    boolean flag1 = inside(l2, 0, k - 1);
                    boolean flag2 = inside(j2, 0, j - 2);
                    boolean flag3 = inside(l2, 0, k - 2);
                    int j3 = l2 * j + j2;
                    if(flag && flag1)
                        ai1[0] = super.inPixels[j3];
                    else
                        ai1[0] = 0xff000000;
                    if(flag2 && flag1)
                        ai1[1] = super.inPixels[j3 + 1];
                    else
                        ai1[1] = 0xff000000;
                    if(flag && flag3)
                        ai1[2] = super.inPixels[j3 + j];
                    else
                        ai1[2] = 0xff000000;
                    if(flag2 && flag3)
                        ai1[3] = super.inPixels[j3 + j + 1];
                    else
                        ai1[3] = 0xff000000;
                    d4 = ImageMath.mod(d4, 1.0D);
                    d5 = ImageMath.mod(d5, 1.0D);
                    i2 = ImageMath.bilinearInterpolate(d4, d5, ai1);
                } else
                {
                    int k2 = ImageMath.clamp((int)(d4 + 0.5D), 0, j - 1);
                    int i3 = ImageMath.clamp((int)(d5 + 0.5D), 0, k - 1);
                    i2 = super.inPixels[i3 * j + k2];
                }
                ai[l++] = i2;
            }

        }

        super.consumer.setPixels(0, 0, j, k, super.defaultRGBModel, ai, 0, j);
        super.consumer.imageComplete(i);
        super.inPixels = null;
    }

    public String toString()
    {
        return "Distort/Water Ripples...";
    }

    static final long serialVersionUID = 0x79f9a3e3012ef15dL;
    private double wavelength;
    private double amplitude;
    private double phase;
    private boolean antialias;
}
