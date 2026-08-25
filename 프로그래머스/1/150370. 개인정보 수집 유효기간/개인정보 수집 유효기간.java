import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        // 약관 타입 + 유효 기간 저장! (key = A, value = 6 * 28)
        Map<String, Integer> termMap = new HashMap<>();
        
        for (String str : terms) {
            String[] split = str.split(" ");          // "A 6"의 공백 제거
            String type = split[0];                   // "A"
            int month = Integer.parseInt(split[1]);   // 6
            
            // 개월 수를 일 수로 바꿔서 저장하기 (6 * 28)
            termMap.put(type, month * 28);
        }
        
        // 오늘 날짜도 일 수 변환!
        int todayTotalDay = totalDay(today);
        // 파기 대상(번호)를 담을 배열 준비
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            String data = split[0];
            String type = split[1];
            
            int priDay = totalDay(data);
            // 만료일 = 수집 일자 + 유효 기간
            int expireDay = priDay + termMap.get(type);
            
            // 오늘 날짜가 만료일 이상이면 파기 대상
            if (todayTotalDay >= expireDay) {
                list.add(i + 1);    // 번호는 1부터 시작하니까 인덱스 + 1
                
            }
        }
        
        // [참고] 직관적이고 가벼운 순수 for문으로 작성해도 ok
        // 1. 리스트에 있는 숫자들 순서대로 올리기
        // 2. 숫자들을 객체껍데기(Integer) 제거 -> 순수 정수 값(int)로 만들어 주기
        // 3. 다시 남은 정수(int)들을 모아서 int[] 배열 상자에 담아주기
        return list.stream().mapToInt(Integer::intValue).toArray();
        
    }
    
    // 날짜 변환 메서드 (문자 -> 숫자)
    private int totalDay(String date) {
        
        // "2022.05.19" 점(.) 기준으로 쪼개기
        // ["2022", "05", "19"]
        String[] dateArr = date.split("\\.");
        
        int year = Integer.parseInt(dateArr[0]);  // 2022
        int month = Integer.parseInt(dateArr[1]); // 5
        int day = Integer.parseInt(dateArr[2]);   // 19
        
        // 년, 월, 일을 전부 '일' 수로 환산해서 합하기
        return (year * 12 * 28) + (month * 28) + day;
    }
}