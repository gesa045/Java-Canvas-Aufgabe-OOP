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

    /** 
     * Gibt die bevorzugte Größe der Zeichenfläche zurück.
     * @return die bevorzugte Größe der Zeichenfläche
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 560);
    }

    /**
     * Beginnt einen neuen Strich mit dem gegebenen Punkt als Startpunkt.
     * @param point Stratpunkt des Striches
     */
    public void beginStroke(Point point) {
        currentPath = new StrokePath(strokeColor, strokeWidth, strokeStyle);
        currentPath.addPoint(point);
        repaint();
    }

    /**
     * Verlängert den aktuellen Strich um neues Segment
     * @param point neue Mausposition
     */
    public void extendStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        currentPath.addPoint(point);
        repaint();
    }

    /**
     * Beendet den aktuellen Strich und fügt ihn den bestehenden Segmenten hinzu.
     * @param point Endpunkt des Striches
     */
    public void endStroke(Point point) {
        if (currentPath == null) {
            return;
        }

        currentPath.addPoint(point);
        segments.addAll(currentPath.toSegments());
        currentPath = null;
        repaint();
    }

    /**
     * Leert die Zeichenfläche.
     */
    public void clear() {
        segments.clear();
        currentPath = null;
        repaint();
    }

    /**
     * Setter für Strichfarbe
     * @param color neue Farbe für den nächsten Strich
     */
    public void setStrokeColor(Color color) {
        this.strokeColor = color;
    }

    /**
     * Setter für Strichstärke
     * @param width neue Strichstärke für den nächsten Strich
     */
    public void setStrokeWidth(int width) {
        this.strokeWidth = width;
    }

    /**
     * Setter für Strichart
     * @param style neue Strichart für den nächsten Strich
     */
    public void setStrokeStyle(String style) {
        this.strokeStyle = style;
    }


    /**
     * Zeichnet alle gespeicherten und aktuellen Striche auf der Zeichenfläche.
     * @param graphics das Graphics-Objekt, auf dem gezeichnet wird (grafischer Kontext zum zeichnen)
     */
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

