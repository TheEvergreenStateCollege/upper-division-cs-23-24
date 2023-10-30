package dev.codewithfriends;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BSTMain {
    
    public static boolean isVisited(BinaryNode<Integer> node, List<Integer> visited) {
        for (int i=0; i < visited.size(); i++) {
            if (node.data.equals(visited.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> dfs(BinaryNode<Integer> node, List<Integer> visited) {

        // mark the current node as visited
        visited.add(node.data);

        // if there is a left, or left unvisited
        if (node.left != null && isVisited(node.left,visited) == false) {
            dfs(node.left, visited);
        }
        // if there is a right, or right unvisited
        if (node.right != null && isVisited(node.right,visited) == false) {
            dfs(node.right, visited);
        }
        // then return
        return visited;
        // update
    }
    /**
     * @param node
     * @param diagramSofar
     * @return
     */
    public static List<String> dfsDiagram(BinaryNode<Integer> node, List<String> diagramSofar) {

        // mark the current node as visited

        // if there is a left, or left unvisited
        if (node.left != null) {
            diagramSofar.add(String.format("    %s --> %s", node.data, node.left.data));
            dfsDiagram(node.left, diagramSofar);
        }
        // if there is a right, or right unvisited
        if (node.right != null) {
            diagramSofar.add(String.format("    %s --> %s", node.data, node.right.data));
            dfsDiagram(node.right, diagramSofar);
        }
        // then return
        return diagramSofar;
        // update
    }

    public static Map<String,String> loadNames(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map<String,String> nameMeanings = (Map<String, String>) ois.readObject();
            return nameMeanings;
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        }
        return null;
  }

  public static void main(String[] args) 
    {
        LinkedList<Integer> visited = new LinkedList<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(73);
        bst.insert(22);
        List<Integer> result = dfs(bst.getRoot(), visited);

        List<String> diagramLines = dfsDiagram(bst.getRoot(), new LinkedList<>());
        for (String d : diagramLines) {
            System.out.println(d);
        }

        Map<String,String> names = loadNames(args[0]);
        
    }
}
