# 운영체제

1. Process와 Thread의 차이

   - 프로세스는 메모리에 올리와 실행되고 있는 프로그램

   - 스레드는 프로세스 안에서 실행되는 흐름 단위

   - 프로세스는 최소 하나의 스레드를 보유하고 있으며, 각각 별도의 주소 공간을 독립적으로 할당받는다. (code, data, heap, stack)

     - 프로세스들은 독립적이기 때문에 통신하기 위해 IPC를 사용해야 한다.

   -  스레드는 프로세스의 코드, 데이터, 힙 영역을 공유하고 별도의 스택 영역을 갖는다.

     - Stack을 분리한 이유는 Stack에는 함수의 호출 정보가 저장되는데, Stack을 공유하면 LIFO 구조에 의해 실행 순서가 복잡해지기 때문에 실행 흐름을 원활하게 만들기 위함이다.

     ![Process구조.JPG](https://github.com/jhk828/CS/blob/master/img/Process%EA%B5%AC%EC%A1%B0.JPG?raw=true)

2. Process State와 상태 변환

   1. `new` (생성) : 프로세스의 작업 공간인 메인 메모리에 생성되고 운영 체제 내부에 PCB가 만들어진다. (PCB는 프로세스 실행 정보를 관리하기 위해 생성됨)
   2. `ready`(준비) : 프로세서에(CPU)게 할당 받기를 기다린다.
   3. `running` : 프로세서를 점유하여 명령어들이 실행된다.
   4. `waiting` (대기) :  이벤트 (I/O 종료 등 외부 신호)가 일어나길 기다린다.
   5. `terminated` : 프로세스 수행이 끝난다. 할당된 자원을 OS에 돌려주고 관련 PCB가 삭제된다.

3. Process 상태 변환

   1. `dispatching`: ready 프로세스는 scheduler dispatcher에 의해 프로세서가 부여되어 running 상태가 된다. (ready -> running)

   2. `time run out` : 어떤 프로세스가 프로세서를 독점하는 것을 막기 위해 운영체제는 인터럽트 클록을 두어 지정된 시간 동안만 프로세스가 프로세서를 점유하도록 만든다. (running -> ready)

   3. `block`: running 프로세스가 지정된 시간 이전에 입출력 연산 등을 필요로 할 경우 그 프로세스는 스스로 프로세서를 양도한다. (running-> waiting)

   4. `wakeup` : 입출력 작업이 끝나면 대기 상태의 프로세스는 준비 상태로 변한다. (waiting -> ready)

   ![ProcessState.JPG](https://github.com/jhk828/CS/blob/master/img/ProcessState.JPG?raw=true)

   

4. PCB란

   - Process Control Block, 프로세스 제어 블록
   - 프로세스에 대한 중요한 정보를 저장하고 있으며 프로세스 생성 시 만들어지며 주기억장치에 유지된다.
   - 문맥전환 등 다른 프로세스를 처리해야 할 때 PCB에 현재 상태를 저장하고 그 작업 상태를 불러와 작업 재개가 가능해진다.
   - PID, 상태, 다음 명령어 주소가 저장된다.

5. 멀티 스레드란

   - 하나의 응용 프로그램을 여러 개의 스레드로 구성하고, 각 스레드로 하여금 하나의 작업을 처리하도록 한다.
   - 웹 서버는 대표적인 멀티 스레드 응용 프로그램이다.

6. 멀티 프로세스로 처리 가능한 걸 굳이 멀티 스레드로 하는 이유는?
   - 프로세스를 생성하여 자원을 할당하는 시스템 콜이 감소함으로써 자원의 효율적 관리가 감소함
   - 프로세스 간의 통신 (IPC)보다 스레드 간의 통신 비용이 적다
   - 대신 멀티 스레드 사용 시 공유 자원으로 인한 문제 해결을 위해 동기화에 신경써야 한다.

7. 멀티 프로세싱과 멀티 프로그래밍의 차이는?

   - 멀티 프로세싱은 여러 개의 처리 장치 (CPU)를 장착하여 동시에 여러 작업을 병렬로 실행한다.
   - 멀티 프로그래밍은 다수 개의 프로그램이 같이 주기억장치에 있도록 한 방식
   - CPU 코어의 관점에서 생각
     CPU 코어 여러개로 프로세스를 여러개 수행한다 -> 멀티 프로세싱
     CPU 코어 하나로 프로세스를 여러개 수행한다 -> 멀티 프로그래밍
     CPU 코어 몇 개를 쓰던 간에 작업을 수행한다 (프로세스보다 확장된 의미) -> 멀티 태스킹

8. [CPU 스케줄링](https://github.com/jhk828/CS/blob/master/img/%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81.JPG)

   - 스케줄러란 CPU와 같은 자원을 우선순위에 기반하여 프로세스에 할당하는 방법을 말한다. 그 Queue에는 세 가지가 있다.
   - Job Queue : 현재 시스템 안에서 돌고 있는 프로세스의 집합
   - Ready Queue : 메모리 안에서 CPU의 할당을 기다리는 프로세스의 집합
   - Device Queue : 장치 입출력을 기다리고 있는 프로세스의 집합

9. 스케줄러 종류 세 가지

   - 장기 스케줄러 (잡 스케줄러) : 프로세스가 한꺼번에 많이 올라올 시, 메모리와 디스크 사이에서 메모리에, Ready Queue에 어떤 걸 집어넣을지 결정
   - 단기 스케줄러 (CPU 스케줄러) : CPU와 메모리 사이에서 Ready Queue에 있는 프로세스 중 어떤 것을 CPU 할당을 받게 할지 스케줄링
   - 중기 스케줄러 (Swapper) : 여유공간 부족 시 공간을 만들기 위해 메모리에서 쫓아내어 디스크로 옮김, 동시에 메모리가 많이 올라가는 것을 조절

10. **Context Switching**이란?

   - 인터럽트를 발생시켜 CPU에서 실행중인 프로세스를 중단하고, 다른 프로세스를 처리하기 위한 과정
   - 현재 실행중인 프로세스의 상태 (Context)를 먼저 저장하고, 다음 프로세스를 동작시켜 작업을 처리한 후에 이전에 저장된 프로세스의 상태를 다시 복구한다.

11. 인터럽트란?

    - CPU가 프로세스를 실행하고 있을 때, 입출력 하드웨어 등의 장치나 예외상황이 발생하여 처리가 필요함을 CPU에게 알리는 것
    - 인터럽트 종류
      - I/O Request : 입출력 요청
      - Time Slice Expired : CPU 사용시간 만료
      - Fork Child : 자식 프로세스 생성
      - Wait for interrupt : 인터럽트 처리 대기
    - 컨텍스트 스위칭 시 CPU는 아무런 작업을 하지 못한다. 따라서 잦은 컨텍스트 스위칭은 성능 저하를 일으킴.

12. 뮤텍스와 세마포어란, 차이점은?

    - 뮤텍스는 Locking 메커니즘. 락을 건 쓰레드만이 임계 영역을 나갈 때 락 해제 가능
    - 세마포어는 Signaling 메커니즘. 락을 걸지 않은 쓰레드도 sinal을 사용하여 락 해제 가능
      - 세마포어 카운트 = 1 설정 시 뮤텍스처럼 사용 가능
    - 세마포어는 뮤텍스가 될 수 있지만 뮤텍스는 세마포어가 될 수 없다.
    - 세마포어는 소유 불가능하지만 뮤텍스는 소유가 가능하다.
    - 동기화의 개수가 다르다.

13. Race Condition
    - 두 개의 스레드가 하나의 자원을 놓고 서로 사용하려고 경쟁하는 상황
    - 공유 자원에 대해 여러 프로세스가 동시에 접근할 때, 실행 순서를 조절해주지 않으면 결과값에 영향을 줄 수 있다.

14. 교착상태란?

    - 한정된 자원을 여러 프로세스가 사용하고자 할 때 발생하는 상황
    - 프로레스가 자원을 얻기 위해 영구적으로 기다리는 상태

15. 교착상태 발생 조건

    1. Mutual Exclusion 상호배제 : 한 자원은 한번에 한 프로세스만 사용 가능
    2. Hold and Wait 점유 대기: 프로세스는 자원이 할당된 상태에서 다른 자원이 할당되기를 기다린다.
    3. No preemption 비선점 : 다른 프로세스가 사용 중인 자원을 선점하여 사용할 수 없다.
    4. Circular Wait 환형 대기: 각 프로세스는 순환적으로 다음 프로세스가 요구하는 자원을 갖고 있다.

16. 교착상태 해결법

    1. 예방 : 4가지 조건 중 하나를 제거해서 데드락을 미리 예방
    2. 회피 : 자원이 어떻게 요청될지에 대한 정보를 미리 알아서, 데드락에 빠질 가능성이 있는지 운영체제가 검사를 해서 빠질 가능성이 없을 대만 자원을 할당하여 문제를 회피한다.
       - 은행원 알고리즘
    3. 발견 & 회복 : 교착 상태가 해결될 때 까지 한 프로세스씩 중지
       - 운영체제가 요청하는 자원을 모두 주고 주기적으로 데드락에 빠졌는지를 검사한다. 데드락에 빠진 것을 발견 (detect)하면, deadlock에 빠지기 전 상태로 회복(recover)
       - 강제로 프로세스/ 스레드 들 중 하나를 종료시켜서 강제로 자원 할당을 해제한다.
       - 발견 : 자원 할당 그래프, 복구 : 선점, 프로세스 중지 (희생자 선택)
    4. 무시

17. 메모리 계층 : 캐시 -> 메모리 -> 하드디스크

18. 메모리 할당 알고리즘

    - First fit : 메모리의 처음부터 검사해서 크기가 충분한 첫번째 메모리에 할당
    - Next fit : 마지막으로 참조한 메모리 공간에서부터 탐색을 시작해 공간을 찾음
    - Best fit : 모든 메모리 공간을 검사해서  <u>내부 단편화를 최소화</u>하는 공간에 할당

19. 페이지 교체 알고리즘에 따른 페이지 폴트 방식

    - 페이지 교체 : 가상 메모리를 통해 조회한 페이지는 다시 사용될 가능성이 높다. 페이지 교체를 위해서는 실제 메모리에 존재하는 페이지를 가상 메모리로 저장한 후, 가상 메모리에서 조회한 페이지를 실제 메모리로 로드해야 한다. 그렇다면 어떤 실제 메모리 페이지를 가상 메모리로 희생시킬 것이냐에 대해 다음 알고리즘들로 정한다.
    - OPT : 최적 교체. 앞으로 가장 오랫동안 사용하지 않을 페이지 교체 (실현 가능성 희박)
    - FIFO : 메모리가 할당된 순서대로 페이지를 교체
    - LRU : 최근에 가장 오랫동안 사용하지 않은 페이지를 교체
    - LFU : 사용 빈도가 가장 적은 페이지를 교체
    - NUR : 최근에 사용하지 않은 페이지를 교체

20. 페이지 폴트 

21. 외부 단편화와 내부 단편화란?
    - 외부 단편화: 실행할 작업보다 많은 메모리 공간이 남아있지만 배치할 수 없는 경우 . 메모리 배치에 따라 발생하는 문제 -> 해결: 페이징
    - 내부 단편화: 작업에 필요한 공간보다 많은 공간을 할당받아서 내부의 사용 불가능한 공간 - > 해결 : 세그멘테이션 (가변 분할 할당)

22. 가상 메모리
    - 각 프로그램에 실제 메모리 주소가 아닌 가상의 메모리 주소를 주는 방식
    - 실제 RAM (주기억장치)보다 큰 메모리 영역 제공
    - 크게 세그먼트 방식과 페이징 방식이 있다.

23. 페이징
    - 논리 주소 공간(가상 메모리)를 동일한 크기의 페이지로 자르고, 물리적 주소 공간(RAM)을 동일 크기의 프레임으로 잘라서 배치한다.
    - 외부 단편화 해결: 연속적이지 않은 공간도 활용할 수 있다.
    - 주소의 동적 재배치 허용 : 페이지와 프레임을 대응시키기 위해 page mapping이 필요해서 paging table이 필요하다. (MMU - 메모리 관리장치 필요)
    - 페이지 단위에 알맞게 꽉채워 쓰는게 아니므로 내부 단편화 문제는 여전히 있다. 페이지가 클수록 내부 단편화도 커짐. 그러나 페이징 단위를 작게 하면 page mapping 과정이 많아져 오히려 효율이 떨어진다.

24. 세그먼테이션

    - 내부 단편화 해결

    - 사용자/프로그래머 관점의 메모리 관리 기법
    - 세그먼테이션은 논리적단위 (세그먼트)로 나누어 메모리를 사용하는 시점에 할당된다.
    - 각 세그먼트는 연속적인 공간에 저장되어 있다. 
    - 세그먼트들의 크기가 다르기 때문에 미리 분할해 둘 수 없고 메모리에 적재될 때 빈 공간을 찾아 할당하는 기법이다
    - mapping을 위해 세그먼트 테이블이 필요하며 각 세그먼트 항목별 세그먼트 시작 주소와 길이 정보를 가지고 있다. 
    - 프로세스가 필요한 메모리 만큼 할당해주기 때문에 내부 단편화를 해결할 수 있으나 여전히 중간에 프로세스가 메모리를 해제하면 구멍이 생겨 외부 단편화 문제가 발생한다.



# Database

1. Inner Join과 Outer Join이란 무엇이며 어떻게 사용되는가?

2. DB에서 인덱스를 사용하는 이유는?

   - 검색을 더 빠르게 하기 위해서
   - 데이터와 데이터 위치를 키와 값의 쌍으로 인덱스를 만들어 둔다.
   - 원하는 값을 찾는 것은 빠르지만 새로운 값을 추가하거나 삭제, 수정할 때는 속도가 느려진다. 인덱스는 항상 정렬된 상태를 유지하기 때문이다.

3. 정규화 작업을 하는 이유는?

   - 데이터가 중복을 줄이기 위해서

   - 데이터 중복 시 삽입, 삭제, 갱신 때 이상 (anomaly)가 발생할 수 있기 때문에

   - 정규화 방법 : 높은 차수의 정규형은 낮은 차수의 정규형을 모두 만족해야 한다.

     - 원 완 이 후 다 조
     - 원자값 완전함수성 이행적함수종속성 후보키 다치종속 조인속성

4. 정규화 과정을 설명하시오

   1. 제1정규화 : 각 로우마다 컬럼값이 1개만 있는 원자값
   2. 제2정규화 : 모든 컬럼이 완전 함수 종속을 만족해야 한다. 기본키 중 특정 컬럼에만 종속된 부분적 종속이 없어야 한다.
   3. 제3정규화 : 기본키를 제외한 속석들 간 이행적 함수 종속이 없어야 한다. 기본키 이외의 다른 컬럼이 그 외 다른 컬럼을 결정할 수 없다.

   ![정규화](https://github.com/jhk828/CS/blob/master/img/%EC%A0%95%EA%B7%9C%ED%99%94.jpeg?raw=true)

5. Anomaly (이상) 현상에 대해 설명하시오

   - 정규화로 해결
   - 갱신 이상: 릴레이션에서 튜플에 있는 속성 값을 갱신할 때 일부 튜플의 정보만 갱신되어 정보에 모순이 생기는 현상 
   - 삽입 이상: 릴레이션에 데이터를 삽입할 때 의도와는 상관없이 원하지 않은 값들도 함께 삽입되는 현상
   - 삭제 이상: 릴레이션에 데이터를 삭제할 때 의도와는 상관없는 값들도 함께 삭제되는 연쇄 삭제 현상

6. 트랜잭션이란?

   - 작업의 논리적 단위
   - 작업의 안정성을 보장해준다.
   - 논리적 작업 셋을 완벽하게 처리하지 못할 경우 원 상태로 복구하여 일부만 적용되지 않도록 해준다.

7. 트랜잭션의 특징 - ACID

   1. 원자성 Atomicity : all or nothing. 트랜잭션 연산들은 한꺼번에 완전하게 수행되거나 아니면 어떠한 연산도 수행되지 않아야 한다.
   2. 일관성 Consistency : 트랜잭션이 완료된 후에도 일어나기 전의 상황과 일관성을 유지해야 한다.
   3. 고립성 Isolation : 각각의 트랜잭션은 서로 간섭 없이 독립적으로 수행되어야 한다.
   4. 지속성 Durability : 트랜잭션이 정상적으로 종료된 다음에는 영구적으로 데이터베이스에 결과가 저장되어야 한다.

8. 참조 무결성이란?

   - 외래키 값은 NULL이거나, 참조하는 테이블의 기본키와 동일해야 한다.

9. NoSQL

   - 관계형 데이터 모델 지양, 스키마가 없거나 느슨한 스키마 제공
   - key-value 형식, 문서형, 그래프형
   - 대량의 분산된 데이터 저장, 조회에 특화



# Network & Web

1. AJAX란 무엇이며 왜 사용하는지 설명하시오
   - 비동기 통신을 위한 JavaScript 라이브러리 입니다.
   -  **A**synchronous **J**avascript **A**nd **X**ml(비동기식 자바스크립트와 xml)
   - 비동기 통신, 클라이언트와 서버간에 XML 데이터를 주고받는 기술
   
2. GET 방식과 POST 방식의 차이점을 설명하시오.

4. REST란 무엇이고, RESTful하게 API를 디자인한다는 것은 무엇인지 설명하시오.

   - Restful하게 API를 디자인한다는 것은 URI를 규칙에 맞게 잘 설계했는지의 여부

     1. 동일한 URI(End point)의 행위에 맞게 POST, GET, DELETE, PATCH등의 메소드를 사용한다.
     2. 명사를 사용한다. 리스트를 표현할 때는 복수형을 사용한다.
     3. URI Path에 불필요한 파라미터를 넣지 않는다. 즉, 단계를 심플하게 설계한다.
   
4. Session과 Cookie에 대해 설명하시오
   - HTTML의 비연결성 (Connectionless), 비상태성 (Stateless) 보완
   - 비연결성 : 클라이언트가 서버에 요청(request)을 했을 때, 그 요청에 맞는 응답(response)을 보낸 후 연결을 끊는 처리방식
   - 비상태성 : 클라이언트와 서버가 통신으로 데이터를 주고받아도 두번째 통신에서는 이전 데이터를 유지하지 않는다.
   - 쿠키: 사용자의 웹 브라우저 (클라이언트 PC)에 저장하는 데이터. text 형식으로 저장됨
     - 만료 시점이 지나야 삭제됨, 세션보다 빠름
   - 세션: 웹 서버에 object 형식으로 저장됨. 
     - 브라우저 종료 시 삭제, 쿠키보다 느림
   
5. OSI 7계층
   1. 물리 : 데이터를 전기적 신호로 변환하여 전송
   2. 데이터링크 : Mac주소 변환, 같은 LAN선 안에 있는 컴퓨터끼리 전송
   3. 네트워크 : 라우팅, 다른 LAN선에 있는 컴퓨터에 라우팅을 통해 전송된다.
   4. 전송 : 통신 활성화, TCP/UDP 프로토콜, 포트 열어둠, host간 전송
      - TCP : 신뢰성, 연결 지향적
      - UDP : 비신뢰성, 비연결성, 실시간
   5. 세션 : 논리적 연결 유지 & 해제
   6. 표현 
   7. 응용
   
6. TCP 3 way handshake - 연결 성립 : TCP의 정확한 전송 보장
   1. 클라이언트가 서버에게 접속을 요청하는 `SYN` 패킷 보낸다. 클라이언트는 응답을 기다린다.
   2. 서버가 `SYN` 요청을 받고 요청을 수락한다는 `ACK`와 `SYN`을 보내 클라이언트가 응답하길 기다린다.
   3. 클라이언트가 `ACK`를 보내고 연결이 이루어져 데이터가 오간다. 
   
7. TCP 4 way handshake - 연결 해제
   1. 클라이언트가 연결을 종료하겠다는`FIN` 플래크 전송
   2. 서버가 `FIN`을 받고 확인했다는 `ACK` 클라이언트를 보낸다. 이때 모든 데이터를 보내기 위해 `TIME OUT` 상태가 된다.
   3. 데이터를 모두 보냈다면 연결이 종료되었다는 `FIN`을 클라이언트에게 보낸다.
   4. 클라이언트가 `FIN`을 받고 확인했다는 `ACK`를 보낸다. 아직 서버로부터 받지 못한 데이터가 있을 수 있으므로 클라이언트는 `TIME WAIT`
   5. 서버는 `ACK`를 받은 후 소켓을 닫는다. (`Closed`)
   6. `TIME WAIT` 시간이 끝나면 클라이언트도 닫늗다. (`Closed`)
   
8. TCP 흐름제어/ 혼잡제어

9. HTTP와 HTTPS의 차이 : 백엔드에서 프론트로 데이터를 전송할 때 데이터 암호화의 유무이다.

   - 암호화 방식은 SSL 인증서다.

   

# 자료구조

1. `Stack`: 가장 먼저 넣는 자료가 마지막에 나오는 구조 (LIFO)
   - 웹 브라우저 방문 기록 (뒤로 가기), 후위 표기법 계산, 수식 괄호 검사, 역순 문자열 만들기
   - 원소를 삽입/삭제 : `O(1)` <- 맨 위 원소에만 접근
2. `Queue`: 가장 먼저 넣는 자료가 먼저 나오는 구조 (FIFO)
   - 우선 순위가 같은 작업 예약, 프로세스 관리, 캐시, BFS
   - 원소를 삽입/삭제 : `O(1)`
3. 순환 큐와 선형 큐
   1. 선형 큐:
      1. `Front`고정, `Back` 이동 하며 데이터 삭제 시, 나머지 데이터를 한 칸 씩 다 옮겨야 한다.
      2. 둘 다 이동하며 삭제 시: 배열 끝에 저장된 상황에서는 `Back`을 더 이상 이동할 수 없어 overflow 발생
   2. 순환 큐 (환형 큐) : `front`가 큐의 끝에 닿으면 큐의 맨 앞으로 자료를 보내 원형으로 연결
4. `Heap`:
5. `Deque(Double Ended Queue)`: `front`와 `end`에서 모두 삽입 삭제 가능
   - 연속적 메모리 기반, 임의 접근 반복자 제공 (인덱스로 접근)
   - 크기 가변적
   - 원소 맨/앞 뒤 삽입/ 삭제 : `O(1)`
   - 원소 탐색 : `O(1)` (인덱스로 접근)
   - 앞과 뒤에서 삽입 삭제가 자주 일어나는 경우, 데이터 개수가 가변적일 경우, 데이터 검색을 거의 하지 않을 경우
6. Java Collections

1. Array(배열)과 List의 차이점은 무엇인지 설명하시오.
2. B-Tree
3. Binary Tree




# Java & OOP

1. Java 특징에 대해 설명하시오

   1. JVM을 통해 운영체제에 독립적으로 작동한다.
   2. 기본 자료형을 제외한 모든 요소들이 객체로 표현된다.
   3. 객체 지향 특성인 캡슐화, 상속, 다형성이 잘 적용된 언어다.
   4. Garbage Collection으로 메모리 관리 기능
   5. 멀티 쓰레드 지원

2. Call by value와 Call by reference에 대해 설명하시오.

   Call by value는 인수로 기본 데이터형을 사용하여 주어진 값을 복사하여 처리한다. 메소드 내의 처리 결과는 메소드  밖의 변수에 영향을 주지 않는다.

   Call by reference는 매개변수의 원래 주소값을 저장하는 방식으로 클래스 객체를 인수로 전달하는 경우이다.

3. 오버로딩과 오버라이딩의 차이에 대해 설명하시오.

   오버로딩은 같은 이름의 메소드를 매개변수 타입이나 개수를 다르게 해서 다르게 정의해 사용하는 것이다.

   오버라이딩은 자식클래스가 상속하는 부모클래스의 메소드를 재정의하는 개념이다. 

4. Primitive type과 Reference type

   - Primitive Type은 변수에 값 자체를 저장하는 정수형, 실수형, 문자형, 논리형. Wrapper Class로 객체로 변환 가능하다.
   - Reference Type은 메모리 상에 객체가 있는 위치를 저장한다. Class, Interface, Array 등

5. Wrapper Class란? 

   - 기본 데이터 타입을 객체 취급 (메소드 인수로 객체 타입만 요구될 경우, 자료의 비교, 형변환)

6. Java의 접근 제한자에 대해 설명하시오

   1. public은 같은 프로젝트 내 어디서든 사용 가능하다.
   2. protected는 같은 패키지 내와, 다른 패키지에서 상속 받은 자식 클래스에서 사용 간으하다.
   3. default - 같은 패키지 내에서 사용 가능하다.
   4. private - 같은 클래스 내에서만 사용 가능하다.

7. 객체지향과 절차지향 프로그래밍에 대해 설명하시오.

8. OOP의 특징 4가지

   1. 캡슐화 : 하나의 클래스 안에 데이터와 기능을 담아 정의하고, 중요한 데이터나 복잡한 기능은 숨기고 외부에서 사용에 필요한 기능만 공개하는 것
   2. 상속 : 객체 정의 시 기존에 존재하는 객체의 속성과 기능을 상속받아 정의
   3. 다형성 : 같은 타입 / 같은 기능의 호출로 다양한 효과를 가져오는 것
   4. 추상화 : 현실 세계에 존재하는 객체의 주요 특징을 추출하는 과정

9. Class와 Object의 차이점

   - Obejct는 시스템의 대상이 되는 모든 것

   - Class란 구체적인 Object들을 분석하여 공통적인 내용을 추상화하여 프로그래밍 언어로 표현한 것

   - Class를 만들기 위해 현실 세계의 Object들을 분석하여 분류 (Classify) 한 다음 분류가 끝나면 분류 수 만큼 Class를 만든다. 

   - Class만의 정적인 특성 (attribute)와 동적인 특성 (behavior)을 파악하여 변수와 methods를 만든다.

10. Static 키워드의 의미

   클래스가 로딩될 때 메모리 공간을 할당하는데 처음 설정된 메모리 공간이 변하지 않음을 의미한다.

11. JVM의 메모리 영역 3가지

    1. Class Area (Method Area)

       - for Class, Static, Method

       - 바이트 코드로부터 클래스의 정보를 읽어들이고 관리하는 영역, 메소드 콜이 되거나 Object를 만들 때의 정보를 저장한다.

    2. Heap

       - for Objects

       - 객체가 실제로 만들어지는 공간. new 하게 되면 멤버 변수, 메소드들이 만들어진다.

    3. Stack

       - for Call

       - method call에 대한, 해당 메소드에 대한 스택 공간이 만들어진다. 메소드가 실행되다가 다른 메소드가 호출되면 다른 스택 공간이 생성되고 메소드가 종료되면 해당 스택이 clear 되고 그전까지 수행되던 메소드의 스택으로 가서 실행한다.

12. Garbage Collection

    - Java는 new 연산자로 Memory Allocation을 수행하지만 Deallocation은 JVM이 알아서 처리한다.

    - JVM은 Heap에 만들어진 객체 중에서 더 이상 참조되지 않은 것들을 대상으로 적절한 시점에 Garbage Collecion 작업을 수행한다.

    - 더 이상 사용하지 않는 동적 할당된 메모리 블록을 찾아서 자동으로 다시 사용 가능한 자원으로 회수한다.

13. Java Thread란

    - 일반 스레드와 거의 차이가 없으며 JVM이 운영체제 역할을 한다.

    - 자바에는 프로세스가 존재하지 않고 스레드만 존재하며 자바 스레드는 JVM에 의해 스케쥴링 되는 실행 단위의 코드 블록이다.

14. 컴파일 에러와 런타임 에러

15. 추상 클래스와 인터페이스의 공통점과 차이점

    둘의 공통점은, 상속받는 클래스 / 구현하는 인터페이스 안에 있는 추상 메소드를 구현하도록 강제합니다.

    인터페이스는 추상 메소드와 상수만 멤버로 가지며, 상속 관계가 아닌 클래스들 간에서도 공통된 로직을 구현하여 쓸 수 있습니다. 다중 implements를 지원합니다.

    반면 추상클래스는 추상 메소드를 하나 이상 가진 클래스며 생성자로 객체 생성이 불가능합니다. 하위 클래스를 제어하기 위해 사용되며 클래스이기 때문에 다중 상속이 불가능합니다.

16. Java 프로그램 실행 과정

    1. JVM이 OS로부터 프로그램이 필요로 하는 메모리를 할당받는다.
    2. 자바 바이트 코드로 변환된 .class파일을 class 로더로 JVM에 로딩한니다.
    3. 로딩된 class 파일이 execution engine을 통해 해석되고 실행된다.
    4. 필요시 garbage collection으로 불필요하게 할당된 메모리를 할당한다.

17. JDBC란?

    Java DataBase Connection, Java로 DB에 접근할 수 있는 프로그래밍

# 디자인 패턴 & FrameWork 

1. MVC 모델 1과 MVC 모델 2의 차이점에 대해 설명하시오.
   1. MVC란 SW를 세가지 요소로 쪼개어 디자인 하는 것. Model, View, Controller
   2. model1은 view와 logic을 JSP 페이지 하나에서 처리하는 구조다. client로부터 요청이 들어오면 JSP 페이지는 java beans나 별도의 service class를 이용해서 작업을 처리, 결과를 client에 출력한다.
      - 개발 시간이 짧지만 유지보수가 어렵고 확장성 (신기술 도입, framewor 등)이 나쁘다.
   3. model2는, client 요청 처리는 servlet이, logic 처리는 java class (Service, Dao,..), client에게 출력하는 response page를 JSP가 담당한다.
      - MVC 패턴을 웹개발에 도입한 구조이다.
      - 개발 시간이 증가하지만 분업과 유지보수가 용이하고 확장성이 뛰어나다.
   
2. 프레임워크의 특징과 프레임워크와 라이브러리 차이점은 무엇인지 설명하시오. 

3. Spring vs Spring Boot

   - Spring을 웹 개발을 위해 사용하려면 많은 모듈 설정이 필요하다. 웹 개발을 위해 필요한 설정 시간을 단축해둔게 Spring Boot이다.

4. 스프링 핵심 삼각형

   IoC는 제어의 역행이란 뜻으로, 인스턴스  생성과 생명주기 관리 권한을 사용자가 아니라 스프링 컨테이너로 역행된 것입니다.

   DI란 빈 설정 정보로 객체의 의존 관계를 알아서 연결해주는 개념입니다.

   AOP란 관점 지향 프로그래밍이란 뜻으로, 공통 관심 사항을 구현한 코드를 로직 앞뒤에 실행시키는 것입니다. 로깅, 트랜잭션 기능 등에 사용됩니다.

5. 웹 서버란?

   - 작성된 HTML 페이지가 네트워크에 종속되지 않고 웹 서비스 되도록 해준다. 정적 컨텐츠에만 응답한다. 
   - Apache, Nginx 등

6. WAS (Web Application server)

   - WAS는 웹 서버와 웹 컨테이너를 합친 개념으로, 동적 서버 콘텐츠 수행(DB 조회, 로직 처리 등 )이 가능하다는 것이 웹 서버와의 가장 큰 차이점이다.

7. Servlet vs JSP (Web Container)

   기능은 같지만 역할에 차이가 있다.

   Servlet은 Java 안에 HTML 코드가 들어가서 (하나의 클래스) 웹 기반의 요청에 대한 동적 처리가 가능하도록 Server Side에서 돌아가는 Java 프로그램이다.

   JSP는 HTML 안에 Java 코드가 들어간 형태로 Java 언어 기반의 Server Side 스크립트 언어이다.

8. Spring AOP란?

   ​	AOP는 여전히 반복되는 중복 코드를 해결하고자 트랜잭션, 보안, 로깅 등을 횡단관심사로 분리하여 비즈니스 로직 앞 뒤 등 원하는 지점에 수행할 수 있도록 하는 것을 의미한다.

9. POJO란

10. 모듈이란

11. Spring MVC 구조 처리 과정
    1. DispatcherServlet : 어플리케이션으로 들어오는 모든 Request를 받아서 처리할 수 있도록 Controller에 전달하고 결과값을 View에게 전달한다.
    2. HandlerMapping : Request URL이 각각 어떤 Controller로 처리될 것인지 찾아준다.
    3. ModelAndView : Controller 처리 결과와 결과를 보여줄 View에 관한 정보를 담고있는 객체이다.
    4. ViewResolver : View 관련 정보를 갖고 실제 View를 찾아준다.
    5. View : Controller가 처리한 결과값을 보여줄 View를 생성한다.

12. Android 생명 주기

13. Android Background / Foreground

    - 화면에 보이진 않지만 앱이 계속 실행중이면 백그라운드 상태다. 포그라운드는 지금 실행 중이라서 앱이 어떤 작업을 하고 있는지 눈으로 확인할 수 있는 상태다.

14. 서버와 클라이언트란

    - 서버는 서비스를 제공하고 클라이언트 컴퓨터의 요청을 처리하기 위해 존재한다. 비즈니스 로직을 수행한다.
    - 클라이언트는 서버에 접속하기 위한 접속 단말기로, 서버에게 자료를 request하고 response로 제공받는다.

15. FCM이란? 

    - Firebase Cloud Messing
    - 어플리케이션 서버를 거쳐서 제공하면 받는 사람이 계속 서버에 접속해 있어야 하고 배터리, 네트워크 문제가 발생한다. 따라서 클라우드 메시지 서버를 사이에 따로 둔다.
    - 교차 플랫폼이기 때문에 FCM으로 개발하면 기기 (ios, Android, Wdb) 종류에 종속되지 않고 푸시 메시지 전송 가능하다.



# 소프트웨어 공학

1. Git이 무엇인지 설명하고 프로젝트에서 깃허브를 사용했다면 어떤 식으로 기여했는지 설명하시오.

   - Git은 형상 관리 도구 (=버전 관리 시스템) 중 하나이다. 
   - 형상 관리, 소스가 변화하는 것을 관리하는 것이고 소스를 버전 별로 관리할 수 있어서 소스를 실제로 삭제하거나 이전 버전으로 돌아갈 때 유용합니다.
   - 형상 관리 도구에는 종류가 좀 있는데 분산 저장소 타입 중 하나가 Git입니다.
   - 분산형 관리가 아닌, 중앙 서버에 소스코드와 히스토리를 저장하는 방식은 SVN

2. 애자일 (Agile)

   



# etc..

1. RNN (Recurrent Neural Network) 이란?

   - 순차적으로 동작하는 데이터 처리에 적합한 모델, 직전의 정보/ 단기 기억만 이용

   - 이전 셀의 출력 (Recurerent)과 입력(Input)이 합쳐져 출력(output)이 나온다. <- tanh
     - (전 셀로부터의 리커런트 정보(기억 정보)를 그대로 흘려보내는 것이 아니라, tanh이 요점을 잘 정리해준다는 느낌)

2. [LSTM (Long Short-Term Memory)이란?](https://doooob.tistory.com/162)

   - 순차적으로 동작하는 데이터 처리에 적합한 모델이다.
   - 이전 정보가 현재 입력 정보와 합쳐져서 해석하는 RNN 모델이 있는데 직전 정보만 활용한다. LSTM은 이를 개선하여 장기 의존 학습이 가능하며, 단어, 문장 등 길고 순서가 있는 데이터 학습에 적합하다.
   - 데이터 개수인 batch_size, 프레임 길이인 timesteps, 한 프레임 당 거리값 개수인 input_dim 순서로 3차원으로 입력한다.

3. CNN이란?

   - 이미지 분류를 위해 인공지능에 많이 사용되는 방법이다.
   - 이미지 분류에 도움이 될 만한 특징을 추출하고, 필터를 거친 특징 중 가장 중요한 특징을 골라서 분류한다.

4. 퍼셉트론이란?

   - 딥러닝의 기원이 되는 알고리즘으로, 다수의 신호를 입력받아 하나의 신호를 출력한다.

5. Pretrained Netwok

   - 이미 학습되어 있는 model을 이용하여 수정하여 사용한다. 이전에 대규모 데이터 셋에서 훈련된 모델/네트워크를 저장하여 그대로 사용하거나 재정의(미세조정)하여 사용한다.

6. One-Hot Encoding이란

   - 문자를 숫자로 바꾸는 기법, 표현하고 싶은 단어 인덱스는 1, 다른 인덱스를 0으로 부여한다.

7. Tensorflow Lite란?

   - Tensorflow 모델을 모바일, IoT 기기에서 사용할 수 있도록 경량화 시킨 버전

8. 머신러닝  vs 딥러닝 차이

   머신러닝이란 AI 구현의 방법론 중 하나다. 데이터의 특성과 패턴을 학습해서 미지의 데이터에 대한 미래 결과를 예측한다. Regression (Linear/ Logistic), SVM, Random Forest, Decision Tree, Neural Network, Clustering, 강화 학습 등이 있다.

   머신러닝은 지도학습과 비지도 학습으로 나뉜다. 지도학습은 입력값 x에 정답 t를 포함하여 학습시켜 미지 데이터의 미래 값을 예측한다. 지도학습에는 회귀 (연속적 숫자값 예측), 분류 (Classification / 어떤 것인가를 예측) 이 있다. 비지도 학습은 label이 없는 데이터를 사용하여 군집화(Clustering/ 비슷한 데이터 끼리 묶는다.), 분류(Grouping/ 어떤 분류에 속하는가) 가 있다.

   딥러닝은 머신 러닝 구현의 한 방법인 신경망을 이용하는 방법으로 CNN, RNN, LSTM, GAN 등이 있다.

9. 데이터 마이닝이란 : 데이터 간 상관관계나 새로운 속성 (feature)를 찾는 기법

10. [SVM (Support Vector Machine) 이란](https://jhk0307.tistory.com/112)

   분류에 적용 가능한 머신 러닝 기법이다. 결정 경계(분류를 나누는 선)를 기준으로 예측하고자 하는 값이 어느 경계에 들어가느냐를 파악한다.





----

출처 

1. https://wooaoe.tistory.com/45?category=822650
2. https://velog.io/@hygoogi/%EA%B8%B0%EC%88%A0%EB%A9%B4%EC%A0%91-%EC%A4%80%EB%B9%84%ED%95%98%EA%B8%B0
3. https://gyoogle.dev/blog/
4. https://jhnyang.tistory.com/290
5. https://mangkyu.tistory.com/92?category=761303
6. https://hyonee.tistory.com/95
7. https://velog.io/@hygoogi/%EA%B8%B0%EC%88%A0%EB%A9%B4%EC%A0%91-%EC%A4%80%EB%B9%84%ED%95%98%EA%B8%B0
8. https://jeong-pro.tistory.com/91
9. https://doooob.tistory.com/162
10. https://velog.io/@choiiis/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EC%8A%A4%ED%83%9DStack%EA%B3%BC-%ED%81%90Queue



