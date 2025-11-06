/*
 * IMPORTACIONES NECESARIAS
 * Importo 'LocalDateTime' y 'DateTimeFormatter' porque
 * el caso de estudio me pide registrar la fecha y hora
 * de la lectura, y la salida de ejemplo muestra un formato de fecha
 * muy peculiar que mas adelante sera necesario formatear.
 *
 * Importo 'Scanner' porque la rubrica me pide EXPLICITAMENTE  capturar 
 * "datos diferentes ingresados desde el teclado". Aunque el caso de estudio no.
 *
 * NOTA DE ARQUITECTURA:
 * Para esta Evidencia de Aprendizaje, sigo la misma logica de las Actividades pasadas.
 * NO USARE ArrayList segun la indicacion de mi asesora.
 *
 */
package com.scysco.dpo1u3ea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * =============================================================================
 * Clase Abstracta EquipoPetrolero
 * =============================================================================
 * a. Definicion de la clase abstracta, propiedades y metodos
 *
 * No creare un "equipo generico", solo creare sus subclases
 * (Bomba, Sensor, Turbina).
 */
abstract class EquipoPetrolero {

  // --- Propiedades (Atributos) ---
  // a. ...propiedades...
  // Las defino como 'protected' para que las subclases
  // las hereden y puedan usarlas.
  protected String idEquipo;
  protected String ubicacion;
  protected String ultimaLectura;

  /**
     * Constructor de la SUPERCLASE.
     * Obligo a todas las subclases a que me den un ID
     * y una Ubicacion cuando la hereden.
     */
  public EquipoPetrolero(String idEquipo, String ubicacion) {
    this.idEquipo = idEquipo;
    this.ubicacion = ubicacion;
    this.ultimaLectura = "N/A"; // Valor inicial
  }

  // --- Metodos Abstractos ---
  // a. ...y metodos (abstractos)
  // Estos son los metodos que obligo a mis subclases a implementar.

  // Debe devolver el estado actual (ej. "Apagada, Presion: 502.0 PSI")
  public abstract String obtenerEstadoActual();

  // Debe enviar un mensaje de alerta (ej. "Requiere revision urgente.")
  public abstract void enviarAlerta(String mensaje);

  // Debe devolver 'true' o 'false' si el equipo necesita mantenimiento.
  public abstract boolean requiereMantenimiento();

  // --- Metodo Comun ---
  //
  // Este metodo SI tiene implementacion y lo heredaran
  // todas las subclases tal cual.
  public void registrarLectura() {
    // Uso las clases de 'time' para obtener la fecha/hora
    // actual y lo formateo para parecerse al del caso de estudio.
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    this.ultimaLectura = now.format(formatter);

    // Imprimo la salida como en el caso de estudio.
    System.out.println("Lectura registrada para el equipo " + this.idEquipo + " en " + this.ultimaLectura);
  }

  // --- Getters y Setters ---
  // b. Implementacion de los metodos getter y setter
  public String getIdEquipo() {
    return idEquipo;
  }

  public void setIdEquipo(String idEquipo) {
    this.idEquipo = idEquipo;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getUltimaLectura() {
    return ultimaLectura;
  }

  // Importante!! No creo un setUltimaLectura, porque solo
  // se debe modificar a traves de 'registrarLectura()'.
}

/**
 * =============================================================================
 * Clase Bomba (Subclase)
 * =============================================================================
 * c. Implementacion de 3 subclases: Bomba...
 *
 * Esta es mi primera SUBCLASE. Hereda de 'EquipoPetrolero'.
 */
class Bomba extends EquipoPetrolero {

  // --- Propiedades especificas de Bomba ---
  // Uso 'private' para encapsularlas. Solo la clase
  // Bomba puede acceder a ellas.
  private double presionActual;
  private boolean encendida; 

  /**
     * Constructor de Bomba.
     * Recibe todos los datos (los de la superclase y los suyos).
     */
  public Bomba(String idEquipo, String ubicacion, double presionActual, boolean encendida) {
    // 1. Llamo al constructor de la SUPERCLASE ('super')
    // para que guarde el id y la ubicacion.
    super(idEquipo, ubicacion);

    // 2. Guardo mis propios datos.
    this.presionActual = presionActual;
    this.encendida = encendida;
  }

