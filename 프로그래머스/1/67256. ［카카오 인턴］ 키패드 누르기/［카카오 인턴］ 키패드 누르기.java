class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        // Pos = Position
        int leftPos = 10;  // * 위치를 10으로 본다.
        int rightPos = 12; // # 위치를 12로 본다.
        
        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                sb.append("L");
                leftPos = num;   // 위치 갱신해주기!
                // [중요] 위치 갱신 해주는 이유: 키패드를 누르고 나면 그 손은 그 숫자(위치)에 멈춰있기 때문! 
                // (+ 나중에 왼손, 오른손 중에 누가 더 가까운지 계산해야하니까)
            }
            else if (num == 3 || num == 6 || num == 9) {
                sb.append("R");
                rightPos = num;
            }
            else {
                if (num == 0) num = 11;
                
                // Dis = Distance
                // getDistance(int from, int to) 메서드 호출!
                int leftDis = getDistance(leftPos, num);
                int rightDis = getDistance(rightPos, num);
                
                // 거리 차이값이 적은 애들 L or R 찍어주기
                if (leftDis < rightDis) {
                    sb.append("L");
                    leftPos = num;  // 위치 갱신은 꼭!
                } 
                else if (rightDis < leftDis) {
                    sb.append("R");
                    rightPos = num;
                }
                else { // 작지도 크지도 않다면 = 같다면 hand 기준!
                    if (hand.equals("left")) {
                        sb.append("L");
                        leftPos = num;
                    }
                    else {
                        sb.append("R");
                        rightPos = num;
                    }
                }
            }
            
        }
    
    return sb.toString();
}
    
    private int getDistance(int from, int to) {
    
    // 숫자 - 1을 해줌으로써 인덱스 기준으로 설정하기
    from -= 1;
    to -= 1;
    
    int fromRow = from / 3;   // 나눈 몫 = 몇 번째 행?
    int fromCol = from % 3;   // 나눈 나머지 = 몇 번째 열?
    
    int toRow = to / 3;
    int toCol = to % 3;
    
    // 각 거리 차이값을 더해줌(Math.abs: 음수로 양수로 만들어줌, 절댓값 계산 역할)
    return Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol);
    
    } 
}