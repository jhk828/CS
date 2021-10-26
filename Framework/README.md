#### ◼ MVC 모델 1 vs MVC 모델 2

1. MVC란 SW를 세가지 요소로 쪼개어 디자인 하는 것. Model, View, Controller
2. model1은 view와 logic을 JSP 페이지 하나에서 처리하는 구조다. client로부터 요청이 들어오면 JSP 페이지는 java beans나 별도의 service class를 이용해서 작업을 처리, 결과를 client에 출력한다.
   - 개발 시간이 짧지만 유지보수가 어렵고 확장성 (신기술 도입, framewor 등)이 나쁘다.
3. model2는, client 요청 처리는 servlet이, logic 처리는 java class (Service, Dao,..), client에게 출력하는 response page를 JSP가 담당한다.
   - MVC 패턴을 웹개발에 도입한 구조이다.
   - 개발 시간이 증가하지만 분업과 유지보수가 용이하고 확장성이 뛰어나다.

#### ◼ Web Server란?

- 클라이언트의 request(요청)을 받아 **정적**인 컨텐츠(html, css, js)를 response(응답)하는 서버.
- 예) <u>Apache, Nginx</u>, IIS, WebtoB 등

#### ◼ WAS 란?

- Web Application Server
- 클라이언트의 request(요청)을 받아 DB 조회나, 어떤 로직을 처리해야하는 **동적**인 컨텐츠를 response(응답)하는 서버.
- 예) <u>Tomcat</u>, WebLogic, WebSphere, Jeus, JBoss 등
- 대부분의 WAS는 정적인 콘텐츠도 제공해주기 때문에 웹 서버 없이 WAS만 존재할 수 있다.
- 즉, WAS는 웹 서버를 포함하는 개념이다.

#### ◼ Web Container

- Java Servlet과 상호작용하는 WAS의 구성요소
- 서블릿의 생명주기를 관리한다.
- 통신을 지원한다.
- 멀티 스레딩을 지원한다.

#### ◼ Web Container

- 기능은 같지만 역할에 차이가 있다.
- Servlet은 Java 안에 HTML 코드가 들어가서 (하나의 클래스) 웹 기반의 요청에 대한 동적 처리가 가능하도록 Server Side에서 돌아가는 Java 프로그램이다.
- JSP는 HTML 안에 Java 코드가 들어간 형태로 Java 언어 기반의 Server Side 스크립트 언어이다.

# Spring

#### ◼ Spring vs Spring Boot

- Spring은 Java `POJO`를 사용한 엔터프라이즈 애플리케이션 프레임워크
- Spring을 웹 개발을 위해 사용하려면 많은 모듈 설정이 필요하다. 웹 개발을 위해 필요한 설정 시간을 단축해둔게 Spring Boot이다.

#### ◼ POJO

- 평범한 자바 객체라는 뜻
- 클래스 상속, 인터페이스 구현, annotation 사용을 강제하지 않는다.

#### ◼ Spring 핵심 삼각형

1. `IoC` (제어의 역행) : 인스턴스 생성과 생명주기 관리 권한을 사용자가 아니라 스프링 컨테이너로 역행된 것
2. `DI` (의존성 주입) : 각각의 계층이나 서비스 객체들 간의 의존 관계를 프레임워크가 알아서 연결해준다.
3. `AOP` (관점 지향 프로그래밍) : 공통 관심 사항을 구현한 코드를 로직 앞뒤에 실행시키는 것입니다. 로깅, 트랜잭션 기능 등에 사용된다.

#### ◼ Spring MVC 구조 처리 과정

1. `DispatcherServlet` : 모든 요청을 받는 관문
2. `HandlerMapping` : Request url을 어떤 Controller에서 처리할지 연결해준다. 
3. `Controller` :  Request를 처리하고 결과를 다시 DispatcherServlet에게 돌려준다.
4. `ModelAndView` : 결과 View 정보를 담고 있다.
5. `ViewResolver` : 실제 View를 찾아준다.
6. `View` : Controller가 처리한 결과값을 보여줄 View를 생성한다.

#### ◼ Service Logic / Business Logic

- `Service Logic` : 정보의 1차적 처리, 데이터 유효성 검사, 예외처리, 트랜잭션 처리 등 여러 장소에서 공통적으로 요구되는 로직
- `Business Logic` : 정보의 최종 처리, 핵심적인 로직, 데이터베이스 접속이 필요하면 Data Access Layer를 호출한다.

