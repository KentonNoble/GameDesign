package Creatures;
// define attack types, AtkGrid[2]=Base Attack Structure

class AttackType {

    //blank layout
    public static int[][] Blank() {
        int[][] ATKGrid =
                {{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        return ATKGrid;
    }

    //Direct layout, Base = {0,0,1,0,0}
    public static int[][] Direct() {
        int[][] ATKGrid =
                {{1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1}};
        return ATKGrid;
    }

    //BullDozer layout, Base = {0,1,1,1,0}
    public static int[][] BullDozer() {
        int[][] ATKGrid =
                {{1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1}};
        return ATKGrid;
    }

    //FireBreather layout, Base = {1,0,1,0,1}
    public static int[][] FireBreathing() {
        int[][] ATKGrid =
                {{1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 1}};
        return ATKGrid;
    }

    //Flanker layout, Base = {1,0,0,0,1}
    public static int[][] Flanker() {
        int[][] ATKGrid =
                {{0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0}};
        return ATKGrid;
    }

    //fog layout, Base = {1,1,1,1,1}
    public static int[][] Fog() {
        int[][] ATKGrid =
                {{1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 1, 1, 1}};
        return ATKGrid;
    }


}
