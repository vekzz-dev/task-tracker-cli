package io.vekzz_dev.task_tracker;

import io.vekzz_dev.task_tracker.cli.CommandRouter;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.repositories.Repository;
import io.vekzz_dev.task_tracker.repositories.TaskRepository;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.storage.FileManager;
import io.vekzz_dev.task_tracker.utils.JacksonConfig;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;

public class Main {

    static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            Repository<TaskStore> repository = new TaskRepository(JacksonConfig.createMapper(), fileManager);

            TaskService service = new TaskService(repository);
            TaskServiceHolder.setTaskService(service);

            CommandRouter router = new CommandRouter(args);
            router.process();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}