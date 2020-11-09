package ee.bcs.valiit.tasks.skynet;

import java.util.ArrayList;
import java.util.List;

//public static class Node {
public class Node {

    private int nodeID;
    private boolean isGW;
    private List<Integer> cNodes = new ArrayList<>();

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public boolean isGW() {
        return isGW;
    }

    public void setGW(boolean GW) {
        isGW = GW;
    }

    public List<Integer> getcNodes() {
        return cNodes;
    }

    public void setcNodes(List<Integer> cNodes) {
        this.cNodes = cNodes;
    }

    public Node(int id, int aNode ) {
        this.cNodes.add(aNode);
        this.nodeID = id;
    }
}
