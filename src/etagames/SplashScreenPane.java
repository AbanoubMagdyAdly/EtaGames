package etagames;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public  class SplashScreenPane extends AnchorPane {

    protected final ImageView imageView;

    public SplashScreenPane() {

        imageView = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(590.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(538.0);
        imageView.setFitWidth(547.0);
        imageView.setLayoutX(24.0);
        imageView.setLayoutY(23.0);
        imageView.setImage(new Image(getClass().getResource("logo.png").toExternalForm()));

           getChildren().add(imageView);

    }

}
