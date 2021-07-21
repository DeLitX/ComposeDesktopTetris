import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import java.io.File

val cellSize = 20f
val gameWidth = 20
val gameHeight = 40
val engine = GameEngine(
    cellSize = cellSize,
    gameWidth = gameWidth,
    gameHeight = gameHeight
)
val isGamePaused = mutableStateOf(false)
val fps = mutableStateOf(0)
val fpsScope = CoroutineScope(Default)
var fpsJob: Job? = null
fun main() {
    startFps()
    Window(size = IntSize((gameWidth * cellSize + 20).toInt(), (gameHeight * cellSize + 40).toInt())) {
        MaterialTheme {
            LocalAppWindow.current.keyboard.setShortcut(Key.A) {
                if (!isGamePaused.value) {
                    engine.goLeft()
                }
            }
            LocalAppWindow.current.keyboard.setShortcut(Key.D) {
                if (!isGamePaused.value) {
                    engine.goRight()
                }
            }
            LocalAppWindow.current.keyboard.setShortcut(Key.Spacebar) {
                if (!isGamePaused.value) {
                    engine.rotate()
                }
            }
            LocalAppWindow.current.keyboard.setShortcut(Key.Escape) {
                if (isGamePaused.value) {
                    startFps()
                } else {
                    stopFps()
                }
            }
            Box() {
                Image(loadImageFromFile(File("image.png")), null)
                Canvas(
                    modifier = Modifier.width((gameWidth * cellSize).dp).height((gameHeight * cellSize).dp)
                ) {
                    fps.value
                    engine.newFrame(this)
                }
            }
        }
    }
}

fun startFps() {
    isGamePaused.value = false
    fpsJob = fpsScope.launch {
        while (!isGamePaused.value) {
            delay(1000 / 5)
            fps.value++
        }
    }
}

fun stopFps() {
    isGamePaused.value = true
    fpsJob?.cancel()
}
