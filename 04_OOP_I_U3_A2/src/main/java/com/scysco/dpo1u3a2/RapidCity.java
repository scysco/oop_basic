/*
 * IMPORTACIONES NECESARIAS
 * Importamos solo 'Scanner'.
 *
 * NOTA IMPORTANTE DE ARQUITECTURA:
 * Esta Actividad 2 se alinea con las restricciones vistas en la Unidad 2.
 * NO usare colecciones (como ArrayList) para almacenar la "flota",
 * ya que el tema de Arreglos corresponde a la Unidad 4 como se dijo en clase.
 *
 * En su lugar, simulare una "flota" fija de dos vehiculos
 * pre-instanciados como variables estaticas, lo cual me permitira
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
 * e. Utilizar interfaces para anadir comportamientos adicionales.
 *
 * JUSTIFICACION DE DISENO:
 * Usare una interfaz para definir el comportamiento 'registrarTransporte()'.
 * Esto desacopla la logica.
 * 'Vehiculo' no necesita saber como registrarse, solo las subclases que
 * "firmen" este contrato (con 'implements') estaran obligadas a definir
 * este metodo.
 */

interface IRegistrable {
  /**
   * Metodo abstracto que obliga a las clases implementadoras
   * a definir como se "registran" (en este caso, como imprimen
   * sus datos iniciales).
   */
  void registrarTransporte();
}

/**
 * =============================================================================
 * Clase Abstracta Vehiculo
 * =============================================================================
 * a. Jerarquia de clases (uso de herencia)
 *
 * JUSTIFICACION DE DISENO:
 * Este define la plantilla, atributos y comportamientos comunes. Todo lo
 * definido aqui sera heredado por las subclases, maximizando la
 * reutilizacion de codigo, que es el objetivo de la herencia.
 */
abstract class Vehiculo {

  // --- AMBITO DE CLASE (PROTECTED) ---
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
   * Este constructor sera invocado por las subclases
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

  // --- METODOS ABSTRACTOS ---
  // a. Jerarquia de clases (implica metodos abstractos para el polimorfismo).
  //
  // Un metodo abstracto es una firma sin implementacion
  // (sin llaves {}). Es una ORDEN que obliga a las subclases a sobreescribirlo
  // (Override)
  // y definir su propia logica.

  /**
   * b. Polimorfismo (a traves de resultados polimorficos)
   *
   * Este es un metodo polimorfico.
   * Cuando llamemos a 'generaRep()' desde una variable de tipo 'Vehiculo',
   * Java decidira en tiempo de ejecucion (enlace dinamico) que version
   * ejecutar (la de Motocicleta o la de CarroChico).
   */
  public abstract String generaRep();

  /**
   * Metodo abstracto para el calculo de envio base.
   */
  public abstract double calcularEnvio(double distanciaKM, double pesoKG);

  /**
   * c. Sobrecarga de metodos
   *
   * Este es un metodo SOBRECARGADO
   * Se llama igual que el anterior ('calcularEnvio'), pero tiene
   * una firma de parametros diferente (recibe un 'costoSeguro' adicional).
   * Java sabe cual llamar en tiempo de compilacion (enlace estatico)
   * basandose en los argumentos que le pasemos.
   */
  public abstract double calcularEnvio(double distanciaKM, double pesoKG, double costoSeguro);

  // --- METODOS CONCRETOS (Getters) ---
  // Metodo simple (no abstracto) que sera heredado tal cual.
  public String getTipoTransporte() {
    return tipoTransporte;
  }
}

