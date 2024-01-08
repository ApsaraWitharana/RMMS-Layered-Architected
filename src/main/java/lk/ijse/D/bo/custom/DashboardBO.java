package lk.ijse.D.bo.custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface DashboardBO {

    ObservableList<XYChart.Series<String, Integer>> getData() throws SQLException, ClassNotFoundException;

}
