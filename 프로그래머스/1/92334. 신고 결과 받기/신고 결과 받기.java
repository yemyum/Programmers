import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        int n = id_list.length;
        int[] answer = new int[n];
        
        // 이름 인덱스화
        Map<String, Integer> idMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idMap.put(id_list[i], i);
        }
        
        // 중복 신고 제거 (HashSet 활용)
        Set<String> reportSet = new HashSet<>(Arrays.asList(report));
        // 2차원 배열 활용
        boolean[][] isReported = new boolean[n][n];  // 처음 기본값 = false
        // 각 유저별 신고 당한 총 횟수
        int[] reportedCount = new int[n];
        
        // 신고 정보 기록하기
        for (String str : reportSet) {
            // ["muzi frodo"]
            String[] parts = str.split(" ");
            int reporterIdx = idMap.get(parts[0]);        // "muzi" = 0
            int reportedIdx = idMap.get(parts[1]);        // "frodo" = 1
            
            isReported[reporterIdx][reportedIdx] = true;  // 신고했으니 true 처리
            reportedCount[reportedIdx]++;  // 신고 당한 사람 카운트 + 1
        }
        
        // k번 이상 신고 당한 유저를, 신고한 사람들에게 메일 발송하기
        // (1) 신고 당한 사람(열)을 0번부터 n - 1번까지 확인
        for (int reported = 0; reported < n; reported++) {
            // (1 - 1) 신고 당한 횟수가 k 이상(= 정지 대상)이라면
            if (reportedCount[reported] >= k) {
                // 그 사람을 신고한 모든 사람(reporter)을 찾아 메일 + 1
                // (2) 해당 피신고자 '열'의 '위 -> 아래(행)' 훑으며 신고자 확인
                for (int reporter = 0; reporter < n; reporter++) {
                    // (2 - 1) 신고한 당사자가 맞다면
                    // (isReported[reporter][reported] == true)
                    if (isReported[reporter][reported]) {
                        answer[reporter]++;  // 신고자의 메일 개수 + 1
                    }
                }
            }
        }
        
        return answer;
        
    }
}