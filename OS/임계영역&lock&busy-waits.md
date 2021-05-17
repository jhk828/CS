공유되는 자원, 즉 동시접근하려는 자원에서 문제가 발생하지 않게 독점을 보고한다.

- 임계영역에는 반드시 한순간 한 프로세스만 진입해야 한다.
- 임계영역은, 프로그램에서 임계 자원을 이용하는 부분으로, 공유 자원의 독점을 보장하는 코드 영역이다 임계 구역은 지정된 시간이 지난 후 종료된다.
- 병렬 컴퓨팅에서 둘 이상의 스레드가 동시에 접근해서는 안 되는 공유 자원에 접근하는 코드의 일부로도 쓰인다.
- 스레드가 공유 자원의 배타적인 사용을 보장받기 위해 임계자원에 들어가거나 나올 때 세마포어와 같은 동기화 메커니즘이 필요하다.

# Lock

내가 자원을 사용하고 있는 동안 문을 걸어잠궈서 나 말고는 들어오지 못하게 한다.

## critical section

```c
int withdraw(account, amount) { // 해당 account에서 amount만큼 인출
    balance = get_balance(account); // 계좌에서 현재 잔액을 가져옴
    balance = balance - amount; // 남은 잔액은 기존 잔액에서 amount만큼 뺀 금액
    put_balance(account, balance); // account에 변경된 잔액을 갱신함
    return balance;
}

// 1. get_balnce, put_balance 함수가 사용하는 account의 데이터 배이스가 shared_data(공유자원)이다.
// 2. shared_data를 둘 이상의 스레드가 동시에 접근 (access)하려는 상황 -> race condition!
// 3. 이 race condition을 유발시키는 code segment의 영역이 critical section이다.

```



## Lock

`critical section`에 한 번에 한 명씩 들어가게끔 하려면, 들어갈 때 Lock을 걸고 (걸어 잠그고) 나올 때 Lock을 풀어주는 것이 `Lock` 방식이다.

```c
int withdraw(account, amount) { 
    lock(lock); // 1. 들어갈 때 잠근다.
    balance = get_balance(account); // 2.
    balance = balance - amount; // 3.
    put_balance(account, balance); // 4.
    unlock(lock); // 5. 나올 때 푼다.
    return balance;
}
```

1. 스레드A가  수행되며 먼저 lock을 건다.
2. 수행 중 time interrupt가 걸렸다고 가정한다.
3. interrupt에 의해 스레드 B가 수행되는데, lock을 걸려고 해도 이미 걸려있다. 따라서 lock에서 멈추어서 스레드 A가 lock을 해제할 때 까지 기다린다.
4. 운영체제 스케줄링에 의해 다시 스레드 A가 수행된다.
5. 스레드 A가 마저 함수를 실행하며 lock을 해제하면 그제서야 스레드 B가 수행된다.



스레드 대신 두 개의 프로세스가 shared data에 접근하는 경우도 마찬가지다.

프로세스는 shared memory 말고 messaging passing 방법도 있다.



## Lock 함수 구현 & spinlocks (=busy-waits)

```c
struct lock {int held = 0; }
// held가 1이면 자원을 쥐고 있다.
// lock이 걸려있지 않은 초기값은 0

void lock(struct lock *l) {
    // held가 1이면 lock이 풀릴 때 까지 무한루프를 돈다. -> spinlocks (=busy-waits)
    
    while(l -> held); 
    // unlock function을 다른 스레드가 호출해주면 held=0이 되면서 spinlock에서 스레드가  빠져나온다.
    
    l -> held = 1; // 스레드가 다른 스레드에 의해 빠져나오게 되고 held를 1로 건다.
}

void unlock(struct lock &l) {
    l -> held = 0;
}
```



## Lock의 문제점

`held`라는 변수 자체거 critical section이 되어 동시 접근이 허용될 수 있다.



## Lock 문제 해결 방법

1. 소프트웨어 알고리즘 (ex. 피터슨 알고리즘)
2. 더 이상 쪼개지지 않는 하드웨어 명령어로 구현하는 방법 (ex. Test and Set)
3. 인터럽트를 disable하고 enable하는 방법

