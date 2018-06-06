# Taller shaders

## Integrantes

|       Integrante     | github nick |
|----------------------|-------------|
| Andrés Felipe Moreno | anfmorenoso |
| Luis Eduardo Martin  | lemartinp   |

## Objetivo

Comprender algunos aspectos fundamentales de los shaders de iluminación así como agregar interactividad a las figuras usando la librería frames

## Desarrollo

### Atajos de teclado
- r -> activar / desactivar rotación
- espacio -> cambiar shader (6 disponibles)
  - Diffuse + specular vertex
  - Diffuse + specular pixel
  - Pvertex_diffuse
  - Ppixel_diffuse
  - Pvertex_specular
  - Ppixel_specular
- flechas -> mover nodo eye en 4 direcciones

Se usó la clase OrbitNode de algunod ejemplos de la librería frames para crear nodos interactivos, los cuales permitieran crear una jerarquía de nodos de la siguiente manera:

![Image of nodes](https://raw.githubusercontent.com/anfmorenoso/shadersFi/master/data/nodes.png)

De ésta manera poder observar e interactuar con los elementos de la escena, resaltando sus diferencias con cada shader.

## Resultados

Se hizo uso a propósito de cilindros con cierto número de caras en su lado redondeado para así hacer más notoria la diferencia entre los shaders
efectuados sobre los vértices y los shaders efectuados sobre los pixeles del objeto, pues en el primer caso es más visible el cambio de una cara
a la otra, mientras que en el segundoo se aplica el shaders de una manera mejor distribuida sobre la superficie del objeto.

Si bien los shaders se comportan bien y se notan las diferencias entre ellos, la luz en sí misma no es fácil de ubicar o de ver su comportamiento general dada su posición

### A futuro

Se podría intentar usar luces dirigidas o de spot para ver las diferencias
Se podrían agregar shaders de textura
