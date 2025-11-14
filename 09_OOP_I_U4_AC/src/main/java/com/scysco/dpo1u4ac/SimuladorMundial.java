package com.scysco.dpo1u4ac;
/*
 * =============================================================================
 * IMPORTACIONES NECESARIAS
 * =============================================================================
 * Importo 'Scanner' e 'InputMismatchException' para la interaccion con el 
 * usuario y el manejo de errores.
 * Importo 'Random' para la aleatoriedad.
 *
 * NO hare uso de 'ArrayList', 'List' o 'Collections' que serian utiles para
 * este caso pero no son parte del temario de la U4, por lo que paara cumplir 
 * 100% con el temario, todo este programa gestionara los datos usando 
 * ARREGLOS DE OBJETOS (ej. Seleccion[]) y no colecciones de Java.
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/*
 * =============================================================================
 * NOTA SOBRE ESTE CODIGO
 * =============================================================================
 * Este codigo esta "sobre-comentado" intencionalmente.
 * Como esta es la actividad final que integra todas las unidades, he usado 
 * los comentarios para explicar el "por que" de cada decision de diseno.
 *
 * Mi objetivo es usar los comentarios para demostrar explicitamente
 * donde estoy aplicando cada requisito de la rubrica.
 *
 * Ademas, para cumplir con el requisito de "una sola clase que contenga el 
 * metodo principal (main)", he colocado todas mis clases (Seleccion, Partido, 
 * FaseTorneo, etc.) en este unico archivo, pero solo la clase principal 
 * 'SimuladorMundial' es 'public'.
 * =============================================================================
 */


/*
 * =============================================================================
 * CLASE 1: Seleccion
 * =============================================================================
 * Esta es mi clase de datos principal.
 * La cree para poder usar "Arreglos de Objetos" (Seleccion[]), que es mucho 
 * mas limpio que usar "arreglos paralelos".
 *
 * Esta clase es ahora simple y limpia, sin la jerarquia 'Jugador' que 
 * era innecesaria.
 */
class Seleccion {
  // Atributos de cada instancia
  String nombre;
  String confederacion;

  // Atributos para la fase de grupos
  int puntos;
  int golesFavor;
  int golesContra;

  // Constructor: El metodo que se llama al crear un objeto (con 'new')
  public Seleccion(String nombre, String confederacion) {
    this.nombre = nombre;
    this.confederacion = confederacion;
    // Inicializo los marcadores
    this.puntos = 0;
    this.golesFavor = 0;
    this.golesContra = 0;
  }

  // Metodo 'getter' para imprimir el nombre
  public String getNombreConConfederacion() {
    return this.nombre + " (" + this.confederacion + ")";
  }

  // Metodo para reiniciar los puntos para la prox. fase
  public void reiniciarEstadisticas() {
    this.puntos = 0;
    this.golesFavor = 0;
    this.golesContra = 0;
  }
}


/*
 * =============================================================================
 * CLASE 2: Partido
 * =============================================================================
 * f. Aleatoriedad
 *
 * Encapsulo toda la logica de simulacion aleatoria en esta clase.
 */
class Partido {
  // Declaro 'random' como estatico para que toda la clase comparta la instancia
  private static Random random = new Random();

  /*
   * Metodo publico y estatico.
   * g. Simulacion iterativa
   * d. Metodos con retorno
   */
  public static Seleccion simularPartido(Seleccion equipoA, Seleccion equipoB) {

    // Reinicio estadisticas para partidos de eliminatoria
    equipoA.reiniciarEstadisticas();
    equipoB.reiniciarEstadisticas();

    // Genero goles aleatorios entre 0 y 5
    int golesA = random.nextInt(6); // 0-5
    int golesB = random.nextInt(6); // 0-5

    System.out.print(equipoA.nombre + " " + golesA + " - " + golesB + " " + equipoB.nombre);

    // Actualizo los atributos de los objetos 'Seleccion'
    equipoA.golesFavor += golesA;
    equipoA.golesContra += golesB;
    equipoB.golesFavor += golesB;
    equipoB.golesContra += golesA;

    // Logica de puntos (3 victoria, 1 empate, 0 derrota)
    if (golesA > golesB) {
      equipoA.puntos += 3;
      System.out.println(" -> Gana: " + equipoA.nombre);
      return equipoA;
    } else if (golesB > golesA) {
      equipoB.puntos += 3;
      System.out.println(" -> Gana: " + equipoB.nombre);
      return equipoB;
    } else {
      // Empate
      equipoA.puntos += 1;
      equipoB.puntos += 1;
      System.out.print(" -> Empate. ");

      // Si hay empate, simulo penales (50/50) para que siempre haya un ganador 
      // en eliminatoria
      System.out.print("Definicion por penales...");
      if (random.nextBoolean()) {
        System.out.println(" Gana " + equipoA.nombre);
        return equipoA;
      } else {
        System.out.println(" Gana " + equipoB.nombre);
        return equipoB;
      }
    }
  }
}


