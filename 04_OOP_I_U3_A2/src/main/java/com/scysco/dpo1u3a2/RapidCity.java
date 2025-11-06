/*
 * IMPORTACIONES NECESARIAS
 * Importamos solo 'Scanner'.
 *
 * NOTA IMPORTANTE DE ARQUITECTURA:
 * Esta Actividad 2 se alinea con las restricciones vistas en la Unidad 2.
 * NO usare colecciones (como ArrayList) para almacenar la "flota",
 * ya que el tema de Arreglos corresponde a la Unidad 4 como se dijo en clase.
 *
 * En su lugar, simulare una "flota" fija de dos vehículos
 * pre-instanciados como variables estáticas, lo cual me permitira
 * cumplir con los requisitos de Polimorfismo sin adelantarme.
 *
 * Tambien este documento esta sobrecomentado y no cumple buenas practicas de 
 * comentado de codigo, seran comentarios mas academicos que de codigo real,
 * esto por ser solicitado asi en anteriores retroalimentaciones.
 */

package com.scysco.dpo1u3a2;

import java.util.Scanner;

/**
 * =============================================================================
 * Interfaz IRegistrable
 * =============================================================================
 * e. Utilizar interfaces para añadir comportamientos adicionales.
 *
 * JUSTIFICACIÓN DE DISEÑO:
 * Usare una interfaz para definir el comportamiento 'registrarTransporte()'.
 * Esto desacopla la lógica. 
 * 'Vehiculo' no necesita saber cómo registrarse, solo las subclases que 
 * "firmen" este contrato (con 'implements') estarán obligadas a definir 
 * este método.
 */

interface IRegistrable {
  /**
     * Método abstracto que obliga a las clases implementadoras
     * a definir cómo se "registran" (en este caso, cómo imprimen
     * sus datos iniciales).
     */
  void registrarTransporte();
}

/**
 * =============================================================================
 * Clase Abstracta Vehiculo
 * =============================================================================
 * a. Jerarquía de clases (uso de herencia)
 *
 * JUSTIFICACIÓN DE DISEÑO:
 * Este define la plantilla, atributos y comportamientos comunes. Todo lo
 * definido aquí será heredado por las subclases, maximizando la
 * reutilización de código, que es el objetivo de la herencia.
 */
abstract class Vehiculo {

  // --- ÁMBITO DE CLASE (PROTECTED) ---
  // Atributos definidos en la superclase. Son 'protected'
  // para que puedan ser heredados y accedidos directamente
  // por las subclases (Motocicleta, CarroChico).
  protected String tipoTransporte;
  protected double costoPorKM;
  protected double costoPorKG;
  protected int capacidadMaximaKg;
  protected String usoIdeal;

  /**
     * Constructor de la SUPERCLASE.
     * Este constructor será invocado por las subclases
     * mediante la palabra clave 'super()' para inicializar
     * estos atributos comunes.
     */
  public Vehiculo(String tipoTransporte, double costoPorKM, double costoPorKG, int capacidadMaximaKg, String usoIdeal) {
    this.tipoTransporte = tipoTransporte;
    this.costoPorKM = costoPorKM;
    this.costoPorKG = costoPorKG;
    this.capacidadMaximaKg = capacidadMaximaKg;
    this.usoIdeal = usoIdeal;
  }

  // --- MÉTODOS ABSTRACTOS ---
  // a. Jerarquía de clases (implica métodos abstractos para el polimorfismo).
  //
  // Un método abstracto es una firma sin implementación
  // (sin llaves {}). Es una ORDEN que obliga a las subclases a sobreescribirlo (Override)
  // y definir su propia lógica.

  /**
     * b. Polimorfismo (a través de resultados polimórficos)
     *
     * Este es un método polimórfico.
     * Cuando llamemos a 'generaRep()' desde una variable de tipo 'Vehiculo',
     * Java decidirá en tiempo de ejecución (enlace dinámico) qué versión
     * ejecutar (la de Motocicleta o la de CarroChico).
     */
  public abstract String generaRep();

  /**
     * Método abstracto para el cálculo de envío base.
     */
  public abstract double calcularEnvio(double distanciaKM, double pesoKG);

