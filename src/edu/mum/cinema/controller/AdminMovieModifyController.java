/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cinema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.mum.cinema.context.ApplicationContext;
import edu.mum.cinema.model.Movie;
import edu.mum.cinema.service.MovieService;
import edu.mum.cinema.utilities.AlertMaker;
import edu.mum.cinema.utilities.Constant;
import org.springframework.util.StringUtils;

/**
 * FXML Controller class
 *
 * @author Mingwei
 */
public class AdminMovieModifyController implements Initializable {
    
    @FXML
    private TextField txtTitle;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField fileImage;
    @FXML
    private TextField txtDuration;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private ChoiceBox<String> chbGenre;
    @FXML
    private Label hidId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chbGenre.setItems(FXCollections.observableArrayList("Drama", "Action", "Advanture", "Comedy", "Fantasy"));
        if(ApplicationContext.stage.getUserData() instanceof Movie){
            Movie m = (Movie) ApplicationContext.stage.getUserData();
            this.hidId.setText(m.getId());
            this.txtTitle.setText(m.getTitle());
            this.fileImage.setText(m.getImageUrl());
            this.txtDuration.setText(m.getDuration());
            this.txtDescription.setText(m.getDescription());
            if(Pattern.matches("\\d+", m.getGenre()))
                this.chbGenre.getSelectionModel().select(Integer.valueOf(m.getGenre()));
        }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        if(!isValidForm()) return;
        Movie m = new Movie();
        m.setId(this.hidId.getText());
        m.setGenre(String.valueOf(this.chbGenre.getSelectionModel().getSelectedIndex()));
        m.setDescription(this.txtDescription.getText());
        m.setDuration(this.txtDuration.getText());
        m.setTitle(this.txtTitle.getText());
        m.setImageUrl(this.fileImage.getText());
        if(ApplicationContext.stage.getUserData() instanceof String){
            AlertMaker.showMessage(MovieService.addMovie(m));
        }else{
            AlertMaker.showMessage(MovieService.updateMovie(m));
        }
        ((Stage)txtTitle.getScene().getWindow()).close();
        ((AdminMovieListController) ApplicationContext.stage.getScene().getUserData()).loadDate();
    }


    @FXML
    private void handleCancelAction(ActionEvent event) {
        ((Stage)txtTitle.getScene().getWindow()).close();
    }

    @FXML
    private void handleImageSection(ActionEvent event) {
    }
    
    private boolean isValidForm(){
        if(StringUtils.isEmpty(txtTitle.getText()) || StringUtils.isEmpty(txtDescription.getText()) 
                || StringUtils.isEmpty(fileImage.getText()) || StringUtils.isEmpty(txtDuration.getText())
                || chbGenre.getValue() == null){
            AlertMaker.showMessage("please make sure all input intems aren't empty");
            return false;
        }
        return true;
    }
    
}