/*
 * =============================================================================
 * JERARQUIA DE CLASES DE POO
 * =============================================================================
 * Esta es mi jerarquia de POO principal.
 * La uso para cumplir la rubrica 'a' y 'b' (Herencia/Polimorfismo)
 */

/*
 * CLASE 3: FaseTorneo (Superclase Abstracta)
 * a. Jerarquia de clases (Superclase)
 */
abstract class FaseTorneo {

  /*
   * m. Arreglos y/o listas
   */
  protected Seleccion[] equiposEnFase;

  public FaseTorneo(Seleccion[] equipos) {
    this.equiposEnFase = equipos;
  }

  /*
   * REQUISITO DE RUBRICA: b. Polimorfismo
   *
   * Este es mi metodo abstracto.
   * Obligo a mis subclases a implementarlo.
   * Devuelve un ARREGLO de Seleccion.
   */
  public abstract Seleccion[] jugarFase();
}


/*
 * CLASE 4: FaseDeGrupos (Subclase Concreta)
 * a. Jerarquia de clases (Subclase)
 */
class FaseDeGrupos extends FaseTorneo {

  public FaseDeGrupos(Seleccion[] equipos) {
    // Llama al constructor de la superclase
    super(equipos);
  }

  /*
   * b. Polimorfismo
   * Implemento el metodo abstracto con la logica de grupos.
   * d. Metodos con retorno
   */
  @Override
  public Seleccion[] jugarFase() {
    System.out.println("\n=== INICIANDO FASE DE GRUPOS ===");

    // Barajo los 48 equipos
    barajarArreglo(this.equiposEnFase);

    // El nuevo arreglo de clasificados (16 equipos)
    Seleccion[] clasificados = new Seleccion[16];
    int contadorClasificados = 0; // Mi indice para
    // 'clasificados'

    int numGrupos = 8;
    int tamanoGrupo = 6;

    /*
     * c. Estructuras de control (for)
     * Itero 8 veces (para 8 grupos)
     */
    for (int i = 0; i < numGrupos; i++) {
      System.out.println("\n--- Grupo " + (char)('A' + i) + " ---");

      // Creo el arreglo para el grupo actual
      Seleccion[] grupoActual = new Seleccion[tamanoGrupo];
      for (int j = 0; j < tamanoGrupo; j++) {
        // Copio los 6 equipos del arreglo grande
        // al arreglo del grupo
        grupoActual[j] = this.equiposEnFase[i * tamanoGrupo + j];
        // Reinicio sus estadisticas
        grupoActual[j].reiniciarEstadisticas();
      }

      // Simulo partidos (todos contra todos)
      /*
       * REQUISITO DE RUBRICA: c. Estructuras de control
       * (for anidado)
       */
      for (int j = 0; j < tamanoGrupo; j++) {
        for (int k = j + 1; k < tamanoGrupo; k++) {
          Partido.simularPartido(grupoActual[j], grupoActual[k]);
        }
      }

      // Ordeno el grupo por puntos (Uso un metodo de ordenamiento simple)
      ordenarGrupoPorPuntos(grupoActual);

      // Agrego los 2 mejores al arreglo de clasificados
      clasificados[contadorClasificados] = grupoActual[0];
      contadorClasificados++;
      clasificados[contadorClasificados] = grupoActual[1];
      contadorClasificados++;

      System.out.println("Clasificados: " + grupoActual[0].nombre + " y " + grupoActual[1].nombre);
    }

    System.out.println("\n=== CLASIFICADOS A OCTAVOS: " + clasificados.length + " EQUIPOS ===");
    return clasificados;
  }