  /**
     * c. Sobrecarga de métodos
     *
     * Este es un método SOBRECARGADO
     * Se llama igual que el anterior ('calcularEnvio'), pero tiene
     * una firma de parámetros diferente (recibe un 'costoSeguro' adicional).
     * Java sabe cuál llamar en tiempo de compilación (enlace estático)
     * basándose en los argumentos que le pasemos.
     */
  public abstract double calcularEnvio(double distanciaKM, double pesoKG, double costoSeguro);

  // --- MÉTODOS CONCRETOS (Getters) ---
  // Método simple (no abstracto) que será heredado tal cual.
  public String getTipoTransporte() {
    return tipoTransporte;
  }
}

/**
 * =============================================================================
 * Clase Motocicleta (Subclase)
 * =============================================================================
 * a. Jerarquía de clases (uso de herencia)
 *
 * Esta es una SUBCLASE (o clase Hija).
 * 1. 'extends Vehiculo': Hereda todo de 'Vehiculo'.
 * 2. 'implements IRegistrable': "Firma el contrato" de la interfaz
 * y se obliga a definir 'registrarTransporte()'.
 */
class Motocicleta extends Vehiculo implements IRegistrable {

  /**
     * Constructor de Motocicleta.
     * Llama al constructor de la superclase ('Vehiculo') con la
     * palabra clave 'super' y le pasa los valores fijos del caso de estudio.
     */
  public Motocicleta() {
    super("Motocicleta", 15.0, 4.0, 20, "transporte de paquetes chicos en distancias no superiores a 20km");
  }

  /**
     * Implementación (Override) del método de la interfaz IRegistrable.
     * Aquí defino"cómo" se registra una Motocicleta,
     * cumpliendo el contrato de la interfaz.
     */
  @Override
  public void registrarTransporte() {
    // Imprimo los datos que esta clase heredó de 'Vehiculo'
    System.out.println("Tipo de Transporte: " + this.tipoTransporte);
    System.out.println("Costo por KM: " + this.costoPorKM);
    System.out.println("Costo por KG transportado: " + this.costoPorKG);
    System.out.println("Capacidad máxima de transporte en Kg: " + this.capacidadMaximaKg);
    System.out.println("Uso Ideal: " + this.usoIdeal);
  }

  /**
     * Implementación (Override) del método abstracto 'generaRep()'.
     * Esta es la lógica polimórfica específica para Motocicleta.
     * La anotación '@Override' le indica al compilador que
     * estamos sobreescribiendo intencionalmente un método heredado.
     */
  @Override
  public String generaRep() {
    /*
         * d. Sobrecarga de operadores
         *
         * JUSTIFICACIÓN: El contenido de la unidad indica
         * que en Java no podemos crear nuevos operadores sobrecargados,
         * pero SÍ podemos usar la sobrecarga existente del operador '+'.
         *
         * Aquí, el operador '+' NO ESTÁ SUMANDO, está CONCATENANDO
         * Strings. Este sera mi ejemplo de sobrecarga de operador que pide
         * la rúbrica.
         */
    return "--- Reporte: " + this.tipoTransporte + " ---\n" +
    "Costo por KM: " + this.costoPorKM + "\n" +
    "Costo por KG: " + this.costoPorKG + "\n" +
    "Capacidad: " + this.capacidadMaximaKg + " Kg\n" +
    "Uso Ideal: " + this.usoIdeal + "\n";
  }

  /**
     * Implementación (Override) del método abstracto base 'calcularEnvio()'.
     */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG) {
    /*
         * d. Sobrecarga de operadores.
         * 
         * JUSTIFICACIÓN: A diferencia del método anterior,
         * aquí el operador '+' SÍ ESTÁ SUMANDO valores numéricos.
         * El compilador de Java decidira que acción realizar (sumar o concatenar)
         * basándose en los tipos de dato.
         */
    return (this.costoPorKM * distanciaKM) + (this.costoPorKG * pesoKG);
  }

  /**
     * Implementación (Override) del método abstracto SOBRECARGADO
     * 'calcularEnvio()'.
     */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG, double costoSeguro) {
    // Reutilización de la lógica del método base (con 2 parámetros)
    // y le sumo el seguro.
    return this.calcularEnvio(distanciaKM, pesoKG) + costoSeguro;
  }
}

/**
 * =============================================================================
 * Clase CarroChico (Subclase)
 * =============================================================================
 * a. Jerarquía de clases (uso de herencia)
 *
 * Esta es la segunda SUBCLASE. Al igual que Motocicleta, hereda de
 * Vehiculo e implementa la interfaz IRegistrable.
 */
