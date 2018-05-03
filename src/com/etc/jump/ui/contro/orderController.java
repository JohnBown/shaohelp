package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.bizimpl.MonadsBizImpl;
import com.etc.jump.entity.Monads;
import com.etc.jump.util.FilePath;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FillTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class orderController implements Initializable{

    @FXML
    private StackPane stackpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXCheckBox chk0;

    @FXML
    private JFXCheckBox chk1;

    @FXML
    private JFXCheckBox chk2;

    @FXML
    private JFXTextField goodsinfo;

    @FXML
    private JFXDatePicker dateat;

    @FXML
    private JFXTimePicker timeat;

    @FXML
    private JFXTextField addressfrom;


    @FXML
    private JFXDatePicker dateto;

    @FXML
    private JFXTimePicker timefrom;

    @FXML
    private JFXTimePicker timeto;

    @FXML
    private JFXTextField addressto;

    @FXML
    private JFXComboBox<Long> chkminute;

    @FXML
    private JFXButton orderbtn;

    MonadsBizImpl monadsBiz = new MonadsBizImpl();
    Monads monads=null;


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


    private void resetUnFocusColor() {
        goodsinfo.setUnFocusColor(Color.rgb(161,161,161));
        addressfrom.setUnFocusColor(Color.rgb(161,161,161));
        addressto.setUnFocusColor(Color.rgb(161,161,161));
        chkminute.setUnFocusColor(Color.rgb(161,161,161));
    }

    private void setUnFocusColor() {
        if (goodsinfo.getText().equals("")){
            goodsinfo.setUnFocusColor(Color.rgb(255,0,0));
        }else if (addressfrom.getText().equals("")){
            addressfrom.setUnFocusColor(Color.rgb(255,0,0));
        }else if (addressto.getText().equals("")){
            addressto.setUnFocusColor(Color.rgb(255,0,0));
        }else if (chkminute.getValue()==null){
            chkminute.setUnFocusColor(Color.rgb(255,0,0));
        }
    }

    private void loadDialog(){
        JFXDialogLayout content = new JFXDialogLayout();
        Text bodytext = new Text("下单成功，跳转到历史记录查看订单？");
        content.setBody(bodytext);

        JFXDialog dialog = new JFXDialog(stackpane,content,JFXDialog.DialogTransition.CENTER);

        JFXButton button = new JFXButton("好的");
        button.setOnAction(event -> {
            dialog.close();
            loadPage(hamburger,FilePath.mymonadsFXMLPath,"历史记录");
        });


        content.setActions(button);
        dialog.setOverlayClose(false);
        dialog.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        chkminute.getItems().addAll(5L,10L,15L,20L,30L,60L,120L,180L,360L);

        chk0.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (chk0.isSelected()){
                chk1.setSelected(false);
                chk2.setSelected(false);
            }
            chk0.setSelected(true);
        });

        chk1.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (chk1.isSelected()){
                chk0.setSelected(false);
                chk2.setSelected(false);
            }
            chk1.setSelected(true);
        });

        chk2.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (chk2.isSelected()){
                chk0.setSelected(false);
                chk1.setSelected(false);
            }
            chk2.setSelected(true);
        });

        goodsinfo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
            }else {
                resetUnFocusColor();
            }
        });

        dateat.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                timeat.setDisable(true);
                dateto.setDisable(true);
                timefrom.setDisable(true);
                timeto.setDisable(true);

                timeat.setValue(null);
                dateto.setValue(null);
                timefrom.setValue(null);
                timeto.setValue(null);
            }else {
                if (dateat.getValue().isBefore(LocalDate.now())){//如果取单日期是过去式
                    new JFXSnackbar(stackpane).show("日期不合理",2000);
                    dateat.setValue(null);
                }else {
                    timeat.setDisable(false);
                    dateto.setDisable(false);
                }
                resetUnFocusColor();
            }
        });

        timeat.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                dateto.setDisable(true);
                timefrom.setDisable(true);
                timeto.setDisable(true);

                dateto.setValue(null);
                timefrom.setValue(null);
                timeto.setValue(null);
            }else {
                if (dateat.getValue().isEqual(LocalDate.now())
                        &&timeat.getValue().isBefore(LocalTime.now())) {//如果取单时间是过去式
                    new JFXSnackbar(stackpane).show("时间不合理", 2000);
                    timeat.setValue(null);
                }else {
                    dateto.setDisable(false);
                }
                resetUnFocusColor();
            }
        });

        addressfrom.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
            }else {
                resetUnFocusColor();
            }
        });

        dateto.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                timefrom.setDisable(true);
                timeto.setDisable(true);

                timefrom.setValue(null);
                timeto.setValue(null);
            }else {
                try {
                    if (dateto.getValue().isBefore(dateat.getValue())) {//如果送单日期相对取单日期是过去式
                        new JFXSnackbar(stackpane).show("日期不合理", 2000);
                        dateto.setValue(null);
                    } else {
                        timefrom.setDisable(false);
                    }
                    resetUnFocusColor();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        timefrom.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                timeto.setDisable(true);

                timeto.setValue(null);
            }else {
                try {
                    if (dateto.getValue().isEqual(dateat.getValue())
                            && timefrom.getValue().isBefore(timeat.getValue())) {
                        new JFXSnackbar(stackpane).show("时间不合理", 2000);
                        timefrom.setValue(null);
                    } else {
                        timeto.setDisable(false);
                    }
                    resetUnFocusColor();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        timeto.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
            }else {
                try {
                    if (timeto.getValue().isBefore(timefrom.getValue())) {
                        new JFXSnackbar(stackpane).show("时间不合理", 2000);
                        timeto.setValue(null);
                    }
                    resetUnFocusColor();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        addressto.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
            }else {
                resetUnFocusColor();
            }
        });

        chkminute.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
            }else {
                resetUnFocusColor();
            }
        });

        orderbtn.setOnAction(event -> {
            boolean b = false;
            if (goodsinfo.getText().equals("")
                    ||addressfrom.getText().equals("")
                    ||addressto.getText().equals("")
                    ||chkminute.getValue()==null){
                setUnFocusColor();
            }else {
                if (dateat.getValue()==null
                        ||timeat.getValue()==null
                        ||dateto.getValue()==null
                        ||timefrom.getValue()==null
                        ||timeto.getValue()==null){
                    new JFXSnackbar(stackpane).show("请检查日期时间",2000);
                }else {//下单成功
                    monads = new Monads();

                    monads.setM_id(1000000000);
                    monads.setM_uid(Main.user.getU_id());
                    if (chk0.isSelected()){
                        monads.setM_goods(0);
                    }else if (chk1.isSelected()){
                        monads.setM_goods(1);
                    }else if (chk2.isSelected()){
                        monads.setM_goods(2);
                    }
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    monads.setM_goodinfo(goodsinfo.getText());
                    monads.setM_order_datetime(LocalDateTime.now());
                    monads.setM_time_at(LocalDateTime.parse(dateat.getValue()+" "+timeat.getValue()+":00",dateTimeFormatter));
                    monads.setM_address_from(addressfrom.getText());
                    monads.setM_time_from(LocalDateTime.parse(dateto.getValue()+" "+timefrom.getValue()+":00",dateTimeFormatter));
                    monads.setM_time_to(LocalDateTime.parse(dateto.getValue()+" "+timeto.getValue()+":00",dateTimeFormatter));
                    monads.setM_address_to(addressto.getText());
                    StringBuffer sb = new StringBuffer(LocalDateTime.now().plusMinutes(chkminute.getValue()).toString());
                    sb.setCharAt(10,' ');
                    sb.delete(19,23);
                    monads.setM_failure_time(LocalDateTime.parse(sb.toString(),dateTimeFormatter));
                    monads.setM_status(0);

                    monadsBiz.addOrd(monads);
                    loadDialog();
                }
            }
        });


    }
} 
