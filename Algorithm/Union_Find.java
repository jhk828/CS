import java.io.*;
import java.util.*;
// 210531

/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1

NO
NO
YES
*/

public class Union_Find {
    // 서로소 집합 알고리즘
    // 팀 합치기 연산 -> 0 a b
    // 같은 팀 여부 확인 -> 1 a b
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken()); // 팀 N번까지
        M = stoi(st.nextToken()); // 연산 횟수
        
        // 부모 테이블에서, 부모를 자기 자신으로 초기화
        parent = new int[N+1];
        for (int i=1;i<=N; i++) {
            parent[i] = i;
        }

        // 연산
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int oper = stoi(st.nextToken());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());

            if(oper == 0) {
                // 합집합 (union) 연산
                unionParent(a, b);
            } else if (oper == 1){
                // 찾기 (Find) 연산
                if(findParent(a) == findParent(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

        br.close();
    }

    // 특정 원소가 속한 집합 찾기
    static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때 까지 재귀적 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    // 두 원소가 속한 집합 합치기
    static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int stoi(String str){
        return Integer.parseInt(str);
    }
}
