package com.KoreaIT.example.JAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static String DatetimeFormat(LocalDateTime datetime) {
		return DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss").format(datetime);
		
	}
}
