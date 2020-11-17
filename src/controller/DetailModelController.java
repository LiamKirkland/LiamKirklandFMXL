package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Matcheduser;

public class DetailModelController {
    
    @FXML
    private Label dateLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label nameLabel;
    
    @FXML
    private ImageView profPic;

    @FXML
    void returnToMain(ActionEvent event) {

        Stage stage = (Stage)backButton.getScene().getWindow();
        
        if(prevScene != null){
            stage.setScene(prevScene);
        }
    }
    
    Matcheduser selectedUser;
    Scene prevScene;
    
    public void setPreviousScene(Scene scene){
        prevScene = scene;
        backButton.setDisable(false);
    }
    
    public void initData(Matcheduser user){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        selectedUser = user;
        idLabel.setText(user.getId().toString());
        nameLabel.setText(user.getName());
        dateLabel.setText(df.format(user.getMatchsince()));
        
        try{
            String imageName = "/resource/images/" + user.getName() + ".jpg";
            Image pfp = new Image(getClass().getResourceAsStream(imageName));
            
            profPic.setImage(pfp);
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
        assert backButton != null : "fx:id=\"backButtong\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert idLabel != null : "fx:id=\"idLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert dateLabel != null : "fx:id=\"dateLabel\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        assert profPic != null : "fx:id=\"profPic\" was not injected: check your FXML file 'DetailModelView.fxml'.";
        
        backButton.setDisable(true);
    }
}
