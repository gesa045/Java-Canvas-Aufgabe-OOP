import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * The drawing surface.
 */
public class DrawingCanvas extends JPanel {

    private final List<StrokeSegment> segments = new ArrayList<>();
    private StrokePath currentPath;

    /**
     * Creates the canvas and installs mouse handling.
     */
    public DrawingCanvas() {
        CanvasMouseListener mouseListener = new CanvasMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 560);
    }

    public void beginStroke(Point point) {
        currentPath = new StrokePath(Color.BLACK, 5);
        currentPath.addPoint(point);
        repaint();
    }

    public void extendStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        currentPath.addPoint(point);
        repaint();
    }

    public void endStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        currentPath.addPoint(point);
        segments.addAll(currentPath.toSegments());
        currentPath = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics.create();
        for (StrokeSegment segment : segments) {
            segment.paint(graphics2d);
        }

        if (currentPath != null) {
            currentPath.paint(graphics2d);
        }

        graphics2d.dispose();
    }
}

