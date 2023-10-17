import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerApp {
    private List<Task> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();
    private JList<String> taskList = new JList<>(taskListModel);
    private JTextArea taskNameField = new JTextArea(10, 20);
    private JTextArea taskDescriptionArea = new JTextArea(10, 20);
    private JButton addButton = new JButton("Add Task");
    private JButton deleteButton = new JButton("Delete Task");
    private JButton updateButton = new JButton("Update Task");
    public TaskManagerApp() {
        JFrame frame = new JFrame("ADV'S Task Manager App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        taskList.setBackground(Color.lightGray);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY); 

        JPanel taskInputPanel = new JPanel();
        taskInputPanel.setLayout(new GridLayout(2, 2));
        taskInputPanel.add(new JLabel("Enter The Task Name Here !!! :"));
        taskNameField.setBackground(Color.lightGray); 
        taskInputPanel.add(new JScrollPane(taskNameField));
        taskInputPanel.add(new JLabel("Enter The Task Description Here !!! :"));
        taskDescriptionArea.setBackground(Color.lightGray); 
        taskInputPanel.add(new JScrollPane(taskDescriptionArea));

        JPanel buttonPanel = new JPanel();
        addButton.setBackground(Color.gray); 
        deleteButton.setBackground(Color.GRAY); 
        updateButton.setBackground(Color.gray); 
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = taskNameField.getText();
                String description = taskDescriptionArea.getText();
                Task task = new Task(name, description);
                tasks.add(task);
                taskListModel.addElement(name);
                clearInputFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex);
                    clearInputFields();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String newName = taskNameField.getText();
                    String newDescription = taskDescriptionArea.getText();
                    Task updatedTask = new Task(newName, newDescription);
                    tasks.set(selectedIndex, updatedTask);
                    taskListModel.set(selectedIndex, newName);
                    clearInputFields();
                }
            }
        });

        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        mainPanel.add(taskInputPanel, BorderLayout.WEST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void clearInputFields() {
        taskNameField.setText("");
        taskDescriptionArea.setText("");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerApp();
            }
        });
    }
}

class Task {
    private String name;
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
