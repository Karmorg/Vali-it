package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class exsController {

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
}
