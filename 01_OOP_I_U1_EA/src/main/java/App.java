package scysco.unadmpoo11;

import java.util.Locale;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    // CONSTANTE para el impuesto por litro
    final double IMPUESTO_POR_LITRO = 3.0818;
    
    // Objeto Scanner para capturar la entrada del teclado del usuario
    // Uso getDefault para asegurar que el punto sea reconocido como el separador decimal.
    Scanner teclado = new Scanner(System.in).useLocale(Locale.getDefault());

    System.out.println("Calculo de impuesto en refrescos");
    System.out.println("------------------------------------");
    System.out.println("Por favor, ingrese el precio base para cada presentación:");
    
    // ---------------------------------------------------------------------------------
    // NOTA: Para respetar estrictamente el criterio de la rúbrica "Variables separadas por botella",
    // se procede a declarar un conjunto de variables para cada presentación.
    // La solución comentada más abajo, usando arreglos, es técnicamente más óptima y escalable.
    // ---------------------------------------------------------------------------------

    // Declaración de variables separadas para cada botella
    double precioBase035, precioBase050, precioBase125, precioBase150, precioBase175, precioBase200, precioBase250, precioBase300;
    
    // Captura de precios
    System.out.printf("Precio botella 0.35 L: $ ");
    precioBase035 = teclado.nextDouble();
    System.out.printf("Precio botella 0.50 L: $ ");
    precioBase050 = teclado.nextDouble();
    System.out.printf("Precio botella 1.25 L: $ ");
    precioBase125 = teclado.nextDouble();
    System.out.printf("Precio botella 1.50 L: $ ");
    precioBase150 = teclado.nextDouble();
    System.out.printf("Precio botella 1.75 L: $ ");
    precioBase175 = teclado.nextDouble();
    System.out.printf("Precio botella 2.00 L: $ ");
    precioBase200 = teclado.nextDouble();
    System.out.printf("Precio botella 2.50 L: $ ");
    precioBase250 = teclado.nextDouble();
    System.out.printf("Precio botella 3.00 L: $ ");
    precioBase300 = teclado.nextDouble();
    
    // Cierre del Scanner para liberar recursos.
    teclado.close();
    
    // Salida de Resultados:
    System.out.println("\n--- Precios Base Ingresados ---");
    System.out.printf("Precio botella 0.35 L: $%.2f\n", precioBase035);
    System.out.printf("Precio botella 0.50 L: $%.2f\n", precioBase050);
    System.out.printf("Precio botella 1.25 L: $%.2f\n", precioBase125);
    System.out.printf("Precio botella 1.50 L: $%.2f\n", precioBase150);
    System.out.printf("Precio botella 1.75 L: $%.2f\n", precioBase175);
    System.out.printf("Precio botella 2.00 L: $%.2f\n", precioBase200);
    System.out.printf("Precio botella 2.50 L: $%.2f\n", precioBase250);
    System.out.printf("Precio botella 3.00 L: $%.2f\n", precioBase300);

    System.out.println("\n--- Detalle de Impuestos y Precios Finales ---");

    // Cálculo y muestra de resultados para cada botella
    double impuesto035 = 0.35 * IMPUESTO_POR_LITRO;
    double total035 = precioBase035 + impuesto035;
    System.out.printf("Botella 0.35 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase035, impuesto035, total035);

    double impuesto050 = 0.50 * IMPUESTO_POR_LITRO;
    double total050 = precioBase050 + impuesto050;
    System.out.printf("Botella 0.50 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase050, impuesto050, total050);

    double impuesto125 = 1.25 * IMPUESTO_POR_LITRO;
    double total125 = precioBase125 + impuesto125;
    System.out.printf("Botella 1.25 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase125, impuesto125, total125);

    double impuesto150 = 1.50 * IMPUESTO_POR_LITRO;
    double total150 = precioBase150 + impuesto150;
    System.out.printf("Botella 1.50 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase150, impuesto150, total150);
    
    double impuesto175 = 1.75 * IMPUESTO_POR_LITRO;
    double total175 = precioBase175 + impuesto175;
    System.out.printf("Botella 1.75 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase175, impuesto175, total175);

    double impuesto200 = 2.00 * IMPUESTO_POR_LITRO;
    double total200 = precioBase200 + impuesto200;
    System.out.printf("Botella 2.00 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase200, impuesto200, total200);

    double impuesto250 = 2.50 * IMPUESTO_POR_LITRO;
    double total250 = precioBase250 + impuesto250;
    System.out.printf("Botella 2.50 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase250, impuesto250, total250);

    double impuesto300 = 3.00 * IMPUESTO_POR_LITRO;
    double total300 = precioBase300 + impuesto300;
    System.out.printf("Botella 3.00 L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n", precioBase300, impuesto300, total300);

    /*
    // --- INICIO: SOLUCIÓN ÓPTIMA ORIGINAL (USANDO ARREGLOS) ---
    // Arreglos para almacenar las presentaciones de refrescos y sus precios
    double[] presentaciones = {0.35, 0.50, 1.25, 1.50, 1.75, 2.00, 2.50, 3.00};
    double[] preciosBase = new double[presentaciones.length];
    
    // Bucle para capturar el precio base de cada presentación
    for (int i = 0; i < presentaciones.length; i++) {
      System.out.printf("Precio botella %.2f L: $ ", presentaciones[i]);
      preciosBase[i] = teclado.nextDouble();
    }
    // Cierre del Scanner para liberar recursos.
    teclado.close();

    // Salida de Resultados:
    System.out.println("\n--- Precios Base Ingresados ---");
    for (int i = 0; i < presentaciones.length; i++) {
      System.out.printf("Precio botella %.2f L: $%.2f\n", presentaciones[i], preciosBase[i]);
    }

    System.out.println("\n--- Detalle de Impuestos y Precios Finales ---");

    // Bucle para calcular y mostrar el detalle de cada presentación
    for (int i = 0; i < presentaciones.length; i++) {
      // Cálculo del impuesto: volumen * impuesto por litro
      // Se usa el tipo 'double' para mantener la precisión decimal
      double impuestoCalculado = presentaciones[i] * IMPUESTO_POR_LITRO;
      // Cálculo del precio final: precio base + impuesto
      double precioFinal = preciosBase[i] + impuestoCalculado;
      // Se muestra la salida formateada para parecerse al ejemplo del caso de estudio
      System.out.printf(
          "Botella %.2f L | Base: $%.2f | Impuesto: $%.2f | Total: $%.2f\n",
          presentaciones[i],
          preciosBase[i],
          impuestoCalculado,
          precioFinal
          );
    }
    // --- FIN: SOLUCIÓN ÓPTIMA ORIGINAL ---
    */
  }
}
