package me.earth.plugins.phobosgui.util;

import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class PhobosColorUtil
{
    public PhobosColorUtil() {
        super();
    }
    
    private ArrayList<ColorName> initColorList() {
        final ArrayList list = new ArrayList();
        list.add(new ColorName("AliceBlue", 240, 248, 255));
        list.add(new ColorName("AntiqueWhite", 250, 235, 215));
        list.add(new ColorName("Aqua", 0, 255, 255));
        list.add(new ColorName("Aquamarine", 127, 255, 212));
        list.add(new ColorName("Azure", 240, 255, 255));
        list.add(new ColorName("Beige", 245, 245, 220));
        list.add(new ColorName("Bisque", 255, 228, 196));
        list.add(new ColorName("Black", 0, 0, 0));
        list.add(new ColorName("BlanchedAlmond", 255, 235, 205));
        list.add(new ColorName("Blue", 0, 0, 255));
        list.add(new ColorName("BlueViolet", 138, 43, 226));
        list.add(new ColorName("Brown", 165, 42, 42));
        list.add(new ColorName("BurlyWood", 222, 184, 135));
        list.add(new ColorName("CadetBlue", 95, 158, 160));
        list.add(new ColorName("Chartreuse", 127, 255, 0));
        list.add(new ColorName("Chocolate", 210, 105, 30));
        list.add(new ColorName("Coral", 255, 127, 80));
        list.add(new ColorName("CornflowerBlue", 100, 149, 237));
        list.add(new ColorName("Cornsilk", 255, 248, 220));
        list.add(new ColorName("Crimson", 220, 20, 60));
        list.add(new ColorName("Cyan", 0, 255, 255));
        list.add(new ColorName("DarkBlue", 0, 0, 139));
        list.add(new ColorName("DarkCyan", 0, 139, 139));
        list.add(new ColorName("DarkGoldenRod", 184, 134, 11));
        list.add(new ColorName("DarkGray", 169, 169, 169));
        list.add(new ColorName("DarkGreen", 0, 100, 0));
        list.add(new ColorName("DarkKhaki", 189, 183, 107));
        list.add(new ColorName("DarkMagenta", 139, 0, 139));
        list.add(new ColorName("DarkOliveGreen", 85, 107, 47));
        list.add(new ColorName("DarkOrange", 255, 140, 0));
        list.add(new ColorName("DarkOrchid", 153, 50, 204));
        list.add(new ColorName("DarkRed", 139, 0, 0));
        list.add(new ColorName("DarkSalmon", 233, 150, 122));
        list.add(new ColorName("DarkSeaGreen", 143, 188, 143));
        list.add(new ColorName("DarkSlateBlue", 72, 61, 139));
        list.add(new ColorName("DarkSlateGray", 47, 79, 79));
        list.add(new ColorName("DarkTurquoise", 0, 206, 209));
        list.add(new ColorName("DarkViolet", 148, 0, 211));
        list.add(new ColorName("DeepPink", 255, 20, 147));
        list.add(new ColorName("DeepSkyBlue", 0, 191, 255));
        list.add(new ColorName("DimGray", 105, 105, 105));
        list.add(new ColorName("DodgerBlue", 30, 144, 255));
        list.add(new ColorName("FireBrick", 178, 34, 34));
        list.add(new ColorName("FloralWhite", 255, 250, 240));
        list.add(new ColorName("ForestGreen", 34, 139, 34));
        list.add(new ColorName("Fuchsia", 255, 0, 255));
        list.add(new ColorName("Gainsboro", 220, 220, 220));
        list.add(new ColorName("GhostWhite", 248, 248, 255));
        list.add(new ColorName("Gold", 255, 215, 0));
        list.add(new ColorName("GoldenRod", 218, 165, 32));
        list.add(new ColorName("Gray", 128, 128, 128));
        list.add(new ColorName("Green", 0, 128, 0));
        list.add(new ColorName("GreenYellow", 173, 255, 47));
        list.add(new ColorName("HoneyDew", 240, 255, 240));
        list.add(new ColorName("HotPink", 255, 105, 180));
        list.add(new ColorName("IndianRed", 205, 92, 92));
        list.add(new ColorName("Indigo", 75, 0, 130));
        list.add(new ColorName("Ivory", 255, 255, 240));
        list.add(new ColorName("Khaki", 240, 230, 140));
        list.add(new ColorName("Lavender", 230, 230, 250));
        list.add(new ColorName("LavenderBlush", 255, 240, 245));
        list.add(new ColorName("LawnGreen", 124, 252, 0));
        list.add(new ColorName("LemonChiffon", 255, 250, 205));
        list.add(new ColorName("LightBlue", 173, 216, 230));
        list.add(new ColorName("LightCoral", 240, 128, 128));
        list.add(new ColorName("LightCyan", 224, 255, 255));
        list.add(new ColorName("LightGoldenRodYellow", 250, 250, 210));
        list.add(new ColorName("LightGray", 211, 211, 211));
        list.add(new ColorName("LightGreen", 144, 238, 144));
        list.add(new ColorName("LightPink", 255, 182, 193));
        list.add(new ColorName("LightSalmon", 255, 160, 122));
        list.add(new ColorName("LightSeaGreen", 32, 178, 170));
        list.add(new ColorName("LightSkyBlue", 135, 206, 250));
        list.add(new ColorName("LightSlateGray", 119, 136, 153));
        list.add(new ColorName("LightSteelBlue", 176, 196, 222));
        list.add(new ColorName("LightYellow", 255, 255, 224));
        list.add(new ColorName("Lime", 0, 255, 0));
        list.add(new ColorName("LimeGreen", 50, 205, 50));
        list.add(new ColorName("Linen", 250, 240, 230));
        list.add(new ColorName("Magenta", 255, 0, 255));
        list.add(new ColorName("Maroon", 128, 0, 0));
        list.add(new ColorName("MediumAquaMarine", 102, 205, 170));
        list.add(new ColorName("MediumBlue", 0, 0, 205));
        list.add(new ColorName("MediumOrchid", 186, 85, 211));
        list.add(new ColorName("MediumPurple", 147, 112, 219));
        list.add(new ColorName("MediumSeaGreen", 60, 179, 113));
        list.add(new ColorName("MediumSlateBlue", 123, 104, 238));
        list.add(new ColorName("MediumSpringGreen", 0, 250, 154));
        list.add(new ColorName("MediumTurquoise", 72, 209, 204));
        list.add(new ColorName("MediumVioletRed", 199, 21, 133));
        list.add(new ColorName("MidnightBlue", 25, 25, 112));
        list.add(new ColorName("MintCream", 245, 255, 250));
        list.add(new ColorName("MistyRose", 255, 228, 225));
        list.add(new ColorName("Moccasin", 255, 228, 181));
        list.add(new ColorName("NavajoWhite", 255, 222, 173));
        list.add(new ColorName("Navy", 0, 0, 128));
        list.add(new ColorName("OldLace", 253, 245, 230));
        list.add(new ColorName("Olive", 128, 128, 0));
        list.add(new ColorName("OliveDrab", 107, 142, 35));
        list.add(new ColorName("Orange", 255, 165, 0));
        list.add(new ColorName("OrangeRed", 255, 69, 0));
        list.add(new ColorName("Orchid", 218, 112, 214));
        list.add(new ColorName("PaleGoldenRod", 238, 232, 170));
        list.add(new ColorName("PaleGreen", 152, 251, 152));
        list.add(new ColorName("PaleTurquoise", 175, 238, 238));
        list.add(new ColorName("PaleVioletRed", 219, 112, 147));
        list.add(new ColorName("PapayaWhip", 255, 239, 213));
        list.add(new ColorName("PeachPuff", 255, 218, 185));
        list.add(new ColorName("Peru", 205, 133, 63));
        list.add(new ColorName("Pink", 255, 192, 203));
        list.add(new ColorName("Plum", 221, 160, 221));
        list.add(new ColorName("PowderBlue", 176, 224, 230));
        list.add(new ColorName("Purple", 128, 0, 128));
        list.add(new ColorName("Red", 255, 0, 0));
        list.add(new ColorName("RosyBrown", 188, 143, 143));
        list.add(new ColorName("RoyalBlue", 65, 105, 225));
        list.add(new ColorName("SaddleBrown", 139, 69, 19));
        list.add(new ColorName("Salmon", 250, 128, 114));
        list.add(new ColorName("SandyBrown", 244, 164, 96));
        list.add(new ColorName("SeaGreen", 46, 139, 87));
        list.add(new ColorName("SeaShell", 255, 245, 238));
        list.add(new ColorName("Sienna", 160, 82, 45));
        list.add(new ColorName("Silver", 192, 192, 192));
        list.add(new ColorName("SkyBlue", 135, 206, 235));
        list.add(new ColorName("SlateBlue", 106, 90, 205));
        list.add(new ColorName("SlateGray", 112, 128, 144));
        list.add(new ColorName("Snow", 255, 250, 250));
        list.add(new ColorName("SpringGreen", 0, 255, 127));
        list.add(new ColorName("SteelBlue", 70, 130, 180));
        list.add(new ColorName("Tan", 210, 180, 140));
        list.add(new ColorName("Teal", 0, 128, 128));
        list.add(new ColorName("Thistle", 216, 191, 216));
        list.add(new ColorName("Tomato", 255, 99, 71));
        list.add(new ColorName("Turquoise", 64, 224, 208));
        list.add(new ColorName("Violet", 238, 130, 238));
        list.add(new ColorName("Wheat", 245, 222, 179));
        list.add(new ColorName("White", 255, 255, 255));
        list.add(new ColorName("WhiteSmoke", 245, 245, 245));
        list.add(new ColorName("Yellow", 255, 255, 0));
        list.add(new ColorName("YellowGreen", 154, 205, 50));
        return list;
    }
    
    public static int toRGBA(final double n, final double n2, final double n3, final double n4) {
        return toRGBA((float)n, (float)n2, (float)n3, (float)n4);
    }
    
    public String getColorNameFromRgb(final int n, final int n2, final int n3) {
        final ArrayList<ColorName> initColorList = this.initColorList();
        ColorName colorName = null;
        int n4 = Integer.MAX_VALUE;
        for (final ColorName colorName2 : initColorList) {
            final int computeMSE = colorName2.computeMSE(n, n2, n3);
            if (computeMSE < n4) {
                n4 = computeMSE;
                colorName = colorName2;
            }
        }
        if (colorName != null) {
            return colorName.getName();
        }
        return "No matched color name.";
    }
    
    public String getColorNameFromHex(final int n) {
        return this.getColorNameFromRgb((n & 0xFF0000) >> 16, (n & 0xFF00) >> 8, n & 0xFF);
    }
    
    public int colorToHex(final Color color) {
        return Integer.decode("0x" + Integer.toHexString(color.getRGB()).substring(2));
    }
    
    public String getColorNameFromColor(final Color color) {
        return this.getColorNameFromRgb(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public static int toRGBA(final int n, final int n2, final int n3) {
        return toRGBA(n, n2, n3, 255);
    }
    
    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }
    
    public static int toARGB(final int n, final int n2, final int n3, final int n4) {
        return new Color(n, n2, n3, n4).getRGB();
    }
    
    public static int toRGBA(final float n, final float n2, final float n3, final float n4) {
        return toRGBA((int)(n * 255.0f), (int)(n2 * 255.0f), (int)(n3 * 255.0f), (int)(n4 * 255.0f));
    }
    
    public static int toRGBA(final float[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA(array[0], array[1], array[2], array[3]);
    }
    
    public static int toRGBA(final double[] array) {
        if (array.length != 4) {
            throw new IllegalArgumentException("colors[] must have a length of 4!");
        }
        return toRGBA((float)array[0], (float)array[1], (float)array[2], (float)array[3]);
    }
    
    public static int toRGBA(final Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static int[] toRGBAArray(final int n) {
        return new int[] { n >> 16 & 0xFF, n >> 8 & 0xFF, n & 0xFF, n >> 24 & 0xFF };
    }
    
    public static int changeAlpha(int n, final int n2) {
        n &= 0xFFFFFF;
        return n2 << 24 | n;
    }
    
    public static class HueCycler
    {
        public int index;
        public int[] cycles;
        
        public HueCycler(final int n) {
            super();
            this.index = 0;
            if (n <= 0) {
                throw new IllegalArgumentException("cycles <= 0");
            }
            this.cycles = new int[n];
            double n2 = 0.0;
            final double n3 = 1.0 / n;
            for (int i = 0; i < n; ++i) {
                this.cycles[i] = Color.HSBtoRGB((float)n2, 1.0f, 1.0f);
                n2 += n3;
            }
        }
        
        public void reset() {
            this.index = 0;
        }
        
        public void reset(final int index) {
            this.index = index;
        }
        
        public int next() {
            final int n = this.cycles[this.index];
            ++this.index;
            if (this.index >= this.cycles.length) {
                this.index = 0;
            }
            return n;
        }
        
        public void setNext() {
            this.next();
        }
        
        public void set() {
            final int n = this.cycles[this.index];
            GL11.glColor3f((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f);
        }
        
        public void setNext(final float n) {
            final int next = this.next();
            GL11.glColor4f((next >> 16 & 0xFF) / 255.0f, (next >> 8 & 0xFF) / 255.0f, (next & 0xFF) / 255.0f, n);
        }
        
        public int current() {
            return this.cycles[this.index];
        }
    }
    
    public static class Colors
    {
        public static final int WHITE;
        public static final int BLACK;
        public static final int RED;
        public static final int GREEN;
        public static final int BLUE;
        public static final int ORANGE;
        public static final int PURPLE;
        public static final int GRAY;
        public static final int DARK_RED;
        public static final int YELLOW;
        public static final int RAINBOW = Integer.MIN_VALUE;
        
        public Colors() {
            super();
        }
        
        static {
            WHITE = PhobosColorUtil.toRGBA(255, 255, 255, 255);
            BLACK = PhobosColorUtil.toRGBA(0, 0, 0, 255);
            RED = PhobosColorUtil.toRGBA(255, 0, 0, 255);
            GREEN = PhobosColorUtil.toRGBA(0, 255, 0, 255);
            BLUE = PhobosColorUtil.toRGBA(0, 0, 255, 255);
            ORANGE = PhobosColorUtil.toRGBA(255, 128, 0, 255);
            PURPLE = PhobosColorUtil.toRGBA(163, 73, 163, 255);
            GRAY = PhobosColorUtil.toRGBA(127, 127, 127, 255);
            DARK_RED = PhobosColorUtil.toRGBA(64, 0, 0, 255);
            YELLOW = PhobosColorUtil.toRGBA(255, 255, 0, 255);
        }
    }
    
    public static class ColorName
    {
        public int r;
        public int g;
        public int b;
        public String name;
        
        public ColorName(final String name, final int r, final int g, final int b) {
            super();
            this.r = r;
            this.g = g;
            this.b = b;
            this.name = name;
        }
        
        public int computeMSE(final int n, final int n2, final int n3) {
            return ((n - this.r) * (n - this.r) + (n2 - this.g) * (n2 - this.g) + (n3 - this.b) * (n3 - this.b)) / 3;
        }
        
        public int getR() {
            return this.r;
        }
        
        public int getG() {
            return this.g;
        }
        
        public int getB() {
            return this.b;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
