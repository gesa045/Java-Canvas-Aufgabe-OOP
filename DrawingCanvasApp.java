import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel; // Für "Toolbar" über dem "Canvas"
import javax.swing.JButton; // Buttons
import javax.swing.JColorChooser; // für Farbauswahl
import javax.swing.JSlider; // Slider für Strichstärke
import javax.swing.JLabel; // Beschriftung für Strichstärke und Strichart
import javax.swing.JComboBox; // "DropDown Menu" für Strichart
import javax.swing.JOptionPane; // für Popup Fenster mit Hilfetext



/**
 * A basic Swing drawing application with a freehand drawing canvas 
 * and a toolbar with buttons.
 */
public class DrawingCanvasApp extends JFrame {

    /**
     * Die Zeichenfläche für alle Linien.
     */
    private final DrawingCanvas canvas;

    /**
     * Erstellt das Haiptfenster der Anwendung. 
     * Initialisierung der GUI-Elemente (toolbar, buttons, slider etc.)
     */
    public DrawingCanvasApp() {
        super("Drawing Canvas App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Canvas in der Mitte
        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);

        // Menü-Balken ("toolbar") oben für Steuerelemente
        JPanel toolbar = new JPanel();
        add(toolbar, BorderLayout.NORTH);
        toolbar.setBackground(Color.LIGHT_GRAY);

        // Button zum leeren der Zeichenfläche
        JButton clearButton = new JButton("alles löschen");
        toolbar.add(clearButton);
        clearButton.addActionListener(e -> {
            canvas.clear();
        });

        // Button für Farbauswahl
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
        JSlider strokeSlider = new JSlider(0, 20, 5);
        strokeSlider.setMajorTickSpacing(5); // Markierungen am Slider alle 5 Einheiten
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);
        
        toolbar.add(new JLabel("Strichstärke: "));
        toolbar.add(strokeSlider);
      
        strokeSlider.addChangeListener(e -> {
            int value = strokeSlider.getValue();
            canvas.setStrokeWidth(value);
        });

        // ComboBox (DropDown-Menü) für Strichart (durchgezogen / gestrichelt)
        JComboBox<String> styleBox = new JComboBox<>(
            new String[]{"durchgezogen", "gestrichelt"}
        );
        toolbar.add(new JLabel("Strichart: "));
        toolbar.add(styleBox);

        styleBox.addActionListener(e -> {
            String selected = (String) styleBox.getSelectedItem();
            canvas.setStrokeStyle(selected);
        });

        // Help-Button mit Popup Fenster für Erklärung der Funktionen
        JButton helpButton = new JButton("?");
        toolbar.add(helpButton);
        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Funktionen: \n\n" +
                "Zeichnen: linke Maustaste gedrückt halten und ziehen\n" +
                "alles löschen: Die komplette Leinwand wird geleert\n" +
                "Farbwahl: Wähle eine gewünschte Farbe für den nächsten Strich\n" +
                "Strichstärke: Ändere die Dicke des Striches (Dicke 1-20)\n" +
                "Strichart: Wähle zwischen durchgezogenem und gestricheltem Strich\n", 
                "Hilfe",
                JOptionPane.INFORMATION_MESSAGE // gibt dem Fenster den Name "Hilfe"
            );
        });


        // Setze minimale Fenstergröße und initiale Größe
        setMinimumSize(new Dimension(900, 650));
        setSize(900, 650);
    }

    /**
     * Startpunkt der Anwendung.
     *
     * @param args command-line arguments, not used
     */
    public static void main(String[] args) {
        DrawingCanvasApp app = new DrawingCanvasApp();
        app.setVisible(true);
    }
}