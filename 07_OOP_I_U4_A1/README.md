# 📦 Proyecto 7: Gestión de Inventario (A1)

Este proyecto corresponde a la **Actividad 1** de la **Unidad 4**, para la asignatura de Programación Orientada a Objetos I.

## 🎯 Objetivo

El programa es una aplicación de consola en Java que simula un sistema de gestión de inventario para la tienda "TechNova", aplicando los conceptos de la Unidad 4: **Arreglos Unidimensionales**.

El objetivo principal es utilizar "arreglos paralelos" para gestionar el catálogo de productos. El sistema utiliza tres arreglos de tamaño fijo:

- `String[] productos`: Almacena los nombres de los productos.
- `int[] existencias`: Almacena la cantidad de stock de cada producto.
- `double[] precios`: Almacena el precio de cada producto.

La aplicación presenta un menú interactivo que permite al usuario:

1.  **Registrar productos:** Añade nuevos productos a los arreglos.
2.  **Mostrar inventario:** Recorre los arreglos con un ciclo `for` para mostrar los productos, su stock, precio y valor total (`existencias * precio`).
3.  **Vender producto:** Busca un producto en el arreglo `productos` y actualiza su cantidad en el arreglo `existencias`.
4.  **Buscar producto:** Permite encontrar un ítem por su nombre.
5.  **Generar reportes:** Itera sobre los arreglos para encontrar productos agotados, el producto con mayor/menor stock y el ingreso total por ventas.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java
- **Gestor de dependencias y builds:** Gradle

## ⚙️ Instrucciones de Ejecución

Para ejecutar este proyecto, necesitas tener Java y Gradle instalados en tu sistema.

1.  Abre una terminal o línea de comandos.
2.  Navega hasta el directorio de este proyecto (ej: `07_OOP_I_U4_A1`).
3.  Ejecuta el siguiente comando:

    ```bash
    gradle run --console=plain
    ```

    El programa se compilará y se ejecutará en la consola, mostrando el menú principal de TechNova.

## 📝 Nota para usuarios de NetBeans

Si deseas abrir este proyecto con **Apache NetBeans**, el IDE puede detectar que no fue creado con su sistema de proyectos nativo.

- Simplemente selecciona `File > Open Project...` y elige la carpeta `07_OOP_I_U4_A1`.
- NetBeans te mostrará un diálogo para "corregir el proyecto" (`Resolve Project Problems`). Haz clic en **Resolve**.
- Esto permitirá que NetBeans configure el proyecto para que puedas ejecutarlo y depurarlo directamente desde el IDE sin alterar los archivos de Gradle.
