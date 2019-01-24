/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_rs232;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 *
 * @author Michael
 */
public class FXMLDocumentController implements Initializable {

    /**
     *
     */
    public ToggleButton tButton_Lecture;
    @FXML
    private Label label_Com;
    @FXML
    private Label label_Vitesse;
    @FXML
    private Label label_Paritee;
    @FXML
    private Label label_Stop;
    @FXML
    private TextField textField_Com;
    @FXML
    public TextArea textArea_Reception;
    @FXML
    public TextArea textArea_Emission;
    @FXML
    private TextField textField_Vitesse;
    @FXML
    private TextField textField_Paritee;
    @FXML
    private TextField textField_Stop;
    @FXML
    private Label label_Donnee;
    @FXML
    private TextField textField_Donnees;
    @FXML
    private ToggleButton Tbutton_Connexion;
    @FXML
    private GridPane gridPane_Config;
    @FXML
    public ToggleButton Tbutton_Mode;
   
    String com="?";
    int vitesse=0;
    int donnees=0;
    int parite=0;
    int stop=0;
    public boolean configConnexionOk=false;
    TheardLiaisonSerie monTheadrLiaisonSerie;
  
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }    



    @FXML
    private void Tbutton_Connexion(ActionEvent event) {
        if (Tbutton_Connexion.isSelected()){
            com=textField_Com.getText();
            vitesse=Integer.parseInt(textField_Vitesse.getText());
            donnees=Integer.parseInt(textField_Donnees.getText());
            parite=Integer.parseInt(textField_Paritee.getText());
            stop=Integer.parseInt(textField_Stop.getText());

            if((com.contains("com") & vitesse!=0 & donnees!=0 & stop!=0)){
                monTheadrLiaisonSerie=new TheardLiaisonSerie(this);
                System.out.println("Config OK");
                configConnexionOk=true;
                System.out.println("configConnexionOk "+configConnexionOk );
                
                monTheadrLiaisonSerie.liaisonSerie.initCom(com);
                monTheadrLiaisonSerie.liaisonSerie.configurerParametres(vitesse, donnees, parite, stop);
                textArea_Emission.setDisable(false);
                textArea_Reception.setDisable(false);
                monTheadrLiaisonSerie.start();
            }
        }    
        if (!Tbutton_Connexion.isSelected()){ 
           configConnexionOk=false; 
           System.out.println("configConnexionOk "+configConnexionOk );
           textArea_Emission.setDisable(true);
           textArea_Reception.setDisable(true);
           monTheadrLiaisonSerie.stop();
           monTheadrLiaisonSerie.liaisonSerie.fermerPort();
          

       }    
        
    }

   /* private void textArea_Emission(KeyEvent event) {
         monTheadrLiaisonSerie.liaisonSerie.ecrire(textArea_Emission.getText().getBytes());
    }
*/

    @FXML
    private void textArea_Emission(KeyEvent event) throws UnsupportedEncodingException {
       
        String leTexte=textArea_Emission.getText(textArea_Emission.getCaretPosition()-1, textArea_Emission.getCaretPosition());
        monTheadrLiaisonSerie.liaisonSerie.ecrire(leTexte.getBytes(StandardCharsets.ISO_8859_1));
    }

    @FXML
    private void Tbutton_Mode(ActionEvent event) {
        
        
    }
   
    
}
