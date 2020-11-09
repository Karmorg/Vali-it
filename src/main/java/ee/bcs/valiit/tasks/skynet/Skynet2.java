package ee.bcs.valiit.tasks.skynet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Skynet2 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        System.err.println("Täppe kokku: " + N);
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
            System.err.println("Täpike 1: " + N1);
            System.err.println("Täpike 2: " + N2);
            node1Index = -1;
            node2Index = -1;

            //Käib läbi punktide nimekirja ja vaatab, kas seal on olemas N1 ja N2
            for (int j = 0; j < nodeList.size(); j++) {
                if (nodeList.get(j).getNodeID() == N1) {
                    node1Index = j;
                    System.err.println("Olemas1: " + N);
                }
                if (nodeList.get(j).getNodeID() == N2) {
                    node2Index = j;
                    System.err.println("Olemas2: " + N);
                }
            }

            //Kui N1 ei olnud listis, siis lisab nimekirja.
            if (node1Index == -1) {
                Node node = new Node(N1, N2);
                nodeList.add(node);
                System.err.println("Loodud täpike: " + N1);

            } else {             //Kui oli nimekirjas, siis vaatab, kas N2 on ühendatud täppide nimekirjas
                cNode1Index = -1;
                for (int k = 0; k < nodeList.get(node1Index).getcNodes().size(); k++) {
                    if (nodeList.get(node1Index).getcNodes().get(k) == N2) {
                        cNode1Index = k;
                    }
                }
                if (cNode1Index == -1) {
                    nodeList.get(node1Index).getcNodes().add(N2);
                }
            }

            //Kui N2 ei olnud listis, siis lisab nimekirja.
            if (node2Index == -1) {
                Node node = new Node(N2, N1);
                nodeList.add(node);
                System.err.println("Loodud täpike: " + N2);
            } else {            //Kui oli nimekirjas, siis vaatab, kas N2 on ühendatud täppide nimekirjas
                cNode2Index = -1;
                for (int k = 0; k < nodeList.get(node2Index).getcNodes().size(); k++) {
                    if (nodeList.get(node2Index).getcNodes().get(k) == N1) {
                        cNode2Index = k;
                    }
                }
                if (cNode2Index == -1) {
                    nodeList.get(node2Index).getcNodes().add(N1);
                }
            }
        }
        System.err.println("Täppe kokku:" +  nodeList);

        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node

            for (Node n : nodeList) {
                System.err.println(n.getNodeID() + " on täpi ID");
                if (n.getNodeID() == EI) {
                    n.setGW(true);
                    System.err.println("GW on: " + n.getNodeID());
                }
            }
        }
        int delete1=-1;
        int delete2=-1;

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            System.err.println("Agent: " + SI);
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            for (Node n : nodeList) {
                if (n.getNodeID() == SI) {
                    int m = 0;
                    for (int cN : n.getcNodes()){
                        for (Node n2 : nodeList){
                            if (n2.getNodeID() == cN && n2.isGW()){
                                System.err.println("ühendatuid täppe: " + n.getcNodes().size());
                                System.err.println("jk nr: " + cN);
                                //n.getcNodes().remove(m);
                                delete1 = cN;
                                delete2 = n.getNodeID();
                            }
                        }
                        m++;
                    }
                }
            }
            if (delete1 == -1){
                delete1 = SI;
                for (Node n : nodeList) {
                    if (n.getNodeID() == delete1) {
                        delete2 = n.getcNodes().get(0);
                        //n.getcNodes().remove(0);
                    }
                }
            }

            //Kustutab paarilise sideme
            for (Node nod : nodeList) {
                if (nod.getNodeID() == delete2) {
                    int o =0;
                    for (int cNod : nod.getcNodes()){
                        if (cNod == delete1){
                            //nod.getcNodes().remove(o);
                        }
                        o++;
                    }
                }
            }
            System.err.println(delete1 + " teine" + delete2);
            //System.out.println(delete1 + " " + delete2);
            System.out.println(delete1 + " " + delete2);
        }
    }
}
