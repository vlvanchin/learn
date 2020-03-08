package singasug.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookController {

 @Autowired
 BookRepository repository;

 @RequestMapping("/b/{id}")
 Book getById(@PathVariable long id) {
     return repository.findOne(id);
 }
}