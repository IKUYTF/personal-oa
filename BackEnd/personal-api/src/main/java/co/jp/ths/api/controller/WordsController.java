package co.jp.ths.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jp.ths.api.entity.Words;
import co.jp.ths.api.service.WordsService;

/**
 * 
 * @author iku 2019/05/01
 *
 */
@RestController
@RequestMapping("/words")
public class WordsController {

	@Autowired
	private WordsService wordsTblService;


	@GetMapping(value = "/words-get/{keyword}")
	public List<Words> getWordsByKeyword(@PathVariable String keyword) {
		String keyword1 = "%" + keyword + "%";

		return wordsTblService.getWordsByKeyword(keyword1);
	}


	@GetMapping(value = "/words-getAll")
	public List<Words> getAllWordss() {

		return wordsTblService.getAllWords();
	}

	@PostMapping(value = "/words-insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Words> addWords(@RequestBody Words tbl) {

		wordsTblService.addWords(tbl);
		
		return getAllWordss();
	}

	@PostMapping(value = "/words-update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Words> updateWords(@RequestBody Words tbl) {
		
		wordsTblService.editWords(tbl);
		
		return getAllWordss();
	}

}
