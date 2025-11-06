/*
 * IMPORTACIONES NECESARIAS
 * Importo solo 'Scanner'.
 *
 * NOTA DE ARQUITECTURA:
 * Para esta actividad 3, sigo la misma logica de la Actividad 2.
 * NO USARE ArrayList.
 *
 * Se simulara el ALMACENAMIENTO, usare una
 * variable estatica 'reservacionActiva' para guardar la
 * unica reservacion que el sistema procesara.
 *
 *
 * g. Secciones documentadas 
 * h. Programa indentado (Importante!!! mi identacion es personalinada a 2 espacios, trabajo con nvim,
 * desconozco su comportamiento en otros editores o IDEs pero creo que es legible en todos lados)
 */
package com.scysco.dpo1u3a3;
import java.util.Scanner;

/**
 * =============================================================================
 * Interfaz IOperacionesReserva
 * =============================================================================
 * d. Interfaces 
 *
 * Defino esta interfaz como un "contrato" 100% abstracto.
 * Me sirve para obligar a mis clases de paquetes
 * a que implementen estos metodos si o si.
 */
interface IOperacionesReserva {
  // Metodo para guardar los datos del usuario en el objeto
  void realizarReservacion(String cliente, int personas, int dias, String fecha);

  /*
     * e. Sobrecarga de metodos u operadores 
     *
     * Defino dos metodos con el mismo nombre 'calcularAnticipo'
     * pero con diferente firma (diferentes parametros).
     * Esto es Sobrecarga (Overload), y se resuelve en tiempo
     * de compilacion.
     */
  double calcularAnticipo();
  double calcularAnticipo(String metodoPago); // Metodo sobrecargado

  // Metodo para la logica de cancelacion
  void cancelarReservacion(String motivo);
}

/**
 * =============================================================================
 * Clase Abstracta Viaje
 * =============================================================================
 * a. Superclase 
 * c. Clases y metodos abstractos... 
 *
 * Creo esta clase 'Viaje' como mi plantilla base (SUPERCLASE).
 * La declaro 'abstract' porque no quiero que nadie pueda crear
 * un objeto "Viaje" generico. Solo se podran crear sus subclases.
 * Contiene tanto propiedades como metodos.
 */
abstract class Viaje {

  // --- Atributos Protegidos ---
  // Uso 'protected' para que estos atributos sean heredados
  // y accesibles por las subclases (TodoIncluido, Personalizado).
  protected String cliente;
  protected int personas;
  protected int dias;
  protected String fecha;
  protected String destino;
  protected double precioBase; // El precio diario por persona
  protected int numeroReserva;

  /**
     * Constructor de la SUPERCLASE.
     * Obliga a las subclases a darme el precio y el destino
     * cuando me hereden (usando 'super()').
     */
  public Viaje(double precioBase, String destino) {
    this.precioBase = precioBase;
    this.destino = destino;
  }

  // --- Metodos Abstractos ---
  // c. Clases y metodos abstractos... 
  // Obligo a las subclases a definir estos metodos
  public abstract String getTipoPaquete();

  // --- Metodos Concretos ---
  // Metodos 'getter' para obtener valores encapsulados
  public int getNumeroReserva() {
    return this.numeroReserva;
  }

  /**
     * c. ...metodos finales 
     *
     * Declaro este metodo como 'final'.
     * Esto significa que es una operacion "protegida".
     * NINGUNA subclase podra sobreescribir o modificar
     * la logica de como se imprime el ticket.
     *
     * Este metodo es concreto (no abstracto) y sera heredado
     * tal cual por todas las subclases.
     */
  public final void mostrarTicket(double anticipo, String metodoPago) {
    System.out.println("******************************");
    System.out.println("*****Ticket de Reservacion*****");
    System.out.println("******************************");
    System.out.println("Numero de Reservacion: " + this.numeroReserva);
    System.out.println("Nombre del Cliente: " + this.cliente);
    System.out.println("Destino: " + this.destino + " (" + this.getTipoPaquete() + ")");
    System.out.println("Numero de Personas: " + this.personas);
    System.out.println("Numero de Dias: " + this.dias);
    System.out.println("Fecha de Reservacion: " + this.fecha);
    System.out.println("Anticipo: $" + anticipo);
    System.out.println("Metodo de Pago: " + metodoPago);
    System.out.println("******************************");
  }
}

