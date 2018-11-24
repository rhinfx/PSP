
# CLASE
En el mundo real, a menudo encontramos muchos objetos individuales del mismo tipo. Puede haber miles de otras bicicletas en existencia, todas de la misma marca y modelo. Cada bicicleta se construyó a partir del mismo conjunto de planos y, por lo tanto, contiene los mismos componentes. En términos orientados a objetos, decimos que su bicicleta es una instancia de la clase de objetos conocidos como bicicletas. Una clase es el plano a partir del cual se crean los objetos individuales.

La siguiente clase de bicicletas es una posible implementación de una bicicleta:
```Java
class Bicycle {

    int cadence = 0;
    int speed = 0;
    int gear = 1;

    void changeCadence(int newValue) {
         cadence = newValue;
    }

    void changeGear(int newValue) {
         gear = newValue;
    }

    void speedUp(int increment) {
         speed = speed + increment;   
    }

    void applyBrakes(int decrement) {
         speed = speed - decrement;
    }

    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
```
  https://docs.oracle.com/javase/tutorial/java/concepts/class.html
  
# OBJETO
Los objetos son clave para entender la tecnología orientada a objetos. Mire a su alrededor ahora mismo y encontrará muchos ejemplos de objetos del mundo real: su perro, su escritorio, su televisor, su bicicleta.

Los objetos del mundo real comparten dos características: todos tienen estado y comportamiento. Los perros tienen estado (nombre, color, raza, hambre) y comportamiento (ladrar, ir a buscar, mover la cola). Las bicicletas también tienen estado (marcha actual, cadencia actual del pedal, velocidad actual) y comportamiento (cambio de marcha, cambio de cadencia del pedal, aplicación de frenos). Identificar el estado y el comportamiento de los objetos del mundo real es una excelente manera de comenzar a pensar en términos de programación orientada a objetos.

Tómese un minuto ahora mismo para observar los objetos del mundo real que se encuentran en su área inmediata. Para cada objeto que vea, hágase dos preguntas: "¿En qué estados se puede encontrar este objeto?" y "¿Qué comportamiento puede realizar este objeto?". Asegúrese de anotar sus observaciones. Mientras lo hace, notará que los objetos del mundo real varían en complejidad; su lámpara de escritorio puede tener solo dos estados posibles (encendido y apagado) y dos comportamientos posibles (encender, apagar), pero su radio de escritorio puede tener estados adicionales (encendido, apagado, volumen actual, estación actual) y comportamiento (encender , desactivar, aumentar el volumen, disminuir el volumen, buscar, escanear y sintonizar). También puede observar que algunos objetos, a su vez, también contendrán otros objetos. Todas estas observaciones del mundo real se traducen en el mundo de la programación orientada a objetos.

Los objetos de software son conceptualmente similares a los objetos del mundo real: también consisten en estado y comportamiento relacionado. Un objeto almacena su estado en campos (variables en algunos lenguajes de programación) y expone su comportamiento a través de métodos (funciones en algunos lenguajes de programación). Los métodos operan en el estado interno de un objeto y sirven como el mecanismo principal para la comunicación de objeto a objeto. Ocultar el estado interno y requerir que toda interacción se realice a través de los métodos de un objeto se conoce como encapsulación de datos, un principio fundamental de la programación orientada a objetos.

  https://docs.oracle.com/javase/tutorial/java/concepts/object.html
  
# ENCAPSULACIÓN
La encapsulación es uno de los cuatro conceptos fundamentales de la POO. Los otros tres son herencia, polimorfismo y abstracción.

La encapsulación en Java es un mecanismo de envolver los datos (variables) y el código que actúa sobre los datos (métodos) juntos como una sola unidad. En la encapsulación, las variables de una clase se ocultarán de otras clases y solo se podrá acceder a ellas a través de los métodos de su clase actual. Por lo tanto, también se conoce como ocultación de datos.

  https://www.tutorialspoint.com/java/java_encapsulation.htm
  
# INSTANCIACIÓN E INICIALIZACIÓN DE OBJETOS
Como saben, una clase proporciona el plano para los objetos; creas un objeto de una clase Cada una de las siguientes declaraciones tomadas del programa CreateObjectDemo crea un objeto y lo asigna a una variable:

```Java
Point originOne = new Point(23, 94);
Rectangle rectOne = new Rectangle(originOne, 100, 200);
Rectangle rectTwo = new Rectangle(50, 100);
```

La primera línea crea un objeto de la clase Point, y la segunda y la tercera línea crean un objeto de la clase Rectangle.

Cada una de estas afirmaciones tiene tres partes.

Declaración: el código establecido a la izquierda del = son todas las declaraciones de variables que asocian un nombre de variable con un tipo de objeto.
Instanciación: la palabra reservada new es un operador de Java que crea el objeto.
Inicialización: al nuevo operador le sigue una llamada a un constructor, que inicializa el nuevo objeto.

  https://docs.oracle.com/javase/tutorial/java/javaOO/objectcreation.html
  
# SOBRECARGA DE MÉTODOS
El lenguaje de programación Java admite métodos sobrecargados. Esto significa que los métodos dentro de una clase pueden tener el mismo nombre si tienen diferentes listas de parámetros.

