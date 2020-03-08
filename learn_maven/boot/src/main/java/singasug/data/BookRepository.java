package singasug.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//spring data implements this interface for us
//@Repository
//interface BookRepository extends JpaRepository<Book, Long> {}

//@Repository
//@RepositoryRestResource // <-- add this
//interface BookRepository extends JpaRepository<Book, Long> {}

@Repository
@RepositoryRestResource
interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(@Param("title") String title); // <- add this

    List<Book> findByYearLessThan(@Param("year") int year); // <- add this
}