
import java.util.ArrayList;

public class IDLList<E> {
	
	private class Node<E> {
		private E data;
		Node<E> next;
		Node<E> previous;
		
		Node(E elem){
			data = elem;
		}
		Node(E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.previous = prev;
			this.next = next;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indicies;
	
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indicies = new ArrayList<Node<E>>();
	}
	
	public boolean add(int index, E elem) {
		if(index < 0 || index > size) {
			throw new IllegalArgumentException();
		}
		if(head == null || index == 0) {
			add(elem);
		}
		else if(index == size){
			append(elem);
		}
		else{
			Node<E> current = indicies.get(index-1);
			Node<E> new_node = new Node<E>(elem);
			new_node.next = current.next;
			current.next.previous = new_node;
			current.next = new_node;
			new_node.previous = current;
			
			size++;
			indicies.add(index, current);
		}
		return true;
	}
	public boolean add(E elem)
	{
		Node<E> n = new Node<E>(elem);
		if(head == null)
		{
			head = n;
			tail = n;
		}
		else {
			n.next = head;
			head.previous = n;
			head = n;
		}
		
		
		indicies.add(0, head);
		size++;
		return true;
	}
	public boolean append(E elem) {
		Node<E> n = new Node<E>(elem);
		n.previous = tail;
		tail.next = n;
		tail = n;
		size++;
		indicies.add(size-1, n);
		return true;
	}

	public E get(int index) {
		if(index < 0 || index >= size)
			throw new IllegalArgumentException("Invalid index");
		return indicies.get(index).data;
	}
	
	public E getHead() {
		if(head == null)
			throw new IllegalStateException("List is empty");
		return head.data;
	}
	
	public E getLast() {
		if(tail == null)
			throw new IllegalStateException("List is empty");
		return tail.data;
	}
	public int size() {
		return size;
	}
	public E remove() {
		if(head == null) {
			throw new IllegalStateException();
		}
		else
		{
			E data = head.data;
			Node<E> next = head.next;
			head = null;
			head = next;
			indicies.remove(0);
			size--;
			return data;
		}
		
	}
	public E removeLast() {
		if(head == null) {
			throw new IllegalStateException();
		}
		else {
			if(tail == null)
				return remove();
			Node<E> prev = tail.previous;
			E data = tail.data;
			tail = null;
			tail = prev;
			tail.next =null;
			
			indicies.remove(size-1);
			size--;
			return data;
			
		}
	}
	public E removeAt(int index) {
		if(index >= size || index < 0) {
			throw new IllegalArgumentException();
		}
		if(index == 0)
			remove();
		else if(index == size-1) 
			removeLast();
		else {
			Node<E> current = indicies.get(index);
			
			current.previous.next = current.next;
			current.next.previous = current.previous;
			
			indicies.remove(index);
			size--;
			return current.data;
		}
		return null;
	}
	
	public boolean remove(E elem) {
		Node<E> current = head;
		int index = 0;
		while(current != null && current.data != elem ) {
			current = current.next;
			index++;
		}
		if(current == null)
			return false;
		else {
			removeAt(index);
			return true;
		}
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		Node<E> current = head;
		s.append("[");
		while (current!=null) {
			if(current == tail)
				s.append(current.data.toString());
			else
				s.append(current.data.toString()+",");
	
			current = current.next;
		}
		s.append("]");
		return s.toString();
	}

}
