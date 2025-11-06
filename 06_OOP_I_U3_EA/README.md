# 🛢️ Proyecto 6: Sistema de Monitoreo Petrover (EA)

Este proyecto corresponde a la **Evidencia de Aprendizaje (EA)** de la **Unidad 3**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa es una aplicación de consola en Java que simula un sistema de monitoreo de maquinaria para la empresa "Petrover@", basado en el caso de estudio de la Evidencia de Aprendizaje.

El objetivo principal es diseñar una jerarquía de clases robusta y aplicar el polimorfismo para gestionar diferentes tipos de maquinaria:

- **Clase Abstracta:** Se define `EquipoPetrolero` como una superclase abstracta que contiene propiedades comunes (`idEquipo`, `ubicacion`) y métodos abstractos (`obtenerEstadoActual`, `requiereMantenimiento`).
- **Subclases Concretas:** Se crean 3 subclases que heredan de `EquipoPetrolero`: `Bomba`, `SensorFlujo` y `TurbinaGas`. Cada una implementa sus propias propiedades (ej. `presionActual`, `flujoPorMinuto`) y la lógica polimórfica para determinar su estado y necesidad de mantenimiento.
- **Clase Controladora:** Una clase `Controlador` se encarga de gestionar los equipos.
- **Entrada de Datos:** El programa utiliza `Scanner` para solicitar al usuario los valores de telemetría de cada equipo (presión, flujo, etc.) antes de ejecutar el monitoreo.

**Nota sobre la implementación:** Aunque el caso de estudio sugería usar `ArrayList` en el `Controlador`, esta solución (basada en retroalimentación) utiliza "slots" de variables fijas para almacenar los equipos, demostrando el polimorfismo sin el uso de colecciones.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `06_OOP_I_U3_EA`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, solicitándote los datos de estado de cada equipo y luego mostrando el reporte de monitoreo.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `06_OOP_I_U3_EA`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
