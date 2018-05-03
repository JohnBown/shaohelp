package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.bizimpl.MonadsBizImpl;
import com.etc.jump.daoimpl.ShaoMonadsDaoImpl;
import com.etc.jump.entity.Monads;
import com.etc.jump.entity.ShaoMonads;
import com.etc.jump.util.FilePath;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FillTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class mymonadsController implements Initializable{

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXTreeTableView<Mymonads> table1;

    @FXML
    private JFXTreeTableView<Myshaomonads> table2;

    MonadsBizImpl monadsBiz = new MonadsBizImpl();

    ShaoMonadsDaoImpl shaoMonadsDao = new ShaoMonadsDaoImpl();

    private void loadPage(Node node, String filepath,String title){
        try {
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filepath));
            Pane pane = fxmlLoader.load();

            stage.setScene(new Scene(pane));
            stage.setTitle(title);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class Mymonads extends RecursiveTreeObject<Mymonads>{//TODO 用于测试类
        StringProperty id;
        StringProperty ordertime;
        StringProperty status;

        Mymonads(String id, String ordertime, String status) {
            this.id = new SimpleStringProperty(id);
            this.ordertime =  new SimpleStringProperty(ordertime);
            this.status =  new SimpleStringProperty(status);
        }
    }

    class Myshaomonads extends RecursiveTreeObject<Myshaomonads>{//TODO 用于测试

        StringProperty id;
        StringProperty ordertime;
        StringProperty status;

        Myshaomonads(String id, String ordertime, String status) {
            this.id = new SimpleStringProperty(id);
            this.ordertime =  new SimpleStringProperty(ordertime);
            this.status =  new SimpleStringProperty(status);
        }
    }

    String getStatus(int i){
        if (i==0){
            return "未被接单";
        }else if (i==1){
            return "超时失效";
        }else if (i==2){
            return "正在完成";
        }else if (i==3){
            return "完成捎单";
        }else{
            return "取消";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //我的订单表

        JFXTreeTableColumn<Mymonads,String> id1 =new JFXTreeTableColumn<>("订单号");
        id1.setPrefWidth(140);
        id1.setCellValueFactory(param -> param.getValue().getValue().id);

        JFXTreeTableColumn<Mymonads,String> ordertime1 =new JFXTreeTableColumn<>("下单时间");
        ordertime1.setPrefWidth(200);
        ordertime1.setCellValueFactory(param -> param.getValue().getValue().ordertime);

        JFXTreeTableColumn<Mymonads,String> status1 =new JFXTreeTableColumn<>("状态");
        status1.setPrefWidth(100);
        status1.setCellValueFactory(param -> param.getValue().getValue().status);

        ObservableList<Mymonads> mymonads = FXCollections.observableArrayList();

        List<Monads> monads = monadsBiz.queryInfoByIdStatus(Main.user.getU_id());

        for (Monads mon:monads) {
            StringBuffer sb = new StringBuffer(""+mon.getM_order_datetime());
            sb.setCharAt(10,' ');

            mymonads.add(new Mymonads(""+mon.getM_id(),sb.toString(),getStatus(mon.getM_status())));
        }

        final TreeItem<Mymonads> root1 = new RecursiveTreeItem<>(mymonads, RecursiveTreeObject::getChildren);
        table1.getColumns().setAll(id1,ordertime1,status1);
        table1.setRoot(root1);
        table1.setShowRoot(false);

        //我的捎单表

        JFXTreeTableColumn<Myshaomonads,String> id2 =new JFXTreeTableColumn<>("订单号");
        id2.setPrefWidth(140);
        id2.setCellValueFactory(param -> param.getValue().getValue().id);

        JFXTreeTableColumn<Myshaomonads,String> ordertime2 =new JFXTreeTableColumn<>("接单时间");
        ordertime2.setPrefWidth(200);
        ordertime2.setCellValueFactory(param -> param.getValue().getValue().ordertime);

        JFXTreeTableColumn<Myshaomonads,String> status2 =new JFXTreeTableColumn<>("状态");
        status2.setPrefWidth(100);
        status2.setCellValueFactory(param -> param.getValue().getValue().status);

        ObservableList<Myshaomonads> myshaomonads = FXCollections.observableArrayList();

        List<ShaoMonads> shaomonads = shaoMonadsDao.infoByUid(Main.user.getU_id());
        Monads monads1=null;
        for (ShaoMonads shao:shaomonads) {
            StringBuffer sb = new StringBuffer(""+shao.getS_order_datetime());
            sb.setCharAt(10,' ');

            monads1 = monadsBiz.queryInfoById(shao.getS_monads_id());
            myshaomonads.add(new Myshaomonads(""+shao.getS_monads_id(),sb.toString(),getStatus(monads1.getM_status())));
        }

        final TreeItem<Myshaomonads> root2 = new RecursiveTreeItem<>(myshaomonads, RecursiveTreeObject::getChildren);
        table2.getColumns().setAll(id2,ordertime2,status2);
        table2.setRoot(root2);
        table2.setShowRoot(false);



        //导入左边框导航栏
        VBox vbox = null;
        try {
            vbox = FXMLLoader.load(getClass().getResource(FilePath.sidebarFXMLPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawer.setSidePane(vbox);

        //初始化“汉堡包”控件
        HamburgerBackArrowBasicTransition burger = new HamburgerBackArrowBasicTransition(hamburger);
        burger.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            burger.setRate(burger.getRate()*-1);
            burger.play();

            if(drawer.isShown()){
                drawer.close();
            }else {
                drawer.toggle();
            }
        });

        //监听左边框导航栏的鼠标事件
        for (Node node: Objects.requireNonNull(vbox).getChildren()){
            if(node.getAccessibleText()!=null){
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {

                    switch (node.getAccessibleText()){
                        case "home":
                            loadPage(node,FilePath.indexFXMLPath,"主页");
                            break;
                        case "order": {
                            loadPage(node,FilePath.orderFXMLPath,"下单");
                            break;
                        }
                        case "history":
                            loadPage(node,FilePath.mymonadsFXMLPath,"历史记录");
                            break;
                        case "info":
                            loadPage(node,FilePath.userinfoFXMLPath,"个人信息");
                            break;
                    }
                    drawer.close();
                    burger.setRate(-1);
                    burger.play();
                });
                node.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
                    FillTransition ft = new FillTransition(Duration.millis(200), (Shape) node, Color.WHITE, Color.valueOf("#34A853"));
                    ft.play();
                });
                node.addEventHandler(MouseEvent.MOUSE_EXITED,event -> {
                    FillTransition ft = new FillTransition(Duration.millis(200), (Shape) node, Color.valueOf("#34A853"), Color.WHITE);
                    ft.play();
                });
            }
        }

    }
} 