/**
 * =============================================================================
 * Clase Motocicleta (Subclase)
 * =============================================================================
 * a. Jerarquia de clases (uso de herencia)
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
   * Implementacion (Override) del metodo de la interfaz IRegistrable.
   * Aqui defino"como" se registra una Motocicleta,
   * cumpliendo el contrato de la interfaz.
   */
  @Override
  public void registrarTransporte() {
    // Imprimo los datos que esta clase heredo de 'Vehiculo'
    System.out.println("Tipo de Transporte: " + this.tipoTransporte);
    System.out.println("Costo por KM: " + this.costoPorKM);
    System.out.println("Costo por KG transportado: " + this.costoPorKG);
    System.out.println("Capacidad maxima de transporte en Kg: " + this.capacidadMaximaKg);
    System.out.println("Uso Ideal: " + this.usoIdeal);
  }

  /**
   * Implementacion (Override) del metodo abstracto 'generaRep()'.
   * Esta es la logica polimorfica especifica para Motocicleta.
   * La anotacion '@Override' le indica al compilador que
   * estamos sobreescribiendo intencionalmente un metodo heredado.
   */
  @Override
  public String generaRep() {
    /*
     * d. Sobrecarga de operadores
     *
     * JUSTIFICACION: El contenido de la unidad indica
     * que en Java no podemos crear nuevos operadores sobrecargados,
     * pero SI podemos usar la sobrecarga existente del operador '+'.
     *
     * Aqui, el operador '+' NO ESTA SUMANDO, esta CONCATENANDO
     * Strings. Este sera mi ejemplo de sobrecarga de operador que pide
     * la rubrica.
     */
    return "--- Reporte: " + this.tipoTransporte + " ---\n" +
        "Costo por KM: " + this.costoPorKM + "\n" +
        "Costo por KG: " + this.costoPorKG + "\n" +
        "Capacidad: " + this.capacidadMaximaKg + " Kg\n" +
        "Uso Ideal: " + this.usoIdeal + "\n";
  }

  /**
   * Implementacion (Override) del metodo abstracto base 'calcularEnvio()'.
   */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG) {
    /*
     * d. Sobrecarga de operadores.
     * * JUSTIFICACION: A diferencia del metodo anterior,
     * aqui el operador '+' SI ESTA SUMANDO valores numericos.
     * El compilador de Java decidira que accion realizar (sumar o concatenar)
     * basandose en los tipos de dato.
     */
    return (this.costoPorKM * distanciaKM) + (this.costoPorKG * pesoKG);
  }

  /**
   * Implementacion (Override) del metodo abstracto SOBRECARGADO
   * 'calcularEnvio()'.
   */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG, double costoSeguro) {
    // Reutilizacion de la logica del metodo base (con 2 parametros)
    // y le sumo el seguro.
    return this.calcularEnvio(distanciaKM, pesoKG) + costoSeguro;
  }
}

/**
 * =============================================================================
 * Clase CarroChico (Subclase)
 * =============================================================================
 * a. Jerarquia de clases (uso de herencia)
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
   * Implementacion (Override) del metodo de la interfaz IRegistrable.
   */
  @Override
  public void registrarTransporte() {
    System.out.println("Tipo de Transporte: " + this.tipoTransporte);
    System.out.println("Costo por KM: " + this.costoPorKM);
    System.out.println("Costo por KG transportado: " + this.costoPorKG);
    System.out.println("Capacidad maxima de transporte en Kg: " + this.capacidadMaximaKg);
    System.out.println("Uso Ideal: " + this.usoIdeal);
  }

  /**
   * Implementacion (Override) del metodo abstracto 'generaRep()'.
   * Logica polimorfica especifica para CarroChico.
   */
  @Override
  public String generaRep() {
    // Uso de la sobrecarga del operador '+' (Concatenacion)
    return "--- Reporte: " + this.tipoTransporte + " ---\n" +
        "Costo por KM: " + this.costoPorKM + "\n" +
        "Costo por KG: " + this.costoPorKG + "\n" +
        "Capacidad: " + this.capacidadMaximaKg + " Kg\n" +
        "Uso Ideal: " + this.usoIdeal + "\n";
  }

  /**
   * Implementacion (Override) del metodo abstracto base 'calcularEnvio()'.
   */
  @Override
  public double calcularEnvio(double distanciaKM, double pesoKG) {
    // Uso de la sobrecarga del operador '+' (Suma aritmetica)
    return (this.costoPorKM * distanciaKM) + (this.costoPorKG * pesoKG);
  }

  /**
   * Implementacion (Override) del metodo abstracto SOBRECARGADO
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
 * 5. Programa el codigo en Java... mediante una sola clase que contenga el
 * metodo principal (main).
 *
 * Esta es la unica clase 'public' del archivo y contiene el punto de
 * entrada 'main' que se encarga de controlar el menu y la
 * interaccion con el usuario.
 */
public class RapidCity {

  // --- AMBITO DE CLASE (STATIC) ---
  // Estas variables son 'static' para que el metodo 'main' (que es static)
  // pueda acceder a ellas. Pertenecen a la CLASE 'RapidCity'.

  /**
   * Objeto Scanner que usare en todos los metodos de esta clase.
   * Lo declarare 'static' para no tener que crearlo varias veces.
   */
  private static Scanner scanner = new Scanner(System.in);

  /**
   * SOLUCION SIN ArrayList: Flota Fija.
   * Creo las instancias de los vehiculos aqui, como 'static'.
   * 'moto' y 'carro' existiran durante toda la vida del programa.
   *
   * DECLARACION POLIMORFICA:
   * Declaro las variables con el tipo de la SUPERCLASE ('Vehiculo')
   * pero las instancio con el constructor de la SUBCLASE.
   * Vehiculo moto = new Motocicleta();
   */
  private static Vehiculo vehiculoMoto = new Motocicleta();
  private static Vehiculo vehiculoCarro = new CarroChico();

