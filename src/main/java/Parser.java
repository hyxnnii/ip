public class Parser {
    private Kira kira;
    public Parser(Kira kira) {
        this.kira = kira;
    }


    public void parse(String userInput) throws EmptyException, InvalidTaskException, UnreadableException {

        List list = this.kira.getList();

        if (userInput.equalsIgnoreCase("list")) {
            System.out.println(list.displayList());
            return;
        }

        String[] strings = userInput.split("\\s+", 2);
        String firstWord = strings[0];


        switch (firstWord) {
            case "mark" -> {
                if (strings.length < 2) {
                    throw new EmptyException("mark");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                Task task = list.getTask(index);
                task.markAsDone();
                System.out.println(task.markedNotification());
            }
            case "unmark" -> {
                if (strings.length < 2) {
                    throw new EmptyException("unmark");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                Task task = list.getTask(index);
                task.markAsUndone();
            }
            case "todo" -> {
                if (strings.length < 2) {
                    throw new EmptyException("todo");
                }
                String restOfWords = strings[1];
                Task task = new ToDo(restOfWords);
                list.addTaskToList(task);
                System.out.println(list.addedNotification(task));
            }
            case "deadline" -> {
                if (strings.length < 2) {
                    throw new EmptyException("deadline");
                }
                String restOfWords = strings[1];
                String deadline = restOfWords.split(" /by ")[1];
                String input = restOfWords.split(" /by ")[0];
                Task task = new Deadline(input, deadline);
                list.addTaskToList(task);
                System.out.println(list.addedNotification(task));
            }
            case "event" -> {
                if (strings.length < 2) {
                    throw new EmptyException("event");
                }
                String restOfWords = strings[1];
                String input = restOfWords.split(" /from ")[0];
                String period = restOfWords.split(" /from ")[1];
                String start = period.split(" /to ")[0];
                String end = period.split(" /to ")[1];
                Task task = new Event(input, start, end);
                list.addTaskToList(task);
                System.out.println(list.addedNotification(task));
            }
            case "delete" -> {
                if (strings.length < 2) {
                    throw new EmptyException("delete");
                }
                String restOfWords = strings[1];
                int index = Integer.parseInt(restOfWords) - 1;
                list.deleteTask(index);
            }
            default -> {
                throw new UnreadableException();
            }
        }
    }
}