Supongamos que tiene una clase que puede usar la caligrafía para dibujar varios tipos de datos (cadenas, enteros, etc.) y que contiene un método para dibujar cada tipo de datos. Es incómodo usar un nuevo nombre para cada método, por ejemplo, drawString, drawInteger, drawFloat, etc. En el lenguaje de programación Java, puede usar el mismo nombre para todos los métodos draw, pero con una lista de argumentos diferente a cada método. Por lo tanto, se podrían declarar cuatro métodos llamados draw, cada uno de los cuales tiene una lista de parámetros diferente.

```Java
public class DataArtist {
    ...
    public void draw(String s) {
        ...
    }
    public void draw(int i) {
        ...
    }
    public void draw(double f) {
        ...
    }
    public void draw(int i, double f) {
        ...
    }
}
```
  https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html
  
# HERENCIA
Diferentes tipos de objetos a menudo tienen una cierta cantidad en común entre sí. Bicicletas de montaña, bicicletas de carretera y bicicletas tándem, por ejemplo, todas comparten las características de las bicicletas (velocidad actual, cadencia actual del pedal, equipo actual). Sin embargo, cada uno también define características adicionales que los hacen diferentes: las bicicletas tándem tienen dos asientos y dos juegos de manubrios; Las bicicletas de carretera tienen manillares sueltos; Algunas bicicletas de montaña tienen un anillo de cadena adicional, lo que les da una menor relación de transmisión.

La programación orientada a objetos permite que las clases hereden el estado y el comportamiento comúnmente utilizados de otras clases. En este ejemplo, Bicycle ahora se convierte en la superclase de MountainBike, RoadBike y TandemBike. En el lenguaje de programación Java, a cada clase se le permite tener una superclase directa, y cada superclase tiene el potencial de un número ilimitado de subclases:

La sintaxis para crear una subclase es simple. Al comienzo de su declaración de clase, use la palabra clave extended, seguida del nombre de la clase para heredar de:

```Java
class MountainBike extends Bicycle {

    // new fields and methods defining 
    // a mountain bike would go here

}
```
  https://docs.oracle.com/javase/tutorial/java/concepts/inheritance.html
  
# POLIMORFISMO
La definición del diccionario de polimorfismo se refiere a un principio en biología en el que un organismo o especie puede tener muchas formas o etapas diferentes. Este principio también puede aplicarse a la programación orientada a objetos y lenguajes como el lenguaje Java. Las subclases de una clase pueden definir sus propios comportamientos únicos y, sin embargo, compartir algunas de las mismas funciones de la clase principal.

El polimorfismo se puede demostrar con una modificación menor en la clase de bicicletas. Por ejemplo, un método printDescription podría agregarse a la clase que muestra todos los datos almacenados actualmente en una instancia.


```Java
public void printDescription(){
    System.out.println("\nBike is " + "in gear " + this.gear
        + " with a cadence of " + this.cadence +
        " and travelling at a speed of " + this.speed + ". ");
}
```
Para demostrar las características polimórficas en el lenguaje Java, heredamos de la clase Bicycle una clase MountainBike. Para MountainBike, agregamos un campo para suspensión, que es un valor de cuerda que indica si la bicicleta tiene un amortiguador delantero, delantero.


Aquí está la clase actualizada:

```Java
public class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(
               int startCadence,
               int startSpeed,
               int startGear,
               String suspensionType){
        super(startCadence,
              startSpeed,
              startGear);
        this.setSuspension(suspensionType);
    }

    public String getSuspension(){
      return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("The " + "MountainBike has a" +
            getSuspension() + " suspension.");
    }
}
```

Tenga en cuenta el método printDescription. Además de la información proporcionada anteriormente, se incluyen datos adicionales sobre la suspensión en la salida.

  https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html
  
# INTERFACE
Como ya aprendió, los objetos definen su interacción con el mundo exterior a través de los métodos que exponen. Los métodos forman la interfaz del objeto con el mundo exterior; Los botones en la parte frontal de su televisor, por ejemplo, son la interfaz entre usted y el cableado eléctrico en el otro lado de su carcasa de plástico. Presiona el botón "Encendido" para encender y apagar el televisor.

En su forma más común, una interfaz es un grupo de métodos relacionados con cuerpos vacíos. El comportamiento de una bicicleta, si se especifica como una interfaz, puede aparecer como sigue:
```Java
interface Bicycle {

    //  wheel revolutions per minute
    void changeCadence(int newValue);

    void changeGear(int newValue);

    void speedUp(int increment);

    void applyBrakes(int decrement);
}
```
Para implementar esta interfaz, el nombre de su clase cambiaría (a una marca particular de bicicleta, por ejemplo, como ACMEBicycle), y usaría la palabra reservada implements en la declaración de la clase:

```Java
class ACMEBicycle implements Bicycle {

    int cadence = 0;
    int speed = 0;
    int gear = 1;

   // The compiler will now require that methods
   // changeCadence, changeGear, speedUp, and applyBrakes
   // all be implemented. Compilation will fail if those
   // methods are missing from this class.

    void changeCadence(int newValue) {
         cadence = newValue;
    }

    void changeGear(int newValue) {
         gear = newValue;
    }

    void speedUp(int increment) {
         speed = speed + increment;   
    }

    void applyBrakes(int decrement) {
         speed = speed - decrement;
    }

    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
```

  https://docs.oracle.com/javase/tutorial/java/concepts/interface.html
