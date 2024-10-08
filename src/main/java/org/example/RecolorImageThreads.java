package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecolorImageThreads {
    private final int numThreads;
    private BufferedImage originalImage;
    private BufferedImage resultImage;
    private final ImageProcessor imageProcessor;
    private final List<RecolorPixelsThread> threadList;

    public RecolorImageThreads(int numThreads, BufferedImage originalImage, BufferedImage resultImage, ImageProcessor imageProcessor) {
        this.numThreads = numThreads;
        this.originalImage = originalImage;
        this.resultImage = resultImage;
        this.imageProcessor = imageProcessor;
        this.threadList = new ArrayList<>();
    }

    public void setUpImages(String originalImagePath, String resultImagePath) throws Exception {
        File originalImageFile = new File(originalImagePath);
        File resultImageFile = new File(resultImagePath);
        if (!originalImageFile.exists()) {
            throw new IllegalArgumentException("Original image path does not exist: " + originalImagePath);
        }
        originalImage = ImageIO.read(originalImageFile);
        if (originalImage == null) {
            throw new IllegalArgumentException("Failed to load original image from path: " + originalImagePath);
        }
        resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        ImageIO.write(resultImage, "jpg", resultImageFile);
    }

    public void recolorImage() {
        List<RecolorPixels> tasks = imageProcessor.divideImage(originalImage, resultImage, numThreads);

        for (RecolorPixels task : tasks) {
            RecolorPixelsThread thread = new RecolorPixelsThread(originalImage, resultImage, imageProcessor) {
                @Override
                public void recolorImage(BufferedImage originalImage, BufferedImage resultImage) {
                    task.recolorImage();
                }

                @Override
                public List<RecolorPixels> divideImage(BufferedImage originalImage, BufferedImage resultImage, int numThreads) {
                    return null;
                }
            });
            threadList.add(thread);
        }

        RecolorPixelsThread thread = new RecolorPixelsThread(originalImage, resultImage, imageProcessor);
        threadList.add(thread);
        thread.start();

        for (RecolorPixelsThread thread : threadList) {
            thread.start();
        }

        for (RecolorPixelsThread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
