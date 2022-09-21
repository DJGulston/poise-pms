import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author Dean Gulston
 * @version 1.0
 *
 */
public class PoisedIncompleteProjects {

	/**
	 * Retrieves and displays all project information for all projects that are
	 * unfinalised/incomplete, i.e. where the finalised_status_id is 0.
	 * @param scInput: Scanner - Scanner object used to obtain user input.
	 * @param statement: Statement - Direct line of connection established to the
	 * PoisePMS database.
	 */
	public static void getIncompleteProjects(Scanner scInput, Statement statement) {
		
		ResultSet results;
		
		try {
			
			// Retrieves all projects that are unfinalised/incomplete, i.e. all projects
			// with finalised_status_id = 0.
			results = statement.executeQuery("SELECT proj.project_number, "
					+ "proj.name, "
					+ "proj.building_type, "
					+ "proj.physical_address, "
					+ "proj.erf_number, "
					+ "proj.total_fee_charged, "
					+ "proj.total_amount_paid_to_date, "
					+ "proj.deadline, "
					+ "fin.id, "
					+ "fin.status, "
					+ "proj.finalised_date, "
					+ "arch.full_name, "
					+ "arch.telephone_number, "
					+ "arch.email_address, "
					+ "arch.physical_address, "
					+ "cont.full_name, "
					+ "cont.telephone_number, "
					+ "cont.email_address, "
					+ "cont.physical_address, "
					+ "cust.full_name, "
					+ "cust.telephone_number, "
					+ "cust.email_address, "
					+ "cust.physical_address "
					+ "FROM Projects AS proj "
					+ "LEFT JOIN Architects AS arch "
					+ "ON arch.full_name = proj.architect_full_name "
					+ "LEFT JOIN Contractors AS cont "
					+ "ON cont.full_name = proj.contractor_full_name "
					+ "LEFT JOIN Customers AS cust "
					+ "ON cust.full_name = proj.customer_full_name "
					+ "INNER JOIN Finalised AS fin "
					+ "ON fin.id = proj.finalised_status_id "
					+ "WHERE fin.id = 0");
			
			int incompleteCount = 0;
			
			while(results.next()) {
				
				incompleteCount++;
				
				// Retrieves each column value from the SELECT query.
				int projectNumber = results.getInt(1);
				String projectName = results.getString(2);
				String buildingType = results.getString(3);
				String physicalAddress = results.getString(4);
				int erfNumber = results.getInt(5);
				double totalFeeCharged = results.getDouble(6);
				double totalAmountPaid = results.getDouble(7);
				Date deadlineDate = results.getDate(8);
				int finalisedStatusId = results.getInt(9);
				String finalisedStatus = results.getString(10);
				Date finalisedDate = results.getDate(11);
				String architectFullName = results.getString(12);
				String architectTelephone = results.getString(13);
				String architectEmailAddress = results.getString(14);
				String architectPhysicalAddress = results.getString(15);
				String contractorFullName = results.getString(16);
				String contractorTelephone = results.getString(17);
				String contractorEmailAddress = results.getString(18);
				String contractorPhysicalAddress = results.getString(19);
				String customerFullName = results.getString(20);
				String customerTelephone = results.getString(21);
				String customerEmailAddress = results.getString(22);
				String customerPhysicalAddress = results.getString(23);
				
				// Column headings and values printed.
				System.out.println("\nProject Number: " + projectNumber
						+ "\nProject Name: " + projectName
						+ "\nBuilding Type: " + buildingType
						+ "\nProject Physical Address: " + physicalAddress
						+ "\nERF Number: " + erfNumber
						+ "\nTotal Fee Charged: " + totalFeeCharged
						+ "\nTotal Amount Paid To Date: " + totalAmountPaid
						+ "\nDeadline: " + deadlineDate
						+ "\nFinalised Status ID: " + finalisedStatusId
						+ "\nFinalised Status: " + finalisedStatus
						+ "\nFinalised Date: " + finalisedDate
						+ "\nArchitect Full Name: " + architectFullName
						+ "\nArchitect Telephone Number: " + architectTelephone
						+ "\nArchitect Email Address: " + architectEmailAddress
						+ "\nArchitect Physical Address: " + architectPhysicalAddress
						+ "\nContractor Full Name: " + contractorFullName
						+ "\nContractor Telephone Number: " + contractorTelephone
						+ "\nContractor Email Address: " + contractorEmailAddress
						+ "\nContractor Physical Address: " + contractorPhysicalAddress
						+ "\nCustomer Full Name: " + customerFullName
						+ "\nCustomer Telephone Number: " + customerTelephone
						+ "\nCustomer Email Address: " + customerEmailAddress
						+ "\nCustomer Physical Address: " + customerPhysicalAddress);
				
			}
			
			if(incompleteCount == 0) {
				System.out.println("\nNo incomplete/unfinalised projects found.");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
