# Validar NIF

Método estático que recibe un String con un NIF y devuelve TRUE o FALSE según si és válido o no en función de la letra. 

## Funcionamiento

Requisitos para que el DNI sea válido:

1. Se divide el número entre 23
2. Obtenemos el resto del número, el resultado está entre 0 y 22.
3. Hacemos corresponder dicho resto con la letra correspondiente según se indica en esta tabla.

| T | R | w | A | G | M | Y | F | P | D | X | B | N | J | Z | S | Q | V | H | L | C | K | E | 
| - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - | - |
| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 0 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 |


### Comprobaciones adicionales

También devuelve FALSE si se cumple cualquiera de estas condiciones.

1. La longitud de la cadena introducida es distinta de 9
2. Alguno de los primeros 8 caracteres de la cadena no son dígitos (0-9)
3. El último carácter de la cadena no es una letra [A-z]
