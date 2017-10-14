package com.epam.spring.advanced.homework.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author Denes_Toth
 */
public class EventRatingDeserializer extends JsonDeserializer<EventRating> {
    @Override
    public EventRating deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return EventRating.valueOf(jsonParser.getText().toUpperCase());
    }
}
