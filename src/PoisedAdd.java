import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Dean Gulston
 * @version 1.0
 *
 */
public class PoisedAdd {
	
	/**
	 * Retrieves maximum project number from the Projects table in the PoisePMS database.
	 * @param statement: Statement - Direct line of connection to the PoisePMS database.
	 * @return maxProjectNum: int - Returns maximum project number in the Projects table.
	 */
	public static int getMaxProjectNumber(Statement statement) {
		int maxProjectNum = 0;
		
		ResultSet results;
		
		try {
			// Gets maximum project number from Projects table in PoisePMS database.
			results = statement.executeQuery("SELECT MAX(project_number) AS max_project_number FROM Projects");
			
			while(results.next()) {
				// Assigns maximum project number.
				maxProjectNum = results.getInt("max_project_number");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maxProjectNum;
	}

	/**
	 * Allows a user to add a new project to the PoisePMS database.
	 * @param scInput: Scanner - Scanner object that obtains user input.
	 * @param statement: Statement - Direct line of connection established to the
	 * PoisePMS database.
	 */
	public static void addProject(Scanner scInput, Statement statement) {
		// Gets the maximum project number from the PoisePMS database so we can determine a
		// project number for our newly added project.
		int maxProjectNumber = getMaxProjectNumber(statement);
		
		// Increment the maximum project number so we can set this as our latest project number.
		maxProjectNumber++;
		
		// User prompted to enter a project name.
		System.out.print("\nEnter the name of the project: ");
		String projectName = scInput.nextLine();
		
		String buildingType = "";
		
		// User will keep being prompted to enter a building type if the building type they
		// keep entering is blank.
		while(buildingType.equals("")) {
			
			// User prompted to enter a building type.
			System.out.print("\nEnter the building type: ");
			buildingType = scInput.nextLine();
			
			// If the user enters a blank building type, an error message is displayed.
			if(buildingType.equals("")) {
				System.out.println("Building type cannot be blank.");
			}
		}
		
		// User prompted to enter the physical address of the project.
		System.out.print("\nEnter the physical address of the project: ");
		String physicalAddress = "'" + scInput.nextLine() + "'";
		
		// If physical address is equal to '', it is set to NULL.
		if(physicalAddress.equals("''")) {
			physicalAddress = "NULL";
		}
		
		boolean promptErfNumber = true;
		int erfNumber = -1;
		
		// User will keep being prompted to enter the erf number if they do not enter
		// a blank string or an integer.
		while(promptErfNumber) {
			try {
				// User prompted to enter the erf number.
				System.out.print("\nEnter the erf number (must be an integer): ");
				String strErfNumber = scInput.nextLine();
				
				// If the user does not enter a blank string, the value is converted to
				// an integer.
				if(!strErfNumber.equals("")) {
					erfNumber = Integer.parseInt(strErfNumber);
				}
				
				// Loop ends if the user enters a blank string or an integer.
				promptErfNumber = false;
			}
			catch (NumberFormatException e) {
				// If the user does not type an integer, an error message is displayed
				// and they are prompted to try again.
				System.out.println("You did not enter an integer. Please try again.");
			}
		}
		
		boolean promptTotalFeeCharged = true;
		double totalFeeCharged = -1.0;
		
		// User will keep being prompted to enter the total fee charged if they do not enter
		// a blank string or a decimal.
		while(promptTotalFeeCharged) {
			
			try {
				// User prompted to enter the total fee charged.
				System.out.print("\nEnter the total fee charged (must be a decimal): R");
				String strTotalFeeCharged = scInput.nextLine();
				
				// If the user does not enter a blank string, the value is converted to
				// a decimal/double.
				if(!strTotalFeeCharged.equals("")) {
					totalFeeCharged = Double.parseDouble(strTotalFeeCharged);
				}
				
				// Loops ends if the user enters a double or a blank string.
				promptTotalFeeCharged = false;
				
			}
			catch (NumberFormatException e) {
				// If the user does not enter a decimal, an error message is displayed
				// and they are prompted to try again.
				System.out.println("You did not enter a decimal. Please try again.");
			}
		}
		
		boolean promptTotalAmountPaid = true;
		double totalAmountPaid = -1.0;
		
		// User will keep being prompted to enter the total amount paid to date if they
		// do not enter a blank string or a decimal.
		while(promptTotalAmountPaid) {
			
			try {
				// User prompted to enter the total amount paid to date.
				System.out.print("\nEnter the total amount paid to date (must be a decimal): R");
				String strTotalAmountPaid = scInput.nextLine();
				
				// If the user does not enter a blank string, the value is converted to
				// a decimal/double.
				if(!strTotalAmountPaid.equals("")) {
					totalAmountPaid = Double.parseDouble(strTotalAmountPaid);
				}
				
				// Loops ends if the user enters a double or a blank string.
				promptTotalAmountPaid = false;
				
			}
			catch (NumberFormatException e) {
				// If the user does not enter a decimal, an error message is displayed
				// and they are prompted to try again.
				System.out.println("You did not enter a decimal. Please try again.");
			}
		}
		
		String deadlineDate = "";
		
		boolean promptDeadlineDate = true;
		
		// User will keep being prompted to enter a deadline date if the deadline date
		// they enter is invalid or not a blank string.
		while(promptDeadlineDate) {
			
			// User prompted to enter the deadline date.
			System.out.print("\nEnter the deadline date (in the format YYYY/MM/DD): ");
			deadlineDate = scInput.nextLine();
			
			// If the user enters a valid date or a blank string, the loop ends.
			if(PoisedDateChecker.isDate(deadlineDate) || deadlineDate.equals("")) {
				deadlineDate = "'" + deadlineDate + "'";
				promptDeadlineDate = false;
			}
			else if(!PoisedDateChecker.isDate(deadlineDate)) {
				// If the user does not a valid date, an error message is displayed
				// and the user is prompted to try again.
				System.out.println("Invalid date. Please try again.");
			}
		}
		
		// If deadline date is equal to '', it is set to NULL.
		if(deadlineDate.equals("''")) {
			deadlineDate = "NULL";
		}
		
		// User prompted to enter the architect full name.
		System.out.print("\nEnter the full name of the architect: ");
		String architectFullName = "'" + scInput.nextLine() + "'";
		
		String architectTelephone = "";
		String architectEmailAddress = "";
		String architectPhysicalAddress = "";
		
		// If architect full name is equal to '', the architect name, telephone, email
		// address and physical address are set to NULL. Otherwise, they are prompted to
		// enter the details for architect telephone, email address and physical address.
		if(architectFullName.equals("''")) {
			architectFullName = "NULL";
			architectTelephone = "NULL";
			architectEmailAddress = "NULL";
			architectPhysicalAddress = "NULL";
		}
		else {
			// User prompted to enter the architect telephone number.
			System.out.print("\nEnter the architect telephone number: ");
			architectTelephone = "'" + scInput.nextLine() + "'";
			
			// If architect telephone is equal to '', it is set to NULL.
			if(architectTelephone.equals("''")) {
				architectTelephone = "NULL";
			}
			
			// User prompted to enter the architect email address.
			System.out.print("\nEnter the architect email address: ");
			architectEmailAddress = "'" + scInput.nextLine() + "'";
			
			// If architect email address is equal to '', it is set to NULL.
			if(architectEmailAddress.equals("''")) {
				architectEmailAddress = "NULL";
			}
			
			// User prompted to enter the architect physical address.
			System.out.print("\nEnter the architect physical address: ");
			architectPhysicalAddress = "'" + scInput.nextLine() + "'";
			
			// If architect physical address is equal to '', it is set to NULL.
			if(architectPhysicalAddress.equals("''")) {
				architectPhysicalAddress = "NULL";
			}
		}
		
		// User prompted to enter the contractor full name.
		System.out.print("\nEnter the full name of the contractor: ");
		String contractorFullName = "'" + scInput.nextLine() + "'";

		String contractorTelephone = "";
		String contractorEmailAddress = "";
		String contractorPhysicalAddress = "";
		
		// If contractor full name is equal to '', the architect name, telephone, email
		// address and physical address are set to NULL. Otherwise, they are prompted to
		// enter the details for contractor telephone, email address and physical address.
		if(contractorFullName.equals("''")) {
			contractorFullName = "NULL";
			contractorTelephone = "NULL";
			contractorEmailAddress = "NULL";
			contractorPhysicalAddress = "NULL";
		}
		else {
			// User prompted to enter the contractor telephone number.
			System.out.print("\nEnter the contractor telephone number: ");
			contractorTelephone = "'" + scInput.nextLine() + "'";
			
			// If contractor telephone is equal to '', it is set to NULL.
			if(contractorTelephone.equals("''")) {
				contractorTelephone = "NULL";
			}
			
			// User prompted to enter the contractor email address.
			System.out.print("\nEnter the contractor email address: ");
			contractorEmailAddress = "'" + scInput.nextLine() + "'";
			
			// If contractor email address is equal to '', it is set to NULL.
			if(contractorEmailAddress.equals("''")) {
				contractorEmailAddress = "NULL";
			}
			
			// User prompted to enter the contractor physical address.
			System.out.print("\nEnter the contractor physical address: ");
			contractorPhysicalAddress = "'" + scInput.nextLine() + "'";
			
			// If contractor physical address is equal to '', it is set to NULL.
			if(contractorPhysicalAddress.equals("''")) {
				contractorPhysicalAddress = "NULL";
			}
		}
		
		String customerFullName = "";
		
		// User will keep being prompted to enter the customer full name if the customer
		// full name they are entering is blank.
		while(customerFullName.equals("")) {
			// User prompted to enter the customer full name.
			System.out.print("\nEnter the full name of the customer: ");
			customerFullName = scInput.nextLine();
			
			// If the user enters a blank customer full name, an error message is displayed.
			if(customerFullName.equals("")) {
				System.out.println("Customer full name cannot be blank.");
			}
		}
		
		String customerTelephone = "";
		String customerEmailAddress = "";
		String customerPhysicalAddress = "";
		
		// User prompted to enter the customer telephone number.
		System.out.print("\nEnter the customer telephone number: ");
		customerTelephone = "'" + scInput.nextLine() + "'";
		
		// If customer telephone is equal to '', it is set to NULL.
		if(customerTelephone.equals("''")) {
			customerTelephone = "NULL";
		}
		
		// User prompted to enter the customer email address.
		System.out.print("\nEnter the customer email address: ");
		customerEmailAddress = "'" + scInput.nextLine() + "'";
		
		// If customer email address is equal to '', it is set to NULL.
		if(customerEmailAddress.equals("''")) {
			customerEmailAddress = "NULL";
		}
		
		// User prompted to enter the customer physical address.
		System.out.print("\nEnter the customer physical address: ");
		customerPhysicalAddress = "'" + scInput.nextLine() + "'";
		
		// If customer physical address is equal to '', it is set to NULL.
		if(customerPhysicalAddress.equals("''")) {
			customerPhysicalAddress = "NULL";
		}
		
		String customerSurname = "";
		
		// Separates customer full name into tokens/strings separated by a space.
		StringTokenizer tokenizer = new StringTokenizer(customerFullName, " ");
		
		// Iterates through each string/token separated by a space in the customer full name.
		// When the loop ends, the customer surname will be set to the last token which is the
		// surname.
		while(tokenizer.hasMoreTokens()) {
			customerSurname = tokenizer.nextToken();
		}
		
		// If the project name is blank, the project name is set to a concatenation of
		// the building type and the customer surname.
		if(projectName.equals("")) {
			projectName = "'" + buildingType + " " + customerSurname + "'";
		}
		
		// Adds single quotes around building type and customer full name in preparation
		// for the SQL query.
		buildingType = "'" + buildingType + "'";
		customerFullName = "'" + customerFullName + "'";
		
		String strErfNumber = "" + erfNumber;
		
		// If the user enters a blank erf number, i.e. it still equals the default -1 value,
		// then we set the string erf number to NULL.
		if(erfNumber == -1) {
			strErfNumber = "NULL";
		}
		
		String strTotalFeeCharged = "" + totalFeeCharged;
		
		// If the user enters a blank total fee charged, i.e. it still equals the default -1.0 value,
		// then we set the string total fee charged to NULL.
		if(totalFeeCharged == -1.0) {
			strTotalFeeCharged = "NULL";
		}
		
		String strTotalAmountPaid = "" + totalAmountPaid;
		
		// If the user enters a blank total amount paid to date, i.e. it still equals the default -1.0 value,
		// then we set the string total amount paid to date to NULL.
		if(totalAmountPaid == -1.0) {
			strTotalAmountPaid = "NULL";
		}
		
		System.out.println("\n" + maxProjectNumber + ", " + projectName + ", " + buildingType + ", " + physicalAddress
				 + ", " + strErfNumber + ", " + strTotalFeeCharged + ", " + strTotalAmountPaid + ", " + deadlineDate
				 + ", 0, NULL, " + architectFullName + ", " + architectTelephone + ", " + architectEmailAddress
				 + ", " + architectPhysicalAddress + ", " + contractorFullName + ", " + contractorTelephone
				 + ", " + contractorEmailAddress + ", " + contractorPhysicalAddress + ", " + customerFullName
				 + ", " + customerTelephone + ", " + customerEmailAddress + ", " + customerPhysicalAddress);
		
		try {
			// Inserts the given values into the Projects table in the PoisePMS database.
			int rowsAffected = statement.executeUpdate("INSERT INTO Projects VALUES ("
					 + maxProjectNumber + ", " + projectName + ", " + buildingType + ", " + physicalAddress
					 + ", " + strErfNumber + ", " + strTotalFeeCharged + ", " + strTotalAmountPaid + ", " + deadlineDate
					 + ", 0, NULL, " + architectFullName + ", " + contractorFullName + ", " + customerFullName + ")");
			
			System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
			
			// Inserts the given values into the Customers table in the PoisePMS database.
			rowsAffected = statement.executeUpdate("INSERT INTO Customers VALUES ("
					 + customerFullName + ", " + customerTelephone + ", " + customerEmailAddress
					 + ", " + customerPhysicalAddress + ")");
			
			System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
			
			// If the given contractor full name is not NULL, then insert the given values into
			// the Contractors table in the PoisePMS database.
			if(!contractorFullName.equals("NULL")) {
				rowsAffected = statement.executeUpdate("INSERT INTO Contractors VALUES ("
						+ contractorFullName + ", " + contractorTelephone + ", " + contractorEmailAddress
						+ ", " + contractorPhysicalAddress + ")");
				
				System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
			}
			
			// If the given architect full name is not NULL, then insert the given values into
			// the Architects table in the PoisePMS database.
			if(!architectFullName.equals("NULL")) {
				rowsAffected = statement.executeUpdate("INSERT INTO Architects VALUES ("
						+ architectFullName + ", " + architectTelephone + ", " + architectEmailAddress
						+ ", " + architectPhysicalAddress + ")");
				
				System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
