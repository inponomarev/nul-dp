package uk.ac.nulondon.tree;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class BinarySearchTree {

    TreeNode root;


    public void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
        } else {
            root.insert(newNode);
        }
    }


    public void visit(Consumer<TreeNode> consumer) {
        root.visit(consumer);
    }


    public static void main(String[] args) throws IOException, URISyntaxException {

        BinarySearchTree bst = new BinarySearchTree();
        for (int i = 0; i < 12; i++) {
            bst.insert(ThreadLocalRandom.current().nextInt(100));
        }

        bst.visit(treeNode -> {
            System.out.println(treeNode.value);
        });

        visualize(bst);
        System.out.println(bst.floor(50));
        System.out.println(bst.ceiling(50));
    }

    public Integer floor(int i) {
        return root.floor(i);
    }

    public Integer ceiling(int i) {
        return root.ceilinig(i);
    }

    private static void visualize(BinarySearchTree bst) throws IOException, URISyntaxException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("digraph G {");
        pw.println("  ordering=\"out\"");
        pw.println("  node[shape=box,style=rounded]");
        bst.visit(treeNode -> {
            pw.printf("  N%d[label=\"%d\"]%n", treeNode.value, treeNode.value);
            if (treeNode.left != null) {
                pw.printf("  N%d->N%d%n", treeNode.value, treeNode.left.value);
            } else {
                pw.printf("  N%dL[style=invis]%n", treeNode.value);
                pw.printf("  N%d->N%dL[%s]%n", treeNode.value, treeNode.value,
                        treeNode.right == null ? "style=invis" : "");
            }
            if (treeNode.right != null) {
                pw.printf("  N%d->N%d%n", treeNode.value, treeNode.right.value);
            } else {
                pw.printf("  N%dR[style=invis]%n", treeNode.value);
                pw.printf("  N%d->N%dR[%s]%n", treeNode.value, treeNode.value,
                        treeNode.left == null ? "style=invis" : "");
            }
        });
        pw.println("}");
        String dot = sw.toString();
        show(dot);
    }

    private static void show(String dot) throws IOException, URISyntaxException {
        String encoded = URLEncoder.encode(dot, "UTF8")
                .replaceAll("\\+", "%20");
        Desktop.getDesktop().browse(
                new URI("https://dreampuf.github.io/GraphvizOnline/#"
                        + encoded));
    }
}
