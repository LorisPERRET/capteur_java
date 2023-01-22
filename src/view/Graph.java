package view;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.Capteur.CapteurAbstrait;

public class Graph extends Visualisateur{
    private CapteurAbstrait capteur;
    private static int time = 0;
    private XYChart.Series series;
    @FXML
    private Label labelCapteur;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart<?, ?> lineChart;

    public Graph(CapteurAbstrait capteur) {
        this.capteur = capteur;
        series = new XYChart.Series();
    }

    @FXML
    public void initialize(){
        capteur.attacher(this);
        String id = String.valueOf(capteur.getId());
        labelCapteur.setText(id);

        series.getData().add(new XYChart.Data(Integer.toString(time), capteur.getTemperature()));
        series.setName("Évolution de la température");

        lineChart.getData().addAll(series);
        lineChart.setCreateSymbols(false);
    }

    @Override
    public void update() {
        time++;
        series.getData().add(new XYChart.Data(Integer.toString(time), capteur.getTemperature()));
        if (series.getData().size() > 25)
            series.getData().remove(0);
    }
}
