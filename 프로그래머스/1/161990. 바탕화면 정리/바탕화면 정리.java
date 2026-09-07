class Solution {
    public int[] solution(String[] wallpaper) {
        
        // lux와 luy는 치솟값을 구해야 함!
        // 왜? 가장 위, 왼쪽의 위치에서부터 시작해야하니까
        // 따라서 0이 아닌 가장 큰 정수값으로 세팅해주어야 좌표 갱신이 가능해짐!
        // (0으로 해주면 최솟값이 0이 되니 갱신이 안됨)
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        
        // rdx와 rdy는 최댓값을 구해야 함
        // 왜? 가장 아래, 오른쪽까지 포함해야하니까! 따라서 0으로 세팅해줌
        int rdx = 0;
        int rdy = 0;
        
        // 행 (+ 배열(Array)이니까 괄호 x)
        int rows = wallpaper.length;
        // 열 (+ 문자열(String)이니까 괄호 o)
        int cols = wallpaper[0].length();
        
        // 이중 for문 활용하는 문제!
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (wallpaper[r].charAt(c) == '#') {
                    lux = Math.min(lux, r);
                    luy = Math.min(luy, c);
                    
                    // 파일 #를 감싸기 위해 오른쪽 아래 꼭짓점까지 드래그해야 함!(= r + 1, c + 1)
                    rdx = Math.max(rdx, r + 1);
                    rdy = Math.max(rdy, c + 1);
                    
                }
            }
        }
        
        // 중괄호{}를 활용해 배열에 직접 값 주입하기!
        // [참고] []는 배열 크기, {}는 값 주입, ()는 메서드(함수) 호출 또는 계산 순서 용도
        return new int[]{lux, luy, rdx, rdy};
    }
}