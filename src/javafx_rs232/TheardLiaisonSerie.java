/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_rs232;

import javafx.application.Platform;
import liaisonSerie.LiaisonSerie;

/**
 *
 * @author Michael
 */
public class TheardLiaisonSerie extends Thread{
    FXMLDocumentController fxmlCont;
    LiaisonSerie liaisonSerie=new LiaisonSerie();

    
    public TheardLiaisonSerie() {
    }

    public TheardLiaisonSerie(FXMLDocumentController fxmlCont) {
        this.fxmlCont = fxmlCont;
    }
    
    
    public void run() {
          while(fxmlCont.configConnexionOk) {
          updateMessage(String.valueOf((char)(liaisonSerie.lire()& 0xFF)));
          
          }
    }

/*
Pour déclencher une opération graphique en dehors du thread graphique  utiliser
javafx.application.Platform.runLater(java.lang.Runnable)
Cette méthode permet d'éxécuter le code du runnable par le thread graphique de JavaFX.    
*/
protected void updateMessage(String message) {
    Runnable command = new Runnable() {
        @Override
        public void run() {
            // Le label étant le label JavaFX dont tu
            // souhaites modifier le texte.
           fxmlCont.textArea_Reception.appendText(message);
           if(fxmlCont.Tbutton_Mode.isSelected()){
             fxmlCont.textArea_Emission.appendText(message.toUpperCase());
           }
           
        }
    };
    if (Platform.isFxApplicationThread()) {
        // Nous sommes déjà dans le thread graphique
        command.run();
    } else {
        // Nous ne sommes pas dans le thread graphique
        // on utilise runLater.
        Platform.runLater(command);
    }
     
}

    
    
}
