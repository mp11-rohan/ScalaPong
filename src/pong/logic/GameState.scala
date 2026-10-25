package pong.logic

case class GameState(
  ball: Ball,
  leftPaddle: Paddle,
  rightPaddle: Paddle,
  leftScore: Int,
  rightScore: Int
)

object GameState {

  def initial: GameState = GameState(ball = Ball.initial, leftPaddle = Paddle.left, rightPaddle = Paddle.right, leftScore = 0, rightScore = 0)  // initial ball, both paddles, scores 0-0

}
