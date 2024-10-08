package org.example;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageProcessor {
    void recolorImage(BufferedImage originalImage, BufferedImage resultImage) throws InterruptedException;

    List<RecolorPixels> divideImage(BufferedImage originalImage, BufferedImage resultImage, int numThreads);
}
