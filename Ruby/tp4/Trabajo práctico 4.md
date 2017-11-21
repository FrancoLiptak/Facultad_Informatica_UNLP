# Trabajo práctico 4

## Gemas y Bundler

### 1. ¿Qué es una gema? ¿Para qué sirve? ¿Qué estructura tienen?


Las gemas Ruby son software que nos permite facilmente descargar, instalar y usar paquetes de software Ruby en nuestro sistema. El paquete de software es llamado "gema", el cual contiene una aplicación o librería empaquetada.

Las gemas pueden ser usadas para extender o modificar funcionalidad de aplicaciones Ruby. Comunmente son usadas para distribuir funcionalidades reusables, que son compartidas por otros usuarios de Ruby en sus aplicaciones y librerías. 

ESTRUCTURA DE UNA GEMA

Cada gema tiene un nombre, versión y plataforma.

Dentro de una gema, podemos encontrar los siguientes componentes:
- Codigo (incluyendo test o utilidades de soporte)
- Documentacion
- gemspec

Cada gema sigue la misma estructura estandar de organizaciónde código:

```
% tree freewill
freewill/
├── bin/
│   └── freewill
├── lib/
│   └── freewill.rb
├── test/
│   └── test_freewill.rb
├── README
├── Rakefile
└── freewill.gemspec

```

http://guides.rubygems.org/what-is-a-gem/

### 2. ¿Cuáles son las principales diferencias entre el comando gem y Bundler? ¿Hacen lo mismo?
### 3. ¿Dónde instalan las gemas los comandos gem y bundle?

Bundler es un manejador de dependencias. Sirve para agregar dependencias externas a un proyecto Ruby. Las librerías a utilizar se deben especificar en el Gemfile, las cuales a su vez pueden especificar otras dependencias. Bundler, usando el archivo Gemfile, se encargará de descargar todo lo necesario (solo es necesario utilizar el comando Bundle). Gracias a esto, uno puede estar seguro de que su aplicación cuente con todas las dependencias necesarias.

Bundler nos permite tener claramente separadas nuestras aplicaciones con sus propias gemas, ya que las gemas se instalan en el proyecto.

Bundler es una gema: gem install bundler.

Gems es el manejador de gemas por parte del ruby core. No es lo mismo instalar una gema con bundler que con gem. Bundler es externo, mientras que gem instala en el directorio del core de Ruby (es decir, las gemas se instalan "globalmente").

### 4. ¿Para qué sirve el comando gem server? ¿Qué información podés obtener al usarlo?

Al utilizar el comando gem server se mostrará en el localhost (puerto 8808) la lista de gemas instaladas en el ruby core.

http://guides.rubygems.org/run-your-own-gem-server/

### 5. Investigá un poco sobre Semantic Versioning (o SemVer). ¿Qué finalidad tiene? ¿Cómo se compone una versión? ¿Ante qué situaciones debería cambiarse cada una de sus partes?

Es una convención de semántica de versionado para código.

Define la composición de un versionado como : MAJOR.MINOR.PATCH.

- MAJOR x.0.0: Se cambia cuando se realizan cambios que hacen la API anterior incompatible
- MINOR 0.x.0: Se cambia cuando agrega funcionalidad compatible hacia atrás.
- PATCH  0.0.x : Se cambia cuando se arreglaron bugs.

https://semver.org/

### 7. Utilizando el proyecto creado en el punto anterior como referencia, contestá las siguientes preguntas:

#### 1. ¿Qué finalidad tiene el archivo Gemfile?

Es el archivo donde se le debe especificar a Bundler las dependencias del proyecto. La idea es que haya un archivo Gemfile por proyecto.

Cada vez que se hace un ` bundle install ` cambia el Gemfile.lock. En este archivo se especifican las versiones de las librerías que se descargaron para el proyecto en que fecha fueron descargadas.

#### 2. ¿Para qué sirve la directiva source del Gemfile? ¿Cuántas pueden haber en un mismo archivo?

Sirve para indicar a donde se debe hacer el requerimiento de las gemas. Puede haber más de un source en el Gemfile. 
El source puede ser especificado para una gema en particular o agrupar gemas por sources.

http://bundler.io/gemfile.html

#### 3. Acorde a cómo agregaste la gema colorputs, ¿qué versión se instaló de la misma? Si mañana se publicara la versión 7.3.2, ¿esta se instalaría en tu proyecto? ¿Por qué? ¿Cómo podrías limitar esto y hacer que sólo se instalen releases de la gema en las que no cambie la versión mayor de la misma?

