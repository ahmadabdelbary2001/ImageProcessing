package org.example;

import java.awt.image.BufferedImage;

public class RecolorPixelsThread extends Thread {
    private final BufferedImage originalImage;
    private final BufferedImage resultImage;
    private final ImageProcessor imageProcessor;

    public RecolorPixelsThread(BufferedImage originalImage, BufferedImage resultImage, ImageProcessor imageProcessor) {
        this.originalImage = originalImage;
        this.resultImage = resultImage;
        this.imageProcessor = imageProcessor;
    }

    @Override
    public void run() {
        // System.out.println(Thread.currentThread().getName());
        try {
            imageProcessor.recolorImage(originalImage, resultImage);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}