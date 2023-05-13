package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class DZ5 {

    public static void Quicksort(int[] array) {
        Quicksort(array, 0, array.length - 1);
    }

    public static void Quicksort(int[] array, int start, int end) {
        if (start >= end) return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur]))
                i++;
            while (j > cur && (array[cur] <= array[j]))
                j--;
            if (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        Quicksort(array, start, cur);
        Quicksort(array, cur + 1, end);
    }

    public static int BinarySearch(int[] array, int key) {
        return BinarySearch(array, key, 0, array.length - 1);
    }

    public static int BinarySearch(int[] array, int key, int low, int high) {
        while (low <= high) {
            int mid = low + (low + high) / 2;
            if (array[mid] < key)
                low = mid + 1;
            else if (array[mid] > key)
                high = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public static class Node {
        public int sum = 0;
        Node left;
        Node right;
        Integer val;

        public Node(int val) {
            this.val = val;
        }

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        static ArrayList<Object[]> stack = new ArrayList<>();

        public void add(int i) {
            Node newNode = new Node(i);
            Node currentNode = this;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (i == currentNode.val)
                    return;
                else if (i < currentNode.val) {
                    currentNode = currentNode.left;
                    if (currentNode == null) {
                        parentNode.left = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.right;
                    if (currentNode == null) {
                        parentNode.right = newNode;
                        return;
                    }
                }
            }
        }

        public void breadthFirstTraversal() {
            Stack<Node> nodes = new Stack<>();
            nodes.push(this);
            int l1 = 0;
            int r1 = 0;
            stack.add(new Object[]{0, this});
            while (!nodes.isEmpty()) {
                Node node = nodes.pop();
                sum += node.val;
                if (node.left != null) {
                    nodes.push(node.left);
                    ++l1;
                    stack.add(new Object[]{l1, node.left});
                }
                if (node.right != null) {
                    nodes.push(node.right);
                    ++r1;
                    stack.add(new Object[]{r1, node.right});
                }
            }
        }

        void print() {
            int temp = 0;
            int numberOfChars = 0;
            String s = this.val + "\n";
            int level = 0;
            for (Object[] arr : stack) {
                if (temp != (int) arr[0]) {
                    temp = (int) arr[0];
                    if (numberOfChars > s.length()) {
                        numberOfChars = s.length();
                        level = temp;
                    }
                    s = "\n";
                } else {
                    s += ((((Node) arr[1]).left != null) ? ((Node) arr[1]).left.val : "") + " " + ((((Node) arr[1]).right != null) ? ((Node) arr[1]).right.val : "") + "  ";
                    continue;
                }
                s += ((((Node) arr[1]).left != null) ? ((Node) arr[1]).left.val : "") + " " + ((((Node) arr[1]).right != null) ? ((Node) arr[1]).right.val : "") + "  ";
            }
            temp = 0;
            s = "";

            String spaces = "";

            for (int i = 0; i < numberOfChars; i++)
                spaces += " ";

            for (int i = level; i < stack.size(); i++) {
                Object[] objects = stack.get(i);
                if (temp != (int) objects[0]) {
                    temp = (int) objects[0];
                    for (int j = 0; j < s.length(); j++)
                        spaces += " ";
                    s = spaces.substring(spaces.length() - 2);
                } else {
                    s += ((((Node) objects[1]).left != null) ? ((Node) objects[1]).left.val : "") + " " + ((((Node) objects[1]).right != null) ? ((Node) objects[1]).right.val : "") + "  ";
                    continue;
                }
                s += ((((Node) objects[1]).left != null) ? ((Node) objects[1]).left.val : "") + " " + ((((Node) objects[1]).right != null) ? ((Node) objects[1]).right.val : "") + "  ";
            }

            temp = 0;
            s = "";

            int tempNum = 0;
            int temp_ = 0;

            for (int i = 0; i < (spaces.length() - (tempNum += 2)) / 3.8; i++) {
                s += " ";
            }

            s += this.val + "\n";

            for (int i = 0; i < (spaces.length() - (tempNum += 2)) / 2; i++) {
                s += " ";
            }

            for (Object[] arr : stack) {
                if (temp != (int) arr[0]) {
                    temp = (int) arr[0];
                    temp_ = 0;
                    s += "\n";
                    for (int i = 0; i < (spaces.length() - (tempNum += 2)); i++)
                        s += " ";
                } else {
                    s += ((((Node) arr[1]).left != null) ? ((Node) arr[1]).left.val : "") + " " + ((((Node) arr[1]).right != null) ? ((Node) arr[1]).right.val : "");
                    temp_ += 1;
                    for (int i = 0; i < temp_; i++)
                        s += "   ";
                    continue;
                }
                s += ((((Node) arr[1]).left != null) ? ((Node) arr[1]).left.val : "") + " " + ((((Node) arr[1]).right != null) ? ((Node) arr[1]).right.val : "");
                temp_ += 1;
                for (int i = 0; i < temp_; i++)
                    s += "   ";
            }

            System.out.println(s);
        }
    }

}