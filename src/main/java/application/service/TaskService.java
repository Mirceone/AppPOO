package application.service;

import application.session.SessionManager;
import application.dao.TaskDao;
import application.entity.Task;
import application.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final TaskDao taskDao;
    User currentUser = SessionManager.getInstance().getCurrentUser();

    public TaskService(EntityManagerFactory factory) {
        taskDao = new TaskDao(factory);
    }

    public void addTask(Task newTask) {
        taskDao.create(newTask);
    }

    public void updateTask(Task updatedTask) {
        taskDao.update(updatedTask);
    }

    public void deleteTask(int taskId) throws InstantiationException, IllegalAccessException {
        Task task = taskDao.find(taskId);
        if (task != null) {
            taskDao.remove(Task.class.newInstance(), taskId);
        }
    }

    public List<Task> getTasksForCurrentUser() {
        if (currentUser != null) {
            return taskDao.getAllTasksByUser(currentUser.getId());
        }
        return new ArrayList<>();
    }
}
