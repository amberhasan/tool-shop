import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private int discountPercentage;
    private Date checkoutDate;
    private double dailyRentalCharge;
    private int chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, int discountPercentage, Date checkoutDate) {
        this.tool = tool;
        this.rentalDays = (rentalDays > 0) ? rentalDays : 0;
        this.discountPercentage = Math.min(100, Math.max(0, discountPercentage));
        this.checkoutDate = checkoutDate;
    }

    private double calculateDiscountAmount(double amount) {
        return amount * (discountPercentage / 100.0);
    }

    public void calculateCharges() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DAY_OF_MONTH, rentalDays);

        dailyRentalCharge = tool.getDailyCharge();
        chargeDays = calculateChargeableDays();
        preDiscountCharge = chargeDays * dailyRentalCharge;
        discountAmount = calculateDiscountAmount(preDiscountCharge);
        finalCharge = preDiscountCharge - discountAmount;
    }

    private int calculateChargeableDays() {
        int chargeableDays = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        for (int i = 0; i < rentalDays; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
            boolean isHoliday = isHoliday(calendar.getTime());
            boolean isChargeableDay = true; // Assume the day is chargeable by default

            // Check tool type-specific charging rules for weekends and holidays
            if (tool.getToolType() == ToolType.LADDER) {
                if (isHoliday) {
                    isChargeableDay = false;
                }
            } else if (tool.getToolType() == ToolType.JACKHAMMER) {
                if (isHoliday || isWeekend) {
                    isChargeableDay = false;
                }
            } else if (tool.getToolType() == ToolType.CHAINSAW) {
                if (isWeekend) {
                    isChargeableDay = false;
                }
            }

            if (isChargeableDay) {
                chargeableDays++;
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return chargeableDays;
    }

    private boolean isHoliday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return ((month == Calendar.JULY && day == 4) || // Independence Day
                (month == Calendar.SEPTEMBER && day <= 7 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)); // Labor Day
    }

    public String printRentalAgreement() {
        StringBuilder output = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        output.append("Tool code: ").append(tool.getToolCode()).append("\n");
        output.append("Checkout date: ").append(dateFormat.format(checkoutDate)).append("\n");
        output.append("Rental days: ").append(rentalDays).append("\n");
        output.append("Discount: ").append(discountPercentage).append("%").append("\n");
        output.append("Final charge: $").append(String.format("%.2f", finalCharge));
        return output.toString();
    }
}
