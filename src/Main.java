import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Image;
import view.Slider;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/MainWindow.fxml"));
        Scene scene = new Scene(loader.load(), 640, 480);
        stage.setScene(scene);
        stage.show();

        Stage slider = new Stage();
        FXMLLoader loaderSlider = new FXMLLoader(getClass().getResource("/fxml/SliderWindow.fxml"));
        loaderSlider.setController(new Slider());
        Scene sceneSlider = new Scene(loaderSlider.load(), 200, 100);
        slider.setScene(sceneSlider);
        slider.show();

        Stage image = new Stage();
        FXMLLoader loaderImage = new FXMLLoader(getClass().getResource("/fxml/ImageWindow.fxml"));
        loaderImage.setController(new Image());
        Scene sceneImage = new Scene(loaderImage.load(), 200, 100);
        image.setScene(sceneImage);
        image.show();
    }

    public static void main(String[] args) {
        launch();
    }
}