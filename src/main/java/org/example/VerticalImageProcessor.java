package org.example;

import java.awt.image.BufferedImage;

public class VerticalImageProcessor implements ImageProcessor {
    @Override
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        int widthPerThread = originalImage.getWidth() / numberOfThreads;
        int height = originalImage.getHeight();

        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                int xOrigin = widthPerThread * threadIndex;
                RecolorPixels recolorPixels = new RecolorPixels(originalImage, resultImage, xOrigin, 0, widthPerThread, height);
                recolorPixels.recolorImage();
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
                thread.join();
        }
    }
}
