import java.util.Scanner; // Importacion de libreria para leer teclado

import javax.sound.midi.SysexMessage;
import javax.swing.JOptionPane;

public class task {
    public static void main(String args[]) {
        int cantidadEstudiantes = 0;
        String tipoNota[] = {"Parcial", "Quices", "Talleres"};
        Scanner read = new Scanner(System.in); // Se crea la instancia 

        cantidadEstudiantes = pedirCantidadEstudiantes(cantidadEstudiantes, read);
        int pass = guardarNotas(cantidadEstudiantes, read, tipoNota);
        mostrarSuccessNoSuccess(pass, cantidadEstudiantes);
        
    }
    
    static int pedirCantidadEstudiantes(int cantidadEstudiantes, Scanner read) {
        do {
            System.out.print("Ingresa cantidad estudiantes: ");
            cantidadEstudiantes = read.nextInt();
            
            // if (cantidadEstudiantes < 0) {
                // System.out.println(
                    String errCE = ((cantidadEstudiantes < 0) ? "\nLa cantidad de estudiantes debe ser mayor a '0'.\n\n" : "" );
                    System.out.println(errCE);
                // );
            //     System.out.println();
            // }

        } while (cantidadEstudiantes < 0);
        
        return cantidadEstudiantes;
    }

    static int guardarNotas(int cantidadEstudiantes, Scanner read, String tipoNota[]) {
        int estudiante[] = new int[cantidadEstudiantes];
        // String estudiante[] = {"Luis", "Alfredo", "Ossa", "Diaz", "Carlos"};
        float notaEstudiante[][] = new float[cantidadEstudiantes][tipoNota.length], definitivaEstudiante[] = new float[cantidadEstudiantes], suma[] = new float[tipoNota.length];
        boolean pass = false;

        for (int i = 0; i < cantidadEstudiantes; i++) {
            estudiante[i] = i;
        }

        for (int i = 0; i < estudiante.length; i++) {
            System.out.println("****************************************************************************");
            for (int j = 0; j < tipoNota.length; j++) {
                if (j > 3) {
                    j=0;
                }
                do {
                    System.out.print("Ingrese la nota del " + tipoNota[j] + " para el estudiante " + estudiante[i] + ": " );
                    if(tipoNota[j] == "Parcial") {
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 ? false : true );
                        notaEstudiante[i][j] *= 0.5;
                    }else if(tipoNota[j] == "Quices") {
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 ? false : true );
                        notaEstudiante[i][j] *= 0.3;
                    }else if(tipoNota[j] == "Talleres") {
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 ? false : true );
                        notaEstudiante[i][j] *= 0.2;
                    }
                    if (pass == false) {
                        errSave();
                    }

                    
                    
                }while( pass == false );

                definitivaEstudiante[i] += notaEstudiante[i][j];
                
            }
            System.out.println("****************************************************************************");
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        int success = mostrarResultados(definitivaEstudiante, estudiante);
        
        estudianteAp(definitivaEstudiante, estudiante, success);

        return success;
    }
    
    static int mostrarResultados(float definitivaEstudiante[], int estudiante[]) {
        float promedioGlobal = 0;
        String status;
        int success = 0;
        for (int i = 0; i < estudiante.length; i++) {
            status = ((definitivaEstudiante[i] >= 3) ? "Aprobado": "Reprobado");
            System.out.println("Nota del estudiante [" + estudiante[i] + "]: " + definitivaEstudiante[i] + " :: " + status);
            
            if(definitivaEstudiante[i] >= 3) {
                success++;
            }
            
            promedioGlobal += definitivaEstudiante[i];
        }
        promedioGlobal /= estudiante.length;
        System.out.println("\nPromedio Global: " + promedioGlobal + "\n");
        return success;
    }

    static void mostrarSuccessNoSuccess(int pass, int cantidadEstudiantes) {
        System.out.println("\nCantidad Estudianes Aprobados: " + pass);
        System.out.println("Cantidad Estudianes Reprobados: " + (cantidadEstudiantes - pass));
    }

    static void estudianteAp(float definitivaEstudiante[], int estudiante[], int success) {
        int estudianteAprobado[] = new int[success], aux = 0;
        
        for (int i = 0; i < definitivaEstudiante.length; i++) {
            if (definitivaEstudiante[i] >= 3) {
                estudianteAprobado[aux++] = estudiante[i];
            }
        }
        for (int i : estudianteAprobado) {
            System.out.println("Estudiante Aprobado [" + i + "]. ");
        }
    }

    static void errSave() {
        System.out.println("Err: El numero debe estar entre '0 & 5'.");
    }

}

