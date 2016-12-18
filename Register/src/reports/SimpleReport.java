package reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

public class SimpleReport {

  public static void main(String[] args) {
	Connection connection = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.0.7:3306/test");
	} catch (SQLException e) {
		e.printStackTrace();
		return;
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		return;
	}

	JasperReportBuilder report = DynamicReports.report();//a new report
	report
	  .columns(
	      Columns.column("Database Id", "Insurance_Bill_Id", DataTypes.integerType()),
	      Columns.column("Desc", "Insuracne_Trans_Description", DataTypes.stringType()),
	      Columns.column("Payed", "Insuracne_Payed", DataTypes.bigDecimalType()),
	      Columns.column("Bal Owed", "Inusrance_Bal_Owed", DataTypes.bigDecimalType()))
	  .title(//title of the report
	      Components.text("SimpleReportExample")
		  .setHorizontalAlignment(HorizontalAlignment.CENTER))
		  .pageFooter(Components.pageXofY())//show page number on the page footer
		  .setDataSource("SELECT Insurance_Bill_Id, Insuracne_Trans_Description, Insuracne_Payed, Inusrance_Bal_Owed FROM Insurance_Bill_Table",
                                  connection);

	try {
                //show the report
		report.show();

                //export the report to a pdf file
		report.toPdf(new FileOutputStream("c:/report.pdf"));
	} catch (DRException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
  }
}