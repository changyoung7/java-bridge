package bridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    // View 초기화
    private InputView iv = new InputView();
    private OutputView ov = new OutputView();

    // 게임 결과에 따른 사다리 결과맵
    private Map<Integer, List<String>> resultMap = new TreeMap<Integer, List<String>>();

    /**
     * 사용자가 게임을 시작하는 메서드
     */
    public void start() {
        this.ov.printStart();

        BridgeNumberGenerator generator = new BridgeRandomNumberGenerator();
        BridgeMaker maker = new BridgeMaker(generator);

        int bridgeSize = this.iv.readBridgeSize();
        List<String> list = maker.makeBridge(bridgeSize);
        going(list);
    }

    /**
     * 랜덤다리를 전달받아서 게임을 진행한다.
     * <p>
     * 끝까지 성공했거나 재시도를 하지 않으면 게임을 종료한다.
     * 
     * @param list
     */
    public void going(List<String> list) {
        int tryCount = 0;
        boolean isSuccess = false;
        while (true) {
            tryCount++;
            isSuccess = moveResult(list);
            if (isSuccess || !retry()) {
                break;
            }
        }
        finish(isSuccess, tryCount);
    }

    /**
     * 종료 메시지를 출력한다.
     * 
     * @param 성공여부
     * @param 시도횟수
     */
    public void finish(boolean isSuccess, int tryCount) {
        this.ov.printResult(isSuccess, this.resultMap);
        this.ov.printFinish(isSuccess, tryCount);
    }

    /**
     * 랜덤다리결과와 입력값을 비교해서 성공여부를 리턴
     * <p>
     * 중간 실행결과 출력
     * 
     * @param 랜덤다리결과
     * @return 게임성공여부
     */
    public boolean moveResult(List<String> bridgeResult) {
        List<String> upList = new ArrayList<String>();
        List<String> downList = new ArrayList<String>();
        boolean isSuccess = false;
        for (int i = 0; i < bridgeResult.size(); i++) {
            String upDown = bridgeResult.get(i);
            isSuccess = move(upDown, upList, downList);
            this.ov.printMap(this.resultMap);
            if (!isSuccess) {
                break;
            }
        }
        return isSuccess;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String upDown, List<String> upList, List<String> downList) {
        boolean isSuccess = upDown.equals(this.iv.readMoving());
        if ("U".equals(upDown)) {
            moveUp(isSuccess, upList, downList);
        }
        if ("D".equals(upDown)) {
            moveDown(isSuccess, upList, downList);
        }
        this.resultMap.put(1, upList);
        this.resultMap.put(0, downList);
        return isSuccess;
    }

    /**
     * 랜덤다리가 'U'인 경우 성공여부에 따른 출력용 리스트 작성
     */
    public void moveUp(boolean isSuccess, List<String> upList, List<String> downList) {
        if (isSuccess) {
            upList.add("O");
            downList.add(" ");
        }
        if (!isSuccess) {
            upList.add(" ");
            downList.add("X");
        }
    }

    /**
     * 랜덤다리가 'D'인 경우 성공여부에 따른 출력용 리스트 작성
     */
    public void moveDown(boolean isSuccess, List<String> upList, List<String> downList) {
        if (isSuccess) {
            upList.add(" ");
            downList.add("O");
        }
        if (!isSuccess) {
            upList.add("X");
            downList.add(" ");
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry() {
        String command = this.iv.readGameCommand();
        return "R".equals(command);
    }
}
