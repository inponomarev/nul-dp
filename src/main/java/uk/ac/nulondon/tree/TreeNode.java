package uk.ac.nulondon.tree;

import java.util.function.Consumer;

public class TreeNode {
    final int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public void insert(TreeNode child) {
        if (this.value > child.value) {
            if (this.left == null) {
                this.left = child;
            } else {
                this.left.insert(child);
            }
        } else if (this.value < child.value) {
            if (this.right == null) {
                this.right = child;
            } else {
                this.right.insert(child);
            }
        }
    }

    public void visit(Consumer<TreeNode> consumer) {
        consumer.accept(this);
        if (left != null) {
            left.visit(consumer);
        }
        if (right != null) {
            right.visit(consumer);
        }
    }

    public Integer floor(int i) {
        if (i == value)
            return i;
        //value cannot be "floor" for i
        if (value > i) {
            if (left == null) {
                return null;
            } else {
                return left.floor(i);
            }
        } else {
            if (right == null) {
                return value;
            } else {
                Integer rightFloor = right.floor(i);
                if (rightFloor == null) {
                    return value;
                } else {
                    return rightFloor;
                }
            }
        }
    }

    public Integer ceilinig(int i) {
        if (i == value)
            return i;
        if (value > i) {
            if (left == null) {

                return value;
            } else {
                Integer leftCeiling = left.ceilinig(i);
                if (leftCeiling == null) {
                    return value;
                } else return leftCeiling;
            }
        } else {
            if (right == null) {
                return null;
            } else {
                return right.ceilinig(i);
            }
        }
    }
}
