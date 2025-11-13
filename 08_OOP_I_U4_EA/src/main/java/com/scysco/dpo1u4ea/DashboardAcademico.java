package com.scysco.dpo1u4ea;
/*
 * Importo 'Scanner' 
 */

import java.util.Scanner;

/**
 * =============================================================================
 * Clase DashboardAcademico
 * =============================================================================
 *
 * Esta es mi unica clase para toda la Evidencia de Aprendizaje.
 */
public class DashboardAcademico {

  // --- Atributos Estaticos (Globales a la clase) ---

  // Defino mi 'Scanner' como estatico para usarlo en todos mis metodos.
  private static Scanner scanner = new Scanner(System.in);

  // Defino el tamano de mi matriz y vectores.
  // Lo baso en el ejemplo del caso de estudio: 4 alumnos, 4 actividades.
  private static final int NUM_ALUMNOS = 4;
  private static final int NUM_ACTIVIDADES = 4;

  /*
  * a. Declaracion de arreglos unidimensionales.
  *
  * Estos son mis vectores. Los creo aqui para que todos los metodos los compartan.
  */
  private static String[] alumnos = new String[NUM_ALUMNOS];
  private static String[] actividades = new String[NUM_ACTIVIDADES];
  private static double[] ponderaciones = new double[NUM_ACTIVIDADES];

  /*
  * d. Declaracion de arreglos multidimensionales.
  *
  * Esta es mi matriz (4 filas x 4 columnas).
  * Las filas (primera dimension) seran los alumnos.
  * Las columnas (segunda dimension) seran las actividades.
  */
  private static double[][] calificaciones = new double[NUM_ALUMNOS][NUM_ACTIVIDADES];


  /**
  * Metodo principal (main).
  * Controla el menu y el flujo del programa.
  */
  public static void main(String[] args) {

    // Inicializo mis arreglos con los nombres del caso de estudio para que el programa sea funcional.
    inicializarDatos();

    // Variable de control para mi menu
    boolean salir = false;

    /*
    * Uso 'do-while' para mi menu, para que se
    * repita hasta que el usuario elija "6. salir".
    */
    do {
      System.out.println("\n== Menu ==");
      System.out.println("1 captura las calificaciones de un alumno");
      System.out.println("2 captura ponderaciones por tarea");
      System.out.println("3 imprime Calificaciones de un alumno");
      System.out.println("4 imprime Calificaciones de una tarea");
      System.out.println("5 imprime Las calificaciones ponderadas de 1 alumno y su promedio final");
      System.out.println("6 salir");

      int opcion = scanner.nextInt();
      scanner.nextLine(); // Limpieza de buffer

      switch (opcion) {
        case 1:
        capturarCalificaciones();
        break;
        case 2:
        capturarPonderaciones();
        break;
        case 3:
        imprimirCalificacionesAlumno();
        break;
        case 4:
        imprimirCalificacionesTarea();
        break;
        case 5:
        imprimirPromedioPonderado();
        break;
        case 6:
        salir = true;
        break;
        default:
        System.out.println("Opcion no valida. Intente de nuevo.");
      }
    } while (!salir);

    System.out.println("Saliendo del dashboard...");
    scanner.close();
  }

  /**
  * Metodo auxiliar para pre-llenar los datos del caso.
  */
  private static void inicializarDatos() {
    // Pre-lleno los nombres de alumnos
    alumnos[0] = "Juan";
    alumnos[1] = "Pedro";
    alumnos[2] = "Luis";
    alumnos[3] = "Ernesto";

    // Pre-lleno los nombres de actividades
    actividades[0] = "Tarea 1";
    actividades[1] = "Tarea 2";
    actividades[2] = "Tarea 3";
    actividades[3] = "Examen";

    // Las ponderaciones y calificaciones inician en 0 (por defecto)
  }

  /**
  * Opcion 1: Captura las calificaciones de un alumno.
  * e. Recorrido de arreglos multidimensionales.
  */
  private static void capturarCalificaciones() {
    System.out.println("\n--- 1. Capturar Calificaciones ---");
    System.out.println("¿Cual es el nombre del alumno?");
    String nombre = scanner.nextLine();

    // Uso mi metodo de busqueda
    int indiceAlumno = buscarIndiceAlumno(nombre);

    if (indiceAlumno == -1) {
      System.out.println("Error: Alumno no encontrado.");
      return;
    }

    System.out.println("Ingresando calificaciones para: " + alumnos[indiceAlumno]);

    /*
    * b. Recorrido de arreglos unidimensionales.
    * Uso un 'for' para recorrer mi vector de actividades.
    */
    for (int j = 0; j < NUM_ACTIVIDADES; j++) {
      System.out.print("Ingresa la calificacion obtenida en la " + actividades[j] + ": ");
      double calif = scanner.nextDouble();

      /*
      * f. Operaciones con arreglos multidimensionales.
      * Guardo la calificacion en la matriz.
      * Fila: indiceAlumno (la que encontre)
      * Columna: j (la que estoy recorriendo)
      */
      calificaciones[indiceAlumno][j] = calif;
    }
    scanner.nextLine(); // Limpieza
    System.out.println("Calificaciones de " + alumnos[indiceAlumno] + " actualizadas.");
  }

  /**
  * Opcion 2: Captura las ponderaciones de las tareas.
  * c. Operaciones con arreglos unidimensionales.
  */
  private static void capturarPonderaciones() {
    System.out.println("\n--- 2. Capturar Ponderaciones ---");
    double sumaPonderaciones = 0;

    for (int j = 0; j < NUM_ACTIVIDADES; j++) {
      System.out.print("Ingrese el peso ponderado de la " + actividades[j] + " (ej. 10 para 10%): ");
      ponderaciones[j] = scanner.nextDouble();
      sumaPonderaciones += ponderaciones[j];
    }
    scanner.nextLine(); // Limpieza

    // Validacion simple
    if (sumaPonderaciones != 100) {
      System.out.println("ADVERTENCIA: La suma de las ponderaciones es " + sumaPonderaciones + "%. No es 100%.");
    } else {
      System.out.println("Ponderaciones actualizadas correctamente.");
    }
  }

