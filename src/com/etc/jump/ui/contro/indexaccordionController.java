package com.etc.jump.ui.contro;

import com.etc.jump.Main;
import com.etc.jump.bizimpl.MonadsBizImpl;
import com.etc.jump.daoimpl.ShaoMonadsDaoImpl;
import com.etc.jump.entity.ShaoMonads;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class indexaccordionController implements Initializable{

    @FXML
    private TitledPane titlepane;

    @FXML
    private FontAwesomeIcon seemore;

    @FXML
    private JFXButton button;

    @FXML
    private Label monadsid;

    ShaoMonadsDaoImpl shaoMonadsDao = new ShaoMonadsDaoImpl();
    MonadsBizImpl monadsBiz = new MonadsBizImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        seemore.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            if (titlepane.isExpanded()){
                seemore.setIconName("ANGLE_RIGHT");
                titlepane.setExpanded(false);
            }else {
                seemore.setIconName("ANGLE_DOWN");
                titlepane.setExpanded(true);
            }
        });

        button.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
            //TODO 如果时间过了，就提交失败，如果有人抢了，也提交失败
            ShaoMonads shaoMonads = new ShaoMonads();
            shaoMonads.setS_monads_id(Integer.parseInt(monadsid.getText()));
            shaoMonads.setS_user_id(Main.user.getU_id());

            StringBuffer sb = new StringBuffer(LocalDateTime.now().toString());
            sb.setCharAt(10,' ');
            sb.delete(19,23);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            shaoMonads.setS_order_datetime(LocalDateTime.parse(sb,dateTimeFormatter));


            if (monadsBiz.queryInfoById(shaoMonads.getS_monads_id()).getM_status()==0) {
                if (shaoMonadsDao.addshao(shaoMonads)) {
                    monadsBiz.updateStatusById(shaoMonads.getS_monads_id(), 2);//提交成功，将状态改为正在完成
                    button.setDisable(true);
                }
            }else {
//                System.out.println("失败");
            }

        });


    }
}
