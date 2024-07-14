package by.krainet.timetracker.repository;

import by.krainet.timetracker.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByTaskId(Long taskId);
    Optional<Record> findByIdAndTaskId(Long id, Long taskId);
}
