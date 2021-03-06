package com.test.fx.view.app.test1;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("/com/test/fx/icon/title.svg"));      //设置图标
//        primaryStage.setIconified(true);        //最小化
//        primaryStage.setMaximized(true);        //最大化
        //width,x,y  监听窗口变化
        primaryStage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            System.out.println(newValue.floatValue());
        });

//        primaryStage.setFullScreen(true);     //全屏
//        primaryStage.setOpacity(0.5);     //透明度
//        primaryStage.setAlwaysOnTop(true);            //总是在最上层

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
        bt1.setBackground(new Background(new BackgroundFill(Paint.valueOf("#888888"),new CornerRadii(20),new Insets(10))));             //背景颜色
        bt1.setBorder(new Border(new BorderStroke(Paint.valueOf("#008888"),BorderStrokeStyle.DOTTED,new CornerRadii(20),new BorderWidths(10))));    //边框
        bt1.setTextFill(Paint.valueOf("#00ffff"));      //字体颜色
        bt1.setStyle("");     //样式设置
        //鼠标事件
        bt1.setOnAction((event) -> {        //点击事件
            System.out.println("bt1Click");
        });
        bt1.addEventHandler(MouseEvent.MOUSE_CLICKED,(event) -> {
            System.out.println(event.getButton().name());
            if(event.getClickCount() == 2 && event.getButton().name().equals(MouseButton.PRIMARY.name())){     //双击事件
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

        Label lb = new Label();
        group.getChildren().add(lb);
        lb.setText("我是标签");

        //输入框
        TextField tf = new TextField();
        group.getChildren().add(tf);
        tf.setLayoutX(100);
        tf.setFont(Font.font(14));
//        tf.setText("aaaa");
        tf.setTooltip(new Tooltip("请输入内容"));        //tip提示
        tf.setPromptText("请输入文字");      //提示
        Platform.runLater(() -> {
            tf.requestFocus();           //设置焦点
            tf.setFocusTraversable(false);
        });
        tf.textProperty().addListener((ob,oldValue,newValue) -> {       //改变事件
            System.out.println(oldValue +" -1 "+newValue);
        });
        tf.selectedTextProperty().addListener((ob,oldValue,newValue) -> {       //内容选中事件
            System.out.println(oldValue +" -2 "+newValue);
        });
        tf.addEventHandler(MouseEvent.MOUSE_ENTERED,(event) -> {
            tf.setFont(Font.font(20));
        });
        tf.addEventHandler(MouseEvent.MOUSE_EXITED,(event) -> {
            tf.setFont(Font.font(14));
        });

        //密码框
        PasswordField pfs = new PasswordField();
        group.getChildren().add(pfs);
        pfs.setLayoutX(300);


        //布局
        AnchorPane ap = new AnchorPane();
        Label l2 = new Label("AnchorPane");
        ap.getChildren().add(l2);
        ap.getChildren().add(group);
        ap.setPadding(new Insets(10));      //内边距

        AnchorPane ap2 = new AnchorPane();
        ap.getChildren().add(ap2);
        ap.setTopAnchor(ap2, 200.0);
        ap.setLeftAnchor(ap2, 100.0);
        ap.setRightAnchor(ap2, 100.0);
        ap.setBottomAnchor(ap2, 200.0);
        ap2.setBackground(new Background(new BackgroundFill(Paint.valueOf("#888888"),new CornerRadii(0),new Insets(0))));


        HBox hb = new HBox();           //水平布局
        hb.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ff0000"),new CornerRadii(0),new Insets(0))));
        hb.setPrefHeight(200);
        ap2.getChildren().add(hb);
        Label l3 = new Label("AnchorPane3");
        Label l4 = new Label("AnchorPane4");
        Label l5 = new Label("AnchorPane5");
        hb.getChildren().addAll(l3,l4,l5);
//        l4.setVisible(false);       //显示隐藏，但是占用空间
//        l4.setManaged(false);       //隐藏
        hb.setPadding(new Insets(10));      //内边距
        hb.setSpacing(10.0);            //间隔
        hb.setAlignment(Pos.CENTER);       //对齐方式

        BorderPane bp = new BorderPane();
        bp.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ff0000"),new CornerRadii(0),new Insets(0))));
        HBox hb2 = new HBox();           //水平布局
        hb2.setPrefHeight(100);
        hb2.setBackground(new Background(new BackgroundFill(Paint.valueOf("#00ff00"),new CornerRadii(0),new Insets(0))));
        bp.setBottom(hb2);
        HBox hb3 = new HBox();           //水平布局
        hb3.setBackground(new Background(new BackgroundFill(Paint.valueOf("#00ffff"),new CornerRadii(0),new Insets(0))));
        bp.setLeft(hb3);
        HBox hb4 = new HBox();           //水平布局
        hb4.setBackground(new Background(new BackgroundFill(Paint.valueOf("#0000ff"),new CornerRadii(0),new Insets(0))));
        bp.setRight(hb4);
        HBox hb5 = new HBox();           //水平布局
        hb5.setBackground(new Background(new BackgroundFill(Paint.valueOf("#ffffff"),new CornerRadii(0),new Insets(0))));
        bp.setCenter(hb5);


        FlowPane fp = new FlowPane();       //流式布局
        fp.setOrientation(Orientation.VERTICAL);        //设置垂直布局

        GridPane gp = new GridPane();       //网格布局
        gp.add(new Button("gpBt"),0,0);
        Button gpBt = new Button("gpBt2");
        gp.add(gpBt,1,1);
        gp.setHgap(10);     //垂直边距
        gp.setVgap(10);     //水平边距
//        gp.setRowSpan(new Button("gpBt2"),0);
        gp.setMargin(gpBt,new Insets(100));      //外边距

        StackPane sp = new StackPane();

        TextFlow tfp = new TextFlow();   //TextFlow布局
        Text tf1 = new Text("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        tf1.setFont(Font.font("Helvetica", FontWeight.BOLD,20));
        tf1.setFill(Paint.valueOf("#ff0000"));
        tf1.setLineSpacing(30);         //行边距
        tf1.setTextAlignment(TextAlignment.CENTER);       //text对齐方式
        tf1.setUnderline(true);     //下划线
        tf1.setCursor(Cursor.HAND);      //设置光标样式
        tf1.setTextOrigin(VPos.BOTTOM);     //设置位置方向
        tfp.getChildren().addAll(tf1);

        TilePane tp = new TilePane();       //瓦片布局（内容相同）


        //对话框
        DialogPane dp = new DialogPane();
        dp.setHeaderText("对话框");
        dp.getButtonTypes().add(ButtonType.CLOSE);  //关闭按钮
        Button close = (Button) dp.lookupButton(ButtonType.CLOSE);
        close.setOnAction((event) -> {
            System.out.println("close");
        });
        ImageView imv = new ImageView("/com/test/fx/icon/title.svg");       //图标
        dp.setGraphic(new Button("aaaa"));
        dp.setGraphic(imv);
        dp.setExpandableContent(new Text("扩展内容"));
        Stage dpStage = new Stage();
        dpStage.setAlwaysOnTop(true);
        Scene dpScene = new Scene(dp);
        dpStage.setScene(dpScene);
        dpStage.show();

        MySch mySch = new MySch(close);
        mySch.setDelay(Duration.millis(0));
        mySch.setPeriod(Duration.millis(1000));
        mySch.start();




        //        scene
//        stage   scene   node
        Scene scene = new Scene(tfp);
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
        primaryStage.setResizable(false);       //禁止拉伸

//        stage1.show();
//        stage2.show();


    }
}

//定时任务
class MySch<T> extends ScheduledService<T>{
    private  Node node;

    public MySch(Node node){
        this.node = node;
    }

    @Override
    protected Task<T> createTask() {
        return new Task<T>() {
            @Override
            protected T call() throws Exception {

                Button bt = new Button();
                T t = null;
                System.out.println("call----");
                return t;
            }

            @Override
            protected void updateValue(T value) {
                System.out.println("updateValue----");
                Double d = new Random().nextDouble();
                System.out.println("updateValue----"+d);
                node.setRotate(d*10);
            }
        };
    }
}
