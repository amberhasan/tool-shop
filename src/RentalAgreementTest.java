import static org.junit.Assert.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalAgreementTest {

    private Date createDate(String dateString) {
        try {
            return new SimpleDateFormat("MM/dd/yy").parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to create date.", e);
        }
    }

    @Test
    public void testScenarioToolCharge101PercentDiscount() {
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", 2.99);
        Date checkoutDate = createDate("09/03/15");
        RentalAgreement agreement = new RentalAgreement(tool, 5, 101, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: JAKR\nCheckout date: 09/03/2015\nRental days: 5\nDiscount: 100%\nFinal charge: $0.00";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioLadder3Days10PercentDiscount() {
        Tool tool = new Tool("LADW", ToolType.LADDER, "Werner", 1.99);
        Date checkoutDate = createDate("07/02/20");
        RentalAgreement agreement = new RentalAgreement(tool, 3, 10, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: LADW\nCheckout date: 07/02/2020\nRental days: 3\nDiscount: 10%\nFinal charge: $3.58";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioChainsaw5Days25PercentDiscount() {
        Tool tool = new Tool("CHNS", ToolType.CHAINSAW, "Stihl", 1.49);
        Date checkoutDate = createDate("07/02/15");
        RentalAgreement agreement = new RentalAgreement(tool, 5, 25, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: CHNS\nCheckout date: 07/02/2015\nRental days: 5\nDiscount: 25%\nFinal charge: $3.35";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioJackhammer6DaysNoDiscount() {
        Tool tool = new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt", 2.99);
        Date checkoutDate = createDate("09/03/15");
        RentalAgreement agreement = new RentalAgreement(tool, 6, 0, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: JAKD\nCheckout date: 09/03/2015\nRental days: 6\nDiscount: 0%\nFinal charge: $8.97";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioJackhammer9DaysNoDiscount() {
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", 2.99);
        Date checkoutDate = createDate("07/02/15");
        RentalAgreement agreement = new RentalAgreement(tool, 9, 0, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: JAKR\nCheckout date: 07/02/2015\nRental days: 9\nDiscount: 0%\nFinal charge: $20.93";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioJackhammer4Days50PercentDiscount() {
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", 2.99);
        Date checkoutDate = createDate("07/02/20");
        RentalAgreement agreement = new RentalAgreement(tool, 4, 50, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: JAKR\nCheckout date: 07/02/2020\nRental days: 4\nDiscount: 50%\nFinal charge: $2.99";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }
}