  // --- Implementacion de Getters/Setters ---
  public double getPresionActual() {
    return presionActual;
  }

  public void setPresionActual(double presionActual) {
    this.presionActual = presionActual;
  }

  public boolean isEncendida() {
    return encendida;
  }

  public void setEncendida(boolean encendida) {
    this.encendida = encendida;
  }

  // --- Implementacion de Metodos Abstractos (Polimorfismo) ---

  /**
     * Logica de estado para la Bomba.
     */
  @Override
  public String obtenerEstadoActual() {
    // Logica para mostrar "Apagada" en vez de "false"
    String estadoEncendido = this.encendida ? "Encendida" : "Apagada"; //operadores ternarios
    /**
     * no consisero que sea adelantarse en la unidad. Son el estandar, 
     * pero pongo un ejemplo de como se haria sin ellos:
     * String estadoEncendido;
     * if (this.encendida) {
     *    estadoEncendido = "Encendida";
     * } else {
     *    estadoEncendido = "Apagada";
     * }
     */

    // Devuelvo el string exacto del ejemplo
    return "Bomba " + this.idEquipo + ": " + estadoEncendido + ", Presion: " + this.presionActual + " PSI";
  }

  /**
     * Logica de alerta para la Bomba.
     */
  @Override
  public void enviarAlerta(String mensaje) {
    System.out.println("\nWarning (" + "Bomba " + this.idEquipo + "):");
    System.out.println(mensaje);
  }

  /**
     * Logica de mantenimiento para la Bomba.
     */
  @Override
  public boolean requiereMantenimiento() {
    // Devuelvo 'true' si se cumple la condicion del caso:
    // presion > 500 O la bomba esta apagada.
    return this.presionActual > 500 || !this.encendida;
  }
}

/**
 * =============================================================================
 * Clase SensorFlujo (Subclase)
 * =============================================================================
 * c. Implementacion de 3 subclases: ...SensorFlujo...
 *
 * Esta es mi segunda SUBCLASE.
 */
class SensorFlujo extends EquipoPetrolero {

  // --- Propiedad especifica ---
  private double flujoPorMinuto;

  /**
     * Constructor de SensorFlujo
     */
  public SensorFlujo(String idEquipo, String ubicacion, double flujoPorMinuto) {
    super(idEquipo, ubicacion);
    this.flujoPorMinuto = flujoPorMinuto;
  }

  // --- Implementacion de Getters/Setters ---
  public double getFlujoPorMinuto() {
    return flujoPorMinuto;
  }

  public void setFlujoPorMinuto(double flujoPorMinuto) {
    this.flujoPorMinuto = flujoPorMinuto;
  }

  // --- Implementacion de Metodos Abstractos ---

  @Override
  public String obtenerEstadoActual() {
    // Devuelvo el string exacto del caso de estudio
    return "Sensor de Flujo " + this.idEquipo + ": " + this.flujoPorMinuto + " l/min";
  }

  @Override
  public void enviarAlerta(String mensaje) {
    // Devuelvo el string exacto del caso de estudio
    System.out.println("\nWarning (Sensor " + this.idEquipo + "): " + mensaje);
  }

  @Override
  public boolean requiereMantenimiento() {
    // Logica de mantenimiento del caso
    return this.flujoPorMinuto == 0;
  }
}

/**
 * =============================================================================
 * Clase TurbinaGas (Subclase)
 * =============================================================================
 * c. Implementacion de 3 subclases: ...TurbinaGas
 *
 * Esta es mi tercera SUBCLASE.
 */
class TurbinaGas extends EquipoPetrolero {

  // --- Propiedad especifica ---
  private double temperaturaEntrada;

  /**
     * Constructor de TurbinaGas
     */
  public TurbinaGas(String idEquipo, String ubicacion, double temperaturaEntrada) {
    super(idEquipo, ubicacion);
    this.temperaturaEntrada = temperaturaEntrada;
  }

