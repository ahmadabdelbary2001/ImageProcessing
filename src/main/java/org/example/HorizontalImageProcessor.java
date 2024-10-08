package org.example;

import java.awt.image.BufferedImage;

public class HorizontalImageProcessor  implements ImageProcessor {
    @Override
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        int width = originalImage.getWidth();
        int heightPerThread = originalImage.getHeight() / numberOfThreads;

        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                int yOrigin = heightPerThread * threadIndex;
                RecolorPixels recolorPixels = new RecolorPixels(originalImage, resultImage, 0, yOrigin, width, heightPerThread);
                recolorPixels.recolorImage();
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }
    }
}
