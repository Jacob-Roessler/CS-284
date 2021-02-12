import java.util.ArrayList;

/**
 * BSFamilyTree creates a tree for specific families. 
 */
public class BSFamilyTree {
    //Data Fields
	private FamilyTreeNode root;
	
    /**
     * Constructor: constructs an empty BSFamilyTree
     */
    public BSFamilyTree() {
    	root = null;
    }

    /**
     * takes in the last name and returns true if there
     * is a FamilyTreeNode with the given last name.
     */
    public boolean doesFamilyExist(String lastName) {
        FamilyTreeNode current = root;
        while(current!=null && !current.getLastName().equals(lastName)) {
        	if(current.getLastName().compareTo(lastName) < 0) {
        		current = current.right;
        	}
        	else
        		current = current.left;
        }
        return current != null && current.getLastName().equals(lastName);
    }

    /**
     * Takes in a last name and creates a new instance of
     * FamilyTreeNode and adds it to the BSFamilyTree.
     */
    public void addFamilyTreeNode(String lastName) {
        if(root == null) {
        	root = new FamilyTreeNode(lastName);
        }
        else if(doesFamilyExist(lastName)) {
        	throw new IllegalArgumentException("Family already exists!");
        }
        else {
        	FamilyTreeNode current = root;
        	FamilyTreeNode parent = null;
        	FamilyTreeNode newNode = new FamilyTreeNode(lastName);
        	boolean toInsert = true;
        	while(toInsert) {
        		parent = current;
        		int c = lastName.compareTo(parent.getLastName());
        		
        		if(c < 0) {
        			current = current.left;
        			
        			if(current == null) {
        				parent.left = newNode;
        				toInsert = false;
        			}
        		}
        		else {
        			current = current.right;
        			
        			if(current == null) {
        				parent.right = newNode;
        				toInsert = false;
        			}
        		}
        	}
        }
    	return;
    }

    /**
     * Takes a last name and then finds that specific
     * family tree and then returns that FamilyTreeNode
     * If last name is not in tree, throws an exception.
     */
    public FamilyTreeNode getFamilyTreeNode(String lastName) {
    	if(!doesFamilyExist(lastName))
    		throw new IllegalArgumentException("Family does not exist!");
    	
    	FamilyTreeNode current = root;
        while(current!=null && !current.getLastName().equals(lastName)) {
        	if(current.getLastName().compareTo(lastName) > 0) {
        		current = current.left;
        	}
        	else
        		current = current.right;
        }
        return current;
    }

    /**
     * Returns true if the input phone number exists in the BSFamilyTree
     * false otherwise.
     */
    public boolean doesNumberExist(String phoneNumber) {
    	FamilyTreeNode current = root;
        return doesNumberExistHelper(current, phoneNumber);
        
    }
    
    public boolean doesNumberExistHelper(FamilyTreeNode current,String phoneNumber) {
    	if(current == null)
        	return false;
        else {
        	if(current.doesNumberExist(phoneNumber))
        		return true;
        	else {
        		return doesNumberExistHelper(current.left, phoneNumber) || doesNumberExistHelper(current.right, phoneNumber);
        	}
        }
    	
    }
    /**
     * Returns the string representation of the BSFamilyTree
     */
    public StringBuilder toString(FamilyTreeNode current, int i) {
    	StringBuilder r = new StringBuilder() ;
    	for (int j=0; j<i; j++){
    		r.append("  ");
    	}
    	if (current==null){
    		r.append("null\n");
    	} else {
    		r.append(current.toString()+"\n");
    		r.append(toString(current.left,i+1));
    		r.append(toString(current.right,i+1));
    	}
    	return r;
    }
    public String toString() {
    	return toString(root, 0).toString();
    }
    
}
