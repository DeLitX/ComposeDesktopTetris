package models.cells

import Position
import models.RelativeClass

class RelativeCell(
    relativePosition: Position,
    cell: Cell
) : RelativeClass<Cell>(relativePosition, cell) {
    override fun <L>addPosition(anotherClass: RelativeClass<L>): RelativeCell {
        return RelativeCell(
            position+anotherClass.position,
            item
        )
    }

    override fun addPosition(anotherPosition: Position): RelativeCell {
        return RelativeCell(
            position+anotherPosition,
            item
        )
    }

}
