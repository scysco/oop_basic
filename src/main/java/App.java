package scysco.unadmpoo11;

import java.util.Locale;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    // CONSTANTE para el impuesto por litro
    final double IMPUESTO_POR_LITRO = 3.0818;
    
    // Arreglos para almacenar las presentaciones de refrescos y sus precios
    double[] presentaciones = {0.35, 0.50, 1.25, 1.50, 1.75, 2.00, 2.50, 3.00};
    double[] preciosBase = new double[presentaciones.length];
    
    // Objeto Scanner para capturar la entrada del teclado del usuario
    // Uso getDefault para asegurar que el punto sea reconocido como el separador decimal.
    Scanner teclado = new Scanner(System.in).useLocale(Locale.getDefault());

    System.out.println("Calculo de impuesto en refrescos");
    System.out.println("------------------------------------");
    System.out.println("Por favor, ingrese el precio base para cada presentación:");

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
  }
}
