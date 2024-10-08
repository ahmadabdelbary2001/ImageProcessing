package org.example;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BlockImageProcessor implements ImageProcessor {

    @Override
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        int blockSize = (int) Math.sqrt(numberOfThreads);

        int width = originalImage.getWidth() / blockSize;
        int height = originalImage.getHeight() / blockSize;

        Thread[] threads = new Thread[numberOfThreads];

        int threadIndex = 0;

        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                final int xOrigin = width * i;
                final int yOrigin = height * j;

                Thread thread = new Thread(() -> {
                    RecolorPixels recolorPixels = new RecolorPixels(originalImage, resultImage, xOrigin, yOrigin, width, height);
                    recolorPixels.recolorImage();
                });

                thread.start();

                threads[threadIndex] = thread;
                threadIndex++;
            }
        }

        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }
    }
}

