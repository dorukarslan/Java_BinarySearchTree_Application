
// Title: BST
// Author: Doruk Arslan
/// Description: This class defines a BST
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class BinaryST<Key extends Comparable<Key>, Value> {

	private Node root;

	public class Node {
		private Key key; // Create key variable
		private Value val; // Create value variable

		private Node left, right; // We create node left and right for BST
		public int count; // We create int count for size.

		public Node(Key key, Value val, int count) {
			// --------------------------------------------------------
			// Summary: Create the node.
			// Precondition: Key key , value val, count is an integer.
			// Postcondition: The value of the key,val,count is set.
			// --------------------------------------------------------
			this.key = key;
			this.val = val;
			this.count = count;
		}

		public void SetKey(Key newkey) {
			// We set our key value on this function.
			this.key = newkey;
		}
	}

	public void put(Key key, Value val) {
		// Recursive call of put method to put root first
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		// --------------------------------------------------------
		// Summary: Put the nodes on here.
		// Precondition: Node x , Key key , val is a value.
		// Postcondition: We add our nodes our tree according to rules.
		// --------------------------------------------------------
		if (x == null) {
			// Check x is null or not.
			return new Node(key, val, 1);
		}
		// For binary tree rules we use compare to method.
		int cmp = key.compareTo(x.key);
		// If cmp<0 so if it is smaller than key add on the left.
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		}
		// If cmp>=1 so if it is bigger or equal than key add on the right.
		else if (cmp >= 0) {
			x.right = put(x.right, key, val);
		}
		// increase our size.
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Value get(Key key) {
		// --------------------------------------------------------
		// Summary: We get our key on here.
		// Precondition: Key key create.
		// Postcondition: We find the values on our key.
		// --------------------------------------------------------
		// First we create temp node and set it to root.
		Node temp = root;

		// Temp shouldn't be null.
		while (temp != null) {
			// For compare to method we create integer result.
			int result = key.compareTo(temp.key);
			// If cmp<0 so if it is smaller than key pass on the left.
			if (result < 0)
				temp = temp.left;
			// If cmp>1 so if it is bigger than key pass on the right.
			else if (result > 0)
				temp = temp.right;

			else {
				// Return our value we search
				return temp.val;

			}

		}
		return null;
	}

// to search for a specific value in tree
	public Value Specget(Key key, Value val) {
		// --------------------------------------------------------
				// Summary: We get our key more spesific.
				// Precondition: given data exist in tree 
				// Postcondition: different from the get function after finding the given key we have one more condition to compare it's value with given value
				// thats why we also  have value as an argument in the function
				// --------------------------------------------------------
				// First we create temp node and set it to root.
		Node temp = root;

		while (temp != null) {
			// For compare to method we create integer result.
			int result = key.compareTo(temp.key);
			
			// If cmp<0 so if it is smaller than key pass on the left.
			if (result < 0)
				temp = temp.left;
			// If cmp>1 so if it is bigger than key pass on the right.
			else if (result > 0)
				temp = temp.right;

			else {
				
				// after finding given key on the tre we also checked its value with given value
				// if they are not equal we are passing the right child of the node that found.(Because equal key can be located at right of it)
				if (temp.val == val) {
					return temp.val;

				} else {
					temp = temp.right;
				}

			}

		}
		return null;
	}

// to exclude  and make a spesific node from the tree in order to delete it afterwards
	public void SpecDelete(Key key, Value val, Key newKey) {
		// Summary: We delete our key more spesificly.
		// Precondition: given data exist in tree  and it is key is equal to key 
		// Postcondition: we set  node's key to newKey in order to mark it and delete it more specific
		// thats why we also  have newKEy  as an argument in the function
		// --------------------------------------------------------
		
		// First we create temp node and set it to root.
		Node temp = root;

		while (temp != null) {
			// For compare to method we create integer result.
			int result = key.compareTo(temp.key);
			// If cmp<0 so if it is smaller than key pass on the left.
			if (result < 0)
				temp = temp.left;
			// If cmp>1 so if it is bigger than key pass on the right.

			else if (result > 0)
				temp = temp.right;

			else {
				// after finding given key on the tre we also checked its value with given value
				// if they are not equal we are passing the right child of the node that found.(Because equal key can be located at right of it)
				if (temp.val == val) {
					// if it is found we set it's key to newKey and delete it from the tree 
					temp.SetKey(newKey);
				//	System.out.println("Silinme yapılıyor kendisi : "+temp.val+ "key i : "+ temp.key);
					
					delete(newKey);
					
				} else {
					temp = temp.right;
				}

			}

		}
	}
	
	
// purpose of this method is travel in the nodes of tree via inputs in order to check if there is an error or missing design in the tree 
	public void trytree() {
		Scanner key = new Scanner(System.in);

		Node x = root;
		boolean flag = true;
		System.out.println("Root : " + x.key + " " + x.val);
		// printing first argument 
		while (flag) {
			System.out.println("command : ");
			int command = key.nextInt();
			// getting input from the user 
			switch (command) {

			case 1:
				// to go left child 
				if (x.left == null) {
					// if it is null we are printing an error message

					System.out.println("Left is null  current : " + x.key + " " + x.val);
					break;
				} else {
					// if it is not null we go to left child of the current node and print its data 

					x = x.left;
					System.out.println(x.key + "  " + x.val);
					break;
				}
				
			case 2:
				// to go right child 
				if (x.right == null) {
					// if it is null we are printing an error message
					System.out.println("Right is null  current : " + x.key + " " + x.val);
					break;
				} else {
					// if it is not null we go to right child of the current node and print its data 
					x = x.right;
					System.out.println(x.key + "  " + x.val);
					break;
				}

			case 3:
				// to break to loop 
				flag = false;
				break;
			}
		}

	}

	public int size() {
		// Recursive call of size method to the root first
		return size(root);
	}

	private int size(Node x) {
		// If x is null method should return 0.
		if (x == null)
			return 0;
		// Else it return x.count so return the size.
		return x.count;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	public boolean isEmpty() {
		// If root is null that means that tree is empty.
		if (root == null)
			return true;
		// If it is not null that means tree is not empty.
		else
			return false;
	}

	private Node delete(Node x, Key key) {
		// --------------------------------------------------------
		// Summary: We delete our node on here
		// Precondition: X is node key is Key.
		// Postcondition: We find the values on our key.
		// --------------------------------------------------------

		// First we checked x is null or not
		if (x == null) {
			return null;
		}
		// For compare to method we create integer cmp.
		int cmp = key.compareTo(x.key);

		// If cmp<0 so if it is smaller than key delete on the left node.
		if (cmp < 0) {
			x.left = delete(x.left, key);
		}
		// If cmp>0 so if it is bigger than key delete on the right node.
		else if (cmp > 0) {
			x.right = delete(x.right, key);
		}
		// On here we checked x.right and x. left is null or not
		else {
			// Node with only one child or no child.
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			// First we create temp node x which is equals to x.
			Node t = x;
			// Node with two children we should take the smallest one.
			x = min(t.right);
			x.right = deleteMin(t.right);
			// Equals x left data to temp left data.
			x.left = t.left;
		}
		// decrease our size.
		x.count = size(x.left) + size(x.right) + 1;
		return x;

	}

	public void deleteMin() {
		// Recursive call of delete method to root first
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		// If left of x null return right of x.
		if (x.left == null)
			return x.right;
		// Equal to x left to x left of deleteMin.
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;

	}

	public void printNodes(Node root) {
		// We print our nodes on here. It is also same with the inorder traversal and
		// recursive.

		// First we checked root is null or not.
		if (root != null) {
			// First call recursive left.
			printNodes(root.left);
			// Print root on here.
			System.out.println(root.val);
			// First call recursive right.
			printNodes(root.right);
		}
	}

	public ArrayList<String> desc(Node root, ArrayList<String> values) {
		
		// --------------------------------------------------------
		// Summary: We return an arraylist that contains all data in the tree in the descending order 
		// Precondition: all data exist in the tree 
		// Postcondition: we add all  value from every node to the given array list and returned it 
		// --------------------------------------------------------

		if (root != null) {
			

			desc(root.right, values);
			// recursively going every node starting from largets(right most) to the smallest(left most)
			// and add their values to the  arraylist that given as an argument 
			values.add(root.val.toString());

			desc(root.left, values);

			return values;

		} else {
			return null;
		}

	}

	public ArrayList<String> asc(Node root, ArrayList<String> values) {
		// --------------------------------------------------------
		// Summary: We return an arraylist that contains all data in the tree in the ascending order 
		// Precondition: all data exist in the tree 
		// Postcondition: we add all  value from every node to the given array list and returned it 
		// --------------------------------------------------------
		if (root != null) {

			asc(root.left, values);
			// recursively going every node starting from  smallest(left most)  to the largets(right most)
			// and add their values to the  arraylist that given as an argument 
			values.add(root.val.toString());

			asc(root.right, values);

			return values;

		} else {
			return null;
		}

	}

	public void findFullNode(Node root) {
		// Find whole nodes on tree

		// First we checked root is null or not.
		if (root != null) {
			// Traverses given tree in Inorder fashion and
			// gets all nodes recursively
			// non-empty
			findFullNode(root.left);
			findFullNode(root.right);
		}
	}

	public Node getRoot() {
		// We get our root with this method
		return root;
	}

	public Node min(Node node) {
		// return the minimum data value found in that
		// tree

		// We create temp Node and equalize it to node.
		Node temp = node;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}
	
	
}