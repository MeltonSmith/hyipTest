package r.ian.ianlabtest.listener;

/**
 * @author Melton Smith
 * @since 21.07.2021
 *
 * Listener optional interface.
 *
 * @param <T> message type to listen.
 */
public interface MessageListener<T> {
    void listen(T incomingMessage);
}
