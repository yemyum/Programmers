import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        int n = friends.length;
        
        // [중요] 친구 이름 -> 인덱스 형식으로 매핑해주기 (String -> int)
        // 왜? 2차원 배열에는 정수형만 들어올 수 있어서!
        // history["muzi"]["frodo"] => X (불가능)
        // history[0][1] => O (가능)
        Map<String, Integer> nameToIndex = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            // 가장 첫번째 친구 -> 인덱스 0으로 지정
            nameToIndex.put(friends[i], i);
        }
        
        // 주고 받은 기록 담기
        // (ex. 0번이 누구에게 줬고, 받았는지 확인할려고!)
        int[][] history = new int[n][n];
        
        // 선물 지수 담기 (준 선물 - 받은 선물)
        int[] giftScore = new int[n];
        
        // 파싱해주기 (공백 제거)
        for (String gift : gifts) {
            String[] parts = gift.split(" "); // 공백 기준 쪼개기
            String giver = parts[0];           // 첫번째 = 준 사람
            String receiver = parts[1];        // 두번째 = 받은 사람
            
            // value(= 친구 이름에 해당하는 인덱스 값) 가져오기
            int giverIdx = nameToIndex.get(giver);
            int receiverIdx = nameToIndex.get(receiver);
            
            history[giverIdx][receiverIdx]++; // 2차원 배열 횟수 올리기
            giftScore[giverIdx]++;            // 줬으니까 + 1
            giftScore[receiverIdx]--;         // 받았으니까 - 1
        }
        
        // 다음 달에 각자 받을 선물 수 카운트하기 (가장 큰 값 비교할 때 필요함!)
        int[] nextMonthGifts = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // i가 j한테 더 많이 줬다면
                if (history[i][j] > history[j][i]) {
                    nextMonthGifts[i]++;  // i가 받음
                }
                
                // j가 i한테 더 많이 줬다면
                else if (history[j][i] > history[i][j]) {
                    nextMonthGifts[j]++;  // j가 받음
                }
                // 주고 받은 기록이 없거나 수가 같다면
                else {
                    if (giftScore[i] > giftScore[j]) {
                        nextMonthGifts[i]++;
                    } else if (giftScore[i] < giftScore[j]) {
                        nextMonthGifts[j]++;
                    }
                    // 선물 지수가 같으면 아무도 안 받음!
                }
            }
        }
        
        int maxGift = 0;
        for (int gift : nextMonthGifts) {
            maxGift = Math.max(maxGift, gift);
        }
        
        return maxGift;
    }
}