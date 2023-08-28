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

    @Test(expected = IllegalArgumentException.class)
    public void testScenarioToolCharge101PercentDiscount() {
        Tool tool = new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid", 2.99);
        Date checkoutDate = createDate("09/03/15");
        RentalAgreement agreement = new RentalAgreement(tool, 5, 101, checkoutDate);

        agreement.calculateCharges();
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

    @Test
    public void testScenarioLadder7DaysNoDiscount() {
        Tool tool = new Tool("LADW", ToolType.LADDER, "Werner", 1.99);
        Date checkoutDate = createDate("07/15/20");
        RentalAgreement agreement = new RentalAgreement(tool, 7, 0, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: LADW\nCheckout date: 07/15/2020\nRental days: 7\nDiscount: 0%\nFinal charge: $13.93";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioChainsaw3Days20PercentDiscount() {
        Tool tool = new Tool("CHNS", ToolType.CHAINSAW, "Stihl", 1.49);
        Date checkoutDate = createDate("09/10/18");
        RentalAgreement agreement = new RentalAgreement(tool, 3, 20, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: CHNS\nCheckout date: 09/10/2018\nRental days: 3\nDiscount: 20%\nFinal charge: $3.58";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioJackhammer2Days5PercentDiscount() {
        Tool tool = new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt", 2.99);
        Date checkoutDate = createDate("06/20/22");
        RentalAgreement agreement = new RentalAgreement(tool, 2, 5, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: JAKD\nCheckout date: 06/20/2022\nRental days: 2\nDiscount: 5%\nFinal charge: $5.68";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

    @Test
    public void testScenarioChainsaw1DayNoDiscount() {
        Tool tool = new Tool("CHNS", ToolType.CHAINSAW, "Stihl", 1.49);
        Date checkoutDate = createDate("12/01/21");
        RentalAgreement agreement = new RentalAgreement(tool, 1, 0, checkoutDate);

        agreement.calculateCharges();
        String expectedOutput = "Tool code: CHNS\nCheckout date: 12/01/2021\nRental days: 1\nDiscount: 0%\nFinal charge: $1.49";
        assertEquals(expectedOutput, agreement.printRentalAgreement());
    }

}
