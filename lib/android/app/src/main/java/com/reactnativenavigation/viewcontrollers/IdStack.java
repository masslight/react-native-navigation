package com.reactnativenavigation.viewcontrollers;

import android.support.annotation.NonNull;

import com.reactnativenavigation.utils.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.reactnativenavigation.utils.CollectionUtils.last;
import static com.reactnativenavigation.utils.CollectionUtils.removeLast;

public class IdStack<E> implements Iterable<String> {

	private final ArrayList<String> deque = new ArrayList();
	private final Map<String, E> map = new HashMap<>();

	public void push(String id, E item) {
		deque.add(id);
		map.put(id, item);
	}

    public void set(String id, E item, int index) {
        deque.add(index, id);
        map.put(id, item);
    }

	public E peek() {
        return isEmpty() ? null : map.get(last(deque));
	}

	public E pop() {
	    return isEmpty() ? null : map.remove(removeLast(deque));
	}

	public boolean isEmpty() {
		return deque.isEmpty();
	}

	public int size() {
		return deque.size();
	}

	public String peekId() {
		return last(deque);
	}

	public void clear() {
		deque.clear();
		map.clear();
	}

	public E get(final String id) {
		return map.get(id);
	}

	public E get(final int index) {
        return map.get(deque.get(index));
    }

	public boolean containsId(final String id) {
		return deque.contains(id);
	}

	public E remove(final String id) {
		if (!containsId(id)) {
			return null;
		}
		deque.remove(id);
		return map.remove(id);
	}

	public boolean isTop(final String id) {
		return StringUtils.isEqual(id, peekId());
	}

	@NonNull
    @Override
	public Iterator<String> iterator() {
		return deque.iterator();
	}


	public Collection<E> values() {
		return map.values();
	}

  // Get child controllers preserving the order. Collection has underfined order of elements.
  public List<E> array() {
    List<E> list = new ArrayList();
    //Iterator<String> iterator = iterator();
    Iterator<String> iterator = deque.descendingIterator();
    while (iterator.hasNext()) {
      list.add(map.get(iterator.next()));
    }
    return list;
  }
    public void remove(Iterator<String> iterator, String id) {
        iterator.remove();
        map.remove(id);
    }
}
