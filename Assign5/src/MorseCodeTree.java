
import java.util.ArrayList;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code to english
 * @author Eric Deng
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
    TreeNode<String> root;

    /**
     * Constructor - calls the buildTree method
     */
    public MorseCodeTree(){
        setRoot(new TreeNode<>(""));
        buildTree();
    }

    /**
     * Returns a reference to the root
     * @return reference to root
     */
    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    /**
     * sets the root of the MorseCodeTree
     * @param newNode a TreeNode<T> that will be the new root
     */
    @Override
    public void setRoot(TreeNode<String> newNode) {
        root=newNode;
    }

    /**
     * Adds element to the correct position in the tree based on the code
     * This method will call the recursive method addNode
     * @param code the code for the new node to be added
     * @param letter the letter for the corresponding code
     * @return the MorseCodeTree with the new node added
     */
    @Override
    public MorseCodeTree insert(String code, String letter) {

        addNode(root,code,letter);
        return this;
    }

    /**
     * This is a recursive method that adds element to the correct position in the tree based on the code.
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of addNode
     * @param letter the data of the new TreeNode to be added
     */
    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {

        if (code.length() == 1) {

            if (code.equals(".")){
                root.left= new TreeNode(letter);

            }
            else if (code.equals("-")){
                root.right=new TreeNode(letter);

            }
            return;
        }

        else if(code.length()>1){
            if(code.charAt(0)==('.')){
                if(root.left==null)
                    root.left=new TreeNode(letter);
                //root=root.left;
                code=code.substring(1);
                addNode(root.left,code,letter);
            }
            else if(code.charAt(0)==('-')){
                if(root.right==null)
                    root.right=new TreeNode(letter);
                //root=root.right;
                code=code.substring(1);
                addNode(root.right,code,letter);
            }
        }


    }

    /**
     * Fetch the data in the tree based on the code This method will call the recursive method fetchNode
     * @param code the code that describes the traversals within the tree
     * @return the string (letter) that corresponds to the code
     */
    @Override
    public String fetch(String code) {

        return fetchNode(root,code);
    }

    /**
     * This is the recursive method that fetches the data of the TreeNode that corresponds with the code
     * @param root the root of the tree for this particular recursive instance of fetchNode
     * @param code the code for this particular recursive instance of fetchNode
     * @return the string (letter) corresponding to the code
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code) {

        if (root == null) {

            return "xD";
        }

        if (code.length() == 1) {

            if (code.equals(".")){
                root=root.left;

            }
            else if (code.equals("-")){
                root=root.right;

            }
            return root.data;
        }

        else if(code.length()>1){
            if(code.charAt(0)==('.')){
                root=root.left;
                code=code.substring(1);
                //fetchNode(root,code);
            }
            else if(code.charAt(0)==('-')){
                root=root.right;
                code=code.substring(1);
                //fetchNode(root,code);
            }


        }

        return fetchNode(root,code);
    }

    /**
     * This operation is not supported in the MorseCodeTree
     * @param data data of node to be deleted
     * @return reference to the current tree
     * @throws UnsupportedOperationException
     */
    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
        return this;
    }

    /**
     * This operation is not supported in the MorseCodeTree
     * @return reference to the current tree
     * @throws UnsupportedOperationException
     */
    @Override
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
        return this;
    }

    /**
     * This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code.
     */
    @Override
    public void buildTree() {
        root=new TreeNode<>("");
        //level one
        insert(".","e");insert("-", "t");
        //level two
        insert("..", "i"); insert(".-", "a");
        insert("-.", "n"); insert("--", "m");
        //level three
        insert("...","s"); insert("..-","u"); insert(".-.","r");
        insert(".--","w"); insert("-..","d"); insert("-.-","k");
        insert("--.","g"); insert("---","o");
        //level four
        insert("....","h"); insert("...-","v"); insert("..-.","f");
        insert(".-..","l"); insert(".--.","p"); insert(".---","j");
        insert("-...","b"); insert("-..-","x"); insert("-.-.","c");
        insert("-.--","y"); insert("--..","z"); insert("--.-","q");
    }

    /**
     * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order
     * Used for testing to make sure tree is built correctly
     * @return an ArrayList of the items in the linked Tree
     */
    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> list= new ArrayList();
        LNRoutputTraversal(root,list);


        return list;
    }

    /**
     * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
     * @param root the root of the tree for this particular recursive instance
     * @param list the ArrayList that will hold the contents of the tree in LNR order
     */
    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
                if(root==null){
                    return;
                }

                LNRoutputTraversal(root.left,list);
                list.add(root.data);
                LNRoutputTraversal(root.right,list);
    }
}


