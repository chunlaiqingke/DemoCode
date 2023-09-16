package com.handsome.democode.leetcode.weekMatch;

public class MinimumMovies {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2,2},{1,1,0},{0,1,1}};
        int step = new MinimumMovies().minimumMoves(grid);
        System.out.println(step);
    }

    public int minimumMoves(int[][] grid) {
        int count = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[i][j] == 0) {
                    count++;
                }
            }
        }
        int resStep = 0;
        for(int i = 0; i < count; i++) {
            resStep += minimumMoves2(grid);
        }

        return resStep;
    }

    public int minimumMoves2(int[][] grid) {
        int firsti = -1;
        int firstj = -1;
        label : for(int i = 0;i<3; i++){
            for(int j = 0; j<3;j++) {
                if(grid[i][j] > 1){
                    firsti = i;
                    firstj = j;
                    break label;
                }
            }
        }

        int[][] gridStep = new int[grid.length][grid[0].length];
        for(int r = 0; r<grid.length; r++) {
            for(int c = 0; c<grid[0].length; c++) {
                gridStep[r][c] = Integer.MAX_VALUE/ 2;
            }
        }
        gridStep[firsti][firstj] = 0;
        for(int l = 0; l < grid.length; l ++) {
            for(int w = 0; w<grid.length; w++) {

                if(l > 0) {
                    gridStep[l][w] = Math.min(gridStep[l - 1][w] + 1,gridStep[l][w]);
                }
                if(l < 2) {
                    gridStep[l][w] = Math.min(gridStep[l + 1][w] + 1,gridStep[l][w]);
                }
                if(w > 0) {
                    gridStep[l][w] = Math.min(gridStep[l][w - 1] + 1,gridStep[l][w]);
                }
                if(w < 2) {
                    gridStep[l][w] = Math.min(gridStep[l][w + 1] + 1,gridStep[l][w]);
                }
            }
        }

        for(int l = grid.length - 1; l >= 0; l --) {
            for(int w = grid[0].length -1 ; w >= 0; w--) {

                if(l > 0) {
                    gridStep[l][w] = Math.min(gridStep[l - 1][w] + 1,gridStep[l][w]);
                }
                if(l < 2) {
                    gridStep[l][w] = Math.min(gridStep[l + 1][w] + 1,gridStep[l][w]);
                }
                if(w > 0) {
                    gridStep[l][w] = Math.min(gridStep[l][w - 1] + 1,gridStep[l][w]);
                }
                if(w < 2) {
                    gridStep[l][w] = Math.min(gridStep[l][w + 1] + 1,gridStep[l][w]);
                }
            }
        }
        int resi = -1;
        int resj = -1;
        int step = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[i][j] == 0 && gridStep[i][j] < step) {
                    resi = i;
                    resj = j;
                    step = gridStep[i][j];
                }
            }
        }
        grid[firsti][firstj] = grid[firsti][firstj] - 1;
        grid[resi][resj] = 1;
        return step;
    }

}
