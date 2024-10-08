package org.example;

import java.awt.image.BufferedImage;

public class ImageProcessorSingleThreaded implements ImageProcessor {
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {
        RecolorPixels recolorPixels = new RecolorPixels(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
        recolorPixels.recolorImage();
    }
}