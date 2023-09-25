package dev.tinokoci.multiorm.database;

public interface DatabaseService {

    <T> boolean save(T entity);
}
