package org.example;

import java.awt.image.BufferedImage;

public interface ImageProcessor {
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException;
}
