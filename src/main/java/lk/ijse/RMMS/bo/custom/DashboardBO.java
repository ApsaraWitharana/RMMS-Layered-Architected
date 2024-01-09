package lk.ijse.RMMS.bo.custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface DashboardBO {

    ObservableList<XYChart.Series<String, Integer>> getData() throws SQLException, ClassNotFoundException;

}
