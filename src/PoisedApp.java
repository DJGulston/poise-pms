import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 
 * @author Dean Gulston
 * @version 1.0
 *
 */
public class PoisedApp {

	/**
	 * Poised application that allows a user to find projects, add new projects, update existing
	 * projects, finalise projects, find incomplete projects and find overdue projects.
	 * @param args - No arguments.
	 */
	public static void main(String[] args) {
		
		try {
			// Establishes a connection to the PoisePMS database via the jdbc:mysql channel on localhost.
			// Connection established using the username 'otheruser' and password 'swordfish'.
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
					"otheruser","swordfish");
			
			// Direct line of connection to the PoisePMS database where queries can be sent.
			Statement statement = connection.createStatement();
			
			
			Scanner scInput = new Scanner(System.in);
			
			boolean continueRunning = true;
			
			// Program keeps running until the types 0 to exit the program.
			while(continueRunning) {
				
				// User prompted to select from the options.
				System.out.print("\nWelcome to the Poised App! Please select an option below:"
						+ "\n1. Add a new project"
						+ "\n2. Update an existing project"
						+ "\n3. Finalise an existing project"
						+ "\n4. View incomplete/unfinalised projects"
						+ "\n5. View overdue projects"
						+ "\n6. Find a project"
						+ "\n0. Exit the program"
						+ "\n\nType only the number: ");
				
				try {
					// Gets input from user and converts it to an integer.
					int userChoice = Integer.parseInt(scInput.nextLine());
					
					switch(userChoice) {
						// If the user types 0, the loop ends and the program closes.
						case 0:
							System.out.println("\nProgram closed!");
							continueRunning = false; // Loop ends.
							break;
							
						// If the user types 1, the user is prompted to add a project to the PoisedPMS
						// database.
						case 1:
							PoisedAdd.addProject(scInput, statement);
							break;
							
						// If the user types 2, the user is prompted to update an existing project of
						// their choice.
						case 2:
							PoisedUpdate.updateProject(scInput, statement);
							break;
							
						// If the user types 3, the user is prompted to finalise a project of their
						// choice.
						case 3:
							PoisedFinalise.finaliseProject(scInput, statement);
							break;
							
						// If the user types 4, all incomplete projects are displayed, i.e. not yet
						// finalised.
						case 4:
							PoisedIncompleteProjects.getIncompleteProjects(scInput, statement);
							break;
							
						// If the user types 5, all overdue projects are displayed.
						case 5:
							PoisedOverdueProjects.getOverdueProjects(scInput, statement);
							break;
							
						// If the user types 6, the user is prompted to search for any project of their
						// choice.
						case 6:
							PoisedFind.findProject(scInput, statement);
							break;
							
						// If the user types any number that is not 0, 1, 2, 3, 4, 5 or 6, they are
						// asked to try again.
						default:
							System.out.println("You did not select an option. Please try again.");
							break;
					}
				}
				catch(NumberFormatException e) {
					// If the user does not type an integer, an error message is displayed and they
					// are asked to try again.
					System.out.println("You did not enter an integer. Please try again.");
				}
			}
			
			scInput.close();
			
			
			// Closes all connections.
			statement.close();
			connection.close();
			
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	

}

/*
 * References:
 * 
 * How to update tables while joining them together:
 * - https://stackoverflow.com/questions/15481505/sql-update-after-joining-two-tables
 * - https://stackoverflow.com/questions/12802662/how-to-use-table-aliases-with-mysql-update
 * 
 * How to use aliases for tables in SQL joins:
 * - https://stackoverflow.com/questions/10724324/mysql-inner-join-alias
 * - https://stackoverflow.com/questions/12802662/how-to-use-table-aliases-with-mysql-update
 * 
 * Update multiple columns at once in a single SQL statement:
 * - https://www.geeksforgeeks.org/how-to-update-multiple-columns-in-single-update-statement-in-sql/
 * 
 * How to get a maximum value in a column:
 * - https://learnsql.com/cookbook/how-to-find-the-maximum-value-of-a-numeric-column-in-sql/
 * 
 * How to determine a leap year:
 * - https://docs.microsoft.com/en-us/office/troubleshoot/excel/determine-a-leap-year
 * 
 * How to get current date in MySQL:
 * - https://www.w3schools.com/sql/func_mysql_now.asp
 * 
 */
