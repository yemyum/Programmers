class Solution {
    public boolean solution(int x) {
        
        int sum = 0;
        int temp = x;
        
        // temp(=x)가 0이 될 때까지 반복!
        while (temp > 0) {
            sum += temp % 10; // 일의 자릿수부터 더하기
            temp /= 10;       // 뒤에서부터 자릿수 줄이기
        }
        
        return x % sum == 0;  // x에 합한 값을 나눈 나머지가 0인지(=나누어 떨어지면 true,  아니면 false)
    }
}