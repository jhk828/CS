링크 & 출처 

1. https://wooaoe.tistory.com/45?category=822650
2. https://velog.io/@hygoogi/%EA%B8%B0%EC%88%A0%EB%A9%B4%EC%A0%91-%EC%A4%80%EB%B9%84%ED%95%98%EA%B8%B0
3. https://gyoogle.dev/blog/
4. https://jhnyang.tistory.com/290



# 운영체제

1. Thread란 무엇인가
   - 스레드는 프로세스의 코드, 데이터, 힙 영역을 공유하고 별도의 스택 영역을 갖는다.
2. 멀티 스레드란
   - 하나의 응용 프로그램을 여러 개의 스레드로 구성하고, 각 스레드로 하여금 하나의 작업을 처리하도록 한다.
   - 웹 서버는 대표적인 멀티 스레드 응용 프로그램이다.
3. 멀티 프로세스로 처리 가능한 걸 굳이 멀티 스레드로 하는 이유는?
   - 프로세스를 생성하여 자원을 할당하는 시스템 콜이 감소함으로써 자원의 효율적 관리가 감소함
   - 프로세스 간의 통신 (IPC)보다 스레드 간의 통신 비용이 적다
   - 대신 멀티 스레드 사용 시 공유 자원으로 인한 문제 해결을 위해 동기화에 신경써야 한다.
4. 교착상태 발생 조건
5. 교착상태 처리법
6. 외부 단편화와 내부 단편화란?
   - 외부 단편화: 작업보다 많은 공간이 있지만 실제로 그 작업을 받아들일 수 없는 문제. 메모리 배치에 따라 발생하는 문제
   - 내부 단편화: 작업에 필요한 공간보다 많은 공간을 할당받아서 내부의 사용 불가능한 공간
7. 가상 메모리
   - 각 프로그램에 실제 메모리 주소가 아닌 가상의 메모리 주소를 주는 방식
   - 실제 RAM (주기억장치)보다 큰 메모리 영역 제공
   - 크게 세그먼트 방식과 페이징 방식이 있다.
8. 페이징
   - 페이지 단위의 논리-물리 주소 관리 기법
   - 외부 단편화 해결- 논리 주소 공간이 하나의 연속적 물리 메모리 공간에 들어가면서 발생
   - 주소의 동적 재배치 허용 : 논리 주소 공간을 **동일한 크기**의 페이지로 자르고, 물리적 주소 공간을 프레임으로 잘라서 배치함 -> 변환을 위한 MMU 필요
   - 페이지가 클수록 내부 단편화도 커짐
9. 세그먼테이션
   - 사용자/프로그래머 관점의 메모리 관리 기법
   - 페이징 기법은 같은 크기의 페이지로 미리 나누지만
   - 세그먼테이션은 논리적단위 (세그먼트)로 나누어 메모리를 사용하는 시점에 할당된다.
10. 뮤텍스와 세마포어란, 차이점은?
    1. 세마포어
       - 







# Database

1. Inner Join과 Outer Join이란 무엇이며 어떻게 사용되는가?

2. DB에서 인덱스를 사용하는 이유는?

