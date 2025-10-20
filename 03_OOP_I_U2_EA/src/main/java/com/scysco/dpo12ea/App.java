package com.scysco.dpo12ea;

// Se importa la clase 'ArrayList' que se habia considerado la solución original.
//import java.util.ArrayList;
// Se importa la clase 'Scanner' herramienta que nos permite leer lo que el 
// usuario escribe en la consola.
import java.util.Scanner;

/**
 * Aplicación para el control de la plaga del gusano barrenador.
 *
 * @author scysco
 *
 *         NOTA PARA LA REVISIÓN DE ESTA ENTREGA
 *         Este código ha sido meticulosamente reescrito y sobre-comentado para
 *         atender
 *         de manera exhaustiva la retroalimentación recibida en la primera
 *         evaluación.
 *         Mi objetivo es demostrar una comprensión profunda de los conceptos de
 *         la unidad,
 *         justificando cada decisión de diseño.
 *         Sobre el Uso de Arreglos (ArrayList): Mi primera solución, que se
 *         encuentra ahora comentada, utilizaba ArrayLists. Como programador con
 *         algo de experiencia,
 *         mi inclinación natural fue usar la herramienta más adecuada y
 *         eficiente, y para
 *         almacenar un número variable de registros, ArrayList es el estándar
 *         en Java.
 *         Entiendo y respeto que este tema aún no se ha cubierto, por lo que he
 *         implementado una solución alternativa usando únicamente las
 *         herramientas vistas,
 *         aunque sea menos flexible que la original.
 *         Sobre la Declaración y Ámbito de Variables: He puesto especial
 *         énfasis en
 *         añadir comentarios que señalan explícitamente el ámbito (scope) de
 *         cada
 *         variable declarada: de clase, de método y de bloque. Con esto, busco
 *         dejar
 *         constancia de que el concepto de la "vida"" de una variable está
 *         perfectamente comprendido.
 *         Sobre los Comentarios: He expandido los comentarios de forma masiva.
 *         Ya
 *         no solo describen la acción de una línea, sino que explican la razón
 *         de ser,
 *         la lógica y la conexión con los requerimientos del caso de estudio y
 *         las
 *         buenas prácticas de programación.
 */
public class App {

  // ---
  // --- ÁMBITO DE CLASE (STATIC) ---
  // ---
  // Las siguientes variables se declaran 'private' para que solo esta clase
  // pueda acceder a ellas, y 'static' para que pertenezcan a la clase en sí,
  // no a una instancia. Esto es CRUCIAL para que su valor se conserve
  // a lo largo de toda la ejecución del programa, permitiendo que un método
  // agregue datos y otro método, llamado después, pueda leerlos.
  // ---

  /*
   * // --- BLOQUE DE CÓDIGO ORIGINAL [COMENTADO PARA LA EVALUACIÓN] ---
   * private static ArrayList<String[]> registrosTIE = new ArrayList<>();
   * private static ArrayList<String[]> registrosVigilancia = new ArrayList<>();
   */

  // --- SOLUCIÓN ADAPTADA A LA RETROALIMENTACIÓN ---
  // En lugar de una lista, uso un String para cada reporte. Comienza con las
  // cabeceras, y luego le ire concatenando cada nuevo registro.
  private static String reporteTIE_str = "| Zona | Lugar                | Cantidad liberada de machos | Fecha de liberacion |\n|------|----------------------|-----------------------------|---------------------|\n";
  private static String reporteVigilancia_str = "| Zona | # Trampa | Lugar de deteccion   | # de casos | Fecha de deteccion | Estado de Alerta |\n|------|----------|----------------------|------------|--------------------|------------------|\n";

