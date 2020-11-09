package ee.bcs.valiit.tasks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RandomGame {

    int bingo;
    int noOfGuess = 0;

    @GetMapping("randomGame")
    public String randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        bingo = random.nextInt(100);

        return "Arvuti genereeris arvu nullist sajani selle ära arvamiseks sisesta üks arv aadressi ribas peale kaldkriipsu.";

    }
    @GetMapping("randomGame/{no}")
    public String randomGame2 (@PathVariable("no") int no){

        if (no == bingo){
            return "Arvasid õigesti peale " + noOfGuess + " katset.";
        }
        if (no < bingo) {
            noOfGuess ++;
            return "Sisestatud arv on väiksem arvuti genereeritust. Katsete arv: " + noOfGuess;
        } else {
            noOfGuess ++;
            return "Sisestatud arv on suurem arvuti genereeritust. Katsete arv: " + noOfGuess;
        }

    }
}