  // Metodo auxiliar para barajar el arreglo (Algoritmo Fisher-Yates)
  // f. Aleatoriedad
  private void barajarArreglo(Seleccion[] arr) {
    Random rnd = new Random();
    for (int i = arr.length - 1; i > 0; i--) {
      int indice = rnd.nextInt(i + 1);
      // Intercambio
      Seleccion temp = arr[indice];
      arr[indice] = arr[i];
      arr[i] = temp;
    }
  }

  // Metodo auxiliar para ordenar por puntos (Bubble Sort simple)
  // e. Metodos sin retorno (void)
  private void ordenarGrupoPorPuntos(Seleccion[] grupo) {
    for (int i = 0; i < grupo.length - 1; i++) {
      for (int j = 0; j < grupo.length - i - 1; j++) {
        // Ordeno de MAYOR a MENOR puntos
        if (grupo[j].puntos < grupo[j + 1].puntos) {
          Seleccion temp = grupo[j];
          grupo[j] = grupo[j + 1];
          grupo[j + 1] = temp;
        }
      }
    }
  }
}


/*
 * CLASE 5: FaseEliminatoria (Subclase Concreta)
 * a. Jerarquia de clases (Subclase)
 *
 * Esta clase TAMBIEN hereda de 'FaseTorneo'. 
 * La usare para Octavos, Cuartos, Semifinal Y Final.
 */
class FaseEliminatoria extends FaseTorneo {

  private String nombreFase;

  public FaseEliminatoria(Seleccion[] equipos, String nombreFase) {
    // Llama al constructor de la superclase
    super(equipos);
    this.nombreFase = nombreFase;
  }

  /*
   * b. Polimorfismo
   *
   * Implemento (@Override) el MISMO metodo abstracto, pero con una 
   * logica interna totalmente diferente.
   */
  @Override
  public Seleccion[] jugarFase() {
    System.out.println("\n=== INICIANDO " + this.nombreFase.toUpperCase() + " ===");
    System.out.println(this.equiposEnFase.length + " equipos en esta fase.");

    // Creo el nuevo arreglo de ganadores (la mitad de tamano)
    Seleccion[] ganadores = new Seleccion[this.equiposEnFase.length / 2];

    /*
     * REQUISITO DE RUBRICA: c. Estructuras de control (for)
     * Recorro la lista de 2 en 2 (i += 2) para
     * crear los enfrentamientos.
     */
    for (int i = 0; i < this.equiposEnFase.length; i += 2) {
      Seleccion equipoA = this.equiposEnFase[i];
      Seleccion equipoB = this.equiposEnFase[i + 1];

      // Simulo el partido de eliminacion
      Seleccion ganador = Partido.simularPartido(equipoA, equipoB);

      // Guardo al ganador en el nuevo arreglo
      // (Uso 'i / 2' para el indice 0, 1, 2, ...)
      ganadores[i / 2] = ganador;
    }

    System.out.println("Avanzan " + ganadores.length + " equipos...");
    return ganadores;
  }
}


/*
 * =============================================================================
 * CLASE 6: SimuladorMundial (Clase Principal)
 * =============================================================================
 */
public class SimuladorMundial {

  // --- Atributos Estaticos (Globales) ---
  private static Scanner scanner = new Scanner(System.in);

  /*
   * m. Arreglos y/o listas
   * Esta es mi herramienta principal de la Unidad 4.
   * Es un ARREGLO DE OBJETOS de tamano fijo 48.
   * NO uso ArrayList.
   */
  private static Seleccion[] equiposMundial = new Seleccion[48];

  // Este contador es vital para manejar mi arreglo fijo
  // (para saber en que indice voy llenando).
  private static int contadorEquipos = 0; 


  /*
   * Metodo principal (main)
   */
  public static void main(String[] args) {
    System.out.println("== SIMULADOR MUNDIAL DE FUTBOL 2026 ==");

    // Muestro los 28 equipos fijos
    mostrarClasificados();

    // El usuario elige los 20 restantes
    seleccionarEquiposRestantes();

    // Muestro la lista final de 48
    mostrarListaFinal();

    // Empieza la simulacion
    jugarTorneo();

    // Cierro el scanner al final
    scanner.close();
  }

