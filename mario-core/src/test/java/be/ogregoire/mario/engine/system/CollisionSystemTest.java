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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import be.ogregoire.mario.engine.component.Bounds;
import be.ogregoire.mario.engine.component.Position;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.artemis.World;

/**
 *
 * @author Olivier Grégoire
 */
public class CollisionSystemTest {

  public CollisionSystemTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private CollisionSystem instance;
  private World world;

  @Before
  public void setUp() {
    world = new World();

    instance = new CollisionSystem();
    instance.boundsMapper = mock(ComponentMapper.class);
    instance.positionMapper = mock(ComponentMapper.class);

  }

  @After
  public void tearDown() {
    instance = null;
  }

  private Entity createEntity(Position p, Bounds b) {
    Entity e = world.createEntity();
    when(instance.boundsMapper.getSafe(e)).thenReturn(b);
    when(instance.positionMapper.getSafe(e)).thenReturn(p);
    return e;
  }

  /**
   * Test of overlaps method, of class CollisionSystem.
   */
  @Test
  public void testOverlaps() {
    OverlapsTester overlaps = new OverlapsTester(new Position(0, 0), new Bounds(1, 1, 4, 4));

    Bounds square = new Bounds(0, 0, 1, 1);

    overlaps.testDoOverlap(new Position(0, 0), square); // bottom-left
    overlaps.testDoOverlap(new Position(4, 4), square); // top-right
    overlaps.testDoOverlap(new Position(4, 1), square);
    overlaps.testDoOverlap(new Position(0, 1), square);
    overlaps.testDoOverlap(new Position(0, 1), square);
    overlaps.testDoOverlap(new Position(0, 1), square);
  }

  private class OverlapsTester {

    Entity entity;

    OverlapsTester(Position p, Bounds b) {
      entity = createEntity(p, b);
    }

    void testDoOverlap(Position p, Bounds b) {
      testOverlap(p, b, true);
    }

    void testNoOverlap(Position p, Bounds b) {
      testOverlap(p, b, false);
    }

    private void testOverlap(Position p, Bounds b, boolean overlap) {
      assertThat(instance.overlaps(entity, createEntity(p, b)), is(overlap));
    }
  }
}
