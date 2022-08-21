import java.util.Scanner; // Importacion de libreria para leer teclado

// import javax.sound.midi.SysexMessage;
// import javax.swing.JOptionPane;

public class task {
    public static void main(String args[]) { // Funcion main
        int cantidadEstudiantes = 0, pass; // inicializa variables
        String tipoNota[] = { "Parcial", "Quices", "Talleres" }; // Declara array tipo de notas
        Scanner read = new Scanner(System.in); // Se crea la instancia

        cantidadEstudiantes = pedirCantidadEstudiantes(cantidadEstudiantes, read); // Apunta a la funcion
                                                                                   // pedirCantidadEstudiantesy la
                                                                                   // almacena en cantidadEstudiantes
        pass = guardarNotas(cantidadEstudiantes, read, tipoNota); // Apunta a la funcion guardarNotas y la almacena en
                                                                  // pass
        mostrarSuccessNoSuccess(pass, cantidadEstudiantes); // Apunta a la funcion mostrarSuccessNoSuccess

    }

    static int pedirCantidadEstudiantes(int cantidadEstudiantes, Scanner read) {
        do { // Siclo para ingresar la cantidad de estudiantes
            System.out.print("Ingresa cantidad estudiantes: "); // Mensaje de salida
            cantidadEstudiantes = read.nextInt(); // Pedir y almacenar resultado en cantidadEstudiantes

            String errCE = ((cantidadEstudiantes < 0) ? "\nLa cantidad de estudiantes debe ser mayor a '0'.\n\n" : ""); /* Valida si cantidadEstudiantes es mayor a 0 y se almacena el resultado en errCE */
            System.out.println(errCE); // imprime resultado de la validacion

        } while (cantidadEstudiantes < 0); // Fin siclo do - while

        return cantidadEstudiantes; // retorna cantidadEstudiantes
    }

