package com.scottejames.util

data class Coord(val x: Int, val y: Int) : Comparable<Coord> {

    override fun compareTo(other: Coord): Int {
        val xs = y.compareTo(other.y)
        if( xs == 0 ) return x.compareTo(other.x)
        return xs
    }

    fun move(d: Direction): Coord {
        return Coord(this.x+d.getDx(), this.y+d.getDy())
    }
}
