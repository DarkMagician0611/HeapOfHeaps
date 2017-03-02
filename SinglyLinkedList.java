public class SinglyLinkedList<E> {//To maintain the name of participants

	private static class Node<E> {
		private E element;
		private Node<E> next;
		public Node(E e, Node<E> n){
			element = e;
			next = n;
		}
		public E getElement(){
			return element;
		}
		public Node<E> getNext(){
			return next;
		}
		public void setNext(Node<E> n){
			next = n;
		}
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public SinglyLinkedList(){
		head = tail = null;
		size = 0;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public E first(){
		if(isEmpty())
			return null;
		return head.getElement();
	}

	public E last(){
		if(isEmpty())
			return null;
		return tail.getElement();
	}

	public void addFirst(E e){
		head = new Node<>(e, head);
		if(size == 0)
			tail = head;
		size++;
	}

	public void addLast(E e){
		Node<E> newest = new Node<>(e, null);
		if(isEmpty()){
			head = newest;
		}
		else
			tail.setNext(newest);
		tail = newest;
		size++;
	}

	public E removeFirst(){
		if(isEmpty())
			return null;
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if(size == 0){
			tail = null;
		}
		return answer;
	}

	public E find(String ID){
		if(isEmpty())
			return null;
		Node<E> p = head;
		while(p != null){
			if(p.element.equals(ID))
				return p.element;
			p = p.next;
		}
		return null;
	}

	public void print(){
		Node<E> p = head;
		while(p != null){
			System.out.println(p.element.toString());
			p = p.next;
		}
	}
}