# Task Tracker CLI

A simple command-line tool to manage your personal tasks. Organize todos, track progress, and auto-save everything.

## What You Can Do

- **Create tasks** with descriptions
- **Update** task details
- **Delete** completed tasks
- **Organize** by status: todo, in-progress, done
- **View** tasks in clear tables
- **Auto-save** - never lose progress

## Installation (JBang Recommended)

### Option 1: Install Globally with JBang (Recommended)

Install once and use `tkt` anywhere on your system:

```bash
# Install JBang (one-time)
curl -Ls https://sh.jbang.dev | bash

# Download JAR from releases and install globally
jbang install --name tkt https://github.com/vekzz-dev/task-tracker-cli/releases/download/v1.0/task-tracker-cli-1.0.jar

# Now use it anywhere
tkt
```

*Benefits: Installs globally, works from any directory, no repeated downloads*

### Option 2: Run Directly with JBang

For one-time use without installation:

```bash
jbang https://github.com/vekzz_dev/task-tracker-cli/src/main/java/io/vekzz_dev/task_tracker/Main.java
```

### Option 3: Build from Source

```bash
git clone https://github.com/vekzz-dev/task-tracker-cli.git
cd task-tracker-cli
mvn compile exec:java -Dexec.mainClass="io.vekzz_dev.task_tracker.Main"
```

## Usage

Start the app, then use these commands:

### Add task
```
add "Buy groceries"
```

### List tasks
```
list all        # All tasks
list todo       # Only pending
list done       # Only completed
list in-progress # Only in progress
```

### Update task
```
update 1 "Buy groceries and cleaning supplies"
```

### Change status
```
mark 1 in-progress    # Options: todo, in-progress, done
```

### Delete task
```
delete 1
```

### Help
```
help
```

## Data Storage

Tasks save automatically to `~/.tktdata/tasks.json`

## Features

- **Simple commands** - Learn in 2 minutes
- **Auto-save** - Never lose data
- **Clear tables** - Easy to read
- **Status tracking** - Organize by progress

---

*Run `help` for all commands.*