package com.library_stock.library_stock.bookInstance;

import com.library_stock.library_stock.bookInstance.types.PreservationState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface BookInstanceRepository extends JpaRepository<BookInstance, Integer> {
    Optional<BookInstance> findByInternalCode(String internalCode);

    List<BookInstance> findByBookId(int bookId);

    Optional<BookInstance> findById(int id);

    List<BookInstance> findByPreservationState(PreservationState preservationState);

    @Query("SELECT MAX(CAST(l.position AS int)) FROM BookInstance bi " +
            "JOIN bi.location l " +
            "WHERE bi.book.id = :bookId")
    Integer findMaxPositionByBookId(int bookId);
}
