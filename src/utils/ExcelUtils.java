package utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {
	private static XSSFWorkbook wb = null;
	private static XSSFSheet sheet = null;
	private static String excellPath;

	public static boolean setExcell(String path) {
		if (wb != null) {
			try {
				wb.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
		}
		File f = new File(path);
		try {
			FileInputStream fis = new FileInputStream(f);

			wb = new XSSFWorkbook(fis);
			excellPath = path;
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Lose otvaranje fajla!");
			return false;
		}
	}

	public static boolean setWorkSheet(int index) {
		try {
			sheet = wb.getSheetAt(index);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Lose otvaranje worksheeta!");
			return false;
		}
	}
	
	public static String getDataAt(int row, int column) {
		try {
			XSSFRow r = sheet.getRow(row);
			XSSFCell celija = r.getCell(column);
			return celija.toString();
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			System.out.println("Nesto je null!");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Doslo je do greske!");
		}
		return "";
	}
	
	
	public static String getDataAtNum(int row, int column) {
		try {
			XSSFRow r = sheet.getRow(row);
			XSSFCell celija = r.getCell(column);
			double br = celija.getNumericCellValue();
			String rez= "";
			rez = rez + (int)br;
			return rez;
		} catch (NullPointerException e) {
			System.out.println(e.toString());
			System.out.println("Nesto je null!");
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Doslo je do greske!");
		}
		return ""; 
	}
	
	public static boolean setDataAt(int row, int column, String data) {
		try {
			XSSFRow r = sheet.getRow(row);
			if (r == null) {
				r = sheet.createRow(row);
			}
			XSSFCell celija = r.getCell(column);
			if (celija == null) {
				celija = r.createCell(column);
			}
			celija.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(excellPath);
			wb.write(fos);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Doslo je do greske!");
			return false;
		}
	}
	
	public static void setUniqueId() {
		for(int i = 1; i < getRowNumber(); i++) {
			String newId = UUID.randomUUID().toString();
			newId = newId.substring(0, 6);
			setDataAt(i, 0, newId);
		}
	}
	
	public static void setLanguagePreference() {
		List<String> languages = new ArrayList<String>();
		languages.add("english");
		languages.add("japanese");
		Random rand = new Random();
		for(int i = 1; i < getRowNumber(); i++ ) {
			int randomIndex = rand.nextInt(languages.size());
			setDataAt(i, 12, languages.get(randomIndex));
		}
	}
	
	public static void setFavoriteCategory() {
		List<String> category = new ArrayList<String>();
		category.add("FISH");
		category.add("DOGS");
		category.add("REPTILES");
		category.add("CATS");
		category.add("BIRDS");
		Random rand = new Random();
		for(int i = 1; i < getRowNumber(); i++ ) {
			int randomIndex = rand.nextInt(category.size());
			setDataAt(i, 13, category.get(randomIndex));
		}
	}
	
	public static int getRowNumber() {
		try {
			
			return sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("Doslo je do greske!");
			return -1;
		}
	}

	public static boolean closeExcell() {
		if (wb != null) {
			try {
				wb.close();
				wb = null;
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				wb = null;
				return false;
			}
		}
		return true;
	}

}
