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
public class PoisedUpdate {

	/**
	 * Allows a user to update a column of their choice for a specific project of
	 * their choice.
	 * @param scInput: Scanner - Scanner object that obtains user input.
	 * @param statement: Statement - Direct line of connection established to the
	 * PoisePMS database.
	 */
	public static void updateProject(Scanner scInput, Statement statement) {
		
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
					System.out.print("\nEnter the project number of the project you would like to update: ");
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
				System.out.print("\nEnter the name of the project you would like to update: ");
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
		
		String columnToBeUpdated = "";
		
		int columnChoice = 0;
		
		boolean promptColumnChoice = true;
		
		// User keeps being prompted to select the column they wish to update if they do not
		// select a choice from the list.
		while(promptColumnChoice) {
			
			try {
				System.out.print("\nPlease choose from the list of columns you wish to update:"
						+ "\n1. Project name"
						+ "\n2. Building type"
						+ "\n3. Physical address"
						+ "\n4. ERF number"
						+ "\n5. Total fee charged"
						+ "\n6. Total amount paid to date"
						+ "\n7. Deadline date"
						+ "\n8. Architect full name"
						+ "\n9. Architect telephone"
						+ "\n10. Architect email address"
						+ "\n11. Architect physical address"
						+ "\n12. Contractor full name"
						+ "\n13. Contractor telephone"
						+ "\n14. Contractor email address"
						+ "\n15. Contractor physical address"
						+ "\n16. Customer full name"
						+ "\n17. Customer telephone"
						+ "\n18. Customer email address"
						+ "\n19. Customer physical address"
						+ "\n0. Cancel update"
						+ "\n\nType only the number: ");
				
				columnChoice = Integer.parseInt(scInput.nextLine());
				
				// Column to be updated, i.e. the column name that will be used in the SQL statement,
				// is set based on the user's column choice.
				switch(columnChoice) {
				
					case 1:
						columnToBeUpdated = "proj.name";
						promptColumnChoice = false;
						break;
						
					case 2:
						columnToBeUpdated = "proj.building_type";
						promptColumnChoice = false;
						break;
						
					case 3:
						columnToBeUpdated = "proj.physical_address";
						promptColumnChoice = false;
						break;
						
					case 4:
						columnToBeUpdated = "proj.erf_number";
						promptColumnChoice = false;
						break;
						
					case 5:
						columnToBeUpdated = "proj.total_fee_charged";
						promptColumnChoice = false;
						break;
						
					case 6:
						columnToBeUpdated = "proj.total_amount_paid_to_date";
						promptColumnChoice = false;
						break;
						
					case 7:
						columnToBeUpdated = "proj.deadline";
						promptColumnChoice = false;
						break;
						
					case 8:
						columnToBeUpdated = "full_name";
						promptColumnChoice = false;
						break;
						
					case 9:
						columnToBeUpdated = "arch.telephone_number";
						promptColumnChoice = false;
						break;
						
					case 10:
						columnToBeUpdated = "arch.email_address";
						promptColumnChoice = false;
						break;
						
					case 11:
						columnToBeUpdated = "arch.physical_address";
						promptColumnChoice = false;
						break;
						
					case 12:
						columnToBeUpdated = "full_name";
						promptColumnChoice = false;
						break;
						
					case 13:
						columnToBeUpdated = "cont.telephone_number";
						promptColumnChoice = false;
						break;
						
					case 14:
						columnToBeUpdated = "cont.email_address";
						promptColumnChoice = false;
						break;
						
					case 15:
						columnToBeUpdated = "cont.physical_address";
						promptColumnChoice = false;
						break;
						
					case 16:
						columnToBeUpdated = "full_name";
						promptColumnChoice = false;
						break;
						
					case 17:
						columnToBeUpdated = "cust.telephone_number";
						promptColumnChoice = false;
						break;
						
					case 18:
						columnToBeUpdated = "cust.email_address";
						promptColumnChoice = false;
						break;
						
					case 19:
						columnToBeUpdated = "cust.physical_address";
						promptColumnChoice = false;
						break;
						
					case 0:
						promptColumnChoice = false;
						break;
					
					// If the user does not select from any of the choices, an error message
					// is displayed and they are prompted to try again.
					default:
						System.out.println("You did not select an option. Please try again.");
						break;
				}
			}
			catch (NumberFormatException e) {
				// Error message is displayed if the user does not enter an integer.
				System.out.println("You did not enter an integer. Please try again.");
			}
		}
		
		String strValue = ""; // The new value that will update the column in the SQL query in the end.
		
		if(columnChoice == 0) {
			// If the user selects option 0, the update feature is cancelled.
			System.out.println("\nUpdate cancelled!");
		}
		else if(columnChoice == 4) {
			// User selects a column that stores integers.
			
			boolean promptInteger = true;
			
			// User keeps being prompted to enter an integer for the chosen column if they
			// do not enter an integer or do not enter a blank value.
			while(promptInteger) {
				
				try {
					// User prompted to enter an integer.
					System.out.print("\nPlease enter the value (must be an integer): ");
					strValue = scInput.nextLine();
					
					if(strValue.equals("")) {
						// Updated value set to NULL if the user enters a blank value.
						strValue = "NULL";
						promptInteger = false;
					}
					else {
						// String entered by user is converted to an integer.
						Integer.parseInt(strValue);
						promptInteger = false;
					}
					
				}
				catch (NumberFormatException e) {
					// If the user's input cannot be converted to an integer, an error
					// message is displayed.
					System.out.println("You did not enter an integer. Please try again.");
				}
			}
		}
		else if(columnChoice == 5 || columnChoice == 6) {
			// User selects a column that stores doubles/decimals.
			
			boolean promptDouble = true;
			
			// User keeps being prompted to enter an double for the chosen column if they
			// do not enter an double or do not enter a blank value.
			while(promptDouble) {
				
				try {
					// User prompted to enter a double/decimal.
					System.out.print("\nPlease enter the value (must be a decimal): ");
					strValue = scInput.nextLine();
					
					if(strValue.equals("")) {
						// If the user enters a blank value, the updated value is set to NULL.
						strValue = "NULL";
						promptDouble = false;
					}
					else {
						// User's input is converted to a double.
						Double.parseDouble(strValue);
						promptDouble = false;
					}
					
				}
				catch (NumberFormatException e) {
					// If the user's input cannot be converted to a double, an error message
					// is displayed.
					System.out.println("You did not enter a decimal. Please try again.");
				}
				
			}
			
		}
		else if(columnChoice == 7) {
			// User selects a column that stores dates.
			
			boolean promptDate = true;
			
			// User keeps being prompted to enter a date if they do not enter a valid date
			// or a blank value.
			while(promptDate) {
				
				// User is prompted to enter a date.
				System.out.print("\nPlease enter the date (in the format YYYY/MM/DD): ");
				strValue = scInput.nextLine();
				
				if(strValue.equals("")) {
					// If the user enters a blank value, the updated value is set to NULL.
					strValue = "NULL";
					promptDate = false;
				}
				else if(PoisedDateChecker.isDate(strValue)) {
					// Single quotes added around the date to be syntactically added to the SQL
					// query at the end.
					strValue = "'" + strValue + "'";
					promptDate = false;
				}
				else if(!PoisedDateChecker.isDate(strValue)) {
					// If the user does not enter a valid date, an error message is displayed,
					// and they are prompted to try again.
					System.out.println("Invalid date. Please try again.");
				}
				
			}
			
		}
		else {
			// User selects a column that stores strings.
			
			// User prompted to enter a string value.
			System.out.print("\nPlease enter the value: ");
			strValue = scInput.nextLine();
			
			if(strValue.equals("")) {
				// If the user enters a blank value, the updated value is set to NULL.
				strValue = "NULL";
			}
			else {
				// Updated value formatted with single quotes to be syntactically added
				// to the SQL query at the end.
				strValue = "'" + strValue + "'";
			}
			
		}
		
		// If the user did not select 0, i.e. they did not cancel the update, then we run
		// an SQL query.
		if(columnChoice != 0) {
			// SQL stuff
			
			int rowsAffected;
			
			try {
				
				if(columnChoice >= 1 && columnChoice <= 7) {
					// SQL query that will run if the user selects a column that is only
					// from the Projects table.
					
					rowsAffected = statement.executeUpdate("UPDATE Projects proj "
							+ "SET " + columnToBeUpdated + " = " + strValue
							+ " WHERE " + whereClause);
					
					System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
				}
				else if(columnChoice == 8) {
					// SQL query that will run if the user selects the architect name column
					// that is in the Projects table and the Architects table.
					
					ResultSet results;
					
					// Retrieves the architect from the Architects table associated with the
					// project to be updated chosen by the user.
					results = statement.executeQuery("SELECT proj.project_number, arch.full_name "
							+ "FROM Projects proj "
							+ "INNER JOIN Architects arch "
							+ "ON proj.architect_full_name = arch.full_name "
							+ "WHERE " + whereClause);
					
					String currentArchitectFullName = null;
					
					while(results.next()) {
						results.getInt(1); // Project number
						currentArchitectFullName = results.getString(2);
					}
					
					boolean architectAlreadyExists = false;
					
					// Returns the number of architects in the Architects table that have the same
					// name as the name inputted by the user.
					results = statement.executeQuery("SELECT COUNT(*) "
							+ "FROM Architects "
							+ "WHERE " + columnToBeUpdated + " = " + strValue);
					
					int architectDuplicates = 0;
					
					while(results.next()) {
						architectDuplicates = results.getInt(1);
					}
					
					// If there are 1 or more architects already existing in the Architects table
					// that have the same name as the name inputted by the user, we set the boolean
					// to true.
					if(architectDuplicates >= 1) {
						architectAlreadyExists = true;
					}
					
					if(strValue.equals("NULL")) {
						// If the user entered a blank architect name, the architect name only
						// in the Projects table is set to NULL.
						rowsAffected = statement.executeUpdate("UPDATE Projects proj "
								+ "SET proj.architect_" + columnToBeUpdated + " = " + strValue
								+ " WHERE " + whereClause);
						
						System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
					}
					else {
						
						if(currentArchitectFullName == null) {
							
							if(!architectAlreadyExists) {
								// If the project has no associated architect and the updated architect
								// name given by the user already exists in the Architects table, then
								// we insert the new architect name into the Architects table.
								rowsAffected = statement.executeUpdate("INSERT INTO Architects "
										+ "VALUES (" + strValue + ", NULL, NULL, NULL)");
								
								System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
							}
							
							// Architect name updated in the Projects table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "SET proj.architect_" + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
						else {
							// If there is an architect associated with the project, we update the
							// architect name in the Projects table and the Architects table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "INNER JOIN Architects arch "
									+ "ON arch.full_name = proj.architect_full_name "
									+ "SET proj.architect_" + columnToBeUpdated + " = " + strValue
									+ ", arch." + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
						
					}
					
				}
				else if(columnChoice >= 9 && columnChoice <= 11) {
					// SQL query that will run if the user selects a column that is only in
					// the Architects table.
					
					// Column updated in the Architects table that is associated with the chosen project.
					rowsAffected = statement.executeUpdate("UPDATE Projects proj "
							+ "INNER JOIN Architects arch "
							+ "ON arch.full_name = proj.architect_full_name "
							+ "SET " + columnToBeUpdated + " = " + strValue
							+ " WHERE " + whereClause);
					
					System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
				}
				else if(columnChoice == 12) {
					// SQL query that will run if the user selects the contractor name column
					// that is in the Projects table and the Contractors table.
					
					ResultSet results;
					
					// Retrieves the contractor from the Contractors table associated with the
					// project to be updated chosen by the user.
					results = statement.executeQuery("SELECT proj.project_number, cont.full_name "
							+ "FROM Projects proj "
							+ "INNER JOIN Contractors cont "
							+ "ON proj.contractor_full_name = cont.full_name "
							+ "WHERE " + whereClause);
					
					String currentContractorFullName = null;
					
					while(results.next()) {
						results.getInt(1); // Project number
						currentContractorFullName = results.getString(2);
					}
					
					boolean contractorAlreadyExists = false;
					
					// Returns the number of contractors in the Contractors table that have the same
					// name as the name inputted by the user.
					results = statement.executeQuery("SELECT COUNT(*) "
							+ "FROM Contractors "
							+ "WHERE " + columnToBeUpdated + " = " + strValue);
					
					int contractorDuplicates = 0;
					
					while(results.next()) {
						contractorDuplicates = results.getInt(1);
					}
					
					// If there are 1 or more contractors already existing in the Contractors table
					// that have the same name as the name inputted by the user, we set the boolean
					// to true.
					if(contractorDuplicates >= 1) {
						contractorAlreadyExists = true;
					}
					
					if(strValue.equals("NULL")) {
						// If the user entered a blank contractor name, the contractor name only
						// in the Projects table is set to NULL.
						rowsAffected = statement.executeUpdate("UPDATE Projects proj "
								+ "SET proj.contractor_" + columnToBeUpdated + " = " + strValue
								+ " WHERE " + whereClause);
						
						System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
					}
					else {
						
						if(currentContractorFullName == null) {
							
							if(!contractorAlreadyExists) {
								// If the project has no associated contractor and the updated contractor
								// name given by the user already exists in the Contractors table, then
								// we insert the new contractor name into the Contractors table.
								rowsAffected = statement.executeUpdate("INSERT INTO Contractors "
										+ "VALUES (" + strValue + ", NULL, NULL, NULL)");
								
								System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
							}
							
							// Contractor name updated in the Projects table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "SET proj.contractor_" + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
						else {
							// If there is an contractor associated with the project, we update the
							// contractor name in the Projects table and the Contractors table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "INNER JOIN Contractors cont "
									+ "ON cont.full_name = proj.contractor_full_name "
									+ "SET proj.contractor_" + columnToBeUpdated + " = " + strValue
									+ ", cont." + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
						
						
					}
					
				}
				else if(columnChoice >= 13 && columnChoice <= 15) {
					// SQL query that will run if the user selects a column that is only in
					// the Contractors table.
					
					// Column updated in the Contractors table that is associated with the chosen project.
					rowsAffected = statement.executeUpdate("UPDATE Projects proj "
							+ "INNER JOIN Contractors cont "
							+ "ON cont.full_name = proj.contractor_full_name "
							+ "SET " + columnToBeUpdated + " = " + strValue
							+ " WHERE " + whereClause);
					
					System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
				}
				else if(columnChoice == 16) {
					// SQL query that will run if the user selects the customer name column
					// that is in the Projects table and the Customers table.
					
					ResultSet results;
					
					// Retrieves the contractor from the Contractors table associated with the
					// project to be updated chosen by the user.
					results = statement.executeQuery("SELECT proj.project_number, cust.full_name "
							+ "FROM Projects proj "
							+ "INNER JOIN Customers cust "
							+ "ON proj.customer_full_name = cust.full_name "
							+ "WHERE " + whereClause);
					
					String currentCustomerFullName = null;
					
					while(results.next()) {
						results.getInt(1); // Project number
						currentCustomerFullName = results.getString(2);
					}
					
					boolean customerAlreadyExists = false;
					
					// Returns the number of customers in the Customers table that have the same
					// name as the name inputted by the user.
					results = statement.executeQuery("SELECT COUNT(*) "
							+ "FROM Customers "
							+ "WHERE " + columnToBeUpdated + " = " + strValue);
					
					int customerDuplicates = 0;
					
					while(results.next()) {
						customerDuplicates = results.getInt(1);
					}
					
					// If there are 1 or more customers already existing in the Customers table
					// that have the same name as the name inputted by the user, we set the boolean
					// to true.
					if(customerDuplicates >= 1) {
						customerAlreadyExists = true;
					}
					
					if(strValue.equals("NULL")) {
						rowsAffected = statement.executeUpdate("UPDATE Projects proj "
								+ "SET proj.customer_" + columnToBeUpdated + " = " + strValue
								+ " WHERE " + whereClause);
						
						System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
					}
					else {
						
						if(currentCustomerFullName == null) {
							
							if(!customerAlreadyExists) {
								// If the project has no associated customers and the updated customer
								// name given by the user already exists in the Customers table, then
								// we insert the new customer name into the Customers table.
								rowsAffected = statement.executeUpdate("INSERT INTO Customers "
										+ "VALUES (" + strValue + ", NULL, NULL, NULL)");
								
								System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
							}
							
							// Customer name updated in the Projects table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "SET proj.customer_" + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
						else {
							// If there is an customer associated with the project, we update the
							// customer name in the Projects table and the Customers table.
							rowsAffected = statement.executeUpdate("UPDATE Projects proj "
									+ "INNER JOIN Customers cust "
									+ "ON cust.full_name = proj.customer_full_name "
									+ "SET proj.customer_" + columnToBeUpdated + " = " + strValue
									+ ", cust." + columnToBeUpdated + " = " + strValue
									+ " WHERE " + whereClause);
							
							System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
						}
					}
					
				}
				else if(columnChoice >= 17 && columnChoice <= 19) {
					// SQL query that will run if the user selects a column that is only in
					// the Customers table.
					
					// Column updated in the Customers table that is associated with the chosen project.
					rowsAffected = statement.executeUpdate("UPDATE Projects proj "
							+ "INNER JOIN Customers cust "
							+ "ON cust.full_name = proj.customer_full_name "
							+ "SET " + columnToBeUpdated + " = " + strValue
							+ " WHERE " + whereClause);
					
					System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
