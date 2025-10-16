package com.scysco.dpo12ea;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Aplicación para el control de la plaga del gusano barrenador,
 * como parte de la Evidencia de Aprendizaje de la Unidad 2.
 * Este programa es una demostración de cómo usar métodos y estructuras de control
 * para crear una aplicación de consola interactiva y modular.
 *
 * @author scysco
 */
public class App {

  // Para guardar los registros entre una opción y otra (registrar y luego reportar),
  // usaremos estas listas. Actúan como una pequeña base de datos temporal
  // mientras el programa está en ejecución.
  private static ArrayList<String[]> registrosTIE = new ArrayList<>();
  private static ArrayList<String[]> registrosVigilancia = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int opcionSeleccionada;

    // Este es el corazón de nuestra aplicación. Un ciclo `do-while` es perfecto aquí
    // porque nos asegura que el menú se mostrará al menos una vez, y se seguirá
    // repitiendo hasta que el usuario elija salir (opción 3).
    do {
      // Llamamos a nuestro método "mensajero" para que nos traiga la opción del usuario.
      opcionSeleccionada = mostrarMenuPrincipal(scanner);

      // Usamos un `switch` para manejar la elección. Es como un director de orquesta
      // que llama al método correcto según la opción. Mucho más limpio que
      // un montón de `if-else` anidados.
      switch (opcionSeleccionada) {
        case 1:
        gestionarModuloTIE(scanner);
        break;
        case 2:
        gestionarModuloVigilancia(scanner);
        break;
        case 3:
        System.out.println("\n!Hasta luego!");
        break;
        default:
        System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
        break;
      }
    } while (opcionSeleccionada != 3);