  // --- Implementacion de Getters/Setters ---
  public double getTemperaturaEntrada() {
    return temperaturaEntrada;
  }

  public void setTemperaturaEntrada(double temperaturaEntrada) {
    this.temperaturaEntrada = temperaturaEntrada;
  }

  // --- Implementacion de Metodos Abstractos ---

  @Override
  public String obtenerEstadoActual() {
    // Devuelvo el string (CE)
    return "Turbina de Gas " + this.idEquipo + ": " + this.temperaturaEntrada + " grados centigrados";
  }

  @Override
  public void enviarAlerta(String mensaje) {
    // Devuelvo el string (CE)
    System.out.println("\nWarning (Turbina " + this.idEquipo + "): " + mensaje);
  }

  @Override
  public boolean requiereMantenimiento() {
    /*
     * NOTA:
     * El caso de estudio dice "revision urgente si la temperatura es menor 
     * o mayor de cero", lo cual no tiene logica, ya que la salida de ejemplo 
     * muestra que para 1400.0,el estado es "normal".
     * Por lo tanto defino una regla logica que coincide con esta salida:
     * El mantenimiento se requiere si la temperatura esta fuera de un rango 
     * operativo (ej. 500 a 1500). El valor de 1400 esta DENTRO, por lo que dara 
     * 'false' (no requiere), lo que coincide con el ejemplo.
     */
    return this.temperaturaEntrada < 500 || this.temperaturaEntrada > 1500;
  }
}


/**
 * =============================================================================
 * Clase Controlador
 * =============================================================================
 * d. Implementacion de la clase Controlador...
 *
 * Esta clase se encarga de gestionar los equipos.
 */
class Controlador {

  /*
   * SOLUCION SIN ARRAYLIST:
   * La instruccion de mi asesora anula el caso de estudio y un elemento de la RUBRICA.
   * En su lugar, creo una "simulacion de coleccion fija".
   * Uso tres slots (variable), una para cada equipo.
   * Las declaro con el tipo de la SUPERCLASE.
   */
  private EquipoPetrolero slotBomba;
  private EquipoPetrolero slotSensor;
  private EquipoPetrolero slotTurbina;

  // Constructor que inicializa los slots como vacios.
  public Controlador() {
    this.slotBomba = null;
    this.slotSensor = null;
    this.slotTurbina = null;
  }

  /**
     * d. ... implementar el metodo agregarEquipo...
     *
     * Implementacion del metodo SIN ArrayList.
     * Decido donde va cada equipo usando 'instanceof' para identificar el tipo de objeto.
     */
  public void agregarEquipo(EquipoPetrolero equipo) {
    /*
         * 'instanceof' es un operador de esta unidad que
         * me permite preguntar ES-UNA-INSTANCIA-DE.
         */
    if (equipo instanceof Bomba) {
      this.slotBomba = equipo;
      System.out.println("Bomba registrada en el slot 1.");
    } else if (equipo instanceof SensorFlujo) {
      this.slotSensor = equipo;
      System.out.println("SensorFlujo registrado en el slot 2.");
    } else if (equipo instanceof TurbinaGas) {
      this.slotTurbina = equipo;
      System.out.println("TurbinaGas registrada en el slot 3.");
    }
  }

