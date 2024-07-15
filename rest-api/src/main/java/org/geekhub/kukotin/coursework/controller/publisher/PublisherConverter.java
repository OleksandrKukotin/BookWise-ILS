package org.geekhub.kukotin.coursework.controller.publisher;

import org.geekhub.kukotin.coursework.service.publisher.Publisher;

import java.util.List;

public class PublisherConverter {

    private PublisherConverter(){
    }

    public static PublisherDTO toDto(Publisher entity) {
        return new PublisherDTO(entity.publisherId(), entity.publisherName());
    }

    public static List<PublisherDTO> toDtos(List<Publisher> entities) {
        return entities.stream()
            .map(PublisherConverter::toDto)
            .toList();
    }

    public static Publisher fromDto(PublisherDTO dto) {
        return new Publisher(dto.publisherId(), dto.publisherName());
    }
}
