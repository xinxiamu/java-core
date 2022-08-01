/*
 * The MIT License
 * Copyright © 2014-2019 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.design.pattern.command.sample2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 调用者
 * Wizard is the invoker of the commands.
 */
public class Wizard {

  private Deque<Command> undoStack = new LinkedList<>(); //撤销命令队列
  private Deque<Command> redoStack = new LinkedList<>(); //重启命令队列

  public Wizard() {
    // comment to ignore sonar issue: LEVEL critical
  }

  /**
   * Cast spell.
   */
  public void castSpell(Command command, Target target) {
    System.out.println(this.getClass().getSimpleName()  + " casts " + command + " at " + target);
    command.execute(target);
    undoStack.offerLast(command);
  }

  /**
   * Undo last spell.
   */
  public void undoLastSpell() {
    if (!undoStack.isEmpty()) {
      var previousSpell = undoStack.pollLast();
      redoStack.offerLast(previousSpell);
      System.out.println(this.getClass().getSimpleName() + " undoes " + previousSpell);
      previousSpell.undo();
    }
  }

  /**
   * Redo last spell.
   */
  public void redoLastSpell() {
    if (!redoStack.isEmpty()) {
      var previousSpell = redoStack.pollLast();
      undoStack.offerLast(previousSpell);
      System.out.println(this.getClass().getSimpleName() + " redoes " + previousSpell);
      previousSpell.redo();
    }
  }

  @Override
  public String toString() {
    return "Wizard";
  }
}
