package fxJava.CalculadoraCompleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application {
	private ComboBox<String> operadorCombo;
	private TextField nReal1;
	private TextField nImaginario1;
	private TextField nReal2;
	private TextField nImaginario2;
	private TextField nRealRes;
	private TextField nImaginarioRes;
	private Label sumaLabel;
	private Label iLabel;
	private Separator separador;
	NumComplejo complejo;
	NumComplejo complejo2;
	NumComplejo complejoRes;

	@Override
	public void start(Stage primaryStage) throws Exception {
		operadorCombo = new ComboBox<String>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");

		complejo = new NumComplejo();
		complejo2 = new NumComplejo();
		complejoRes = new NumComplejo();
		
		nReal1 = new TextField();
		nReal1.setPrefWidth(50);
		nReal1.setAlignment(Pos.CENTER);
		nReal1.textProperty().set("0");

		nReal2 = new TextField();
		nReal2.setPrefWidth(50);
		nReal2.setAlignment(Pos.CENTER);
		nReal2.textProperty().set("0");

		nRealRes = new TextField();
		nRealRes.setPrefWidth(50);
		nRealRes.setAlignment(Pos.CENTER);
		nRealRes.setDisable(true);

		nImaginario1 = new TextField();
		nImaginario1.setPrefWidth(50);
		nImaginario1.setAlignment(Pos.CENTER);
		nImaginario1.textProperty().set("0");

		nImaginario2 = new TextField();
		nImaginario2.setPrefWidth(50);
		nImaginario2.setAlignment(Pos.CENTER);
		nImaginario2.textProperty().set("0");

		nImaginarioRes = new TextField();
		nImaginarioRes.setPrefWidth(50);
		nImaginarioRes.setAlignment(Pos.CENTER);
		nImaginarioRes.setDisable(true);

		sumaLabel = new Label("+");

		separador = new Separator();
		separador.setOrientation(Orientation.HORIZONTAL);

		iLabel = new Label("i");

		VBox cajaOperador = new VBox();
		cajaOperador.setAlignment(Pos.CENTER);
		cajaOperador.setSpacing(5);
		cajaOperador.getChildren().addAll(operadorCombo);

		HBox cajaDatos1 = new HBox();
		cajaDatos1.setAlignment(Pos.CENTER);
		cajaDatos1.setSpacing(5);
		cajaDatos1.getChildren().addAll(nReal1, sumaLabel, nImaginario1, iLabel);

		HBox cajaDatos2 = new HBox();
		cajaDatos2.setAlignment(Pos.CENTER);
		cajaDatos2.setSpacing(5);
		cajaDatos2.getChildren().addAll(nReal2, new Label("+"), nImaginario2, new Label("i"));

		HBox cajaResultado = new HBox();
		cajaResultado.setAlignment(Pos.CENTER);
		cajaResultado.setSpacing(5);
		cajaResultado.getChildren().addAll(nRealRes, new Label("+"), nImaginarioRes, new Label("i"));

		VBox cajaPrincipal = new VBox();
		cajaPrincipal.setAlignment(Pos.CENTER);
		cajaPrincipal.setSpacing(5);
		cajaPrincipal.getChildren().addAll(cajaDatos1, cajaDatos2, separador, cajaResultado);

		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(5);
		root.getChildren().addAll(cajaOperador, cajaPrincipal);

		Scene scene = new Scene(root, 480, 320);

		primaryStage.setTitle("CalculadoraCompleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Bindings.bindBidirectional(nReal1.textProperty(), complejo.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(nImaginario1.textProperty(), complejo.imaginarioProperty(), new NumberStringConverter());
		
		Bindings.bindBidirectional(nReal2.textProperty(), complejo2.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(nImaginario2.textProperty(), complejo2.imaginarioProperty(), new NumberStringConverter());
		
		operadorCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (operadorCombo.getSelectionModel().getSelectedItem().equals("+")) {
				complejoRes = complejo.añadir(complejo2);
				nRealRes.textProperty().bind(complejoRes.realProperty().asString("%.2f"));
				nImaginarioRes.textProperty().bind(complejoRes.imaginarioProperty().asString("%.2f"));
			}

			if (operadorCombo.getSelectionModel().getSelectedItem().equals("-")) {
				complejoRes = complejo.restar(complejo2);
				nRealRes.textProperty().bind(complejoRes.realProperty().asString("%.2f"));
				nImaginarioRes.textProperty().bind(complejoRes.imaginarioProperty().asString("%.2f"));
			}

			if (operadorCombo.getSelectionModel().getSelectedItem().equals("*")) {
				complejoRes = complejo.multiplicar(complejo2);
				nRealRes.textProperty().bind(complejoRes.realProperty().asString("%.2f"));
				nImaginarioRes.textProperty().bind(complejoRes.imaginarioProperty().asString("%.2f"));
			}
			if (operadorCombo.getSelectionModel().getSelectedItem().equals("/")) {
				complejoRes = complejo.dividir(complejo2);
				nRealRes.textProperty().bind(complejoRes.realProperty().asString("%.2f"));
				nImaginarioRes.textProperty().bind(complejoRes.imaginarioProperty().asString("%.2f"));
			}
		});

	}

	public static void main(String[] args) {
		launch(args);

	}

}