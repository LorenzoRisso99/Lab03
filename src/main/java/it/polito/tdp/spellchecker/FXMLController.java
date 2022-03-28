package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSpell;

    @FXML
    private ComboBox<String> cmbLingua;

    @FXML
    private Label lblErrori;

    @FXML
    private Label lblTempo;

    @FXML
    private TextArea txtErrori;

    @FXML
    private TextArea txtTesto;

    @FXML
    void doClearText(ActionEvent event) {
    	txtErrori.clear();
    	txtTesto.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	model.loadDictionary(cmbLingua.getValue() + ".txt");
    	String s = txtTesto.getText();
    	s.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"?]", "");
    	String celle[] = s.split(" ");
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < celle.length; i++) {
			l.add(celle[i]);
		}
		List<RichWord> k = model.spellCheckText(l);
		for (RichWord r : k) {
			if (r.isCorretto() == false) {
				txtErrori.appendText(r.toString() + "\n");
			}
		}

    	
    }
    
    public void setModel(Dictionary model) {
		this.model = model;
	}
	

    @FXML
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        cmbLingua.getItems().clear();      //non necessario
        cmbLingua.getItems().addAll("Italian","English");
    }
    
}
