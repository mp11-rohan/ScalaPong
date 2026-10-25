package pong.ui

import java.awt.event.KeyEvent.*
import pong.logic.Controls
import processing.event.KeyEvent

class Input {

  private var heldKeys: Set[Int] = Set.empty

  def keyPressed(event: KeyEvent): Unit = heldKeys += event.getKeyCode()

  def keyReleased(event: KeyEvent): Unit = heldKeys -= event.getKeyCode()

  def controls: Controls = Controls(leftDirection = direction(VK_W, VK_S), rightDirection = direction(VK_UP, VK_DOWN))

  // -1 if only the up key is held, 1 if only the down key, 0 otherwise
  private def direction(upKey: Int, downKey: Int): Int =
    toInt(heldKeys.contains(downKey)) - toInt(heldKeys.contains(upKey))

  private def toInt(held: Boolean): Int = if (held) 1 else 0
}
