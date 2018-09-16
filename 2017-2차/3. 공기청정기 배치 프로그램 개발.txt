import java.util.Scanner;

public class Main {
	int H;			//세로 크기
	int W;			//가로 크기
	int map[][] = new int[50][50];		// 지도
	int fill_map[][] = new int[50][50];
	int room_cnt = 0;
	int room_area = 0;
	int max_area = 0;

	class ANS {
		int count;	// room 개수
		int area;		// 가장 넓은 room 넓이
		ANS(int count, int area){
			this.count = count;
			this.area = area;
		}
	};

	public void inputData()  {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt(); 	// 세로크기
		W = sc.nextInt();	// 가로크기
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}
	
	public boolean open(int mapinfo, int dir){
		if((mapinfo&dir)==dir) return false;
		return true;
	}
	
	public void solve(){
		int cnt = 1;
		
		for(int k=0;k<H;k++){
			for(int l=0;l<W;l++){
				if(fill_map[k][l] == 0){
					room_area = 1;
					room_cnt++;
					fill_map[k][l] = room_cnt;
		
			while(true){
				cnt = 0;
				for(int i=0;i<H;i++){
					for(int j=0;j<W;j++){
						if(fill_map[i][j]==room_cnt){
							if(open(map[i][j], 1) && fill_map[i][j-1] != room_cnt ){fill_map[i][j-1] = room_cnt; cnt++;}
							if(open(map[i][j], 2) && fill_map[i-1][j] != room_cnt ){fill_map[i-1][j] = room_cnt; cnt++;}
							if(open(map[i][j], 4) && fill_map[i][j+1] != room_cnt ){fill_map[i][j+1] = room_cnt; cnt++;}
							if(open(map[i][j], 8) && fill_map[i+1][j] != room_cnt ){fill_map[i+1][j] = room_cnt; cnt++;}
						}
					}
				}
				room_area += cnt;
				if(max_area<room_area) max_area = room_area;
				
				if(cnt == 0) break;
					}//end of while

				}
			}
		}//end of for
		
		System.out.println(room_cnt);	//	정답 출력
		System.out.println(max_area);		//	정답 출력
	}
	
	public static void main(String[] args){
		Main m = new Main();
		ANS ans = m.new ANS(0, 0);

		m.inputData();				//	입력 함수

		//	코드를 작성하세요
		m.solve();
		
	
		//System.out.println(ans.count);	//	정답 출력
		//System.out.println(ans.area);		//	정답 출력
	}
}
