package ru.vsu.cs.task3.Primitives;

import ru.vsu.cs.task3.Math.Vectors.Vector3F;

import java.util.ArrayList;

public class Cube {
    public ArrayList<Vector3F> vertices;
    public Cube() {
        vertices = new ArrayList<Vector3F>();
        vertices.add(new Vector3F(0, 0, 0));
        vertices.add(new Vector3F(1, 0, 0));
        vertices.add(new Vector3F(1, 1, 0));
        vertices.add(new Vector3F(0, 1, 0));

        vertices.add(new Vector3F(0, 0, 1));
        vertices.add(new Vector3F(1, 0, 1));
        vertices.add(new Vector3F(1, 1, 1));
        vertices.add(new Vector3F(0, 1, 1));
    }
}
