package com.example.optishift.service;

import com.example.optishift.dto.TaskCreateRequest;
import com.example.optishift.dto.TaskResponse;
import com.example.optishift.dto.TaskUpdateRequest;
import com.example.optishift.entity.Project;
import com.example.optishift.entity.Shift;
import com.example.optishift.entity.Task;
import com.example.optishift.mapper.TaskMapper;
import com.example.optishift.repository.ProjectRepository;
import com.example.optishift.repository.ShiftRepository;
import com.example.optishift.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ShiftRepository shiftRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = taskMapper.toEntity(request);
        
        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found"));
            task.setProject(project);
        }
        
        if (request.getShiftId() != null) {
            Shift shift = shiftRepository.findById(request.getShiftId())
                    .orElseThrow(() -> new EntityNotFoundException("Shift not found"));
            task.setShift(shift);
        }
        
        Task savedTask = taskRepository.save(task);
        return taskMapper.toResponse(savedTask);
    }
    
    @Transactional
    public TaskResponse updateTask(Long id, TaskUpdateRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        
        taskMapper.updateEntityFromRequest(request, task);
        
        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new EntityNotFoundException("Project not found"));
            task.setProject(project);
        }
        
        if (request.getShiftId() != null) {
            Shift shift = shiftRepository.findById(request.getShiftId())
                    .orElseThrow(() -> new EntityNotFoundException("Shift not found"));
            task.setShift(shift);
        }
        
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toResponse(updatedTask);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
        return taskMapper.toResponse(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
