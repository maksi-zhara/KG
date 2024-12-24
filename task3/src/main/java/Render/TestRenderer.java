package Render;

import Matrixes.Matrix4f;
import Model.Polygon;
import Vectors.Vector2f;
import Vectors.Vector3f;
import Vectors.Vector4f;
import javafx.scene.canvas.GraphicsContext;
import Model.Model;
import Camera.Camera;
import ru.vsu.cs.affineTransormation.SubAffine;

import java.util.ArrayList;
import java.util.List;

public class TestRenderer {
    public static void render(
            final Model model,
            final Camera camera,
            final GraphicsContext graphicsContext,
            final float width,
            final float height) {
        graphicsContext.clearRect(0, 0, width, height);
        SubAffine affine = new SubAffine();
        affine.add(model.getTranslation());
        affine.add(model.getRotation());
        affine.add(model.getScale());
        camera.setAspectRatio(width / height);
        Matrix4f modelMatrix = affine.getMatrix();
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f perspectiveMatrix = camera.getProjectionMatrix();

        Matrix4f modelView = viewMatrix.matrixProduct(modelMatrix);
        final int nPolygons = model.polygons.size();
        outer:
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            Polygon polygon = model.polygons.get(polygonInd);
            final int nVerticesInPolygon = model.polygons.get(polygonInd).getVertexIndices().size();
            List<Vector3f> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = model.vertices.get(polygon.getVertexIndices().get(vertexInPolygonInd));

                Vector4f vertex4f = new Vector4f(vertex.getX(), vertex.getY(), vertex.getZ(), 1.0f);
                Vector4f vertex4fEye = modelView.multiplyByVector(vertex4f);
                float wc = -vertex4fEye.getZ();
                Vector4f vertex4fClip = perspectiveMatrix.multiplyByVector(vertex4fEye);
                if(Math.abs(vertex4fClip.getX()) > vertex4fClip.getW() || Math.abs(vertex4fClip.getY()) > vertex4fClip.getW() || Math.abs(vertex4fClip.getZ()) > vertex4fClip.getW()) {
                    continue outer;
                }
                Vector3f ndc = new Vector3f(vertex4fClip.getX() / wc, vertex4fClip.getY() / wc, vertex4fClip.getZ() / wc);
                Vector3f resultPoint = GraphicsConveyor.vertexToPoint(ndc, (int)width, (int)height);
                resultPoints.add(resultPoint);
            }
            drawEdges(graphicsContext, resultPoints, nVerticesInPolygon);

        }

    }
    private static void drawEdges(GraphicsContext graphicsContext, List<Vector3f> points, int nVerticesInPolygon) {
        for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
            graphicsContext.strokeLine(
                    points.get(vertexInPolygonInd - 1).getX(),
                    points.get(vertexInPolygonInd - 1).getY(),
                    points.get(vertexInPolygonInd).getX(),
                    points.get(vertexInPolygonInd).getY());
        }

        if (nVerticesInPolygon > 0)
            graphicsContext.strokeLine(
                    points.get(nVerticesInPolygon - 1).getX(),
                    points.get(nVerticesInPolygon - 1).getY(),
                    points.get(0).getX(),
                    points.get(0).getY());
    }
}
