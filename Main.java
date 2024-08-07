package management;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Seat 클래스 정의
class Seat {
	private Member occupant;

	public void occupy(Member occupant) {
		this.occupant = occupant;
	}
    
	public void vacate() {
		this.occupant = null;
	}
    //좌석 점유 여부 확인
	public boolean isOccupied() {
		return occupant != null;
	}
}

// Room 클래스 정의
class Room {
	Seat[] seats;

	public Room(int numberOfSeats) {
		seats = new Seat[numberOfSeats];
		for (int i = 0; i < numberOfSeats; i++) {
			seats[i] = new Seat();
		}
	}

	public void showSeats() {
		for (int i = 0; i < seats.length; i++) {
			System.out.println("좌석 " + i + ": " + (seats[i].isOccupied() ? "사용중" : "비어있음"));
		}
	}

	public boolean memberEnter(int seatNumber, Member member) {
		if (seats[seatNumber].isOccupied()) {
			return false;
		}
		seats[seatNumber].occupy(member);
		return true;
	}

	public boolean memberExit(int seatNumber) {
		if (!seats[seatNumber].isOccupied()) {
			return false;
		}
		seats[seatNumber].vacate();
		return true;
	}
}

// 메인 클래스 정의
public class Main {
	static Map<String, Member> members = new HashMap<>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Room room = new Room(10); // 10개의 좌석을 가진 방 생성

		// 회원 추가
		members.put("01012345678", new Member("01012345678", "0000", null, 0));
		members.put("01085326852", new Member("01085326852", "swdw6852", null, 0));

		while (true) {
			System.out.println("--------명지 스터디 카페--------");
			System.out.println("[1] 관리자");
			System.out.println("[2] 회원");
			System.out.println("[3] 종료");

			String userType = scanner.next();
			boolean isAdmin = userType.equals("1");

			if (userType.equals("3")) {
				break;
			}

			if (isAdmin) {
				while (true) {
					System.out.println("1. 좌석 보기");
					System.out.println("2. 종료");
					System.out.print("선택: ");
					int adminChoice = scanner.nextInt();

					if (adminChoice == 1) {
						room.showSeats();
					} else if (adminChoice == 2) {
						break;
					} else {
						System.out.println("잘못된 선택입니다.");
					}
				}
			} else {
				System.out.print("아이디 입력: ");
				String id = scanner.next();
				System.out.print("비밀번호 입력: ");
				String password = scanner.next();

				if (authenticate(id, password)) {
					while (true) {
						System.out.println("1. 회원 입장");
						System.out.println("2. 회원 퇴장");
						System.out.println("3. 종료");
						System.out.print("선택: ");
						int userChoice = scanner.nextInt();

						if (userChoice == 1) {
							System.out.print("좌석 번호 입력: ");
							int seatNumber = scanner.nextInt();
							if (seatNumber < 0 || seatNumber >= room.seats.length) {
								System.out.println("잘못된 좌석 번호입니다.");
								continue;
							}
							// 요금제 선택
							System.out.println("요금제를 선택하세요:");
							System.out.println("1. 1시간 - 2000원");
							System.out.println("2. 2시간 - 3000원");
							System.out.println("3. 4시간 - 4000원");
							System.out.print("선택: ");
							int feeChoice = scanner.nextInt();
							int fee = 0;
							switch (feeChoice) {
							case 1:
								fee = 2000;
								break;
							case 2:
								fee = 3000;
								break;
							case 3:
								fee = 4000;
								break;
							default:
								System.out.println("잘못된 선택입니다.");
								continue;
							}

							LocalDateTime startTime = LocalDateTime.now();
							Member member = new Member(id, password, startTime, fee);
							if (room.memberEnter(seatNumber, member)) {
								System.out.println("입장 성공");
								System.out.println("입장 시간: " + startTime);
							} else {
								System.out.println("입장 실패: 이미 사용중인 좌석입니다.");
							}
						} else if (userChoice == 2) {
							System.out.print("좌석 번호 입력: ");
							int seatNumber = scanner.nextInt();
							if (seatNumber < 0 || seatNumber >= room.seats.length) {
								System.out.println("잘못된 좌석 번호입니다.");
								continue;
							}
							if (room.memberExit(seatNumber)) {
								System.out.println("퇴장 성공");
								LocalDateTime now = LocalDateTime.now();
								System.out.println("퇴장 시간: " + now);
							} else {
								System.out.println("퇴장 실패: 사용중인 좌석이 아닙니다.");
							}
						} else if (userChoice == 3) {
							break;
						} else {
							System.out.println("잘못된 선택입니다.");
						}
					}
				} else {
					System.out.println("없는 회원입니다.");
				}
			}
		}

		scanner.close();
	}
    // 회원의 비밀번호와 아이디를 확인(비교)하여 인증을 수행
	public static boolean authenticate(String id, String password) {
		Member member = members.get(id);
		return member != null && member.getPassword().equals(password);
	}
}