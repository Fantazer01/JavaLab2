package org.example.task16;

import java.util.NoSuchElementException;

public class Queue<T> {
    public class Iterator {
        private Node next;
        private Node  ptr;

        Iterator(Node first) {
            ptr = null;
            next = first;
        }

        boolean hasNext() {
            return next != null;
        }

        T remove() {
            if (ptr.prev == null) {
                ptr = null;
                return Queue.this.remove();
            }
            Node del = ptr;
            ptr.prev.next = next;
            ptr = null;
            Queue.this.size--;
            return del.data;
        }

        T next() {
            if (hasNext()) {
                T res = next.data;
                ptr = next;
                next = ptr.next;
                return res;
            } else {
                throw new NoSuchElementException("next = null");
            }
        }

        public T current() {
            return ptr.data;
        }
    }
    public class Node {
        /**
         * param data - информауия, которую хранит Node
         * param next - следующий Node
         * param prev - передыдущий Node
         * В задании не говорилось какую конкретно очередь реализовывать -
         * односвязную или двусвязную. Сделал двусвязную
         */

        private T data;
        private Node next;
        private Node prev;
        public Node(T data) {
            this.data = data;
            next = null;
            prev = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getPrev() {
            return prev;
        }
    }

    /**
     * param first - первый Node в очереди
     * param last - последний Node в очереди
     * param size - размер очереди
     */
    private Node first;
    private Node last;
    private int size;

    /**
     * Пустой конструктор
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }
    public Iterator iterator() {
        return new Iterator(first);
    }

    /**
     * добавляет новый Node в конец очереди
     * @param data ифнормация которую хранит элемент очереди
     */
    public void add(T data) {
        if (size == 0) {
            first = new Node(data);
            last = first;
            size++;
            return;
        }

        if (size == 1) {
            last = new Node(data);
            first.setNext(last);
            last.setPrev(first);
            size++;
            return;
        }

        Node node = last;
        last = new Node(data);
        node.setNext(last);
        last.setPrev(node);
        size++;
    }

    /**
     * удаляет первый элемент очереди
     */
    public T remove() {
        if (size == 0) return null;

        if (size == 1) {
            Node it = first;
            first = null;
            last = null;
            size--;
            return it.data;
        }
        Node it = first;
        Node node = first.getNext();
        node.setPrev(null);
        first = node;
        size--;
        return it.data;

    }

    /**
     * геттер для size
     * @return возвращает размер очереди
     */
    public int size() {
        return size;
    }

    /**
     * Перегруженный метод toString, чтобы можно было выводить
     *  объект Queue в System.out.print()
     * @return  возвращает строку с содержимым очереди
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append("'");
            sb.append(node.getData());
            sb.append("'");
            if (node.next != null) {
                sb.append(",");
            }
            node = node.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
