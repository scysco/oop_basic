package com.scysco.dpo1u4a1;

/*
 * IMPORTACIONES 
 * Importo 'Scanner' para capturar datos desde teclado.
 */
import java.util.Scanner;

/**
 * =============================================================================
 * Clase GestionInventario
 * =============================================================================
 * Esta sera la unica clase que usare para toda la actividad.
 */
public class GestionInventario {

  // --- Atributos Estaticos (Globales a la clase) ---

  // Declaro mi 'Scanner' como estatico para poder usarlo
  // en todos mis metodos sin tener que pasarlo como parametro.
  private static Scanner scanner = new Scanner(System.in);

  /*
   * a. Declara tres arreglos unidimensionales...
   *
   * Estos son mis arreglos "paralelos".
   * La posicion 'i' en los tres arreglos corresponde al mismo producto
   */
  private static String[] productos;
  private static int[] existencias;
  private static double[] precios;

  /*
   * b. Define una constante para identificar productos agotados.
   */
  private static final int LIMITE_AGOTADO = 0;

  // Defino un tamano maximo para mis arreglos.
  private static final int TAMANO_MAX_INVENTARIO = 5;

  // Esta variable la usare como indice para saber donde registrar el proximo
  // producto.
  private static int numProductosRegistrados = 0;

  // Esta variable la usare para el requisito de
  // "Calculo del ingreso total por ventas".
  private static double ingresoTotalVentas = 0.0;

  /**
   * Metodo principal (main).
   * Controla el flujo del programa y el menu.
   */
  public static void main(String[] args) {

    // --- Inicializacion de los Arreglos ---
    // Antes de usarlos, debo "construir" los arreglos
    // Los inicializo con el tamano maximo que defini.
    productos = new String[TAMANO_MAX_INVENTARIO];
    existencias = new int[TAMANO_MAX_INVENTARIO];
    precios = new double[TAMANO_MAX_INVENTARIO];

    // Variable de control para mi menu
    boolean salir = false;

    /*
     * d. Uso de estructuras de control...
     * Elijo 'do-while' porque es perfecto para un menu.
     * ya que me aseguro que se ejecute al menos una vez.
     */
    do {
      // Imprimo el menu como el del caso de estudio
      System.out.println("\n=== Gestion de Inventario TechNova ===");
      System.out.println("1. Registrar producto");
      System.out.println("2. Mostrar inventario completo");
      System.out.println("3. Vender producto");
      System.out.println("4. Buscar producto por nombre");
      System.out.println("5. Generar reporte de inventario");
      System.out.println("6. Salir");
      System.out.print("Opcion: ");

      int opcion = scanner.nextInt();
      scanner.nextLine(); // Limpieza de buffer

      /*
       * Uso 'switch' para manejar la opcion del usuario.
       * Es mas limpio que usar multiples 'if'.
       */
      switch (opcion) {
        case 1:
          registrarProducto();
          break;
        case 2:
          mostrarInventario();
          break;
        case 3:
          venderProducto();
          break;
        case 4:
          buscarProducto();
          break;
        case 5:
          generarReporte();
          break;
        case 6:
          salir = true;
          break;
        default:
          System.out.println("Opcion no valida. Intente de nuevo.");
      }

    } while (!salir);

    System.out.println("Saliendo del sistema...");
    scanner.close();
  }

  /**
   * f. Captura datos desde teclado...
   * Metodo para la Opcion 1: Registrar un nuevo producto.
   */
  private static void registrarProducto() {
    System.out.println("\n--- 1. Registrar Producto ---");

    /*
     * Verifico si mi inventario (arreglo) ya esta lleno.
     */
    if (numProductosRegistrados >= TAMANO_MAX_INVENTARIO) {
      System.out.println("ERROR: El inventario esta lleno. No se pueden agregar mas productos.");
      return; // Salgo del metodo
    }

    // Si hay espacio, pido los datos
    System.out.print("Ingrese el nombre del producto: ");
    String nombre = scanner.nextLine();

    System.out.print("Ingrese las existencias iniciales: ");
    int stock = scanner.nextInt();

    System.out.print("Ingrese el precio unitario: ");
    double precio = scanner.nextDouble();
    scanner.nextLine(); // Limpieza de buffer

    /*
     * OPERACION CON ARREGLOS
     * Guardo los datos en los arreglos paralelos, usando 'numProductosRegistrados'
     * como el indice actual.
     */
    productos[numProductosRegistrados] = nombre;
    existencias[numProductosRegistrados] = stock;
    precios[numProductosRegistrados] = precio;

    // Incremento mi indice para el proximo registro
    numProductosRegistrados++;

    System.out.println("Producto '" + nombre + "' registrado exitosamente.");
  }

  /**
   * Metodo para la Opcion 2: Mostrar el inventario.
   * e. Emplea ciclos para recorrer...
   * i. Verifica que la salida muestre...
   */
  private static void mostrarInventario() {
    System.out.println("\n--- 2. Inventario Completo ---");
    System.out.println("       Producto      | Existencias | Precio Unitario | Valor Total ");
    System.out.println("------------------------------------------------------------------");

    /*
     * Uso un ciclo 'for' para "recorrer" el arreglo
     * desde la posicion 0 hasta el ultimo producto que haya registrado
     * ('numProductosRegistrados').
     */
    for (int i = 0; i < numProductosRegistrados; i++) {
      // c. Implementa metodos que...calculen valores totales.
      double valorTotal = existencias[i] * precios[i];

      // Imprimo la informacion de los 3 arreglos en la misma linea.
      System.out.printf(
          "%-20s | %11d | $%14.2f | $%11.2f\n",
          productos[i],
          existencias[i],
          precios[i],
          valorTotal);
    }

    if (numProductosRegistrados == 0) {
      System.out.println("El inventario esta vacio.");
    }
  }

