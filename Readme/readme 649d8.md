# readme

# UML

![UML.png](readme%20649d8/UML.png)

# 유사코드

1. Client Side에서 System.out을 통해 키보드로 입력한 option값을 Server로 출력한다
StrWriter() :: <StringOutputStream Thread화>
2. Server에서 option값을 읽어온다
StrReader() :: <StringInputStream Thread화>
3. 읽어온 option에 따라 DB질의를 시작한다
op a : BookDAO.getBookAll()
op s : BookDAO.getBookTitle()
4. 출력된 결과값,즉 객체를 Client Side에 넘긴다.
op a : ArrayList<BookDTO> -> ListBookDTOWriter() :: ObjectOutputStream Thread화
op a : ArrayList<BookDTO> -> ListBookDTOWriter() :: ObjectOutputStream Thread화
5. Client에서 결과값을 읽어온다.
ListBookDTOReader() :: ObjectInputStream Thread화
6. Console에 읽어온 결과값을 출력한다.
while( BookDTO dto : ArrayList<BookDTO> list ){
System.out.println("title : "+dto.getBookTitle());
...
}
7. 무한루프를 통해 1~6을 반복한다.

# 리팩토링/이슈 리스트

1. 관리프로그램 치고는 두 개의 메서드가 전부
2. 스레드풀로 관리 요망 :: Str입출력2개 / Object입출력2개 , 4개의 메소드 돌리고 있음
3. 동기식 접속 (queue와 같은 형식) 을 비동기식 접속으로 만들기