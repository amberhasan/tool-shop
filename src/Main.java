import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter tool code: ");
        String toolCode = scanner.next();

        System.out.print("Enter rental days: ");
        int rentalDays = scanner.nextInt();

        System.out.print("Enter discount percentage: ");
        int discountPercentage = scanner.nextInt();

        System.out.print("Enter checkout date (MM/dd/yyyy): ");
        String checkoutDateStr = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date checkoutDate = null;
        try {
            checkoutDate = dateFormat.parse(checkoutDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM/dd/yyyy.");
            return;
        }

        Tool tool = new Tool(toolCode); // Assuming a constructor in Tool class
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalDays, discountPercentage, checkoutDate);
        rentalAgreement.calculateCharges();
        rentalAgreement.printRentalAgreement();
    }
}
