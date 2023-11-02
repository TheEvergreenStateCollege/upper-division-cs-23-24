/*public class BST
{
	public static void main(String args[])
	{
	
	Node root;
	
	
	public void addNode(int key, String name)
	{
		Node newNode = new Node(key, name);
		
		if(root == null) 
		{
			root = newNode;
		}
		else
		{
			Node focusNode = root;
			Node parent;
			
			while(true)
			{
				if(key < focusNode.key)
				{
					focusNode = focusNode.leftChild;
					
					if(focusNode == null)
					{
						parent.leftChild = newNode;
					}
					return;
				}
				else
				{
					focusNode = focusNode.rightChild;
					
					if (focusNode == null)
					{
						parent.rightChild = newNode;
					}
					return;
				}
}

 */