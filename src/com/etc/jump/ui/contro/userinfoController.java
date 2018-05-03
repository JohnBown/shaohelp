package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.biz.CityBiz;
import com.etc.jump.biz.UserBiz;
import com.etc.jump.bizimpl.CityBizImpl;
import com.etc.jump.bizimpl.UserBizImpl;
import com.etc.jump.entity.City;
import com.etc.jump.entity.Users;
import com.etc.jump.util.FilePath;
import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FillTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class userinfoController implements Initializable{

    @FXML
    private AnchorPane userinfoPane;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXButton editpwd;

    @FXML
    private JFXPasswordField pwd;

    @FXML
    private JFXPasswordField pwdagain;

    @FXML
    private TitledPane titlePane;

    @FXML
    private JFXButton editbtn;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXComboBox<String> comb1;

    @FXML
    private JFXComboBox<String> comb2;

    @FXML
    private JFXComboBox<String> comb3;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField school;

    @FXML
    private JFXTextField phonecall;


    CityBiz citybizimpl = new CityBizImpl();
    ArrayList<String> province;
    ArrayList<String> city;
    ArrayList<String> district;

    UserBiz userBiz = new UserBizImpl();

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

    private void unEditable(){//设置不可编辑
        username.setEditable(false);
        comb1.setDisable(true);
        comb2.setDisable(true);
        comb3.setDisable(true);
        address.setEditable(false);
        school.setEditable(false);
        phonecall.setEditable(false);
    }


    private void Editable(){//设置可编辑
        username.setEditable(true);
        comb1.setDisable(false);
        comb2.setDisable(false);
        comb3.setDisable(false);
        address.setEditable(true);
        school.setEditable(true);
        phonecall.setEditable(true);
    }


    private void resetUnFocusColor() {
        username.setUnFocusColor(Color.rgb(161, 161, 161));
        comb1.setUnFocusColor(Color.rgb(161, 161, 161));
        comb2.setUnFocusColor(Color.rgb(161, 161, 161));
        comb3.setUnFocusColor(Color.rgb(161, 161, 161));
        address.setUnFocusColor(Color.rgb(161, 161, 161));
        school.setUnFocusColor(Color.rgb(161, 161, 161));
        phonecall.setUnFocusColor(Color.rgb(161, 161, 161));
    }

    private void setUnFocusColor() {
        if (username.getText().equals("")) {
            username.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (comb1.getValue() == null) {
            comb1.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (comb2.getValue() == null) {
            comb2.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (comb3.getValue() == null) {
            comb3.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (address.getText().equals("")) {
            address.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (school.getText().equals("")) {
            school.setUnFocusColor(Color.rgb(255, 0, 0));
        } else if (phonecall.getText().equals("")) {
            phonecall.setUnFocusColor(Color.rgb(255, 0, 0));
        }
    }

    public void editbtnClicked(){

        if (editbtn.getText().equals("编辑")){
            comb3.setPromptText("所在区");
            comb2.setPromptText("所在市");
            comb1.setPromptText("所在省");
            Editable();
            editbtn.setText("保存");
        }else if (!username.getText().equals("")
                && (comb3.getValue() != null)
                && !address.getText().equals("")
                && !school.getText().equals("")
                && !phonecall.getText().equals("")) {

            if (titlePane.isExpanded()){
                JFXDialogLayout content = new JFXDialogLayout();
                Text bodytext = new Text("密码没有保存，确认放弃修改？");
                content.setBody(bodytext);

                JFXDialog dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);

                JFXButton button =new JFXButton("确认");
                button.setOnAction(event -> {
                    dialog.close();

                    //确认不修改密码
                    titlePane.setExpanded(false);
                    editpwd.setText("修改密码");
                    editpwd.setTextFill(Color.WHITE);
                    pwd.clear();
                    pwdagain.clear();


                });

                content.setActions(button);
                dialog.setOverlayClose(false);
                dialog.show();
            }

            //提交数据
            try {
                userBiz.updateInfoById(Main.user.getU_id(),
                        username.getText(),
                        citybizimpl.idByName(comb3.getValue()),
                        address.getText(),
                        school.getText(),
                        phonecall.getText());

            }catch (Exception e){
                e.printStackTrace();
            }

            editbtn.setText("编辑");
            unEditable();
        }
    }


    public void editpwdButtonClicked(){

        if(!editbtn.getText().equals("编辑")) {
            if (titlePane.isExpanded()) {
                if (!pwd.getText().equals("")) {
                    if (pwd.getText().equals(pwdagain.getText())) {

                        userBiz.updatePwd(Main.user.getU_id(),pwdagain.getText());

                        titlePane.setExpanded(false);
                        editpwd.setText("修改密码");
                        editpwd.setTextFill(Color.WHITE);
                        pwd.clear();
                        pwdagain.clear();
                    }
                } else {
                    new JFXSnackbar(userinfoPane).show("密码不能为空", 2000);
                }

            } else {
                titlePane.setExpanded(true);
                editpwd.setText("保存密码");
                editpwd.setTextFill(Color.valueOf("#FBBC05"));
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username.setText(Main.user.getU_name());
        int districtId = Main.user.getU_city();
        int cityId = citybizimpl.keysById(Main.user.getU_city());
        int provinceId = citybizimpl.keysById(cityId);
        comb3.setPromptText(citybizimpl.nameById(districtId));
        comb2.setPromptText(citybizimpl.nameById(cityId));
        comb1.setPromptText(citybizimpl.nameById(provinceId));
        address.setText(Main.user.getU_address());
        school.setText(Main.user.getU_school());
        phonecall.setText(Main.user.getU_phone());

        province = (ArrayList<String>) citybizimpl.provinceByKey(0);
        for (String s:province){
            comb1.getItems().add(s);
        }

        unEditable();//初始化不可编辑

        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点
                setUnFocusColor();
//                if(username.getText().equals(""))
//                    new JFXSnackbar(registerPane).show("填写用户名",2000);
            }
        });

        pwd.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                pwd.setUnFocusColor(Color.rgb(161,161,161));
                pwdagain.setUnFocusColor(Color.rgb(161,161,161));
            } else {//失去焦点，
                //以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                if (!pwd.getText().matches("^[a-zA-Z]\\w{5,17}$")) {
                    if(!pwd.getText().equals("")) {
                        new JFXSnackbar(userinfoPane).show("密码长度6~18，只能包含字母（开头）、数字和下划线", 2000);
                    }
                    pwd.clear();
                    pwd.setUnFocusColor(Color.rgb(255,0,0));
                }
            }
        });

        pwdagain.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                if (!pwdagain.getText().equals(""))
                    pwd.setUnFocusColor(Color.rgb(161,161,161));
                pwdagain.setUnFocusColor(Color.rgb(161,161,161));

            }else {
                if (!pwdagain.getText().equals(pwd.getText())) {
                    pwdagain.clear();
                    pwd.setUnFocusColor(Color.rgb(255, 0, 0));
                    new JFXSnackbar(userinfoPane).show("密码不一致",2000);
                    pwdagain.setUnFocusColor(Color.rgb(255, 0, 0));
                }
            }
        });

        comb1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
                comb2.getItems().clear();
                comb3.getItems().clear();
            } else {//失去焦点
                setUnFocusColor();
                String str = comb1.getValue();
                Integer inte = citybizimpl.idByName(str);

                city = (ArrayList<String>) citybizimpl.provinceByKey(inte);
                for (String s:city){
                    comb2.getItems().add(s);
                }

                if (comb1.getValue()==null){
                    comb2.getItems().clear();
                    comb3.getItems().clear();
                }
            }
        });

        comb2.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {//获得焦点
                resetUnFocusColor();
                comb3.getItems().clear();
            } else {//失去焦点
                setUnFocusColor();
                String str = comb2.getValue();
                Integer inte = citybizimpl.idByName(str);

                district = (ArrayList<String>) citybizimpl.provinceByKey(inte);
                for (String s:district){
                    comb3.getItems().add(s);
                }

                if (comb2.getValue()==null){
                    comb3.getItems().clear();
                }
            }
        });

        comb3.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                resetUnFocusColor();
            } else {
                setUnFocusColor();
            }
        });

        address.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();

            } else {//失去焦点
                setUnFocusColor();
            }
        });

        school.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else {//失去焦点
                setUnFocusColor();
            }
        });

        phonecall.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {//获得焦点
                resetUnFocusColor();
            } else { //失去焦点，判断输入为手机号
                if (!phonecall.getText().matches(
                        "^(13[0-9]|14[0-9]|15[0-9]|166|17[0-9]|18[0-9]|19[8|9])\\d{8}$")) {
                    setUnFocusColor();
                    phonecall.clear();
                    new JFXSnackbar(userinfoPane).show("请填写真实有效的手机号码",2000);

                } else {
                    setUnFocusColor();
                }
            }
        });



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
