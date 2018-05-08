public class FrameLimiterThread implements Runnable {
    drawingPlane plane;
    int fps = 30;
    public FrameLimiterThread(drawingPlane frame) {
        plane=frame;
    }
    public FrameLimiterThread(drawingPlane frame, int frames) {
        plane=frame;
        fps=frames;
    }
    public void run() {
        while (true) {
            plane.redraw();
            try {
                Thread.sleep(1000/fps);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}