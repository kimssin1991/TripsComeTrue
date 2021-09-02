package DTO;

public class ClientDTO {
   
   int num;
   String name;
   String pay;
   String days;
   String startdate;
   String finishdate;
   String spot;
   String info;



   public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPay() {
      return pay;
   }

   public void setPay(String pay) {
      this.pay = pay;
   }

   public String getDays() {
      return days;
   }

   public void setDays(String days) {
      this.days = days;
   }

   public String getStartdate() {
      return startdate;
   }

   public void setStartdate(String startdate) {
      this.startdate = startdate;
   }

   public String getFinishdate() {
      return finishdate;
   }

   public void setFinishdate(String finishdate) {
      this.finishdate = finishdate;
   }

   public String getSpot() {
      return spot;
   }

   public void setSpot(String spot) {
      this.spot = spot;
   }

   public String getInfo() {
      return info;
   }

   public void setInfo(String info) {
      this.info = info;
   }

   @Override
   public String toString() {
      return "TabDTO [num=" + num + ", name=" + name + ", pay=" + pay + ", days=" + days + ", startdate=" + startdate
            + ", finishdate=" + finishdate + ", spot=" + spot + ", info=" + info + "]";
   }
}