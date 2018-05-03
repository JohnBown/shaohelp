package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.bizimpl.MonadsBizImpl;
import com.etc.jump.bizimpl.UserBizImpl;
import com.etc.jump.entity.Monads;
import com.etc.jump.entity.Users;
import com.etc.jump.util.FilePath;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
//import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class indexController implements Initializable{

    @FXML
    private AnchorPane indexPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    MonadsBizImpl monadsBiz = new MonadsBizImpl();
    List<Monads> monads=null;

    UserBizImpl userBiz = new UserBizImpl();


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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        monads = monadsBiz.queryAllUndone();

        for (Monads monads:monads){

            if (monads.getM_failure_time().isBefore(LocalDateTime.now())){
                monads.setM_status(1);
                monadsBiz.updateStatusById(monads.getM_id(),1);
                continue;
            }

            if (monads.getM_uid()== Main.user.getU_id()){
                continue;
            }

            int id = monads.getM_id();
            int uid = monads.getM_uid();
            Users users = userBiz.queryInfoByUid(uid);
            String uname = users.getU_name();
            String ucall = users.getU_phone();
            int goods = monads.getM_goods();
            String goodsinfo = monads.getM_goodinfo();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime orderdatetime = monads.getM_order_datetime();
            LocalDateTime timeat = monads.getM_time_at();
            String addressfrom = monads.getM_address_from();
            LocalDateTime timefrom = monads.getM_time_from();
            LocalDateTime timeto = monads.getM_time_to();
            String addressto = monads.getM_address_to();
            LocalDateTime timefailure = monads.getM_failure_time();
            int status = monads.getM_status();

            AnchorPane accordPane = null;
            try {
                accordPane = FXMLLoader.load(getClass().getResource(FilePath.indexaccordionFXMLPath));

                //定位TitlePane中的Node进行修改
                TitledPane tp = (TitledPane) accordPane.getChildren().get(0);
                AnchorPane ap = (AnchorPane) tp.getContent();
                for (Node node:ap.getChildren()){
                    if (node instanceof HBox){
                        ((Label)((HBox) node).getChildren().get(1)).setText(""+id);
                    }else if (node.getId()!=null){
                        if (node.getId().equals("addressfrom")){
                            ((Label)node).setText(addressfrom);
                        }else if(node.getId().equals("timeat")){
                            StringBuffer sb = new StringBuffer(timeat.toString());
                            sb.setCharAt(10,' ');
                            ((Label)node).setText(sb.toString());
                        }else if (node.getId().equals("addressto")){
                            ((Label)node).setText(addressto);
                        }else if (node.getId().equals("timefromto")){
                            StringBuffer sbfrom = new StringBuffer(timefrom.toString());
                            StringBuffer sbto = new StringBuffer(timeto.toString());
                            StringBuffer sb = new StringBuffer(sbfrom.substring(0));
                            sb.append(' ');
                            sb.append(sbto.substring(11));
                            sb.setCharAt(10,' ');
                            sb.setCharAt(16,'-');
                            ((Label)node).setText(sb.toString());
                        }else if (node.getId().equals("callnum")){
                            StringBuffer sb = new StringBuffer(ucall);
                            sb.setCharAt(4,'*');
                            sb.setCharAt(5,'*');
                            sb.setCharAt(6,'*');
                            sb.setCharAt(7,'*');

                            ((Label)node).setText(sb.toString());
                        }else if (node.getId().equals("name")){
                            ((Label)node).setText(users.getU_name());
                        }
                    }
                }

                //定位AnchorPane中Node进行修改
                ap = (AnchorPane) accordPane.getChildren().get(1);
                for (Node node: ap.getChildren()){
                    if (node instanceof FontAwesomeIcon){
                        if (node.getId()!=null) {
                            if (node.getId().equals("goods")) {
                                if (goods == 0) {//外卖
                                    ((FontAwesomeIcon) node).setIconName("MOTORCYCLE");
                                } else if (goods == 1) {//快递
                                    ((FontAwesomeIcon) node).setIconName("TRUCK");
                                } else if (goods == 2) {//其他
                                    ((FontAwesomeIcon) node).setIconName("CODEPEN");
                                }
                            }
                        }
                    }else if (node instanceof HBox){
                        java.time.Duration duration = java.time.Duration.between(LocalDateTime.now(),timefailure);
                        ((Label)((HBox) node).getChildren().get(1)).setText(""+duration.toMinutes());
                    }else if (node instanceof VBox){
                        StringBuffer sb = new StringBuffer(timeat.toString());
                        ((Label)((VBox) node).getChildren().get(0)).setText(sb.substring(11,16));
                        ((Label)((VBox) node).getChildren().get(1)).setText(sb.substring(0,10));
                    } else if(node.getId()!=null) {
                        if (node.getId().equals("goodsinfo")) {
                            ((Label) node).setText(goodsinfo);
                        }if (node.getId().equals("address")){
                            ((Label) node).setText(addressfrom);
                        }
                    }
                }

                vBox.getChildren().add(accordPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        scrollPane.setContent(vBox);
        JFXScrollPane.smoothScrolling(scrollPane);


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
                    FillTransition ft = new FillTransition( Duration.millis(200), (Shape) node, Color.WHITE, Color.valueOf("#34A853"));
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
