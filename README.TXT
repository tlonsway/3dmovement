AUTHOR: Tristan Lonsway

TIME INITIATED: 3/17/18

DESCRIPTION:
3D engine using Matrix-Vector math and ZBuffering. Generates a small, random map with the aim of having somewhat hill like or natural features. Textures and shaders will not be used, only internal polygon/edge coloration. Translation, rotation, and projection matrices are implemented to move and display the objects. The engine is powered by the CPU, as there is no current CUDA implementation. No external 3D libraries are used - only awt and swing for placing the rasterized vectors and polygons on a display frame. A pixel buffer might be implemented to work with the ZBuffer as a way of reducing stress on graphics. Multithreading will likely be implement for mathematical sorting and transformations to speed up the program, though this may have a large effect on CPU utilization.
