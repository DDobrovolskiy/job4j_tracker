package ru.job4j.tracker;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;

public class SqlTrackerTest {

    @Test
    public void createItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(ConnectionRollback.create(ConnectionSQL.get()));
        tracker.add(new Item("name"));
        assertThat(tracker.findByName("name").size(), is(1));
    }

    @Test
    public void replaceItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(ConnectionRollback.create(ConnectionSQL.get()));
        Item item = new Item("name");
        tracker.add(item);
        tracker.replace(item.getId(), new Item("gg"));
        assertThat(tracker.findByName("gg").size(), is(1));
    }

    @Test
    public void deleteItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(ConnectionRollback.create(ConnectionSQL.get()));
        Item item = new Item("name");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findByName(item.getName()).size(), is(0));
    }

    @Test
    public void findByIdItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(ConnectionRollback.create(ConnectionSQL.get()));
        Item item = new Item("name");
        tracker.add(item);
        Assert.assertEquals(tracker.findById(item.getId()), item);
    }

    @Test
    public void findAllItem() throws SQLException {
        SqlTracker tracker = new SqlTracker(ConnectionRollback.create(ConnectionSQL.get()));
        Item item = new Item("name");
        tracker.add(item);
        assertThat(tracker.findAll().size(), is(1));
    }
}