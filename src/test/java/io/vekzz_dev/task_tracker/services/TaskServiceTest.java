package io.vekzz_dev.task_tracker.services;

import io.vekzz_dev.task_tracker.models.Status;
import io.vekzz_dev.task_tracker.models.Task;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.repositories.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private Repository<TaskStore> repository;

    @Mock
    private TaskStore taskStore;

    private TaskService service;

    private Task task1;
    private Task task2;
    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task1 = new Task(1, "Task 1", Status.TODO, LocalDateTime.now(), LocalDateTime.now());
        task2 = new Task(2, "Task 2", Status.IN_PROGRESS, LocalDateTime.now(), LocalDateTime.now());
        tasks = new ArrayList<>(List.of(task1, task2));

        when(repository.getAll()).thenReturn(taskStore);
        when(taskStore.getTasks()).thenReturn(tasks);
        when(taskStore.generateNextId()).thenReturn(3);

        service = new TaskService(repository);
    }

    @Test
    void testListReturnsAllTasksWhenStatusIsAll() {
        List<Task> result = service.list("all");

        assertThat(result).hasSize(2).contains(task1, task2);
    }

    @Test
    void testListReturnsFilteredTasksByStatus() {
        List<Task> result = service.list("todo");

        assertThat(result).hasSize(1).contains(task1);
    }

    @Test
    void testListThrowsIllegalArgumentExceptionForInvalidStatus() {
        assertThatThrownBy(() -> service.list("invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAddCreatesAndAddsTaskReturnsId() {
        int id = service.add("New Task");

        assertThat(id).isEqualTo(3);
        assertThat(tasks).hasSize(3);
        Task added = tasks.get(2);
        assertThat(added.getDescription()).isEqualTo("New Task");
        assertThat(added.getStatus()).isEqualTo(Status.TODO);
        verify(repository).save(taskStore);
    }

    @Test
    void testUpdateChangesDescriptionAndUpdatedAt() {
        service.update(1, "Updated Task 1");

        assertThat(task1.getDescription()).isEqualTo("Updated Task 1");
        assertThat(task1.getUpdatedAt()).isAfter(task1.getCreatedAt());
        verify(repository).save(taskStore);
    }

    @Test
    void testUpdateThrowsNoSuchElementExceptionForInvalidId() {
        assertThatThrownBy(() -> service.update(99, "Update"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Task not found with id: 99");
    }

    @Test
    void testDeleteRemovesTask() {
        service.delete(1);

        assertThat(tasks).hasSize(1).doesNotContain(task1);
        verify(repository).save(taskStore);
    }

    @Test
    void testDeleteThrowsNoSuchElementExceptionForInvalidId() {
        assertThatThrownBy(() -> service.delete(99))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Task not found with id: 99");
    }

    @Test
    void testMarkChangesStatusAndUpdatedAt() {
        service.mark(2, "done");

        assertThat(task2.getStatus()).isEqualTo(Status.DONE);
        assertThat(task2.getUpdatedAt()).isAfter(task2.getCreatedAt());
        verify(repository).save(taskStore);
    }

    @Test
    void testMarkThrowsNoSuchElementExceptionForInvalidId() {
        assertThatThrownBy(() -> service.mark(99, "done"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Task not found with id: 99");
    }

    @Test
    void testMarkThrowsIllegalArgumentExceptionForInvalidStatus() {
        assertThatThrownBy(() -> service.mark(1, "invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}