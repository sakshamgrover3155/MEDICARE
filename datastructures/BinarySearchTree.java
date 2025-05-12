// datastructures/BinarySearchTree.java
package datastructures;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinarySearchTree<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }

        if (comparator.compare(data, node.data) < 0) {
            node.left = insertRecursive(node.left, data);
        } else if (comparator.compare(data, node.data) > 0) {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

    public boolean search(T data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(Node<T> node, T data) {
        if (node == null) {
            return false;
        }

        if (comparator.compare(data, node.data) == 0) {
            return true;
        }

        if (comparator.compare(data, node.data) < 0) {
            return searchRecursive(node.left, data);
        } else {
            return searchRecursive(node.right, data);
        }
    }

    public void inOrderTraversal(Consumer<T> action) {
        inOrderTraversalRecursive(root, action);
    }

    private void inOrderTraversalRecursive(Node<T> node, Consumer<T> action) {
        if (node != null) {
            inOrderTraversalRecursive(node.left, action);
            action.accept(node.data);
            inOrderTraversalRecursive(node.right, action);
        }
    }
}