La gema instalada es: ` gem 'colorputs', '~> 0.2.3' `.

Si se publicara una nueva versión, se instalaría en el proyecto. En el Gemfile.lock se puede especificar la o las versiones que se deberian requerir de una gema. Una gema puede tener especificada una o mas versiones.

http://guides.rubygems.org/patterns/#pessimistic-version-constraint

Para que no cambie la versión mayor de la misma se deberia cambiar en el Gemfile por alguna de estas dos sentencias:

` gem 'colorputs', '<= 0.2' ` 
` gem ‘colorputs’, ~> ‘0.2’ ` 

#### 4. ¿Qué ocurrió la primera vez que ejecutaste prueba.rb? ¿Por qué?

No funcionó, ya que la gema solicitada no estaba instalada.

#### 5. ¿Qué cambió al ejecutar bundle install?

` bundle install ` instaló la gema en el sistema. Desde este momento, la ejecución de prueba.rb no falló.

#### 6. ¿Qué diferencia hay entre bundle install y bundle update?

- bundle install : Instala las dependencias especificadas en el Gemfile.
- bundle update : Actualiza las librerías que se encuentran intaladas en el proyecto.

https://stackoverflow.com/questions/16495626/difference-between-bundle-install-and-bundle-update

#### 7. ¿Qué ocurrió al ejecutar prueba_dos.rb de las distintas formas enunciadas? ¿Por qué? ¿Cómo modificarías el archivo prueba_dos.rb para que funcione correctamente?

Al ejecutar ` ruby prueba_dos.rb `  ocurrió un error (uninitialized constant Bundler (NameError)).
Al ejecutar ` bundle exec ruby prueba_dos.rb ` funcionó perfectamente.

La razón es que ` bundle exec ` ejecuta el script en el contexto del bundle actual definido por Gemfile.

Para que funcione de la primer forma, habría que agregar la siguiente línea: ` require 'bundler/setup' `.

### 8. Desarrollá una gema (llamada MethodCounter, por ejemplo) que empaquete toda la funcionalidad implementada en el ejercicio 4 de la práctica 2 (el módulo Countable).

Para desarrollar esta gema, el procedimiento fue el siguiente:

1- En el directorio sobre el cual se desarrolló la gema, debe haber un archivo ` .gemspec `. Este archivo nos brinda información acerca de la gema (por ejemplo el nombre del autor, versión de la gema, etc).
2- Además de ese archivo, debe haber una carpeta 'lib'.
3- Dentro de esta carpeta debe estar el archivo .rb del método en cuestión (en este caso, el archivo del ejercicio 4 de la práctica 2).
4- El nombre del archivo .rb que está dentro de /lib debe tener el mismo nombre que la gema. Esto es para después poder usarla con ` require 'nombre_gema' `.
5- Luego hay que instalar la gema. Lo hacemos con: ` gem install nombre_gema-numero_version.gem `.

Luego de esto, podemos usar ` 'require nombre_gema' `, y funcionará correctamente. En este caso, será ` 'require countable' `. 

# Sinatra

## 1. ¿Qué es Rack? ¿Qué define? ¿Qué requisitos impone?

Sinatra se apoya en rack, una interface con el webserver modular.
La capacidad más importante de rack es la de soportar middlewares: Esto es, componentes que operan entre la aplicación y el web server monitoreando o manipulando los reqs/resp HTTP proveyendo así de varios tipos de funcionalidades comunes.
En sinatra, es simple utilizar los middlewares Rack con el método use

Rack es una gema que provee una interface entre WebServers y Frameworks basados en Ruby.

Para usar Rack, proporcione una "aplicación": un objeto que responde al método de llamada, tomando el hash de entorno como parámetro y devolviendo una matriz con tres elementos:

- El código de respuesta HTTP.
- Un hash de encabezados.
- El cuerpo de respuesta, que debe responder a cada uno. 

## 3. Sinatra se define como "DSL para crear aplicaciones web". ¿Qué quiere decir esto? ¿Qué es un DSL?

Domain Specific Languages: Lenguaje de programación enfocado en un dominio de aplicación en particular.

Sinatra se considera una DSL ya que utilizando principios de intercambios de datos REST relaciona los llamados endpoints con requisitos HTTP en un dominio de aplicación en específico.

DSL es un lenguaje de programación o especificación dedicado a resolver un problema en particular, representar un problema específico y proveer una técnica para solucionar una situación particular. El concepto no es nuevo pero se ha vuelto más popular debido al aumento del uso de modelaje específico del dominio.
