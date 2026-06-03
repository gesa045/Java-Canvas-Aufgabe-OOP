import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Verarveitet Maus-Eingaben.
 * reagiert auf drücken, bewegen und loslassen der Maus.
 * Ereignisse werden an DrawingCanvas weitergegeben.
 */
public class CanvasMouseListener extends MouseAdapter {

    /**
     * Referenz auf Zeichenfläche
     */
    private final DrawingCanvas canvas;

    /**
     * Erstellung MouseListener und Verbindung zur Zeichenfläche.
     * @param canvas die Zeichenfläche, auf der gezeichnet wird
     */
    public CanvasMouseListener(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    /** 
     * Beginnt einen neuen Strich, wenn die Maus gedrückt wird.
     * * @param event Mausereignis mit aktueller Position der Maus
     */
    @Override
    public void mousePressed(MouseEvent event) {
        canvas.beginStroke(event.getPoint());
    }

    /**
     * Erweitert den aktuellen Strich, wenn die Maus gleichzeitig gedrückt und bewegt wird.
     * @param event Mausereignis mit aktueller Position der Maus
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        canvas.extendStroke(event.getPoint());
    }

    /**
     * Beendet den aktuellen Strich, wenn die Maus losgelassen wird.
     * @param event Mausereignis mit aktueller Position der Maus
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        canvas.endStroke(event.getPoint());
    }
}