class CarroChico extends Vehiculo implements IRegistrable {

  /**
     * Constructor de CarroChico.
     * Llama al constructor 'super()' con los datos fijos de este caso.
     */
  public CarroChico() {
    super("Carro Chico", 30.0, 8.0, 200, "transporte de paquetes medianos en distancias no superiores a 60km");
  }

  /**
     * Implementación (Override) del método de la interfaz IRegistrable.
     */
  @Override
  public void registrarTransporte() {
    System.out.println("Tipo de Transporte: " + this.tipoTransporte);
    System.out.println("Costo por KM: " + this.costoPorKM);
    System.out.println("Costo por KG transportado: " + this.costoPorKG);
    System.out.println("Capacidad máxima de transporte en Kg: " + this.capacidadMaximaKg);
    System.out.println("Uso Ideal: " + this.usoIdeal);
  }

  /**
     * Implementación (Override) del método abstracto 'generaRep()'.
     * Lógica polimórfica específica para CarroChico.
     */
  @Override
  public String generaRep() {
    // Uso de la sobrecarga del operador '+' (Concatenación) 
    return "--- Reporte: " + this.tipoTransporte + " ---\n" +
    "Costo por KM: " + this.costoPorKM + "\n" +
    "Costo por KG: " + this.costoPorKG + "\n" +
    "Capacidad: " + this.capacidadMaximaKg + " Kg\n" +
    "Uso Ideal: " + this.usoIdeal + "\n";
  }

  /**
     * Implementación (Override) del método abstracto base 'calcularEnvio()'.
     */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG) {
    // Uso de la sobrecarga del operador '+' (Suma aritmética) 
    return (this.costoPorKM * distanciaKM) + (this.costoPorKG * pesoKG);
  }

  /**
     * Implementación (Override) del método abstracto SOBRECARGADO
     * 'calcularEnvio()'.
     */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG, double costoSeguro) {
    return this.calcularEnvio(distanciaKM, pesoKG) + costoSeguro;
  }
}

/**
 * =============================================================================
 * Clase RapidCity (Clase Principal)
 * =============================================================================
 * 5. Programa el código en Java... mediante una sola clase que contenga el método principal (main).
 *
 * Esta es la única clase 'public' del archivo y contiene el punto de
 * entrada 'main' que se encarga de controlar el menú y la
 * interacción con el usuario.
 */
public class RapidCity {

  // --- ÁMBITO DE CLASE (STATIC) ---
  // Estas variables son 'static' para que el método 'main' (que es static)
  // pueda acceder a ellas. Pertenecen a la CLASE 'RapidCity'.

  /**
     * Objeto Scanner que usare en todos los métodos de esta clase.
     * Lo declarare 'static' para no tener que crearlo varias veces.
     */
  private static Scanner scanner = new Scanner(System.in);

  /**
     * SOLUCIÓN SIN ArrayList: Flota Fija.
     * Creo las instancias de los vehículos aquí, como 'static'.
     * 'moto' y 'carro' existiran durante toda la vida del programa.
     *
     * DECLARACIÓN POLIMÓRFICA:
     * Declaro las variables con el tipo de la SUPERCLASE ('Vehiculo')
     * pero las instancio con el constructor de la SUBCLASE.
     * Vehiculo moto = new Motocicleta();
     */
  private static Vehiculo vehiculoMoto = new Motocicleta();
  private static Vehiculo vehiculoCarro = new CarroChico();

