package IMC;

import javafx.application.Application; 
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {

	private TextField Peso;
	private TextField Altura;
	private StringProperty kg = new SimpleStringProperty ();
	private StringProperty cm = new SimpleStringProperty ();
	private DoubleProperty sum1 = new SimpleDoubleProperty(1);
	private DoubleProperty sum2 = new SimpleDoubleProperty(1);
	private Label result;
	private Label textResult;
	private DoubleProperty insult = new SimpleDoubleProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Peso = new TextField();
		Altura = new TextField();
		result = new Label();
		textResult = new Label();
		
		HBox hbox2 = new HBox();
		hbox2.setAlignment(Pos.CENTER);
		hbox2.getChildren().addAll(new Label("Peso: "), Peso, new Label(" Kg"));
		
	    HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
	    hbox.getChildren().addAll(new Label("Altura: "), Altura, new Label(" M"));
		

  		VBox root = new VBox(5);
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(hbox2, hbox, result, textResult);
		
		
		
		Scene scene = new Scene(root, 320, 200);
		

		
		Peso.textProperty().bindBidirectional(kg);
		Altura.textProperty().bindBidirectional(cm);
		kg.bindBidirectional(sum1, new NumberStringConverter());
		cm.bindBidirectional(sum2, new NumberStringConverter());
		
			
			primaryStage.setTitle("ICM");
			primaryStage.setScene(scene);
			primaryStage.show();
		
			result.textProperty().bind(Bindings.concat("IMC: ").concat((sum1.divide(
					(sum2.multiply(sum2).divide(10000))))));
			
			insult.bind(sum1.divide(
					(sum2.multiply(sum2).divide(10000))));
		
			StringExpression Finalresult = Bindings.concat(Bindings.when(insult.lessThan(18.5))
					.then("Bajo Peso")
					.otherwise("")).concat(Bindings.when(insult.lessThan(25)
							.and(insult.greaterThanOrEqualTo(18.5)))
							.then("Normal")
							.otherwise("")).concat(Bindings.when(insult.lessThan(35.0)
									.and(insult.greaterThanOrEqualTo(25.0)))
									.then("Sobrepeso")
									.otherwise("")).concat(Bindings.when(insult.greaterThanOrEqualTo(35))
											.then("Obeso")
											.otherwise(""));
			textResult.textProperty().bind(Finalresult);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		launch(args);
	}

}
