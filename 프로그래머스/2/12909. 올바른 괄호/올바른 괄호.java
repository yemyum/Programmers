import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);  // 한 개씩 꺼내기
            
            // 열린 괄호면
            if (c == '(') {
                stack.push(c);  // 스택에 넣어두기
            }
            // 닫힌 괄호면
            else {
                if(stack.isEmpty()) {  // 스택이 비어있다면
                    return false;
                }
                
                stack.pop();  // '()' 서로 짝이 됐으니 스택에서 제거
            }
        }
        
        return stack.isEmpty();  // 비었으면 true, 아니면 false => 마지막 남은 괄호가 있는지 검사
    }
}