    static int guardarNotas(int cantidadEstudiantes, Scanner read, String tipoNota[]) {
        int estudiante[] = new int[cantidadEstudiantes];
        // String estudiante[] = {"Luis", "Alfredo", "Ossa", "Diaz", "Carlos"};
        float notaEstudiante[][] = new float[cantidadEstudiantes][tipoNota.length],
                definitivaEstudiante[] = new float[cantidadEstudiantes], suma[] = new float[tipoNota.length];
        boolean pass = false;

        for (int i = 0; i < cantidadEstudiantes; i++) {
            estudiante[i] = i;
        }

        for (int i = 0; i < estudiante.length; i++) {
            System.out.println("****************************************************************************");
            for (int j = 0; j < tipoNota.length; j++) { // for para recorrer tipoNota respecto a la cantidad de
                                                        // estudianes
                j = (j > 3) ? 0 : j; // valida y se asegura que j no sea mayor que la cantidad de notas
                do {
                    System.out.print(
                            "Ingrese la nota del " + tipoNota[j] + " para el estudiante " + estudiante[i] + ": "); // Imprime y pide el tipo de nota que requiere y el estudiante respectivo
                    if (tipoNota[j] == "Parcial") { // Condiciones para validar que tipo de nota requiere
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 ? false : true); // valida si el valor ingresado es correcto
                        notaEstudiante[i][j] *= 0.5;
                    } else if (tipoNota[j] == "Quices") { // Condiciones para validar que tipo de nota requiere
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 ? false : true);// valida si el valor ingresado es correcto
                        notaEstudiante[i][j] *= 0.3;
                    } else if (tipoNota[j] == "Talleres") { // Condiciones para validar que tipo de nota requiere
                        notaEstudiante[i][j] = read.nextFloat();
                        pass = (notaEstudiante[i][j] > 5 || notaEstudiante[i][j] < 0 ? false : true); // valida si el valor ingresado es correcto
                        notaEstudiante[i][j] *= 0.2;
                    }
                    if (pass == false) { // Valida si hay error y si lo hay, imprime mensaje de error
                        errSave(); // Ir a la funcion error para mostrarlo
                    }
                } while (pass == false); // permanece en el bucle siempre y cuando pass sea falso hasta que la nota del
                                         // estudiante sea correcta

                definitivaEstudiante[i] += notaEstudiante[i][j]; // Almacena la definitiva de los estudiantes en
                                                                 // definitivaEstudiante

            }
            System.out.println("****************************************************************************");
        }

        System.out.println();
        System.out.println();
        System.out.println();

        int success = mostrarResultados(definitivaEstudiante, estudiante); // va a la funcion mostrarResultados y
                                                                           // almacena resultado en success

        estudianteAp(definitivaEstudiante, estudiante, success); // va hacia la funcion estudianteAp o estudiante
                                                                 // aprovado

        ordenarMaMe(definitivaEstudiante, estudiante);

        return success; // retorna success o estado aprobado o no
    }

    static int mostrarResultados(float definitivaEstudiante[], int estudiante[]) { // Funcion par amostrar resultados de
                                                                                   // notas y aprobados con reprobados
        float promedioGlobal = 0;
        String status; // Almacena si está aprovado
        int success = 0;
        for (int i = 0; i < estudiante.length; i++) {
            status = ((definitivaEstudiante[i] >= 3) ? "Aprobado" : "Reprobado"); // indica si aprobo o no
            System.out.println(
                    "Nota del estudiante [" + estudiante[i] + "]: " + definitivaEstudiante[i] + " :: " + status);

            if (definitivaEstudiante[i] >= 3) {
                success++; // Acumulador de aprobados
            }

            promedioGlobal += definitivaEstudiante[i]; // suma toda las notas
        }
        promedioGlobal /= estudiante.length; // calcula el promedio
        System.out.println("\nPromedio Global: " + promedioGlobal + "\n"); // imprime promedio global
        return success; // retorna numero de aprobados
    }

    static void mostrarSuccessNoSuccess(int pass, int cantidadEstudiantes) { // funcion para mostrar cantidad de
                                                                             // aprobados y reprobados
        System.out.println("\nCantidad Estudianes Aprobados: " + pass);
        System.out.println("Cantidad Estudianes Reprobados: " + (cantidadEstudiantes - pass));
    }

    static void estudianteAp(float definitivaEstudiante[], int estudiante[], int success) { // Funcion para mostrar
                                                                                            // estudiantes Aprobados
        int estudianteAprobado[] = new int[success], aux = 0;

        for (int i = 0; i < definitivaEstudiante.length; i++) {
            if (definitivaEstudiante[i] >= 3) { // condicion para almacenar estudiantes aprobados
                estudianteAprobado[aux++] = estudiante[i];
            }
        }
        for (int i : estudianteAprobado) { // foreach para imprimir estudianes aprobados
            System.out.println("Estudiante Aprobado [" + i + "]. ");
        }
    }

    static void errSave() { // Funcion para mostrar error
        System.out.println("Err: El numero debe estar entre '0 & 5'.");
    }

    static void ordenarMaMe(float definitivaEstudiante[], int estudiante[]) {
        for(int i=0 ; i < definitivaEstudiante.length ; i++){
            for(int j=0 ; j < definitivaEstudiante.length ; j++){ 

                int aux3 = ((j == (definitivaEstudiante.length - 1)) ? j : (j+1)); // Condicion para que no se rompa el proceso al encontrar que definitivaEStudiante no tiene valor ni espacio despues de definitivaEstudiante.lenght + 1

                if(definitivaEstudiante[j] > definitivaEstudiante[aux3]){
                    float aux=definitivaEstudiante[j];
                    definitivaEstudiante[j]=definitivaEstudiante[aux3];
                    definitivaEstudiante[j+1]=aux;

                    int aux2=estudiante[j];
                    estudiante[j]=estudiante[aux3];
                    estudiante[j+1]=aux2;
                }
            }
        }
        
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("Lista Mayor a Menor");
        System.out.println("************************************************************");

        for (int i = 0; i < estudiante.length; i++) {
            System.out.println("Nota del estudiante [" + estudiante[i] + "]: " + definitivaEstudiante[i]);
        }

        System.out.println("************************************************************");

        
    }

}
