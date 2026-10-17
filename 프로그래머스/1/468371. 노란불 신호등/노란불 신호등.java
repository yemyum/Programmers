class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        
        // 전체 몇 초까지 돌아야 하는지 계산하기
        long maxTime = 1;
        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            maxTime = getLCM(maxTime, cycle);  // 헬퍼 메서드 호출
        }
        
        for (long t = 1; t <= maxTime; t++) {
            boolean allYellow = true;
            
            // 전체 돌면서 확인하기
            for (int i = 0; i < n; i++) {
                int g = signals[i][0];
                int y = signals[i][1];
                int r = signals[i][2];
                int cycle = g + y + r;
                
                // 현재 위치(= 인덱스 기준)
                long offset = (t - 1) % cycle;
                
                if (offset < g || offset >= g + y) {
                    allYellow = false;
                    break; // 노란색 아니니까 다음으로!
                }
            }
                
                if (allYellow) {
                    return (int) t; // int형으로 맞춰주기
                }
            
        }
        
        return -1;
    }
    
    // 최소공배수
    // 검사할 최대 범위를 정하기!
    // 각 신호등 패턴이 동시에 처음으로 돌아오는 순간까지만! 그 이후는 반복되니까 더 볼 필요X
    // [예시] A 신호등 4초, B 신호등 6초 주기 -> 12초에 가장 처음 동시에 갱신됨
    // [주의] 노란불 찾는거X 헷갈리지 말기
    private long getLCM(long a, long b) {
        return (a * b) / getGCD(a, b);   // (공식)
    }
    
    // 최대공약수
    private long getGCD(long a, long b) {
        while (b != 0) {
            long temp = a % b;   // [예시] 7 & 1 = 0
            a = b;               // a = 1
            b = temp;            // b = 0
        }
        
        return a;
    }
}