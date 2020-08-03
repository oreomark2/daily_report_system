package models.validators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Report;

public class ReportValidator {
	public static List<String> validate(Report r){
		List<String> errors = new ArrayList<String>();

		String title_error = _validateTitle(r.getTitle());
		if(!title_error.equals("")){
			errors.add(title_error);
		}

		String content_error = _validateContent(r.getContent());
		if(!content_error.equals("")){
			errors.add(content_error);
		}

		String time_error = _validateTime(r.getStart_time(), r.getEnd_time());
		if(!time_error.equals("")){
			errors.add(time_error);
		}

		return errors;
	}

	private static String _validateTitle(String title){
		if(title == null || title.equals("")){
			return "タイトルを入力してください";
		}

		return "";
	}
	private static String _validateContent(String content){
		if(content == null || content.equals("")){
			return "内容を入力してください。";
		}

		return "";
	}

	public static String _validateTime(String start_time, String end_time){
		if(start_time.equals(end_time)){
			return "出勤時刻と退勤時刻は同じ時間に設定できません";
		}

		Calendar start_timeCal = Calendar.getInstance();
		Calendar end_timeCal = Calendar.getInstance();

		String[] start_timeList = start_time.split(":");
		start_timeCal.set(2000, 1, 1, Integer.parseInt(start_timeList[0]), Integer.parseInt(start_timeList[1]), 0);

		String[] end_timeList = end_time.split(":");
		end_timeCal.set(2000, 1, 1, Integer.parseInt(end_timeList[0]), Integer.parseInt(end_timeList[1]), 0);

		int diff = start_timeCal.compareTo(end_timeCal);

		if(diff > 0){
			return "出勤時刻は退勤時刻より前の時間で入力してください";
		}

		return "";
	}


}
