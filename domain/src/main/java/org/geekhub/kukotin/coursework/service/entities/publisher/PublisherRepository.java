package org.geekhub.kukotin.coursework.service.entities.publisher;

import java.util.List;

public interface PublisherRepository {

    void save(Publisher publisher);

    List<Publisher> findAll();

    void update(Publisher publisher);

    void delete(Publisher publisher);
}
