/*
 * Copyright (C) 2011 The Android Open Source Project
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
package shape;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * A two-dimensional triangle for use as a drawn object in OpenGL ES 1.0/1.1.
 */
public class Triangle {

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private static final String TAG = "Triangle";

    static float triangleCoords[] = {
            0.0f, 1.0f, -5.0f,
            1.0f, 0.0f, -5.0f,
            -1.0f, 0.0f, -5.0f,

            -1.0f, 0.0f, -5.0f,
            1.0f, 0.0f, -5.0f,
            0.0f, -1.0f, -5.0f
    };

    private final FloatBuffer vertexBuffer;

    float color[] = {1.0f, 0.0f, 0.0f, 1.0f};

    float ROTATION = 0.0f;

    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    public Triangle() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param gl - The OpenGL ES context in which to draw this shape.
     */
    public void draw(GL10 gl) {
        Log.d(TAG, "draw");

        //gl.glRotatef(ROTATION, 0.0f, 0.0f, 1.0f);

        gl.glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glColor4f(color[0], color[1], color[2], color[3]);
        gl.glVertexPointer(COORDS_PER_VERTEX, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, triangleCoords.length);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

    }
}