#### ◼ VO vs DTO

- `VO` : 사용되는 값이 객체로 표현되며, 값 변경이 없는 경우
- `DTO` : <u>데이터 전송을 위한 객체</u>, 비즈니스 로직까지 담아서 사용하기도 한다.

#### ◼ Spring Annotation

- `@`를 이용한 주석
- 프로그램 코드의 일부가 아닌 프로그램에 관한 데이터를 제공, 코드에 정보 추가하는 정형화된 방법
- 용도 : `@Override` 처럼 컴파일러에 정보 제공         

#### ◼ Filter vs Interceptor

- `Filter` : 요청과 응답을 거른 뒤 정제하는 역할이다.

  서블렛 필터는 DispatcherServlet 이전에 실행되는데 필터가 동작하도록 자원의 앞단에서 요청내용을 변경하거나 여러가지 체크를 수행하거나, 자원의 처리가 끝난 후 변경하는 처리를 할 수 있다. 보통 web.xml에 등록하고 인코딩 변환, XSS 방어 등의 요청에 대한 처리로 사용된다.

- `Interceptor` : 요청 전후로 가로챈다.

  - 채팅을 위해 WebSocket + STOMP를 사용할 때 StompCommand가 구독, 연결, 연결끊기 중 무엇인지 판단하기 위해 `ChannelInterceptor`을 구현

- 필터는 스프링 컨텍스트 외부에 존재하며 스프링과 무관한 자원에 대해 동작한다.

- 인터셉터는 스프랭의 DispatcherServlet이 컨트롤러를 호출하기 전, 후로 끼어들어 Spring Context(영역) 내부에서 Controller(Handler)dp rhksgks dmdekqdp eogo cjflgksek.

- Filter -> Interceptor -> AOP -> Interceptor -> Filter



# Android

#### ◼ Android 생명주기

1. 최초로 앱을 실행하면 `onCretae()`가 호출된다.
2. `onStart()`가 호출되면서 사용자가 액티비티를 볼 수 있다.
3. 액티비티가 사용자와 상호작용이 가능한 포그라운드에 위치하면 `onResume()`
4. 실행 중이던 액티비티가 포커스를 잃어 사용자와 상호작용이 불가능해지면 `onPause()` 호출
5. 액티비티가 더 이상 보이지 않으면 `onStop()`
6. 액티비티 종료 혹은 앱 프로세스 종료 `onDestroy()`

#### ◼ Android 4대 컴포넌트 

- Activity, Service, Broadcast Receiver, Content Provide
- Activity : UI 화면 담당
- Service : 화면에 존재하지 않고 백그라운드에서 실행되는 컴포넌트

#### ◼ Service 3가지 분류

1. 포어그라운드 서비스 : 알림을 표시해 놓고 사용자와 상호 작용하지 않아도 계속 실행됨
2. 백그라운드 서비스 : 사용자가 직접 알지 못하는 작업 수행, 화면에 당장 보이지는 않지만 실행중이면 백그라운드 상태
3. 바운드 서비스 : 앱 내에서 서비스를 사용하여 간단한 클라이언트 - 서버 환경을 구성 (특정 컴포넌트와 서비스간 상호작용)



## FCM

#### ◼ FCM이란

- Firebase Cloud Messing

- 어플리케이션 서버를 거쳐서 제공하면 받는 사람이 계속 서버에 접속해 있어야 하고 배터리, 네트워크 문제가 발생한다. 따라서 클라우드 메시지 서버를 사이에 따로 둔다.

- 교차 플랫폼이기 때문에 FCM으로 개발하면 기기 (ios, Android, Wdb) 종류에 종속되지 않고 푸시 메시지 전송 가능하다.

- FCM 메시지를 수신하려면 `onMessageReceived`, `onDeleteMessage` 콜백 재정의한다. 
  - 포어그라운드 : 알림과 데이터 모두 `onMessageReceived`에서 받는다.

  - 백그라운드 : 알림 (notification)은 작업 표시줄, 데이터는 `onMessageReceive` 

    - 앱이 백그라운드 상태이면 Android에서 알림 메시지를 작업 표시줄로 전송한다. 사용자가 알림을 탭하면 기본적으로 앱 런처가 열립니다.

      => FCM 메시지 타입을 notification에서 data로 변경하고 안드로이드에서 받을 때도 getData 함수로 접근