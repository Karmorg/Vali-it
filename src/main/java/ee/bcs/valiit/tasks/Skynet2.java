package ee.bcs.valiit.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Skynet2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        List<Node> nodeList = new ArrayList<>();
        int node1Index;
        int node2Index;
        int cNode1Index;
        int cNode2Index;

        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();

            node1Index = 0;
            node2Index = 0;

            //Käib läbi punktide nimekirja ja vaatab, kas seal on olemas N1 ja N2
            for (int j = 0; j < nodeList.size(); j++) {
                if (nodeList.get(j).getNodeID() == N1) {
                    node1Index = j;
                }
                if (nodeList.get(j).getNodeID() == N2) {
                    node2Index = j;
                }
            }

            //Kui N1 ei olnud listis, siis lisab nimekirja.
            if (node1Index != 0) {
                Node node = new Node(N1, N2);
                nodeList.add(node);
            } else {             //Kui oli nimekirjas, siis vaatab, kas N2 on ühendatud täppide nimekirjas
                cNode1Index = 0;
                for (int k = 0; k < nodeList.get(node1Index).getcNodes().size(); k++) {
                    if (nodeList.get(node1Index).getcNodes().get(k) == N2) {
                        cNode1Index = k;
                    }
                }
                if (cNode1Index != 0) {
                    nodeList.get(node1Index).getcNodes().add(N2);
                }
            }

            //Kui N2 ei olnud listis, siis lisab nimekirja.
            if (node2Index != 0) {
                Node node = new Node(N2, N1);
                nodeList.add(node);
            } else {            //Kui oli nimekirjas, siis vaatab, kas N2 on ühendatud täppide nimekirjas
                cNode2Index = 0;
                for (int k = 0; k < nodeList.get(node2Index).getcNodes().size(); k++) {
                    if (nodeList.get(node2Index).getcNodes().get(k) == N1) {
                        cNode2Index = k;
                    }
                }
                if (cNode2Index != 0) {
                    nodeList.get(node2Index).getcNodes().add(N1);
                }
            }
        }


        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            for (Node n : nodeList) {
                if (n.getNodeID() == in.nextInt()) {
                    n.setGW(true);
                }
            }
        }

        int delete1=0;
        int delete2=0;
        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            for (Node n : nodeList) {
                if (n.getNodeID() == in.nextInt()) {
                    for (int cN : n.getcNodes()){
                        for (Node n2 : nodeList){
                            if (n2.isGW()){
                                n.getcNodes().remove(cN);
                                delete1 = cN;
                                delete2 = n.getNodeID();
                            }
                        }
                    }
                }
            }

            for (Node n : nodeList) {
                if (n.getNodeID() == delete1) {
                    for (int cN : n.getcNodes()){
                        if (cN == delete2){
                            n.getcNodes().remove(cN);
                        }
                    }
                }
            }

            // ja nüüd veel vaja paarilises nodes ära kustutada link

            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            System.out.println("0 1");
        }
    }
}
