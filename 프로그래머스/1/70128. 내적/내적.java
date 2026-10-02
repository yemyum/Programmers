class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        
        // a, b 길이가 같으니 그냥 a.length해줘도 됨!
        for (int i = 0; i < a.length; i++) {
            answer += a[i]*b[i];  // 결과를 answer 누적합해주기
        }
        return answer;
    }
}