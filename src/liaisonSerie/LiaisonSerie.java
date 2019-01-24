package liaisonSerie;

import com.fazecast.jSerialComm.SerialPort;

import java.util.ArrayList;

public class LiaisonSerie {

    public SerialPort serialPort;


//******************************************************************************
//******************************************************************************

    /**
     * Methode qui stocke les noms des ports disponibles dans un ArrayList
     *
     * @return le ArrayList
     */
    public ArrayList listerLesPorts() {
        ArrayList liste = new ArrayList();
        for (SerialPort serialPort : SerialPort.getCommPorts()) {
            System.out.print(serialPort.getDescriptivePortName() + " : ");
            System.out.println(serialPort.getSystemPortName());
            liste.add(serialPort.getSystemPortName());
        }

        return liste;
    }
//******************************************************************************
//******************************************************************************

    /**
     * Methode qui initialise l'objet serialPort avec en paramétre
     * le portDeTravail
     */
    public void initCom(String portDeTravail) {
        this.serialPort = SerialPort.getCommPort(portDeTravail);
    }
//******************************************************************************
//******************************************************************************

    /**
     * M&eacute;thode de configuration et ouvre la liaison s&eacute;rie
     *
     * @param vitesse
     * @param donnees
     * @param parite
     * @param stop
     */
    public void configurerParametres(int vitesse, int donnees, int parite, int stop) {
        this.serialPort.openPort();//ouvre port com
        this.serialPort.setComPortParameters(vitesse, donnees, parite, stop);
    }
//******************************************************************************
//******************************************************************************

    /**
     * Méthode fermant le port série ouvert et les flux
     */
    public void fermerPort() {
        this.serialPort.closePort();
    }
//******************************************************************************
//******************************************************************************

    /**
     * Méthode qui lit dans le buffer de réception le nbs de bytes recus
     */
    public int detecteSiReception() {
        int nbsOctect = 0;
        nbsOctect = this.serialPort.bytesAvailable();
        //System.out.println("serialPort.bytesAvailable(): "+nbsOctect);
        return nbsOctect;
    }
//******************************************************************************
//******************************************************************************

    /**
     * Methode qui lit un octet sur le port
     */
    public byte lire() {
        this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        byte[] octetLu = new byte[1];
        int nbsLu = this.serialPort.readBytes(octetLu, octetLu.length);
        System.out.println(nbsLu + " octect(s) lu(s)");
        return octetLu[0];
    }
//******************************************************************************
//******************************************************************************

    /**
     * Methode qui lit une trame d'octet sur le port
     *
     * @param longeur
     */
    public byte[] lireTrame(int longeur) {
        this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        byte[] trameLue = new byte[longeur];
        int nbsLu = this.serialPort.readBytes(trameLue, trameLue.length);
        return trameLue;
    }

    /******************************************************************************
     //******************************************************************************
     /**
     * Methode qui écrit un octet sur le port
     *
     */
    public void ecrire(byte[] b) {
        this.serialPort.writeBytes(b, b.length);
    }
}