package ssh.io;

import com.jcraft.jsch.JSchException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ssh.io.SlogDownloadFile;

public class SlogDownloadFX extends Application {
	
	private static final String PATH_REMOTE = "/data/wiseguy/log/";
	private static final String PATH_LOCAL = "C:\\Users\\wlopes\\IdeaProjects\\slog\\src\\main\\resources\\input\\";
	

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		stage.setTitle("Download log for SLOG");
		Scene scene = new Scene(new Group(), 450, 250);

		ComboBox comboBox = new ComboBox();
		comboBox.getItems().addAll("uatdev", "uatprod", "prod");
		
		TextField textField = new TextField();
		textField.setMinWidth(250);
	
		comboBox.setOnAction((event) -> {
		    int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
		    Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
		});
		
		Button button = new Button("Download");
        button.setOnAction(value ->  {
        	
        	String ENVIRONMENT = (String) comboBox.getValue();
        	String NAME_FILE = textField.getText();
        	
        	
		    String host= "ny2-lia-001."+ENVIRONMENT+".tradingscreen.com";
		    String remote = PATH_REMOTE + ENVIRONMENT + "/" + NAME_FILE;
			String local = PATH_LOCAL + NAME_FILE;

			System.out.println("ENVIRONMENT: " + ENVIRONMENT);
			System.out.println("Log file: " + NAME_FILE);
			System.out.println("Host: " + host);

			System.out.println("Remote Path: " + remote);
			System.out.println("Local Path: " + remote);
			
			try {
				SlogDownloadFile.downloadFile(host, remote, local);
			} catch (JSchException e) {
				e.printStackTrace();
			}
			
        });

		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setHgap(20);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.add(new Label("Environment: "), 0, 0);
		grid.add(comboBox, 1, 0);
		grid.add(new Label("Log File: "), 0, 1);
		grid.add(textField, 1, 1);
		grid.add(button, 1, 2);

		Group root = (Group) scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();

	}
}