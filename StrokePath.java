import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporärer Zeichenpfad während mit der Maus gezeichnet wird.
 * Speichert alle Punkte eines Striches, sowie die Farbe, Strichstärke und Strichart des Striches.
 * Sobald der Strich fertig ist, werden die Punkte in einzelne StrokeSegmente umgewandelt und gespeichert.
 */
public class StrokePath {
    /**
     * Liste aller Punkte, die während des Zeichnens eines Striches erfasst werden.
     */
    private final List<Point> points = new ArrayList<>();
    /**
     * Farbe, Strichstärke und Strichart des Striches, die beim Erstellen des StrokePath übergeben werden.
     */
    private final Color color;
    private final int width;
    private final String style; // neue Variable für Strichart

    /**
     * Konstruktor für StrokePath, der die Farbe, Strichstärke und Strichart des Striches festlegt.
     * @param color Strichfarbe
     * @param width Strichbreite
     * @param style Strichstyle (durchgezogen oder gestrichelt)
     */
    public StrokePath(Color color, int width, String style) {
        this.color = color;
        this.width = width;
        this.style = style; // Strichart setzen
    }

    /**
     * Fügt einen neuen Punkt zum Strich hinzu.
     * @param point neue Mausposition, die zum Strich hinzugefügt wird
     */
    public void addPoint(Point point) {
        points.add(point);
    }

    /**
     * Wandelt den Strich in eine Liste von StrokeSegementen um.
     * @return Liste der StrokeSegment-Objekte
     */
    public List<StrokeSegment> toSegments() {
        List<StrokeSegment> result = new ArrayList<>();
        for (int index = 1; index < points.size(); index++) {
            Point previous = points.get(index - 1);
            Point current = points.get(index);
            result.add(new StrokeSegment(
                previous.x, previous.y, 
                current.x, current.y, 
                color, 
                width,
                style
            ));
        }
        return result;
    }

    /**
     * Zeichen den aktuellen Strich auf der Zeichenfläche, während er gezeichnet wird.
     * @param graphics2d grafischer Kontext zum Zeichnen
     */
    public void paint(Graphics2D graphics2d) {
        for (StrokeSegment segment : toSegments()) {
            segment.paint(graphics2d);
        }
    }
}

