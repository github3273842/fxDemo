package com.test.fx.view.app.test1;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("/com/test/fx/icon/title.svg"));      //设置图标
//        primaryStage.setIconified(true);        //最小化
//        primaryStage.setMaximized(true);        //最大化
        //width,x,y  监听
        primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println(newValue.floatValue());
        });

//        primaryStage.setFullScreen(true);     //全屏
//        primaryStage.setOpacity(0.5);     //透明度
//        primaryStage.setAlwaysOnTop(true);

//        primaryStage.initStyle(StageStyle.UTILITY);       //风格

        Stage stage1 = new Stage();
//        stage1.initModality(Modality.APPLICATION_MODAL);      //模态

        Stage stage2 = new Stage();
        stage2.setTitle("2");

        stage1.initOwner(primaryStage);
        stage1.initModality(Modality.WINDOW_MODAL);     //相对窗口模态

        Platform.runLater(() -> {
            System.out.println(11111);
        });

//        Platform.setImplicitExit(false);        //窗口关闭但程序不终止
//        Platform.exit();            //关闭所有窗口
        Platform.isSupported(ConditionalFeature.SCENE3D);       //检查系统设备支持功能

        Screen screnn = Screen.getPrimary();
        Rectangle2D rec1 = screnn.getBounds();              //屏幕
        Rectangle2D rec2 = screnn.getVisualBounds();        //可视屏幕
        System.out.println(rec1.getWidth()+"--"+rec1.getHeight());
        System.out.println(rec2.getWidth()+"--"+rec2.getHeight());

//        HostServices host = getHostServices();          //打开网页
//        host.showDocument("www.baidu.com");


        Group group = new Group();      //容器
        group.getChildren().add(new Button("gb1"));
        group.setOpacity(0.6);

        //按钮
        Button bt1 = new Button("bt1");
        bt1.setLayoutX(100);        //设置位置
        bt1.setLayoutY(100);
        bt1.setPrefSize(200,100);       //设置宽高
        bt1.setFont(Font.font("sans=serif",40));
        bt1.setBackground(new Background(new BackgroundFill(Paint.valueOf("#888888"),new CornerRadii(20),new Insets(10))));             //边框
        bt1.setBorder(new Border(new BorderStroke(Paint.valueOf("#008888"),BorderStrokeStyle.DOTTED,new CornerRadii(20),new BorderWidths(10))));
        bt1.setTextFill(Paint.valueOf("#00ffff"));      //字体颜色
        bt1.setStyle("");     //样式设置
        //鼠标事件
        bt1.setOnAction((event) -> {
            System.out.println("bt1Click");
        });

        bt1.addEventHandler(MouseEvent.MOUSE_CLICKED,(event) -> {
            System.out.println(event.getButton().name());
            if(event.getClickCount() == 2 && event.getButton().name().equals(MouseButton.PRIMARY.name())){     //双击
                System.out.println(event.getClickCount()+"击");
            }

        });
        //键盘事件
        bt1.setOnKeyPressed((event) -> {
            System.out.println(event.getCode().name());
        });

        group.getChildren().add(bt1);
        boolean gFlag = group.contains(110,110);        //判断某点上是否右node
        System.out.println(gFlag);


        //        scene
//        stage   scene   node
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        //快捷键(S+SHIFT)
        KeyCombination kc = new KeyCodeCombination(KeyCode.S,KeyCombination.SHIFT_DOWN);
        scene.getAccelerators().put(kc,() -> {
            System.out.println("快捷键");
        });

        primaryStage.setTitle("fxDemo");
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        primaryStage.show();

//        stage1.show();
//        stage2.show();

    }
}