  /**
     * d. ...y monitorearEquipos()
     *
     * Implementacion de 'monitorearEquipos' SIN ArrayList.
     * No puedo usar un bucle 'for', debo llamar manualmente al polimorfismo 
     * en cada variable de forma individual.
     */
  public void monitorearEquipos() {
    System.out.println("\n--- INICIANDO MONITOREO DE EQUIPOS ---");

    // --- Bloque 1: Monitoreo de Bomba ---
    // Reviso que el slot no este vacio
    if (this.slotBomba != null) {
      /*
       * POLIMORFISMO EN ACCION!!!
       * Llamo a los metodos desde 'slotBomba' (que es de tipo 'EquipoPetrolero').
       * Java en tiempo de ejecucion sabe que debe ejecutar los metodos de la clase 'Bomba'.
       */
      System.out.println(this.slotBomba.obtenerEstadoActual());
      this.slotBomba.registrarLectura();

      // Logica de decision polimorfica
      if (this.slotBomba.requiereMantenimiento()) {
        this.slotBomba.enviarAlerta("Requiere revision urgente.");
      } else {
        this.slotBomba.enviarAlerta("Estado normal de operacion.");
      }
    }

    // --- Bloque 2: Monitoreo de Sensor ---
    if (this.slotSensor != null) {
      /*
       * POLIMORFISMO EN ACCION!!!
       * Aqui, Java ejecutara los metodos de 'SensorFlujo'.
    // REQUISITO DE RUBRICA: d. ...monitorearEquipos...
       */
      System.out.println(this.slotSensor.obtenerEstadoActual());
      this.slotSensor.registrarLectura();

      if (this.slotSensor.requiereMantenimiento()) {
        this.slotSensor.enviarAlerta("Requiere revision urgente.");
      } else {
        this.slotSensor.enviarAlerta("Estado normal de operacion.");
      }
    }

    // --- Bloque 3: Monitoreo de Turbina ---
    if (this.slotTurbina != null) {
      /*
       * POLIMORFISMO EN ACCION!!!
       * Aqui, Java ejecutara los metodos de 'TurbinaGas'.
       */
      System.out.println(this.slotTurbina.obtenerEstadoActual());
      this.slotTurbina.registrarLectura();

      if (this.slotTurbina.requiereMantenimiento()) {
        this.slotTurbina.enviarAlerta("Requiere revision urgente.");
      } else {
        this.slotTurbina.enviarAlerta("Estado normal de operacion.");
      }
    }
    System.out.println("\n--- MONITOREO FINALIZADO ---");
  }
}


/**
 * =============================================================================
 * Clase Petrover (Clase Principal)
 * =============================================================================
 *
 * Esta es mi unica clase 'public'. Contiene el 'main'.
 * Como el caso de estudio no muestra un menu, el 'main'
 * solo ejecutara el escenario de ejemplo.
 */

public class Petrover {

  /**
     * Metodo principal (main). Punto de entrada del programa.
     */
  public static void main(String[] args) {

    // Creo el Scanner 
    Scanner scanner = new Scanner(System.in);

    System.out.println("Iniciando Sistema de Monitoreo Petrover@");
    System.out.println("Por favor, ingrese los datos para los 3 equipos:");

    // Captura de datos para la Bomba ---
    // (Uso los IDs y Ubicaciones del caso de estudio)
    System.out.println("\n--- Datos BOMBA (B-0050) ---");
    System.out.print("Ingrese Presion (ej. 502.0): ");
    double presion = scanner.nextDouble();
    System.out.print("¿Esta encendida? (true/false): ");
    boolean encendida = scanner.nextBoolean();

    // Creo la instancia de la Bomba con los datos del usuario
    Bomba bomba = new Bomba("B-0050", "Plataforma 1", presion, encendida);

    // --- Captura de datos para el Sensor ---
    System.out.println("\n--- Datos SENSOR (S-0015) ---");
    System.out.print("Ingrese Flujo por Minuto (ej. 0.0): ");
    double flujo = scanner.nextDouble();

    SensorFlujo sensor = new SensorFlujo("S-0015", "Plataforma 1", flujo);

    // --- Captura de datos para la Turbina ---
    System.out.println("\n--- Datos TURBINA (T-0158) ---");
    System.out.print("Ingrese Temperatura de Entrada (ej. 1400.0): ");
    double temp = scanner.nextDouble();

    TurbinaGas turbina = new TurbinaGas("T-0158", "Plataforma 2", temp);

    // Cierro el scanner
    scanner.close();

    // Creo el Controlador.
    Controlador controlador = new Controlador();

    // Agrego los equipos al controlador.
    controlador.agregarEquipo(bomba);
    controlador.agregarEquipo(sensor);
    controlador.agregarEquipo(turbina);

    // Ejecuto el monitoreo para generar la salida.
    controlador.monitorearEquipos();

    System.out.println("Sistema finalizado.");
  }
}
