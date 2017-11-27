package org.myas.kafka.utils;

/**
 * @author Mykhailo Yashchuk
 */
public class PropertiesHelper {
    // Consumer mandatory fields
    public static final String CONS_MAN_BOOTSTRAP_SERVERS = "bootstrap.servers";
    public static final String CONS_MAN_GROUP_ID = "group.id";
    public static final String CONS_MAN_KEY_DESER = "key.deserializer";
    public static final String CONS_MAN_VALUE_DESER = "value.deserializer";

    // Consumer important fields
    public static final String CONS_IMP_FETCH_MIN_BYTES = "fetch.min.bytes";
    public static final String CONS_IMP_FETCH_MAX_WAIT_MS = "fetch.max.wait.ms";
    public static final String CONS_IMP_MAX_PART_FETCH_BYTES = "max.partition.fetch.bytes";
    public static final String CONS_IMP_SESSION_TIMEOUT_MS = "session.timeout.ms";
    public static final String CONS_IMP_AUTO_OFFSET_RESET = "auto.offset.reset";
    public static final String CONS_IMP_ENABLE_AUTO_COMMIT = "enable.auto.commit";
    public static final String CONS_IMP_PART_ASSIGN_STRATEGY = "partition.assignment.strategy";
    public static final String CONS_IMP_CLIENT_ID = "client.id";
    public static final String CONS_IMP_MAX_POLL_RECORDS = "max.poll.records";
    public static final String CONS_IMP_RECV_BUFFER_SIZE = "receive.buffer.size";
    public static final String CONS_IMP_SEND_BUFFER_SIZE = "send.buffer.size";

    // Producer mandatory fields
    public static final String PROD_MAN_BOOTSTRAP_SERVERS = "bootstrap.servers";
    public static final String PROD_MAN_KEY_SER = "key.serializer";
    public static final String PROD_MAN_VALUE_SER = "value.serializer";

    // Producer important fields
    public static final String PROD_IMP_ACKS = "acks";
    public static final String PROD_IMP_BUFFER_MEMORY = "buffer.memory";
    public static final String PROD_IMP_COMPR_TYPE = "compression.type";
    public static final String PROD_IMP_RETRIES = "retries";
    public static final String PROD_IMP_BATCH_SIZE = "batch.size";
    public static final String PROD_IMP_LINGER_MS = "linger.ms";
    public static final String PROD_IMP_CLIENT_ID = "client.id";
    public static final String PROD_IMP_MAX_IN_FLIGHT_REQ_PER_CON = "max.in.flight.requests.per.connection";
    public static final String PROD_IMP_TIMEOUT_MS = "timeout.ms";
    public static final String PROD_IMP_REQ_TIMEOUT_MS = "request.timeout.ms";
    public static final String PROD_IMP_META_FETCH_TIMEOUT_MS = "matadata.fetch.timeout.ms";
    public static final String PROD_IMP_MAX_BLOCK_MS = "max.block.ms";
    public static final String PROD_IMP_MAX_REQ_SIZE = "max.request.size";
    public static final String PROD_IMP_RECV_BUFFER_BYTES = "receive.buffer.bytes";
    public static final String PROD_IMP_SEND_BUFFER_BYTES = "send.buffer.bytes";
}
