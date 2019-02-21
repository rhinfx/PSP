# Consumir API desde Aplicación de Android

Aplicación creada con android studio que permite hacer una llamada, en este caso a mi api rest creada desde un proyecto web diánmico en eclipse y que tengo lanzada en local mediante apache tomcat. La API se trata de una lista de platos, menús, usuarios y autores de los platos, en método GET devuelve los resultados a las llamadas en JSON.

## Funcionamiento

La aplicación cuando se inicia ejecuta una tarea de la clase AsynTask de Android que dicha clase dispone de varios métodos, uno de los cuales "doInBackground()" crea un hilo a parte, que usamos para realizar tareas pesadas, en este caso conectarnos a la API. Sin embargo todos los demás métodos de la clase AsynTask se ejecutan en el hilo principal del programa, por lo que nos permite desde los otros métodos, en nuestro caso, actualizar la interfaz o lo que tengamos que hacer.

Básicamente nos comunicamos con la api en la tarea en un hilo aparte y parseamos el JSON leído mediante al librería GSON a nuestro modelo de datos, que lo guardamos en una Lista de platos con la que con el método "onPostExecute(Result)" se la pasamos a la activity principal para que podamos trabajar con ella.

Dispone de un menú desplegable "Navigation Drawer" y en la opción Dishes, mostramos mediante un RecyclerView toda la lista de platos que ya hemos leído en cuanto iniciamos la aplicación mediante la AsynTask, si pulsamos en un plato, nos muestra el detalle de este.

## Ejemplo de ejecucuión

![Imagen API-ANDROID](https://github.com/rhinfx/PSP/blob/master/SegundaEvaluacion/3%20-%20Cosumir%20API/consumirAPIRESTandroid.gif)

