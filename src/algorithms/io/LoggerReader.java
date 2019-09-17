package algorithms.io;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.regex.Pattern;

import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A simple java swing applet japplet, that makes a table a jtable and fills its
 * columns and rows with parsed log4j data from a log file which it parses a
 * parser for such log files so it is a log reader which can parse logfiles
 * log4jfiles and show in applet with a table jtable with columns that you can
 * resize columns.
 * 
 * assumes log4j standard output for logs created in 2011: <code>
 * 2011-MMM-DD HH:MM:SS com.package class method 
 * LEVEL: TEXT 
 * ADDITIONAL TEXT 
 * ADDITIONAL TEXT
 * ADDITIONAL TEXT 
 * 2011-MMM-DD HH:MM:SS com.package class method 
 * LEVEL: TEXT 
 * </code> and so on
 */
public class LoggerReader extends JApplet {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private final class RunnableImplementation implements Runnable {
      private static final String LOG_FILE_URI = "/Users/wendellopes/Downloads/Logger/sample.log.0";
      private final JTable table;

      private RunnableImplementation(JTable table) {
         this.table = table;
      }

      @Override
      public void run() {
         try {
            File f = new File(new URI(LOG_FILE_URI));

            long lengthRead = 0;

            while (true != false) {
               if (f.length() > lengthRead) {
                  BufferedReader br = new BufferedReader(new FileReader(f));

                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                  while (br.ready()) {
                     String readLine = br.readLine();
                     while (br.ready()) {
                        if (readLine.startsWith("2011-")) {
                           String time = readLine.subSequence(12, 20).toString();
                           
                           String rest = readLine.substring(20);
                           Pattern p = Pattern.compile("[A-Z]");
                           String[] split = p.split(rest);
                           String packagee = split[0].substring(split[0].indexOf(".") + 1);
                           
                           String[] split2 = rest.substring(split[0].length()).split(" ");
                           String clazz = split2[0];
                           String method = split2[1];

                           readLine = br.readLine();

                           split = readLine.split(":");
                           String level = split[0];
                           String text = split[1];
                           for (String string : split) {
                              text = text.concat(string);
                           }

                           model.insertRow(0, new Object[] { level, time, packagee, clazz, method, text });

                           readLine = br.readLine();
                           while (!readLine.startsWith("2011-") && br.ready()) {
                              text = readLine;
                              readLine = br.readLine();
                              model.insertRow(0, new Object[] { "", "", "", "", "", text });
                           }
                        } else {
                           throw new RuntimeException("parse error :'" + readLine + "'");
                        }

                     }

                  }

                  lengthRead = f.length();
               }
               Thread.sleep(10L);
            }
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }
   }

   public LoggerReader() {

      String data[][] = {};
      String col[] = { "level", "date", "package", "class", "method", "text" };
      DefaultTableModel model = new DefaultTableModel(data, col);
      final JTable table = new JTable(model);
      table.setSize(1100, 650);
      JScrollPane pane = new JScrollPane(table);
      pane.setSize(1100, 650);

      table.getColumnModel().getColumn(0).setMaxWidth(35);
      table.getColumnModel().getColumn(1).setMaxWidth(65);
      table.getColumnModel().getColumn(2).setPreferredWidth(128);
      table.getColumnModel().getColumn(2).setResizable(false);
      table.getColumnModel().getColumn(3).setPreferredWidth(128);
      table.getColumnModel().getColumn(4).setPreferredWidth(128);
      table.getColumnModel().getColumn(5).setPreferredWidth(1000);
      table.getColumnModel().getColumn(5).setPreferredWidth(1000);

      this.add(pane);

      Runnable d = new RunnableImplementation(table);

      Thread th = new Thread(d);
      th.start();

   }

}