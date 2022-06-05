package com.provectus.kafka.ui.newserde.spi;

import org.apache.kafka.common.header.Headers;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Serde {

  enum Type {
    KEY, VALUE
  }

  void configure(
      PropertyResolver serdeProperties,
      PropertyResolver kafkaClusterProperties,
      PropertyResolver globalProperties
  );

  Optional<String> description();

  Optional<SchemaDescription> getSchema(String topic, Type type);

  boolean canDeserialize(String topic, Type type);

  boolean canSerialize(String topic, Type type);

  //----------------------------------------------------------------------------

  Serializer serializer(String topic, Type type);

  Deserializer deserializer(String topic, Type type);

  interface Serializer {
    byte[] serialize(String topic, String input);
  }

  interface Deserializer {
    DeserializeResult deserialize(String topic, Headers headers, byte[] data);
  }

}
