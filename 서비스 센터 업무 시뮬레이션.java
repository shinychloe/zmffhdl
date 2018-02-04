import java.util.Scanner;

public class Main {
	
	int N,Q;
	int[] T;
	int[] engineer;
	
	void inputData(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();				// 엔지니어의 수
		Q = sc.nextInt();				//	고객의 수
		engineer = new int[N+1];
		T = new int[Q+1];
		for(int i=1;i<=Q;i++){
			T[i] = sc.nextInt();		// 고객의 업무처리 시간
		}
		sc.close();
	}
	
	public void solve(){
		int min_eng = 1;
		
		for(int i=1;i<=Q;i++){	
				min_eng = 1;
				for(int j=2;j<=N;j++){
					if(engineer[j] < engineer[min_eng]){
						min_eng = j;
					}
				}
				engineer[min_eng] += T[i];
				//System.out.println(engineer[1] +" "+engineer[2]);
		}
		
		int max = 0;
		for(int i=1;i<=N;i++){
			if(max < engineer[i])
				max = engineer[i];
		}
		System.out.println(max);
	}
		
	public static void main(String[] args) {
		int sol=0;
		Main m = new Main();
		
		m.inputData();				//	입력 함수
		
		//	코드를 작성하세요
		m.solve();

		
		//System.out.println(sol);	//	정답 출력
	}
}
