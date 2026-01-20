package io.vekzz_dev.task_tracker;

import io.vekzz_dev.task_tracker.cli.CommandRouter;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.repositories.Repository;
import io.vekzz_dev.task_tracker.repositories.TaskRepository;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.storage.FileManager;
import io.vekzz_dev.task_tracker.utils.JacksonConfig;

public class Main {

    static void main(String[] args) {
        try {
            FileManager fileManager = new FileManager();
            Repository<TaskStore> repository = new TaskRepository(JacksonConfig.createMapper(), fileManager);

            TaskService service = new TaskService(repository);

            CommandRouter router = new CommandRouter(args, service);
            router.process();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}