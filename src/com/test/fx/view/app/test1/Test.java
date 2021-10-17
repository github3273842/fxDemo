package com.test.fx.view.app.test1;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("/com/test/fx/icon/title.svg"));
//        primaryStage.setIconified(true);        //最小化
//        primaryStage.setMaximized(true);        //最大化
        //width,x,y
        primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println(newValue.floatValue());
        });
        //quanping
//        primaryStage.setFullScreen(true);

        //
//        primaryStage.setOpacity(0.5);
        //
//        primaryStage.setAlwaysOnTop(true);

//        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(new Group()));



        Stage stage1 = new Stage();
        stage1.setTitle("1");
//        stage1.initModality(Modality.APPLICATION_MODAL);

        Stage stage2 = new Stage();
        stage2.setTitle("2");


        stage1.initOwner(primaryStage);
        stage1.initModality(Modality.WINDOW_MODAL);

        primaryStage.setTitle("fxDemo");
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.show();

        stage1.show();
        stage2.show();
    }
}
