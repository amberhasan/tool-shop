public class Tool {
    private String toolCode;
    private ToolType toolType;
    private String brand;
    private double dailyCharge;

    public Tool(String toolCode, ToolType toolType, String brand, double dailyCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
    }

    public Tool(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }
}
