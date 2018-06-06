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
1. r -> activar / desactivar rotación
2. espacio -> cambiar shader (5 disponibles)
3. flechas -> mover nodo eye en 4 direcciones

Se usó la clase OrbitNode de algunod ejemplos de la librería frames para crear nodos interactivos, los cuales permitieran crear una jerarquía de nodos de la siguiente manera:

![Image of nodes](https://raw.githubusercontent.com/anfmorenoso/shadersFi/master/data/nodes.png)

De ésta manera poder observar e interactuar con los elementos de la escena, resaltando sus diferencias con cada shader.
