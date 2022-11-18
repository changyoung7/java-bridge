package bridge;

import java.util.List;
import java.util.Map;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printStart() {
        System.out.println("다리 건너기 게임을 시작합니다.\n");
    }

    public void printFinish(boolean isSuccess, int tryCount) {
        if (isSuccess) {
            System.out.println("\n게임 성공 여부: 성공");
        }

        if (!isSuccess) {
            System.out.println("\n게임 성공 여부: 실패");
        }

        System.out.println("총 시도한 횟수: " + tryCount);
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(Map<Integer, List<String>> map) {
        for (int i = map.size(); i > 0; i--) {
            StringBuilder sb = printMap(map.get(i - 1));
            System.out.println(sb.toString());
        }
    }

    /**
     * 한줄의 포맷에 문장을 조립한다.
     */
    private StringBuilder printMap(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sb.append(list.get(i));
                continue;
            }
            sb.append(" | ").append(list.get(i));
        }
        return sb.append(" ]");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean isSuccess, Map<Integer, List<String>> map) {
        if (isSuccess) {
            System.out.println();
        }
        System.out.println("최종 게임 결과");
        printMap(map);
    }
}
