import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.jetbrains.skija.Image
import java.io.File

@Composable
fun loadImageFromFile(file: File): ImageBitmap {
    return Image.makeFromEncoded(file.readBytes()).asImageBitmap()
}