  /**
  * Opcion 3: Imprime las calificaciones de un alumno.
  * e. Recorrido de arreglos multidimensionales.
  */
  private static void imprimirCalificacionesAlumno() {
    System.out.println("\n--- 3. Imprimir Calificaciones de Alumno ---");
    System.out.println("¿Que alumno desea conocer? (1-4)");

    // Doy opciones para facilitar la busqueda
    for(int i = 0; i < NUM_ALUMNOS; i++) {
      System.out.println((i + 1) + " " + alumnos[i]);
    }
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Limpieza

    // Valido la opcion
    if (opcion < 1 || opcion > NUM_ALUMNOS) {
      System.out.println("Opcion no valida.");
      return;
    }

    int indiceAlumno = opcion - 1; // Convierto (1-4) a (0-3)

    System.out.println(alumnos[indiceAlumno] + " Obtuvo:");

    /*
    * RECORRIDO DE MATRIZ (POR FILA)
    * Fijo la fila (indiceAlumno) y recorro las columnas (j) (Esto me da todos los datos de esa fila).
    */
    for (int j = 0; j < NUM_ACTIVIDADES; j++) {
      System.out.println(actividades[j] + " Calif " + calificaciones[indiceAlumno][j]);
    }
  }

  /**
  * Opcion 4: Imprime las calificaciones de una tarea.
  * e. Recorrido de arreglos multidimensionales.
  */
  private static void imprimirCalificacionesTarea() {
    System.out.println("\n--- 4. Imprimir Calificaciones de Tarea ---");
    System.out.println("¿Que tarea deseas analizar? (1-4)");

    for(int j = 0; j < NUM_ACTIVIDADES; j++) {
      System.out.println((j + 1) + " " + actividades[j]);
    }
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Limpieza

    if (opcion < 1 || opcion > NUM_ACTIVIDADES) {
      System.out.println("Opcion no valida.");
      return;
    }

    int indiceTarea = opcion - 1; // Convierto (1-4) a (0-3)

    System.out.println("La " + actividades[indiceTarea] + " obtuvo el siguiente rendimiento academico:");

    double sumaTotal = 0;

    /*
    * RECORRIDO DE MATRIZ (POR COLUMNA)
    * f. Operaciones con arreglos multidimensionales.
    * Fijo la columna (indiceTarea) y recorro las filas (i).
    */
    String calificacionesStr = "";
    for (int i = 0; i < NUM_ALUMNOS; i++) {
      double calif = calificaciones[i][indiceTarea];
      calificacionesStr += calif + ", ";
      sumaTotal += calif;
    }

    double promedioTarea = sumaTotal / NUM_ALUMNOS;

    System.out.println(calificacionesStr);
    System.out.println("Obteniendo un promedio general de " + promedioTarea);
  }

  /**
  * Opcion 5: Imprime el promedio ponderado de un alumno.
  * f. Operaciones con arreglos multidimensionales.
  */
  private static void imprimirPromedioPonderado() {
    System.out.println("\n--- 5. Imprimir Promedio Ponderado ---");
    System.out.println("¿Que alumno desea conocer? (1-4)");

    for(int i = 0; i < NUM_ALUMNOS; i++) {
      System.out.println((i + 1) + " " + alumnos[i]);
    }
    int opcion = scanner.nextInt();
    scanner.nextLine(); // Limpieza

    if (opcion < 1 || opcion > NUM_ALUMNOS) {
      System.out.println("Opcion no valida.");
      return;
    }

    int indiceAlumno = opcion - 1; // Convierto (1-4) a (0-3)

    System.out.println(alumnos[indiceAlumno] + " Obtuvo:");

    double promedioFinalPonderado = 0;

    /*
    * Recorro la fila del alumno (i) y, por cada calificacion,la multiplico por la ponderacion
    * en la misma posicion (j).
    */
    for (int j = 0; j < NUM_ACTIVIDADES; j++) {
      double califBase = calificaciones[indiceAlumno][j];
      double peso = ponderaciones[j];

      // Verifico si las ponderaciones ya se capturaron
      if (peso == 0.0) {
        System.out.println("ERROR: Las ponderaciones (Opcion 2) no han sido capturadas. El calculo sera incorrecto.");
        return;
      }

      double califPonderada = califBase * (peso / 100.0);
      promedioFinalPonderado += califPonderada;

      System.out.println(actividades[j] + " Calif ponderada " + califPonderada + "%");
    }

    System.out.println("El promedio final ponderado es de " + promedioFinalPonderado + "%");
  }


  /**
  * --- Metodo Auxiliar ---
  * Lo creo para reutilizar codigo.
  * b. Recorrido de arreglos unidimensionales.
  *
  * Recorre el arreglo 'alumnos' buscando un nombre.
  * Devuelve el 'indice' (la posicion) si lo encuentra.
  * Devuelve -1 si no lo encuentra.
  */
  private static int buscarIndiceAlumno(String nombreBuscado) {
    for (int i = 0; i < NUM_ALUMNOS; i++) {
      // Uso 'equalsIgnoreCase' para ignorar mayusculas
      if (alumnos[i].equalsIgnoreCase(nombreBuscado)) {
        return i; // Devuelvo la posicion.
      }
    }
    return -1; // No se encontro.
  }
}
