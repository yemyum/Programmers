class Solution {
    public String solution(String phone_number) {
        
        int len = phone_number.length();
        
        // 길이 - 4까지 *로 반복해서 채우고, 뒤 4자리 오려와서 붙이기!
        // "반복할 문자열".repeat(반복횟수)
        return "*".repeat(len - 4) + phone_number.substring(len - 4);
    }
}