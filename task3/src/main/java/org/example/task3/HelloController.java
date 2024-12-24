package org.example.task3;

import Model.Model;
import ObjReader.ObjReader;
import Render.TestRenderer;
import Vectors.Vector3f;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import Camera.Camera;
public class HelloController {
    private Model mesh = null;
    private Camera camera;
    @FXML
    Canvas mainCanvas;

    @FXML
    AnchorPane anchorPane;

    @FXML
    void initialize() throws IOException {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> mainCanvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> mainCanvas.setHeight(newValue.doubleValue()));
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double width = mainCanvas.getWidth();
                double height = mainCanvas.getHeight();
                if(mesh!=null) {
                    TestRenderer.render(mesh, camera, mainCanvas.getGraphicsContext2D(), (int)width, (int)height);
                }
            }
        };
        gameLoop.start();
        Platform.runLater(() -> {
            camera = new Camera(new Vector3f(0, 0, -10.0f), new Vector3f(0, 0, 0), 60, (float)(mainCanvas.getWidth()/mainCanvas.getHeight()), 1, 100);
            mainCanvas.setWidth(800);
            mainCanvas.setHeight(600);
        });
    }
    @FXML
    private void onOpenModelMenuItemClick() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());
        try {
            String fileContent = Files.readString(fileName);
            mesh = ObjReader.read(fileContent);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}