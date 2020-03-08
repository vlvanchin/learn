package singasug.data;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
class BookValidationController {

    @RequestMapping("/b/validationOff")
    String validationOff(@RequestBody Book book) {
        return "OK - " + book;
    }

    @RequestMapping("/b/validationOn")
    String validationOn(@Valid @RequestBody Book book) {
        return "OK - " + book;
    }

}