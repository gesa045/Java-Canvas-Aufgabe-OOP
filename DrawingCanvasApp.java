import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JComboBox;



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

        // Button kann jetzt die clear Funktion des Canvas aufrufen
        JButton clearButton = new JButton("alles löschen");
        toolbar.add(clearButton);
        clearButton.addActionListener(e -> {
            canvas.clear();
        });

        // zweiter Button für Farbauswahl
        JButton colorButton = new JButton("Farbwahl");
        toolbar.add(colorButton);
        colorButton.addActionListener(e -> {
            // 	Erklärung für showDialog von docs.oracle.com: showDialog(Component component, String title, Color initialColor) Shows a modal color-chooser dialog and blocks until the dialog is hidden.
            Color newColor = JColorChooser.showDialog(
                this,
                "Wähle eine Farbe",
                Color.BLACK
            );
            if (newColor != null) {
                canvas.setStrokeColor(newColor);
            }
        });

        // Slider für Strichstärke
        JSlider strokeSlider = new JSlider(1, 20, 5);
        toolbar.add(new JLabel("Strichstärke: "));
        toolbar.add(strokeSlider);
        strokeSlider.addChangeListener(e -> {
            int value = strokeSlider.getValue();
            canvas.setStrokeWidth(value);
        });

        // ComboBox (DropDown-Menü) für Strichart
        JComboBox<String> styleBox = new JComboBox<>(
            new String[]{"durchgezogen", "gestrichelt"}
        );
        toolbar.add(new JLabel("Strichart: "));
        toolbar.add(styleBox);

        styleBox.addActionListener(e -> {
            String selected = (String) styleBox.getSelectedItem();
            canvas.setStrokeStyle(selected);
    });

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