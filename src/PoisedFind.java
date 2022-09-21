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
public class PoisedFind {

	/**
	 * Returns all information for a project specified by the user either by its
	 * project number or project name.
	 * @param scInput: Scanner - Scanner object used to obtain user input.
	 * @param statement: Statement - Direct line of connection established to the
	 * PoisePMS database.
	 */
	public static void findProject(Scanner scInput, Statement statement) {
		
		int searchMethod = 0;
		
		boolean promptSearchMethod = true;
		
		// User will keep being prompted to choose a search method if do not enter 1 or 2.
		while(promptSearchMethod) {
			
			try {
				// User prompted to choose a search method.
				System.out.print("\nHow would you like to search for the project:"
						+ "\n1. Project number"
						+ "\n2. Project name"
						+ "\n\nType only the number: ");
				
				searchMethod = Integer.parseInt(scInput.nextLine());
				
				// Error message is displayed if the user does not type 1 or 2.
				if(searchMethod != 1 && searchMethod != 2) {
					System.out.println("You did not select a search method. Please try again.");
				}
				else {
					// Loop ends if user types 1 or 2.
					promptSearchMethod = false;
				}
			}
			catch (NumberFormatException e) {
				// Error message displayed if the user does not enter an integer.
				System.out.println("You did not enter an integer. Please try again.");
			}
		}
		
		String whereClause = "";
		
		if(searchMethod == 1) { // User searches for project by project number.
			
			int projectNumber = 0;
			
			boolean promptProjectNumber = true;
			
			// User will keep being prompted to enter the project number if they do not
			// enter an integer.
			while(promptProjectNumber) {
				
				try {
					// User prompted to enter the project number.
					System.out.print("\nEnter the project number: ");
					projectNumber = Integer.parseInt(scInput.nextLine());
					
					// The where clause is set to the given project number in preparation
					// for the final SELECT query.
					whereClause = "proj.project_number = " + projectNumber;
					
					promptProjectNumber = false; // Loop ends.
				}
				catch (NumberFormatException e) {
					// Error message displayed if the user does not enter an integer.
					System.out.println("You did not enter an integer. Please try again.");
				}
				
			}
			
		}
		else if(searchMethod == 2) { // User searches for project by project name.
			
			String projectName = "''";
			
			// User will keep being prompted to enter the project name if they enter a blank
			// project name.
			while(projectName.equals("''")) {
				
				// User prompted to enter the project name.
				System.out.print("\nEnter the project name: ");
				projectName = "'" + scInput.nextLine() + "'";
				
				// If the user enters a blank project name, an error message is displayed and
				// they are prompted to try again.
				if(projectName.equals("''")) {
					System.out.println("You did not enter a project name. Please try again.");
				}
			}
			
			// The where clause is set to the given project name in preparation for the
			// final SELECT query.
			whereClause = "proj.name = " + projectName;
		}
		
		ResultSet results;
		
		try {
			// All results for the given project obtained from the PoisePMS database
			// using the where clause constructed above.
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
					+ "WHERE " + whereClause);
			
			int projectNumber = 0;
			String projectName = "";
			String buildingType = "";
			String physicalAddress = "";
			int erfNumber = 0;
			double totalFeeCharged = 0.0;
			double totalAmountPaid = 0.0;
			Date deadlineDate = null;
			int finalisedStatusId = 0;
			String finalisedStatus = "";
			Date finalisedDate = null;
			String architectFullName = "";
			String architectTelephone = "";
			String architectEmailAddress = "";
			String architectPhysicalAddress = "";
			String contractorFullName = "";
			String contractorTelephone = "";
			String contractorEmailAddress = "";
			String contractorPhysicalAddress = "";
			String customerFullName = "";
			String customerTelephone = "";
			String customerEmailAddress = "";
			String customerPhysicalAddress = "";
			
			int projectCount = 0;
			
			while(results.next()) {
				
				projectCount++;
				
				// Retrieves each column value from the SELECT query.
				projectNumber = results.getInt(1);
				projectName = results.getString(2);
				buildingType = results.getString(3);
				physicalAddress = results.getString(4);
				erfNumber = results.getInt(5);
				totalFeeCharged = results.getDouble(6);
				totalAmountPaid = results.getDouble(7);
				deadlineDate = results.getDate(8);
				finalisedStatusId = results.getInt(9);
				finalisedStatus = results.getString(10);
				finalisedDate = results.getDate(11);
				architectFullName = results.getString(12);
				architectTelephone = results.getString(13);
				architectEmailAddress = results.getString(14);
				architectPhysicalAddress = results.getString(15);
				contractorFullName = results.getString(16);
				contractorTelephone = results.getString(17);
				contractorEmailAddress = results.getString(18);
				contractorPhysicalAddress = results.getString(19);
				customerFullName = results.getString(20);
				customerTelephone = results.getString(21);
				customerEmailAddress = results.getString(22);
				customerPhysicalAddress = results.getString(23);
				
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
			
			if(projectCount == 0) {
				System.out.println("No projects found.");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
