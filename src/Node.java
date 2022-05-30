import java.util.*;


public class Node<T> {  //A object that can take almost any input means T
	
	private T data;
	private boolean visited;   //field to check if it has been visited for searching
	private List<Node<T>> children = new ArrayList<>();   //children states will connect to this node
	
	public List<Node<T>> getChildren() {
		return children;
	}
	public void setChildren(List<Node<T>> children) {   //adds a list of the children so they connect to the current node
		this.children = children;
	}
	public T getData() {  //getter function
		return data;
	}
	public void setData(T data) {  //setter functions
		this.data = data;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	
}
