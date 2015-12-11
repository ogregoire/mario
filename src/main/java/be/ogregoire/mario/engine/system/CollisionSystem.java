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
package be.ogregoire.mario.engine.system;

import be.ogregoire.mario.engine.component.Bounds;
import be.ogregoire.mario.engine.component.Position;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import net.mostlyoriginal.api.system.core.PassiveSystem;

/**
 *
 * @author Olivier Grégoire
 */
@Wire
public class CollisionSystem extends PassiveSystem {

  ComponentMapper<Bounds> boundsMapper;
  ComponentMapper<Position> positionMapper;

  public final boolean overlaps(final Entity a, final Entity b) {
    final Bounds aBounds = boundsMapper.getSafe(a);
    final Position aPosition = positionMapper.getSafe(a);
    final Bounds bBounds = boundsMapper.getSafe(b);
    final Position bPosition = positionMapper.getSafe(b);
    if (aBounds == null || aPosition == null || bBounds == null || bPosition == null) {
      return false;
    }
    return aPosition.x + aBounds.left <= bPosition.x + bBounds.right
        && aPosition.y + aBounds.bottom <= bPosition.y + bBounds.top
        && aPosition.x + aBounds.right >= bPosition.x + bBounds.left
        && aPosition.y + aBounds.top >= bPosition.y + bBounds.bottom;
  }

}
