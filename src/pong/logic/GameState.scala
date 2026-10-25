package pong.logic

sealed abstract class Phase
case object Playing extends Phase
case object GameOver extends Phase
case object MainMenu extends Phase

case class GameState(
  ball: Ball,
  leftPaddle: Paddle,
  rightPaddle: Paddle,
  leftScore: Int,
  rightScore: Int,
  phase: Phase,
  speed: Speed,
  pauseFrames: Int,
  mode: GameMode
)

object GameState {

  def initial: GameState = newGame(Normal, TwoPlayers).copy(phase = MainMenu)
  def newGame(speed: Speed, mode: GameMode): GameState = GameState(ball = Ball.serve(direction = 1, speed = speed.ballSpeed), leftPaddle = Paddle.left, rightPaddle = Paddle.right, leftScore = 0, rightScore = 0, phase = Playing, speed=speed, pauseFrames = 0, mode = mode)
}