/**
 * =============================================================================
 * Clase TodoIncluido (Subclase)
 * =============================================================================
 * b. Subclases 
 *
 * Esta es mi primera SUBCLASE.
 * 1. 'extends Viaje': Hereda todo de 'Viaje'.
 * 2. 'implements IOperacionesReserva': "Firma el contrato" de la interfaz.
 */
class TodoIncluido extends Viaje implements IOperacionesReserva {

  /**
     * Constructor de TodoIncluido.
     * Elijo el destino "Puerto Vallarta" para que coincida con
     * la captura de pantalla del caso.
     * Le paso el precio base a la superclase.
     */
  public TodoIncluido() {
    // Llamo al constructor de 'Viaje' con los datos fijos
    super(2500.00, "Puerto Vallarta Todo Incluido");
  }

  // --- Implementacion de metodos de la Interfaz ---
  @Override
  public String getTipoPaquete() {
    return "Todo Incluido";
  }

  @Override
  public void realizarReservacion(String cliente, int personas, int dias, String fecha) {
    // A diferencia de la Act 2, aqui SI guardo los datos que el usuario me da.
    this.cliente = cliente;
    this.personas = personas;
    this.dias = dias;
    this.fecha = fecha;
  }

  @Override
  public double calcularAnticipo() {
    // Implementacion del metodo base (no usado en main)
    double total = this.precioBase * this.personas * this.dias;
    return total * 0.10; // 10% de anticipo
  }

  /*
     * e. Sobrecarga de metodos u operadores 
     */
  @Override
  public double calcularAnticipo(String metodoPago) {
    // Implementacion del metodo SOBRECARGADO
    // (Este es el que uso en 'main')
    return this.calcularAnticipo();
  }

  @Override
  public void cancelarReservacion(String motivo) {
    // Logica de cancelacion especifica para "Todo Incluido"
    double total = this.precioBase * this.personas * this.dias;
    double penalizacion = total * 0.05; // Penalizacion del 5%

    System.out.println("***** Ticket de cancelacion *****");
    System.out.println("Cancelacion realizada. Motivo: " + motivo);
    System.out.println("Penalizacion cobrada: $" + penalizacion);
    System.out.println("*********************************");
  }
}

/**
 * =============================================================================
 * Clase Personalizado (Subclase)
 * =============================================================================
 * b. Subclases 
 *
 * Esta es mi segunda SUBCLASE.
 */
class Personalizado extends Viaje implements IOperacionesReserva {

  /**
     * Constructor de Personalizado.
     * Le paso el precio y destino a la superclase.
     */
  public Personalizado() {
    super(2750.00, "Ixtapa Personalizado");
  }

  // --- Implementacion de metodos de la Interfaz ---
  @Override
  public String getTipoPaquete() {
    return "Personalizado";
  }

  @Override
  public void realizarReservacion(String cliente, int personas, int dias, String fecha) {
    this.cliente = cliente;
    this.personas = personas;
    this.dias = dias;
    this.fecha = fecha;
  }

  @Override
  public double calcularAnticipo() {
    double total = this.precioBase * this.personas * this.dias;
    return total * 0.10; // 10% de anticipo
  }

  /*
     * e. Sobrecarga de metodos u operadores 
     */
  @Override
  public double calcularAnticipo(String metodoPago) {
    // Implementacion del metodo SOBRECARGADO
    return this.calcularAnticipo();
  }

  @Override
  public void cancelarReservacion(String motivo) {
    // Logica de cancelacion especifica para "Personalizado"
    double total = this.precioBase * this.personas * this.dias;
    double penalizacion = total * 0.10; // Penalizacion del 10%

    System.out.println("***** Ticket de cancelacion *****");
    System.out.println("Cancelacion realizada. Motivo: " + motivo);
    System.out.println("Penalizacion cobrada: $" + penalizacion);
    System.out.println("*********************************");
  }
}

