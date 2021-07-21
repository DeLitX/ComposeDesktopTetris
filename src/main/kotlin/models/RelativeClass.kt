package models

import Position

abstract class RelativeClass<T>(var position: Position, val item: T) {
    abstract fun <L>addPosition(anotherClass: RelativeClass<L>): RelativeClass<T>
    abstract fun addPosition(anotherPosition:Position):RelativeClass<T>
}