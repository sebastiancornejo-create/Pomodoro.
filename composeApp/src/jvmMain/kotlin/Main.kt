import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

private const val TRABAJO = 25 * 60
private const val DESCANSO = 5 * 60

enum class Modo(val titulo: String, val duracion: Int) {
    TRABAJO("TRABAJO", TRABAJO),
    DESCANSO("DESCANSO", DESCANSO)
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Pomodoro"
    ) {
        PomodoroApp()
    }
}

@Composable
fun PomodoroApp() {
    var modo by remember { mutableStateOf(Modo.TRABAJO) }
    var segundos by remember { mutableStateOf(TRABAJO) }
    var activo by remember { mutableStateOf(false) }
    var pomodoros by remember { mutableStateOf(0) }
    var mensaje by remember { mutableStateOf("Listo para comenzar") }

    LaunchedEffect(activo, segundos, modo) {
        if (!activo) return@LaunchedEffect

        if (segundos > 0) {
            delay(1000)
            segundos--
        } else {
            if (modo == Modo.TRABAJO) {
                pomodoros++
                modo = Modo.DESCANSO
                segundos = DESCANSO
                mensaje = "Trabajo terminado. Es hora de descansar."
            } else {
                modo = Modo.TRABAJO
                segundos = TRABAJO
                mensaje = "Descanso terminado. Volvamos al trabajo."
            }
        }
    }

    val minutos = segundos / 60
    val segundosRestantes = segundos % 60

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "🍅 POMODORO",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            Text(
                modo.titulo,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(15.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = 6.dp
            ) {
                Text(
                    "%02d:%02d".format(minutos, segundosRestantes),
                    modifier = Modifier.fillMaxWidth().padding(35.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(18.dp))

            Text("Pomodoros completados: $pomodoros")

            Spacer(Modifier.height(10.dp))

            Text(
                mensaje,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(22.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = {
                    activo = !activo
                    mensaje = if (activo) "Temporizador iniciado" else "Temporizador pausado"
                }) {
                    Text(if (activo) "PAUSAR" else "INICIAR")
                }

                OutlinedButton(onClick = {
                    activo = false
                    segundos = modo.duracion
                    mensaje = "Ciclo reiniciado"
                }) {
                    Text("REINICIAR")
                }
            }

            Spacer(Modifier.height(18.dp))

            Text("25 minutos de trabajo + 5 minutos de descanso")
        }
    }
}
