package modele;

public class Date {
    private int DayOfMonth;
    private int monthOfYear;
    private int year;

    public Date(int DayOfMonth,int monthOfYear,int year){
        this.DayOfMonth=DayOfMonth;
        this.monthOfYear=monthOfYear;
        this.year=year;
    }

    public void setDayOfMonth(int dayOfMonth) {DayOfMonth = dayOfMonth;}
    public void setMonthOfYear(int monthOfYear) {this.monthOfYear = monthOfYear;}
    public void setYear(int year) {this.year = year;}

    @Override
    public String toString(){
        return DayOfMonth+"/"+monthOfYear+"/"+year;
    }
}
