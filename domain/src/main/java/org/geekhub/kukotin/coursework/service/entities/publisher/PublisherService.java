package org.geekhub.kukotin.coursework.service.entities.publisher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    PublisherRepository repository;

    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public void add(Publisher publisher) {
        repository.save(publisher);
    }

    public List<Publisher> getAll() {
        return repository.findAll();
    }

    public void update(Publisher publisher) {
        repository.update(publisher);
    }

    public void remove(Publisher publisher) {
        repository.delete(publisher);
    }
}
