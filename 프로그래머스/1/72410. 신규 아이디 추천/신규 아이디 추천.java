class Solution {
    public String solution(String new_id) {
        
        // [중요] 변수 재할당 누락하지 말 것! (new_id = ?)
        
        // [1단계: 대문자 -> 소문자]
        new_id = new_id.toLowerCase();
        
        // [2단계: 소문자, 숫자, (-), (_), (.) 제외한 모든 문자 제거]
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        // ^(not) 사용해서 제거(=빈 문자열로 바꾸기)
            
        // [3단계: (.)가 2번 이상이면 하나의 (.)로 치환]
        new_id = new_id.replaceAll("\\.{2,}", ".");
        // *마침표를 뜻하는 \. 사용
        
        // [4단계: (.)가 처음이나 마지막에 존재하면 제거]
        if (new_id.startsWith(".")) {
            new_id = new_id.substring(1);  // 이때 0부터 자르면 아무일도 안일어남! 
            // 1로 해줘야 0부터 1직전까지 자른 문자를 남겨줌 (0123 -> 123)
        }
        
        // [참고] substring(start, end)도 자르고 버리는게 아니라 남기는 것이다!
        // [중요] new_id가 빈 문자열이 아닌 경우도 포함하기 (new_id가 "." 단 하나일수도 있음)
        if (!new_id.isEmpty() && new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        
        // [5단계: 빈 문자열이면 "a" 대입]
        if (new_id.isEmpty()) {
            new_id = "a";
        }
        // 또는 if (new_id.equals("")) { new_id = "a"; }
        
        // [6단계: 길이 >= 16이면 첫 15개를 제외한 나머지 문자 제거 + 마지막에 (.)가 오면 마침표도 제거]
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
        }
        
        if (new_id.endsWith(".")) {
            new_id = new_id.substring(0, new_id.length() - 1);
        }
            
        // [7단계: 길이 <= 2이면 길이가 3이 될 때까지 마지막 문자를 반복해서 붙임]
        // [주의] 왜 <= 3이 아닐까? 만약 길이가 2라면 += 문자를 통해 길이가 3이 됨
        // 3 <= 3은 true니까 또 += 문자를 하게 되어 길이가 총 4가 되는 문제 발생!
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1); 
        }
        
        return new_id;
    }
}