import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import models.cells.Cell
import models.cells.EmptyCell
import models.cells.RelativeCell
import models.figures.*
import kotlin.random.Random

class GameEngine(
    val gameWidth: Int,
    val gameHeight: Int,
    val cellSize: Float,
) {
    private lateinit var _currentFigure: RelativeFigure
    private val _cells: MutableList<MutableList<Cell>> = MutableList(gameWidth) { x ->
        MutableList(gameHeight) { y ->
            EmptyCell(cellSize)
        }
    }

    /**
     * This is useless list for now. At first I wanted to redraw only cells, which have been changed,
     * but compose is not designed for this
     **/
    private val _cellsToRedraw = MutableList(gameWidth * gameHeight) { index ->
        RelativeCell(
            relativePosition = Position(index % gameHeight, index / gameHeight),
            cell = EmptyCell(cellSize)
        )
    }
    val isActive = mutableStateOf(true)

    init {
        createNewFigure()
    }

    fun randomColor(): Color {
        val red = Random.nextInt(256)
        val green = Random.nextInt(256)
        val blue = Random.nextInt(256)
        return Color(red, green, blue)
    }

    fun rotate() {
        val currentCells = _currentFigure.getAbsoluteCellsPosition()
        val newCells = _currentFigure.getAbsoluteCellsPosition(_currentFigure.item.rotatePositions())
        val oldCellsPositions = currentCells.map { it.position }
        val newCellsPositions = newCells.map { it.position }
        if (canBeLocated(newCells, oldCellsPositions)) {
            val cellsToDraw =
                currentCells
                    .filterNot { newCellsPositions.contains(it.position) }
                    .map { RelativeCell(relativePosition = it.position, cell = EmptyCell(cellSize)) }
                    .plus(newCells)
            _cellsToRedraw.addAll(cellsToDraw)
            _currentFigure.item.rotate()
            for (i in cellsToDraw) {
                _cells[i.position.x][i.position.y] = i.item
            }
        }
    }

    fun goLeft() {
        move(offset = Position(-1, 0))
    }

    fun goRight() {
        move(offset = Position(1, 0))
    }


    fun newFrame(drawScope: DrawScope) {
        if (isActive.value) {
            figureNextStep()
            drawCells(drawScope)
            _cellsToRedraw.clear()
        }
    }

    private fun drawCells(drawScope: DrawScope) {
        for (x in _cells.indices) {
            for (y in _cells[x].indices) {
                val cell = _cells[x][y]
                cell.paint(topLeft = Offset(x * cellSize, y * cellSize), drawScope)
            }
        }
    }

    private fun createNewFigure(): Boolean {
        val enumValues = FiguresEnum.values()
        val enumSize = enumValues.size
        val rand = Random.nextInt(enumSize)
        val color = randomColor()
        val figure = when (enumValues[rand]) {
            FiguresEnum.L -> {
                LFigure(cellSize = cellSize, color = color)
            }
            FiguresEnum.Square -> {
                SquareFigure(cellSize = cellSize, color = color)
            }
            FiguresEnum.Stick -> {
                StickFigure(cellSize = cellSize, color = color)
            }
            FiguresEnum.T -> {
                TFigure(cellSize = cellSize, color = color)
            }
            FiguresEnum.InvertedL -> {
                InvertedLFigure(cellSize = cellSize, color = color)
            }
            FiguresEnum.ZigZag -> {
                ZigZagFigure(cellSize = cellSize, color = color)
            }
        }
        val topLeftPoint = gameWidth / 2 - figure.currentWidth / 2
        val cells = figure.pattern
        cells.forEach { it.position.plus(Position(topLeftPoint, 0)) }
        return if (canBeLocated(cells)) {
            _currentFigure = RelativeFigure(Position(x = topLeftPoint, y = 0), figure)
            true
        } else {
            false
        }
    }

    private fun move(offset: Position, onError: () -> Unit = {}) {
        val currentRelativeCells = _currentFigure.getAbsoluteCellsPosition()
        val newRelativeCells = currentRelativeCells.map { it.addPosition(offset) }
        val oldCellsPositions = currentRelativeCells.map { it.position }
        val newCellsPositions = newRelativeCells.map { it.position }
        if (canBeLocated(newRelativeCells, oldCellsPositions)) {
            val cellsToDraw =
                currentRelativeCells
                    .filterNot { newCellsPositions.contains(it.position) }
                    .map { RelativeCell(relativePosition = it.position, cell = EmptyCell(cellSize)) }
                    .plus(newRelativeCells)
            _cellsToRedraw.addAll(cellsToDraw)
            _currentFigure.position += offset
            for (i in cellsToDraw) {
                _cells[i.position.x][i.position.y] = i.item
            }
        } else {
            onError()
        }
    }


    private fun isRowFiled(rowPosition: Int): Boolean {
        for (x in 0 until gameWidth) {
            if (_cells[x][rowPosition] is EmptyCell) {
                return false
            }
        }
        return true
    }

    private fun figureNextStep() {
        move(offset = Position(0, 1)) {
            for (y in _currentFigure.position.y until _currentFigure.position.y + _currentFigure.item.currentHeight) {
                if (isRowFiled(y)) {
                    clearRow(y)
                }
            }
            createNewFigure()
        }
    }

    private fun canBeLocated(cells: List<RelativeCell>, oldCellsPositions: List<Position> = listOf()): Boolean {
        val newCells = cells.filterNot { oldCellsPositions.contains(it.position) }
        try {
            for (cell in newCells) {
                if (_cells[cell.position.x][cell.position.y] !is EmptyCell) {
                    return false
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            return false
        }
        return true
    }

    private fun clearRow(rowPosition: Int) {
        for (x in 0 until gameWidth) {
            val cell = EmptyCell(cellSize)
            _cellsToRedraw.add(RelativeCell(Position(x, rowPosition), cell))
            _cells[x][rowPosition] = cell
        }
    }

}