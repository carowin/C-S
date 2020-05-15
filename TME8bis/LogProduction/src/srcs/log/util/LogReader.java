package srcs.log.util;


import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

import srcs.log.DateLog;
import srcs.log.Log;

public class LogReader extends FilterReader{
	private BufferedReader in;
	private String nom;
	
	public LogReader(String nom, BufferedReader in) {
		super(in);
		this.in = in;
		this.nom = nom;
	}
	
	public Log nextLog() throws IOException {
		String line = in.readLine();
		if(line == null) {
			return null;
		}else {
			Log log = new Log();
			DateLog date = new DateLog();
			date.setAnnée(Parser.getYear(line));
			date.setMois(Parser.getMonth(line));
			date.setJour(Parser.getDay(line));
			date.setHeure(Parser.getHour(line));
			date.setMinutes(Parser.getMinute(line));
			date.setSecondes(Parser.getSecond(line));
			date.setMillisecondes(Parser.getMilliSecond(line));
		
			log.setDatelog(date);
			log.setLevel(Parser.getLevel(line));
			log.setMachine(Parser.getMachine(line));
			log.setMessage(Parser.getMessage(line));
			return log;
		}
	}


}
