# 🤖 Proyecto 2: Registro para Concurso de Robótica

Este proyecto corresponde a la **Actividad 2** de la **Unidad 2**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa consiste en una aplicación de consola desarrollada en Java que simula el proceso de inscripción para un concurso de robótica. La aplicación solicita los datos de un equipo (nombre, coach, institución), captura la información de cada integrante y, utilizando **métodos** y **estructuras de control cíclicas y selectivas**, calcula el promedio de edad para asignar automáticamente una categoría: **"JUNIOR"** o **"SENIOR"**.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1. Abre una terminal o línea de comandos.
2. Navega hasta el directorio de este proyecto (`02_OOP_I_U2_A2`).
3. Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, guiándote a través del proceso de registro del equipo.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `02_OOP_I_U2_A2`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
