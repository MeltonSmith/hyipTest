package r.ian.ianlabtest.listener;

/**
 * @author Melton Smith
 * @since 21.07.2021
 *
 * Опциональный интерфейс для лисенеров.
 * Необязательно реализовывать всю инфраструктуру по регистрации и обработке, достаточно и тестов.
 *
 * @param <T> тип сообщений, которые будем слушать.
 */
public interface MessageListener<T> {
    void listen(T incomingMessage);
}
