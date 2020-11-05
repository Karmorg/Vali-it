package ee.bcs.valiit.tasks;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int nodeID;

    public int getNodeID() {
        return nodeID;
    }

    private boolean isGW;

    public boolean isGW() {
        return isGW;
    }

    public void setGW(boolean GW) {
        isGW = GW;
    }

    public List<Integer> getcNodes() {
        return cNodes;
    }

    public void setcNodes(Integer aNode) {
        this.cNodes.add(aNode);
    }

    private List<Integer> cNodes = new ArrayList<>();

    public Node(int id, int aNode ) {
        this.cNodes.add(aNode);
        this.nodeID = id;
    }
}
