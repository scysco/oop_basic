# 🎓 Proyecto 8: Dashboard Académico (EA)

Este proyecto corresponde a la **Evidencia de Aprendizaje (EA)** de la **Unidad 4**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa es una aplicación de consola en Java que simula un "Dashboard Académico" para el Instituto Aurora, aplicando los conceptos de la Unidad 4: **Arreglos Unidimensionales** y **Arreglos Multidimensionales**.

El objetivo principal es utilizar arreglos para gestionar las calificaciones de un grupo de alumnos. El sistema se basa en:

- Un **arreglo multidimensional** (matriz) de 4x4 para almacenar las calificaciones: `double[][] calificaciones`.
- **Arreglos unidimensionales** (vectores) paralelos para almacenar los nombres de los alumnos (`String[] alumnos`), los nombres de las actividades (`String[] actividades`) y las ponderaciones de cada actividad (`double[] ponderaciones`).

La aplicación presenta un menú interactivo (`do-while` / `switch`) que permite al docente:

1.  **Capturar calificaciones por alumno:** Busca a un alumno por nombre y recorre una **fila** de la matriz para asignar sus calificaciones.
2.  **Capturar ponderaciones:** Recorre el vector de ponderaciones para asignar el porcentaje de cada actividad.
3.  **Imprimir calificaciones de un alumno:** Recorre una **fila** de la matriz para mostrar todas las calificaciones de un estudiante.
4.  **Imprimir rendimiento de una tarea:** Recorre una **columna** de la matriz para mostrar las calificaciones de todos los alumnos en una actividad específica.
5.  **Calcular promedio ponderado:** Recorre la fila de un alumno en paralelo con el vector de ponderaciones para calcular su promedio final.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `08_OOP_I_U4_EA`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, mostrando el menú principal del Dashboard Académico.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `08_OOP_I_U4_EA`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
