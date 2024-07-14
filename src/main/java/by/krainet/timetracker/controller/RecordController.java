package by.krainet.timetracker.controller;

import by.krainet.timetracker.model.Record;
import by.krainet.timetracker.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}/records")
public class RecordController {
    private final RecordService recordService;

    @GetMapping
    public List<Record> getAllRecordsByTaskId(@PathVariable Long projectId, @PathVariable Long taskId) {
        return recordService.getRecordsByTaskId(projectId, taskId);
    }

    @GetMapping("/{recordId}")
    public Record getRecordByIdAndTaskId(@PathVariable Long projectId, @PathVariable Long taskId,
                                         @PathVariable Long recordId) {
        return recordService.getRecordByIdAndTaskId(projectId, recordId, taskId);
    }

    @PostMapping
    public Record addRecord(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Record record) {
        return recordService.addRecord(projectId, taskId, record);
    }

    @PutMapping("/{recordId}")
    public Record updateRecord(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long recordId,
                               @RequestBody Record recordDetails) {
        return recordService.updateRecord(projectId, taskId, recordId, recordDetails);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long projectId, @PathVariable Long taskId,
                                             @PathVariable Long recordId) {
        recordService.deleteRecord(projectId, taskId, recordId);
        return ResponseEntity.noContent().build();
    }
}
