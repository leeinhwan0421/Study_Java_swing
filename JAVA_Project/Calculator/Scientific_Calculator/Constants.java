package Calculator.Scientific_Calculator;

/// 다 우겨넣는 유틸리티 클래스입니다. 예외처리나 보기 흉한 구문을 숨기는데 쓰는 곳.
/// Design Pattern을 이용하지 않았을 때에는 이렇게 하드코딩한 흔적을 지울 수 밖에 없다.
public class Constants
{
    public static final int INPUT_LIMIT = 12;
    public static final String title_name = "Scientific_Calculator";

    // 이거 써서 연산자 체크 하시면 됩니다
    enum Operator_Mode
    {
        NONE,                   // 없음
        PLUS,                   // 덧셈
        SUB,                    // 뺄셈
        MULTI,                  // 곱셈
        DIV,                    // 나눗셈
        POW,                    // 제곱
        PER,                    // 백분율
        ROOT,                   // 루트
    }

    enum Run_Mode 
    {
        NONE,
        EQUAL,
        INPUT_NUMBER,
        INPUT_OPERATOR,
    }

    public static Operator_Mode SetOperator_Mode(String s)
    {
        switch(s)
        {
            case "+":
            return Operator_Mode.PLUS;

            case "-":
            return Operator_Mode.SUB;

            case "*":
            return Operator_Mode.MULTI;
            
            case "÷":
            return Operator_Mode.DIV;

            case "²":
            return Operator_Mode.POW;

            case "½":
            return Operator_Mode.ROOT;

            case "%":
            return Operator_Mode.PER;

            default:
            return Operator_Mode.NONE;
        }
    }

    public static String Modified_Input(String s)
    {
        switch (s)													
		{
			case "x²":
                s = "²";
			break;
			case "x½":
                s = "½";
			break;
		}

        return s;
    }

    // Runmode는 상수로 전달해도 되는 것입니다.
    public static boolean Requirements(String text, Constants.Run_Mode mode)
    {
        char lastText = ' ';

        if (text.length() != 0)
            lastText = text.charAt(text.length() - 1);

        switch(mode)
        {
            case EQUAL:
            if (text.length() == 0)                                                         // 길이가 0인경우 연산이 불가능하니 연산 X
                return false;                                                               // 마지막 기호가 십진수, '²', '%', '½' 가 아니면 연산 불가능
            if (!Character.isDigit(lastText) && (lastText != '²' && lastText != '%' && lastText != '½'))
                return false;
            break;
            case INPUT_NUMBER:
                if (text.length() >= Constants.INPUT_LIMIT) 
                    return false;     	                                                    // 숫자인 경우 INPUT_LIMIT의 자릿수 제한을 둔다
                if (lastText == '%' || lastText == '²' || lastText == '½')                  // 특정 기호인 경우 적지 못하도록
                    return false;
            break;
            case INPUT_OPERATOR:
                if (text.length() == 0)
                    return false;													        // 숫자가 아닌경우 맨 처음에 입력되면 안됨.

                if (text.length() >= Constants.INPUT_LIMIT - 1) 			                // 숫자가 아닌경우 뒤에 한자리 숫자를 입력 할 수 있게끔 Limit - 1의 제한을 둔다
                    return false;
        
                if (!Character.isDigit(lastText) && 	                                    // 숫자가 아닌경우 앞에 연산자가 있을 시에 쓰지 못하도록 한다
                    lastText != '²' && lastText != '½' && lastText != '%') 					// 단, 예외적으로 '²', '½', '%' 기호는 사용 할 수 있음
                    return false;
            break;
            default:
                return false;                                                              // 모종의 이유로 할당되지 않았을 때에는 실행하지 않도록.
        }

        return true;
    }
}