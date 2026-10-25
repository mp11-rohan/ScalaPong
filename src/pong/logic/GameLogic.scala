package pong.logic

import pong.logic.Constants.*

object GameLogic {

  // One frame: returns the next state
  def step(state: GameState, controls: Controls): GameState = moveBall(movePaddles(state = state, controls = controls))

  private def movePaddles(state: GameState, controls: Controls): GameState = state.copy(
        leftPaddle = state.leftPaddle.moveBy(PaddleSpeed*controls.leftDirection),
        rightPaddle = state.rightPaddle.moveBy(PaddleSpeed*controls.rightDirection)
        )

  private def moveBall(state: GameState): GameState = state.copy(ball = state.ball.move())
}
