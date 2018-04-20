//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import edu.princeton.cs.algs4.Picture;

public class SeamRemover {
    public SeamRemover() {
    }

    public static Picture removeHorizontalSeam(Picture var0, int[] var1) {
        if(var1 == null) {
            throw new NullPointerException("Input seam array cannot be null.");
        } else if(var0.width() == 1) {
            throw new IllegalArgumentException("Image width is 1.");
        } else if(var1.length != var0.width()) {
            throw new IllegalArgumentException("Seam length does not match image width.");
        } else {
            for(int var2 = 0; var2 < var1.length - 2; ++var2) {
                if(Math.abs(var1[var2] - var1[var2 + 1]) > 1) {
                    throw new IllegalArgumentException("Invalid seam, consecutive vertical indices are greater than one apart.");
                }
            }

            Picture var5 = new Picture(var0.width(), var0.height() - 1);

            for(int var3 = 0; var3 < var0.width(); ++var3) {
                int var4;
                for(var4 = 0; var4 < var1[var3]; ++var4) {
                    var5.set(var3, var4, var0.get(var3, var4));
                }

                for(var4 = var1[var3] + 1; var4 < var0.height(); ++var4) {
                    var5.set(var3, var4 - 1, var0.get(var3, var4));
                }
            }

            return var5;
        }
    }

    public static Picture removeVerticalSeam(Picture var0, int[] var1) {
        if(var1 == null) {
            throw new NullPointerException("Input seam array cannot be null.");
        } else if(var0.height() == 1) {
            throw new IllegalArgumentException("Image height is 1.");
        } else if(var1.length != var0.height()) {
            throw new IllegalArgumentException("Seam length does not match image height.");
        } else {
            for(int var2 = 0; var2 < var1.length - 2; ++var2) {
                if(Math.abs(var1[var2] - var1[var2 + 1]) > 1) {
                    throw new IllegalArgumentException("Invalid seam, consecutive horizontal indices are greater than one apart.");
                }
            }

            Picture var5 = new Picture(var0.width() - 1, var0.height());

            for(int var3 = 0; var3 < var0.height(); ++var3) {
                int var4;
                for(var4 = 0; var4 < var1[var3]; ++var4) {
                    var5.set(var4, var3, var0.get(var4, var3));
                }

                for(var4 = var1[var3] + 1; var4 < var0.width(); ++var4) {
                    var5.set(var4 - 1, var3, var0.get(var4, var3));
                }
            }

            return var5;
        }
    }
}
