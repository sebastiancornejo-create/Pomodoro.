# PomodoroJetBrains

Aplicación de escritorio creada con Kotlin + Compose Multiplatform para ejecutarse en JetBrains IntelliJ IDEA.

## Funciones
- 25 minutos de trabajo
- 5 minutos de descanso
- Iniciar
- Pausar
- Reiniciar
- Cuenta regresiva mm:ss
- Cambio automático de trabajo a descanso
- Contador de pomodoros
- Mensajes de estado
- Uso de LaunchedEffect

## Ejecutar
1. Abrir la carpeta `PomodoroJetBrains` en IntelliJ IDEA.
2. Esperar la sincronización de Gradle.
3. Ejecutar la tarea `composeApp > Tasks > compose desktop > run`.

También puedes ejecutar desde la terminal:

`./gradlew :composeApp:run`

En Windows:

`gradlew.bat :composeApp:run`

## GitHub
Después de probarlo:

`git init`
`git add .`
`git commit -m "Pomodoro inicial"`
`git branch -M main`
`git remote add origin URL_DE_TU_REPOSITORIO`
`git push -u origin main`
