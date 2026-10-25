package pong.logic

import pong.logic.Constants.*
import pong.logic.Collision.bounceOffWalls
import pong.logic.Collision.bounceOffPaddle

object GameLogic {

  // One frame: returns the next state
  def step(state: GameState, controls: Controls): GameState = state.phase match {
  case Playing  => checkWinner(scorePoint(moveBall(movePaddles(state = state, controls = controls)))) 
  case GameOver => state
}

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

  private def scorePoint(state: GameState): GameState = {
    if(state.ball.position.x > FieldWidth) state.copy(leftScore = state.leftScore + 1, ball = Ball.serve(direction = 1))
    else if(state.ball.position.x + BallSize < 0) state.copy(rightScore = state.rightScore + 1, ball = Ball.serve(direction = -1))
    else state
  }

  private def checkWinner(state: GameState): GameState = 
    if(state.leftScore >= WinningScore || state.rightScore >= WinningScore) state.copy(phase = GameOver) else state
}
