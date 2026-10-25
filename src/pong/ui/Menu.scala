package pong.ui

import engine.GameBase
import engine.graphics.{Color, Point}
import pong.logic.*
import pong.logic.Constants.*

class Menu(game: GameBase) {

  private val TitleSize = 80
  private val TextSize = 30
  private val HintSize = 18

  def draw(speed: Speed, mode: GameMode): Unit = {
    val centerX = FieldWidth / 2
    game.setBackground(Color.Black)
    game.setFillColor(Color.White)
    game.drawTextCentered("PONG", TitleSize, Point(centerX, FieldHeight / 4))
    game.drawTextCentered(s"< ${mode.label} >", TextSize, Point(centerX, FieldHeight / 2 - 30))
    game.drawTextCentered(s"< ${speed.label} >", TextSize, Point(centerX, FieldHeight / 2 + 15))
    game.drawTextCentered("Up / Down: players    Left / Right: ball speed", HintSize, Point(centerX, FieldHeight / 2 + 60))
    game.drawTextCentered("Enter: start", HintSize, Point(centerX, FieldHeight / 2 + 85))
    game.drawTextCentered("W / S and Up / Down: move paddles", HintSize, Point(centerX, FieldHeight * 3 / 4 + 30))
  }
}