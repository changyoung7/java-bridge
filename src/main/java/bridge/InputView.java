package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println("다리의 길이를 입력해주세요.");
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BridgeConstants.MSG_ERR_PREFIX + e.getMessage());
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println("\n이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String line = Console.readLine().toUpperCase();
        if (!"U".equals(line) && !"D".equals(line)) {
            throw new IllegalArgumentException(
                    BridgeConstants.MSG_ERR_PREFIX + "입력값을 확인하세요. (위: U, 아래: D)");
        }
        return line;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println("\n게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String line = Console.readLine().toUpperCase();
        if (!"R".equals(line) && !"Q".equals(line)) {
            throw new IllegalArgumentException(
                    BridgeConstants.MSG_ERR_PREFIX + "입력값을 확인하세요. (재시도: R, 종료: Q)");
        }
        return line;
    }
}
