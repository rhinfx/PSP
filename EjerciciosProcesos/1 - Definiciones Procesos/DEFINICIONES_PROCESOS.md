# Multiproceso

El multiprocesamiento se refiere a la capacidad de un sistema para admitir más de un procesador al mismo tiempo.

# Aplicación

Las aplicaciones en un sistema de multiprocesamiento se dividen en rutinas más pequeñas que se ejecutan de forma independiente. El sistema operativo asigna estos hilos a los procesadores que mejoran el rendimiento del sistema.

# Servicio

Un servicio es un proceso que no muestra ninguna ventana ni gráfico en pantalla porque no está pensado para que el usuario lo maneje directamente.

Habitualmente, un servicio es un programa que atiende a otro programa.

No tienen interfaz, se ejecutan en segundo plano.

![Imagen EjemploHerencia](https://raw.githubusercontent.com/rhinfx/PSP/master/EjerciciosProcesos/1%20-%20Definiciones%20Procesos/img/Servicios.png)


# Proceso

Es un archivo que está en ejecución y bajo el control del sistema operativo. Un proceso puede atravesar diversas etapas en su «ciclo de vida». Los estados en los que puede estar son:

- En ejecución: está dentro del microprocesador
- Pausado/detenido/en espera: el proceso tiene que seguir en ejecución pero en ese momento el S.O tomó la decisión de dejar paso a otro
- Interrumpido: el proceso tiene que seguir en ejecución pero el usuario ha decidido interrumpir la ejecución.
- Existen otros estados pero ya son muy dependientes del sistema operativo concreto

![Imagen EjemploHerencia](https://raw.githubusercontent.com/rhinfx/PSP/master/EjerciciosProcesos/1%20-%20Definiciones%20Procesos/img/Proceso2-1.png)


# Hilo

Un hilo es un concepto más avanzado que un proceso: al hablar de procesos cada uno tiene su propio espacio en memoria. Si abrimos 20 procesos cada uno de ellos consume 20x de memoria RAM. Un hilo es un proceso mucho más ligero, en el que el código y los datos se comparten de una forma distinta.

Un proceso no tiene acceso a los datos de otro procesos. Sin embargo un hilo sí accede a los datos de otro hilo. Esto complicará algunas cuestiones a la hora de programar.

![Imagen EjemploHerencia](http://blog.elinsti.com/wp-content/uploads/2018/10/Ejercicio-Herencia-Java.jpg)


# Programación Concurrente

La programación concurrente es la parte de la programación que se ocupa de crear programas que pueden tener varios procesos/hilos que colaboran para ejecutar un trabajo y aprovechar al máximo el rendimiento de sistemas multinúcleo.



# Programación Paralela

 En el caso de la programación paralela un solo ordenador puede ejecutar varias tareas a la vez (lo que supone que tiene 2 o más núcleos).
 
 

# Programación Distribuida

En sistemas distribuidos hablamos de programación distribuida.


