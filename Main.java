
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TaskFlow | Your Personal Planner");
            frame.setSize(1050, 700);
            frame.setMinimumSize(new Dimension(850, 600));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            Color background = new Color(246, 243, 252);
            Color purple = new Color(111, 90, 168);
            Color darkPurple = new Color(75, 59, 120);
            Color lightPurple = new Color(232, 225, 247);
            Color white = Color.WHITE;
            Color textColor = new Color(48, 43, 62);
            Color muted = new Color(125, 119, 140);
            Color red = new Color(190, 70, 85);

            Font normalFont = new Font("Arial", Font.PLAIN, 14);
            Font boldFont = new Font("Arial", Font.BOLD, 14);

            ArrayList<Task> tasks = TaskStorage.loadTasks();
            String[] currentFilter = {"All"};

            // Sidebar
            JPanel sidebar = new JPanel();
            sidebar.setPreferredSize(new Dimension(210, 700));
            sidebar.setBackground(darkPurple);
            sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
            sidebar.setBorder(
                    BorderFactory.createEmptyBorder(30, 18, 20, 18));

            JLabel logo = new JLabel("TaskFlow");
            logo.setFont(new Font("Arial", Font.BOLD, 29));
            logo.setForeground(white);

            JLabel tagline = new JLabel("YOUR DAILY PLANNER");
            tagline.setFont(new Font("Arial", Font.PLAIN, 10));
            tagline.setForeground(new Color(210, 201, 232));

            JLabel menuTitle = new JLabel("  WORKSPACE");
            menuTitle.setFont(new Font("Arial", Font.BOLD, 11));
            menuTitle.setForeground(new Color(205, 194, 230));
            menuTitle.setBorder(
                    BorderFactory.createEmptyBorder(40, 0, 15, 0));

            JLabel allTasks = new JLabel("   All Tasks");
            JLabel pendingTasks = new JLabel("   Pending Tasks");
            JLabel completedTasks = new JLabel("   Completed Tasks");

            JLabel[] menuItems = {
                allTasks, pendingTasks, completedTasks
            };

            for (JLabel item : menuItems) {
                item.setFont(normalFont);
                item.setForeground(white);
                item.setCursor(new Cursor(Cursor.HAND_CURSOR));
                item.setBorder(
                        BorderFactory.createEmptyBorder(13, 5, 13, 5));
                item.setAlignmentX(Component.LEFT_ALIGNMENT);
                item.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, 45));
            }

            JLabel tip = new JLabel(
                    "<html><div style='width:145px'>"
                    + "Small steps every day lead to big results."
                    + "</div></html>");
            tip.setFont(new Font("Arial", Font.ITALIC, 12));
            tip.setForeground(new Color(220, 212, 237));

            sidebar.add(logo);
            sidebar.add(Box.createVerticalStrut(5));
            sidebar.add(tagline);
            sidebar.add(menuTitle);
            sidebar.add(allTasks);
            sidebar.add(pendingTasks);
            sidebar.add(completedTasks);
            sidebar.add(Box.createVerticalGlue());
            sidebar.add(tip);

            // Main content
            JPanel content = new JPanel(new BorderLayout(0, 25));
            content.setBackground(background);
            content.setBorder(
                    BorderFactory.createEmptyBorder(30, 30, 25, 30));

            JPanel header = new JPanel();
            header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
            header.setBackground(background);

            JLabel heading = new JLabel("My Dashboard");
            heading.setFont(new Font("Arial", Font.BOLD, 30));
            heading.setForeground(textColor);

            JLabel subtitle = new JLabel(
                    "Plan your day, one task at a time.");
            subtitle.setFont(normalFont);
            subtitle.setForeground(muted);

            header.add(heading);
            header.add(Box.createVerticalStrut(7));
            header.add(subtitle);

            // Task input
            JPanel inputCard = new JPanel();
            inputCard.setLayout(new BoxLayout(inputCard, BoxLayout.Y_AXIS));
            inputCard.setBackground(white);
            inputCard.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(lightPurple, 1, true),
                    BorderFactory.createEmptyBorder(16, 18, 16, 18)));

            JLabel addHeading = new JLabel("Create a new task");
            addHeading.setFont(boldFont);
            addHeading.setForeground(textColor);

            JTextField taskInput = new JTextField();
            taskInput.setFont(normalFont);
            taskInput.setForeground(textColor);
            taskInput.setBackground(new Color(250, 249, 253));
            taskInput.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(lightPurple, 1, true),
                    BorderFactory.createEmptyBorder(10, 12, 10, 12)));
            taskInput.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 43));

            String[] priorities = {"High", "Medium", "Low"};
            JComboBox<String> priorityBox = new JComboBox<>(priorities);

            String[] categories = {
                "College", "Personal", "Projects", "Other"
            };
            JComboBox<String> categoryBox = new JComboBox<>(categories);

            priorityBox.setFont(normalFont);
            categoryBox.setFont(normalFont);

            JButton addButton = new JButton("+ Add Task");
            addButton.setFont(boldFont);
            addButton.setBackground(purple);
            addButton.setForeground(white);
            addButton.setFocusPainted(false);
            addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addButton.setBorder(
                    BorderFactory.createEmptyBorder(10, 18, 10, 18));

            JButton sortButton = new JButton("Sort by Priority");
            sortButton.setFont(normalFont);
            sortButton.setBackground(lightPurple);
            sortButton.setForeground(darkPurple);
            sortButton.setFocusPainted(false);
            sortButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sortButton.setBorder(
                    BorderFactory.createEmptyBorder(10, 14, 10, 14));

            JPanel optionsRow = new JPanel(
                    new FlowLayout(FlowLayout.LEFT, 8, 0));
            optionsRow.setBackground(white);
            optionsRow.add(new JLabel("Priority:"));
            optionsRow.add(priorityBox);
            optionsRow.add(new JLabel("Category:"));
            optionsRow.add(categoryBox);
            optionsRow.add(addButton);

            JPanel sortRow = new JPanel(
                    new FlowLayout(FlowLayout.RIGHT, 0, 0));
            sortRow.setBackground(white);
            sortRow.add(sortButton);

            JPanel optionsAndSort = new JPanel(new BorderLayout());
            optionsAndSort.setBackground(white);
            optionsAndSort.add(optionsRow, BorderLayout.CENTER);
            optionsAndSort.add(sortRow, BorderLayout.EAST);

            inputCard.add(addHeading);
            inputCard.add(Box.createVerticalStrut(12));
            inputCard.add(taskInput);
            inputCard.add(Box.createVerticalStrut(12));
            inputCard.add(optionsAndSort);

            // Dashboard summary cards
            JLabel totalNumber = new JLabel("0");
            JLabel pendingNumber = new JLabel("0");
            JLabel doneNumber = new JLabel("0");

            JPanel summaryCards = new JPanel(
                    new GridLayout(1, 3, 12, 0));
            summaryCards.setBackground(background);

            JPanel totalCard = createSummaryCard(
                    "TOTAL TASKS", totalNumber,
                    new Color(111, 90, 168), white, lightPurple);

            JPanel pendingCard = createSummaryCard(
                    "PENDING", pendingNumber,
                    new Color(190, 130, 40), white, lightPurple);

            JPanel doneCard = createSummaryCard(
                    "COMPLETED", doneNumber,
                    new Color(65, 145, 100), white, lightPurple);

            summaryCards.add(totalCard);
            summaryCards.add(pendingCard);
            summaryCards.add(doneCard);
  
            // Task statistics
            JLabel countLabel = new JLabel("All Tasks (0)");
            countLabel.setFont(new Font("Arial", Font.BOLD, 19));
            countLabel.setForeground(textColor);

            JLabel completedLabel = new JLabel("0 of 0 completed");
            completedLabel.setFont(normalFont);
            completedLabel.setForeground(muted);

            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setString("0% completed");
            progressBar.setForeground(purple);
            progressBar.setBackground(lightPurple);
            progressBar.setBorderPainted(false);
            progressBar.setPreferredSize(new Dimension(200, 16));
            progressBar.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 16));

            JPanel statsCard = new JPanel();
            statsCard.setLayout(new BoxLayout(statsCard, BoxLayout.Y_AXIS));
            statsCard.setBackground(white);
            statsCard.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(lightPurple, 1, true),
                    BorderFactory.createEmptyBorder(15, 18, 15, 18)));

            JPanel statsTop = new JPanel(new BorderLayout());
            statsTop.setBackground(white);
            statsTop.add(countLabel, BorderLayout.WEST);
            statsTop.add(completedLabel, BorderLayout.EAST);

            statsCard.add(statsTop);
            statsCard.add(Box.createVerticalStrut(12));
            statsCard.add(progressBar);

            // Task list
            JPanel taskList = new JPanel();
            taskList.setLayout(new BoxLayout(taskList, BoxLayout.Y_AXIS));
            taskList.setBackground(background);
            taskList.setBorder(
                    BorderFactory.createEmptyBorder(3, 2, 10, 2));

            JScrollPane scrollPane = new JScrollPane(taskList);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.getViewport().setBackground(background);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            JPanel listSection = new JPanel(new BorderLayout(0, 12));
            listSection.setBackground(background);
            listSection.add(statsCard, BorderLayout.NORTH);
            listSection.add(scrollPane, BorderLayout.CENTER);

             
            JPanel center = new JPanel(new BorderLayout(0, 18));
            center.setBackground(background);

            JPanel topSection = new JPanel(
              new BorderLayout(0, 15));
            topSection.setBackground(background);

            topSection.add(inputCard, BorderLayout.NORTH);
            topSection.add(summaryCards, BorderLayout.SOUTH);

            center.add(topSection, BorderLayout.NORTH);
            center.add(listSection, BorderLayout.CENTER);

            content.add(header, BorderLayout.NORTH);
            content.add(center, BorderLayout.CENTER);

            frame.add(sidebar, BorderLayout.WEST);
            frame.add(content, BorderLayout.CENTER);

            // Update count and progress
            Runnable updateCount = () -> {
                int done = 0;

                for (Task task : tasks) {
                    if (task.isCompleted()) {
                        done++;
                    }
                }

                int visibleCount = currentFilter[0].equals("Pending")
                        ? tasks.size() - done
                        : currentFilter[0].equals("Completed")
                        ? done : tasks.size();

                countLabel.setText(
                        currentFilter[0].equals("All")
                        ? "All Tasks (" + visibleCount + ")"
                        : currentFilter[0].equals("Pending")
                        ? "Pending Tasks (" + visibleCount + ")"
                        : "Completed Tasks (" + visibleCount + ")");

                completedLabel.setText(
                        done + " of " + tasks.size() + " completed");
                        totalNumber.setText(String.valueOf(tasks.size()));
                        pendingNumber.setText(String.valueOf(tasks.size() - done));
                        doneNumber.setText(String.valueOf(done));

                int percentage = tasks.isEmpty()
                        ? 0 : (done * 100) / tasks.size();

                progressBar.setValue(percentage);
                progressBar.setString(percentage + "% completed");
            };

            Runnable[] renderTasks = new Runnable[1];

            // Display tasks
            renderTasks[0] = () -> {
                taskList.removeAll();
                boolean foundTask = false;

                for (Task task : tasks) {
                    if (currentFilter[0].equals("Pending")
                            && task.isCompleted()) {
                        continue;
                    }

                    if (currentFilter[0].equals("Completed")
                            && !task.isCompleted()) {
                        continue;
                    }

                    foundTask = true;

                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected(task.isCompleted());
                    checkBox.setBackground(white);
                    checkBox.setFocusPainted(false);
                    checkBox.setFont(
                            new Font("Arial", Font.PLAIN, 15));
                    checkBox.setForeground(textColor);

                    String taskName = escapeHtml(task.getName());

                    if (task.isCompleted()) {
                        checkBox.setText("<html><strike>"
                                + taskName + "</strike></html>");
                        checkBox.setForeground(muted);
                    } else {
                        checkBox.setText(taskName);
                    }

                    JLabel categoryLabel =
                            new JLabel(task.getCategory());
                    categoryLabel.setFont(
                            new Font("Arial", Font.PLAIN, 12));
                    categoryLabel.setForeground(muted);

                    JLabel priorityLabel =
                            new JLabel(task.getPriority());
                    priorityLabel.setFont(
                            new Font("Arial", Font.BOLD, 12));

                    if (task.getPriority().equals("High")) {
                        priorityLabel.setForeground(
                                new Color(190, 65, 85));
                    } else if (task.getPriority().equals("Medium")) {
                        priorityLabel.setForeground(
                                new Color(190, 130, 40));
                    } else {
                        priorityLabel.setForeground(
                                new Color(65, 145, 100));
                    }

                    JPanel details = new JPanel(
                            new FlowLayout(FlowLayout.RIGHT, 12, 0));
                    details.setBackground(white);
                    details.add(categoryLabel);
                    details.add(priorityLabel);

                    // Edit button
                    JButton editButton = new JButton("Edit");
                    editButton.setFont(
                            new Font("Arial", Font.BOLD, 12));
                    editButton.setForeground(darkPurple);
                    editButton.setBackground(lightPurple);
                    editButton.setFocusPainted(false);
                    editButton.setCursor(
                            new Cursor(Cursor.HAND_CURSOR));
                    editButton.setBorder(
                            BorderFactory.createEmptyBorder(8, 12, 8, 12));

                    // Delete button
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.setFont(
                            new Font("Arial", Font.BOLD, 12));
                    deleteButton.setForeground(red);
                    deleteButton.setBackground(
                            new Color(255, 241, 243));
                    deleteButton.setFocusPainted(false);
                    deleteButton.setCursor(
                            new Cursor(Cursor.HAND_CURSOR));
                    deleteButton.setBorder(
                            BorderFactory.createEmptyBorder(8, 12, 8, 12));

                    JPanel actionButtons = new JPanel(
                            new FlowLayout(FlowLayout.RIGHT, 5, 0));
                    actionButtons.setBackground(white);
                    actionButtons.add(editButton);
                    actionButtons.add(deleteButton);

                    JPanel rightSide = new JPanel(
                            new FlowLayout(FlowLayout.RIGHT, 8, 0));
                    rightSide.setBackground(white);
                    rightSide.add(details);
                    rightSide.add(actionButtons);

                    JPanel row = new JPanel(new BorderLayout(10, 0));
                    row.setBackground(white);
                    row.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(lightPurple, 1, true),
                            BorderFactory.createEmptyBorder(12, 12, 12, 12)));
                    row.setMaximumSize(
                            new Dimension(Integer.MAX_VALUE, 75));
                    row.setPreferredSize(new Dimension(700, 75));
                    row.add(checkBox, BorderLayout.CENTER);
                    row.add(rightSide, BorderLayout.EAST);

                    checkBox.addActionListener(e -> {
                        task.setCompleted(checkBox.isSelected());
                        TaskStorage.saveTasks(tasks);
                        renderTasks[0].run();
                    });

                    // Open the edit window
                    editButton.addActionListener(e -> {
                        JTextField editName =
                                new JTextField(task.getName());

                        String[] editPriorities = {
                            "High", "Medium", "Low"
                        };
                        JComboBox<String> editPriority =
                                new JComboBox<>(editPriorities);
                        editPriority.setSelectedItem(task.getPriority());

                        String[] editCategories = {
                            "College", "Personal", "Projects", "Other"
                        };
                        JComboBox<String> editCategory =
                                new JComboBox<>(editCategories);
                        editCategory.setSelectedItem(task.getCategory());

                        JPanel editPanel = new JPanel(new GridLayout(0, 1, 5, 5));
                        editPanel.add(new JLabel("Task name:"));
                        editPanel.add(editName);
                        editPanel.add(new JLabel("Priority:"));
                        editPanel.add(editPriority);
                        editPanel.add(new JLabel("Category:"));
                        editPanel.add(editCategory);

                        int answer = JOptionPane.showConfirmDialog(
                                frame,
                                editPanel,
                                "Edit Task",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

                        if (answer == JOptionPane.OK_OPTION) {
                            String newName = editName.getText().trim();

                            if (newName.isEmpty()) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Task name cannot be empty.",
                                        "Invalid Task Name",
                                        JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            task.setName(newName);
                            task.setPriority(
                                    (String) editPriority.getSelectedItem());
                            task.setCategory(
                                    (String) editCategory.getSelectedItem());

                            TaskStorage.saveTasks(tasks);
                            renderTasks[0].run();
                        }
                    });

                    // Delete a task
                    deleteButton.addActionListener(e -> {
                        int answer = JOptionPane.showConfirmDialog(
                                frame,
                                "Are you sure you want to delete this task?\n\n"
                                + task.getName(),
                                "Delete Task",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);

                        if (answer == JOptionPane.YES_OPTION) {
                            tasks.remove(task);
                            TaskStorage.saveTasks(tasks);
                            renderTasks[0].run();
                        }
                    });

                    taskList.add(row);
                    taskList.add(Box.createVerticalStrut(10));
                }

                if (!foundTask) {
                    JLabel emptyLabel = new JLabel(
                            "<html><div style='text-align:center;'>"
                            + "<b>No tasks here yet!</b><br>"
                            + "Add a task or choose another filter."
                            + "</div></html>");
                    emptyLabel.setFont(normalFont);
                    emptyLabel.setForeground(muted);
                    emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    taskList.add(Box.createVerticalStrut(35));
                    taskList.add(emptyLabel);
                }

                taskList.revalidate();
                taskList.repaint();
                updateCount.run();
            };

            // Add a new task
            Runnable addTask = () -> {
                String taskName = taskInput.getText().trim();

                if (taskName.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Please enter a task name.",
                            "Missing Task",
                            JOptionPane.WARNING_MESSAGE);
                    taskInput.requestFocus();
                    return;
                }

                String priority =
                        (String) priorityBox.getSelectedItem();
                String category =
                        (String) categoryBox.getSelectedItem();

                Task task = new Task(taskName, priority, category);
                tasks.add(task);

                TaskStorage.saveTasks(tasks);
                taskInput.setText("");

                renderTasks[0].run();
            };

            // Sidebar filters
            allTasks.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentFilter[0] = "All";
                    renderTasks[0].run();
                }
            });

            pendingTasks.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentFilter[0] = "Pending";
                    renderTasks[0].run();
                }
            });

            completedTasks.addMouseListener(
                    new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    currentFilter[0] = "Completed";
                    renderTasks[0].run();
                }
            });

            // Sort tasks by priority
            sortButton.addActionListener(e -> {
                tasks.sort((a, b) -> {
                    int priorityA = a.getPriority().equals("High") ? 1
                            : a.getPriority().equals("Medium") ? 2 : 3;

                    int priorityB = b.getPriority().equals("High") ? 1
                            : b.getPriority().equals("Medium") ? 2 : 3;

                    return Integer.compare(priorityA, priorityB);
                });

                TaskStorage.saveTasks(tasks);
                renderTasks[0].run();
            });

            addButton.addActionListener(e -> addTask.run());
            taskInput.addActionListener(e -> addTask.run());

            renderTasks[0].run();
            frame.setVisible(true);
        });
    }

    private static JPanel createSummaryCard(
            String title,
            JLabel number,
            Color accent,
            Color white,
            Color lightPurple) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(white);

        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(lightPurple, 1, true),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 11));
        titleLabel.setForeground(new Color(125, 119, 140));

        number.setFont(new Font("Arial", Font.BOLD, 28));
        number.setForeground(accent);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(number);

        return card;
    }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
