# Task Tracker CLI

**Task Tracker CLI** is a lightweight and user-friendly command-line tool that helps you manage your daily tasks
efficiently.

This application was built as a solution for the **Task Tracker** challenge from [roadmap.sh](https://roadmap.sh/projects/task-tracker), with
an emphasis on clarity, functionality, and persistence of your to-dos.


## Features

* Add new tasks quickly and easily.
* Update the description of any task.
* Delete tasks by their ID.
* Mark tasks as `todo`, `in-progress`, or `done`.
* List tasks filtered by status or all at once.
* View a help menu with available commands.
* Data is stored persistently to ensure your tasks are saved across sessions.


## How to Run It

Clone the repository:

```bash
git clone https://github.com/vekzz-dev/task-tracker-cli.git
cd task-tracker-cli
```

Build the application:

```bash
mvn clean package
```

Run the application:

```bash
java -jar target/task-tracker-cli-1.0.jar
```


## How to Use It

### Add a new task

```bash
add "Buy groceries"
```

**Output:**

```
Task successfully added (ID: 1)
```

### Update a task

```bash
update 1 "Buy groceries and cook dinner"
```

**Output:**

```
Task successfully updated (ID: 1)
```

### Delete a task

```bash
delete 1
```

**Output:**

```
Task successfully removed (ID: 1)
```

### Mark the status of a task

```bash
mark 1 done
```

Valid statuses: `todo`, `in-progress`, `done`

**Output:**

```
Task marked correctly (ID: 1)
```

### List tasks

* List all tasks:

  ```bash
  list
  ```
* List tasks by status:

  ```bash
  list todo
  list in-progress
  list done
  ```

### View command information

```bash
help
```

**Output:**

```
Available commands:
    add <description>               -> Add new task.
    update <id> <new description>   -> Update existing task.
    delete <id>                     -> Delete task.
    list <status>                   -> List all tasks or by status (done, todo, in-progress).
    mark <id> <status>              -> Mark task (done, todo, in-progress).
    help                            -> View command information.
    exit                            -> Exit.
```

### Exit the application

```bash
exit
```


## License

This project is licensed under the [MIT License](LICENSE).
