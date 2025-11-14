# ⚽ Proyecto 9: Simulador Mundial 2026 (AC)

Este proyecto corresponde a la **Actividad Complementaria (AC)** de la **Unidad 4**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

Este programa es una aplicación de consola integral que simula el torneo completo del **Mundial de Fútbol 2026**. Es un proyecto integrador que aplica todos los conceptos vistos a lo largo de la asignatura:

- **POO (Abstracción y Encapsulación):** Se crean clases como `Seleccion` (para guardar los datos de un equipo) y `Partido` (para encapsular la lógica de un enfrentamiento).
- **Jerarquía de Clases y Polimorfismo:** Se utiliza una superclase abstracta `FaseTorneo` que define un método abstracto `jugarFase()`. Dos subclases, `FaseDeGrupos` y `FaseEliminatoria`, heredan de ella e implementan (`@Override`) el método `jugarFase()` con lógicas completamente diferentes (una simula "todos contra todos" y la otra "eliminación directa").
- **Arreglos (Unidimensionales y de Objetos):** El núcleo del programa se gestiona con un arreglo de objetos `Seleccion[]` de tamaño 48. Se evita el uso de `ArrayList` para cumplir con los requisitos del temario de la Unidad 4.
- **Interactividad y Manejo de Excepciones:** El programa interactúa con el usuario (`Scanner`) para que seleccione los 20 equipos restantes y utiliza `try-catch` para validar que la entrada sea numérica (`InputMismatchException`).
- **Aleatoriedad y Lógica:** Se utiliza la clase `Random` (encapsulada en la clase `Partido`) para simular los marcadores de todos los partidos, incluyendo una tanda de penales aleatoria en caso de empate en fases eliminatorias.

El programa guía al usuario desde la selección de equipos, simula la fase de grupos (con 8 grupos de 6), y avanza por octavos, cuartos, semifinal y la final, hasta coronar a un campeón de forma aleatoria.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `09_OOP_I_U4_AC`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, mostrándote los 28 equipos clasificados y pidiéndote que selecciones los 20 restantes para iniciar la simulación.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `09_OOP_I_U4_AC`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