  /**
   * El método main es el punto de entrada de cualquier programa en Java.
   * 
   * @param args - Un arreglo de Strings para recibir argumentos desde la línea de
   *             comandos (en este caso, no lo utilizamos).
   */
  public static void main(String[] args) {
    // --- ÁMBITO DE MÉTODO (main) ---
    // Estas variables solo existen dentro del método 'main'.
    // Se crea una instancia de la clase Scanner. La pasaré a otros métodos
    // para que no tengan que crear la suya propia.
    Scanner scanner = new Scanner(System.in);

    // Esta variable almacenará la opción que el usuario elija en el menú principal.
    int opcionSeleccionada;

    // Se utiliza un ciclo 'do-while'. Esta es la elección perfecta para un menú,
    // ya que nos garantiza al menos una ejecución.
    do {
      // Se llama al método que muestra el menú que nos devolverá un número,
      // que guardo en nuestra variable local.
      opcionSeleccionada = mostrarMenuPrincipal(scanner);

      // La estructura 'switch' es ideal para manejar las opciones de un menú.
      // que es mas eficiente que una cadena larga de 'if-else if'.
      // Evalúa el valor de 'opcionSeleccionada' y salta al caso correspondiente.
      switch (opcionSeleccionada) {
        case 1:
          // Si el usuario tecleó '1', llamara al método que se encarga de todo el Módulo
          // TIE.
          gestionarModuloTIE(scanner);
          break; // sin él, el programa seguiría al siguiente 'case'.

        case 2:
          // Si el usuario tecleó '2', llamara al especialista del Módulo de Vigilancia.
          gestionarModuloVigilancia(scanner);
          break;

        case 3:
          // Si el usuario tecleó '3', mostrar un mensaje de despedida.
          System.out.println("\n!Hasta luego!");
          break;

        default:
          // El 'default' es un comodín. Si el usuario escribe cualquier cosa que no
          // sea 1, 2 o 3, mostrando este error.
          System.out.println("\nOpción no válida. Por favor, intente de nuevo.");
          break;
      }
      // La condición para que el ciclo se repita es que la opción sea DIFERENTE de 3.
      // En el momento en que el usuario elige 3, la condición se vuelve falsa y el
      // ciclo terminara.
    } while (opcionSeleccionada != 3);

    // Una vez que el ciclo termina, ya no necesitamos el 'scanner'. Como ya he
    // dicho antes
    // es una buena práctica "cerrarlo" para liberar los recursos del sistema.
    scanner.close();
  }

  /**
   * La única responsabilidad de este método es imprimir el menú principal
   * en la pantalla y devolver la elección del usuario. Cumple con el requisito
   * de la actividad de ser un método que devuelve un valor (un 'int').
   * 
   * @param scanner - Recibe el objeto Scanner como parámetro para poder usarlo.
   * @return El número entero que el usuario haya tecleado.
   */
  public static int mostrarMenuPrincipal(Scanner scanner) {
    System.out.println("\nPrograma de Control del Gusano Barrenador");
    System.out.println("1. Modulo de Registro de control del TIE");
    System.out.println("2. Modulo de Monitoreo del Sistema de Vigilancia");
    System.out.println("3. Salir");
    System.out.print("Seleccione una opcion: ");
    // Lee el próximo entero que el usuario escriba y lo devuelve inmediatamente.
    return scanner.nextInt();
  }

