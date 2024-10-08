import junit.framework.TestCase;
import org.example.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecolorerTest extends TestCase {

    private void processImageWithProcessor(ImageProcessor imageProcessor, int numberOfThreads) throws IOException, InterruptedException {
        final String SOURCE_FILE = "./resources/many-flowers.jpg";
        final String DESTINATION_FILE = "./out/many-flowers.jpg";

        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();
        imageProcessor.recolorImage(originalImage, resultImage, numberOfThreads);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println(String.valueOf(duration));
    }

    public void testBlockImageProcessor() throws IOException, InterruptedException {
        ImageProcessor imageProcessor = new BlockImageProcessor();
        processImageWithProcessor(imageProcessor, 4);
    }

    public void testHorizontalImageProcessor() throws IOException, InterruptedException {
        ImageProcessor imageProcessor = new HorizontalImageProcessor();
        processImageWithProcessor(imageProcessor, 4);
    }

    public void testVerticalImageProcessor() throws IOException, InterruptedException {
        ImageProcessor imageProcessor = new VerticalImageProcessor();
        processImageWithProcessor(imageProcessor, 4);
    }

    public void testImageProcessorSingleThreaded() throws IOException, InterruptedException {
        ImageProcessor imageProcessor = new ImageProcessorSingleThreaded();
        processImageWithProcessor(imageProcessor, 1);
    }
}
