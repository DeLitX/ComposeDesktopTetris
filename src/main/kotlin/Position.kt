data class Position(var x: Int, var y: Int) {
    operator fun plus(position: Position): Position {
        return Position(x + position.x, y + position.y)
    }
}