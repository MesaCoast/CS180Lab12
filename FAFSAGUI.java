import javax.swing.JOptionPane;

public class FAFSAGUI{
	public static void main(String[] args){
		JOptionPane j = new JOptionPane();
		boolean cont = true;
		
		while(cont){
			j.showMessageDialog(null, "Welcome to the FAFSA!", "Welcome", j.INFORMATION_MESSAGE);

			boolean isAcceptedStudent = j.showConfirmDialog(null, "Have you been accepted into a degree or certificate program?", "Program Acceptance", j.YES_NO_OPTION, j.QUESTION_MESSAGE) == j.YES_OPTION;

			boolean isSSregistered = j.showConfirmDialog(null, "Are you registered for the selective service?", "Selective Service", j.YES_NO_OPTION, j.QUESTION_MESSAGE) == j.YES_OPTION;

			boolean hasSSN = j.showConfirmDialog(null, "Do you have a social security number?", "Social Security Number", j.YES_NO_OPTION, j.QUESTION_MESSAGE) == j.YES_OPTION;

			boolean hasValidResidency = j.showConfirmDialog(null, "Do you have valid residency status?", "Residency Status", j.YES_NO_OPTION, j.QUESTION_MESSAGE) == j.YES_OPTION;

			int age = Integer.parseInt(j.showInputDialog(null, "How old are you?", "Age", j.QUESTION_MESSAGE));

			while(age < 0){
				j.showMessageDialog(null, "Age cannot be a negative number.", "Error: Age", j.ERROR_MESSAGE);
				age = Integer.parseInt(j.showInputDialog(null, "How old are you?", "Age", j.QUESTION_MESSAGE));
			}

			int creditHours = Integer.parseInt(j.showInputDialog(null, "How many credit hours do you plan on taking?", "Credit Hours", j.QUESTION_MESSAGE));

			while((creditHours < 1)||(creditHours > 24)){
				j.showMessageDialog(null, "Credit hours must be between 1 and 24, inclusive.", "Error: Credit Hours", j.ERROR_MESSAGE);
				creditHours = Integer.parseInt(j.showInputDialog(null, "How many credit hours do you plan on taking?", "Credit Hours", j.QUESTION_MESSAGE));
			}

			int studentIncome = Integer.parseInt(j.showInputDialog(null, "What is your total yearly income?", "Student Income", j.QUESTION_MESSAGE));

			while(studentIncome < 0){
				j.showMessageDialog(null, "Income cannot be a negative number.", "Error: Student Income", j.ERROR_MESSAGE);
				studentIncome = Integer.parseInt(j.showInputDialog(null, "What is your total yearly income?", "Student Income", j.QUESTION_MESSAGE));
			}

			int parentIncome = Integer.parseInt(j.showInputDialog(null, "What is your parent's total yearly income?", "Parent Income", j.QUESTION_MESSAGE));

			while(parentIncome < 0){
				j.showMessageDialog(null, "Income cannot be a negative number.", "Error: Parent Income", j.ERROR_MESSAGE);
				parentIncome = Integer.parseInt(j.showInputDialog(null, "What is your parent's total yearly income?", "Sudent Income", j.QUESTION_MESSAGE));
			}

			boolean isIndependent = j.showConfirmDialog(null, "Are you a dependent?", "Dependency", j.YES_NO_OPTION, j.QUESTION_MESSAGE) != j.YES_OPTION;

			String classStanding;
			String[] levels = new String[]{"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};

			if(j.showInputDialog(null, "What is your current class standing?", "Class Standing", j.PLAIN_MESSAGE, null, levels, "Freshman").equals("Graduate"))
				classStanding = "Graduate";
			else
				classStanding = "Undergraduate";
		
			FAFSA f = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN, hasValidResidency, !(isIndependent), age, creditHours, studentIncome, parentIncome, classStanding);

			double loan = f.calcStaffordLoan();
			double grant = f.calcFederalGrant();
			double workStudy = f.calcWorkStudy();
			double total = loan + grant + workStudy;

			j.showMessageDialog(null, "Loans: $" + loan +"\nGrants: $" + grant + "\nWork Study: $" + workStudy + "\n__________\nTotal: $" + total, "FAFSA Results", j.INFORMATION_MESSAGE);

			cont = j.showConfirmDialog(null, "Would you like to complete another Application?", "Continue", j.YES_NO_OPTION, j.QUESTION_MESSAGE) == j.YES_OPTION;
		}
	}
}
