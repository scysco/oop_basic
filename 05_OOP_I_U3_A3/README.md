# ✈️ Proyecto 5: Agencia de Viajes (A3)

Este proyecto corresponde a la **Actividad 3** de la **Unidad 3**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa es una aplicación de consola en Java que simula el sistema de reservas para la "Agencia de Viajes: Aquí volamos todos", basado en el caso de estudio de la Actividad 3. El objetivo es aplicar conceptos avanzados de POO, incluyendo:

- **Jerarquía de Clases:** Se utiliza una superclase abstracta `Viaje` y subclases concretas (`TodoIncluido`, `Personalizado`).
- **Interfaces:** Se define un contrato `IOperacionesReserva` para estandarizar los métodos de la reserva.
- **Métodos Abstractos y Finales:** Se implementan métodos abstractos para el polimorfismo y métodos finales para proteger la lógica (ej. `mostrarTicket`).
- **Sobrecarga de Métodos:** El método `calcularAnticipo` se sobrecarga para manejar diferentes escenarios de pago.
- **Manejo de Excepciones:** Se utiliza un bloque `try-catch` para gestionar errores de forma controlada, específicamente al intentar cancelar una reservación que no existe (previniendo un `NullPointerException`).

La aplicación presenta un menú interactivo que permite al usuario realizar reservaciones y cancelarlas de forma segura.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `05_OOP_I_U3_A3`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, mostrando el menú principal de la agencia de viajes.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `05_OOP_I_U3_A3`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
