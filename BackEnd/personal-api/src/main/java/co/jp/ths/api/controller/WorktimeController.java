package co.jp.ths.api.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.jp.ths.api.entity.Worktime;
import co.jp.ths.api.entity.WorktimeCsv;
import co.jp.ths.api.service.WorktimeService;

/**
 * 
 * @author iku 2019/06/13
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/worktime")
public class WorktimeController {

	@Autowired
	private WorktimeService WorktimeTblService;

	@GetMapping(value = "/worktime-getAll/{user}/{month}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Worktime> getAllWorktimes(@PathVariable String user, @PathVariable String month) {
		Worktime inputTime = new Worktime();
		inputTime.setDate(month);
		inputTime.setUser(user);

		return WorktimeTblService.getAllWorktime(inputTime);
	}

	@GetMapping(value = "/worktime-exportCsv/{month}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WorktimeCsv> exportCsv(@PathVariable String month) {
		Worktime inputTime = new Worktime();
		inputTime.setDate(month);
		inputTime.setUser("yutf");
		List<Worktime> ikuTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("liqc");
		List<Worktime> riTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("xiasl");
		List<Worktime> kaTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("litao");
		List<Worktime> ritouTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("chixj");
		List<Worktime> chiTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("kachimi");
		List<Worktime> kachimiTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("fukunaga");
		List<Worktime> fukunagaTime = WorktimeTblService.getAllWorktime(inputTime);
		inputTime.setUser("sekiya");
		List<Worktime> sekiyaTime = WorktimeTblService.getAllWorktime(inputTime);
		List<WorktimeCsv> csvDataList = new ArrayList<>();
		int year = Integer.valueOf(month.split("-")[0]);
		int mon = Integer.valueOf(month.split("-")[1]);
		int days = getDaysOfMonth(year, mon);
		int ikuCurDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 1; i <= days; i++) {
			WorktimeCsv csv = new WorktimeCsv();
			csv.setDate(String.valueOf(i));
			csv.setWeekday(getWeek(year, mon-1, i));

			int day = 0;
			if(ikuCurDay != ikuTime.size()) {
				 day = Integer.valueOf(ikuTime.get(ikuCurDay).getDate().split("-")[2]);
			} 
			if(day == i) {
				csv.setIkuDirect(countWorkingTime(ikuTime.get(ikuCurDay).getStartTime(), ikuTime.get(ikuCurDay).getEndTime()));
				csv.setIkuOvertime(countOvertime(ikuTime.get(ikuCurDay).getStartTime(), ikuTime.get(ikuCurDay).getEndTime()));
				countWork += Float.valueOf(csv.getIkuDirect());
				countOver += Float.valueOf(csv.getIkuOvertime());
				ikuCurDay++;		
			} else {
				csv.setIkuDirect("");
				csv.setIkuOvertime("");
			}
			csvDataList.add(csv);
		}
		WorktimeCsv csv = new WorktimeCsv();
		csv.setDate("");
		csv.setWeekday("合計");
		csv.setIkuDirect(String.valueOf(countWork));
		csv.setIkuOvertime(String.valueOf(countOver));
		csvDataList.add(csv);
		
		
		setKaWorktime(csvDataList, kaTime);
		setLiWorktime(csvDataList, riTime);
		setLitouWorktime(csvDataList, ritouTime);
		setChiWorktime(csvDataList, chiTime);
		setKachimiWorktime(csvDataList, kachimiTime);
		setFukunagaWorktime(csvDataList, fukunagaTime);
		setSekiyaWorktime(csvDataList, sekiyaTime);

		return csvDataList;
	}


	@ResponseBody
	@RequestMapping(value = "/worktime-insert", method= RequestMethod.POST)
	public List<Worktime> addWorktime(@RequestParam Map<String, Object> params) {
		
		Worktime tbl = saveWorktime(params);
		
		if (StringUtils.isBlank(tbl.getRestMemo())) {
			tbl.setRestMemo("");
		}
		if ("休み".equals(tbl.getType())) {
			tbl.setStartTime("");
			tbl.setEndTime("");
		}

		WorktimeTblService.addWorktime(tbl);

		return getAllWorktimes(tbl.getUser(), tbl.getDate().substring(0, 7));
	}

	@ResponseBody
	@RequestMapping(value = "/worktime-update", method= RequestMethod.POST)
	public List<Worktime> updateWorktime(@RequestParam Map<String, Object> params) {

		Worktime tbl = saveWorktime(params);

		if (StringUtils.isBlank(tbl.getRestMemo())) {
			tbl.setRestMemo("");
		}

		if ("休み".equals(tbl.getType())) {
			tbl.setStartTime("");
			tbl.setEndTime("");
		}
		WorktimeTblService.editWorktime(tbl);

		return getAllWorktimes(tbl.getUser(), tbl.getDate().substring(0, 7));
	}
	
	private Worktime saveWorktime(Map<String, Object> params) {

		Worktime tbl = new Worktime();
		tbl.setDate((String) params.get("date"));
		tbl.setStartTime((String) params.get("startTime"));
		tbl.setEndTime((String) params.get("endTime"));
		tbl.setType((String) params.get("type"));
		tbl.setRestMemo((String) params.get("restMemo"));
		tbl.setUser((String) params.get("user"));
		return tbl;
	}
	
	private String countWorkingTime(String startTime, String endTime) {
		if(StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime))
			return "0.0";
		float startHour = Float.valueOf(startTime.split(":")[0]);
		float startMin = Float.valueOf(startTime.split(":")[1])/60;
		float endHour = Float.valueOf(endTime.split(":")[0]);
		float endMin = Float.valueOf(endTime.split(":")[1])/60;
		float worktime = endHour + endMin - startHour - startMin;
		if (worktime >= 8) {
			worktime -= 0.5;
		} else if(worktime < 0) {
			worktime = 0;
		}
		return String.valueOf(worktime);
	}

	private String countOvertime(String startTime, String endTime) {
		float worktime = Float.valueOf(countWorkingTime(startTime, endTime));
		if (worktime > 8.5) 
			worktime -= 7.5;
		else if(worktime >= 8) 
			worktime = 1;
		else if(worktime > 7) 
			worktime -= 7;
		else 
			worktime = 0;
		return String.valueOf(worktime);
	}

	private int getDaysOfMonth(int year, int month) {
		boolean isLeapYear = isLeapYear(year);
		int days = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			if (isLeapYear) {
				days = 29;
			} else {
				days = 28;
			}
		}
		return days;
	}

	private String getWeek(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

		switch (week) {
		case 1:
			return "月";
		case 2:
			return "火";
		case 3:
			return "水";
		case 4:
			return "木";
		case 5:
			return "金";
		case 6:
			return "土";
		case 0:
			return "日";
		default:
			return "日";
		}
	}

	private boolean isLeapYear(int year) {
		if (year % 100 == 0 && year % 400 == 0) {
			return true;
		} else if (year % 100 != 0 && year % 4 == 0) {
			return true;
		}
		return false;
	}
	
	private void setKaWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {
		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setKaDirect(String.valueOf(countWork));
				csvList.get(i).setKaOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setKaDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setKaOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getKaDirect());
				countOver += Float.valueOf(csvList.get(i).getKaOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setKaDirect("");
				csvList.get(i).setKaOvertime("");
			}
		}

	
	}
	
	private void setLiWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {

			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setLiDirect(String.valueOf(countWork));
				csvList.get(i).setLiOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setLiDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setLiOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getLiDirect());
				countOver += Float.valueOf(csvList.get(i).getLiOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setLiDirect("");
				csvList.get(i).setLiOvertime("");
			}
		}
	}
	

	private void setLitouWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setLitaoDirect(String.valueOf(countWork));
				csvList.get(i).setLitaoOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setLitaoDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setLitaoOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getLitaoDirect());
				countOver += Float.valueOf(csvList.get(i).getLitaoOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setLitaoDirect("");
				csvList.get(i).setLitaoOvertime("");
			}
		}
	}
	

	private void setChiWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setChiDirect(String.valueOf(countWork));
				csvList.get(i).setChiOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setChiDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setChiOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getChiDirect());
				countOver += Float.valueOf(csvList.get(i).getChiOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setChiDirect("");
				csvList.get(i).setChiOvertime("");
			}
		}
	}
	

	private void setKachimiWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setKachimiDirect(String.valueOf(countWork));
				csvList.get(i).setKachimiOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setKachimiDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setKachimiOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getKachimiDirect());
				countOver += Float.valueOf(csvList.get(i).getKachimiOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setKachimiDirect("");
				csvList.get(i).setKachimiOvertime("");
			}
		}
	}
	

	private void setFukunagaWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setFukunagaDirect(String.valueOf(countWork));
				csvList.get(i).setFukunagaOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setFukunagaDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setFukunagaOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getFukunagaDirect());
				countOver += Float.valueOf(csvList.get(i).getFukunagaOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setFukunagaDirect("");
				csvList.get(i).setFukunagaOvertime("");
			}
		}
	}
	

	private void setSekiyaWorktime(List<WorktimeCsv> csvList, List<Worktime> list) {

		int currentDay = 0;
		float countWork = 0;
		float countOver = 0;
		for(int i = 0; i<csvList.size(); i++) {
			if("合計".equals(csvList.get(i).getWeekday())) {
				csvList.get(i).setSekiyaDirect(String.valueOf(countWork));
				csvList.get(i).setSekiyaOvertime(String.valueOf(countOver));
				return;
			}
			int day = 0;

			if(currentDay != list.size()) {
				 day = Integer.valueOf(list.get(currentDay).getDate().split("-")[2]);
			} 
			if(day == i+1 && list.size() != 0) {
				csvList.get(i).setSekiyaDirect(countWorkingTime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				csvList.get(i).setSekiyaOvertime(countOvertime(list.get(currentDay).getStartTime(), list.get(currentDay).getEndTime()));
				countWork += Float.valueOf(csvList.get(i).getSekiyaDirect());
				countOver += Float.valueOf(csvList.get(i).getSekiyaOvertime());
				currentDay++;		
			} else {
				csvList.get(i).setSekiyaDirect("");
				csvList.get(i).setSekiyaOvertime("");
			}
		}
	}

}
