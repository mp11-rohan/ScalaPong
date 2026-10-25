package pong.logic

import engine.graphics.Point
import pong.logic.Constants.* 

case class Ball(position: Point, velocity: Point) {

  def move(): Ball = copy(position = this.position + this.velocity, velocity = this.velocity)
}

object Ball {

  def initial: Ball = serve(direction = 1)
  def serve(direction: Int): Ball = Ball(position = Point(BallStartX, BallStartY), velocity = Point(BallStartSpeed*direction, 0))
}