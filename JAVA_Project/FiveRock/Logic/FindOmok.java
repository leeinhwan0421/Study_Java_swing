package FiveRock.Logic;

import FiveRock.UI.UIInformation;

import FiveRock.CustomDataStructure.Node;
import FiveRock.CustomDataStructure.Stone;
import FiveRock.CustomDataStructure.Stone.State;

public class FindOmok 
{
    private static boolean HorizontalThreeCount(Stone[][] stones, Node node, State state, State enemyState)
    {
        for (int j = node.x - 2; j < node.x + 2; j++)
        {
            int ax = CheckOverRange(j);
            int ay = CheckOverRange(node.y);

            if ((ax > 2 && ax < 16) && (stones[ax - 3][ay].StoneState == enemyState || stones[ax + 3][ay].StoneState == enemyState))
                continue;

            {
                if (stones[ax - 1][ay].StoneState == state && stones[ax][ay].StoneState == state &&  stones[ax + 1][ay].StoneState == state) 
                {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean VerticalThreeCount(Stone[][] stones, Node node, State state, State enemyState)
    {
        for (int i = node.y - 2; i < node.y + 2; i++) 
        {
            int ax = CheckOverRange(node.x);
            int ay = CheckOverRange(i);

            if ((ay > 2 && ay < 16) && (stones[ax][ay - 3].StoneState == enemyState || stones[ax][ay + 3].StoneState == enemyState))
                continue;

            {
                if (stones[ax][ay - 1].StoneState == state && stones[ax][ay].StoneState == state && stones[ax][ay + 1].StoneState == state) 
                {
                    return true;
                }
            }
        }
        return false;
    }

    private static int DiagonalThreeCount(Stone[][] stones, Node node, State state)
    {
        int cnt = 0;

        int ax = node.x; 
        int ay = node.y;

        if (ax <= 1)
            ax = 2;
        else if (ax >= 18)
            ax = 17;

        if (ay <= 1)
            ay = 2;
        else if (ay >= 18)
            ay = 17;

        if (stones[ax][ay].StoneState == state && stones[ax + 1][ay + 1].StoneState == state &&  stones[ax + 2][ay + 2].StoneState == state) 
            cnt++;

        if (stones[ax][ay].StoneState == state && stones[ax - 1][ay - 1].StoneState == state &&  stones[ax - 2][ay - 2].StoneState == state) 
            cnt++;
            
        if (stones[ax][ay].StoneState == state && stones[ax - 1][ay + 1].StoneState == state &&  stones[ax - 2][ay + 2].StoneState == state) 
            cnt++;

        if (stones[ax][ay].StoneState == state && stones[ax + 1][ay - 1].StoneState == state &&  stones[ax + 2][ay - 2].StoneState == state) 
            cnt++;

        return cnt;
    }

    public static boolean FindThreeThreeStarPoint(Stone[][] stones, Node node, State state)
    {
        stones[node.x][node.y].StoneState = state;

        State enemyState;

        if (state == State.BLACK)
            enemyState = State.WHITE;
        else
            enemyState = State.BLACK;

        System.out.print(enemyState);
        boolean isHorizontal = HorizontalThreeCount(stones, node, state, enemyState);
        boolean isVertical = VerticalThreeCount(stones, node, state, enemyState);
        int isDiagonaltal = DiagonalThreeCount(stones, node, state);

        if (isDiagonaltal >= 2)
        {
            return true;
        }

        if ((isHorizontal && isVertical) || (isHorizontal && isDiagonaltal >= 1) || (isDiagonaltal >= 1 && isVertical))
        {
            return true;
        }

        return false;
    }

    public static boolean FindFiveStar(Stone[][] stones)
    {
        int [][] d = {{0,1}, {1,0}, {1,1}, {-1,1}};

        for (int i = 0; i < UIInformation.getInstance().mapSize.x; i++)
        {
            for (int j = 0; j < UIInformation.getInstance().mapSize.y; j++)
            {
                if (stones[i][j].StoneState == State.WHITE || stones[i][j].StoneState == State.BLACK)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        int ax = i;
                        int ay = j;
                        int cnt = 1;

                        while(true)
                        {
                            ax += d[k][0];
                            ay += d[k][1];

                            if ( 0 <= ax && ax < UIInformation.getInstance().mapSize.x && 0 <= ay && ay < UIInformation.getInstance().mapSize.y) 
                            {
                                if (stones[i][j].StoneState == stones[ax][ay].StoneState)
                                    cnt++;
                                else
                                    break;
                            }
                            else break;
                        }

                        ax = i;
                        ay = j;

                        while(true)
                        {
                            ax -= d[k][0];
                            ay -= d[k][1];

                            if ( 0 <= ax && ax < UIInformation.getInstance().mapSize.x && 0 <= ay && ay < UIInformation.getInstance().mapSize.y)
                            {
                                if (stones[i][j].StoneState == stones[ax][ay].StoneState)
                                    cnt++;
                            else
                                break;
                            }
                            else break;
                        }

                        if (cnt == 5)
                        {
                            Stone.State state = stones[i][j].StoneState;
                            System.out.println("Winner : " + state);
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static int CheckOverRange(int n)
    {
        if (n <= 0)
        {
            return 1;
        }
        else if (n >= 19)
        {
            return 18; 
        }
        else
        {
            return n;
        }
    }
}
