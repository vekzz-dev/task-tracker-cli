package io.vekzz_dev.task_tracker.repositories;

public interface Repository<T> {

    T getAll();

    void save(T object);
}