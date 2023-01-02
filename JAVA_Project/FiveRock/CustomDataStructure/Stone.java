package FiveRock.CustomDataStructure;

public class Stone 
{
    public enum State
    {
        NONE,
        WHITE,
        BLACK,
        NOTSTARTING // 시작하지 않은 상태
    }

    public Stone() {}

    public State StoneState = State.NONE;
}
