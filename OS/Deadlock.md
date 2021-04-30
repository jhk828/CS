# Deadlock (교착상태)

## Deadlock이란

두 개 이상의 프로세스가 이벤트가 발생하기를 무한정 기다리는 것



## Deadlock 원인

한정된 자원을 여러 프로세스가 쓰려고 하기 때문이다.



## 4 conditions (발생 조건)

1. Mutual Exclusion 상호배제 : 한 자원은 한번에 한 프로세스만 사용 가능
2. Hold and Wait 점유 대기: 프로세스는 자원이 할당된 상태에서 다른 자원이 할당되기를 기다린다.
3. No preemption 비선점 : 다른 프로세스가 사용 중인 자원을 선점하여 사용할 수 없다.
4. Circular Wait 환형 대기: A 프로세스가 a 자원을, B 프로세스가 b 자원을, C프로세스가 c 자원을 사용하고 있다면 A는 b룰, B는 c를, C는 a를 할당받기를 기다리고 있다. 
   (자원 A를 가진 프로세스 P1과 자원 B를 가진 프로세스 P2가 있을 때, P1은 B를 필요로 하고 P2는 A를 필요로 한다면 두 프로세스 P1, P2는 서로 자원을 얻기위해 무한정 기다리고 있다.)



## 해결 방법

1. 데드락으로 못 들어가게 하기 : Prevention & Avoidance
2. 데드락에 들어가고 난 후 recover : Detection & Recovery
3. 데드락이 일어나지 않은 척 하기 : Ignore



## 1-1. Deadlock Prevention

> 데드락은 위의 4가지 조건을 모두 충족해야 발생하기 때문에, 4가지 조건 중 하나를 제거해서 데드락을 미리 예방한다.



## 1-2. Deadlock Avoidance

> 교착 상태 가능성을 배제하지 않고 발생하지 않게 피해나간다.

- Bakers Algorithm
  - 프로세스가 자원을 요구할 때 시스템은 자원을 할당한 후에도 안정 상태인지 사전에 검사하여 할당 유무를 결정한다.



## 2-2. Deadlock Detection

> 교착 상태 발생을 허용하고 발생 시 원인을 규명하여 해결

### Resource Allocation State

> Safe, Unsafe, Deadlock

 `deadlock`(circular-wait & deadlock)  ⊂  `unsafe` (circular-wait but no deadlock)  |     `safe`(no circular-wait)

- unsafe state면 데드락 가능성이 있는거고 이 unsafe에 안들어가게 하는게 Avoidance다.



## 2-1. Deadlock Recovery

> 교착 상태 발견 후 환형 대기를 배제시키거나 자원을 중단하여 회복한다.



## 3. Deadlock Ignore

>  위의 방법들이 비용이 많으 드는 경우 무시한다.

