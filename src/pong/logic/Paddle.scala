package pong.logic

import pong.logic.Constants.LeftPaddleX
import pong.logic.Constants.RightPaddleX
import pong.logic.Constants.PaddleStartY

case class Paddle(x: Float, y: Float) {

  def moveBy(dy: Float): Paddle = copy(x = this.x, y = this.y + dy)

}

object Paddle {

  def left: Paddle = Paddle(x = LeftPaddleX, y = PaddleStartY)   
  def right: Paddle = Paddle(x = RightPaddleX, y = PaddleStartY)  

}
