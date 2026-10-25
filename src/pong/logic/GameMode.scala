package pong.logic

sealed abstract class GameMode(val label: String)
case object OnePlayer extends GameMode("1 Player")
case object TwoPlayers extends GameMode("2 Players")

object GameMode {

  def toggled(mode: GameMode): GameMode = mode match {
    case OnePlayer  => TwoPlayers
    case TwoPlayers => OnePlayer
  }
}