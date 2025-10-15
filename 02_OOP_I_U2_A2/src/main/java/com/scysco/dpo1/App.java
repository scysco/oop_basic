package com.scysco.dpo1;
import java.util.Scanner;

/**
 * Programa ficticio para gestionar la inscripción de equipos en el Concurso Nacional de Robótica 2025.
 * Captura datos del equipo y sus integrantes, calcula el promedio de edad y asigna una categoría.
 *
 * @author scysco
 */
public class App {
  // --- Constantes para las categorías ---
  // Define los rangos de edad para facilitar el mantenimiento del código.
  private static final int EDAD_MIN_JUNIOR = 15;
  private static final int EDAD_MAX_JUNIOR = 18;
  private static final int EDAD_MIN_SENIOR = 19; // Asumiendo que 18.01 ya es Senior
  private static final int EDAD_MAX_SENIOR = 23;

  public static void main(String[] args) {
    // Objeto Scanner para leer la entrada del usuario desde la consola.
    Scanner scanner = new Scanner(System.in);

    System.out.println("Concurso Nacional de Robotica 2025");

    // --- 1. Captura de datos generales del equipo ---
    System.out.print("Ingrese el nombre del equipo: ");
    String nombreEquipo = scanner.nextLine();

    System.out.print("Ingrese el nombre del coach: ");
    String nombreCoach = scanner.nextLine();

    System.out.print("Ingrese la institucion a la que pertenecen: ");
    String institucion = scanner.nextLine();

    System.out.print("Ingrese el numero de integrantes: ");
    int numeroIntegrantes = scanner.nextInt();
    scanner.nextLine(); // Consumir el salto de línea pendiente

    // --- 2. Procesamiento de datos de integrantes ---
    int sumaEdades = calcularSumaEdades(scanner, numeroIntegrantes);
        
    // --- 3. Cálculos y asignación de categoría ---
    double promedioEdad = calcularPromedio(sumaEdades, numeroIntegrantes);
    String categoria = determinarCategoria(promedioEdad);
        
    // --- 4. Mostrar resultados ---
    mostrarFichaInscripcion(nombreEquipo, nombreCoach, institucion, numeroIntegrantes, promedioEdad, categoria);

    // Se cierra el scanner para liberar recursos.
    scanner.close();
  }

  /**
   * Solicita los datos de cada integrante, calcula y devuelve la suma de sus edades.
   * Utiliza una estructura cíclica 'for' para iterar sobre el número de integrantes.
   * @param scanner El objeto Scanner para leer la entrada del usuario.
   * @param numeroIntegrantes El total de integrantes del equipo.
   * @return La suma total de las edades de los integrantes.
   */
  public static int calcularSumaEdades(Scanner scanner, int numeroIntegrantes) {
    int sumaEdadesAcumulada = 0;
    for (int i = 1; i <= numeroIntegrantes; i++) {
      System.out.println("Estudiante " + i);
      System.out.print("Nombre: ");
      // Se captura el nombre, aunque no se usa en cálculos, es parte del caso de estudio.
      String nombreEstudiante = scanner.nextLine(); 
      System.out.print("Edad: ");
      int edad = scanner.nextInt();
      scanner.nextLine(); // Consumir el salto de línea
      sumaEdadesAcumulada += edad;
    }
    return sumaEdadesAcumulada;
  }

  /**
   * Calcula el promedio de edad.
   * @param sumaEdades La suma total de las edades.
   * @param numeroIntegrantes El número total de integrantes.
   * @return El promedio de edad como un valor double.
   */
  public static double calcularPromedio(int sumaEdades, int numeroIntegrantes) {
    // Se realiza un cast a double para asegurar que la división no sea entera.
    return (double) sumaEdades / numeroIntegrantes;
  }

  /**
   * Determina la categoría del equipo basándose en el promedio de edad.
   * Utiliza una estructura de control de selección 'if-else if'.
   * @param promedioEdad El promedio de edad del equipo.
   * @return Un String con el nombre de la categoría ("JUNIOR", "SENIOR" o "No asignada").
   */
  public static String determinarCategoria(double promedioEdad) {
    if (promedioEdad >= EDAD_MIN_JUNIOR && promedioEdad <= EDAD_MAX_JUNIOR) {
      return "JUNIOR";
    } else if (promedioEdad >= EDAD_MIN_SENIOR && promedioEdad <= EDAD_MAX_SENIOR) {
      return "SENIOR";
    } else {
      return "No asignada"; // Para casos fuera de los rangos definidos
    }
  }

  /**
   * Muestra en consola la ficha de inscripción con todos los datos del equipo.
   * Este método recibe todos los datos necesarios como parámetros.
   */
  public static void mostrarFichaInscripcion(String equipo, String coach, String inst, int integrantes, double promEdad, String cat) {
    System.out.println("\nFICHA DE INSCRIPCION");
    System.out.println("=====================");
    System.out.println("Equipo: " + equipo);
    System.out.println("Coach: " + coach);
    System.out.println("Institucion: " + inst);
    System.out.println("Numero de integrantes: " + integrantes);
    System.out.println("Promedio de edad: " + promEdad);
    System.out.println("Categoria: " + cat);
  }
}
