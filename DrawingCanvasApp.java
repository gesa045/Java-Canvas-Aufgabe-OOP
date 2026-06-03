import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * A basic Swing drawing application with only a freehand drawing canvas.
 */
public class DrawingCanvasApp extends JFrame {

    private final DrawingCanvas canvas;

    /**
     * Creates the application window.
     */
    public DrawingCanvasApp() {
        super("Drawing Canvas App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new DrawingCanvas();

        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        setMinimumSize(new Dimension(900, 650));
        setSize(900, 650);
    }

    /**
     * Program entry point.
     *
     * @param args command-line arguments, not used
     */
    public static void main(String[] args) {
        DrawingCanvasApp app = new DrawingCanvasApp();
        app.setVisible(true);
    }
}