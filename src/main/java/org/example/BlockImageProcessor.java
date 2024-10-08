package org.example;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BlockImageProcessor implements ImageProcessor {

    @Override
    public void recolorImage(BufferedImage originalImage, BufferedImage resultImage) {

    }

    @Override
    public List<RecolorPixels> divideImage(BufferedImage originalImage, BufferedImage resultImage, int numThreads) {
        int blockWidth = originalImage.getWidth() / numThreads;
        int blockHeight = originalImage.getHeight() / numThreads;

        List<RecolorPixels> tasks = new ArrayList<>();

        for (int x = 0; x < originalImage.getWidth(); x += blockWidth) {
            for (int y = 0; y < originalImage.getHeight(); y += blockHeight) {
                int width = Math.min(blockWidth, originalImage.getWidth() - x);
                int height = Math.min(blockHeight, originalImage.getHeight() - y);

                RecolorPixels task = new RecolorPixels(originalImage, resultImage, x, y, width, height);
                tasks.add(task);
            }
        }

        return tasks;
    }
}

