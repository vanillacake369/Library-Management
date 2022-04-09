# Library-Management

## UML

![UML](https://user-images.githubusercontent.com/75259783/162561426-fe7e57f0-f4ab-40ea-b2d5-deee91877c5e.png)


## Pseudo-Code

1. Client Side에서 System.out을 통해 키보드로 입력한 option값을 Server로 출력한다
2. Server에서 option값을 읽어온다
3. 읽어온 option에 따라 DB질의를 시작한다
4. 출력된 결과값,즉 객체를 Client Side에 넘긴다.
5. Client에서 결과값을 읽어온다.
6. Console에 읽어온 결과값을 출력한다.
7. 사용자가 quit를 입력하기 전까지 1~6을 무한정 반복한다.

## 리팩토링/이슈 리스트

1. 관리프로그램 치고는 두 개의 메서드가 전부
2. 스레드풀로 관리 요망 :: Str입출력2개 / Object입출력2개 , 4개의 메소드 돌리고 있음
3. 동기식 접속 (queue와 같은 형식) 을 비동기식 접속으로 만들기
