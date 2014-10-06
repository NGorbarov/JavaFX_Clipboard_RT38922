import java.awt.BorderLayout;
import java.awt.Dimension;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ClipboardBug extends JFrame {
	private final JTextField txt = new JTextField("Swing JTextField");
	private final JFXPanel   jfxPanel = new JFXPanel();

	public ClipboardBug() {
        super();
        
        Platform.runLater(new Runnable() {
            @Override 
            public void run() {
                TextField textField = new TextField("JavaFX input field");
                WebView view = new WebView();
                view.getEngine().loadContent("<html><body><input type=text value='HTML Text control'/></body></html>");
                VBox hb = new VBox();
                hb.getChildren().addAll(textField, view);
                jfxPanel.setScene(new Scene(hb));
            }
        });
  
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(txt, BorderLayout.NORTH);
        panel.add(jfxPanel, BorderLayout.CENTER);
        
        getContentPane().add(panel);
        
        setPreferredSize(new Dimension(1024, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

	public static void main(String[] args) {
		Platform.setImplicitExit(false);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
            	ClipboardBug test = new ClipboardBug();
                test.setVisible(true);
           }     
       });
	}

}
