package singasug.data;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookConfig {

    @Bean ApplicationRunner init(BookRepository repository) {
        // init db
        return args -> {
            repository.save(new Book("Java tutorial", 1995));
            repository.save(new Book("Spring reference", 2016));
        };
    }
}