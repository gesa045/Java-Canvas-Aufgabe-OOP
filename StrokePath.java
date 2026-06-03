import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporary stroke while the mouse is being dragged.
 */
public class StrokePath {
    private final List<Point> points = new ArrayList<>();
    private final Color color;
    private final int width;
    private final String style; // neue Variable für Strichart

    public StrokePath(Color color, int width, String style) {
        this.color = color;
        this.width = width;
        this.style = style; // Strichart setzen
    }

    public void addPoint(Point point) {
        points.add(point);
    }

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

    public void paint(Graphics2D graphics2d) {
        for (StrokeSegment segment : toSegments()) {
            segment.paint(graphics2d);
        }
    }
}

