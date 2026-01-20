package io.vekzz_dev.task_tracker.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.storage.FileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class TaskRepositoryTest {

    @Mock
    private ObjectMapper mapper;

    @Mock
    private FileManager fileManager;

    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new TaskRepository(mapper, fileManager);
    }

    @Test
    void testGetAllReturnsEmptyTaskStoreWhenJsonIsBlank() {
        when(fileManager.read()).thenReturn("");

        TaskStore result = repository.getAll();

        assertThat(result.getTasks()).isEmpty();
        verify(fileManager).read();
        verifyNoInteractions(mapper);
    }

    @Test
    void testGetAllReturnsEmptyTaskStoreWhenJsonIsNull() {
        when(fileManager.read()).thenReturn(null);

        TaskStore result = repository.getAll();

        assertThat(result.getTasks()).isEmpty();
        verify(fileManager).read();
        verifyNoInteractions(mapper);
    }

    @Test
    void testGetAllParsesValidJsonToTaskStore() throws JsonProcessingException {
        String json = "{\"tasks\":[]}";
        TaskStore expected = new TaskStore();

        when(fileManager.read()).thenReturn(json);
        when(mapper.readValue(json, TaskStore.class)).thenReturn(expected);

        TaskStore result = repository.getAll();

        assertThat(result).isEqualTo(expected);
        verify(fileManager).read();
        verify(mapper).readValue(json, TaskStore.class);
    }

    @Test
    void testGetAllThrowsIllegalStateExceptionOnJsonProcessingException() throws JsonProcessingException {
        String json = "invalid json";

        when(fileManager.read()).thenReturn(json);
        when(mapper.readValue(json, TaskStore.class)).thenThrow(new JsonProcessingException("error") {});

        assertThatThrownBy(() -> repository.getAll())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Failed to parse json");

        verify(fileManager).read();
        verify(mapper).readValue(json, TaskStore.class);
    }

    @Test
    void testSaveSerializesAndWritesTaskStore() throws JsonProcessingException {
        TaskStore taskStore = new TaskStore();
        String json = "{\"tasks\":[]}";

        when(mapper.writeValueAsString(taskStore)).thenReturn(json);

        repository.save(taskStore);

        verify(mapper).writeValueAsString(taskStore);
        verify(fileManager).write(json);
    }

    @Test
    void testSaveThrowsIllegalStateExceptionOnJsonProcessingException() throws JsonProcessingException {
        TaskStore taskStore = new TaskStore();

        when(mapper.writeValueAsString(taskStore)).thenThrow(new JsonProcessingException("error") {});

        assertThatThrownBy(() -> repository.save(taskStore))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Failed to write json");

        verify(mapper).writeValueAsString(taskStore);
        verifyNoInteractions(fileManager);
    }
}