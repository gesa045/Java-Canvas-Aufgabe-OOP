import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Representiert ein einzelnes Segment eines Striches, bestehend aus zwei Punkten, einer Farbe, Strichstärke und Strichart.
 * Ein Strich besteht aus mehreren Segmenten, die zusammenhängend gezeichnet werden.
 */
public class StrokeSegment {
    /**
     * Die x- und y-Koordinaten der beiden Start- und Endpunkte des Segmentes.
     */
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    /** 
     * Farbe, Strichstärke und Strichart des Segmentes, die von StrokePath übergeben werden.
     */
    private final Color color;
    private final int width;
    private final String style; // neue Variable für Strichart

    /**
     * Konstruktor für StrokeSegment, mit allen visuellen Eigenschaften und Koordinaten.
     * @param x1 x-Koordinate des Startpunktes
     * @param y1 y-Koordinate des Startpunktes
     * @param x2 x-Koordinate des Endpunktes
     * @param y2 y-Koordinate des Endpunktes
     * @param color Strichfarbe
     * @param width Strichbreite
     * @param style Strichstyle (durchgezogen oder gestrichelt)
     */
    public StrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, String style) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.style = style; // Standard Strichart setzen
    }

    /**
     * Zeichnet dieses Segment auf der Zeichenfläche, unter Berücksichtigung der Farbe, Strichstärke und Strichart.
     * @param graphics2d grafischer Kontext zum Zeichnen
     */
    void paint(Graphics2D graphics2d) {
        Stroke previousStroke = graphics2d.getStroke();
        graphics2d.setColor(color);
         if ("gestrichelt".equals(style)) {
            graphics2d.setStroke(new BasicStroke(
                width, 
                BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_ROUND, 
                1.0f, 
                new float[]{10.0f, 20.0f},
                0.0f
            ));
        } else {
            graphics2d.setStroke(new BasicStroke(
                width, 
                BasicStroke.CAP_ROUND, 
                BasicStroke.JOIN_ROUND));
        }
        graphics2d.drawLine(x1, y1, x2, y2);
        graphics2d.setStroke(previousStroke);
    }
}

