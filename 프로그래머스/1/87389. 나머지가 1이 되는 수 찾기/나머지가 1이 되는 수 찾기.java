class Solution {
    public int solution(int n) {
        
        // 자연수니까 1부터 시작
        for (int i = 1; i < n; i++) {
            if (n % i == 1) {
                return i;
            }
        }
        
        // 컴파일 에러 방지용(형식상!)
        return -1;
    }
}