# 🚚 Proyecto 4: Gestión de Flota Vehicular (A2)

Este proyecto corresponde a la **Actividad 2** de la **Unidad 3**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa es una aplicación de consola en Java que simula el sistema de gestión de flota "RapidCity" (basado en el caso de estudio de la Actividad 2). El objetivo principal es aplicar los conceptos fundamentales de las características de la POO vistas en la Unidad 3:

- **Jerarquía de Clases (Herencia):** Se define una clase abstracta `Vehiculo` que hereda atributos y métodos comunes a subclases concretas (`Motocicleta`, `CarroChico`).
- **Polimorfismo:** Las subclases sobrescriben métodos abstractos (como `generaRep()` y `calcularEnvio()`) para que cada tipo de vehículo responda de forma diferente a la misma llamada.
- **Sobrecarga de Métodos:** El método `calcularEnvio` se presenta en múltiples versiones (con y sin seguro) para demostrar la sobrecarga.
- **Interfaces:** Se utiliza la interfaz `IRegistrable` para definir un contrato de comportamiento que las clases de vehículos deben implementar.

La aplicación presenta un menú interactivo (`do-while` y `switch`) que permite al usuario simular el registro de los vehículos, generar reportes polimórficos y calcular los costos de envío.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `04_OOP_I_U3_A2`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, mostrando el menú principal del sistema RapidCity.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `04_OOP_I_U3_A2`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
