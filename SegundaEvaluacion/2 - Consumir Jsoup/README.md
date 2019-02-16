# Consumir datos con Jsoup

Aplicación de Java, con interfaz creada en JavaFX que nos muestra imagenes de animalitos obtenidas mediante web scraping a través de Jsoup

## Funcionamiento

La aplicación dispone de un botón que activará la búsqueda en la web https://old.reddit.com/r/aww/ que se actualiza continuamente con images de perritos y gatitos. Mediante Jsoup buscamos en la web todos los enlaces <a> y dentro de ellos todos los que en el atributo "href" tengan algo relacionado con una imagen, como .jpg. Mostramos los links por consola y cargamos 3 imagenes de todas las encontradas en nuestra aplicación en 3 ImageView. También tenemos un label que se actualizará con el título de la web buscada, también a través de Jsoup

## Ejemplo de ejecucuión

![Imagen AWW](https://github.com/rhinfx/PSP/blob/master/SegundaEvaluacion/2%20-%20Consumir%20Jsoup/jsoup-aww.gif)

