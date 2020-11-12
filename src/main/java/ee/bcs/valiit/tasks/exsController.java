package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.repository2.Account;
import ee.bcs.valiit.tasks.repository2.AccountRepository2;
import ee.bcs.valiit.tasks.skynet.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@RestController
public class exsController {
    List<Node> nodeList = new ArrayList<>();

    //@GetMapping("test") - see teeb ühe lehe juurde rootile

    @GetMapping(value = "/")
    public String getHelloWorld() {
        return "Hello World - Tere, tere, vana kere";
    }

    @GetMapping(value = "Exercises")
    public String getExercises() {
        return "Siit lehelt leiad hulga harjutusi";
    }

    @GetMapping(value = "/fibonacci")
    public int getFibonacci(@RequestParam("elementNr") int nthElement) {
        return Lesson2.fibonacci(nthElement);
    }

    @GetMapping("ex2")
    public List<Integer> exs2(@RequestParam("nr") int nr) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 1; i <= nr; i++) {
            resultList.add(i * 2);
        }
        return resultList;
    }


    @GetMapping("/client/{nimi}/muu/{nr}")  //läheb tööle URL: client/mingitekst?clientID=5
    //mingitekst= "suvalinejutt";
    //pathParam saab ka defineerimata jätta, kui lihtsalt kirjutada muutujad. Läheb vaikimisi sama nimega, nagu meetodis nimetatud

    public String test2(@PathVariable("nimi") String mingiTekst,
                        @PathVariable("nr") int nr,
                        @RequestParam("clientID") Long clientID,
                        @RequestParam(value = "testID", required = false) Integer optional) {
        return mingiTekst + " sisestati aadressiribale kaldkriipsu järgi" + clientID;
    }

    @GetMapping("dto_test")
    public Node getNode() {
        Node n = new Node(7, 3);
        n.setGW(true);
        return n;
    }

    @PostMapping("dto_test")
    public Node get2Node(@RequestBody Node node) {
        Node n = new Node(9, 1);
        n.setGW(true);
        return n;
    }

    @PostMapping("dto_test2")
    public List<Node> get3Node(@RequestBody Node node) {
        List<Node> nodeList = new ArrayList<>();
        Node n = new Node(9, 1);
        n.setGW(true);
        nodeList.add(node);
        nodeList.add(n);
        return nodeList;
    }

    @GetMapping("nodes")
    public List<Node> getNodes() {
        return nodeList;
    }

    @GetMapping("node/{id}")
    public Node getNodeByID(@PathVariable("id") int n) {
        return nodeList.get(n);
    }

    @PutMapping("node/{id}")
    public Node putNodeByID(@PathVariable("id") int n, @RequestBody Node node) {
        nodeList.set(n, node);
        return nodeList.get(n);
    }

    @PostMapping("node")
    public List<Node> postNode(@RequestBody Node node) {
        nodeList.add(node);
        return nodeList;
    }

    @DeleteMapping("node/{id}")
    public List<Node> removeNodeByID(@PathVariable("id") int n) {
        nodeList.remove(n);
        return nodeList;
    }
}
