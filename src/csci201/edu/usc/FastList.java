package csci201.edu.usc;

public class FastList<AnyType extends IntegerComparable> {
	// each node has between one and eight next references
	public static final int MAX_LEVEL = 7;

	// reference to a header node
	// the value stored in the header node is never accessed.
	private Node<AnyType> head;

	// the current highest level of all nodes in the list
	private int maxListLevel;

	/**
	 * Builds an empty list with a header node
	 */
	public FastList() {
		maxListLevel = 0;
		head = new Node<AnyType>(MAX_LEVEL, null);
	}

	/**
	 * Returns the string contents of the list The method traverses the level 0
	 * references
	 */
	public String toString() {
		return toString(0);
	}

	/**
	 * Returns the string contents of the list at a given level The method traverses
	 * nodes at given level
	 */
	public String toString(int level) {

		if (level > maxListLevel)
			return "";

		Node<AnyType> currentNode = head;
		Shelter shelter;
		String nodes = "[HDR]-->";
		while (currentNode.next[level] != null) {
			shelter = (Shelter) currentNode.next[level].data;
			nodes += shelter.getChiralFrequency().toString();
			currentNode = currentNode.next[level];
			if (currentNode.next[level] != null) {
				nodes += "-->";
			} else {
				nodes += "-->NULL";
			}
		}
		return nodes;
	}

	/**
	 * Returns true if the given value is stored in the list, otherwise false. The
	 * search begins at the topmost reference of the header
	 */
	public AnyType contains(int toSearch) {
		return null;
	}

	/**
	 * Returns true if the given value is stored in the list, otherwise false. The
	 * search begins at the topmost reference of the header
	 */
	public AnyType contains(AnyType toSearch) {
		Node<AnyType> currentNode = head;
		
		// Go through each level starting with the highest
		for (int i = maxListLevel; i >= 0; i--) {
			System.out.println("Pointer at level: " + i);
			// Move forward to the next node
			while ((currentNode.next[i] != null) 
					&& (((IntegerComparable) currentNode.next[i].data).compareTo(toSearch) <= 0)) {
				currentNode = currentNode.next[i];
				System.out.println(currentNode.data.toString());
			}
			
			// Need to go down a level
			if ((currentNode.next[i] != null) 
					&& (((IntegerComparable) currentNode.next[i].data).compareTo(toSearch) > 0)) {
				System.out.println("Next node: "+ currentNode.next[i].data.toString() + " has a larger value, need to go down a level");
			} else {
				System.out.println("Next node is NULL, need to go down a level");
			}
		}
		
		if ((currentNode != null) && (((IntegerComparable) currentNode.data).compareTo(toSearch) == 0)) {
			System.out.println(currentNode.data.toString());
			System.out.println("Found matching node");
			return currentNode.data;
		}
		else {
			System.out.println("Could not find a match!!!");
			return null;
		}
	}

	/**
	 * Inserts a given value into the list at random level In order to insert a new
	 * node into the list we must maintain an array of nodes whose next references
	 * must be updated.
	 */
	public void insert(AnyType toInsert) {
		int randLevel = randomLevel();
		System.out.println("Random Level = " + randLevel);
		insertHelper(toInsert, randLevel);
	}

	/**
	 * Returns a random level between 0 and MAX_LEVEL
	 */
	public int randomLevel() {
		int rand = (int) (Math.log(1 - Math.random()) / Math.log(0.5)); 
		return Math.min(rand, MAX_LEVEL);
	}
	

	/**
	 * Inserts a given value into the list at a given level The level must not
	 * exceed MAX_LEVEL.
	 */
	public void insert(AnyType toInsert, int level) {
		if (level >= 0 && level <= MAX_LEVEL)
			insertHelper(toInsert, level);
	}

	private void insertHelper(AnyType toInsert, int level) {

		System.out.println("Max Level = " + maxListLevel + " Insert Level = " + level);
		if (level > maxListLevel) {
			System.out.println("Need to adjust head node");
			adjustHead(level);
		}

		Node<AnyType>[] nodesToUpdate = new Node[maxListLevel + 1];
		Node<AnyType> currentNode = head;

		for (int i = level; i >= 0; i--) {
			while ((currentNode.next[i] != null) && ((IntegerComparable) currentNode.next[i].data).compareTo(toInsert) < 0)
				currentNode = currentNode.next[i];
			nodesToUpdate[i] = currentNode;
		}

		currentNode = new Node<AnyType>(level, toInsert);

		for (int i = 0; i <= level; i++) {
			currentNode.next[i] = nodesToUpdate[i].next[i];
			nodesToUpdate[i].next[i] = currentNode;
		}

		System.out.println("Content of nodesToUpdate array: ");
		for (int i = 0; i < nodesToUpdate.length; i++) {
			if (nodesToUpdate[i] != null && nodesToUpdate[i].data != null) {
				System.out.println("[" + i + "] = " + nodesToUpdate[i].data);
			} else {
				System.out.println("[" + i + "] = NULL");
			}
		}
	}

	private void adjustHead(int newLevel) {
		Node<AnyType> currentNode = head;
		head = new Node<AnyType>(newLevel, null);
		for (int i = 0; i <= maxListLevel; i++)
			head.next[i] = currentNode.next[i];
		maxListLevel = newLevel;
	}

	/**
	 * Deletes a node that contains the given value. In order to delete a node we
	 * must maintain an array of nodes whose next references must be updated.
	 */
	public void delete(AnyType toDelete) {
		Node<AnyType>[] nodesToUpdate = new Node[maxListLevel + 1];
		Node<AnyType> currentNode = head;
		int level = maxListLevel;
		
		// Go through each level starting with the highest
		for (int i = maxListLevel; i >= 0; i--) {
			System.out.println("Pointer at level: " + i);
			// Move forward to the next node
			while ((currentNode.next[i] != null) 
					&& (((IntegerComparable) currentNode.next[i].data).compareTo(toDelete) < 0)) {
				currentNode = currentNode.next[i];
			}
			nodesToUpdate[i] = currentNode;
		}
		
		currentNode = currentNode.next[0];
		
		if ((currentNode != null) && (((IntegerComparable) currentNode.data).compareTo(toDelete) == 0)) {
			
			for(int i=0; i <= maxListLevel; i++) { 
	            if(nodesToUpdate[i].next[i] != currentNode) 
	               break; 
	  
	            nodesToUpdate[i].next[i] = currentNode.next[i]; 
	        } 
	  
	        // Remove levels having no elements  
	        while(maxListLevel>0 && 
	              head.next[maxListLevel] == null) 
	        	maxListLevel--;
			
			System.out.println("Successfully deleted :" + toDelete.getCompareValue());
		}
		else {
			System.out.println("Could not find a match!!!");
		}
	}

	private static class Node<AnyType> {
		public AnyType data;
		public Node[] next;

		public Node(int randLevel, AnyType data) {
			next = new Node[randLevel + 1];
			this.data = data;
		}
	}

	public void printAll() {
		for (int i = 0; i <= maxListLevel; i++) {
			System.out.println("Level " + i + ": " + toString(i));
		}
	}
}
