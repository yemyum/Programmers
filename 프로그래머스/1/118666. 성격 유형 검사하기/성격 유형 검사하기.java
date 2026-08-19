import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        
        // 왜 해시맵인가?
        // => 각 알파벳(타입)마다 누적 점수가 필요
        // [핵심] 'R의 점수를 가져와라', 'F의 점수를 올려라' 등등
        // 알파벳 글자로 점수를 찾아야 함! (순서가 아닌 '이름'으로 찾기)
        // 따라서 Key의 값을 실시간 갱신하기 위해 HashMap 사용 
        // (한 글자씩 다룰거니까 Character)
        Map<Character, Integer> scoreMap = new HashMap<>();
        // 한 글자씩만 담을 때 유용한 char[] 배열 사용
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
        // 배열에서 하나씩 꺼내 모두 기본값(0) 세팅
        for (char t : types) {
            scoreMap.put(t, 0);
        }
        
        for (int i = 0; i < survey.length; i++) {
            int choice = choices[i];
            
            if (choice < 4) {
                char type = survey[i].charAt(0);
                scoreMap.put(type, scoreMap.get(type) + (4 - choice));
            
            } else if (choice > 4) {
                char type = survey[i].charAt(1);
                scoreMap.put(type, scoreMap.get(type) + (choice - 4));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        // (1) 'R'이 'T'보다 크면 당연히 'R' (true)
        // (2) 'R'이 'T'와 같다면 'R' (사전순)
        sb.append(scoreMap.get('R') >= scoreMap.get('T') ? 'R' : 'T');
        sb.append(scoreMap.get('C') >= scoreMap.get('F') ? 'C' : 'F');
        sb.append(scoreMap.get('J') >= scoreMap.get('M') ? 'J' : 'M');
        sb.append(scoreMap.get('A') >= scoreMap.get('N') ? 'A' : 'N');
        
        return sb.toString(); // 메서드 타입 일치시키기
            
    }
}