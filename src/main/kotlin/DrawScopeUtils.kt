import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawBorder(topLeft: Offset,cellSize:Float){
    drawRect(color = Color.Black, topLeft = topLeft, size = Size(cellSize, cellSize),style = Stroke(width = 0.1f))
}