  /**
   * Punto de entrada del programa.
   *
   * @param args Argumentos de linea de comandos (no las usare).
   */
  public static void main(String[] args) {

    // --- AMBITO DE METODO (main) ---
    // La variable 'salir' solo existe dentro del metodo 'main'.
    boolean salir = false;

    /*
     * ESTRUCTURA DE CONTROL: do-while
     * Elijo 'do-while' porque es la estructura perfecta para un menu:
     * 1. 'do' (Hacer): Muestra el menu al menos una vez.
     * 2. 'while' (Mientras): Repite el menu mientras la opcion de salir
     * no sea seleccionada.
     */
    do {
      System.out.println("\n--- RapidCity: Gestion de Flota ---");
      System.out.println("1. Registrar Transporte");
      System.out.println("2. Genera Reporte");
      System.out.println("3. Calcular envio");
      System.out.println("4. Salir");
      System.out.print("Seleccione una opcion: ");

      int opcion = scanner.nextInt();
      scanner.nextLine(); // Limpieza de buffer (ya lo eh explicado antes)

      /*
       * ESTRUCTURA DE CONTROL: switch
       * Uso'switch' para manejar la 'opcion' del menu.
       * Es mas limpio que una serie de 'if-else'.
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
          System.out.println("Opcion no valida.");
      }
    } while (salir == false); // El ciclo continua mientras 'salir' sea falso

    // --- CIERRE DE RECURSOS ---
    scanner.close();
  }

  /**
   * Metodo para la Opcion 1: Registrar Transporte.
   * En esta simulacion, "registrar" significa mostrar los
   * datos fijos del vehiculo seleccionado, como en la captura
   * del caso de estudio.
   */
  private static void registrarTransporte() {
    System.out.println("\n--- Registrar Transporte ---");

    // Uso el metodo auxiliar para obtener el objeto (moto o carro)
    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Seleccione el tipo a registrar:");

    if (vehiculoSeleccionado != null) {
      /*
       * USO DE INTERFAZ:
       * Para llamar a 'registrarTransporte()', necesito asegurarme de que el objeto
       * es de tipo 'IRegistrable'. Hago un "cast" explicito.
       */
      IRegistrable registro = (IRegistrable) vehiculoSeleccionado;
      registro.registrarTransporte();
    }
  }

  /**
   * Metodo para la Opcion 2: Generar Reporte.
   */
  private static void generarReporte() {
    System.out.println("\n--- Generar Reporte ---");

    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Selecciona un tipo de transporte:");

    if (vehiculoSeleccionado != null) {
      /*
       * !AQUI OCURRE EL POLIMORFISMO!
       * No necesito 'if (vehiculo es Moto)'...
       * Simplemente llamo a 'generaRep()'.
       * Java (en tiempo de ejecucion) sabe que objeto es
       * ('vehiculoMoto' o 'vehiculoCarro')
       * y ejecuta la version correcta del metodo.
       */
      System.out.println(vehiculoSeleccionado.generaRep());
    }
  }

  /**
   * Metodo para la Opcion 3: Calcular Envio.
   */
  private static void calcularEnvio() {
    System.out.println("\n--- Calcular Envio ---");

    Vehiculo vehiculoSeleccionado = seleccionarVehiculo("Selecciona un tipo de transporte:");

    if (vehiculoSeleccionado != null) {
      System.out.print("Introduzca la distancia al punto de destino en KM: ");
      double distancia = scanner.nextDouble();

      System.out.print("Introduzca el peso del objeto o bien a transportar en KG: ");
      double peso = scanner.nextDouble();
      scanner.nextLine(); // Consumir newline

      // Demostracion de SOBRECARGA DE METODOS
      System.out.print("Desea anadir seguro por $50? (si/no): ");
      String conSeguro = scanner.nextLine();

      double costoTotal;
      if (conSeguro.equalsIgnoreCase("si")) {
        // Java sabe que debe llamar a la version con 3 parametros
        costoTotal = vehiculoSeleccionado.calcularEnvio(distancia, peso, 50.0);
      } else {
        // Java sabe que debe llamar a la version con 2 parametros
        costoTotal = vehiculoSeleccionado.calcularEnvio(distancia, peso);
      }

      // Doy formato a la salida como en el caso de estudio
      System.out.println("El costo por el envio seria de:");
      System.out.println(String.format("%.0f pesos", costoTotal));
    }
  }

  /**
   * Metodo auxiliar para seleccionar un vehiculo.
   * Evita repetir codigo en los otros metodos.
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
      return vehiculoMoto; // Devuelve la instancia estatica de Motocicleta
    } else if (seleccion == 2) {
      return vehiculoCarro; // Devuelve la instancia estatica de CarroChico
    } else {
      System.out.println("Seleccion no valida.");
      return null;
    }
  }
}
