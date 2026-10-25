package pong.logic

import pong.logic.Constants.*
import pong.logic.Collision.bounceOffWalls
import pong.logic.Collision.bounceOffPaddle

object GameLogic {

  // One frame: returns the next state
  def step(state: GameState, controls: Controls): GameState = moveBall(movePaddles(state = state, controls = controls))

  private def movePaddles(state: GameState, controls: Controls): GameState = state.copy(
        leftPaddle = state.leftPaddle.moveBy(PaddleSpeed*controls.leftDirection),
        rightPaddle = state.rightPaddle.moveBy(PaddleSpeed*controls.rightDirection)
        )

  private def moveBall(state: GameState): GameState = {
    val ballMoved = state.ball.move()
    val afterWalls = bounceOffWalls(ballMoved)
    val afterLeftPaddle = bounceOffPaddle(afterWalls, state.leftPaddle)
    val afterRightPaddle = bounceOffPaddle(afterLeftPaddle, state.rightPaddle)
    state.copy(ball = afterRightPaddle)
  }
}
