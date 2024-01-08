package lk.ijse.D.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.ijse.D.bo.custom.DashboardBO;
import lk.ijse.D.dao.custom.ItemDAO;
import lk.ijse.D.dao.custom.impl.ItemDAOImpl;
import lk.ijse.D.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBOImpl implements DashboardBO {

    ItemDAO itemDAO = new ItemDAOImpl();
    @Override
    public ObservableList<XYChart.Series<String, Integer>> getData() throws SQLException, ClassNotFoundException {
        ArrayList<Item> data = itemDAO.getData();
        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (Item item : data){
            String itemName = item.getName();
            int itemQty = item.getQty();
           series.getData().add(new XYChart.Data<>(itemName, itemQty));
        }

        datalist.add(series);
        return datalist;

//        while(resultSet.next()){
//            String itemName = resultSet.getString("item_name");
//            int itemQty = resultSet.getInt("item_qty");
//            series.getData().add(new XYChart.Data<>(itemName, itemQty));
//        }
//
//        datalist.add(series);
//        return datalist;

    }
}
