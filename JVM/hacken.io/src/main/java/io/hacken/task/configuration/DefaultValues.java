package io.hacken.task.configuration;

public final class DefaultValues {

    private DefaultValues() {
        throw new IllegalArgumentException("Private constructor");
    }

    public static final int DEFAULT_PAGE_LENGTH = 30;

    public static final String MB_TOPIC = "TRANSACTION_TOPIC";

    public static final String GENERIC_KAFKA_GROUP_ID = "hacken.io";
}
