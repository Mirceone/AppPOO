package application.controller;

import application.service.TaskService;
import application.session.SessionManager;
import application.entity.Task;
import application.entity.User;

import javax.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddTaskWindowController {

    @FXML
    private ComboBox<String> listComboBox;
    @FXML
    private TextField newListNameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private ComboBox<String> importanceComboBox;
    @FXML
    private Label addTask;

    private TaskService taskService;
    private final SessionManager sessionManager;

    public AddTaskWindowController() {
        this.sessionManager = SessionManager.getInstance();
    }

    @FXML
    private void initialize() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaFxTest");
        taskService = new TaskService(emf);

        // Initialize comboboxes and fields
        initializeComboBox();
        importanceComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
    }

    private void initializeComboBox() {
        if (sessionManager != null && sessionManager.getCurrentUser() != null) {
            List<Task> tasks = taskService.getTasksForCurrentUser();

            // Clear existing items
            listComboBox.getItems().clear();

            // Use a Set to track unique titles
            Set<String> uniqueTitles = new HashSet<>();

            // Populate ComboBox with unique task titles
            for (Task task : tasks) {
                String title = task.getTitle();
                if (!uniqueTitles.contains(title)) {
                    listComboBox.getItems().add(title);
                    uniqueTitles.add(title);
                }
            }
        } else {
            // Handle the case where sessionManager or getCurrentUser() is null
            System.err.println("Session manager or current user is null.");
        }
    }





    @FXML
    private void handleAddTask() {

        // Retrieve input values
        String listName;
        newListNameField.getText();
        String description = descriptionField.getText();
        LocalDate dueDate = dueDatePicker.getValue();
        String importance = importanceComboBox.getValue();

        // verificare daca e lista noua sau existenta
        String selectedTitle = listComboBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedTitle); // Debug print to check selected title
        if (selectedTitle == null || selectedTitle.isEmpty()) {
            listName = newListNameField.getText();
        } else {
            listName = selectedTitle;
        }


        // Validate required fields
        if (description.isEmpty() || dueDate == null || importance == null) {
            // Show error message (this can be a popup or a label in the UI)
            System.out.println("Please fill in all required fields.");
            addTask.setText("All fields must be filled.");
            return;
        }

        // Preluare user curent din session manager
        User currentUser = SessionManager.getInstance().getCurrentUser();

        // Initializare instanta Task pentru un nou task
        Task newTask = new Task();
        newTask.setTitle(listName);
        newTask.setDescription(description);
        newTask.setStatus(importance);
        newTask.setDueDate(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        newTask.setIdUser(currentUser);

        // adaugare task nou in db
        taskService.addTask(newTask);

        addTask.setText("Task added successfully.");

        // Close the window after adding the task
//        Stage stage = (Stage) descriptionField.getScene().getWindow();
//        stage.close();
    }
}
