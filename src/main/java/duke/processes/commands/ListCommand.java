package duke.processes.commands;

import duke.Duke;
import duke.processes.utility.Interface;
import duke.processes.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    public static ArrayList<Task> sortedList = new ArrayList<>();
    protected String listType;

    public ListCommand(String[] command) {

        if (command.length == 1) {
            this.listType = "list";
        } else if (command[1].equalsIgnoreCase("event")) {
            this.listType = "event";
        } else if (command[1].equalsIgnoreCase("deadline")) {
            this.listType = "deadline";
        } else if (command[1].equalsIgnoreCase("todo")) {
            this.listType = "todo";
        } else {
            this.listType = "others";
        }
    }

    public CommandResult execute() {
        sortedList.clear();
        switch (listType) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            Interface.printList(Duke.taskList);
            break;
        case "event":
        case "deadline":
        case "todo":
            getListTypeTasks(listType);
            bubbleSortTask();
            System.out.println(listType + " list! Total number of " + listType + "s = "
                    + sortedList.size());
            Interface.printList(sortedList);
            break;
        case "others":
            return new CommandResult("please specify type for list [list, list deadline, list event,list todo ]");
        }
        return new CommandResult("--------END OF LIST-----------");
    }

    private void getListTypeTasks(String type) {
        for (Task task : Duke.taskList) {
            if (task.getTaskType().equalsIgnoreCase(type)) {
                sortedList.add(task);
            }
        }
    }

    private void bubbleSortTask() {
        for (int j = 0; j < sortedList.size() - 1; j++) {
            for (int i = 0; i < sortedList.size() - j - 1; i++) {
                if (sortedList.get(i + 1).getDateValue().isBefore(sortedList.get(i).getDateValue())) {
                    swap(i);
                }
            }
        }
    }

    private void swap(int i) {
        Task t;
        t = sortedList.get(i);
        sortedList.set(i, sortedList.get(i + 1));
        sortedList.set(i + 1, t);
    }
}
