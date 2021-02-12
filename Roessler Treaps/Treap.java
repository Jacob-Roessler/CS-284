//Jacob Roessler
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>>{
	private Random priorityGenerator; //used to generate random priorities when none is provided
	private Node<E> root; //root of treap
	
	/**
	 * Inner node class
	 * @param <E> generic comparable type 
	 */
	private static class Node<E extends Comparable<E>>{
		public E data; //node data
		public int priority; //node priority
		public Node<E> left; //left child 
		public Node<E> right; //right child
		
		/**
		 * Constructs Node<E> with given data and an integer priority
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
			left= null;
			right= null;
		}
		
		/**
		 * Performs a right rotation on the "self" node
		 * @return Node that has been rotated right
		 */
		public Node<E> rotateRight(){
			if (left == null) 
				return this; 
			else {
				Node<E> root = left;
				left = left.right;
				root.right = this;
				return root;
			}
			
		}
		
		/**
		 * Performs a left rotation on the "self" node
		 * @return Node that has been rotated left
		 */
		public Node<E> rotateLeft(){
			if (right == null)
				return this; 
			else {
				Node<E> root = right;
				right = right.left;
				root.left = this;
				return root;
			}
			
		}
		
		/**
		 *@return String representation of the node
		 */
		public String toString() {
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}
	
	
	
	/**
	 * Default constructor for treap which has a root = null and constructs a random priority generator with a random seed
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	
	/**
	 * Same as default but changes the priority generation based on the seed
	 * @param seed for priority generation
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	
	/**
	 * Adds an element with the value "key" but with a random priority into the treap using the add(key, priority) method
	 * @param key or data element to be added
	 * @return true if node added will always be true
	 */
	boolean add(E key) {
		int priority = priorityGenerator.nextInt();
	    return add(key, priority);
	}
	
	/**
	 * Adds an element to the treap by keeping track of the path from the root in a stack then
	 * calls the reheap function with the stack and node to maintain the treap invariant
	 * @param key or data element to be added
	 * @param priority to be assigned to the added node
	 * @return true
	 */
	boolean add(E key, int priority) {
	    Node<E> toAdd = new Node<E>(key, priority);  

	    Node<E> r = root;  
	    Node<E> trail = null;  
	    Stack<Node<E>> path = new Stack<Node<E>>();
	    
	    while (r != null) {  
	        trail = r;  
	        if (key.compareTo((E) r.data) < 0)  
	            r = r.left;  
	        else
	            r = r.right;
	        path.push(trail);

	    }  
	    
	    if (trail == null)  
	        trail = toAdd;  
	    else if (key.compareTo((E) trail.data) < 0)  
	        trail.left = toAdd;      
	    else
	        trail.right = toAdd;  
	    
	
	    reheap(path, toAdd);
	    return true;  
	}
	
	/**
	 * Uses a stack to perform a reheap by rotating nodes based on their priority.
	 * @param path input stack
	 * @param current node to be added
	 */
	public void reheap(Stack<Node<E>> path, Node<E> current) {
		if(path.empty())
			root = current;
		else if(current.priority > path.peek().priority) {
			if(path.peek().right == current) {
				Node<E> popped = path.pop();
				popped.rotateLeft();
				if(!path.empty()) {
					if(path.peek().right == popped) {
						path.peek().right = current;
					}
					else {
						path.peek().left = current;
					}
				}
			}
			else {
				Node<E> popped = path.pop();
				popped.rotateRight();
				if(!path.empty()) {
					if(path.peek().right == popped) {
						path.peek().right = current;
					}
					else {
						path.peek().left = current;
					}
				}
			}
			reheap(path, current);
		}
		else
			return;
	}
	
	/**
	 * Checks if value "key" is in the treap then will proceed with removal using delete helper function
	 * @param key or data value
	 * @return true if node is present and capable of removal false otherwise
	 */
	boolean delete(E key) {
		if(find(key)) {
			root = delete(root, key);
			return true;
		}
		else
			return false;
	}
	/**
	 * A recursive delete function using rotation to ensure the intended removal node first becomes a leaf thus maintaining
	 * the heap invariant
	 * @param current node that is being inspected
	 * @param key data to be removed
	 * @return the new root node that is asigned in the main remove function
	 */
	public Node<E> delete(Node<E> current, E key){
		if (current == null) 
	        return current; 
		  
	    if (current.data.compareTo(key) > 0) 
	        current.left = delete(current.left, key); 
	    else if (current.data.compareTo(key) < 0) 
	        current.right = delete(current.right, key); 
	    else if (current.left == null) 
	        current = current.right;  
	    else if (current.right == null) 
	        current = current.left;
	    else if (current.left.priority < current.right.priority) 
	    { 
	        current = current.rotateLeft(); 
	        current.left = delete(current.left, key); 
	    } 
	    else
	    { 
	        current = current.rotateRight(); 
	        current.right = delete(current.right, key); 
	    } 
	  
	    return current; 
	}
	
	/**
	 * Returns true if the node with key is in the treap
	 * @param key
	 * @return true if found false otherwise
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * find helper function that used recursion to explorer left and right trees for key
	 * @param root of treap
	 * @param key or data to find
	 * @return true if found false otherwise
	 */
	private boolean find(Node<E> root, E key) {
		if(root == null)
			return false;
		else {
			int comparison = root.data.compareTo(key);
			if(comparison == 0)
				return true;
			else
				return comparison < 0 ? find(root.right, key) : find(root.left, key);
		}

	}
	
	/**
	 *Generates treap string based on levels using spaces
	 *@return String representation of treap
	 */
	public String toString() {
		return toString(root,0).toString();
	}
	
	/**
	 * Main function for building the string of the treap from in class tree to string
	 * @param current node inspecting
	 * @param i level of spaces
	 * @return string builder of the representation of the treap
	 */
	private StringBuilder toString(Node<E> current, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append(" ");
		}
		if (current==null) {
			r.append("null\n");
		} else {
			r.append(current.toString()+"\n");
			r.append(toString(current.left,i+1));
			r.append(toString(current.right,i+1));
		}
		return r;
	}
	
	
}
