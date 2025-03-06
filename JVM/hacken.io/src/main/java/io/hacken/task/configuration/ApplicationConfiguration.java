package io.hacken.task.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hacken.task.database.dao.transaction.AcceptedTransactionDao;
import io.hacken.task.service.transaction.datahandler.CommonTransactionBuffer;
import io.hacken.task.service.transaction.datahandler.DefaultTransactionDataHandlerImpl;
import io.hacken.task.service.transaction.datahandler.TransactionApacheKafkaDataHandlerImpl;
import io.hacken.task.service.transaction.datahandler.TransactionDataHandlerI;
import io.hacken.task.service.transaction.trotting.TransactionTrottingHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.hacken.task.configuration.DefaultValues.GENERIC_KAFKA_GROUP_ID;
import static io.hacken.task.configuration.DefaultValues.MB_TOPIC;
import static java.util.Objects.requireNonNull;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.common.config.TopicConfig.RETENTION_MS_CONFIG;
import static org.springframework.http.HttpMethod.*;

@Slf4j
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Autowired
    public ApplicationConfiguration(@Value("${server.apache-kafka.enable}") boolean isApacheKafkaEnabled,
                                    @Value("${server.apache-kafka.host}") String apacheKafkaHost,
                                    @Value("${server.apache-kafka.port}") int apacheKafkaPort,
                                    @Value("${server.apache-kafka.generic-topic.retention-ms}") String apacheKafkaRetentionMs,
                                    @Value("${server.apache-kafka.generic-topic.partitions}") int apacheKafkaPartitions,
                                    @Value("${server.apache-kafka.generic-topic.replicas}") int apacheKafkaReplicas,
                                    @Value("${server.port}") int port,
                                    ConfigurableApplicationContext applicationContext) {
        if (isApacheKafkaEnabled) {
            String bootstrapServers = apacheKafkaHost + ":" + apacheKafkaPort;
            var beanFactory = applicationContext.getBeanFactory();
            var producerFactory = this.buildApacheKafkaProducerFactory(bootstrapServers);
            var consumerFactory = this.buildApacheKafkaConsumerFactory(bootstrapServers);
            beanFactory.registerSingleton("mbApacheKafkaProducerFactory", producerFactory);
            beanFactory.registerSingleton("mbApacheKafkaConsumerFactory", consumerFactory);
            beanFactory.registerSingleton("mbApacheKafkaTemplate",
                    this.buildApacheKafkaTemplate(producerFactory));
            beanFactory.registerSingleton("mbApacheKafkaListenerContainerFactory",
                    this.buildListenerContainerFactory(consumerFactory));
            beanFactory.registerSingleton("mbApacheKafkaGenericTopic",
                    this.createGenericTopic(apacheKafkaRetentionMs, apacheKafkaPartitions, apacheKafkaReplicas));
        }

        log.info("Server URL -> http://localhost:" + port);
    }

    public ProducerFactory<String, byte[]> buildApacheKafkaProducerFactory(String bootstrapServers) {
        var config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    public ConsumerFactory<String, String> buildApacheKafkaConsumerFactory(String bootstrapServers) {
        var config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(GROUP_ID_CONFIG, GENERIC_KAFKA_GROUP_ID);
        return new DefaultKafkaConsumerFactory<>(config);
    }

    public KafkaTemplate<String, byte[]> buildApacheKafkaTemplate(ProducerFactory<String, byte[]> factory) {
        return new KafkaTemplate<>(factory);
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> buildListenerContainerFactory(
            ConsumerFactory<String, String> factory
    ) {
        var listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        listenerContainerFactory.setConsumerFactory(factory);
        return listenerContainerFactory;
    }

    public NewTopic createGenericTopic(String retentionMsValue, int partitionCount, int replicaCount) {
        return TopicBuilder.name(MB_TOPIC)
                .config(RETENTION_MS_CONFIG, retentionMsValue)
                .partitions(partitionCount)
                .replicas(replicaCount)
                .build();
    }

    public CorsConfigurationSource createCorsConfigurationSource(@Value("${server.trusted-urls}") String trustedUrls) {
        String[] urls = Arrays.stream(trustedUrls.split(",")).map(String::trim).toArray(String[]::new);
        String[] methods = {GET.name(), POST.name(), PUT.name(), OPTIONS.name()};
        var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(urls));
        configuration.setAllowedMethods(Arrays.asList(methods));
        configuration.setAllowedHeaders(List.of("*"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*").addResourceLocations(new UrlResource(
                requireNonNull(this.getClass().getClassLoader().getResource("static"))));
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() {
        return authentication -> authentication;
    }

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(@Value("${server.trusted-urls}") String trustedUrls,
                                                            HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(customizer ->
                        customizer.requestMatchers("/**").permitAll().anyRequest().hasRole("USER"))
                .cors(customizer -> customizer.configurationSource(this.createCorsConfigurationSource(trustedUrls)))
                .csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable).build();
    }

    @Bean
    public TransactionDataHandlerI buildTransactionDataHandler(
            @Autowired(required = false) KafkaTemplate<String, byte[]> kafkaTemplate,
            AcceptedTransactionDao acceptedTransactionDao,
            TransactionTrottingHandler trottingHandler,
            CommonTransactionBuffer transactionBuffer,
            ObjectMapper objectMapper
    ) {
        return kafkaTemplate == null
                ? new DefaultTransactionDataHandlerImpl(transactionBuffer, trottingHandler, acceptedTransactionDao)
                : new TransactionApacheKafkaDataHandlerImpl(objectMapper, transactionBuffer, trottingHandler, kafkaTemplate);
    }

    public ObjectMapper buildJacksonObjectMapper() {
        return new ObjectMapper();
    }
}
