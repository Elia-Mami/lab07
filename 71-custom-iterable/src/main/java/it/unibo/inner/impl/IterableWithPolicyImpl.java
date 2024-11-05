package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private List<T> array;
    private Predicate<T> myFilter;

    public IterableWithPolicyImpl(T[] elements){
        this(elements, new Predicate<T>() {

            @Override
            public boolean test(T elem) {
                return true;
            }
            
        } );
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> predicate){
        array = Arrays.asList(elements);
        myFilter = predicate;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public class MyIterator implements Iterator<T>{
        private List<T> iterableArray;
        private int currentElement = 0;

        public MyIterator(){
            this.iterableArray = array;
        }

        @Override
        public boolean hasNext() {
            return currentElement < array.size();
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T nextElement = iterableArray.get(currentElement);
            currentElement++;

            return nextElement;
        }

    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        
    }
    
}