  /**
   * Metodo para la Opcion 3: Vender un producto.
   * c. Implementa metodos que actualicen datos...
   */
  private static void venderProducto() {
    System.out.println("\n--- 3. Vender Producto ---");
    System.out.print("Ingrese el nombre del producto vendido: ");
    String nombre = scanner.nextLine();

    // Busco el producto
    int indice = buscarIndiceProducto(nombre);

    // Verifico si existe
    if (indice == -1) {
      System.out.println("Producto no encontrado: " + nombre);
      return;
    }

    // Si existe, pido la cantidad
    System.out.print("Ingrese la cantidad vendida: ");
    int cantidadVendida = scanner.nextInt();
    scanner.nextLine(); // Limpieza

    /*
     * d. Uso de estructuras de control para validar disponibilidad...
     *
     * Uso 'if' para validar las existencias.
     */
    if (cantidadVendida <= 0) {
      System.out.println("Cantidad no valida.");
    } else if (cantidadVendida > existencias[indice]) {
      System.out.println("Venta no registrada. Solo hay " + existencias[indice] + " unidades.");
    } else {
      // 4. Actualizo los datos
      existencias[indice] = existencias[indice] - cantidadVendida;
      ingresoTotalVentas = ingresoTotalVentas + (cantidadVendida * precios[indice]);

      System.out.println("Venta registrada. Nueva cantidad de " + productos[indice] + ": " + existencias[indice]);
    }
  }

  /**
   * Metodo para la Opcion 4: Buscar un producto.
   * e. Emplea ciclos para recorrer...
   */
  private static void buscarProducto() {
    System.out.println("\n--- 4. Buscar Producto por Nombre ---");
    System.out.print("Ingrese el nombre del producto a buscar: ");
    String nombre = scanner.nextLine();

    // Uso mi metodo auxiliar 'buscarIndiceProducto'
    int indice = buscarIndiceProducto(nombre);

    if (indice != -1) {
      // Si lo encontre, muestro sus datos
      System.out.println("Producto encontrado:");
      System.out.println(
          "Producto: " + productos[indice] +
              " | Precio: $" + precios[indice] +
              " | Existencias: " + existencias[indice]);
    } else {
      System.out.println("Producto no encontrado: " + nombre);
    }
  }

  /**
   * Metodo para la Opcion 5: Generar reportes.
   * El caso de estudio pide varios reportes
   * los implemento todos aqui.
   */
  private static void generarReporte() {
    System.out.println("\n--- 5. Reporte de Inventario ---");

    if (numProductosRegistrados == 0) {
      System.out.println("No hay productos registrados para generar un reporte.");
      return;
    }

    // --- Reporte 1: Productos Agotados ---
    System.out.println("\n1. Productos Agotados:");
    boolean hayAgotados = false;
    for (int i = 0; i < numProductosRegistrados; i++) {
      // Uso la constante que defini
      if (existencias[i] == LIMITE_AGOTADO) {
        System.out.println("- " + productos[i] + " (Estado: AGOTADO)");
        hayAgotados = true;
      }
    }
    if (!hayAgotados) {
      System.out.println("No hay productos agotados.");
    }

    // --- Reporte 2: Mayor y Menor Disponibilidad ---
    System.out.println("\n2. Disponibilidad de Stock:");
    int indiceMin = 0; // Asumo que el primero es el min
    int indiceMax = 0; // Asumo que el primero es el max

    for (int i = 1; i < numProductosRegistrados; i++) {
      if (existencias[i] < existencias[indiceMin]) {
        indiceMin = i;
      }
      if (existencias[i] > existencias[indiceMax]) {
        indiceMax = i;
      }
    }
    System.out.println(
        "Producto con MAYOR disponibilidad: " + productos[indiceMax] + " (" + existencias[indiceMax] + " unidades)");
    System.out.println(
        "Producto con MENOR disponibilidad: " + productos[indiceMin] + " (" + existencias[indiceMin] + " unidades)");

    // --- Reporte 3: Ingreso Total por Ventas ---
    System.out.println("\n3. Ingreso Total por Ventas:");
    System.out.println("Ingreso total acumulado: $" + ingresoTotalVentas);
  }

  /**
   * --- Metodo Auxiliar ---
   * Este es un metodo que creo para reutilizar codigo.
   * Lo uso en la Opcion 3 (Vender) y la Opcion 4 (Buscar).
   * Recorre el arreglo 'productos' buscando un nombre.
   * Devuelve el 'indice' si lo encuentra.
   * Devuelve -1 si no lo encuentra.
   */
  private static int buscarIndiceProducto(String nombreBuscado) {
    /*
     * Recorro el arreglo comparando los nombres.
     */
    for (int i = 0; i < numProductosRegistrados; i++) {
      // Uso 'equalsIgnoreCase' para ignorar mayusculas
      if (productos[i].equalsIgnoreCase(nombreBuscado)) {
        return i; // Devuelvo la posicion.
      }
    }
    return -1; // Devuelvo -1 porque no lo encontre.
  }
}
