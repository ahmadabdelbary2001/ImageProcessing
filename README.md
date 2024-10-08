# Image Recoloring

## Description

The project introduces a multithread approach to image recoloring using Java. This application has provided three different strategies for dividing work among threads other than the usual one, that is, No Division (Single Thread):

1. **Horizontal Division**

This is one technique of horizontally dividing the image into strips; each is processed differently by a thread. It would do well for general image processing, but if it contains lots of vertical or diagonal designs, it might be problematic since it operates on rows of an image independently and may or may not account for continuity across columns.

2. **Vertical Division**

It is done by dividing the whole image into vertical slices. That is very handy in cases when horizontal continuity has to be kept up, but it may miss those fine details that stretch both horizontally and vertically, such as a diagonal pattern or edges whose course runs through both directions.

3. **Block Division**

The picture is subdivided into small rectangular blocks, each block dealt with by a different thread. This is a very efficient approach in applications where high accuracy at the edges and other localized details is highly crucial, such as in edge detection. Operating in compact areas provides more context to each thread in the processing of pixels; hence, it is better in results for both horizontal and vertical boundaries.

4. **No Division (Single Thread)**

This strategy consists of only one thread dealing with the whole image, although it is very simple and easy to implement. It is the slowest in big images since it does not use parallel processing that may distribute workload among threads. Actually, it is identical to "Block Division" in edge detection.edge detection or when dealing with regions of interest that are scattered across the image.

## Getting Started

### Requirements
- JDK 11 or later
- Maven for dependency management
- JUnit 3 for running unit tests


### Testing

The project includes unit tests for each recoloring strategy:
- **HorizontalRecolorerTest**
- **VerticalRecolorerTest**
- **BlockRecolorerTest**

You can run these tests using Maven:
```
mvn test
```

### Recolor Strategies

#### 1. Horizontal Division
This strategy splits the image into horizontal stripes, each processed by a different thread. It works well for most general image processing tasks but can miss certain nuances in images, especially those that involve vertical or diagonal patterns.

#### 2. Vertical Division
This strategy divides the image into vertical slices. It is useful when horizontal continuity in the image is more important to the processing task, but it might not handle edges that span both horizontally and vertically very well.

#### 3. Block Division
In the block division approach, the image is split into smaller rectangular blocks, where each block is processed by a different thread. This method is ideal for tasks where precision around edges or specific regions is crucial. Since each thread works on a compact section of the image, the block division helps better process edges or fine details that span across both horizontal and vertical directions.

For instance, in image processing tasks such as **edge detection**, where the contours of objects are important, dividing the image into blocks ensures that the threads can process the entirety of a localized area, including both horizontal and vertical boundaries of edges. This gives the threads more context when processing pixels around the edges, improving the quality of the result.

## When Block Division is Better (Regardless of Image processing)

Apart from image processing, block division can be more efficient in other tasks that involve grid-based data, such as **matrix multiplication**.

In matrix multiplication, if we divide the work into smaller blocks, each thread can process a square block of the resulting matrix independently. This allows better handling of both row and column operations simultaneously, reducing the need for frequent thread synchronization and improving parallelization efficiency. This block approach enables threads to work on smaller sections of the matrix, reducing the complexity of managing shared data, and leading to better performance in distributed and parallel computing tasks.

