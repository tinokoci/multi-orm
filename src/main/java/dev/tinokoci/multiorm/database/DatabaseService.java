package dev.tinokoci.multiorm.database;

public interface DatabaseService {

    /***
     * @return If the connection was established successfully
     */
    DatabaseService connect(UriBuilder uri);

    /***
     * @return The saved entity or null if save failed
     */
    <T> T save(T entity);
}
