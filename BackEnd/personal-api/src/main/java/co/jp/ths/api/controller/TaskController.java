package co.jp.ths.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jp.ths.api.entity.Task;
import co.jp.ths.api.service.TaskService;

/**
 * 
 * @author iku 2019/05/01
 *
 */
@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskTblService;


	@GetMapping(value = "/task-get/{keyword}")
	public List<Task> getTaskByKeyword(@PathVariable String keyword) {
		String keyword1 = "%" + keyword + "%";

		return taskTblService.getTaskByKeyword(keyword1);

	}

	@GetMapping(value = "/testGetPort")
	public String getPortTest() {

		return "{\"error\":[{\"code\":\"M0000000024\", \"message\":\"testMessage\"}]}";
	}

	@GetMapping(value = "/task-getAll")
	public List<Task> getAllTasks() {

		return taskTblService.getAllTask();
	}
	
	@GetMapping(value = "/task-getUnfinish")
	public List<Task> getUnfinishTasks() {

		return taskTblService.getUnfinish();
	}
	
	@GetMapping(value = "/task-getFinish")
	public List<Task> getFinishTasks() {

		return taskTblService.getFinish();
	}
	
	@GetMapping(value = "/task-getMemo")
	public List<Task> getMemo() {
		
		return taskTblService.getMemo();
	}

	@PostMapping(value = "/task-insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Task> addTask(@RequestBody Task tbl) {

		tbl.setUuid(UUID.randomUUID().toString());

		taskTblService.addTask(tbl);
		
		return getAllTasks();
	}

	@PostMapping(value = "/task-update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Task> updateTask(@RequestBody Task tbl) {
		
		taskTblService.editTask(tbl);
		
		return getAllTasks();
	}

}
