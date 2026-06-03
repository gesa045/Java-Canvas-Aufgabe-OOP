import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Represents one line segment of a stroke.
 */
public class StrokeSegment {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;
    private final Color color;
    private final int width;
    private final String style; // neue Variable für Strichart

    public StrokeSegment(int x1, int y1, int x2, int y2, Color color, int width, String style) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.style = style; // Standard Strichart setzen
    }

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

