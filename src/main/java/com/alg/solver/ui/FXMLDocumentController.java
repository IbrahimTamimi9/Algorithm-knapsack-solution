package com.alg.solver.ui;

import com.alg.solver.model.Item;
import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;
import com.alg.solver.model.Util;
import com.alg.solver.solver.KnapsackSolver;
import com.alg.solver.strategy.HillClimbingStrategy;
import com.alg.solver.strategy.KnapsackStrategy;
import com.alg.solver.strategy.RandomRestartStrategy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, Item> itemVColumn;

    @FXML
    private TableColumn<Item, Item> itemWColumn;

    @FXML
    private TextField itemVTextField;

    @FXML
    private TextField itemWTextField;

    @FXML
    private TextField itemSizeField;

    @FXML
    private TextField knapsackWField;

    @FXML
    private TableView<Item> HCItemTable;

    @FXML
    private TableColumn<Item, Integer> itemValueColumn;

    @FXML
    private TableColumn<Item, Integer> itemWeightColumn;

    @FXML
    private Label totalTimeLabel;

    @FXML
    private Label totalValueLabel;

    @FXML
    private Label totalWeightLabel;

    @FXML
    private TextField restartLimitField;

    @FXML
    private void generateItemButton() {
        if (itemSizeField.getText() != null && !itemSizeField.getText().isEmpty()) {
            int itemSize = Integer.valueOf(itemSizeField.getText());
            itemTable.getItems().addAll(Util.generateRandomData(itemSize));
        }
    }

    @FXML
    private void addItemButton() {
        if (itemWTextField.getText() != null && !itemWTextField.getText().isEmpty()) {
            if (itemVTextField.getText() != null && !itemVTextField.getText().isEmpty()) {
                int itemWeight = Integer.valueOf(itemWTextField.getText());
                int itemValue = Integer.valueOf(itemVTextField.getText());
                itemTable.getItems().add(new Item(itemWeight, itemValue));
            }
        }
    }

    @FXML
    void clearAllButton() {
        itemTable.getItems().clear();
    }


    @FXML
    private void solveButton() {
        KnapsackData knapsackData = getKnapsackData();
        KnapsackStrategy strategy = new RandomRestartStrategy(new HillClimbingStrategy(),
                Integer.parseInt(restartLimitField.getText()));
        Solution solution = KnapsackSolver.findSolution(knapsackData, strategy);
        totalValueLabel.setText("Total Value: " + solution.getGainedValue());
        totalWeightLabel.setText("Total Weight: " + solution.getGainedWeight());
        totalTimeLabel.setText("Total Time: " + solution.getTotalTime() + " ms");
        HCItemTable.getItems().clear();
        HCItemTable.getItems().addAll(solution.getPickedItem());
    }

    private KnapsackData getKnapsackData() throws NumberFormatException {
        int knapsackWeight = Integer.parseInt(knapsackWField.getText());
        KnapsackData knapsackData = new KnapsackData(knapsackWeight);
        knapsackData.addItem(new ArrayList<>(itemTable.getItems()));
        return knapsackData;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemVColumn.setCellValueFactory(new PropertyValueFactory("value"));
        itemWColumn.setCellValueFactory(new PropertyValueFactory("weight"));
        itemValueColumn.setCellValueFactory(new PropertyValueFactory("value"));
        itemWeightColumn.setCellValueFactory(new PropertyValueFactory("weight"));
    }

}
