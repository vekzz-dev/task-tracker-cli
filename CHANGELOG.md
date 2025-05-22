## Release v1.0.0 - 2025-05-21

### Features

- (model): Implementar nuevo formato de toString para cada tarea
- (cli): Agregar mejor paseador de input y validaciones
- (core): Inicializar inyección de dependencias en clase TaskTrackerCLI
- (controller): Implementar lógica de clase TaskController para add, update, list, delete y mark
- (service): Implementar lógica para add, update, delete, mark y list en clase TaskService
- (cli): Implementar clase TaskView para la impresión de tareas en consola
- (service): Implementar lógica para agregar tareas en TaskService
- (service): Inyectar dependencias de IdRepository y TaskRepository en TaskService
- (repository): Implementar clase FileIdRepository para la persistencia de id
- (repository): Implementar la clase FileTaskRepository para escribir y leer datos en archivos
- (utils): Implementar la clase JsonParser que permite serializar y deserializar tasks
- (model): Agregar metodos get para id y createdAt
- (repository): Agregar interfaz IdRepository para la gestión de identificadores de tareas
- (repository): Agregar interfaz TaskRepository para gestión de tareas
- (service): Definir firma de métodos add, update, delete, mark y list
- (service): Crear clase TaskService
- (controller): Implementar lógica para validar la cantidad de argumentos
- (model): Implementar clase Task como entidad base
- (model): Implementar enums de estado done, todo e in-progress
- (cli): Registrar comando help e implementar su lógica
- (cli): Registrar comando exit e implementar su lógica
- (cli): Implementar lógica de ejecución de comandos
- (exception): Agregar excepción de comando no encontrado
- (cli): Implementar logica de escaneo y procesamiento de comandos con argumentos
- (cli): Registar comandos add, list, update, mark y delete
- (controller): Definir firma de métodos add, update, list, mark y delete
- (controller): Crear clase TaskController
- (cli): Crear clase CommandManager

### Bug Fixes

- (service): Convertir la lista inmutable a mutable
- (service): Cambiar el estatus de 'in-progress' a 'todo' al crear una tarea
- (utils): Agregar validación de retorno al deserializar un Json si esta vacío
- (repository): Cambiar el parametro Task del metodo save por el parametro List<Task>
- (controller): Corregir límites de args para comando add

### Documentation

- actualizar changelog para v1.0.0
- Quitar divisiones en README.md
- Actualizar README.md
- Incluir licencia MIT

### Styles

- (core): Eliminar espacio vacío en TaskTrackerCLI
- (controller): Agregar espaciado e identación entre métodos
- (cli): Mejorar redacción del input prompt

### Refactoring

- (utils): Cambiar metodos de instancia por estáticos en JsonParser y su llamada en FileTaskRepository
- (core): Extraer el método ensureFileExists e implementarlo en clase FileUtils para reutilizarlo
- (model): Cambiar orden de parametros del constructor y modificar la salida toString
- (cli): Cambiar llamada de métodos estáticos de TaskController a llamada por instancia
- (cli): convertir métodos exit, executeCommand y printHelp a métodos de instancia
- (core): Implementar inyección de dependencias en CommandManager, TaskController
- (cli): Renombrar método main por run y convertir en método de instancia
- (controller): Convertir métodos estáticos a métodos de instancia
- (controller): Hacer void el metodo de validación de args y ajustar visibilidad

### Chores

- Agregar manifiesto de clase principal en pom.xml
- Update README.md
- Cambiar versión del proyecto en pom.xml
- (core): Transcribir de idioma español a inglés todos los mensajes
- Agregar README.md y CHANGELOG.md
- Commit inicial

### Other

- add(model): Agregar descripciones a los enums de Status

## Release v1.0.0 - 2025-05-21

### Features

- (model): Implementar nuevo formato de toString para cada tarea
- (cli): Agregar mejor paseador de input y validaciones
- (core): Inicializar inyección de dependencias en clase TaskTrackerCLI
- (controller): Implementar lógica de clase TaskController para add, update, list, delete y mark
- (service): Implementar lógica para add, update, delete, mark y list en clase TaskService
- (cli): Implementar clase TaskView para la impresión de tareas en consola
- (service): Implementar lógica para agregar tareas en TaskService
- (service): Inyectar dependencias de IdRepository y TaskRepository en TaskService
- (repository): Implementar clase FileIdRepository para la persistencia de id
- (repository): Implementar la clase FileTaskRepository para escribir y leer datos en archivos
- (utils): Implementar la clase JsonParser que permite serializar y deserializar tasks
- (model): Agregar metodos get para id y createdAt
- (repository): Agregar interfaz IdRepository para la gestión de identificadores de tareas
- (repository): Agregar interfaz TaskRepository para gestión de tareas
- (service): Definir firma de métodos add, update, delete, mark y list
- (service): Crear clase TaskService
- (controller): Implementar lógica para validar la cantidad de argumentos
- (model): Implementar clase Task como entidad base
- (model): Implementar enums de estado done, todo e in-progress
- (cli): Registrar comando help e implementar su lógica
- (cli): Registrar comando exit e implementar su lógica
- (cli): Implementar lógica de ejecución de comandos
- (exception): Agregar excepción de comando no encontrado
- (cli): Implementar logica de escaneo y procesamiento de comandos con argumentos
- (cli): Registar comandos add, list, update, mark y delete
- (controller): Definir firma de métodos add, update, list, mark y delete
- (controller): Crear clase TaskController
- (cli): Crear clase CommandManager

### Bug Fixes

- (service): Convertir la lista inmutable a mutable
- (service): Cambiar el estatus de 'in-progress' a 'todo' al crear una tarea
- (utils): Agregar validación de retorno al deserializar un Json si esta vacío
- (repository): Cambiar el parametro Task del metodo save por el parametro List<Task>
- (controller): Corregir límites de args para comando add

### Documentation

- Quitar divisiones en README.md
- Actualizar README.md
- Incluir licencia MIT

### Styles

- (core): Eliminar espacio vacío en TaskTrackerCLI
- (controller): Agregar espaciado e identación entre métodos
- (cli): Mejorar redacción del input prompt

### Refactoring

- (utils): Cambiar metodos de instancia por estáticos en JsonParser y su llamada en FileTaskRepository
- (core): Extraer el método ensureFileExists e implementarlo en clase FileUtils para reutilizarlo
- (model): Cambiar orden de parametros del constructor y modificar la salida toString
- (cli): Cambiar llamada de métodos estáticos de TaskController a llamada por instancia
- (cli): convertir métodos exit, executeCommand y printHelp a métodos de instancia
- (core): Implementar inyección de dependencias en CommandManager, TaskController
- (cli): Renombrar método main por run y convertir en método de instancia
- (controller): Convertir métodos estáticos a métodos de instancia
- (controller): Hacer void el metodo de validación de args y ajustar visibilidad

### Chores

- Cambiar versión del proyecto en pom.xml
- (core): Transcribir de idioma español a inglés todos los mensajes
- Agregar README.md y CHANGELOG.md
- Commit inicial

### Other

- add(model): Agregar descripciones a los enums de Status

