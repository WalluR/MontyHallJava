package MonthyHallFx;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import MontyHall.MontyHallClass;

/**
 * Main controlleri Monty Hall pelille, Pelin todennäköisyys ei ehkä ihan oikea, Javan random luokka saattaa arpoa liian geneerisesti numeroita.
 * @author Wiljami
 * @version 17.12.2018
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("MonthyHallGUI.fxml"));
			final Pane root = (Pane)ldr.load();
			final MonthyHallController MontyCtrl = (MonthyHallController)ldr.getController();
			
			final Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Monthy Hall Game");
			
			primaryStage.show();
			MontyCtrl.main();
			MontyHallClass monty = new MontyHallClass();
			MontyCtrl.setMonty(monty);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
