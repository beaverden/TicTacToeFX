package com.TicTacToe;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {
	public Stage mainStage;
	public Scene mainScene;
	public Scene gameScene;

	public VBox mainBox;
	public Label playerLabel;
	public GridPane gridPane;
	public Text[][] letters;
	public Game game;

	public void initialize() {
		game = new Game();

		prepareLetters();
	}
	
	public void setStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void setMainSceneScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	public void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}

	public void prepareLetters() {

		letters = new Text[4][4];
		for (int i = 0; i <= 4; i += 2) {
			for (int j = 0; j <= 4; j += 2) {
				final int a = i / 2 + 1;
				final int b = j / 2 + 1;
				Text t = new Text();
				t.setWrappingWidth(135);
				t.getStyleClass().addAll("letter", "letterinactive");
				t.setId(Integer.toString(i) + Integer.toString(j));
				t.setOpacity(0);
				t.setText("X");
				GridPane.setRowIndex(t, i);
				GridPane.setColumnIndex(t, j);
				letters[a][b] = t;
				gridPane.getChildren().add(t);

				t.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (!game.isActivated(a, b)) {
							game.move(a, b);
							playerLabel.setText("Player " + game.getTurn() + " moves");
							game.redrawLetters(letters);
						}

					}

				});

				t.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (!game.isActivated(a, b)) {
							t.setText(game.getStringTurn());
							t.setOpacity(1);
						}
					}

				});
				
				t.setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (!game.isActivated(a, b)) {
							t.setText(game.getStringTurn());
							t.setOpacity(0);
						}

					}

				});
			}
		}
	}

	public void backToMenuPressed() {
		mainStage.setScene(mainScene);
	}
	
	public void undoPressed() {
		game.undo();
		game.redrawLetters(letters);
	}

}
