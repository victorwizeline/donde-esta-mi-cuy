# Donde esta mi cuy?

**Dónde está mi cuy?** es un ejemplo de cómo podemos implementar localización en tiempo real usando la ya muy conocida plataforma de desarrollo Firebase en conjunto con la librería GeoFire la cual nos permite hacer consultas de puntos geográficos con un radio definido.

## Requisitos

Para poder ejecutar esta aplicación es necesario preparar un par de configuraciones, lo primero es tener el archivo google-services.json el cual puedes obtener al momento de registrar una nueva aplicación desde la consola de firebase, este archivo debe colocarse dentro de la carpeta app, lo segundo es agregar un nuevo string al archivo strings, el cual debe tener el formato que aparece bajo esta instrucción, solo debes agregar la llave la cual puedes obtener en la consola de desarrollo google.

<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">TU_LLAVE</string>

## Lincencia

    Copyright [2018] [Victor Mariano Lopez Medina]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.