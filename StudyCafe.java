package management;

import java.util.ArrayList;

public class StudyCafe {
    
    String name = "명지 스터디 카페"; // 스터디카페 이름
    
    ArrayList<Member> memberList; // 회원 목록
    Fee[] feeTable; // 요금표
    
    
    // 생성자
    public StudyCafe(String name, int partitionedSeatRows, int partitionedSeatCols) {
        this.name = name;
        
        this.memberList = new ArrayList<>();
        this.feeTable = new Fee[3]; // 3가지 요금제
        
      //요금제 초기화
        this.feeTable[0] = new Fee("1시간", 2000);
        this.feeTable[1] = new Fee("2시간", 3000);
        this.feeTable[2] = new Fee("4시간", 4000);
    }
    
   
    }


class Fee {
    String kind; // 요금 종류
    int pay; // 요금
    
    // 생성자
    public Fee(String kind, int pay) {
        this.kind = kind;
        this.pay = pay;
    }
}