  /*
   * Metodo para la seccion 1 del caso de estudio.
   * e. Metodos sin retorno (void)
   *
   * Imprime los 28 equipos ya clasificados y los agrega a mi arreglo 'equiposMundial'.
   */
  private static void mostrarClasificados() {
    System.out.println("Selecciones ya clasificadas hasta octubre 2025:");

    // Uso arreglos de Strings '[]' para guardar los nombres
    String[] afc = {"Japon", "Iran", "Corea del Sur", "Australia", "Qatar", "Arabia Saudita", "Jordania", "Uzbekistan"};
    String[] caf = {"Marruecos", "Tunez", "Egipto", "Argelia", "Ghana", "Cabo Verde", "Sudafrica", "Costa de Marfil", "Senegal"};
    String[] conmebol = {"Argentina", "Brasil", "Uruguay", "Ecuador", "Colombia", "Paraguay"};
    String[] concacaf = {"Mexico", "Estados Unidos", "Canada"};
    String[] ofc = {"Nueva Zelanda"};
    String[] uefa = {"Inglaterra"};

    // Imprimo y agrego a la lista
    System.out.println("\nAFC:");
    // Uso un 'for-each' loop para mas elegancia
    for(String nombre : afc) {
      System.out.println(nombre);
      // Agrego al arreglo principal usando el contador
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "AFC");
      contadorEquipos++; // Incremento mi indice
    }

