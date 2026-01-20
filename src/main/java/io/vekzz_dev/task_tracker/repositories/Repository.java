package io.vekzz_dev.task_tracker.repositories;

import java.util.List;

public interface Repository<T> {

    T getAll();

    void save(T object);
}