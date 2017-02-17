# FoodieApp
Aplicación sin uso comercial. Aplicación desarrollada para **workshop** sobre testing, patrones, arquitectura, etc. en Android.

## Clean Architecture o Arquitectura por Capas.
Se ha elegido una arquitectura por capas para diferenciar bien las distintas responsabilidades dentro de una aplicación. Además se ha separado en módulos el código para facilitar a distinguir que partes se testean con herramientas java y cuales necesitan herramientas para Android.
Cómo modulos dentro de la aplicación se ha diferenciado:
* Módulo app o de aplicación. Es el modulo y capa donde se define todo lo relacionado con el sdk de Android. Depende por tanto del sdk.
* Módulo de presentación. Es el módulo donde se define el presenter dentro de un patrón MVP. No depende de Android por tanto sólo usaremos Java.
* Módulo de negocio o de dominio. Es el módulo y capa donde se define el modelo de negocio. Es la capa más interna por tanto no depende del sdk de Android. Sólo usaremos Java.
* Módulo de datos. Es el módulo y capa donde se define la fuente de datos. Es una capa externa a la aplicación. Depende del sdk de Android.

## Inyección de Dependencias.
En el proyecto se usará inyección de dependencias. Para facilitar el desarrollo de factorías se usará [Dagger 2](https://google.github.io/dagger/). 
Dagger 2 será nuestro contenedor de dependencias.

## Testing
Los test unitarios se realizarán por los módulos definidos:
* Módulo App: Se testeará con [Espresso](https://developer.android.com/training/testing/ui-testing/espresso-testing.html).
* Módulo de Presentación: Se testeará con [jUnit](http://junit.org/junit4/). 
* Módulo de Dominio: Se testeará con jUnit.
* Módulo de Datos: Se testeará con jUnit y Espresso.

Además, se usarán las siguientes librerías:
* Test dobles usaremos [Mockito](http://site.mockito.org/).
* Para la inyección de dependencias en los test [DaggerMock](https://github.com/fabioCollini/DaggerMock).
* [Robolectric](http://robolectric.org/) Para hacer test unitarios que dependen de Android. No necesita emulador.
* [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) para emular un servidor que devuelve peticiones json.
