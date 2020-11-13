package ee.bcs.valiit.tasks.exeption;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    //See on minu enda erand, kus tahan, et tuleks staatus 400
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationExeption(ApplicationException e){
        //järgmine rida läheb logisse
        System.out.println("juhtus viga tulenevalt sisestatud väärtusest");
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    //See on kõigi muude tekkida võivate vigade jaoks
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Server error"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
