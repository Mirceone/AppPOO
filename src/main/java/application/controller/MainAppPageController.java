package application.controller;

import application.dao.TaskDao;
import application.entity.Task;
import application.service.TaskService;
import application.session.SessionManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainAppPageController {

    private SceneController sceneController = new SceneController();
    private TaskDao taskDao;
    private TaskService taskService;

    @FXML
    private Label welcomeText;

    @FXML
    private VBox listContainer;

    public void initialize() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaFxTest");
        taskDao = new TaskDao(factory);
        taskService = new TaskService(factory);

        refreshTaskList();
    }

    public void setWelcomeMessage(String message) {
        welcomeText.setText(message);
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        // Ask for confirmation before logging out
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Confirm Logout");
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Clear the current user session
            SessionManager.getInstance().clearCurrentUser();
            sceneController.switchToLogin(event);
        }
    }

    public void onSettingsButtonClick(ActionEvent event) {
        sceneController.onSettingsButtonClick(event);
    }

    public void onAddTaskButtonClick(ActionEvent event) throws IOException {
        sceneController.showAddTaskWindow();
        refreshTaskList();
    }

    private void refreshTaskList() {
        listContainer.getChildren().clear();
        int userId = SessionManager.getInstance().getCurrentUser().getId(); // Get the current user ID
        Map<String, List<Task>> groupedTasks = loadTasksFromDatabase(userId).stream()
                .collect(Collectors.groupingBy(Task::getTitle));

        groupedTasks.forEach((title, tasks) -> {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(title);

            ListView<Task> taskListView = new ListView<>();
            taskListView.setItems(FXCollections.observableArrayList(tasks));
            taskListView.setCellFactory(listView -> new TaskCell(taskDao, this));

            titledPane.setContent(taskListView);
            listContainer.getChildren().add(titledPane);
        });
    }

    private List<Task> loadTasksFromDatabase(int userId) {
        return taskDao.getAllTasksByUser(userId);
    }

    static class TaskCell extends ListCell<Task> {
        private final TaskDao taskDao;
        private final MainAppPageController controller;

        public TaskCell(TaskDao taskDao, MainAppPageController controller) {
            this.taskDao = taskDao;
            this.controller = controller;
        }

        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setText(null);
                setGraphic(null);
            } else {
                HBox cellContainer = new HBox(10);
                Label taskDescription = new Label(task.getDescription());

                Circle statusIndicator = new Circle(5);
                switch (task.getStatus()) {
                    case "High":
                        statusIndicator.setFill(Color.RED);
                        break;
                    case "Medium":
                        statusIndicator.setFill(Color.ORANGE);
                        break;
                    case "Low":
                        statusIndicator.setFill(Color.GREEN);
                        break;
                }

                Button removeButton = new Button("Remove");
                removeButton.setOnAction(event -> {
                    taskDao.remove(task, task.getId());
                    controller.refreshTaskList();
                });

                cellContainer.getChildren().addAll(statusIndicator, taskDescription, removeButton);
                setGraphic(cellContainer);
            }
        }
    }
}
