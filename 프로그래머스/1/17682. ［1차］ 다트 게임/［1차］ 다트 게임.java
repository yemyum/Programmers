import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int[] scores = new int[3]; // 기회는 3번(0, 1, 2)
        int idx = -1;              // 인덱스도 맞춰주기, 기회를 사용했으면 ++ -> scores[0]이 됨
        
        // [중요] 숫자 10을 대비해서 문자열로 임시 저장하기
        String numStr = "";
        
        for (int i = 0; i < dartResult.length(); i++) {
            char c = dartResult.charAt(i);  // i번째 문자(숫자)를 꺼내 c에 주입
            
            if (Character.isDigit(c)) {     // c가 숫자면 (0~9)
                numStr += c;  // 문자열에 c 이어붙이기
            }
            
            else if (c == 'S' || c == 'D' || c == 'T') {
                idx++;  // 기회 1번 씀 (0, 1, 2)
                int score = Integer.parseInt(numStr); // 문자열 -> 정수형
                
                if (c == 'S') {
                    // * Math.pow(): 거듭제곱 계산해주는 메서드
                    // [주의] 계산 시 실수형(double)으로 결과물을 뱉기 때문에 int로 미리 처리해주어야 함!, (int) 형변환
                    scores[idx] = (int) Math.pow(score, 1);
                } else if (c == 'D') {
                    scores[idx] = (int) Math.pow(score, 2);
                } else if (c == 'T') {
                    scores[idx] = (int) Math.pow(score, 3);
                }
                
                numStr = "";  // 다음 숫자를 담기 위해 초기화 처리
            }
            
            else if (c == '*') {
                scores[idx] *= 2;          // 현재 점수 2배
                if (idx > 0) {             // 이전 숫자가 있으면
                    scores[idx - 1] *= 2;  // 이전 점수도 2배
                }
                
            } else if (c == '#') {
                scores[idx] *= -1; 
            }
        }
        
        return Arrays.stream(scores).sum(); // 모든 점수 합산
    }
}