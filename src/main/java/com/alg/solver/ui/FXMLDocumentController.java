package com.alg.solver.ui;

import com.alg.solver.model.Item;
import com.alg.solver.model.KnapsackData;
import com.alg.solver.model.Solution;
import com.alg.solver.model.Util;
import com.alg.solver.reader.FileReader;
import com.alg.solver.solver.KnapsackSolver;
import com.alg.solver.strategy.HillClimbingStrategy;
import com.alg.solver.strategy.KnapsackStrategy;
import com.alg.solver.strategy.RandomRestartStrategy;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    public Button readProfitFromFileButton;
    public Button readWeightFromFileButton;
    private int counter = 0;
    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, Item> itemProfitColumn;

    @FXML
    private TableColumn<Item, Item> itemWeightColumn;

    @FXML
    private TextField itemProfitTextField;

    @FXML
    private TextField itemWTextField;

    @FXML
    private TextField itemSizeField;

    @FXML
    private TextField knapsackWeightField;

    @FXML
    private TableView<Item> resultItemTable;

    @FXML
    private TableColumn<Item, Integer> resultItemProfitColumn;

    @FXML
    private TableColumn<Item, Integer> resultItemWeightColumn;

    @FXML
    private Label totalTimeLabel;

    @FXML
    private Label totalProfitLabel;

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
            if (itemProfitTextField.getText() != null && !itemProfitTextField.getText().isEmpty()) {
                int itemWeight = Integer.valueOf(itemWTextField.getText());
                int itemValue = Integer.valueOf(itemProfitTextField.getText());
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
        totalProfitLabel.setText("Total Value: " + solution.getGainedValue());
        totalWeightLabel.setText("Total Weight: " + solution.getGainedWeight());
        totalTimeLabel.setText("Total Time: " + solution.getTotalTime() + " ms");
        resultItemTable.getItems().clear();
        resultItemTable.getItems().addAll(solution.getPickedItem());
    }

    private KnapsackData getKnapsackData() throws NumberFormatException {
        int knapsackWeight = Integer.parseInt(knapsackWeightField.getText());
        KnapsackData knapsackData = new KnapsackData(knapsackWeight);
        knapsackData.addItem(new ArrayList<>(itemTable.getItems()));
        return knapsackData;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemProfitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        itemWeightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        resultItemProfitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        resultItemWeightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
    }

    @FXML
    public void readProfitFromFile() {
        List<String> lines = readFromFile(readProfitFromFileButton.getContextMenu());
        List<Item> items = new ArrayList<>(itemTable.getItems());
        clearAllButton();
        if (items.isEmpty()) {
            lines.forEach(this::addProfit);
        } else {
            for (String line : lines) {
                line = line.trim();
                items.get(counter).setProfit(Integer.valueOf(line));
                counter++;
            }
        }
        itemTable.getItems().addAll(items);
        counter = 0;
    }

    @FXML
    public void readWeightFromFile() {
        List<String> lines = readFromFile(readWeightFromFileButton.getContextMenu());
        List<Item> items = new ArrayList<>(itemTable.getItems());
        clearAllButton();
        if (items.isEmpty()) {
            lines.forEach(this::addWeight);
        } else {
            for (String line : lines) {
                line = line.trim();
                items.get(counter).setWeight(Integer.valueOf(line));
                counter++;
            }
        }
        itemTable.getItems().addAll(items);
        counter = 0;
    }

    private List<String> readFromFile(ContextMenu contextMenu) {
        FileChooser fileChooser = createFileChooser();
        File file = fileChooser.showOpenDialog(contextMenu);
        FileReader reader = new FileReader();
        try {
            return reader.read(file);
        } catch (IOException e) {
            System.out.println("E   .... " + e.getLocalizedMessage());
            System.err.println(e.getLocalizedMessage());
            return new ArrayList<>();
        }
    }

    private void addProfit(String line) throws NumberFormatException {
        line = line.trim();
        ObservableList<Item> items = itemTable.getItems();
        items.add(new Item(-1, Integer.valueOf(line)));
    }

    private void addWeight(String line) {
        line = line.trim();
        ObservableList<Item> items = itemTable.getItems();
        items.add(new Item(Integer.valueOf(line), -1));
    }


    private FileChooser createFileChooser() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extension);
        return chooser;
    }
}
