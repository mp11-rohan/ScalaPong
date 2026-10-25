package pong.logic

import pong.logic.Constants.*
import pong.logic.Collision.bounceOffWalls
import pong.logic.Collision.bounceOffPaddle

object GameLogic {

  // One frame: returns the next state
  def step(state: GameState, controls: Controls): GameState = state.phase match {
  case Playing  =>
    if (state.pauseFrames > 0) tickPause(state)
    else scorePoint(moveBall(movePaddles(state, controls))) 
  case GameOver => state
  case MainMenu => state
}

  private def tickPause(state: GameState): GameState = {
    val ticked = state.copy(pauseFrames = state.pauseFrames - 1)
    if (ticked.pauseFrames == 0) checkWinner(ticked) else ticked
  }

  private def afterPoint(state: GameState): GameState = state.copy(
    leftPaddle = Paddle.left,
    rightPaddle = Paddle.right,
    pauseFrames = PointPauseFrames
  )

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
    val ball = state.ball
    if (ball.position.x + BallSize < 0)
      afterPoint(state.copy(rightScore = state.rightScore + 1, ball = Ball.serve(-1, state.speed.ballSpeed)))
    else if (ball.position.x > FieldWidth)
      afterPoint(state.copy(leftScore = state.leftScore + 1, ball = Ball.serve(1, state.speed.ballSpeed)))
    else state
}

  private def checkWinner(state: GameState): GameState = 
    if(state.leftScore >= WinningScore || state.rightScore >= WinningScore) state.copy(phase = GameOver) else state

  def changeSpeed(state: GameState, offset: Int): GameState = state.copy(speed = Speed.shifted(state.speed, offset))

  def startGame(state: GameState): GameState = GameState.newGame(state.speed)

  def backToMenu(state: GameState): GameState = GameState.initial.copy(speed = state.speed)
}
