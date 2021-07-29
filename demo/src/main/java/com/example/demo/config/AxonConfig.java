package com.example.demo.config;

import com.mongodb.client.MongoClient;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Autowired
    public void configuration(Configurer configurer, MongoClient client) {
        configurer.configureEmbeddedEventStore(configuration -> storageEngine(client)).eventProcessing(conf -> {
            conf.registerTokenStore(configuration -> tokenStore(client, configuration.serializer()));
        });
    }

    @Bean
    public EventStorageEngine storageEngine(MongoClient client) {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build();
    }

    @Bean
    public TokenStore tokenStore(MongoClient client, Serializer serializer) {
        return MongoTokenStore.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).serializer(serializer).build();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(org.axonframework.config.Configuration configuration) {
        return new EventCountSnapshotTriggerDefinition(configuration.snapshotter(), 5);
    }

    @Bean
    public Configurer configurer() {
        return DefaultConfigurer.defaultConfiguration();
    }
}
