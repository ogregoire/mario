/*
 * Copyright 2015 Olivier Grégoire.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.ogregoire.mario.engine.component;

import com.artemis.Component;

/**
 *
 * @author Olivier Grégoire
 */
public class Bounds extends Component {

  public int left;
  public int bottom;
  public int right;
  public int top;

  public Bounds(final int width, final int height) {
    this.left = 0;
    this.bottom = 0;
    this.right = width;
    this.top = height;
  }

  public Bounds(final int left, final int bottom, final int right, final int top) {
    this.left = left;
    this.bottom = bottom;
    this.right = right;
    this.top = top;
  }

  public int centerX() {
    return left + (right - left) / 2;
  }

  public int centerY() {
    return bottom + (top - bottom) / 2;
  }
}
