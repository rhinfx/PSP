# Ejemplo Herencia

Ejemplo de ejercico de clases con herencia en java según el siguiente diagrama:

![Imagen EjemploHerencia](http://blog.elinsti.com/wp-content/uploads/2018/10/Ejercicio-Herencia-Java.jpg)

## Funcionamiento

La clase base es la clase Empleado. Esta clase contiene:

- Un atributo privado nombre de tipo String que heredan el resto de clases.
- Un constructor por defecto
- Un constructor con parámetros que inicializa el nombre con el String que recibe.
- Método set y get para el atributo nombre.
- Un método toString() que devuelve "Empleado" + nombre.

El resto de las clases sólo sobrescriben el método toString() en cada una de ellas y declaran el constructor
adecuado.