/**
 * =============================================================================
 * Clase AgenciaViajes (Clase Principal)
 * =============================================================================
 *
 * Esta es mi unica clase 'public', contiene el 'main' y
 * controla todo el flujo del menu
 */
public class AgenciaViajes {

  // --- Atributos Estaticos (Ambito de Clase) ---

  // Declaro el Scanner como 'static' para usarlo en todos
  // mis metodos sin tener que crearlo de nuevo.
  private static Scanner scanner = new Scanner(System.in);

  /*
     * SOLUCION SIN ARRAYLIST: Almacenamiento Simulado
     * Creo esta variable 'static' de tipo SUPERCLASE ('Viaje').
     * Donde guardare la UNICA reservacion que el programa
     * manejara a la vez. Inicia como 'null'.
     * Esta variable es la clave para mi polimorfismo y
     * para el 'try-catch'
     */
  private static Viaje reservacionActiva = null;

  // Uso un contador estatico para generar el "Numero de Reservacion"
  private static int contadorReservas = 0;


  /**
     * Metodo principal (main). Punto de entrada del programa.
     */
  public static void main(String[] args) {

    // --- Ambito de Metodo (main) ---
    boolean salir = false;

    do {
      System.out.println("\n--- Agencia de Viajes ---");
      System.out.println("--- Aqui volamos todos ---");
      System.out.println("1. Realizar Reservacion");
      System.out.println("2. Cancelar Reservacion");
      System.out.println("3. Salir");
      System.out.print("Seleccione la opcion deseada: ");

      // --- Ambito de Bloque (do-while) ---
      int opcion = -1; // Inicializo en un valor invalido

      /*
        * f. Try-Catch 
        *
        * Aunque el requisito principal de try-catch esta en la
        * Opcion 2 (cancelar), elijo aprovecharlo y usarlo aqui tambien.
        * para prevenir que el programa "truene" si el
        * usuario escribe "hola" en lugar de un numero.
      */
      try {
        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpieza de buffer
      } catch (java.util.InputMismatchException e) {
        // Si el 'try' falla, "atrapo" la excepcion aqui.
        System.out.println("\nERROR: Debe ingresar un numero (1, 2 o 3).");
        scanner.nextLine(); // Limpio el buffer del texto erroneo
      }

      /**
        * Uso 'switch' para manejar la 'opcion' del menu.
        * como dije antes es mas limpio que un 'if-else'.
        */
      switch (opcion) {
        case 1:
        metodoRealizarReservacion();
        break;
        case 2:
        metodoCancelarReservacion();
        break;
        case 3:
        salir = true; // Cambio la variable de control
        System.out.println("Saliendo del sistema...");
        break;
        default:
        // Si la opcion no es 1, 2, 3 (o -1 por el error)
        if (opcion != -1) {
          System.out.println("Opcion no valida.");
        }
        break;
      }
    } while (salir == false); // El ciclo se repite hasta que 'salir' es true

    // Cierro el scanner al final del programa.
    scanner.close();
  }

