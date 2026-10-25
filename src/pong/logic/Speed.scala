package pong.logic

sealed abstract class Speed(val label: String, val ballSpeed: Float)
case object Slow extends Speed("Slow", 5)
case object Normal extends Speed("Normal", 8)
case object Fast extends Speed("Fast", 10)

object Speed {

  val all: List[Speed] = List(Slow, Normal, Fast)

  def shifted(current: Speed, offset: Int): Speed = {
    val index = (all.indexOf(current) + offset).max(0).min(all.length - 1)
    all(index)
  }
}