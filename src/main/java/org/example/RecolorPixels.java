package org.example;

import java.awt.image.BufferedImage;

public class RecolorPixels {
    private final BufferedImage originalImage;
    private final BufferedImage resultImage;
    private final int leftCorner, topCorner, width, height;

    public RecolorPixels(BufferedImage originalImage, BufferedImage resultImage, int leftCorner, int topCorner, int width, int height) {
        this.originalImage = originalImage;
        this.resultImage = resultImage;
        this.leftCorner = leftCorner;
        this.topCorner = topCorner;
        this.width = width;
        this.height = height;
    }

    public void recolorImage() {
        for (int x = leftCorner; x < leftCorner + width && x < originalImage.getWidth(); x++) {
            for (int y = topCorner; y < topCorner + height && y < originalImage.getHeight(); y++) {
                recolorPixel(originalImage, resultImage, x, y);
            }
        }
    }

    private void recolorPixel(BufferedImage originalImage, BufferedImage resultImage, int x, int y) {
        int rgb = originalImage.getRGB(x, y);
        int red = getRed(rgb);
        int green = getGreen(rgb);
        int blue = getBlue(rgb);

        int newRed;
        int newGreen;
        int newBlue;

        if (isShadeOfGray(red, green, blue)) {
            newRed = Math.min(255, red + 10);
            newGreen = Math.max(0, green - 80);
            newBlue = Math.max(0, blue - 20);
        } else {
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        int newRGB = createRGBFromColors(newRed, newGreen, newBlue);
        setRGB(resultImage, x, y, newRGB);
    }

    private boolean isShadeOfGray(int red, int green, int blue) {
        return Math.abs(red - green) < 30 && Math.abs(red - blue) < 30 && Math.abs(green - blue) < 30;
    }

    private int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    private int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    private int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }

    private int createRGBFromColors(int red, int green, int blue) {
        int rgb = 0;
        rgb |= blue;
        rgb |= green << 8;
        rgb |= red << 16;
        rgb |= 0xFF000000;
        return rgb;
    }

    private void setRGB(BufferedImage image, int x, int y, int rgb) {
        image.getRaster().setDataElements(x, y, image.getColorModel().getDataElements(rgb, null));
    }
}
