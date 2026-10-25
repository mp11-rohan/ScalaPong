package pong.ui

import java.awt.event.KeyEvent.VK_SPACE
import engine.GameBase
import pong.logic.*
import pong.logic.Constants.*
import processing.event.KeyEvent

class PongGame extends GameBase {

  private var state: GameState = GameState.initial
  private val input = new Input
  private val renderer: Renderer = new Renderer(this)
  private val updateTimer = new UpdateTimer(FramesPerSecond)

  override def draw(): Unit = {
    updateState()
    renderer.draw(state)
  }

  override def keyPressed(event: KeyEvent): Unit = {
    input.keyPressed(event = event)
    if(state.phase == GameOver && event.getKeyCode() == VK_SPACE) state = GameState.initial
  } 

  override def keyReleased(event: KeyEvent): Unit = input.keyReleased(event = event)

  override def settings(): Unit = {
    pixelDensity(displayDensity())
    size(FieldWidth, FieldHeight)
  }

  override def setup(): Unit = {
    text("", 0, 0)
    updateTimer.init()
  }

  private def updateState(): Unit = {
    if(updateTimer.timeForNextFrame()) {
      state = GameLogic.step(state = state, controls = input.controls)
      updateTimer.advanceFrame()
    }
  }
}
