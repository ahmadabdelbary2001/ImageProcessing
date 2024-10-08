package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        final String SOURCE_FILE = "./resources/many-flowers.jpg";
        final String DESTINATION_FILE = "./out/many-flowers.jpg";

        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();
        int numberOfThreads = 4;
        //ImageProcessor imageProcessor = new ImageProcessorSingleThreaded();
        //imageProcessor.recolorImage(originalImage, resultImage, numberOfThreads);

        ImageProcessor imageProcessor = new BlockImageProcessor();
        imageProcessor.recolorImage(originalImage, resultImage, numberOfThreads);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println(String.valueOf(duration));
    }
}
