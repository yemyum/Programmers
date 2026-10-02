import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {

        int n = message.length();
        
        boolean[] isSpoiler = new boolean[n];  // '스포 구간 맞는지?'
        // 스포일러 부분 꺼내 확인하기
        for (int[] range : spoiler_ranges) {
            int start = range[0];
            int end = range[1];
            for (int i = start; i <= end; i++) {
                isSpoiler[i] = true;   // = 스포구간!
            }
        }
        
        // 공개된 일반 단어
        HashSet<String> on = new HashSet<>();
        // 스포 방지 처리된 단어
        HashSet<String> off = new HashSet<>();
        
        // 공백 기준으로 단어, 시작 위치 탐색하기
        String[] parts = message.split(" ");
        // 현재 위치 추적하는 용도
        int currentIndex = 0;
        
        for (String word : parts) {
            // 빈 문자열 예외 처리 (공백)
            if (word.isEmpty()) {
                currentIndex++;
                continue;
            }
            
            boolean isWordSpoiler = false;
            for (int i = currentIndex; i < currentIndex + word.length() && i < n; i++) {
                if (isSpoiler[i]) {  // 스포 단어에 있다면
                    isWordSpoiler = true;
                    break;   // 스포 글자가 단 하나라도 있다면 멈추기!
                }
            }
            
            if (isWordSpoiler) {    // = true면
                off.add(word);
            } else {
                on.add(word);
            }
            
            // [중요] 다음 위치는 word 길이 + 1(공백 포함해주기)
            currentIndex += word.length() + 1;
        }
        
        int count = 0;  // 리턴할 정답
        
        // '일반 단어에 없는' 진짜 중요한 단어만 개수 새기
        for (String word : off) {
            if (!on.contains(word)) {  // '일반(= on)에 포함되어 있지 않다면'
                count++;
            }
        }
        
        return count;
    }
}