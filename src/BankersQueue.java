import java.util.*;

public class BankersQueue<E> implements Queue<E>, Iterable<E> {
    private ArrayList<E> front;
    private ArrayList<E> back;
    private int modCount = 0;


    public BankersQueue() {
        front = new ArrayList<>();
        back = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        back.add(e);
        ++modCount;
    }

    @Override
    public void remove() {
        checkIfEmpty();
        front.remove(front.size() - 1);
        ++modCount;
    }

    @Override
    public E element() {
        checkIfEmpty();
        return front.get(front.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return front.isEmpty() && back.isEmpty();
    }

    @Override
    public int size() {
        return front.size() + back.size();
    }

    private void checkIfEmpty(){
        if(isEmpty())
            throw new NoSuchElementException();
        if (front.isEmpty())
            backToFront();
    }

    private void backToFront() {
        Iterator<E> it = back.iterator();
        backToFront(it);
    }

    private void backToFront(Iterator<E> it){
        if(it.hasNext()){
            front.add(0, it.next());
            it.remove();
            backToFront(it);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new BankersIterator();
    }

    private class BankersIterator implements Iterator<E>{
        private int expectedModCount = modCount;
        ListIterator<E> frontIt = front.listIterator(front.size());
        ListIterator<E> backIt = back.listIterator();

        @Override
        public boolean hasNext() {
            return frontIt.hasPrevious() || backIt.hasNext();
        }

        @Override
        public E next() {
            if(expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if(frontIt.hasPrevious())
                return frontIt.previous();
            else if(backIt.hasNext())
                return backIt.next();
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
