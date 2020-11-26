package com.jhlabs.image;

import java.awt.Rectangle;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageFilter;

// Referenced classes of package com.jhlabs.image:
//            WholeImageFilter, ImageMath

public abstract class TransformFilter extends WholeImageFilter
{

    public void setEdgeAction(int i)
    {
        edgeAction = i;
    }

    public int getEdgeAction()
    {
        return edgeAction;
    }

    protected abstract void transformInverse(int i, int j, double ad[]);

    protected void transformSpace(Rectangle rectangle)
    {
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
        int l = super.transformedSpace.width;
        int i1 = super.transformedSpace.height;
        int j2 = 0;
        int ai[] = new int[l * i1];
        int j1 = super.transformedSpace.x;
        int k1 = super.transformedSpace.y;
        int ai1[] = new int[4];
        double ad[] = new double[2];
        for(int k2 = 0; k2 < i1; k2++)
        {
            for(int l2 = 0; l2 < l; l2++)
            {
                transformInverse(j1 + l2, k1 + k2, ad);
                int l1 = (int)ad[0];
                int i2 = (int)ad[1];
                if(l1 < 0 || l1 >= j || i2 < 0 || i2 >= k)
                {
                    int i3;
                    switch(edgeAction)
                    {
                    case 0: // '\0'
                    default:
                        i3 = 0;
                        break;

                    case 2: // '\002'
                        i3 = super.inPixels[ImageMath.mod(i2, k) * j + ImageMath.mod(l1, j)];
                        break;

                    case 1: // '\001'
                        i3 = super.inPixels[ImageMath.clamp(i2, 0, k - 1) * j + ImageMath.clamp(l1, 0, j - 1)];
                        break;
                    }
                    ai[j2++] = i3;
                } else
                {
                    double d = ad[0] - (double)l1;
                    double d1 = ad[1] - (double)i2;
                    int j3 = j * i2 + l1;
                    int k3 = l1 != j - 1 ? 1 : 0;
                    int l3 = i2 != k - 1 ? j : 0;
                    ai1[0] = super.inPixels[j3];
                    ai1[1] = super.inPixels[j3 + k3];
                    ai1[2] = super.inPixels[j3 + l3];
                    ai1[3] = super.inPixels[j3 + k3 + l3];
                    ai[j2++] = ImageMath.bilinearInterpolate(d, d1, ai1);
                }
            }

        }

        super.consumer.setPixels(0, 0, l, i1, super.defaultRGBModel, ai, 0, l);
        super.consumer.imageComplete(i);
        super.inPixels = null;
        ai = null;
    }

    public TransformFilter()
    {
        edgeAction = 0;
    }

    public static final int ZERO = 0;
    public static final int CLAMP = 1;
    public static final int WRAP = 2;
    protected int edgeAction;
}
