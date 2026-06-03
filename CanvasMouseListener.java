import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Handles mouse input for freehand drawing.
 */
public class CanvasMouseListener extends MouseAdapter {

    private final DrawingCanvas canvas;

    public CanvasMouseListener(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        canvas.beginStroke(event.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        canvas.extendStroke(event.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        canvas.endStroke(event.getPoint());
    }
}