3. 정규화 작업을 하는 이유는?

   - 데이터가 중복을 줄이기 위해서

   - 데이터 중복 시 삽입, 삭제, 갱신 때 이상 (anomaly)가 발생할 수 있기 때문에

   - 정규화 방법 : 높은 차수의 정규형은 낮은 차수의 정규형을 모두 만족해야 한다.

     - 원 완 이 후 다 조
     - 원자값 완전함수성 이행적함수종속성 후보키 다치종속 조인속성

     ![정규화](https://github.com/jhk828/CS/blob/master/img/%EC%A0%95%EA%B7%9C%ED%99%94.jpeg?raw=true)

4. Anomaly (이상) 현상에 대해 설명하시오

   - 정규화로 해결
   - 갱신 이상: 릴레이션에서 튜플에 있는 속성 값을 갱신할 때 일부 튜플의 정보만 갱신되어 정보에 모순이 생기는 현상 
   - 삽입 이상: 릴레이션에 데이터를 삽입할 때 의도와는 상관없이 원하지 않은 값들도 함께 삽입되는 현상
   - 삭제 이상: 릴레이션에 데이터를 삭제할 때 의도와는 상관없는 값들도 함께 삭제되는 연쇄 삭제 현상

5. 



# Web

1. AJAX란 무엇이며 왜 사용하는지 설명하시오
   - 비동기 통신을 위한 JavaScript 라이브러리 입니다.
   -  **A**synchronous **J**avascript **A**nd **X**ml(비동기식 자바스크립트와 xml)
   - 비동기 통신, 클라이언트와 서버간에 XML 데이터를 주고받는 기술
2. Java 라이브러리란 무엇이고, 정적 라이브러리와 공유 라이브러리의 차이점에 대해 설명하시오.
3. GET 방식과 POST 방식의 차이점을 설명하시오.
4. REST란 무엇이고, RESTful하게 API를 디자인한다는 것은 무엇인지 설명하시오.

   - Restful하게 API를 디자인한다는 것은 URI를 규칙에 맞게 잘 설계했는지의 여부

     1. 동일한 URI(End point)의 행위에 맞게 POST, GET, DELETE, PATCH등의 메소드를 사용한다.
     2. 명사를 사용한다. 리스트를 표현할 때는 복수형을 사용한다.
     3. URI Path에 불필요한 파라미터를 넣지 않는다. 즉, 단계를 심플하게 설계한다.
5. Spring AOP가 무엇인지 OOP와 AOP를 비교하여 설명하시오.
6. Session과 Cookie에 대해 설명하시오



# 자료구조

1. Array(배열)과 List의 차이점은 무엇인지 설명하시오.
2. 
3. 




# Java & OOP

1. 인터페이스와 추상 클래스의 각각의 특징과 차이점을 설명하시오.

2. Call by value와 Call by reference에 대해 설명하시오.

3. 오버로딩과 오버라이딩의 차이에 대해 설명하시오.

4. 객체지향과 절차지향 프로그래밍에 대해 설명하시오.

5. OOP의 특징 4가지

   1. 캡슐화 : 하나의 클래스 안에 데이터와 기능을 담아 정의하고, 중요한 데이터나 복잡한 기능은 숨기고 외부에서 사용에 필요한 기능만 공개하는 것
   2. 상속 : 객체 정의 시 기존에 존재하는 객체의 속성과 기능을 상속받아 정의
   3. 다형성 : 같은 타입 / 같은 기능의 호출로 다양한 효과를 가져오는 것
   4. 추상화 : 현실 세계에 존재하는 객체의 주요 특징을 추출하는 과정

6. Class와 Object의 차이점

   1. Obejct는 시스템의 대상이 되는 모든 것
   2. Class란 구체적인 Object들을 분석하여 공통적인 내용을 추상화하여 프로그래밍 언어로 표현한 것
   3. Class를 만들기 위해 현실 세계의 Object들을 분석하여 분류 (Classify) 한 다음 분류가 끝나면 분류 수 만큼 Class를 만든다. 
   4. Class만의 정적인 특성 (attribute)와 동적인 특성 (behavior)을 파악하여 변수와 methods를 만든다.

7. JVM의 메모리 영역 3가지

   1. Class Area (Method Area)

      - for Class, Static, Method
      - 바이트 코드로부터 클래스의 정보를 읽어들이고 관리하는 영역, 메소드 콜이 되거나 Object를 만들 때의 정보를 저장한다.

   2. Heap

      - for Objects
      - 객체가 실제로 만들어지는 공간. new 하게 되면 멤버 변수, 메소드들이 만들어진다.

   3. Stack

      - for Call
      - method call에 대한, 해당 메소드에 대한 스택 공간이 만들어진다. 메소드가 실행되다가 다른 메소드가 호출되면 다른 스택 공간이 생성되고 메소드가 종료되면 해당 스택이 clear 되고 그전까지 수행되던 메소드의 스택으로 가서 실행한다.

      

8. Garbage Collection

   - Java는 new 연산자로 Memory Allocation을 수행하지만 Deallocation은 JVM이 알아서 처리한다.
   - JVM은 Heap에 만들어진 객체 중에서 더 이상 참조되지 않은 것들을 대상으로 적절한 시점에 Garbage Collecion 작업을 수행한다.
   
9. Java Thread란

   - 일반 스레드와 거의 차이가 없으며 JVM이 운영체제 역할을 한다.
   - 자바에는 프로세스가 존재하지 않고 스레드만 존재하며 자바 스레드는 JVM에 의해 스케쥴링 되는 실행 단위의 코드 블록이다.

10. 컴파일 에러와 런타임 에러

# 디자인 패턴

1. MVC 모델 1과 MVC 모델 2의 차이점에 대해 설명하시오.
   1. MVC란 SW를 세가지 요소로 쪼개어 디자인 하는 것. Model, View, Controller
   2. model1은 view와 logic을 JSP 페이지 하나에서 처리하는 구조다. client로부터 요청이 들어오면 JSP 페이지는 java beans나 별도의 service class를 이용해서 작업을 처리, 결과를 client에 출력한다.
      - 개발 시간이 짧지만 유지보수가 어렵고 확장성 (신기술 도입, framewor 등)이 나쁘다.
   3. model2는, client 요청 처리는 servlet이, logic 처리는 java class (Service, Dao,..), client에게 출력하는 response page를 JSP가 담당한다.
      - MVC 패턴을 웹개발에 도입한 구조이다.
      - 개발 시간이 증가하지만 분업과 유지보수가 용이하고 확장성이 뛰어나다.

# 소프트웨어 공학 & etc

1. Git이 무엇인지 설명하고 프로젝트에서 깃허브를 사용했다면 어떤 식으로 기여했는지 설명하시오.

   - Git은 형상 관리 도구 (=버전 관리 시스템) 중 하나이다. 
   - 형상 관리, 소스가 변화하는 것을 관리하는 것이고 소스를 버전 별로 관리할 수 있어서 소스를 실제로 삭제하거나 이전 버전으로 돌아갈 때 유용합니다.
   - 형상 관리 도구에는 종류가 좀 있는데 분산 저장소 타입 중 하나가 Git입니다.
     - 분산형 관리가 아닌, 중앙 서버에 소스코드와 히스토리를 저장하는 방식은 SVN

2. 프레임워크의 특징과 프레임워크와 라이브러리 차이점은 무엇인지 설명하시오. 

   



