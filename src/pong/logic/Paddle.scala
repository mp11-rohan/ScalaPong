package pong.logic

import pong.logic.Constants.* 


case class Paddle(x: Float, y: Float) {

  def moveBy(dy: Float): Paddle = copy(x = this.x, y = (this.y + dy).max(0).min(FieldHeight - PaddleHeight))

}

object Paddle {

  def left: Paddle = Paddle(x = LeftPaddleX, y = PaddleStartY)   
  def right: Paddle = Paddle(x = RightPaddleX, y = PaddleStartY)  

}