    // Buena práctica: siempre cerrar el scanner al final para liberar recursos.
    scanner.close();
  }

  /**
     * Muestra el menú principal y captura la selección del usuario.
     * Su única misión es mostrar el menú y devolvernos el número que el usuario eligió.
     * Este método cumple con el requisito de la actividad de devolver un valor (un int).
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @return La opción (int) seleccionada por el usuario.
     */
  public static int mostrarMenuPrincipal(Scanner scanner) {
    System.out.println("\nPrograma de Control del Gusano Barrenador");
    System.out.println("1. Modulo de Registro de control del TIE");
    System.out.println("2. Modulo de Monitoreo del Sistema de Vigilancia");
    System.out.println("3. Salir");
    System.out.print("Seleccione una opcion: ");
    return scanner.nextInt();
  }

  /**
     * Gestiona toda la lógica del Módulo TIE. Al encapsularla aquí,
     * el método `main` queda mucho más limpio y legible.
     * Este es un ejemplo de un método que no devuelve valor (void).
     *
     * @param scanner El objeto Scanner que pasamos desde main.
     */
  public static void gestionarModuloTIE(Scanner scanner) {
    int opcion;
    do {
      System.out.println("\n--- Modulo de Monitoreo de la Tecnica del Insecto Esteril (TIE) ---");
      System.out.println("1. Registros de datos de monitoreo");
      System.out.println("2. Generar reporte");
      System.out.println("3. Volver al menu principal");
      System.out.print("Seleccione una opcion: ");
      opcion = scanner.nextInt();

      // ¡Ojo! Este `scanner.nextLine()` es clave. Después de leer un número con `nextInt()`,
      // queda un "salto de línea" invisible en el buffer. Si no lo "consumimos" con
      // esta línea, la siguiente lectura de texto (`nextLine()`) fallaría.
      scanner.nextLine();

      switch (opcion) {
        case 1:
        System.out.print("Ingrese la zona: ");
        String zona = scanner.nextLine();
        System.out.print("Ingrese el numero de trampas: ");
        String numTrampas = scanner.nextLine();
        System.out.print("Ingrese el lugar de liberacion Unidad de Produccion o Rastro: ");
        String lugar = scanner.nextLine();
        System.out.print("Ingrese la cantidad de moscas macho esteriles liberadas: ");
        String cantidad = scanner.nextLine();
        System.out.print("Ingrese la fecha de liberacion (AAAA-MM-DD): ");
        String fecha = scanner.nextLine();

        // Guardamos los datos en nuestra lista temporal.
        registrosTIE.add(new String[]{zona, lugar, cantidad, fecha});
        System.out.printf("Registrado: %s moscas macho esteriles liberadas en la zona %s en el lugar %s\n", cantidad, zona, lugar);
        break;
        case 2:
        System.out.println("\nReporte Modulo TIE:");
        System.out.println("| Zona | Lugar                | Cantidad liberada de machos | Fecha de liberacion |");
        System.out.println("|------|----------------------|-----------------------------|---------------------|");
        // Recorremos la lista de registros con un for-each, que es más simple y legible.
        for (String[] registro : registrosTIE) {
          System.out.printf("| %-4s | %-20s | %-27s | %-19s |\n", registro[0], registro[1], registro[2], registro[3]);
        }
        break;
        case 3:
        System.out.println("Volviendo al menu principal...");
        break;
        default:
        System.out.println("Opción no válida.");
        break;
      }
    } while (opcion != 3);
  }

  /**
     * Gestiona toda la lógica del Módulo de Vigilancia.
     * Al igual que el método anterior, es `void` y mantiene el `main` organizado.
     *
     * @param scanner El objeto Scanner.
     */
  public static void gestionarModuloVigilancia(Scanner scanner) {
    int opcion;
    do {
      System.out.println("\n--- Modulo de Monitoreo del Sistema de Vigilancia ---");
      System.out.println("1. Registro de deteccion");
      System.out.println("2. Reporte de deteccion del gusano barrenador");
      System.out.println("3. Volver al menu principal");
      System.out.print("Seleccione una opcion: ");
      opcion = scanner.nextInt();
      scanner.nextLine(); // No olvidar consumir el salto de línea.

      switch (opcion) {
        case 1:
        System.out.println("Registro de deteccion de presencia del gusano:");
        System.out.print("Ingrese la zona: ");
        String zona = scanner.nextLine();
        System.out.print("Ingrese el numero de trampas: ");
        int numTrampas = scanner.nextInt();
        scanner.nextLine();

        // Un bucle `for` es ideal aquí, porque sabemos exactamente cuántas veces
        // se va a repetir (una vez por cada trampa).
        for (int i = 1; i <= numTrampas; i++) {
          System.out.printf("Ingrese el lugar Unidad de produccion/rastro para la trampa %d: ", i);
          String lugar = scanner.nextLine();
          System.out.printf("Ingrese la fecha de deteccion (AAAA-MM-DD) para la trampa %d: ", i);
          String fecha = scanner.nextLine();
          System.out.printf("Ingrese el numero de casos detectados en la trampa %d: ", i);
          String casos = scanner.nextLine();

          registrosVigilancia.add(new String[]{zona, String.valueOf(i), lugar, casos, fecha, "Alerta"});
          System.out.printf("Registrado %s casos del gusano barrenador en la zona %s, trampa %d en el lugar %s en la fecha %s\n", casos, zona, i, lugar, fecha);
        }
        System.out.println("Datos de trampas y detecciones registrados para la zona " + zona);
        break;
        case 2:
        System.out.println("\nReporte Modulo Vigilancia:");
        System.out.println("| Zona | # Trampa | Lugar de deteccion   | # de casos | Fecha de deteccion | Estado de Alerta |");
        System.out.println("|------|----------|----------------------|------------|--------------------|------------------|");
        for (String[] registro : registrosVigilancia) {
          System.out.printf("| %-4s | %-8s | %-20s | %-10s | %-18s | %-16s |\n", registro[0], registro[1], registro[2], registro[3], registro[4], registro[5]);
        }
        break;
        case 3:
        System.out.println("Volviendo al menu principal...");
        break;
        default:
        System.out.println("Opción no válida.");
        break;
      }
    } while (opcion != 3);
  }
}