  /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de línea de comandos (no las usare).
     */
  public static void main(String[] args) {

    // --- ÁMBITO DE MÉTODO (main) ---
    // La variable 'salir' solo existe dentro del método 'main'.
    boolean salir = false;

    /*
         * ESTRUCTURA DE CONTROL: do-while
         * Elijo 'do-while' porque es la estructura perfecta para un menú:
         * 1. 'do' (Hacer): Muestra el menú al menos una vez.
         * 2. 'while' (Mientras): Repite el menú mientras la opción de salir
         * no sea seleccionada.
         */
    do {
      System.out.println("\n--- RapidCity: Gestión de Flota ---");
      System.out.println("1. Registrar Transporte");
      System.out.println("2. Genera Reporte");
      System.out.println("3. Calcular envío");
      System.out.println("4. Salir");
      System.out.print("Seleccione una opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine(); // Limpieza de buffer (ya lo eh explicado antes)

      /*
             * ESTRUCTURA DE CONTROL: switch
             * Uso'switch' para manejar la 'opcion' del menú.
             * Es más limpio que una serie de 'if-else'.
             */
      switch (opcion) {
        case 1:
        registrarTransporte();
        break;
        case 2:
        generarReporte();
        break;
        case 3:
        calcularEnvio();
        break;
        case 4:
        salir = true; // Cambio la variable de control del 'do-while'
        System.out.println("Saliendo del sistema...");
        break;
        default:
        System.out.println("Opción no válida.");
      }
    } while (salir == false); // El ciclo continúa mientras 'salir' sea falso

    // --- CIERRE DE RECURSOS ---
    scanner.close();
  }

  /**
     * Método para la Opción 1: Registrar Transporte.
     * En esta simulación, "registrar" significa mostrar los
     * datos fijos del vehículo seleccionado, como en la captura
     * del caso de estudio.
     */
  private static void registrarTransporte() {
    System.out.println("\n--- Registrar Transporte ---");

    // Uso el método auxiliar para obtener el objeto (moto o carro)
    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Seleccione el tipo a registrar:");

    if (vehiculoSeleccionado != null) {
      /*
             * USO DE INTERFAZ:
             * Para llamar a 'registrarTransporte()', necesito asegurarme de que el objeto 
             * es de tipo 'IRegistrable'. Hago un "cast" explícito.
             */
      IRegistrable registro = (IRegistrable) vehiculoSeleccionado;
      registro.registrarTransporte();
    }
  }

  /**
     * Método para la Opción 2: Generar Reporte.
     */
  private static void generarReporte() {
    System.out.println("\n--- Generar Reporte ---");

    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Selecciona un tipo de transporte:");

    if (vehiculoSeleccionado != null) {
      /*
             * ¡AQUI OCURRE EL POLIMORFISMO! 
             * No necesito 'if (vehiculo es Moto)'...
             * Simplemente llamo a 'generaRep()'.
             * Java (en tiempo de ejecución) sabe qué objeto es
             * ('vehiculoMoto' o 'vehiculoCarro')
             * y ejecuta la versión correcta del método.
             */
      System.out.println(vehiculoSeleccionado.generaRep());
    }
  }

  /**
     * Método para la Opción 3: Calcular Envío.
     */
  private static void calcularEnvio() {
    System.out.println("\n--- Calcular Envío ---");

    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Selecciona un tipo de transporte:");

    if (vehiculoSeleccionado != null) {
      System.out.print("Introduzca la distancia al punto de destino en KM: ");
      double distancia = scanner.nextDouble();

      System.out.print("Introduzca el peso del objeto o bien a transportar en KG: ");
      double peso = scanner.nextDouble();
      scanner.nextLine(); // Consumir newline

      // Demostración de SOBRECARGA DE MÉTODOS
      System.out.print("¿Desea añadir seguro por $50? (si/no): ");
      String conSeguro = scanner.nextLine();

      double costoTotal;
      if (conSeguro.equalsIgnoreCase("si")) {
        // Java sabe que debe llamar a la versión con 3 parámetros
        costoTotal = vehiculoSeleccionado.calcularEnvio(distancia, peso, 50.0);
      } else {
        // Java sabe que debe llamar a la versión con 2 parámetros
        costoTotal = vehiculoSeleccionado.calcularEnvio(distancia, peso);
      }

      // Doy formato a la salida como en el caso de estudio
      System.out.println("El costo por el envío seria de:");
      System.out.println(String.format("%.0f pesos", costoTotal));
    }
  }

  /**
     * Método auxiliar para seleccionar un vehículo.
     * Evita repetir código en los otros métodos.
     * DEVUELVE un objeto de tipo 'Vehiculo'.
     */
  private static Vehiculo seleccionarVehiculo(String mensaje) {
    System.out.println(mensaje);
    System.out.println("1: " + vehiculoMoto.getTipoTransporte());
    System.out.println("2: " + vehiculoCarro.getTipoTransporte());
    System.out.print("Seleccione: ");

    int seleccion = scanner.nextInt();
    scanner.nextLine(); // Consumir newline

    if (seleccion == 1) {
      return vehiculoMoto; // Devuelve la instancia estática de Motocicleta
    } else if (seleccion == 2) {
      return vehiculoCarro; // Devuelve la instancia estática de CarroChico
    } else {
      System.out.println("Selección no válida.");
      return null;
    }
  }
}
