import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


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

        setLayout(new BorderLayout());

        // Canvas in der Mitte
        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);


        // MenuBalken oben für zukünftige Buttons
        JPanel toolbar = new JPanel();
        add(toolbar, BorderLayout.NORTH);
        toolbar.setBackground(Color.LIGHT_GRAY);

        // erster Button zum Testen
        JButton ClearButton = new JButton("Test Button");
        toolbar.add(ClearButton);


        // Setze minimale Fenstergröße und initiale Größe
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