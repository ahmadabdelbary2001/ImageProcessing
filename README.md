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

## Requirements
- JDK 11 or later
- Maven for dependency management
- JUnit 3 for running unit tests

## Observations
- The time difference between the Block Division and the Horizontal Division is very small, while the Vertical Division was slower than the previous approach.
- Because both block division and horizontal division make use of the cache locality by accessing the neighborhoods of pixels more effectively, they work faster than vertical division.

