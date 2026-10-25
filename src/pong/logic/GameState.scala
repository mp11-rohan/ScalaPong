package pong.logic

sealed abstract class Phase
case object Playing extends Phase
case object GameOver extends Phase

case class GameState(
  ball: Ball,
  leftPaddle: Paddle,
  rightPaddle: Paddle,
  leftScore: Int,
  rightScore: Int,
  phase: Phase
)

object GameState {

  def initial: GameState = GameState(ball = Ball.initial, leftPaddle = Paddle.left, rightPaddle = Paddle.right, leftScore = 0, rightScore = 0, phase = Playing)

}