  /**
     * Metodo para la Opcion 1: Realizar Reservacion.
     */
  private static void metodoRealizarReservacion() {
    System.out.println("\n--- Destinos Disponibles: ---");
    System.out.println("1. Cancun Todo Incluido");
    System.out.println("2. Puerto Vallarta Todo Incluido");
    System.out.println("3. Ixtapa Personalizado");
    System.out.print("Seleccione el destino (numero): ");
    int destino = scanner.nextInt();
    scanner.nextLine(); // Limpieza de buffer

    // Verifico el destino
    if (destino < 1 || destino > 3) {
      System.out.println("Destino no valido.");
      return; // Salgo del metodo
    }

    // --- Captura de datos del usuario ---
    System.out.print("Nombre del Cliente: ");
    String nombreCliente = scanner.nextLine();
    System.out.print("Numero de Personas: ");
    int numPersonas = scanner.nextInt();
    scanner.nextLine(); // Limpieza
    System.out.print("Numero de Dias: ");
    int numDias = scanner.nextInt();
    scanner.nextLine(); // Limpieza
    System.out.print("Fecha de Reservacion (DD/MM/AAAA): ");
    String fechaRes = scanner.nextLine();
    System.out.print("Metodo de Pago (Transferencia/PayPal): ");
    String metodoPago = scanner.nextLine();

    // Incremento mi contador de reservas
    contadorReservas++;

    /**
      * POLIMORFISMO!!!
      * Decido que objeto crear basado en la
      * seleccion del usuario.
      * Pero el objeto lo guardo en mi variable 'reservacionActiva',
      * que es de tipo SUPERCLASE ('Viaje').
      */
    if (destino == 1 || destino == 2) {
      // Creo una instancia de TodoIncluido
      reservacionActiva = new TodoIncluido();
    } else {
      // Creo una instancia de Personalizado
      reservacionActiva = new Personalizado();
    }

    // Asigno el numero de reserva
    reservacionActiva.numeroReserva = contadorReservas;

    // Llamo a los metodos de la interfaz/superclase
    // No necesito saber si es 'TodoIncluido' o 'Personalizado'
    
    // Debo "castear" la variable al tipo de la interfaz
    // para que el compilador encuentre el metodo.
    ((IOperacionesReserva) reservacionActiva).realizarReservacion(nombreCliente, numPersonas, numDias, fechaRes);

    //e. Sobrecarga de metodos u operadores 
    double anticipo = ((IOperacionesReserva) reservacionActiva).calcularAnticipo(metodoPago);

    //c. ...metodos finales 
    reservacionActiva.mostrarTicket(anticipo, metodoPago);
  }


  /**
    * Metodo para la Opcion 2: Cancelar Reservacion.
    * f. Try-Catch 
    */
  private static void metodoCancelarReservacion() {
    System.out.println("\n--- Cancelar Reservacion ---");

    /*
         * Uso un bloque 'try' para "intentar" ejecutar
         * codigo que se que es peligroso o puede fallar.
         */
    try {
      System.out.print("Numero de Reservacion: ");
      int num = scanner.nextInt();
      scanner.nextLine(); // Limpieza de buffer

      /**
        * Aqui'reservacionActiva' podria ser 'null'
        * si el usuario ejecuta la Opcion 2 antes que la 1.
        *
        * Si 'reservacionActiva' es null, la linea
        * 'reservacionActiva.getNumeroReserva()'
        * lanzara una 'NullPointerException' y el programa
        * debera saltar directo al bloque 'catch' sin "tronar".
        */
      if (reservacionActiva.getNumeroReserva() == num) {
        System.out.print("Motivo de la cancelacion: ");
        String motivo = scanner.nextLine();
        /**
          * POLIMORFISMO!
          * No me importa si 'reservacionActiva' es 'TodoIncluido' o 'Personalizado'.
          * Solo llamo al metodo 'cancelarReservacion'
          * Java decidira en tiempo de ejecucion cual logica usar
          */
        ((IOperacionesReserva) reservacionActiva).cancelarReservacion(motivo);

        // Reinicio mi simulacion de almacenamiento
        reservacionActiva = null; 
      } else {
        System.out.println("Numero de reservacion no encontrado.");
      }
    }
    /**
      * Uso un bloque 'catch' para "atrapar" esa excepcion (NullPointerException).
      * Si el 'try' falla, el programa saltara aqui.
      */
    catch (NullPointerException e) {
      // Controlo el error y le doy al usuario un
      // mensaje en lugar de un error rojo.
      System.out.println("\nERROR: No existe ninguna reservacion activa para cancelar.");
      System.out.println("Por favor, realice una reservacion (Opcion 1) primero.");
    }
    /**
     * Tambien atrapo el error si el usuario escribe 
     * letras en lugar de un numero de reservacion.
     */
    catch (java.util.InputMismatchException e) {
      System.out.println("\nERROR: Debe ingresar un numero de reservacion valido.");
      scanner.nextLine(); // Limpio el buffer del texto erroneo
    }
  }
}
