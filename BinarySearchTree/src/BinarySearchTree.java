/**
 * Created by young on 2017/2/2.
 */
//Binary search tree data structure
public class BinarySearchTree {
    //Node class (inner class)
    public class Node
    {
        private int data;                   //data of node
        private Node left;                  //left subtree of node
        private Node right;                 //right subtree of node

        //Constructor of node
        private Node(int data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    //Root of tree
    public Node root;

    //Constructor of tree
    public BinarySearchTree()
    {
        root = null;
    }

    //Searches data in tree, returns true if data is found and
    //false otherwise

    public boolean search(int data)
    {
        Node temp = root;                   //start at root

        while (temp != null)                //search for data
        {
            //compare data with data in current node
            if (data == temp.data)          //data found
                return true;
            else if (data < temp.data)      //search left subtree
                temp = temp.left;
            else                            //search right subtree
                temp = temp.right;
        }

        return false;                       //data not found
    }


    public boolean searchR(int data, Node node)
    {
        if (node == null)
            return false;

        if (node.data == data)
            return true;
        else if (data < node.data)
            return searchR(data, node.left);
        else
            return searchR(data, node.right);
    }
    //Inserts data into tree, duplicates are not allowed,
    //returns true if insertion succeeds and false otherwise
    public boolean insert(int data)
    {
        if (root == null)                   //tree is empty
        {
            root = new Node(data, null, null);
            return true;                    //insert at root
        }
        else                                //tree is not empty
        {
            Node temp = root;               //start at root
            Node prev = null;               //prev follows temp

            while (temp != null)
            {
                //compare data with data in current node
                if (data == temp.data)      //data found, duplicate not inserted
                    return false;
                else if (data < temp.data)  //insert in left subtree
                {
                    prev = temp;            //move prev and temp
                    temp = temp.left;
                }
                else                        //insert in right subtree
                {
                    prev = temp;            //move prev and temp
                    temp = temp.right;
                }
            }

            if (data < prev.data)           //insert at left
                prev.left = new Node(data, null, null);
            else                            //insert at right
                prev.right = new Node(data, null, null);

            return true;                    //insert succeeds
        }
    }

    //Deletes data from tree, returns true if deletion succeeds and false otherwise
    public boolean delete(int data)
    {
        Node temp = root;                   //start at root
        Node prev = null;                   //prev follows temp

        while (temp != null)                //search for data
        {
            if (data == temp.data)          //data found
                break;
            else if (data < temp.data)      //search in left subtree
            {
                prev = temp;
                temp = temp.left;
            }
            else                            //search in right subtree
            {
                prev = temp;
                temp = temp.right;
            }
        }

        if (temp == null)                   //data not found
            return false;
        else                                //data found
        {
            if (temp.left == null)           //data node has no left subtree
            {
                if (temp == root)
                    root = temp.right;       //right subtree is connected to root
                else if (temp == prev.left)
                    prev.left = temp.right;     //right subtree is connected to left of parent
                else
                    prev.right = temp.right;        //right subtree is connected to right of parent
            }

            else if (temp.right == null)            //data node has no right subtree
            {
                if (temp == root)
                    root = temp.left;               //left subtree is connected to root
                else if (temp == prev.left)
                    prev.left = temp.left;          //left subtree is connected to left of parent
                else
                    prev.right = temp.left;         //left subtree is connected to right of parent
            }

            else                                    //data node has left and right subtrees
            {
                Node p = temp.left;
                Node q = temp;
                while (p.right != null)             //find maximum in left subtree
                {
                    q = p;                          //q follows p
                    p = p.right;                    //move p to the right most corner
                }
                temp.data = p.data;                 //copy maximum to data node
                if (q == temp)                      //and delete maximum node
                    q.left = p.left;
                else
                    q.right = p.left;
            }

            return true;                            //deletion succeeds
        }
    }

    //Prints the tree elements in preoredr
    public void preorder()
    {
        preorder(root);                             //call recursive preorder method
    }

    //Recursive method that prints the tree elements in preorder,
    //node is the root of the tree that is printed in preorder
    private void preorder(Node node)
    {
        if (node != null)
        {
            System.out.println(node.data);          //print the root
            preorder(node.left);                    //print left sub tree in preorder
            preorder(node.right);                   //print right sub tree in preorder
        }
    }

    //Prints the tree elements in inorder
    public void inorder()
    {
        inorder(root);                              //call recursive inorder method
    }

    //Recursive method that prints the tree elements in inorder,
    //node is the root of the tree that is printed in inorder
    private void inorder(Node node)
    {
        if (node != null)
        {
            inorder(node.left);                     //print left sub tree in inorder
            System.out.print(node.data);            //print root
            inorder(node.right);                    //print right sub tree in inorder
        }
    }

    //Counts the nodes of the tree
    public int countNodes()
    {
        return countNodes(root);
    }

    //Recursive method that counts the nodes of the tree
    private int countNodes(Node node)
    {
        if (node == null)                           //empty tree has 0 nodes
            return 0;
        else                                        //1 + nodes in left and right sub trees
            return 1 + countNodes(node.left) + countNodes(node.right);
    }

    //Countes the leaves of the tree
    public int countLeaves()
    {
        return countLeaves(root);
    }

    //Recursive method that counts the leaves of the tree
    private int countLeaves(Node node)
    {
        if (node == null)
            return 0;                               //empty tree has 0 leaves
        else if (node.left == null && node.right == null)
            return 1;                               //single leaf
        else
            return countLeaves(node.left) + countLeaves(node.right);
    }                                               //leaves in left and right sub trees

    //Finds the height of the tree
    public int height()
    {
        return height(root);
    }

    //Recursive method that finds the height of the tree
    private int height(Node node)
    {
        if (node == null)                           //empty tree has height 0
            return 0;
        else
        {
            int leftHeight = height(node.left);     //height of left sub tree
            int rightHeight = height(node.right);   //height of right sub tree
            if (leftHeight <= rightHeight)
                return 1 + rightHeight;             //height is 1 + maximum of
            else                                    //heights of left and right
                return 1 + leftHeight;              //sub trees
        }
    }

    public void printM (Node node)
    {
        if (node != null)
        {
            printM(node.right);
            System.out.println(node.data);
            printM(node.left);
        }
    }
}