    System.out.println("\nCAF:");
    for(String nombre : caf) {
      System.out.println(nombre);
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "CAF");
      contadorEquipos++;
    }

    System.out.println("\nCONMEBOL:");
    for(String nombre : conmebol) {
      System.out.println(nombre);
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "CONMEBOL");
      contadorEquipos++;
    }

    System.out.println("\nCONCACAF:");
    for(String nombre : concacaf) {
      System.out.println(nombre);
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "CONCACAF");
      contadorEquipos++;
    }

    System.out.println("\nOFC:");
    for(String nombre : ofc) {
      System.out.println(nombre);
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "OFC");
      contadorEquipos++;
    }

    System.out.println("\nUEFA:");
    for(String nombre : uefa) {
      System.out.println(nombre);
      equiposMundial[contadorEquipos] = new Seleccion(nombre, "UEFA");
      contadorEquipos++;
    }

    System.out.println("\nTotal de equipos clasificados: " + contadorEquipos);
  }

  /*
   * Metodo para la seccion 2 del caso de estudio.
   * h. Interactividad...
   * i. ...y validacion.
   */
  private static void seleccionarEquiposRestantes() {
    System.out.println("\nAhora debes elegir las selecciones restantes para completar el Mundial 2026:");

    // Defino mis listas de candidatos
    String[] candidatosUEFA = {"Francia", "Alemania", "España", "Portugal", "Italia", "Paises Bajos", "Croacia", "Belgica", "Polonia", "Suiza", "Dinamarca", "Escocia", "Serbia", "Republica Checa", "Turquia", "Noruega", "Suecia", "Austria", "Ucrania", "Hungria"};
    String[] candidatosAFC = {"Emiratos Arabes Unidos", "Irak", "Siria", "Oman", "Vietnam", "China", "Bahrein"};
    String[] candidatosCAF = {"Nigeria", "Camerun", "Mali", "Congo", "Zambia", "Burkina Faso"};
    String[] candidatosCONMEBOL = {"Chile", "Peru", "Bolivia", "Venezuela"};
    String[] candidatosCONCACAF = {"Costa Rica", "Panama", "Jamaica", "Honduras", "El Salvador", "Haiti", "Trinidad y Tobago"};

    // Uso un metodo auxiliar para no repetir codigo
    // Le paso mi arreglo principal 'equiposMundial' y el 'contadorEquipos' para que sepa donde seguir llenando.
    elegirDeLista("UEFA", 16, candidatosUEFA);
    elegirDeLista("AFC", 1, candidatosAFC);
    elegirDeLista("CAF", 1, candidatosCAF);
    elegirDeLista("CONMEBOL", 1, candidatosCONMEBOL);
    elegirDeLista("CONCACAF", 1, candidatosCONCACAF);

    System.out.println("\n¡Seleccion completada!");
  }

  /*
   * Metodo auxiliar para la seleccion de equipos.
   * i. Manejo de excepciones y validacion.
   * e. Metodos sin retorno (void)
   */
  private static void elegirDeLista(String confederacion, int cantidad, String[] candidatos) {
    System.out.println("\nSelecciona " + cantidad + " selecciones de " + confederacion + " de la siguiente lista:");

    // Imprimo la lista de candidatos
    for (int i = 0; i < candidatos.length; i++) {
      System.out.println((i + 1) + ". " + candidatos[i]);
    }

    /*
     * c. Estructuras de control (for)
     * Lo uso para pedir 'cantidad' de equipos.
     */
    for (int i = 0; i < cantidad; i++) {
      int numeroSeleccion = -1;

      /*
       * c. Estructuras de control
       * Uso 'do-while' para validar la entrada.
       */
      do {
        System.out.print("Introduce el numero de la seleccion #" + (i + 1) + ": ");

        /*
         * i. Manejo de excepciones
         * Uso 'try-catch' para atrapar el error
         */
        try {
          numeroSeleccion = scanner.nextInt();
          scanner.nextLine(); // Limpieza

          // i. ...y validacion
          if (numeroSeleccion < 1 || numeroSeleccion > candidatos.length) {
            System.out.println("Error: Numero fuera de rango. Debe ser entre 1 y " + candidatos.length);
            numeroSeleccion = -1; // Fuerza a repetir
          }

        } catch (InputMismatchException e) {
          System.out.println("Error: Debes introducir un numero.");
          scanner.nextLine(); // Limpio el buffer
          numeroSeleccion = -1; // Fuerza a repetir
        }
      } while (numeroSeleccion == -1);

      // Agrego la seleccion al ARREGLO PRINCIPAL
      String nombreElegido = candidatos[numeroSeleccion - 1];
      equiposMundial[contadorEquipos] = new Seleccion(nombreElegido, confederacion);
      contadorEquipos++; // Incremento el indice global
    }
  }

  /*
   * Metodo para la seccion 3. Muestra la lista final.
   */
  private static void mostrarListaFinal() {
    System.out.println("\nTotal de selecciones agregadas: " + contadorEquipos); // contadorEquipos ahora vale 48
    System.out.println("Todas las selecciones del Mundial 2026:");

    /*
     * m. Arreglos y/o listas (Recorrido de un arreglo de objetos)
     */
    for(Seleccion s : equiposMundial) {
      // Uso'for-each' que ya es el estandar para este tipo de recorrido.
      System.out.println(s.getNombreConConfederacion());
    }
  }

  /*
   * Metodo para la seccion 4 y 5.
   * g. Simulacion iterativa...
   * Aqui es donde ocurre el POLIMORFISMO DE FASES.
   */
  private static void jugarTorneo() {

    // Fase de Grupos
    // Declaro mi variable con el tipo de la SUPERCLASE
    FaseTorneo fase;

    // Le asigno un objeto de la SUBCLASE
    fase = new FaseDeGrupos(equiposMundial);

    // Llamo al metodo POLIMORFICO.
    // Devuelve un ARREGLO de Seleccion[16]
    Seleccion[] octavos = fase.jugarFase();

    // Octavos de Final
    // Reutilizo mi variable 'fase' (de tipo Superclase)
    // Le asigno un objeto de la OTRA SUBCLASE
    fase = new FaseEliminatoria(octavos, "Octavos de Final");

    // Llamo al MISMO metodo 'jugarFase()',
    // pero se ejecuta la logica de 'FaseEliminatoria'.
    Seleccion[] cuartos = fase.jugarFase();

    // Cuartos de Final
    fase = new FaseEliminatoria(cuartos, "Cuartos de Final");
    Seleccion[] semifinal = fase.jugarFase();

    // Semifinal
    fase = new FaseEliminatoria(semifinal, "Semifinal");
    Seleccion[] finalistas = fase.jugarFase();

    // Final
    fase = new FaseEliminatoria(finalistas, "LA GRAN FINAL");
    Seleccion[] ganadorArr = fase.jugarFase();

    // Anuncio al campeon
    Seleccion campeon = ganadorArr[0]; // El ganador esta en la pos. 0
    System.out.println("\n=============================================");
    System.out.println("¡" + campeon.nombre.toUpperCase() + " (" + campeon.confederacion + ") ES EL CAMPEON DEL MUNDIAL 2026!");
    System.out.println("=============================================");
  }
}
