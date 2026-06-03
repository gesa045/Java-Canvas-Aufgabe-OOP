import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * Zeichenfläche
 * Verwaltet die Striche, die gezeichnet werden, und reagiert auf Maus-Eingaben.
 */
public class DrawingCanvas extends JPanel {

    /** 
     * Liste aller Striche, die dauerhaft angezeigt werden sollen. 
     * Jeder Strich hat mehrere Segmente, die in der Klasse StrokeSegment dargestellt werden.
     */
    private final List<StrokeSegment> segments = new ArrayList<>();
    /** 
     * Aktueller Strich, der gerade gezeichnet wird, aber noch nicht in segments aufgenommen wurde.
     */
    private StrokePath currentPath;
    /**
     * Aktuelle Farbe, Strichstärke und Strichart für neue Striche.
     */
    private Color strokeColor = Color.BLACK;
    private int strokeWidth = 5;
    private String strokeStyle = "durchgezogen";

    /**
     * Erstellt die Zeichenfläche und den MouseListener für das freie 
     * Zeichnen mit der Maus.
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
        currentPath = new StrokePath(strokeColor, strokeWidth, strokeStyle);
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

    // Funktion hinzufügen um Canvas zu leeren
    public void clear() {
        segments.clear();
        currentPath = null;
        repaint();
    }

    // Setter für farbauswahl
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    // Setter für Strichstärke
    public void setStrokeWidth(int width) {
        this.strokeWidth = width;
    }

    // Setter für Strichart
    public void setStrokeStyle(String style) {
        this.strokeStyle = style;
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

