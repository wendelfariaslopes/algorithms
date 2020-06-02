package com.organon.tool;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.organon.oms.Order;

public class FileCSVTest {

	private static Logger logger = Logger.getLogger(FileCSVTest.class.getName()); // jul java util logging

	public static void main(String[] args) throws Exception {

		List<Developer> list = Arrays.asList(new Developer("mkyong", new BigDecimal(120500), 32),
				new Developer("zilap", new BigDecimal(150099), 5),
				new Developer("ultraman", new BigDecimal(99999), 99));
		
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(new Order(0,0,"GOOG",100,true,20.0));
		for(int i = 0; i < 40; i++) {
			listOrder.add(new Order(i,i*37,"GOOG-"+i,100*i*13,true,i*22.0));
		}
		
		test2(list);
		test2(listOrder);

		
	}

	public static <T> void test2(List<T> list) throws IOException {

		if (list.isEmpty()) {
			logger.severe("List cannot be Empty");
		}else {
			
			// must be at least one element (and all elements are the same)
			Class elementType = list.get(0).getClass();

			String csvFile = "C:\\Users\\Utilisateur\\git\\algorithms\\src\\com\\organon\\tool\\"
					+ elementType.getSimpleName() + ".csv";
			FileWriter writer = new FileWriter(csvFile);

			// for header
			Field[] fields = list.get(0).getClass().getDeclaredFields();
			List<String> listFields = new ArrayList<>();

			for (Field f : fields) {
				listFields.add(f.getName());
			}

			FileCSV.writeLine(writer, listFields);

			for (Object o : list) {
				List<String> listValues = runGetter(o);
				FileCSV.writeLine(writer, listValues);
			}

			writer.flush();
			writer.close();
			
		}

		

	}

	public static List<String> runGetter(Object o) {

		List<String> listFields = new ArrayList<>();

		Field[] fields = o.getClass().getDeclaredFields();

		for (Field field : fields) {

			for (Method method : o.getClass().getMethods()) {

				if ((method.getName().startsWith("get"))
						&& (method.getName().length() == (field.getName().length() + 3))) {
					if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
						// MZ: Method found, run it
						try {
							listFields.add(method.invoke(o).toString());
						} catch (IllegalAccessException e) {
							logger.severe("Could not determine method: " + method.getName());
						} catch (InvocationTargetException e) {
							logger.severe("Could not determine method: " + method.getName());
						}

					}
				}

			}

		}

		return listFields;
	}

	public void test1() throws IOException {

		String currentDirectory = System.getProperty("user.dir");
		System.out.println("The current working directory is " + currentDirectory);

		String csvFile = currentDirectory + "/abc.csv";
		FileWriter writer = new FileWriter(csvFile);

		FileCSV.writeLine(writer, Arrays.asList("a", "b", "c", "d"));

		// custom separator + quote
		FileCSV.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"');

		// custom separator + quote
		FileCSV.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'');

		// double-quotes
		FileCSV.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));

		writer.flush();
		writer.close();

	}

}
