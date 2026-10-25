package pong.ui

import engine.GameBase
import engine.graphics.{Color, Point, Rectangle}
import pong.logic.*
import pong.logic.Constants.*
import engine.graphics.Color.Gray

class Renderer(game: GameBase) {

  // UI-only numbers, named instead of left as magic numbers
  private val ScoreTextSize = 40
  private val ScoreY = 60
  private val DashHeight = 20
  private val DashWidth = 4

  def draw(state: GameState): Unit = {
    game.setBackground(Color.Black)
    drawCenterLine()
    drawPaddle(state.leftPaddle)
    drawPaddle(state.rightPaddle)
    drawBall(state.ball)
    drawScores(state = state)
    state.phase match {
        case GameOver => drawGameOver(state = state)
        case Playing => ()
    }
  }

  private def drawCenterLine(): Unit = {
    game.setFillColor(Color.Gray)
    for(h <- 0 until FieldHeight by 2*DashHeight) {
      game.drawRectangle(Rectangle(leftUp = Point((FieldWidth - DashWidth)/2, h), width = DashWidth, height = DashHeight))
    }
  }

  private def drawPaddle(paddle: Paddle): Unit = {
    game.setFillColor(Color.White)
    game.drawRectangle(Rectangle(leftUp = Point(paddle.x, paddle.y), width = PaddleWidth, height = PaddleHeight))
  }

  private def drawBall(ball: Ball): Unit = {
    game.setFillColor(Color.White)
    game.drawRectangle(Rectangle(leftUp = Point(BallStartX, BallStartY), width = BallSize, height = BallSize))
  }

  private def drawScores(state: GameState): Unit = {
    game.setFillColor(Color.White)
    game.drawTextCentered(string = state.leftScore.toString(), size= ScoreTextSize, center = Point(FieldWidth/4, ScoreY)) // Left score
    game.drawTextCentered(string = state.rightScore.toString(), size = ScoreTextSize, center = Point(3*FieldWidth/4, ScoreY)) // Right score
  }

  private def drawGameOver(state: GameState): Unit = {
    val winnerText = if(state.leftScore>state.rightScore) "Left player won!" else "Right player won!"
    val center = Point(FieldWidth/2, FieldHeight/2)
    game.setFillColor(Color.Red)
    game.drawTextCentered(string = "GAME OVER", size = ScoreTextSize, center = center)
    game.setFillColor(Color.White)
    game.drawTextCentered(string = winnerText, size = ScoreTextSize / 3, center = center + Point(0, ScoreTextSize + 5))
  }
}