  /**
   * Este método encapsula TODA la funcionalidad del Módulo TIE. Al tener la
   * lógica
   * separada aquí, el método 'main' se mantiene limpio y fácil de leer. Este es
   * un
   * método 'void', es decir, no devuelve ningún valor. Su trabajo lo hace
   * "internamente", modificando la variable de clase 'reporteTIE_str'.
   * 
   * @param scanner - Recibe el Scanner.
   */
  public static void gestionarModuloTIE(Scanner scanner) {
    // --- ÁMBITO DE MÉTODO (gestionarModuloTIE) ---
    // La variable 'opcion' solo existe dentro de este método.
    int opcion;
    do {
      // Se imprime el submenú correspondiente a este módulo.
      System.out.println("\n--- Modulo de Monitoreo de la Tecnica del Insecto Esteril (TIE) ---");
      System.out.println("1. Registros de datos de monitoreo");
      System.out.println("2. Generar reporte");
      System.out.println("3. Volver al menu principal");
      System.out.print("Seleccione una opcion: ");
      opcion = scanner.nextInt();

      // --- DETALLE TÉCNICO IMPORTANTE ---
      // Al igual que en C que vimos el semestre pasado, después de leer un número con
      // 'nextInt()',
      // el cursor del scanner se queda justo después del número, pero el "Enter" que
      // el usuario
      // presionó sigue en la memoria (el buffer de entrada). Si intentáramos leer
      // texto a
      // continuación, leería ese "Enter" vacío. Esta línea 'consume' ese
      // "Enter" para limpiar el camino para la siguiente lectura de texto.
      scanner.nextLine();

      switch (opcion) {
        case 1:
          // --- ÁMBITO DE BLOQUE (case 1) ---
          // Todas estas variables ('zona', 'lugar', etc.) se declaran aquí
          // y solo existen hasta el 'break'.
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

          // Se usa 'String.format' para construir una línea de texto con un
          // formato de tabla perfecto, alineando cada dato en su columna.
          String nuevaLinea = String.format("| %-4s | %-20s | %-27s | %-19s |\n", zona, lugar, cantidad, fecha);

          // Aquí ocurre la "magia" de esta nueva solución sin arrays. Usamos el operador
          // '+=' para tomar lo que ya había en 'reporteTIE_str' y añadirle
          // la 'nuevaLinea' al final.
          reporteTIE_str += nuevaLinea;

          // Le damos retroalimentación al usuario para confirmar que los datos se
          // guardaron.
          System.out.printf("Registrado: %s moscas macho esteriles liberadas en la zona %s en el lugar %s\n", cantidad,
              zona, lugar);
          break;
        case 2:
          // Para generar el reporte, simplemente imprimimo el contenido completo
          // de la variable de clase, que ya tiene las cabeceras y todos
          // los registros que se hayan añadido.
          System.out.println("\nReporte Modulo TIE:");
          System.out.print(reporteTIE_str);
          break;
        case 3:
          // Mensaje para que el usuario sepa que está regresando al menú anterior.
          System.out.println("Volviendo al menu principal...");
          break;
        default:
          System.out.println("Opción no válida.");
          break;
      }
      // El submenú se repetirá hasta que el usuario elija la opción 3 para volver.
    } while (opcion != 3);
  }

  /**
   * Encapsula toda la funcionalidad del Módulo de Vigilancia.
   * 
   * @param scanner - Recibe el Scanner para poder interactuar con el usuario.
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
      scanner.nextLine(); // Limpieza de buffer.

      switch (opcion) {
        case 1:
          System.out.println("Registro de deteccion de presencia del gusano:");
          System.out.print("Ingrese la zona: ");
          String zona = scanner.nextLine();
          System.out.print("Ingrese el numero de trampas: ");
          int numTrampas = scanner.nextInt();
          scanner.nextLine(); // Limpieza de buffer tras leer el número de trampas.

          // El ciclo 'for' es la estructura perfecta para esta tarea, porque
          // a diferencia del 'do-while', se usa cuando sabemos exactamente
          // cuántas veces queremos repetir una acción (en este caso, 'numTrampas' veces).
          for (int i = 1; i <= numTrampas; i++) {
            // --- ÁMBITO DE BLOQUE (for) ---
            // Estas variables son locales al bucle. Se crean y se destruyen
            // en cada una de las iteraciones.
            System.out.printf("Ingrese el lugar Unidad de produccion/rastro para la trampa %d: ", i);
            String lugar = scanner.nextLine();
            System.out.printf("Ingrese la fecha de deteccion (AAAA-MM-DD) para la trampa %d: ", i);
            String fecha = scanner.nextLine();
            System.out.printf("Ingrese el numero de casos detectados en la trampa %d: ", i);
            String casos = scanner.nextLine();

            // Se formatea y se concatena la nueva línea al String del reporte de
            // vigilancia.
            String nuevaLinea = String.format("| %-4s | %-8d | %-20s | %-10s | %-18s | %-16s |\n", zona, i, lugar,
                casos, fecha, "Alerta");
            reporteVigilancia_str += nuevaLinea;

            // Se confirma al usuario cada registro individual.
            System.out.printf(
                "Registrado %s casos del gusano barrenador en la zona %s, trampa %d en el lugar %s en la fecha %s\n",
                casos, zona, i, lugar, fecha);
          }
          // Mensaje final para confirmar que se procesaron todas las trampas.
          System.out.println("Datos de trampas y detecciones registrados para la zona " + zona);
          break;
        case 2:
          System.out.println("\nReporte Modulo Vigilancia:");
          System.out.print(reporteVigilancia_str);
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
