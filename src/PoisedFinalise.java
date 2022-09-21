import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author Dean Gulston
 * @version 1.0
 *
 */
public class PoisedFinalise {

	/**
	 * Prompts the user to enter a project name or project number for a project
	 * that they want to finalise. When finalised, the finalised status id is updated
	 * to 1, and the user is prompted to enter a finalised date for the project.
	 * @param scInput: Scanner - Scanner object that obtains user input.
	 * @param statement: Statement - Direct line of connection established to the
	 * PoisePMS database.
	 */
	public static void finaliseProject(Scanner scInput, Statement statement) {
		
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
					System.out.print("\nEnter the project number of the project you would like to finalise: ");
					projectNumber = Integer.parseInt(scInput.nextLine());
					
					// The where clause is set to the given project number in preparation
					// for the final SELECT query.
					whereClause = "project_number = " + projectNumber;
					
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
				System.out.print("\nEnter the name of the project you would like to finalise: ");
				projectName = "'" + scInput.nextLine() + "'";
				
				// If the user enters a blank project name, an error message is displayed and
				// they are prompted to try again.
				if(projectName.equals("''")) {
					System.out.println("You did not enter a project name. Please try again.");
				}
			}
			
			// The where clause is set to the given project name in preparation for the
			// final SELECT query.
			whereClause = "name = " + projectName;
		}
		
		String finalisedDate = "''";
		
		boolean promptFinalisedDate = true;
		
		// User will keep being prompted to enter a finalised date until they enter a valid date.
		while(promptFinalisedDate) {
			
			// User prompted to enter the finalised date.
			System.out.print("\nEnter the finalised date (in the format YYYY/MM/DD): ");
			finalisedDate = scInput.nextLine();
			
			if(finalisedDate.equals("''")) {
				// If the user enters a blank string, an error message is displayed and
				// they are prompted to try again.
				System.out.println("You did not enter a date. Please try again.");
			}
			else if(!PoisedDateChecker.isDate(finalisedDate)) {
				// If the user enters a string that is not a valid date, an error message
				// is displayed and they are prompted to try again.
				System.out.println("Invalid date. Please try again.");
			}
			else if(PoisedDateChecker.isDate(finalisedDate)) {
				// If the user enters a valid date, the date is formatted to have single
				// quotes around it and the loop ends.
				finalisedDate = "'" + finalisedDate + "'";
				promptFinalisedDate = false;
			}
		}
		
		int rowsAffected;
		
		try {
			
			// The given project in the Projects table is finalised.
			rowsAffected = statement.executeUpdate("UPDATE Projects "
					+ "SET finalised_status_id = 1, "
					+ "finalised_date = " + finalisedDate
					+ " WHERE " + whereClause);
			
			System.out.println("Query completed: " + rowsAffected + " row(s) affected.");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
