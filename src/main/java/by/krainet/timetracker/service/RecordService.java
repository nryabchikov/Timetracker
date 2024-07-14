package by.krainet.timetracker.service;

import by.krainet.timetracker.exception.ProjectNotFoundException;
import by.krainet.timetracker.exception.RecordNotFoundException;
import by.krainet.timetracker.exception.TaskNotFoundException;
import by.krainet.timetracker.model.Record;
import by.krainet.timetracker.model.Task;
import by.krainet.timetracker.repository.ProjectRepository;
import by.krainet.timetracker.repository.RecordRepository;
import by.krainet.timetracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public List<Record> getRecordsByTaskId(Long projectId, Long taskId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        return recordRepository.findByTaskId(taskId);
    }

    public Record getRecordByIdAndTaskId(Long projectId, Long recordId, Long taskId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        return recordRepository.findByIdAndTaskId(recordId, taskId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
    }

    public Record addRecord(Long projectId, Long taskId, Record record) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        record.setTask(task);
        return recordRepository.save(record);
    }

    public Record updateRecord(Long projectId, Long taskId, Long recordId, Record recordDetails) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        Record record = recordRepository.findByIdAndTaskId(recordId, taskId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));

        record.setUser(recordDetails.getUser());
        record.setStartTime(recordDetails.getStartTime());
        record.setEndTime(recordDetails.getEndTime());
        record.setDescription(recordDetails.getDescription());

        return recordRepository.save(record);
    }

    public void deleteRecord(Long projectId, Long taskId, Long recordId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        Record record = recordRepository.findByIdAndTaskId(recordId, taskId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
        recordRepository.delete(record);
    }
}
