import java.util.Scanner; // Importacion de libreria para leer teclado

import javax.sound.midi.SysexMessage;
import javax.swing.JOptionPane;

public class task{

    public static void main(String args[]){
        Automovil unAuto = new Automovil();
        unAuto.verMarca();
        unAuto.verCilindraje();
    }
}
    // Fin de la clase AppAutomovil

    public class Automovil{
        public String marca = "Renault";
        public int cilindraje = 1599;
        public void verMarca(){
        System.out.println("La marca del vehiculo es:  " + this.marca);
    }

    public void verCilindraje(){
        System.out.println("El cilindraje del vehiculo es: " + this.cilindraje  );
    }

}//fin de la clase Persona